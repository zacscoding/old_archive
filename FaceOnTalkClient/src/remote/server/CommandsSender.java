package remote.server;

import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JPanel;

import remote.Commands;

public class CommandsSender implements KeyListener 
								,MouseMotionListener ,MouseListener {
	
	private Socket socket;
	private JPanel centerPanel;
	private PrintWriter writer;
	private Rectangle cScreenDim;
	
	public CommandsSender(Socket socket,JPanel centerPanel,Rectangle screenDim) {
		this.socket = socket;
		this.centerPanel = centerPanel;
		this.cScreenDim = screenDim;
		init();
	}
	
	private void init() {
		centerPanel.addKeyListener(this);
		centerPanel.addMouseMotionListener(this);
		centerPanel.addMouseListener(this);		
		try {
			writer = new PrintWriter(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	/////////////////////////
	// KeyListener
	////////////////////////
	@Override
	public void keyPressed(KeyEvent event) {
		writer.println(Commands.KEY_PRESS);
		writer.println(event.getKeyCode());
		writer.flush();		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		writer.println(Commands.KEY_RELEASE);
		writer.println(event.getKeyCode());
		writer.flush();
	}

	@Override
	public void keyTyped(KeyEvent event) {		
		//empty
	}
	
	/////////////////////////
	// MouseMotionListener
	////////////////////////
	@Override
	public void mouseDragged(MouseEvent event) {	
		//empty
	}	
	@Override
	public void mouseMoved(MouseEvent event) {		
		//e.g) 클라이언트 스크린 너비 : 200 // 서버 Panel 너비 : 100
		// -> 서버에서 마우스 x좌표가 10 -> 클라이언트의  x = 10/100*200 
		// y좌표도 같음		
		
		double xRatio = cScreenDim.getWidth() / centerPanel.getWidth();
		double yRatio = cScreenDim.getHeight() / centerPanel.getHeight();
		
		writer.println(Commands.MOUSE_MOVE);
		System.out.println("y : "+(int)(event.getY()*yRatio));
		System.out.println("x : "+(int)(event.getX()*xRatio));
		writer.println((int)(event.getX()*xRatio));
		writer.println((int)(event.getY()*yRatio));
		writer.flush();
	} 
	private void parsingRobotComm(MouseEvent e) {		
		int button = e.getButton();
		int xButton = 16;		
        if (button == 3) {
            xButton = 4;
        }
		writer.println(xButton);		
		writer.flush();	
	}

	/////////////////////////
	// MouseListener
	////////////////////////	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//empty		
	}
	@Override
	public void mousePressed(MouseEvent event) {
		writer.println(Commands.MOUSE_PRESS);
		parsingRobotComm(event);			
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		writer.println(Commands.MOUSE_RELEASE);
		parsingRobotComm(event);
	}
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		//empty
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//empty
	}

	
	
	
	
	
	
	
	

}
