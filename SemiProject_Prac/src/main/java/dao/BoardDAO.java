package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.Board;
import Util.BoardConfig;

public class BoardDAO {
	
	private static BoardDAO bd;
	public static BoardDAO getInstance() {
		if(bd ==null) {
			bd= new BoardDAO();
		}
		return bd;
	}
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle2");
		return ds.getConnection();
	}
	
	public int insert(Board temp)throws Exception {
		String sql = "insert into Board values(board_id.nextval,?,?,?,sysdate,?)";
		System.out.println("insert SQL");
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
				){
			psmt.setString(1, temp.getTitle());
			psmt.setString(2, temp.getWrite());
			psmt.setString(3, temp.getContents());
			psmt.setInt(4, temp.getView_count());
			int result = psmt.executeUpdate();
			return result;
		}catch(Exception e){
			e.getStackTrace();
		}
		
		return 0;
		
	}
	
	public List<Board> printAll() throws Exception{
		List<Board> li = new ArrayList<Board>();
		String sql = "select * from Board";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			){
			
			while(rs.next()) {
				li.add(new Board(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6)));
			}
			return li;
		}
	}
	
	public Board printOne(int id) throws Exception{
		String sql = "select * from Board where board_id =?";
		Board temp = null;
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			){
			psmt.setInt(1, id);
			try(ResultSet rs = psmt.executeQuery();){
				if(rs.next()) {
				temp = new Board(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6));
			}
			}
			return temp;
		}
		
	}
	
	public int update(Board temp) throws Exception{
		String sql = "update board set title=?,contents=?,write_date=sysdate where board_id=?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
				){
				psmt.setString(1, temp.getTitle());
				psmt.setString(2, temp.getContents());
				psmt.setInt(3, temp.getBoardid());
				return psmt.executeUpdate();
				}
			}
	
	public int delete(String id) throws Exception{
		String sql = "delete from board where board_id=?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			){
			psmt.setString(1,id);
			return psmt.executeUpdate();
				}
			}
	
	public void increamentViews(String id) throws Exception{
		String sql = "select view_count from board where board_id=?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			){
				
			}
		}
	
	private int getRecordCount() throws Exception{
		String sql = "select count(*) from board";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
				){
				 	if(rs.next()) {
				 		return rs.getInt(1);
				 	}
				 	
				}
		return 0;
	}
	
	public List<String> getPageNavi(int currentPage) throws Exception{
		 int recordTotalCount = getRecordCount();
		 int recordCountPerPage = BoardConfig.RECORD_COUNT_PER_PAGE; // 한 페이지당 보여줄 게시글의 개수
		 int naviCountPerPage =BoardConfig.NAVI_COUNTER_PER_PAGE ; //내 위치 페이지를 기준으로 시작부터 끝가지의 페이지가 총 몇개.
		 int pageTotalCount = recordTotalCount % recordCountPerPage !=0? (recordTotalCount / recordCountPerPage)+1: recordTotalCount / recordCountPerPage;  // 현재 내가 위치하는 페이지 번호.
		 if(currentPage >pageTotalCount) currentPage = pageTotalCount;
		 else if(currentPage < 1 ) currentPage =1;
		 int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
		 int endNavi = startNavi + (naviCountPerPage-1);
		 if(endNavi > pageTotalCount) endNavi = pageTotalCount;
		 List<String> pageNavi = new ArrayList();
		 if(currentPage != 1) {pageNavi.add("<");}
		 for(int i=startNavi; i<=endNavi; i++) {
			 pageNavi.add(String.valueOf(i));
		 }
		 if(endNavi != pageTotalCount) {pageNavi.add(">");}
		 return pageNavi;
	}
	
	public List<Board> getPageList(int start, int end) throws Exception{
		String sql = "select*from(select row_number() over(order by board_id desc) rnum,board_id,title,writer,contents,write_date,view_count from board) where rnum between ? and ?";
		List<Board> li = new ArrayList<Board>();
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setInt(1,start);
			psmt.setInt(2,end);
			try(ResultSet rs = psmt.executeQuery()){
				while(rs.next()) {
				li.add(new Board(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getInt(7)));
			}
			}
			return li; 
		}
		
	}
	
	public List<Board> getSearchList(int start, int end,String category,String search) throws Exception{
		String sql = "select * from(select row_number() over(order by board_id desc) rnum,board_id,title,writer,contents,write_date,view_count from board where "+category+" like ?) where (rnum between ? and ?)";
		List<Board> li = new ArrayList<Board>();
		try(Connection conn  = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);){
			psmt.setString(1, "%"+search+"%");
			psmt.setInt(2,start);
			psmt.setInt(3,end);
			try{
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					li.add(new Board(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getInt(7)));
				}}catch(Exception e) {
					e.getStackTrace();
				}
				return li;
			}
		}
	
	public List<Board> getAllList() throws Exception{
		String sql = "select * from board";
		List<Board> li = new ArrayList();
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();){
			while(rs.next()) {
				li.add(new Board(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6)));
			}
			return li;
		}
	}
	
	public List<Board> likeFacebook(int viewcount,int index) throws Exception{
		String sql = "select*from(select row_number() over(order by board_id desc) rnum,board_id,title,writer,contents,write_date,view_count from board) where rnum between ? and ?";
		List<Board> li = new ArrayList();
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setInt(1, 1+viewcount*(index-1));
			psmt.setInt(2, viewcount*index);
			try(ResultSet rs = psmt.executeQuery()){
				while(rs.next()) {
					li.add(new Board(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getInt(7)));
				}
			}
			return li;
		}
	}
	
	}
	
	
//	public static void main(String[] args) throws Exception{
//		
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
//			 	 
//			String sql = "insert into Board values(board_id.nextval,?,?,?,sysdate,?)";
//			for(int i=100; i<=200; i++) {
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, "테스트 제목. "+i);
//			psmt.setString(2, "테스트 내용입니다. "+i);
//			psmt.setString(3, "pigi1004");
//			psmt.setInt(4, 0);
//			psmt.executeUpdate();
//		}
	
	
	
	
	
	


