package chat.anonymity;

import java.io.*;
import java.net.*;

class Listener_Of_Client extends Thread{
	BufferedWriter networkWriter;
	BufferedReader networkReader;
	Socket socket;
	String name;
	static String[] parsingData;
	static ChatRoom startchat;
	Wait_Room waitroom;
	Listener_Of_Client(BufferedWriter networkWriter, BufferedReader networkReader, Socket socket ,Wait_Room waitroom){
		this.networkWriter = networkWriter;
		this.networkReader = networkReader;
		this.socket = socket;
		this.waitroom = waitroom;
	}
	@Override
	public void run() {
		try {
			String line;
			while( (line = networkReader.readLine()) != null ){
				System.out.println("서버에서 온 메세지 : " + line);
				parsingData = line.split("/");
	/*==================================================================
	 *				 대기실에 출력해야할 메세지
	 * ==================================================================*/
				if( line.startsWith(MsgInfo.MAIN)){		
					Wait_Room.showText.append(parsingData[1]+"\n");
				}
	/*==================================================================
	 *				 line이 USERLIST로 시작하면..
	 * ==================================================================*/					
				else if(line.startsWith(MsgInfo.USERLIST)){		//접속자 정보
					waitroom.IDlist.removeAll();			//대기실의 IDlist 초기화
					String data = parsingData[1].substring(1, parsingData[1].length()-1);
					String lists = new String();
					// Main, aaa 의 구분은 , 이므로 잘라낸다.
					String[] roomlists = data.split(", ");
					for(int i = 0; i<roomlists.length; i++){
						lists = roomlists[i];				
						waitroom.IDlist.add(lists);			//대기실의 idlist에 등록
					}
				}
	/*==================================================================
	 *				 line이 ROOMLIST로 시작하면 방 정보를 출력
	 * ==================================================================*/
				else if(line.startsWith(MsgInfo.ROOMLIST)){	
					waitroom.roomList.removeAll();
					for(int i=1; i<parsingData.length; i++){
						waitroom.roomList.add(parsingData[i]);	//list에 등록
					}
					waitroom.roomList.remove("Main");
				}
	/*==================================================================
	 *				 line이 MAKEROOM으로 시작하면 방 만듦
	 * ==================================================================*/
				else if( line.startsWith(MsgInfo.MAKEROOM)){		
					// [MAKEROOM]/방이름 형식.
					startchat = new ChatRoom(parsingData[1]);
					startchat.showFrame(anonyChatFrame.name);
				}
	/*==================================================================
	 *				 line이 ENTER으로 시작하면 방에 접속
	 * ==================================================================*/
				else if( line.startsWith(MsgInfo.ENTER)) {
					// [ENTER]/방이름 형식이므로
					startchat = new ChatRoom(parsingData[1]);
					startchat.dispose();
					startchat.showFrame(anonyChatFrame.name);
				}
	/*==================================================================
	 *				 line이 CHATUSER로 시작하면 대화방에 출력해야 함.
	 * ==================================================================*/			
				else if( line.startsWith(MsgInfo.CHATUSER)){
			// [CHATUSER]/유저1/유저2/유저3/.....유저x/방장대화명
					startchat.IDlist.removeAll();						//대화방 ID리스트 초기화
					for(int i=1; i<parsingData.length-1; i++){			//유저1부터 유저x까지
//						System.out.print(parsingData[i]+"\t");			//확인용
//						System.out.println();							//확인용
						startchat.IDlist.add(parsingData[i]);			//list에 등록 유저1~유저x까지만
					}
					startchat.IDlist.remove(parsingData[parsingData.length-1]);		//방장대화명을 가진 유저를 지움.
					startchat.IDlist.add("[방장]"+parsingData[parsingData.length-1]);	//그리고 방장대화명을 등록.
//					System.out.println("리스트에 유저리스트 등록 완료");	//확인
				}
	/*==================================================================
	 *				 line이 GOWAIT로 시작하면.. 대기실로 가야 함.
	 * ==================================================================*/
				else if( line.startsWith(MsgInfo.GOWAIT)){
					startchat.setVisible(false);	//대화방 창 숨기기
					waitroom.setVisible(true);		//숨겼던걸 다시 보이게 함.
					Wait_Room.showText.setText("");
				
	/*==================================================================
	 *				line이 CHIEF이면 자신의 방장코드를 1 또는 0으로 입력한다.
	 * ==================================================================*/					
				}else if( line.startsWith(MsgInfo.CHIEF) ){
					startchat.chiefcode = Integer.parseInt(parsingData[1]);
	/*==================================================================
	 *				line이 KICK이면 강퇴~! 당했으므로.. 대기실로..
	 * ==================================================================*/
				}else if( line.startsWith(MsgInfo.KICK) ){
					startchat.setVisible(false);	//대화방 창 숨기기
					waitroom.setVisible(true);		//숨겼던걸 다시 보이게 함.
					Wait_Room.showText.setText("");
				}else if( line.startsWith(MsgInfo.SHOWUSER)){
					Show_All showuser= new Show_All();
					for(int i = 1; i<parsingData.length; i++){
						Show_All.idlist.add(parsingData[i]);
					}
	/*==================================================================
	 *				 그 외에는 각 채팅방에 출력해야 됨.
	 * ==================================================================*/				
				}else{			
					ChatRoom.showText.append(parsingData[1]+"\n");
				}
			}
			System.out.println("종료");
			networkWriter.close();
			try {networkReader.close();} catch (IOException e1) {}
			try {socket.close();} catch (IOException e) {}
		} catch (IOException e) {
			System.out.println("소켓 종료");
		}
	}
}