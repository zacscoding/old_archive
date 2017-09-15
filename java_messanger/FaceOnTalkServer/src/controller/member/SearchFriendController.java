package controller.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import controller.WebCommandController;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;


/*
 * 친구 찾기 컨트롤러 클래스
 */
public class SearchFriendController implements WebCommandController {
	@Override
	public Request execute(Request request) {		
		Request response = new Request(request.getType());		
		String searchType = request.getParameter("searchtype"); //찾는 타입(eamil,phone,name..)
		Map<String, Boolean> errors = new Hashtable<>();
		response.setAttribute("errors", errors);
		int from = Integer.parseInt(request.getParameter("from"));
		
		try{
			if(searchType.equals("email")) {
				searchByEmail(request.getParameter("value"),response,errors,from);
			}else if(searchType.equals("name")) {
				searchByName(request.getParameter("value"),response,errors,from);
			}else if(searchType.equals("phone")) {
				searchByPhone(request.getParameter("value"),response,errors,from);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			errors.put("SQLException", Boolean.TRUE);
		}		
		return response;
	}
	
	//이메일로 친구 찾기
	private void searchByEmail(String email,Request response,Map<String,Boolean> errors,int from) throws SQLException {		
		MemberDao memberDao = MemberDao.getInstance();
		MemberVO member = null;
		member = memberDao.selectById(email);
		if(member==null || member.getId()==from) {
			errors.put("cantfind",Boolean.TRUE);
		}else {			
			member.hideInfoForSearchList();
			List<MemberVO> memberList = new ArrayList<>(1);
			memberList.add(member);
			response.setAttribute("searchresult",memberList);			
		}									
	}	
	
	//폰으로 친구 찾기
	private void searchByPhone(String phone, Request response,Map<String,Boolean> errors,int from) throws SQLException {
		MemberDao memberDao = MemberDao.getInstance();		
		List<MemberVO> memberList = null;
		memberList = memberDao.selectByPhone(phone,from);
		if(memberList== null || memberList.isEmpty()){
			errors.put("cantfind",Boolean.TRUE);
			return;
		}
		for(int i=0;i<memberList.size();i++) {			
			memberList.get(i).hideInfoForSearchList();
		}		
		response.setAttribute("searchresult",memberList);
	}		
	
	//이름으로 친구 찾기
	private void searchByName(String name, Request response,Map<String,Boolean> errors,int from) throws SQLException {
		MemberDao memberDao = MemberDao.getInstance();		
		List<MemberVO> memberList = null;
		memberList = memberDao.selectByName(name,from);
		if(memberList== null || memberList.isEmpty() ){
			errors.put("cantfind",Boolean.TRUE);
			return;
		}
		for(int i=0;i<memberList.size();i++) {			
			memberList.get(i).hideInfoForSearchList();
		}		
		response.setAttribute("searchresult",memberList);
	}	
}
