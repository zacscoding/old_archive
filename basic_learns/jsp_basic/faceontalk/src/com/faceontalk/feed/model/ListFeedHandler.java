package com.faceontalk.feed.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.feed.action.FeedListAction;
import com.faceontalk.feed.dto.Criteria;
import com.faceontalk.feed.dto.FeedPage;
import com.faceontalk.feed.dto.PageMaker;
import com.faceontalk.member.action.User;
import com.faceontalk.mvc.controller.CommandHandler;

public class ListFeedHandler implements CommandHandler {
	FeedListAction feedListAction = new FeedListAction();	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;		
		if(pageNoVal != null)
			pageNo = Integer.parseInt(pageNoVal);
		String perPageNumVal = req.getParameter("perPageNum");
		int perPageNum = 10;
		if(perPageNumVal != null)
			perPageNum = Integer.parseInt(perPageNumVal);
		
		String readType = req.getParameter("readType");
		if(readType==null)
			readType="all";	
		User loginUser = (User)req.getSession().getAttribute("authUser");		
		int userId = Integer.parseInt(loginUser.getId());
		
		Criteria cri = new Criteria();
		cri.setPage(pageNo);
		cri.setPerPageNum(perPageNum);
		
		req.setAttribute("list",feedListAction.listCriteria(cri, readType,userId));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(feedListAction.listCount(readType,userId));
		
		req.setAttribute("pageMaker", pageMaker);				
		return "/WEB-INF/view/feedList.jsp";
	}	
}
