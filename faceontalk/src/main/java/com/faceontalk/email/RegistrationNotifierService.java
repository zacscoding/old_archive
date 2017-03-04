package com.faceontalk.email;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.util.UriComponentsBuilder;

import com.faceontalk.domain.member.MemberVO;

public class RegistrationNotifierService {
	@Autowired
	private JavaMailSender mailSender;	
	
	private static final String AUTH_URI = "http://localhost:8080/accounts/confirm_verification";	
	public void sendMail(MemberVO vo,String auth_token) throws Exception /*//MessagingException,UnsupportedEncodingException*/{
		try {
			MimeMessage message = mailSender.createMimeMessage();		
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"utf-8");
			
			messageHelper.setSubject("[FaceOnTalk] 회원 가입 인증 안내");
			String queryURI = makeQuery(vo.getUser_id(),auth_token);
			
			String htmlContent = vo.getUser_id()+"님의 이메일 인증(아래 링크를 클릭해주세요.)<br>:"
					+ "<a href='"+queryURI+"'>"+queryURI+"</a>";
			
			messageHelper.setText(htmlContent,true);
			messageHelper.setFrom("admin@mypet.com","admin");
			messageHelper.setTo(new InternetAddress(vo.getUser_email(),vo.getUser_id(),"utf-8"));			
			mailSender.send(message);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String makeQuery(String user_id,String auth_token) {		
		return UriComponentsBuilder.fromPath(AUTH_URI)
					.queryParam("user_id",user_id)
					.queryParam("auth_token",auth_token)
					.build()
					.toUriString();								
	}
}
