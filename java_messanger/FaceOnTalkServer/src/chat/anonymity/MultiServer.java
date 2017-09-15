package chat.anonymity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.ServerFrame;
import serverinfo.ServerInfo;
import util.SocketUtil;

public class MultiServer extends ServerFrame{
	public MultiServer(String title) {
		super(title);
		super.initFrame();
	}

	final String SERVER = "[SERVER]";
	private String username;
	private ServerSocket filesocket;			//파일 서버
	private ServerSocket serverSocket;
	public void startServer() {		
		Thread thread = new Thread() {
			@Override
			public void run() {
				displayText("[start server]");
				try{					
					serverSocket = new ServerSocket(ServerInfo.ANONY_CHAT_PORT);					
					while(true){
						Socket socket = serverSocket.accept();
						displayText("connect : "+socket.getInetAddress());
						Client_Connection client = new Client_Connection(socket);
						new Thread(client).start(); 		//클라이언트 쓰레드를 시작합니다.
						Room_Manager.makeRoom("Main");	
						Room_Manager.allUserList.add(client);
					}					
				}catch(IOException e){
					e.printStackTrace();
					stopServer();
				}
			}
			
		};
		thread.start();		
	}
	
	public void stopServer() {
		SocketUtil.close(serverSocket);
		displayText("[stop server]");
	}
}
