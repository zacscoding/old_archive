package grade;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchFrame extends Frame implements Runnable, FocusListener {
	private GradeHandler manager = GradeHandler.getInstance();

	public SearchFrame() {
		super("검색/수정/삭제");
	}

	private void initFrame() {
		///////////////////////////////////////////
		// 초기설정
		this.setSize(600, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2.0 - this.getWidth() / 2.0);
		int ypos = (int) (screen.getHeight() / 2.0 - this.getHeight() / 2.0);
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		///////////////////////////////////////////
		
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void run() {
		initFrame();
	}
}
