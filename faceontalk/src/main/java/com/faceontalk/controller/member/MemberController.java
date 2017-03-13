package com.faceontalk.controller.member;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.email.EmailSenderUtil;
import com.faceontalk.exception.DuplicateIdException;
import com.faceontalk.exception.ExceedPeriodException;
import com.faceontalk.service.feed.FeedService;
import com.faceontalk.service.member.MemberService;
import com.faceontalk.util.MediaUtils;
import com.faceontalk.util.UploadFileUtils;

@Controller
@RequestMapping("/accounts")
public class MemberController {	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String UPLOAD_PATH="c:\\faceontalk\\upload\\profile";
	
	@Inject
	private MemberService memberService;
	@Inject
	private FeedService feedService;
	
	/** Join us (AJAX)  */	
	//POST
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST)	
	public ResponseEntity<Map<String,String>> registPOST(@RequestBody MemberVO vo) throws Exception {
		logger.info("/accounts/join (POST)");
		logger.info(vo.toString());		
		ResponseEntity<Map<String,String>> entity = null;				
		Map<String,String> retMap = new HashMap<>();
		try {
			memberService.regist(vo);
			retMap.put("result","SUCCESS");
			retMap.put("mail",EmailSenderUtil.getEmailAddr(vo.getUser_email()));
			entity = new ResponseEntity<>(retMap,HttpStatus.OK);
		} catch(DuplicateIdException ex) {
			retMap.put("result","DUPLICATED_ID");
			entity = new ResponseEntity<>(retMap,HttpStatus.OK);
		} catch(Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return entity;		
	}	
		
	/** 	edit account	*/
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editGet(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("login");
		
		model.addAttribute("vo",memberService.searchByNum(vo.getUser_no()));
		return "/user/edit";
	}	
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editGetPOST(HttpServletRequest request,MemberVO vo,Model model) throws Exception {
		String url = "redirect:/accounts/mypage";		
		try {
			MemberVO anotherUser = memberService.searchById(vo.getUser_id());
			//user_name변경 
			if(anotherUser != null) {
				throw new DuplicateIdException();
			}
			
			//update db
			memberService.edit(vo);			
			vo = memberService.searchByNum(vo.getUser_no());	
			
			//update session
			HttpSession session = request.getSession();
			session.setAttribute("login",vo);
			
		} catch(DuplicateIdException ex) {
			model.addAttribute("duplicateId", Boolean.TRUE);
			model.addAttribute("vo",memberService.searchByNum(vo.getUser_no()));
			url = "/user/edit";
		}		
		return url;
	}
	
	/**		change password	*/
	@ResponseBody
	@RequestMapping(value="/editPassword",method=RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody MemberVO vo) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		try {
			memberService.changePassoword(vo.getUser_no(),vo.getPassword());
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}		
				
		return entity;
	}
	
	
	/**	Auth Email */
	@RequestMapping(value="/confirm_verification", method=RequestMethod.GET)
	public String confirmAuth(EmailAuthVO dto,RedirectAttributes rttr) throws Exception {		
		logger.info("confirm_verifination...");
				
		String msg = null;
		try {
			memberService.confirmAuth(dto);
			msg = "Auth Success";
		} catch(ExceedPeriodException e) {
			msg = "Exceed Period...";
		}		
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/";
	}
	
	/**		Upload profile		*/
	@ResponseBody
	@RequestMapping(value="/uploadPic/{user_no}",method=RequestMethod.POST, produces="test/plain;charset=UTF-8")
	public ResponseEntity<String> uploadPicutre(@PathVariable("user_no") Integer user_no,
													HttpServletRequest request,
													MultipartFile file) throws Exception {		
		ResponseEntity<String> entity = null;		
		//이미지 타입인지 체크
		String fileName = file.getOriginalFilename();
		logger.info("/uploadPic  fileName : "+fileName);		
		MediaType mediaType = MediaUtils.getMediaType(fileName.substring(fileName.lastIndexOf('.')+1));		
		try {
			if(mediaType == null) { //이미지가 아니면
				entity = new ResponseEntity<String>("notMatchedTypes",HttpStatus.OK);
			} else { //이미지 이면
				
				//db update
				String savedFileName  = UploadFileUtils.uploadFile(UPLOAD_PATH, file.getOriginalFilename(), "p", file.getBytes());
				memberService.editProfile(user_no, savedFileName);
				
				//session update
				MemberVO vo = getLoginUser(request);
				vo.setProfile_pic(savedFileName);
				
				entity = new ResponseEntity<String>(savedFileName,HttpStatus.CREATED );			
			}			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	/**		delete profile pic		*/
	@ResponseBody
	@RequestMapping(value="/uploadPic",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteFile(HttpServletRequest request, @RequestBody MemberVO vo) throws Exception {
		
		logger.info("deleteFile.. file name : "+vo.getProfile_pic());
		String fileName = vo.getProfile_pic();
		ResponseEntity<String> entity = null;
		
		//파일 삭제
		File file = new File(UPLOAD_PATH+fileName.replace('/',File.separatorChar));
		
		try {
			
			memberService.editProfile(vo.getUser_no(),null);			
			logger.info(file.getAbsolutePath());
			if(file.exists()) 
				file.delete();
			
			//session update
			getLoginUser(request).setProfile_pic(null);
			
			entity = new ResponseEntity<String>("deleted",HttpStatus.OK); 
		} catch(Exception e) {
			entity = new ResponseEntity<String>("failed",HttpStatus.OK);
		}	
		
		return entity; 		
	}
	
	
	/**	Search Member	*/	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String getUserDetail(String user_id,HttpServletRequest request, Model model) throws Exception {
		logger.info("getUserDetail..get user_id = "+user_id);
		
		HttpSession session = request.getSession();
		
		MemberVO loginUser = (MemberVO)session.getAttribute("login");
		
		if(loginUser!=null && user_id.equals(loginUser.getUser_id())) { //본인 페이지 를 클릭했을 경우
			return "redirect:/accounts/mypage";
		}
		
		MemberVO vo = memberService.searchById(user_id);
		
		//유저가 없으면 db에서  like 연산으로 유저 리스트로 보냄
		if(vo == null) {
			return "redirect:/accounts/list?user_id="+user_id;
		}		
		
		//로그인 상태에서 팔로우 체크
		if(loginUser!=null) { //check follow
			FollowVO follower= new FollowVO();
			follower.setFollower(loginUser.getUser_no());
			follower.setFollowing(vo.getUser_no());
			model.addAttribute("isFollower",memberService.isFollow(follower));
		} else {
			model.addAttribute("isFollower",Boolean.FALSE);
		}
		
		model.addAttribute("memberVO",vo);		
		model.addAttribute("feedList",feedService.listUsersFeedPics(vo.getUser_no()));
		model.addAttribute("following_cnt",memberService.getFollowingCount(vo.getUser_no()));
		model.addAttribute("follower_cnt",memberService.getFollowerCount(vo.getUser_no()));
		
		return "/user/detail";
	}
	
	@RequestMapping(value="/mypage",method=RequestMethod.GET) 
	public String myPage(HttpServletRequest request, Model model) throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("login");
		
		if(memberVO == null)
			return "redirect:/";
		
		//follow info
		memberVO.setFollower_cnt(memberService.getFollowerCount(memberVO.getUser_no()));
		memberVO.setFollowing_cnt(memberService.getFollowingCount(memberVO.getUser_no()));		
		model.addAttribute("memberVO",memberService.searchByNum(memberVO.getUser_no()));
		
		// feed info
		model.addAttribute("feedList",feedService.listUsersFeedPics(memberVO.getUser_no()));
		
		return "/user/mypage";		
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getUserList(String user_id,Model model) throws Exception {
		
		List<MemberVO> memberList =  memberService.searchListById(user_id);
		
		if(memberList == null) {
			model.addAttribute("msg","검색하신 "+user_id+"는 존재하지 않습니다.");
		} else {
			for(MemberVO vo : memberList) {
				vo.setFollower_cnt(memberService.getFollowerCount(vo.getUser_no()));
				vo.setFollowing_cnt(memberService.getFollowingCount(vo.getUser_no()));
			}				
		}				
		model.addAttribute("memberList",memberList);		
		return "/user/list";
	}
	
	/////////////////
	// private methods
	private MemberVO getLoginUser(HttpServletRequest request) {
		return (MemberVO)(request.getSession().getAttribute("login"));
	}

}
