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
	
	private static int sendNumber;
	
	public static void confirmNumber(String to) {
		sendNumber = (int)(Math.random()*10000+1);
		Properties props = new Properties();
		String host = "smtp.naver.com";
		String user = "pigi-1004@naver.com";
		String password  = "dz1178512";
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", 465);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
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

