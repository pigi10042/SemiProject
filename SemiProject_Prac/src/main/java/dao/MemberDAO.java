package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.Member;

public class MemberDAO {
	
	private static MemberDAO md;
	public static MemberDAO getInstance() {
		if(md ==null) {
			md= new MemberDAO();
		}
		return md;
	}
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle2");
		return ds.getConnection();
	}
	public int insert(Member mb)throws Exception {
		String sql = "insert into kakamember values(?,?,?,?,?)";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);){
			psmt.setString(1, mb.getEmail());
			psmt.setString(2, mb.getPw());
			psmt.setString(3, mb.getNickname());
			psmt.setString(4, mb.getPhone());
			psmt.setString(5, mb.getSnsCheck());
			return psmt.executeUpdate();
		}
	}
	
	
	public boolean login(Member mb) throws Exception{
		String sql = "select * from kakamember where email=?";
		if(!this.snsCheck(mb)) {
		sql +="and pw =?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);){
			psmt.setString(1, mb.getEmail());
			psmt.setString(2, mb.getPw());
			return psmt.executeQuery().next();
		}
		}
		else {
			try(Connection conn = this.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);){
				psmt.setString(1, mb.getEmail());
				return psmt.executeQuery().next();
				}
		}
			
	}
	
	private boolean snsCheck(Member temp) throws Exception{
		String sql = "select KAKAOCHECK from kakamember where email=?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);){
			psmt.setString(1, temp.getEmail());
			String check = psmt.executeQuery().getString(1);
			return check.equals("1")? true :false;
		}
	}
	
	public boolean dupliId(String email) throws Exception {
		String sql = "select * from member where email =?";
		try(Connection conn = this.getConnection();
			PreparedStatement psmt = conn.prepareStatement(sql);
				){
				psmt.setString(1, email);
				return psmt.executeQuery().next();
		}
	}

}
