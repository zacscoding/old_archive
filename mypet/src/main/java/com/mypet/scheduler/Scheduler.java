package com.mypet.scheduler;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.service.MemberService;

@Component
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Inject
	MemberService memberService;
	
	@Scheduled(cron="0 20 1 * * *") 
	//@Scheduled(cron="0 30 2 * * * * *")
	public void removeExceed() {
		try {
			memberService.removeExceedAuth();			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	/*
	@Scheduled(cron="0 19 1 * * *")
	public void test1() {
		logger.info("test1");
	}
	*/
	

}
