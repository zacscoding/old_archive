package com.faceontalk.controller.feed;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.faceontalk.service.feed.FeedService;
import com.faceontalk.service.member.MemberService;

@Controller
public class UsersFeedController {
	
	private Logger logger = LoggerFactory.getLogger(UsersFeedController.class);
	
	@Inject
	FeedService feedService;	
	@Inject
	MemberService memberService;
	
	
	
	
	

}
