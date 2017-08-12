package com.faceontalk.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faceontalk.dao.MemberDAO;
import com.faceontalk.dto.MemberVO;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
	}
	
	
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
		int result = memberDao.insertMember(member);
		
		HttpSession session = request.getSession();
		
		if(result==1) {
			session.setAttribute("userid",member.getUserId());
			session.setAttribute("message","회원 가입에 성공하였습니다.");
		} else {
			session.setAttribute("message","회원 가입에 실패하였습니다.");
		}			
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
	}
}
