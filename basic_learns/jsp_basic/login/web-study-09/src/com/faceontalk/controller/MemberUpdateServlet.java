package com.faceontalk.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.MemberDAO;
import com.faceontalk.dto.MemberVO;

@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//get
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String userId = request.getParameter("userid");
  		MemberDAO memberDao = MemberDAO.getInstance();
  		MemberVO member = memberDao.getMember(userId);
  		
  		request.setAttribute("member",member);
  		
  		RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberUpdate.jsp");
  		dispatcher.forward(request, response);
  		
	}
	//post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		MemberVO member = new MemberVO(
				request.getParameter("name"),
				request.getParameter("userid"),
				request.getParameter("pwd"),
				request.getParameter("email"),
				request.getParameter("phone"),
				Integer.parseInt(request.getParameter("admin")));
		MemberDAO memberDao = MemberDAO.getInstance();
		
		int result = memberDao.updateMember(member);
		response.sendRedirect("login.do");
	}
}
