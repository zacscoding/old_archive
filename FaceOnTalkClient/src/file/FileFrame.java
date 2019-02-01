package file;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class FileFrame extends JFrame {
	JProgressBar statusBar;
	String title;
	
	public FileFrame(String title) {
		this.title = title;
		init();
	}
	
	
	///////////
	//GUI
	private void init() {
		setBounds(120,120,400,400);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		JPanel list = new JPanel();
		list.setBackground(Color.WHITE);		
		list.setBorder(loweredetched);
		list.setLayout(new FlowLayout());		
		JLabel status = new JLabel(title+"");
		statusBar = new JProgressBar();
		statusBar.setMaximum(100);
		statusBar.setMinimum(0);		
		list.add(status);	list.add(statusBar);		
		add(list);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start();
	}
	void start() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true){
					try{
						Thread.sleep(50);
					}catch(InterruptedException e){}
					if(statusBar.getValue() == statusBar.getMaximum())
						dispose();
					statusBar.setValue(statusBar.getValue()+1);
				}				
			}
		};
		thread.start();
	}
}
