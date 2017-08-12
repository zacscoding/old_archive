package remotetest.client;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Client1 {
	static Socket socket;
	static ObjectOutputStream oos;
	static Robot robot;
	static Rectangle rectangle;
	public static void main(String[] args){
		try{
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost",5001));
			
			//oos = new ObjectOutputStream(socket.getOutputStream());
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));			
			robot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
			rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());			
			
			for(int i=0;i<101;i++){
				BufferedImage image = robot.createScreenCapture(rectangle);
				ImageIcon imageIcon = new ImageIcon(image);
				oos.writeObject(imageIcon);
				oos.flush();
				oos.reset();				
			}
		}catch(IOException e){
			if(socket!=null && !socket.isClosed())
				try{socket.close();}catch(IOException ignored){}				
		}catch(AWTException e){
			
		}
		
	}
}
