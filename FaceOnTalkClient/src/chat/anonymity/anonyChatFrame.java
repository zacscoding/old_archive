package chat.anonymity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import serverinfo.ServerInfo;

/////////////////////
//Client Main Start
public class anonyChatFrame{
	Wait_Room waitroom = new Wait_Room("대기실");
	static Socket socket;							
	static String name;								
	static BufferedReader networkReader;			
	static BufferedWriter networkWriter;			
	static String ip =ServerInfo.IP;		// IP
	static int port	= ServerInfo.ANONY_CHAT_PORT;				// PORT번호
	public anonyChatFrame(String name) throws IOException {		
		anonyChatFrame.name = name;	//사용자 ID		
		waitroom.showFrame(name);
		setSocket(ip, port, name);
	}
	public void setSocket(String ip, int port, String name) throws IOException{
		try{
			socket = new Socket(ip,port);
			networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			Listener_Of_Client listener = new Listener_Of_Client(networkWriter, networkReader, socket ,waitroom);
			//만약 생성자를 waitroom으로만 하게 되면 Listener_Of_Client에서는 서버명.networkWriter[Reader]로 해야함.
			listener.setDaemon(true);
			listener.start();
			//채팅자가 입장했음을 알림.
			sendMsg(MsgInfo.NEW, name);	
		}catch(IOException e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void sendMsg(String token, String msg) throws IOException {
		if(msg == null){
			msg = "";
		}
		networkWriter.write(token + "/" + msg + "\n");
		networkWriter.flush();
	}

	public static void main(String[] args) throws IOException {
		InputId getid = new InputId();
		getid.setVisible(true);
	}
}
