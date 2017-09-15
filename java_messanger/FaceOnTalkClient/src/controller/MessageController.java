package controller;

import java.util.Hashtable;

import dto.MemberVO;
import frame.ChatFrame;
import request.Request;
import util.UserGroupParser;

/*
 * 메시지를 보낼때 처리해주는 클래스
 */
public class MessageController {
	///singleton
	private static MessageController inst = null;	
	public static MessageController getInstance() {
		if(inst == null)
			inst = new MessageController();
		return inst;
	}
	
	//variables
	private Hashtable<String,ChatFrame> roomTable;
	private MemberController friendsManager;	
	private MemberVO admin;	
	
	private MessageController(){
		roomTable = new Hashtable<>();
		friendsManager = MemberController.getInstance();
		admin = friendsManager.getAdmin();
	}	
	
	//methods	
	//1.방생성
	public void makeChatRoom(String title,int[] roomUsers) {
		roomUsers[0] = admin.getId();		
		String groupId = UserGroupParser.getUserString(roomUsers);
		roomTable.put(groupId, new ChatFrame(title,groupId,String.valueOf(admin.getId())));			
	}
		
	//2.메시지 받기
	public void receiveMessage(Request response) {				
		String sender = response.getParameter("sender");
		String groupId = response.getParameter("groupid");
		String content = response.getParameter("content");		
		if(sender.equals(String.valueOf(admin.getId()))) {
			return;
		}		
		ChatFrame chatFrm = roomTable.get(groupId);
		if(chatFrm==null) {								
				roomTable.put(groupId
						,new ChatFrame(
								getChatRoomTitle(groupId,admin.getId())
								,groupId
								,String.valueOf(admin.getId())
								,content)
						);				
		} else {
				chatFrm.receiveMessage(content);
		}					
	}
	
	//채팅방 공지
	public void receiveNotice(Request response) {		
		String groupId = response.getParameter("groupid");
		String content = response.getParameter("content");		
		ChatFrame chatFrm = roomTable.get(groupId);
		if(chatFrm==null) {	
			chatFrm = new ChatFrame(
									getChatRoomTitle(groupId,admin.getId())
									,groupId
									,String.valueOf(admin.getId()));
			roomTable.put(groupId,chatFrm);					
		}
		chatFrm.receiveNotice(content);
	}
	
	//방 X버튼 누름
	public void disposeChatRoom(String groupId) {
		roomTable.remove(groupId);		
	}
	
	//현재 채팅 창이 띄어져 있는지 여부	
	//방 타이틀 설정
	public String getChatRoomTitle(String groupId,int user) {
		int[] userArray = UserGroupParser.getUserInteger(groupId);
		int idx = 0;
		if(userArray[0]==user)
			idx++;
		if(userArray.length<=2) {
			return friendsManager.getMember(userArray[idx]).getName();
		} else {
			return friendsManager.getMember(userArray[idx]).getName()+"외 "+(userArray.length-1)+"명";
		}
	}
}
