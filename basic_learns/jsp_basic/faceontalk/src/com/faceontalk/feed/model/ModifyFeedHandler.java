package com.faceontalk.feed.model;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.feed.action.ModifyFeedAction;
import com.faceontalk.feed.action.ReadFeedAction;
import com.faceontalk.feed.dto.FeedVO;
import com.faceontalk.feed.dto.WriterVO;
import com.faceontalk.member.action.User;
import com.faceontalk.mvc.controller.CommandHandler;

public class ModifyFeedHandler implements CommandHandler {
	private ReadFeedAction readAction = new ReadFeedAction();
	private ModifyFeedAction modifyAction = new ModifyFeedAction();
	
	//private 
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}		
	}
	//GET
	public String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			req.setAttribute("feed",readAction.getFeed(no)); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/view/modifyForm.jsp";
	}	
	//POST
	public String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			int result = modifyAction.update(toModifyFeed(req));
			if(result>0)
				req.setAttribute("message","성공적으로 수정하였습니다.");
			else
				req.setAttribute("message","수정에 실패하였습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/list.do";		
	}
	
	//Request -> Feed인스턴스 생성
	private FeedVO toModifyFeed(HttpServletRequest request){	
		User user = (User)request.getSession().getAttribute("authUser");
		return new FeedVO(
				Integer.parseInt(request.getParameter("feed_no"))
				,new WriterVO(
						Integer.parseInt(user.getId())
						,user.getName()
						,user.getEmail())
				,request.getParameter("modcontent")
				,new Date());				
	}
}
