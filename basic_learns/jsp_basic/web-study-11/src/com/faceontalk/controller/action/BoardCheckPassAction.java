package com.faceontalk.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.BoardDAO;
import com.faceontalk.dto.BoardVO;

public class BoardCheckPassAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		BoardDAO boardDao = BoardDAO.getInstance();		
		BoardVO board = boardDao.selectOneBoardByNum(num);
		
		if(board.getPass().equals(pass)) {
			url = "/board/checkSuccess.jsp";
		} else {
			url = "/board/boardCheckPass.jsp";
			request.setAttribute("message","비밀번호가 일치하지 않습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);				
	}
}
