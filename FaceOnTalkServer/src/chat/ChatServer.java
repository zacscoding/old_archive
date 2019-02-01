package chat;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import controller.login.LoginUserManager;
import main.ServerFrame;
import serverinfo.ServerInfo;
import util.SocketUtil;

public class ChatServer extends ServerFrame implements ActionListener {
	static ExecutorService executorService;
	private ServerSocket serverSocket;
		
	public ChatServer() {
		super("Chat Server");
		super.initFrame();
	}	
	
	public void startServer() {
		executorService = new ThreadPoolExecutor (3, 50, 100L, TimeUnit.MINUTES, new SynchronousQueue<Runnable> ());
		try {
			serverSocket = new ServerSocket(ServerInfo.CHAT_PORT);			
		}catch(IOException e) {
			if(serverSocket!=null && !serverSocket.isClosed()) {
				stopServer();				
			}
			return;
		}	
		
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
		displayText("[Chat Server Start]");
	}
	
	public void stopServer() {
		try {
			LoginUserManager manager = LoginUserManager.getInstance();
			manager.removeAllUser();
			SocketUtil.close(serverSocket);
			SocketUtil.close(executorService);
			displayText("[stopped server]");
		}catch(Exception ignored){}
	}		
}
