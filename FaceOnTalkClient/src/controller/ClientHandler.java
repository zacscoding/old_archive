package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dto.MemberVO;
import frame.LoginFrame;
import main.MainFrame;
import noticeframe.FriendsLoginFrame;
import noticeframe.MessageFrame;
import remote.client.RemoteClient;
import request.Request;
import serverinfo.ServerInfo;

public class ClientHandler {
	/////////////////
	//singleton
	/////////////////
	private static ClientHandler inst = null;
	private ClientHandler(){}
	public static ClientHandler getInstance(){
		if(inst == null)
			inst = new ClientHandler();
		return inst;
	}	
	
	////////////////
	// variables
	///////////////
	private Socket socket;
	private MemberVO admin;
	private MessageController messageManager;
	private MemberController friendsManager;
	
	////////////////
	//실시간 응답 처리 프레임
	////////////////
	private JFrame loginFrm;	
	private MainFrame mainFrm;
	
	////////////////
	//methods
	////////////////	
	//login
	public void login(String email, String password, JFrame loginFrm) {		
		this.loginFrm = loginFrm;						
		Request request = new Request("login");
		request.setParameter("email", email);
		request.setParameter("password", password);	
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					// connect
					socket = new Socket(ServerInfo.IP,ServerInfo.CHAT_PORT);					
					// 로긴 요청
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(request);			
					oos.flush();				
					receive();	
				} catch (Exception e) {
					e.printStackTrace();			
					new MessageFrame("서버와의 연결이 좋지않습니다.",false);
				}
			}
		};
		thread.start();		 
	}

	//Server -> User 
	private void receive() {	
		while (true) {
			try {				
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Request response = (Request) ois.readObject();
				String type = response.getType();
				/////////////////////////
				// 채팅 메시지 받음
				/////////////////////////	
				if (type.equals("message")) {
					messageManager.receiveMessage(response);
				} else if(type.equals("notice")) {
					messageManager.receiveNotice(response);
				}
				/////////////////////////
				// 내가 서버에 친구 추가 요청 보낸 것 서버 온 응답
				/////////////////////////
				else if (type.equals("addfriend")) {
					System.out.println("친구요청 보낸것 응답 옴");
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if(errors!=null) {
						if (errors.isEmpty()) {
							new MessageFrame("성공적으로 요청을 보냈습니다.\n",true);						
						} else {
							System.out.println("errors 엠티 아님");
							if (errors.get("invalidResponseId") !=null) {
								new MessageFrame("유효하지 않은 아이디 입니다.\n",false);							
							} else if (errors.get("alreadyfriend") != null) {
								new MessageFrame("이미 친구입니다.",true);							
							} else if (errors.get("SQLException") != null) {
								new MessageFrame("서버와의 연결이 좋지 않습니다.\n잠시후 연결해주세요",false);							
							}
						}
					}					
				}
				/////////////////////////
				// 새로운 사람으로 부터 친구 요청 옴
				/////////////////////////
				else if (type.equals("addfriend_req")) {
					System.out.println("새로운 사람으로 부터 친구 요청 옴");		
					MemberVO fromUser = (MemberVO) response.getAttribute("from");
					String message = fromUser.getName() + "(" + fromUser.getEmail() + ")님으로 부터 친구요청이 왔습니다.";
					int result = JOptionPane.showConfirmDialog(null, message);
					if (result == JOptionPane.OK_OPTION) {
						Request request = new Request("responseadd");
						request.setParameter("from", String.valueOf(admin.getId()));
						request.setParameter("to", String.valueOf(fromUser.getId()));
						request.setParameter("status", "1");
						send(request);
						MemberController.getInstance().addFriend(fromUser);
						mainFrm.fillFriendsList();
					}
				}
				
				/////////////////////////
				// 내가 친구 요청 보낸거 수락 옴
				/////////////////////////
				else if (type.equals("addfriend_res")) {	
					System.out.println("addfriend_res");
					MemberVO fromUser = (MemberVO) response.getAttribute("from");
					String message = fromUser.getName() + "(" + fromUser.getEmail() + ")\n님이 친구 수락하였습니다.";
					new MessageFrame(message,true);					
					friendsManager.addFriend(fromUser);
					mainFrm.fillFriendsList();
				}

				/////////////////////////
				// 내가 친구 수락 요청 보낸거
				/////////////////////////
				else if (type.equals("responseadd")) {
					System.out.println("친구 요청 결과 보낸 것 응답");
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if (!errors.isEmpty()) {
						String message = "서버와의 연결이 좋지 않습니다.\n";
						if (errors.get("invalidToId") == Boolean.TRUE) {
							message = "유효하지 않은 아이디 입니다.\n";
						} else if (errors.get("SQLException")) {
							////
						}
						new MessageFrame(message,false);
					} else { // 성공적으로 디비에 저장했으면 아무것도 하지 않음
						// empty
					}
				}
				/////////////////////////
				// 친구 로그인
				/////////////////////////
				else if (type.equals("friendlogin")) {
					MemberVO friend = (MemberVO) response.getAttribute("loginuser");
					friendsManager.addLogOnUser(friend);
					mainFrm.fillFriendsList();
					new FriendsLoginFrame(friend.getName());					
				}

				/////////////////////////
				// 친구 로그 아웃
				/////////////////////////
				else if (type.equals("friendlogout")) {
					System.out.println("친구 로그아웃 요청 받음");
					MemberVO friend = (MemberVO) response.getAttribute("logoutuser");
					friendsManager.removeLogOnUser(friend);
					mainFrm.fillFriendsList();					
				}

				/////////////////////////
				// 친구 로그인 체크
				/////////////////////////
				else if (type.equals("checklogin")) {
					System.out.println("로그인 체크 응답 받음");
					MemberVO friend = (MemberVO) response.getAttribute("checkuser");
					String result = response.getParameter("result");
					if (result.equals("true")) {
						System.out.println("로그인중");
						friendsManager.addLogOnUser(friend);
						mainFrm.fillFriendsList();
					}
				}	
				
				//////////////////
				// 원격 요청 들어옴
				//////////////////
				else if(type.equals("remote_req")) {
					System.out.println("원격 요청 받기 해석");
					String groupId = response.getParameter("groupid");
					String from = response.getParameter("from");					
					String to = response.getParameter("to");
					System.out.println(groupId);
					String name = friendsManager.getMember(from).getName();
					int result = JOptionPane.showConfirmDialog(null,name+"님의로부터 원격을 수락하시겠습니까?");
					Request request = new Request("remote_res");
					if (result == JOptionPane.OK_OPTION) {
						request.setParameter("denied","false");
						String ip = response.getParameter("ip");
						String port = response.getParameter("port");
						new RemoteClient(ip,Integer.parseInt(port));						
					} else {						
						request.setParameter("denied","true");						
					}					
					request.setParameter("from",to);
					request.setParameter("to",from);
					request.setParameter("groupid",groupId);
					send(request);
				}
				//////////////////
				// 원격 요청 한거 거절 or 로그아웃
				//////////////////
				else if(type.equals("remote_res")) {
					System.out.println("원격 요청 해석");
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if (errors == null) {
						String isDenied = response.getParameter("denied");
						if(isDenied.equals("true")) {
							String from = response.getParameter("from");
							new MessageFrame(friendsManager.getMember(from).getName()+"님이 \n원격을 거절하였습니다.",false);							
						}
					} else { // 로그아웃함
						if(errors.get("logout")!=null) {
							String logoutUser = response.getParameter("logoutuser");	
							new MessageFrame(friendsManager.getMember(logoutUser).getName()+"님은 \n현재 로그아웃 상태입니다.",false);
						}
					}									
				}
				
//				else if(type.equals("file_req")) {
//					Request request = new Request("file_req");			
//					request.setParameter("groupid",groupId);
//					request.setAttribute("file",selectedFile);						
//					request.setParameter("port", String.valueOf(serverSocket.getLocalPort()));
//					request.setParameter("ip",ip);
//					request.setParameter("from",user);
//					
//					int result = JOptionPane.showConfirmDialog(null, "파일전송?");
//					if(result == JOptionPane.OK_OPTION) {
//						
//					}
//					
//				} else if(type.equals("file_res")) {
//					
//				}
				
				
				
				//////////////////
				// 로그인 요청에 대한 응답
				/////////////////
				else if(type.equals("login")) { //로그인에 대한 응답
					System.out.println("로그인에 대한 응답 옴");									
					Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
					if (errors.isEmpty()) { // login success
						admin = (MemberVO)response.getAttribute("loginuser");
						friendsManager = MemberController.getInstance();
						friendsManager.initFriendsList(admin
								,response.getParameter("ip")
								,(List<MemberVO>)response.getAttribute("onlist")
								,(List<MemberVO>)response.getAttribute("offlist")
								,(List<MemberVO>) response.getAttribute("pendinglist"));							
						messageManager = MessageController.getInstance();
						loginFrm.dispose();
						mainFrm = new MainFrame(response);
						mainFrm.setVisible(true);						
					} else { //login fail
						if(errors.get("notmatch")!=null) { //비번 틀림
							new MessageFrame("ID/PASSWORD를 확인해주세요.",false);
							break;
						} else if(errors.get("duplicatedLogin")!=null) { //동시 로그인
							int result = JOptionPane.showConfirmDialog(null,"현재 접속중인 유저가 있습니다.로그아웃 시키겠습니까?");							
							if (result == JOptionPane.OK_OPTION) {
														
							} else {						
														
							}									
						}					
					}					
				}				
				else{
					//empty
				}
			} catch (IOException e) {
				e.printStackTrace();									
				////////////////
				//서버 접속 끊겼다는 메시지					
				////////////////
				new MessageFrame("서버와의 연결이 끊겼습니다.",false);
				mainFrm.dispose();
				new LoginFrame("Face On Talk").setVisible(true);
				break;				
			} catch(ClassNotFoundException ignored) {
				ignored.printStackTrace();
			} 
		}						
	}
	
	
	
	//User -> Server 
	public void send(Request request) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							new BufferedOutputStream(socket.getOutputStream()));					
					// send to server
					oos.writeObject(request);
					oos.flush();					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	
	public Socket getSocket() {
		return socket;
	}
}
