package remoteserver;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ServerInitiator {
	private JFrame frame = new JFrame();	
	private JDesktopPane desktop = new JDesktopPane();

	public static void main(String[] args){		
		new ServerInitiator().initialize(5001);				
	}
	
	public void initialize(int port){
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("112.152.25.67",5001));			
			drawGUI();			
			Socket client = serverSocket.accept();
			System.out.println("연결 성공 : "+client.getInetAddress());
			System.out.println("new client connected");
			new ClientHandler(client,desktop);						
		}catch(IOException ex){			
			if(serverSocket!=null && !serverSocket.isClosed())
				try{serverSocket.close();}catch(IOException e){}
		}
	}	
	public void drawGUI(){
		frame.add(desktop,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Show the frame in a maximized state
        frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
	}
}
