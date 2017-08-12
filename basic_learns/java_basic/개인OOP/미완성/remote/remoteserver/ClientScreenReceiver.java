package remoteserver;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//클라이언트의 스크린샷을 받음
public class ClientScreenReceiver extends Thread{
	
	private ObjectInputStream ois = null;
	private JPanel cPanel = null;
	private boolean continueLoop = true;
	
	public ClientScreenReceiver(ObjectInputStream ois, JPanel p){
		this.ois = ois;
		cPanel = p;
		
		//
		start();		
	}	
	@Override
	public void run(){
		try{
			//사용자의 스크린샷 이미지를 읽고 그리기
			while(continueLoop){
				//사용자 스크린샷 받고 , 현재 panel에 맞게 리사이즈
				ImageIcon imageIcon = (ImageIcon)ois.readObject();
				//System.out.println("new image received");
				Image image = imageIcon.getImage();
				image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);				
				//cPanel에 이미지 넣기
				Graphics graphics = cPanel.getGraphics();
				graphics.drawImage(image, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);				
			}
		}catch(IOException e){
			//continueLoop = false;
			JOptionPane.showMessageDialog(cPanel, "연결 끊김");
			cPanel.getGraphics().drawImage(null, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel); 
			//e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
























