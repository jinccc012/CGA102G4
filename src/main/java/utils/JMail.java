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

//tmuvjhytwgwnclin
public class JMail {
    static String host = "smtp.gmail.com"; 
    static int port = 587;
    static final String username = "g4cj84@gmail.com";
    static final String password = "tmuvjhytwgwnclin"; //GMAIL 需要使用兩段驗證

    public static void Send_mail(String To_mail, String Mail_title, String Mail_text) {
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //設定信箱服務器
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To_mail));
            message.setSubject(Mail_title);//信件標題
            message.setContent(Mail_text, "text/html;charset=UTF-8");//信件內容
            Transport transport = session.getTransport("smtp");
            transport.connect(host, port, username, password);
            Transport.send(message);
            System.out.println(" " + To_mail + "  " + Mail_title + " " + "寄送email結束.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}