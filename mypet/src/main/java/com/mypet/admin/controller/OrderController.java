package com.mypet.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.service.OrderService;

/**
 * 주문관리
 */
@Controller
@RequestMapping("/admin/order/")
public class OrderController {
	
	@Inject
	OrderService service;
	
	/*	주문 리스트 보기	*/
	
	
	
	

}
