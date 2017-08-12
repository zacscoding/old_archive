package chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import controller.ChatCommandController;
import controller.login.LoginController;
import controller.login.LoginUserManager;
import controller.login.LogoutController;
import controller.member.AddFriendController;
import controller.member.RemoveFriendController;
import controller.member.ResponseAddFriendController;
import dao.MemberDao;
import dto.MemberVO;
import request.Request;
import util.UserGroupParser;

public class ClientHandler {	
	private Socket socket;	
	private MemberVO loginUser;	
	private LoginUserManager manager = LoginUserManager.getInstance();	
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		ChatServer.executorService.submit(() -> {
			receive();
		});		
	}
	
	public Socket getSocket() {
		return socket;
	}
			
	public void receive() {
		while(true) {			 
			try {
				ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Request request = (Request)oi.readObject();
				
				ChatCommandController controller = null;				
				String type = request.getType();	
				System.out.println(type);
				
				////////////////////////////////////////////
				//로그인 요청
				///////////////////////////////////////////
				if (type.equals("login")) { // 로그인 요청
					System.out.println("로그인 요청 해석");
					Request response = new LoginController().execute(request, this);
					loginUser = (MemberVO)response.getAttribute("loginuser");					
					@SuppressWarnings("unchecked")
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if (errors.isEmpty()) { // 로그인 성공
						System.out.println("로그인 성공");
						loginUser = (MemberVO) response.getAttribute("loginuser");
						
						// 친구들에게 로그인 했다고 뿌림
						List<MemberVO> friendsList = (List<MemberVO>) response.getAttribute("onlist");
						int[] friends = new int[friendsList.size()];
						for(int i=0;i<friends.length;i++) {
							friends[i] = friendsList.get(i).getId();
						}						
						Request notice = new Request("friendlogin");
						notice.setAttribute("loginuser", loginUser);
						broadCastForGroup(notice,friends,false);						
					}	
					send(response);
				}

				////////////////////////////////////////////
				//메시지 요청
				///////////////////////////////////////////
				else if (type.equals("message")) { 
					System.out.println("메시지 요청 해석");
					/*
					 * DB 에 message까지 저장할 경우 처리
					 * 코드 Request response = new
					 * MessageController().execute(request);
					 * 
					 * @SuppressWarnings("unchecked") Map<String,Boolean> errors =
					 * (Map<String,Boolean>)response.getAttribute("errors");
					 * if(errors.isEmpty()) { //성공 저장 int[] users =
					 * UserGroupParser.getUserInteger(request.getParameter("groupid"
					 * )); for(int i=0;i<users.length;i++) { //접속자에게 전송
					 * ClientHandler client =
					 * manager.getUser(Integer.valueOf(users[i])); client.response =
					 * response; SelectionKey key =
					 * client.socketChannel.keyFor(selector);
					 * key.interestOps(SelectionKey.OP_WRITE); } } else { //저장 실패
					 * 
					 * }
					 */////////////////////////////////////
					String groupId = request.getParameter("groupid"); //그룹아이디 -> 인트배열로
					int senderId = Integer.parseInt(request.getParameter("sender")); //보낸사람 아이디
					String name = manager.getUser(senderId).getUser().getName(); //보낸 사람 이름				 
					String content = name + " > " + request.getParameter("content"); //보낸 사람 이름 > 채팅 내용
					request.setParameter("content", content); //request에 담기
					broadCastForGroup(request,groupId,false);				
				}				
				///////////////////////////////////////////
				//
				//////////////////////////////////////////
				
				////////////////////////////////////////////
				//파일 전송 요청
				///////////////////////////////////////////
				else if (type.equals("file_req")) { // 파일 전송 (보내는 사람이 보낸 요청)
					String groupId = request.getParameter("groupid");				
					broadCastForGroup(request, groupId, false);
				} else if (type.equals("file_res")) { // 파일 전송 (받는 사람이 보낸 응답)

				}

				////////////////////////////////////////////
				// 원격 요청 
				///////////////////////////////////////////
				else if (type.equals("remote_req")) { // 원격 (원격 하는 사람이 보낸 요청)
					System.out.println("원격 요청 해석");
					//데이터 파싱
					String groupId = request.getParameter("groupid");
					System.out.println("group id : "+groupId);
					String from = request.getParameter("from");
					String to = request.getParameter("to");
					String port = request.getParameter("port");				
					String ip  = request.getParameter("ip");
					
//					System.out.println("요청 ip : "+ip);
//					System.out.println("요청 port"+port);
					
					//채팅방에 공지 띄우기
					Request notice = new Request("notice");
					String content = "\t"+manager.getUser(from).getUser().getName()+"님이 원격을 요청하였습니다.";
					notice.setParameter("groupid",groupId);
					notice.setParameter("content",content);					
					broadCastForGroup(notice, groupId, true);
					
					if (manager.isLogin(to)) {//받는 사람에게 수락 메시지 보내기						
						ClientHandler client = manager.getUser(to);
						Request response = new Request(request.getType());	
						response.setParameter("groupid",groupId);
						response.setParameter("from", from);
						response.setParameter("to", to);
						response.setParameter("port", port);
						response.setParameter("ip", ip);						
						client.send(response); //to에게 보내기						
					} else {
						Request response = new Request("remote_res");
						Map<String,Boolean> errors = new Hashtable<>();
						errors.put("logout",Boolean.TRUE);
						response.setParameter("logoutuser",from);
						response.setAttribute("errors",errors);	
						send(response); //원격 요청한 사람에게 보내기
					}
				} else if (type.equals("remote_res")) { // 원격( 원격 받는 사람이 보낸 응답(거절) )
					String groupId = request.getParameter("groupid");
					String from = request.getParameter("from");
					String to = request.getParameter("to");	
					String content = null;
					
					if(request.getParameter("denied").equals("true")) 						
						content =  "\t"+manager.getUser(from).getUser().getName()+"님이 원격을 거절하였습니다.";						
					else 
						content =  "\t"+manager.getUser(from).getUser().getName()+"님이 원격을 승인하였습니다.";
																		
					//채팅창에 표시하기
					Request notice = new Request("notice");						
					notice.setParameter("groupid",groupId);
					notice.setParameter("content",content);					
					broadCastForGroup(notice, groupId, true);					
				}

				////////////////////////////////////////////
				// 친구 등록
				///////////////////////////////////////////
				else if (type.equals("addfriend")) {
					System.out.println("친구등록해석");
					Request response = new AddFriendController().execute(request);
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if (errors.isEmpty()) { // 친구 등록 성공 저장하면
						// 요청한 사람이 접속중이면 메시지 전송
						int from = Integer.parseInt(request.getParameter("from"));
						int to = Integer.parseInt(request.getParameter("to"));
						if (manager.isLogin(to)) { //친구 요청 한 사람이 로그인 중이면
							ClientHandler client = manager.getUser(to);
							Request res = new Request("addfriend_req");
							/////// 요청한 사람 정보를 담아서 보냄
							try{
								MemberVO fromUser = MemberDao.getInstance().selectById(from);
								fromUser.hideForFriendsList();
								res.setAttribute("from", fromUser);
								client.send(res);		
							}catch(SQLException e){}											
						}
					}
					send(response);
				}
				////////////////////////////////////////////
				// 친구요청온것 승인
				///////////////////////////////////////////
				else if (type.equals("responseadd")) {
					System.out.println("친구 승인 해석");
					Request response = new ResponseAddFriendController().execute(request);
					int from = Integer.parseInt(request.getParameter("from"));
					int to = Integer.parseInt(request.getParameter("to"));
					if (manager.isLogin(to)) {
						ClientHandler client = manager.getUser(to);
						Request res = new Request("addfriend_res");
						/////// 요청한 사람 정보를 담아서 보냄
						try {
							MemberVO fromUser = MemberDao.getInstance().selectById(from);
							fromUser.hideForFriendsList();
							res.setAttribute("from", fromUser);
							client.send(res);
						} catch (SQLException ignored) {
						}
					}
					send(response);
				}

				////////////////////////////////////////////
				// 친구삭제
				///////////////////////////////////////////
				else if (type.equals("remove")) {
					System.out.println("친구삭제 해석");
					Request response = new RemoveFriendController().execute(request);
					int from = Integer.parseInt(request.getParameter("from"));
					int to = Integer.parseInt(request.getParameter("to"));
					if (manager.isLogin(to)) {
						ClientHandler client = manager.getUser(to);
						Request cRes = new Request("removefriend_req");						
						/////// 요청한 사람 정보를 담아서 보냄
						try {
							MemberVO fromUser = MemberDao.getInstance().selectById(from);
							fromUser.hideForFriendsList();
							cRes.setAttribute("from", fromUser);
							client.send(cRes);							
						} catch (SQLException ignored) {
						}
					}
					send(response);
				}
				
				////////////////////////////////////////////
				// 로그인 체크
				///////////////////////////////////////////
				else if(type.equals("checklogin")) {
					Request response = new Request(request.getType());
					MemberVO checkUser = (MemberVO) request.getAttribute("checkuser");
					response.setAttribute("checkuser",checkUser);
					String result = null;
					if(manager.isLogin(checkUser.getId())) {
						result = "true";
					} else {
						result = "false";
					}
					response.setParameter("result",result);
					send(response);
				}				
				
			} catch(IOException e) { //연결끊기면
				new LogoutController().execute(loginUser); //로그아웃 처리
				manager.removeUser(loginUser.getId());
				break;
			} catch(ClassNotFoundException e) {
				
			}		
		}
	}
	
	//로직을 처리한 response를 클라이언트에게 보냄
	public void send(Request response) {
		ChatServer.executorService.submit( () -> {
			try{								
				ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.writeObject(response);
				oos.flush();						
			} catch(Exception e) {
				try{					
					manager.removeUser(loginUser.getId());
					socket.close();
				}catch(IOException e2){}
				
			}
		});
	}
	
	//그룹아이디(String)을 매개변수
	private void broadCastForGroup(Request response,String groupId,boolean includeUser) {
			int[] users = UserGroupParser.getUserInteger(groupId);
			broadCastForGroup(response,users,includeUser);		
	}	
	
	//그룹아이디(int[])를 매개변수 -> 친구들에게 Request 인스턴스 전송
	private void broadCastForGroup(Request response,int[] users,boolean includeUser) {		
			int admin = loginUser.getId();
			for (int i = 0; i < users.length; i++) {			
				ClientHandler client = manager.getUser(users[i]);
				if (client != null && admin!=users[i]) {				
					client.send(response);								
				}
			}		
			if(includeUser) {
				send(response);
			}
	}
	
	public void closeSocket() {
		if(socket!=null && socket.isClosed())
			try{socket.close();}catch(IOException e){}
	}

	public MemberVO getUser() {
		return loginUser;
	}	
}