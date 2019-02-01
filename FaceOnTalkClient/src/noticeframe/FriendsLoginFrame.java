package noticeframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class FriendsLoginFrame extends JFrame {	
	private final int WIDTH = 230 , HEIGHT = 150;
	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();
	private JButton btnImage = new JButton(new ImageIcon("image/friendimage.png"));
	private JTextArea areaNotice;
	
	
	public FriendsLoginFrame(String title){  //String title
		super("Friend Log in..");
		areaNotice = new JTextArea(title+"님이 \n로그인하였습니다.");
		initFrame();		
	}
	
	public void initFrame(){
		  setSize(WIDTH,HEIGHT);
	      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	      int xpos = (int) (screen.getWidth() - this.getWidth());
	      int ypos = (int) (screen.getHeight() - this.getHeight()-40);
	      setLocation(xpos, ypos);
	      setResizable(false);
	      setVisible(true);
	
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(null);

		  panel.setBounds(0, 0, WIDTH, HEIGHT);
		  contentPane.add(panel);
		  panel.setLayout(null);
		  panel.setBackground(new Color(255, 255, 255));
	      ////////////
		  ///error버튼
		  panel.add(btnImage);
		  btnImage.setBounds(20, 25, 45, 45);
		  btnImage.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
		  btnImage.setContentAreaFilled(false);
		  btnImage.setFocusPainted(false);
		  ////////////
		  ///area제목
		  panel.add(areaNotice);
		  areaNotice.setBounds(80, 33, 200, 40);
		  areaNotice.setFont(new Font("굴림체", Font.BOLD, 15));		
		  
		  try{
			    Thread.sleep(3000);
			    dispose();
		  }catch(Exception e){}		  
	}	
}
