package com.faceontalk.controller.feed;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.PageMaker;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.service.feed.FeedService;
import com.faceontalk.util.MediaUtils;
import com.faceontalk.util.UploadFileUtils;

@Controller
@RequestMapping(value="/feed/*")
public class FeedController {	
	private static final String UPLOAD_PATH="c:\\faceontalk\\upload\\feed";
	
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);	
	@Inject
	private FeedService feedService;
		
	
	/**		feed lists about search				*/
	@RequestMapping(value="/searchList", method=RequestMethod.GET)
	public void listSearchPage(@ModelAttribute("cri") SearchCriteria cri, Model model, HttpServletRequest request) throws Exception {
		
		String tag_name = cri.getKeyword();
		
		logger.info("listSearchPage ... tag_name : "+tag_name);
		
		//keyword가 없으면 모든 피드 리스트를 보여줌
		if(tag_name == null || tag_name.isEmpty()) {			
			model.addAttribute("feedList", feedService.listAllFeeds(cri));
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(feedService.listAllFeedCount());
			model.addAttribute("pageMaker", pageMaker);
			return;
		}
		
		//해시 태그가 존재하지 않음
		HashTagVO vo = feedService.selectTagByName(tag_name);		
		
		if(vo == null) {
			model.addAttribute("msg","검색하신 \"#"+tag_name+ "\" 는 존재하지 않습니다.");
			return;
		}		
		
		//tag 테이블에 존재하지만, 관련된 피드가 없음(피드 삭제 시 해시태그는 그대로 두어서)
		int totalCount = feedService.listCountsByTagCount(vo.getTag_id());
		
		model.addAttribute("feedList", feedService.listFeedsByTag(cri,vo.getTag_id()));
		
		// make pageMaker
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);		
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);		
	}
	
	
	/**		feed lists about followers and mine		*/
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listFollowersPage(@ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) throws Exception {				
		
		logger.info(cri.toString());
		
		// get loggined user
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("login");
		
		// get feed list
		model.addAttribute("feedList", feedService.listFollowersFeeds(cri, vo.getUser_no()));
				
		// make pageMaker
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		int totalCount = feedService.listFollowersFeedCount(vo.getUser_no());
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);		
		if(totalCount == 0)
			model.addAttribute("msg","등록된 피드가 없습니다.");		
	}	
	
	
	
	/**		register 	*/		
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		//empty		
	}	

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(FeedVO vo,RedirectAttributes rttr) throws Exception {
		logger.info("/register...post");
		logger.info(vo.toString());				
		
		feedService.register(vo);		
		
		rttr.addFlashAttribute("message", "SUCCESS");
		
		/**		temp code 	*/
		return "redirect:/feed/list";				
	}
	
	
	/**	update	*/
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
		
		return "redirect:/feed/list";	
	}	
	
	
	
	
	/**		Ajax		*/	
	/**		ajax register pic	*/
	@ResponseBody
	@RequestMapping(value="/uploadPic",method=RequestMethod.POST, produces="test/plain;charset=UTF-8")
	public ResponseEntity<String> uploadPicutre(MultipartFile file) throws Exception {		
		ResponseEntity<String> entity = null;		
		//이미지 타입인지 체크
		String fileName = file.getOriginalFilename();
		logger.info("/uploadPic  fileName : "+fileName);
		
		MediaType mediaType = MediaUtils.getMediaType(fileName.substring(fileName.lastIndexOf('.')+1));
		
		if(mediaType == null) { //이미지가 아니면
			entity = new ResponseEntity<String>("notMatchedTypes",HttpStatus.OK);
		} else { //이미지 이면
			entity = new ResponseEntity<String>(
					UploadFileUtils.uploadFile(UPLOAD_PATH, file.getOriginalFilename(), "f", file.getBytes()),
					HttpStatus.CREATED );			
		}		
		return entity;
	}	
	
	
	/**		delete pic		*/
	@ResponseBody
	@RequestMapping(value="/uploadPic",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteFile(@RequestBody String fileName) {
		
		logger.info("delete file : "+fileName);			
		
		//파일 삭제
		File file = new File(UPLOAD_PATH+fileName.replace('/',File.separatorChar));
		
		if(file.exists())
			file.delete();		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);		
	}
	
	/**		read		*/
	@ResponseBody
	@RequestMapping(value="/{feed_no}",method=RequestMethod.GET)
	public ResponseEntity<FeedVO> getFeed(@PathVariable("feed_no") Integer feed_no) throws Exception {
		logger.info("getFeed.." + feed_no);
		
		ResponseEntity<FeedVO> entity = null;
		try {
			entity = new ResponseEntity<FeedVO>(feedService.selectFeedByNum(feed_no),HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
 		return entity;
	}
	
	/**	remove	*/
	// 게시글 삭제 -> tbl_tag에서 참조하는 피드가 0개이면 tag도 삭제할지 고민
	@ResponseBody
	@RequestMapping(value="/{feed_no}",method=RequestMethod.DELETE)
	public ResponseEntity<String> removeFeed(@PathVariable("feed_no") Integer feed_no) throws Exception {
		
		logger.info("remove feed.. in FeedController feed_no : "+feed_no);
		
		ResponseEntity<String> entity = null;
		try {
			feedService.remove(feed_no);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}	
		
	@RequestMapping(value="/result",method=RequestMethod.GET)
	public void result() {
		//empty
	}
		
}
