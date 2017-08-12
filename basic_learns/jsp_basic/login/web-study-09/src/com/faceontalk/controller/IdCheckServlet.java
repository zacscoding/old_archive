package com.faceontalk.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.MemberDAO;

@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String userId = request.getParameter("userid");  		
  		MemberDAO memberDao = MemberDAO.getInstance();
  		
  		int result = memberDao.confirmId(userId);  		
  		request.setAttribute("userid",userId);
  		request.setAttribute("result",result);
  		
  		RequestDispatcher dispatcher = request.getRequestDispatcher("member/idcheck.jsp");
  		dispatcher.forward(request, response);  		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
