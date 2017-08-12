package com.faceontalk.feed.model;




import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.feed.action.WriteFeedAction;
import com.faceontalk.feed.dto.FeedVO;
import com.faceontalk.feed.dto.WriterVO;
import com.faceontalk.member.action.User;
import com.faceontalk.mvc.controller.CommandHandler;


public class WriteFeedHandler implements CommandHandler {
	WriteFeedAction writeAction = new WriteFeedAction();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		FeedVO feed = createWriteRequest(user, req);
		int result = writeAction.write(feed);
		if(result>0)
			req.setAttribute("message","성공적으로 저장하였습니다.");
		else
			req.setAttribute("message","저장에 실패하였습니다.");
		return "/list.do";
	}
	
	private FeedVO createWriteRequest(User user,HttpServletRequest req) {
		return new FeedVO(
				new WriterVO(user.getId(),user.getName(),user.getEmail()),
				req.getParameter("content"),
				new Date(),
				new Date(),
				0);		
	}
}
