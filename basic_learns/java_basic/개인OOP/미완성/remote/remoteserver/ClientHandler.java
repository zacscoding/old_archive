package remoteserver;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class ClientHandler extends Thread{
	private JDesktopPane desktop = null;
	private Socket cSocket = null;
	private JInternalFrame interFrame = new JInternalFrame("Client Screen",true,true,true);
	
	private JPanel cPanel = new JPanel();
	
	public ClientHandler(Socket cSocket,JDesktopPane desktop){
		this.cSocket = cSocket;
		this.desktop = desktop;
		start();		
	}
	
	public void drawGUI(){
		interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        desktop.add(interFrame);
        try {
            //Initially show the internal frame maximized
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        //this allows to handle KeyListener events
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
	}
	
	public void run(){
		//사용자 스크린 사이즈를 나타내는데 사용
		Rectangle clientScreenDim = null;		
		//client의 스크린샷이나 스크린 dimension 을 읽는데 사용
		ObjectInputStream ois = null;
		//drawing GUI
		drawGUI();
		
		//client의 스크린 사이즈 얻기
		try{
			ois = new ObjectInputStream(new BufferedInputStream(cSocket.getInputStream()));
			clientScreenDim = (Rectangle) ois.readObject();			
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}		
		//스크린샷 받기 시작
		new ClientScreenReceiver(ois,cPanel);		
		//사용자에게 command 보내기
		new ClientCommandsSender(cSocket,cPanel,clientScreenDim);		
	}	
}













