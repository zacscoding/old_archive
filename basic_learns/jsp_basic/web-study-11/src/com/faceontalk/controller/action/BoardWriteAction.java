package com.faceontalk.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.BoardDAO;
import com.faceontalk.dto.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		BoardVO board = convertBoard(request);		
		BoardDAO boardDao = BoardDAO.getInstance();
		boardDao.insertBoard(board);		
		new BoardListAction().execute(request, response);
	}
	
	private BoardVO convertBoard(HttpServletRequest request) {
		return new BoardVO(
				0,
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("pass"),
				request.getParameter("title"),
				request.getParameter("content"),
				0,
				null				
				);
	}
}
