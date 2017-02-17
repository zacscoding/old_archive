package com.faceontalk.feed.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.service.FeedService;

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
		//not yet implement
	}	
	//followers
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listFollowerPage(@ModelAttribute("cri") Criteria cri,Model model) throws Exception {
		logger.info(cri.toString());
		//not yet implement
		
	}
	
	//register	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		//empty		
	}	

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(FeedVO vo,RedirectAttributes rttr) throws Exception {
		logger.info("/register...post");
		logger.info(vo.toString());		
		feedService.register(vo);			
						
		rttr.addFlashAttribute("message", "SUCCESS");
		
		/**temp code */
		return "redirect:/feed/result";				
	}
		
	//update
	@RequestMapping(value="/modifyFeed",method=RequestMethod.GET)
	public void modifyFeedGET(Integer feed_no, Model model) throws Exception {
		logger.info("/modifyFeed .. get");
		model.addAttribute("vo",feedService.selectFeedByNum(feed_no));		
	}	

	@RequestMapping(value="/modifyFeed",method=RequestMethod.POST)
	public String modifyFeedPOST(FeedVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("/modifyFeed .. post");
		logger.info("in controller vo : "+vo);
		feedService.modify(vo);
		
		rttr.addFlashAttribute("message", "SUCCESS");
		
		/**temp code */
		return "redirect:/feed/result";	
	}
	
		
	//remove
	
	@RequestMapping(value="/removeFeed",method=RequestMethod.GET)
	public void removeFeedGET(Integer feed_no, RedirectAttributes rttr) throws Exception {
		//empty
	}
	
	// 게시글 삭제 -> tbl_tag에서 참조하는 피드가 0개이면 tag도 삭제할지 고민
	@RequestMapping(value="/removeFeed",method=RequestMethod.POST)
	public String removeFeedPOST(Integer feed_no, RedirectAttributes rttr) throws Exception {
		logger.info("/removeFeed...POST");
		logger.info("in FeedController feed_no : "+feed_no);
		
		feedService.remove(feed_no);	
		
		rttr.addFlashAttribute("message", "SUCCESS");
		
		/**temp code */
		return "redirect:/feed/result";	
	}
	
	

	
	
}
