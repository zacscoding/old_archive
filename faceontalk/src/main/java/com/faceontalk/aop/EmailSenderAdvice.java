//package com.faceontalk.aop;
//
//import java.util.Date;
//
//import javax.inject.Inject;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.faceontalk.domain.member.MemberVO;
//import com.faceontalk.email.EmailSenderUtil;
//import com.faceontalk.email.RegistrationNotifierService;
//import com.faceontalk.persistence.member.MemberDAO;
//
//@Component	 //스프링 빈 인식 위해 설정
//@Aspect 	//AOP 기능을 하는 클래스의 선언에 추가
//public class EmailSenderAdvice {
//	
//	private static final Logger logger = LoggerFactory.getLogger(EmailSenderAdvice.class);
//	
//	@Inject
//	private RegistrationNotifierService registrationNotifierService;
//	
//	@Inject
//	private MemberDAO memberDAO;
//	
//	//@Around("execution(* com.faceontalk.controller.member.MemberController.registPOST(..))")	
//	public Object sendEmail(ProceedingJoinPoint pjp) throws Throwable {		
//		logger.info("EmailSenderAdvice....");		
//		try {
//			Object result = pjp.proceed();
//			
//			Runnable thread = new Runnable() {
//				@Override
//				public void run() {
//					Object[] objects = pjp.getArgs();
//					for(Object obj : objects) {
//						if(obj instanceof MemberVO) {
//							MemberVO vo = (MemberVO) obj;							
//							//create auth token
//							String auth_token = EmailSenderUtil.createToken();
//							int amount = 60*60*24; //하루
//							Date auth_limit = new Date(System.currentTimeMillis()+(1000*amount));
//							try {
//								memberDAO.registerAuthToken(vo.getUser_id(),auth_token,auth_limit);
//								//send email authentication with uri
//								registrationNotifierService.sendMail(vo, auth_token);
//							} catch(Exception e) {
//								e.printStackTrace();
//							}																
//							break;
//						}				
//					}					
//				}
//			};
//			thread.run();						
//			return result;
//		} finally {
////			//이메일 전송 실행
////			Object[] objects = pjp.getArgs();
////			for(Object obj : objects) {
////				if(obj instanceof MemberVO) {
////					MemberVO vo = (MemberVO) obj;
////					
////					//create auth token
////					String auth_token = EmailSenderUtil.createToken();
////					int amount = 60*60*24; //하루
////					Date auth_limit = new Date(System.currentTimeMillis()+(1000*amount));	
////					memberDAO.registerAuthToken(vo.getUser_id(),auth_token,auth_limit);
////					
////					//send email authentication with uri
////					registrationNotifierService.sendMail(vo, auth_token);		
////					break;
////				}				
////			}
//			//logger.info(Arrays.toString(pjp.getArgs()));	
//			
//		}		
//	}
//}
