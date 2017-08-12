package remoteclient;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClientInitiator {
	Socket socket = null;
	
	public static void main(String[] args){
		new ClientInitiator().initialize("localhost",50001);
	}
	
	public void initialize(String ip,int port){
		Robot robot = null;
		Rectangle rectangle = null;
		
		try{
			System.out.println("connecting to server..");
			socket = new Socket(ip,port); //연결 요청
			
			//get default screen device
			GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
			
			//get screen dimensions
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			rectangle = new Rectangle(dim);
			
			//prepard Robot object
			robot = new Robot(gDev);
			
			//draw client gui
			drawGUI();
			
			//ScreenSpyer sends screenshots of the client scree
			new ScreenSpyer(socket,robot,rectangle);
			
			//ServerDelegate recieves server commads and execute them
			new ServerDelegate(socket,robot);
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(AWTException e){
			e.printStackTrace();
		}
		
	}
	
	private void drawGUI(){
		JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("Terminate");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
	}
	

}
