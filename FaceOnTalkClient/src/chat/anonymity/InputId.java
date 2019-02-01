package chat.anonymity;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputId extends JFrame implements ActionListener, KeyListener{
	JTextField id;	
	JButton ok;
	JComboBox Gender;
	public InputId(){
		Gender = new JComboBox();
		Gender.addItem("남자");
		Gender.addItem("여자");

		JLabel title = new JLabel("아이디를 입력하세요.");
		JLabel caution = new JLabel("※2자 이상, 8자 이내로 입력");
		id = new JTextField(10);
		ok = new JButton("OK");
		id.addKeyListener(this);
		ok.addActionListener(this);
		
		Panel centerPanel = new Panel();
		centerPanel.add(Gender);	centerPanel.add(id);	centerPanel.add(ok);
		Panel southPanel = new Panel();
		southPanel.add(caution);
		
		/*===================
		 * 전체 레이아웃
		 *=====================*/
		add(title, "North");
		add(centerPanel, "Center");
		add(southPanel, "South");
		setBounds(500, 300, 250, 120);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();				
			}
		});
		setVisible(true);		
	}	
	
	//////////////////////////////////
	// keyboard Enter -> ok Button 실행
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == '\n') {
			ok.doClick();
		}
	}
	//////////////////////
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String gender;
		/*if(id.getText().length() > 8 || id.getText().length() <2){
			JOptionPane.showMessageDialog(null,"2자 이상, 8자 이내로 입력하세요.");
			this.dispose();
			InputId getid = new InputId();
			getid.setVisible(true);
		}*/
		if(id.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
			this.dispose();
			InputId getid = new InputId();
			getid.setVisible(true);
		}else if(id.getText().length() > 8){
			JOptionPane.showMessageDialog(null,"8자를 초과 하였습니다.");
			this.dispose();
			InputId getid = new InputId();
			getid.setVisible(true);
		}else if(id.getText().length() < 2){
			JOptionPane.showMessageDialog(null, "2자 이상 입력하세요.");
			this.dispose();
			InputId getid = new InputId();
			getid.setVisible(true);
		}else{
			if(e.getActionCommand().equals("OK")){
				if(Gender.getSelectedIndex() == 0){
					gender = "♂ ";
				}else{
					gender = "♀ ";
				}
				try {
					this.dispose();		//이 창은 없어져야 한다.
					anonyChatFrame client = new anonyChatFrame(gender+id.getText());
				} catch (IOException e1) {}
			}
		}
	}
}