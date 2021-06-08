package dto;

public class Member {
	
	private String email;
	private String pw;
	private String phone;
	private String nickname;
	private String snsCheck;
	
	public Member(String email, String pw, String phone, String nickname) {
		this.email = email;
		this.pw = pw;
		this.phone = phone;
		this.nickname = nickname;
	}
	public Member(String email, String pw, String phone, String nickname, String snsCheck) {
		this.email = email;
		this.pw = pw;
		this.phone = phone;
		this.nickname = nickname;
		this.snsCheck  = snsCheck;
	}
	public Member(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSnsCheck() {
		return snsCheck;
	}
	public void setSnsCheck(String snsCheck) {
		this.snsCheck = snsCheck;
	}
}
