package com.faceontalk.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.FollowDTO;
import com.faceontalk.service.member.MemberService;

@RestController
@RequestMapping("/follow/*")
public class FollowController {
	
	private Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Inject
	MemberService memberService;
	
	
	/*		follow 하기		*/
	@RequestMapping(value = "/{follower}/{following}",method=RequestMethod.PUT)
	public ResponseEntity<String> registFollow(@PathVariable("follower") Integer follower,
												@PathVariable("following") Integer following) throws Exception {
		
		ResponseEntity<String> entity = null;		
		try {					
			//regist follow 
			FollowVO vo = new FollowVO();
			vo.setFollower(follower);
			vo.setFollowing(following);			
			memberService.regist(vo);			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);			
		}		
		return entity;		
	}
	
	/*		unfollow 하기		*/
	@RequestMapping(value = "/{follower}/{following}",method=RequestMethod.DELETE)
	public ResponseEntity<String> removeFollow(@PathVariable("follower") Integer follower,			
												@PathVariable("following") Integer following) throws Exception {
		
		ResponseEntity<String> entity = null;		
		
		try {					
			//regist follow 
			FollowVO vo = new FollowVO();
			vo.setFollower(follower);
			vo.setFollowing(following);
			
			memberService.remove(vo);
			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);			
		}		
		return entity;	
	}
	
	/*	follower,following count	*/
	@RequestMapping(value = "/{user_no}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Integer>> removeFollow(@PathVariable("user_no") Integer user_no)throws Exception {
		
		ResponseEntity<Map<String,Integer>> entity = null;		
		
		try {			
			Map<String,Integer> retMap = new HashMap<>();
			
			retMap.put("follower_cnt",memberService.getFollowerCount(user_no));
			
			retMap.put("following_cnt",memberService.getFollowingCount(user_no));
			
			entity = new ResponseEntity<Map<String,Integer>>(retMap,HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<Map<String,Integer>>(HttpStatus.BAD_REQUEST);
		}		
		
		return entity;	
	}
	
	
	/*		follower list		*/
	@RequestMapping(value="/follower/{user_no}")
	public ResponseEntity<List<FollowDTO>> listFollower(@PathVariable("user_no") Integer user_no,HttpServletRequest request)throws Exception {		
		ResponseEntity<List<FollowDTO>> entity = null;
		try {			
			//팔로워 리스트 가져오기
			List<FollowDTO> list = memberService.getFollowerList(user_no);
			
			//로그인 유저가 팔로우 하는지 체크
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("login");
			if(loginUser != null)
				checkFollow(list,loginUser.getUser_no());
			
			entity = new ResponseEntity<>(list,HttpStatus.OK);									
		} catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/following/{user_no}")
	public ResponseEntity<List<FollowDTO>> listFollowing(@PathVariable("user_no") Integer user_no,HttpServletRequest request)throws Exception {
		ResponseEntity<List<FollowDTO>> entity = null;
		try {
			//팔로잉 리스트 가져오기
			List<FollowDTO> list = memberService.getFollowingList(user_no);
			
			//로그인 유저가 팔로우하는지 체크
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("login");
			
			//로그인 상태이고, 자신의 following 리스트를 가져오는 케이스가 아님
			if(loginUser != null && loginUser.getUser_no() != user_no) 
				checkFollow(list,loginUser.getUser_no());
			
			entity = new ResponseEntity<>(list,HttpStatus.OK);		
		} catch(Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;		
	}
	
	
	
	
	/////////////////////////////
	//private methods	
	//get follower , following count
	private Map<String,Object> getFollowInfo(Integer user_no) throws Exception {
		
		MemberVO vo = memberService.searchByNum(user_no);
		
		if(vo == null)
			return new HashMap<String,Object>();
		
		Map<String,Object> retMap = new HashMap<>();
		
		retMap.put("follower_cnt",vo.getFollower_cnt());
		retMap.put("following_cnt", vo.getFollowing_cnt());
		
		return retMap;
	}
	
	//팔로우 상태 체크
	private void checkFollow(List<FollowDTO> list,Integer user_no) throws Exception {
		FollowVO vo = new FollowVO();
		vo.setFollower(user_no);
		for(FollowDTO dto : list) {
			vo.setFollowing(dto.getUser_no());
			if(memberService.isFollow(vo)) 
				dto.setFollow(true);
			
		}
	}
	
	
}
