package controller.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import chat.ClientHandler;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;

public class LoginController {
	public Request execute(Request request,ClientHandler client) {
		Request response = new Request(request.getType());
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);	
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");		
		MemberDao memberDao = MemberDao.getInstance();
		MemberVO member = null;
		try{
			member = memberDao.selectById(userEmail);			
			//////////
			//로그인 실패			
			if(member==null || !member.matchPassword(userPassword)) {				
				errors.put("notmatch",Boolean.TRUE);
				return response;
			}
			
			if(LoginUserManager.getInstance().isLogin(member.getId())) {
				response.setAttribute("user",member);
				errors.put("duplicatedLogin",Boolean.TRUE);
				return response;
			}
			
			//////////////
			//로그인 성공
			LoginUserManager manager = LoginUserManager.getInstance();
			//1.로그인 유저 정보
			response.setAttribute("loginuser",member);
			manager.addUser(member.getId(),client);			
			//2.친구 목록			
			int id = member.getId();
			List<MemberVO> friendsList = memberDao.getFriendsList(id,member.getFriendCount());					
			List<MemberVO> onFriendsList = new LinkedList<>();
			List<MemberVO> offFriendsList = new LinkedList<>();
			for(int i=0;i<friendsList.size();i++) {
				MemberVO friend = friendsList.get(i);				
				if(manager.isLogin(friend.getId())){					
					onFriendsList.add(friend);
				}
				else{					
					offFriendsList.add(friend);
				}
			}
			response.setAttribute("onlist",onFriendsList);
			response.setAttribute("offlist",offFriendsList);
			//3.친구 요청 온 것
			List<MemberVO> pendingList = memberDao.getPendingList(id);
			response.setAttribute("pendinglist",pendingList);
			//4.유저 아이피(나중에 원격이나 , 파일 전송 등 P2P형식으로 통신할때 아이피 사용
			String addr = client.getSocket().getRemoteSocketAddress().toString();
			int colonIdx = addr.indexOf(':');
			response.setAttribute("ip",addr.substring(1, colonIdx));			
		} catch(SQLException e) {
			e.printStackTrace();	
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}
}
