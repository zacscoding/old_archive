package web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.ActionFactory;
import main.ServerFrame;
import serverinfo.ServerInfo;
import util.SocketUtil;


/*
 * Class MainServer
 * :연결 요청을 받음 -> 핸들러 -> 응답
 */
public class WebServer extends ServerFrame {
	public static ExecutorService executorService;
	private ServerSocket serverSocket;	

	public WebServer() {
		super("Web Server");		
		initFrame();
	}
	
	//서버 시작
	public void startServer() {
		///////////////////
		//나중에 지우기
		getStartStopButton().setText("stop");		
		///////////////////////
		//스레드풀 <<개수 테스트해보기		
		
		executorService = Executors.newFixedThreadPool(100);
		ActionFactory.getInstance();
		//소켓 바인딩
		try {
			serverSocket = new ServerSocket(ServerInfo.WEB_PORT);			
		}catch(IOException e) {
			if(serverSocket!=null && !serverSocket.isClosed()) {
				stopServer();				
			}
			return;
		}		
		//연결요청 스레드 풀 등록
		executorService.submit( () -> {			
			while (true) {
				Socket socket = null;
				try {
					socket = serverSocket.accept();
					displayText("connect : "+socket.getInetAddress());
					new ClientHandler(socket);
				} catch (IOException e) {
					SocketUtil.close(socket);
				}
			}
		});		
		displayText("[Web Server start]");
	}	
	
	//서버 종료
	public void stopServer(){		
		SocketUtil.close(serverSocket);
		SocketUtil.close(executorService);
	}	
}
