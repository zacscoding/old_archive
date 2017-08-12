package remoteclient;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;

//서버에게 자신의 스크린 정보를 보냄
public class ScreenSpyer extends Thread{
	Socket socket = null;
	Robot robot = null; //스크린 캡쳐하는데 사용
	Rectangle rectangle = null; //스크린 디멘션 사용시
	boolean continueLoop = true; //프로그램 지속 여부
	
	public ScreenSpyer(Socket socket,Robot robot,Rectangle rect){
		this.socket = socket;
		this.robot = robot;
		rectangle = rect;
		start();
	}
	
	public void run(){
		ObjectOutputStream oos = null; 
		
		try{
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			/*
			 * 마우스의 정확한 위치를 조절하기 위해 screen size를 보냄.
			 */
			oos.writeObject(rectangle);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		while(continueLoop){
			//capture screen
			BufferedImage image = robot.createScreenCapture(rectangle);
			
			/*
			 * BufferedImage는 Serializable을 구현 안하므로 ImageIcon으로 변경 
			 */
			ImageIcon imageIcon = new ImageIcon(image);
			
			//전송
			try{
				//System.out.println("before send image");
				oos.writeObject(imageIcon);
				oos.reset();				
			}catch(IOException e){
				continueLoop = false;
				e.printStackTrace();
			}
			
			//wait for 100ms to reduce network traffic
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
















