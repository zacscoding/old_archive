package controller.login;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.sql.SQLException;
import java.util.List;

import chat.ClientHandler;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;

/*
 * 로그아웃 컨트롤러 클래스 
 * 로그 아웃 시 모든 친구들에게 친구 로그아웃 했다고 처리해줌
 */
public class LogoutController {
	public void execute(MemberVO user) {
		//접속 친구들에게 로그아웃했다고 notice
		Request notice = new Request("friendlogout");
		notice.setAttribute("logoutuser",user);
		MemberDao memberDao = MemberDao.getInstance();
		LoginUserManager manager = LoginUserManager.getInstance();		
		try{
			int id = user.getId();
			List<MemberVO> friendsList = memberDao.getFriendsList(id,user.getFriendCount());			
			for(int i=0;i<friendsList.size();i++) {
				MemberVO friends = friendsList.get(i);						
				ClientHandler client = manager.getUser(friends.getId());
				if(client!=null) {
					client.send(notice);					
				}			
			}									
		}catch(SQLException ignored) {
			
		}		
	}
}		
		
