package controller.member;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;

/*
 * 정보 변경 컨트롤러 
 */
public class ModifyInfoController implements WebCommandController{
	@Override
	public Request execute(Request request) {
		MemberDao memberDao = MemberDao.getInstance();
		String idVal = request.getParameter("id");
		int id = Integer.parseInt(idVal);
		Request response = new Request(request.getType());
		MemberVO member = null;		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);		
		try{
			member = memberDao.selectById(id);
			if(member==null) { 
				errors.put("invalidId",Boolean.TRUE);
				return response;
			}		
			
			String name = request.getParameter("name");
			if(name!=null)
				member.setName(name);
			
			String pwd = request.getParameter("password");
			if(pwd!=null)
				member.changePassword(pwd);
			
			String phone = request.getParameter("phone");
			if(phone!=null)
				member.setPhone(phone);			
			
			memberDao.update(member);						
		}catch(SQLException e) {
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}	
}
