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

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/login.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) { //이미 로그인 된 사용자
			url="main.jsp";			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/login.jsp";
		
		String userId = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDAO memberDao = MemberDAO.getInstance();
		int result = memberDao.userCheck(userId, pwd);
		
		if(result == 1) { //로그인 성공
			MemberVO member = memberDao.getMember(userId);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",member);
			request.setAttribute("message","회원 가입에 성공하였습니다.");
			url = "main.jsp";			
		} else if(result == 0) {
			request.setAttribute("message","비밀번호가 맞지 않습니다.");
		} else if(result == -1) {
			request.setAttribute("message","존재하지 않는 회원입니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}








