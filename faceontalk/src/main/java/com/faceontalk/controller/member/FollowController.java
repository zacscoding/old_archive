package com.faceontalk.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
import com.faceontalk.service.member.MemberService;

@RestController
@RequestMapping("/follow/*")
public class FollowController {
	
	private Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Inject
	MemberService memberService;
	
	
	/*		follow		*/
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
	
	/*		unfollow		*/
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
	
	
	/*		follow list		*/
	
	
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
	
	
	

}
