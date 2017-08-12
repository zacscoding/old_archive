package remotetest.server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Server2 {
	static ServerSocket serverSocket = null;
	static Socket socket = null;
	static InputStream is = null;
	public static void main(String[] args){	
		try{
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));			
			
			socket = serverSocket.accept();
			is = socket.getInputStream();
			
			Thread thread = new Thread(){
				@Override
				public void run() {					
					try{
						ImageIO.read(is);
						long startTime = System.currentTimeMillis();
						for(int i=0;i<100;i++){
							BufferedImage image = ImageIO.read(is);	
							System.out.println(i+"받음");
						}
						long lastTime = System.currentTimeMillis();
						System.out.println("시간 : "+(lastTime-startTime));
					}catch(Exception e){}
				}				
			};			
			thread.start();			
		}catch(IOException e){
			if(serverSocket!=null && !serverSocket.isClosed())
				try{serverSocket.close();}catch(IOException ee){}
		}		
	}
}
