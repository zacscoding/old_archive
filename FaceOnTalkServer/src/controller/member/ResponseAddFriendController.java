package controller.member;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dao.RelationshipDao;
import request.Request;

/*
 * A -> B 친구요청
 * B -> A 친구 수락시 B가 서버로 type="responseadd"보냄.
 * 친구 수락 컨트롤러 클래스
 */
public class ResponseAddFriendController implements WebCommandController{
	@Override
	public Request execute(Request request) {
		RelationshipDao relationDao = RelationshipDao.getInstance();
		MemberDao memberDao = MemberDao.getInstance();		
		
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);				
		try {
			if((memberDao.confirmId(to))==-1) {				
				errors.put("invalidToId",Boolean.TRUE);
				return response;
			}			
			relationDao.addFriendResponse(from,to,request.getParameter("status"));									
		}catch(SQLException e) {
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;		
	}
}
