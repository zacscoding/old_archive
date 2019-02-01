package controller;

import java.util.Iterator;
import java.util.List;

import dto.MemberVO;
import request.Request;

/*
 * 접속 유저 정보, 접속,비접속 친구 정보, 친구요청온 정보를 담고 꺼내주는 매니저 클래스
 */
public class MemberController {
	//singleton
	private static MemberController inst = null;
	private MemberController(){}
	public static MemberController getInstance() {
		if(inst == null) 
			inst = new MemberController();
		return inst;
	}
	
	////////////////
	//variables
	private MemberVO admin;
	private String ip;
	private List<MemberVO> onFriendsList;
	private List<MemberVO> offFriendsList;
	private List<MemberVO> pendingList;
	
	////////////////
	//methods	
	//initialize
	public void initFriendsList(MemberVO admin,String ip,List<MemberVO> onFriendsList,List<MemberVO>offFriendsList,List<MemberVO> pendingList) {
		this.admin = admin;
		this.ip = ip;
		this.onFriendsList = onFriendsList;
		this.offFriendsList = offFriendsList;
		this.pendingList = pendingList;
	}		
	////////////////
	//
	public MemberVO getMember(int id) {
		for(int i=0;i<onFriendsList.size();i++) {
			MemberVO mem = onFriendsList.get(i);
			if(mem.getId() == id){				
				return mem;
			}
		}
		for(int i=0;i<offFriendsList.size();i++) {
			MemberVO mem = offFriendsList.get(i);
			if(mem.getId() == id){				
				return mem;
			}
		}
		return null;	
	}
	public MemberVO getMember(String id) {
		return getMember(Integer.parseInt(id));
	}
	
	public MemberVO getAdmin() {
		return admin;
	}	
	
	public String getIP() {
		return ip;
	}
	
	//친구 로그인
	public void addLogOnUser(MemberVO friend) {
		System.out.println("friend : "+friend);		
		if(offFriendsList.isEmpty()){
			onFriendsList.add(friend);
		}else {
			for(int i=0;i<offFriendsList.size();i++){				
				if(friend.getId() == offFriendsList.get(i).getId()) {				
					onFriendsList.add(offFriendsList.remove(i));				
					break;
				}
			}						
		}		
	}
	
	//친구 로그아웃
	public void removeLogOnUser(MemberVO friend) {		
 		for(int i=0;i<onFriendsList.size();i++){
			if(friend.getId() == onFriendsList.get(i).getId()) {
				System.out.println(onFriendsList.get(i).getName());
				offFriendsList.add(onFriendsList.remove(i));
				break;
			}				
		}
	}
	
	//새로운 친구 추가
	public void addFriend(MemberVO friend) {
		offFriendsList.add(friend);		
		checkLogin(friend);		
	}	
	
	public void permitFriend(MemberVO friend) {
		Iterator<MemberVO> itr = pendingList.iterator();
		while(itr.hasNext()) {
			MemberVO inst = itr.next();
			if(inst.equals(friend)) {
				itr.remove();
				break;
			}
		}
		addFriend(friend);		
	}
	
	////////////////
	//로그인 체크 
	////////////////
	public void checkLogin(MemberVO friend) {
		Request request = new Request("checklogin");
		request.setAttribute("checkuser",friend);
		ClientHandler.getInstance().send(request);
	}
	
	////////////////
	//로그온 유저 얻기 
	////////////////
	public List<MemberVO> getLogonUsers() {		
		return onFriendsList;
	}
}















