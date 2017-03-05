package com.faceontalk.controller.feed;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.PageMaker;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.service.feed.FeedService;
import com.faceontalk.util.MediaUtils;
import com.faceontalk.util.UploadFileUtils;

@Controller
@RequestMapping(value="/feed/*")
public class FeedController {	
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);	
	@Inject
	private FeedService feedService;
	
	private static final String uploadPath="c:\\faceontalk\\upload\\feed";
		
	////////////////////////
	// get feed lists
	///////////////////////		
	/** 	search		*/
	@RequestMapping(value="/explore/tags",method=RequestMethod.GET)	
	public void listPage(@ModelAttribute("cri") SearchCriteria cri,Model model) throws Exception {
		logger.info(cri.toString());
		//not yet implement
	}	
	
	
	//get followers + mine feed lists
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listFollowerPage(@ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest request) throws Exception {		
		
		logger.info(cri.toString());
		
		//get loggined user 
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("login");
				
		//get feed list
		model.addAttribute("feedList",feedService.listFollowersFeeds(cri,vo.getUser_no()));
		
		//calc pageMaker
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(feedService.listFollowersFeedCount(vo.getUser_no()));		
		model.addAttribute("pageMaker",pageMaker);
		
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
		
		
		//String thumbName = vo.getFile_name();		
		//vo.setFile_name(thumbName.substring(0, 12) + thumbName.substring(14));		
		
		feedService.register(vo);		
		
		rttr.addFlashAttribute("message", "SUCCESS");		
		/**temp code */
		return "redirect:/feed/result";				
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
		
		rttr.addFlashAttribute("message", "SUCCESS");
		
		/**temp code */
		return "redirect:/feed/result";	
	}	
	
	/**	remove	*/	
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
	
	@RequestMapping(value="/result",method=RequestMethod.GET)
	public void result() {
		//empty
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
					UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED );
		}		
		return entity;
	}	
	
	/**		delete pic		*/
	@ResponseBody
	@RequestMapping(value="/deleteImage",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		logger.info("delete file : "+fileName);
		
		//썸네일 삭제
		String front = fileName.substring(0,12);
		String end = fileName.substring(14);
		File file = new File(uploadPath+(front+end).replace('/',File.separatorChar));
		if(file.exists())
			file.delete();
		
		//원본 삭제
		file = new File(uploadPath+fileName.replace('/',File.separatorChar));
		if(file.exists())
			file.delete();
		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);		
	}
	
	
}
