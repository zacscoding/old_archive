package com.faceontalk.scheduler;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.faceontalk.member.service.MemberService;

/*
 * 1)인증 기간 지난 것, tbl_member, tbl_email_auth 삭제.
 * 2)파일 업로드 시 썸네일 생성 후 POST 하지 않은 사진 삭제. 
 */
@Component
public class Scheduler {	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);	
	@Inject
	MemberService memberService;
	
	@Scheduled(cron="0 20 1 * * *") 
	public void removeExceed() {
		try {
			memberService.removeExpiredAuth();			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	

}
