package controller.member;

import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;


/*
 * 아이디 확인 컨트롤러 클래스
 * -> 회원 가입할 때 중복체크 시 사용
 */
public class ConfirmIdController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		MemberDao memberDao = MemberDao.getInstance();
		String email = request.getParameter("email");	
		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);		
		int existId = -1;
		try{
			existId = memberDao.confirmId(email);			
			if(existId == 1) { //아이디 존재				
				errors.put("duplicatedid",Boolean.TRUE);
			}					
		}catch(SQLException e) {
			e.printStackTrace();			
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}
}
