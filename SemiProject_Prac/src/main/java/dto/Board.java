package dto;

import java.sql.Date;

public class Board {
	
	private int boardid; 
	private String title;
	private String write;
	private String contents;
	private Date write_date;
	private int view_count;
	private int comment_count; 
	
	public Board(int boardid, String title, String write, String contents, Date write_date, int view_count) {
		this.boardid = boardid;
		this.title = title;
		this.write = write;
		this.contents = contents;
		this.write_date = write_date;
		this.view_count = view_count;
	}
	public Board(String title, String write, String contents, int view_count) {
		this.title = title;
		this.write = write;
		this.contents = contents;
		this.view_count = view_count;
	}
	public Board(int boardid,String title,String contents) {
		this.boardid = boardid;
		this.title = title;
		this.contents = contents;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	
	
	
	
}
