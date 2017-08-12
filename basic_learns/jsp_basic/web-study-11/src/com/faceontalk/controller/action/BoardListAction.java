package com.faceontalk.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.dao.BoardDAO;
import com.faceontalk.dto.BoardVO;

/*
 * 게시글 리스트를 위한 액션 클래스
 */
public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String url = "/board/boardList.jsp";
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardVO> boardList = boardDao.selectAllBoards();		
		request.setAttribute("boardList",boardList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
