package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * 서버 GUI -> Web,Chat서버가 이 클래스를 상속해서
 * GUI도 공통적으로 사용하고, startSever(); stopServer(); 오버라이딩에 의해
 * 실제 Web,ChatServer의 메소드 부분이 실행됨
 */

public class ServerFrame extends JFrame implements ActionListener{

	public ServerFrame(String title) {
		super(title);
	}
	//////////////////
	// UI
	protected JTextArea txtDisplay;
	protected JButton btnStartStop;
	protected JScrollPane displayScroll;

	public void initFrame() {
		setBounds(700, 200, 400, 400);
		setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		displayScroll = new JScrollPane(txtDisplay);

		btnStartStop = new JButton("start");
		btnStartStop.addActionListener(this);
		add(displayScroll, BorderLayout.CENTER);
		add(btnStartStop, BorderLayout.SOUTH);		
		addWindowListener(new WindowAdapter() {
			///////////////
			/// 서버 종료 코드 넣기
			@Override
			public void windowClosing(WindowEvent e) {
				/// dispose();
				stopServer();
				System.exit(0);
			}
		});
	}
	
	protected void displayText(String text) {
		txtDisplay.append(text+"\n");
		displayScroll.getVerticalScrollBar().setValue(displayScroll.getVerticalScrollBar().getMaximum());
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getActionCommand() == "start") {
			btnStartStop.setText("stop");
			startServer();
		} else if (e.getActionCommand() == "stop") {
			btnStartStop.setText("start");
			stopServer();
		}
	}	
	public JButton getStartStopButton() {
		return btnStartStop;		
	}
	public void startServer() {
		//empty
	}
	public void stopServer() { 
		//empty
	}
}
