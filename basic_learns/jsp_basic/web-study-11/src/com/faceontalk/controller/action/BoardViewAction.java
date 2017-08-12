package com.faceontalk.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.BoardDAO;
import com.faceontalk.dto.BoardVO;

public class BoardViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/board/boardView.jsp";
		String num = request.getParameter("num");
		BoardDAO boardDao = BoardDAO.getInstance();
		BoardVO board = boardDao.selectOneBoardByNum(num);
		boardDao.updateReadCount(num);
		
		request.setAttribute("board",board);		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
