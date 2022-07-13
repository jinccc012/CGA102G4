package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	public void sendMail(String to,String subject,String messageText,String member) {
		 try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");

	       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
	       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "ixlogic.wu@gmail.com";
		     final String myGmail_password = "ddjomltcnypgcstn";
			   Session session = Session.getInstance(props, new Authenticator() {
				   protected PasswordAuthentication getPasswordAuthentication() {
					   return new PasswordAuthentication(myGmail, myGmail_password);
				   }
			   });

			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(myGmail));
			   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			  
			   //設定信中的主旨  
			   message.setSubject(subject);
			   //設定信中的內容 
			   message.setText(messageText);

			   Transport.send(message);
			   System.out.println("傳送成功!");
	     }catch (MessagingException e){
		     System.out.println("傳送失敗!");
		     e.printStackTrace();
	     }
	   }
		
		 public static void main (String args[]){

	      String to = "asd28018807@gmail.com";
	      
	      String subject = "FamilyRent得標通知";
	      
	      String ch_name = "焦豪";
	    //  String passRandom = "111";
	      String messageText = ch_name +"先生/小姐:您好 ，恭喜你得標，請於三天內完成匯款，並通知業務匯款已完成。"; 
	       
	      MailService mailService = new MailService();
	      mailService.sendMail("asd28018807@gmail.com", "FamilyRent得標通知", "先生/小姐:您好 ，恭喜你得標，請於三天內完成匯款，並通知業務匯款已完成。",ch_name);

	   }

	}


