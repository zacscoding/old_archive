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
 * 회원 가입 컨트롤러 클래스
 */
public class JoinController implements WebCommandController {			
	@Override
	public Request execute(Request request) {
		MemberDao memberDao = MemberDao.getInstance();
		String email = request.getParameter("email");	
		System.out.println("email : "+email);
		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);		
		MemberVO member = null;
		int existId = -1;
		try{
			existId = memberDao.confirmId(email);			
			if(existId == 1) { //아이디 존재				
				errors.put("duplicatedid",Boolean.TRUE);
				return response;
			}
			member = new MemberVO(0
					,email					
					,request.getParameter("name")
					,request.getParameter("password")
					,request.getParameter("phone").replaceAll("-","")
					,request.getParameter("birth")
					,request.getParameter("gender")
					,0
					,new Date());			
			memberDao.insert(member);						
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception");
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}	
}




























