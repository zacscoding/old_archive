package com.mypet.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.service.MemberService;

/**
 *  회원 관리
 */
@Controller
@RequestMapping("/admin/usermanage")
public class MemberController {
	
	@Inject
	MemberService service;
	
	/*	회원 리스트 보기*/
	/*	회원 상세 정보 보기*/
	/*	회원 정보 수정 */
	
	
	
}
