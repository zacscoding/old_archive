package com.faceontalk.feed.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	
	//register	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		//empty
	}	
	
	@Transactional
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(FeedVO vo,RedirectAttributes rttr) throws Exception {
		logger.info("/register...post");
		logger.info(vo.toString());
		
		List<String> hashTags = HashTagHelper.getAllHashTags(vo.getContent());		
		feedService.register(vo);			
		//sol2) --- > 임시 (last_insert_id() 함수에 따라 비효율)
		if(!hashTags.isEmpty()) { //exist hash tag
			//새로 삽입된 피드 가져오기(필요한건 feed_no)
			vo = feedService.getLastInsertedFeed();			
			for(String tag_name : hashTags) {
				registFeedAndTag(vo.getFeed_no(),tag_name);
			}				
		}						
		//rttr.addFlashAttribute("msg", "SUCCESS");		
		//return "redirect:/sboard/list";
		return "home";
		
		
		
		
//		//sol1) ---> 이걸로 다시 하기
//		if(!hashTags.isEmpty()) { //exist hash tag
//			//새로 삽입된 피드 가져오기(필요한건 feed_no)
//			vo = feedService.getLastInserted();
//			for(String tag_name : hashTags) {
//				logger.info("tag_name : "+tag_name);
//				//search hashtag 
//				HashTagVO tag = feedService.selectTagByName(tag_name);
//				Integer tag_id = null;
//				if(tag == null) { //not exist					
//					feedService.registerTags(tag_name);	
//					tag_id = -1;
//				} else { //exist
//					tag_id = tag.getTag_id();
//				}			
//				logger.info("vo : "+vo.getFeed_no() + "tag_id : "+tag_id);				
//				feedService.registerRelation(vo.getFeed_no(),tag_id);
//			}
//		}	
		
	}
	
	
	
	
	//update
	@RequestMapping(value="/modifyFeed",method=RequestMethod.GET)
	public void modifyFeedGET(Integer feed_no, Model model) throws Exception {
		model.addAttribute("vo",feedService.selectFeedByNum(feed_no));		
	}	
	
	@RequestMapping(value="/modifyFeed",method=RequestMethod.POST)
	public String modifyFeedPOST(FeedVO vo, RedirectAttributes rttr) throws Exception {
		/*
		 * logic
		//1. check for modified hash tags
		// 1) 기존 저장된 해시 태그들을 가져옴
		// 2) 새로 변경된 내용의 해시 태그들을 가져옴
		// 3) 기존 vs 새로운 태그들 비교 
		//		==> sol1) 기존 HashMap, 새로운 태그 list => HashMap.contains(새로운태그) ????
		//		==> sol2) 
		// 4-1) 태그 그대로면 continue
		// 4-2) 태그 변동 되었으면 
		// ==> 기존 tag,feed relation 삭제 .
		// ==> 새로운 태그가 tbl_tab에 존재하지 않으면 regist tag
		// ==> regist relation...
		 */
		
		
		//tags
		Map<String,HashTagVO> prevTagsMap = feedService.getTagsMap(vo.getFeed_no());		
		List<String> modifiedTagsList = HashTagHelper.getAllHashTags(vo.getContent());
		List<String> newTagsList = new LinkedList<>();		
		for(int i=0;i<modifiedTagsList.size();i++) {
			String tag_name = modifiedTagsList.get(i);
			if(prevTagsMap.remove(tag_name) ==null) {
				newTagsList.add(tag_name);				
			}
		}		
		//새로운 관계 생성 == newTagsList
		for(String tag_name : newTagsList) {
			registFeedAndTag(vo.getFeed_no(),tag_name);
		}		
		//기존 관계 삭제 == prevTagsMap
		Iterator<String> keyItr = prevTagsMap.keySet().iterator();
		while(keyItr.hasNext()) {
			HashTagVO tag = prevTagsMap.get(keyItr.next());
			feedService.removeRelation(vo.getFeed_no(), tag.getTag_id());
		}
		
		//2.content
		feedService.modify(vo);
		
		
		//3. check for modified file image
		return "home";
	}
	
	
		
	//remove
	// 게시글 삭제 -> tbl_tag에서 참조하는 피드가 0개이면 tag도 삭제할지 고민	
	
	
	
	////////////////////////////
	//private
	
	/// check transactional
	private void registFeedAndTag(int feed_no,String tag_name) throws Exception {
		HashTagVO tag = feedService.selectTagByName(tag_name);				
		if(tag == null) {
			feedService.registerTags(tag_name);
			tag = feedService.getLastInsertedTag();
		}				
		feedService.registerRelation(feed_no,tag.getTag_id());
	}
	
	
}
