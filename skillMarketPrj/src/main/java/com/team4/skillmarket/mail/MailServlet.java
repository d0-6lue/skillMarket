package com.team4.skillmarket.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mail-id")
public class MailServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String recipient = req.getParameter("memberEmail");
 
        // 1. 발신자의 메일 계정과 비밀번호 설정
        final String user = "j10345678@gmail.com";
        final String password = "ygucyddaxrkeywem";
 
        // 2. Property에 SMTP 서버 정보 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
     // 인증 번호 생성기
 		StringBuffer temp = new StringBuffer();
 		Random rnd = new Random();
 		for (int i = 0; i < 10; i++) {
 			int rIndex = rnd.nextInt(3);
 			switch (rIndex) {
 			case 0:
 				// a-z
 				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
 				break;
 			case 1:
 				// A-Z
 				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
 				break;
 			case 2:
 				// 0-9
 				temp.append((rnd.nextInt(10)));
 				break;
 			}
 		}
 		String AuthenticationKey = temp.toString();
 		System.out.println(AuthenticationKey);
        
 
        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
 
        // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
        // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.
 
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
 
            // 수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
 
            // Subject
            message.setSubject("안녕하세요 스킬 마켓 인증 번호입니다.");
 
            // Text
            message.setText("인증번호는 "+AuthenticationKey);
 
            Transport.send(message);    // send message
 
 
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        out.write(AuthenticationKey + "");
        
        HttpSession saveKey = req.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
    }
}