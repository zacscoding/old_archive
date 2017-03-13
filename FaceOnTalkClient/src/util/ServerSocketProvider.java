package util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/*
 * 이용 가능한 port로 서버 소켓 열어주는 클래스
 */
class NotExistAvailablePort extends Exception {	
}

public class ServerSocketProvider {
	private static int startPort = 9000;
	private static int trySize = 5;

	public static ServerSocket getServerSocket(String ip) {		
		ServerSocket serverSocket = null;
		while(true) {
			try {
				serverSocket = createServerSocket(ip,startPort);
				break;
			} catch(NotExistAvailablePort e) {
				startPort += trySize;
			}			
		}
		return serverSocket;		
	}
	
	private static ServerSocket createServerSocket(String ip,int startPort) throws NotExistAvailablePort {
		int[] ports = new int[trySize]; //e.g) 9000~9004까지 인트 배열에 담음
		for(int i=0;i<ports.length;i++) {
			ports[i] = startPort+i;
		}
		for(int port : ports) {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket();
				serverSocket.bind(new InetSocketAddress(ip,port));
				return serverSocket; //서버 열리면 반환 아니면 catch문으로 가서 continue								
			} catch(IOException e) {
				if(serverSocket!=null && !serverSocket.isClosed())
					try{serverSocket.close();}catch(IOException e2) {}
				continue;
			}
		}
		throw new NotExistAvailablePort(); //9000~9004번이 사용 불가능하면 위의 getServerSocket()메소드에 catch부분 -> 9005로 바꿔줘서 반복
	}		
	//private constructor
	private ServerSocketProvider(){}
}
