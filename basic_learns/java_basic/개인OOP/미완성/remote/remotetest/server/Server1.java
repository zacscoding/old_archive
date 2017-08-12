package remotetest.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Server1 {
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static ObjectInputStream ois= null;
	public static void main(String[] args){
		
		
		try{
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));			
			
			socket = serverSocket.accept();
			//ois = new ObjectInputStream(socket.getInputStream());
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));			
			
			Thread thread = new Thread(){
				@Override
				public void run() {					
					try{
						ois.readObject();
						long startTime = System.currentTimeMillis();
						for(int i=0;i<100;i++){						
							ImageIcon imageIcon = (ImageIcon)ois.readObject();
						}
						long lastTime = System.currentTimeMillis();
						System.out.println("시간 : "+(lastTime-startTime));
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			};			
			thread.start();			
		}catch(IOException e){
			if(serverSocket!=null && !serverSocket.isClosed())
				try{serverSocket.close();}catch(IOException ee){}
			e.printStackTrace();
		}		
	}
}
