package remote.server;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScreenReceiver {
	private ObjectInputStream ois;
	private JPanel centerPanel;
	private boolean isConnected;
	
	public ScreenReceiver(ObjectInputStream ois,JPanel centerPanel) {
		this.ois = ois;
		this.centerPanel = centerPanel;
		isConnected = true;
		receive();
	}	
	private void receive() {
		Thread thread = new Thread() {
			@Override
			public void run() {				
				while(isConnected) {
					try {
						/////////////
						//Client로부터 스크린 받기
						//////////////
						ImageIcon imageIcon = (ImageIcon)ois.readObject();
						Image image = imageIcon.getImage();
						image = image.getScaledInstance(centerPanel.getWidth(),centerPanel.getHeight(),Image.SCALE_FAST); //resize for center panel
						
						/////////////
						//스크린 centerPanel에 넣기
						//////////////
						Graphics graphics = centerPanel.getGraphics();
						graphics.drawImage(image, 0, 0, centerPanel.getWidth(), centerPanel.getHeight(),centerPanel);						
					} catch(IOException e) {
						e.printStackTrace();
						break;
					} catch(ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}	
	public void interrupt() {
		isConnected = false;
	}
}
