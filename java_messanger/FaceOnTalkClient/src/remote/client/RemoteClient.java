package remote.client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RemoteClient extends JFrame {
	private Socket socket = null;
	private String ip;
	private int port;
	private ScreenSender screenSender;
	private CommandReceiver commandReceiver;
	
	public RemoteClient(String ip,int port) {
		super("Face On Talk Remote Service");
		this.ip = ip;
		this.port = port;
		init();
		startClient();
	}	
	
	private void startClient() {
		Robot robot = null;
		Rectangle rectangle = null;
		try {
			socket = new Socket(ip,port);
			
			//screen device
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice();
			
			//screen dimension, rectangle
			Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
			rectangle = new Rectangle(screenDim);			
			robot = new Robot(gd);
			screenSender = new ScreenSender(socket,robot,rectangle);
			commandReceiver = new CommandReceiver(socket,robot);
			setVisible(true);
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(AWTException e) {
			e.printStackTrace();
		}
	}	
	
	//////////////
	//GUI
	private void init() {
		JButton btnExit = new JButton("³¡³»±â");
		setBounds(120,120,200,200);
		add(btnExit);		
		btnExit.addActionListener(e -> {
			screenSender.interrupt();
			commandReceiver.interrupt();
			dispose();			
		});		
	}
}
