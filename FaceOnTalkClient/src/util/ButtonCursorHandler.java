package util;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/*
 * 마우스 진입하면 손가락 모양으로 바꿔주는 클래스
 */

public class ButtonCursorHandler implements MouseListener {
	private static Cursor enteredCursor = new Cursor(Cursor.HAND_CURSOR);
	private static Cursor exitedCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	
	//singleton
	private static ButtonCursorHandler inst = null;
	private ButtonCursorHandler(){}		
	public static ButtonCursorHandler getInstance() {
		if(inst ==null)
			inst = new ButtonCursorHandler();
		return inst;
	}		
	//change cursor type
	@Override
	public void mouseEntered(MouseEvent e) {
		((JButton)e.getSource()).setCursor(enteredCursor);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		((JButton)e.getSource()).setCursor(exitedCursor);
	}	
	//empty
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
