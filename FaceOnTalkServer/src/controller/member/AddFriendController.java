package controller.member;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dao.RelationshipDao;
import request.Request;


/*
 * 친구 요청 컨트롤러
 * A->B 친구요청 하면 , A가 서버로 보낼 때 사용함
 */
public class AddFriendController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		RelationshipDao relationDao = RelationshipDao.getInstance();
		MemberDao memberDao = MemberDao.getInstance();	
				
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);				
		try{
			if((memberDao.confirmId(to))==-1) { //존재하지 않는 아이디
				errors.put("invalidResponseId",Boolean.TRUE);
				return response;
			} else if(relationDao.isFriends(from, to)>0) { //이미 친구
				errors.put("alreadyfriend",Boolean.TRUE);
				return response;
			}			
			relationDao.addFriendRequest(from, to);	
		}catch(SQLException e) {			
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;	
	}
}
