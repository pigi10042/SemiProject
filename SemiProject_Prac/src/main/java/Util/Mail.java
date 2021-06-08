package Util;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail  {
	
	private static final String host = "smtp.naver.com";
	private static final String user = user;
	private static final String password  = pw;
	private static int sendNumber;
	
	public static void confirmNumber(String to) {
		sendNumber = (int)(Math.random()*10000+1);
		Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", 587);
	    props.put("mail.smtp.auth", "true");
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);}
	      });
	    try {

	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(user));
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	         //제목
	         message.setSubject("[KH Story] 인증번호 발송.");
	         //내용
	         message.setText("[KH Story] 인증번호 발송드립니다.\n 아래 번호를 기입해주세요.\n[인증번호]:"+sendNumber);
	         Transport.send(message);
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public static boolean checkNumber(int Number) {
		return sendNumber==Number;
	} 
	


}

