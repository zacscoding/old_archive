package controller.login;

import java.util.Hashtable;
import java.util.Iterator;

import chat.ClientHandler;

/*
 * 로그인 유저들을 관리하는 클래스
 */

public class LoginUserManager {
	//singleton
	private static LoginUserManager inst = null;
	public static LoginUserManager getInstance(){
		if(inst == null)
			inst = new LoginUserManager();
		return inst;
	}
	
	private Hashtable<Integer,ClientHandler> connections; //로그인 유저들을 담는 hashtable	
	private LoginUserManager(){
		connections = new Hashtable<>(100);		
	}	
	//로그인 시
	public ClientHandler addUser(Integer id,ClientHandler client){
		return connections.put(id, client);
	}	
	//서치
	public ClientHandler getUser(Integer id){
		return connections.get(id);
	}	
	public ClientHandler getUser(String id) {
		return connections.get(Integer.parseInt(id));
	}
	//제거
	public boolean removeUser(Integer id){
		return connections.remove(id)!=null;
	}
	//모든 유저들을 제거
	public void removeAllUser(){		
		Iterator<Integer> itr = connections.keySet().iterator();
		while(itr.hasNext()){
			Integer id = itr.next();
			ClientHandler c = connections.remove(id);
			c.closeSocket();
		}		
	}
	//로그인 존재 유무
	public boolean isLogin(Integer id){
		return connections.get(id)!=null;
	}
	public boolean isLogin(String id) {
		return connections.get(Integer.parseInt(id))!=null;
	}
}











