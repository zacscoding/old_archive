package remotetest.client;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Client2 {
	static Socket socket;
	static OutputStream os;
	static Robot robot;
	static Rectangle rectangle;
	public static void main(String[] args){
		try{
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost",5001));
			
			os = socket.getOutputStream();
			
			robot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
			rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());			
			
			for(int i=0;i<101;i++){
				BufferedImage image = robot.createScreenCapture(rectangle);
				ImageIO.write(image, "jpg",os);
				os.flush();				
			}
		}catch(IOException e){
			if(socket!=null && !socket.isClosed())
				try{socket.close();}catch(IOException ignored){}				
		}catch(AWTException e){
			
		}
		
	}
}
