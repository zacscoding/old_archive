package remoteserver;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

public class ClientCommandsSender implements KeyListener,MouseMotionListener,MouseListener{
	
	private Socket cSocket = null;
	private JPanel cPanel = null;
	private PrintWriter writer = null;
	private Rectangle clientScreenDim = null;
	
	public ClientCommandsSender(Socket s,JPanel p,Rectangle r){
		cSocket = s;
		cPanel = p;
		clientScreenDim = r;
		
		//Panel에 리스너 등록
		cPanel.addKeyListener(this);
		cPanel.addMouseListener(this);
		cPanel.addMouseMotionListener(this);
		
		try{
			//client에게 보낼 PrintWriter 준비
			writer = new PrintWriter(cSocket.getOutputStream());			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
		
	//KeyListener
	@Override
	public void keyPressed(KeyEvent e) {		
		//System.out.println("key pressed");
		writer.println(EnumCommands.PRESS_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("key released");
		writer.println(EnumCommands.RELEASE_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//empty
	}
	///////////////
	//MouseMotionListener
	@Override
	public void mouseMoved(MouseEvent e) {
		double xScale = clientScreenDim.getWidth()/cPanel.getWidth();
		//System.out.println("xScale : "+xScale);
		double yScale = clientScreenDim.getHeight()/cPanel.getHeight();
		//System.out.println("yScale : "+yScale);
		//System.out.println("mouse moved");
		
		writer.println(EnumCommands.MOVE_MOUSE.getAbbrev());
		writer.println((int)(e.getX() * xScale));
		writer.println((int)(e.getY() * yScale));
		writer.flush();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {  
		//empty
	}
	
	///////////////
	//MouseListener
	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("mouse pressed");
		writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if(button == 3)
			xButton = 4;
		writer.println(xButton);
		writer.flush();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouse released");
		writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if(button == 3)
			xButton = 4;
		writer.println(xButton);
		writer.flush();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//empty		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//empty		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//empty		
	}
}
