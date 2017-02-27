package com.faceontalk.scheduler;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.faceontalk.member.service.MemberService;

@Component
public class Scheduler {	
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Inject
	MemberService memberService;
	
	
//	@Scheduled(fixedRate=10000)
//	public void test() {
//		logger.info("schduler test..");		
//	}
	
	@Scheduled(cron="0 20 1 * * *") 
	public void removeExceed() {
		try {
			memberService.removeExpiredAuth();			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	

}
