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

public class MessageFrame extends JFrame implements ActionListener {
	private static ImageIcon errorImg = new ImageIcon("image/errorimage.png");
	private static ImageIcon successImg = new ImageIcon("image/successimage.png");
	private static Font errorFont = new Font("굴림체", Font.BOLD, 12);
	private static Font sucessFont = new Font("굴림체", Font.BOLD, 12);
	
	private final int WIDTH = 300 , HEIGHT = 160;
	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();	
	private JButton btnImage = new JButton();
	private JButton btnOk = new JButton("확인");
	private JTextArea areaNotice = new JTextArea();
	
	public MessageFrame(String title,boolean isSuccess) {  
		Thread thread = new Thread() {
			@Override
			public void run() {
				areaNotice = new JTextArea(title);
				if(isSuccess) {
					btnImage = new JButton(successImg);
					areaNotice.setFont(sucessFont);
				}
				else {
					btnImage = new JButton(errorImg);
					areaNotice.setFont(errorFont);
				}
				initFrame();			
			}
		};
		thread.start();		
	}
	public void initFrame(){
		  setSize(WIDTH,HEIGHT);
	      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	      int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
	      int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
	      setLocation(xpos, ypos);
	      setResizable(false);
	      setVisible(true);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
		  btnImage.setBounds(20, 20, 45, 45);
		  btnImage.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
		  btnImage.setContentAreaFilled(false);
		  btnImage.setFocusPainted(false);
		  ////////////
		  ///area제목
		  panel.add(areaNotice);
		  areaNotice.setBounds(80, 25, 200, 30);
//		  areaNotice.setFont(new Font("굴림체", Font.BOLD, 12));
		  ////////////
		  ///Ok버튼
		  panel.add(btnOk);
		  btnOk.setBounds(110, 75, 60, 28);
		  btnOk.addActionListener(this);
		  btnOk.setBackground(new Color(255, 255, 255));
		  btnOk.setForeground(Color.BLACK);
		  
		  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "확인"){
			dispose();
		}		
	}
}
