package controller.chat;

import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import controller.ChatCommandController;
import dao.MessageDao;
import dto.MessageVO;
import request.Request;

/*
 * 메시지를 다뤄주는 컨트롤러 클래스 
 */

public class MessageController implements ChatCommandController {	
	public Request execute(Request request) {
		MessageDao messageDao = MessageDao.getInstance();		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);		
		try{
			MessageVO message = new MessageVO(
					request.getParameter("group_id")
					,Integer.parseInt(request.getParameter("sender"))
					,request.getParameter("content")
					,new Date()
					);
			messageDao.insert(message);
			response.setAttribute("message",message);
		}catch(SQLException e) {
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;	
	}		
}

