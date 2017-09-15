package controller.member;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;

/*
 * 친구 찾기 컨트롤러 클래스
 * (클라이언트에서 자체적으로 search type을 판별하고, request 인스턴스에 타입을 보냄)
 */
public class FindUserInfoController implements WebCommandController {
	@Override
	public Request execute(Request request) {	
		String searchType = request.getParameter("searchtype");		
		if(searchType.equals("email"))
			return searchEmail(request);
		else 
			return searchPassword(request);
	}
	
	private Request searchEmail(Request request) {
		Request response = new Request(request.getType());
		MemberDao memberDao = MemberDao.getInstance();
		Map<String,Boolean> errors = new Hashtable<>(); 
		response.setAttribute("errors",errors);		
		try {
			MemberVO member = memberDao.findId(
					request.getParameter("birth")
					,request.getParameter("phone")
					,request.getParameter("gender"));
			if(member==null) {
				errors.put("notexist",Boolean.TRUE);
			} else {
				member.hideInfoForSearchList();
				String result = "찾으시는 아이디 \n : "+member.getEmail();
				response.setParameter("result",result);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
			errors.put("SQLException",Boolean.TRUE);
		}		
		return response;		
	}
	
	private Request searchPassword(Request request) {
		Request response = new Request(request.getType());
		MemberDao memberDao = MemberDao.getInstance();
		Map<String,Boolean> errors = new Hashtable<>(); 
		response.setAttribute("errors",errors);		
		try {
			MemberVO member = memberDao.findPassword(
					request.getParameter("email")
					,request.getParameter("phone")
					);					
			if(member==null) {
				errors.put("notexist",Boolean.TRUE);
			} else {
				System.out.println(member.getName());
				response.setAttribute("user",member);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
			errors.put("SQLException",Boolean.TRUE);
		}		
		return response;	
	}
	
}


















