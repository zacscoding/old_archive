package com.faceontalk.feed.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;
import com.faceontalk.feed.service.FeedService;
import com.faceontalk.feed.util.HashTagHelper;

@Controller
@RequestMapping(value="/feed")
public class FeedController {	
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);	
	@Inject
	private FeedService feedService;
	
	////////////////////////
	// get feed lists
	///////////////////////		
	//search
	@RequestMapping(value="/explore/tags",method=RequestMethod.GET)	
	public void listPage(@ModelAttribute("cri") SearchCriteria cri,Model model) throws Exception {
		logger.info(cri.toString());
	}	
	//followers
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listFollowerPage(@ModelAttribute("cri") Criteria cri,Model model) throws Exception {
		logger.info(cri.toString());
		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		//empty
	}
	
	@Transactional
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(FeedVO vo,RedirectAttributes rttr) throws Exception {
		logger.info("/register...post");
		logger.info(vo.toString());
		
		feedService.register(vo);		
		List<String> hashTags = HashTagHelper.getAllHashTags(vo.getContent());
		
		if(!hashTags.isEmpty()) { //exist hash tag
			//새로 삽입된 피드 가져오기(필요한건 feed_no)
			vo = feedService.getLastInserted();
			for(String tag_name : hashTags) {
				//search hashtag 
				HashTagVO tag = feedService.selectTagByName(tag_name);
				Integer tag_id = null;
				if(tag == null) { //not exist					
					feedService.registerTags(tag_name);				
				} else { //exist
					tag_id = tag.getTag_id();
				}
				
				feedService.registerRelation(vo.getFeed_no(), tag_id);
			}
		}		
		
		//rttr.addFlashAttribute("msg", "SUCCESS");		
		//return "redirect:/sboard/list";
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
