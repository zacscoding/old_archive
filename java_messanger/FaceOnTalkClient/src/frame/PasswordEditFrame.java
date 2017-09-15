package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import connector.WebConnector;
import dto.MemberVO;
import noticeframe.MessageFrame;
import request.Request;

public class PasswordEditFrame extends JFrame implements ActionListener, FocusListener {
	MemberVO admin;
	public PasswordEditFrame(MemberVO admin) {
		this.admin = admin;
		this.init();
		this.action();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == modifyBtn) {
			changePasswordHandler();
		}else if (e.getSource() == cancelBtn) {
			dispose();
		}
	}
	
	public void changePasswordHandler() {
		String password = passwordModify_tf.getText();
		String confirmPassword = passwordIdentify_tf.getText();
		if(password.equals(confirmPassword)) {
					/////////////
					//서버로 정보 변경 요청
					Request request = new Request("modify");
					request.setParameter("id",String.valueOf(admin.getId()));
					request.setParameter("password",password);
					Request response = WebConnector.connect(request);
					Map<String,Boolean> errors = (Map<String,Boolean>)response.getAttribute("errors");
					if(errors.isEmpty()) { //성공 변경
						admin.changePassword(password);
						new MessageFrame("성공적으로 변경하였습니다.",true);
						dispose();
					} else {
						new MessageFrame("서버와의 연결이 좋지 않습니다.",false);
					}					
		}
	}
	
	public void focusGained(FocusEvent e) {
		if(e.getSource() == passwordModify_tf){
			if(passwordModify_tf.getText().equals("수정할 비밀번호 입력")){
				passwordModify_tf.setText("");
				passwordModify_tf.setForeground(Color.BLACK);
			}
		}else if(e.getSource() == passwordIdentify_tf){
			if(passwordIdentify_tf.getText().equals("수정할 비밀번호 재입력")){
				passwordIdentify_tf.setText("");
				passwordIdentify_tf.setForeground(Color.BLACK);
			}
		}
	}

	public void focusLost(FocusEvent e) {
		if(passwordModify_tf.getText().isEmpty()){
			passwordModify_tf.setText("수정할 비밀번호 입력");
			passwordModify_tf.setForeground(Color.GRAY);
		}else if(passwordIdentify_tf.getText().isEmpty()){
			passwordIdentify_tf.setText("수정할 비밀번호 재입력");
			passwordIdentify_tf.setForeground(Color.GRAY);
		}
	}
	
	
	private JPanel modifyPanel = new JPanel();
	private JPanel identifyPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	private JLabel passwordModify = new JLabel("    비밀  번 호  수정 : ");
	private JLabel passwordIdentify = new JLabel("    비밀  번호  확인 : ");
	private JTextField passwordModify_tf = new JPasswordField("수정할 비밀번호 입력");
	private JTextField passwordIdentify_tf = new JPasswordField("수정할 비밀번호 재입력");
	private JButton modifyBtn = new JButton("수정");
	private JButton cancelBtn = new JButton("취소");
	
	
	public void action(){
		modifyBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		passwordModify_tf.addFocusListener(this);
		passwordIdentify_tf.addFocusListener(this);
	}
	
	public void init() {
		super.setSize(350, 200);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);

		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
		
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(3,1));
		con.add(modifyPanel);
		con.add(identifyPanel);
		con.add(btnPanel);
		modifyPanel.setLayout(new FlowLayout());
		identifyPanel.setLayout(new FlowLayout());
		btnPanel.setLayout(new FlowLayout());
		modifyPanel.setBackground(new Color(255, 255, 255));
		identifyPanel.setBackground(new Color(255, 255, 255));
		btnPanel.setBackground(new Color(255, 255, 255));
		modifyPanel.add(passwordModify);
		modifyPanel.add(passwordModify_tf);
		passwordModify_tf.setPreferredSize(new Dimension(180, 30));
		passwordModify_tf.setForeground(Color.GRAY);
		identifyPanel.add(passwordIdentify);
		identifyPanel.add(passwordIdentify_tf);
		passwordIdentify_tf.setPreferredSize(new Dimension(180, 30));
		passwordIdentify_tf.setForeground(Color.GRAY);
		btnPanel.add(modifyBtn);
		btnPanel.add(cancelBtn);
		modifyBtn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		modifyBtn.setContentAreaFilled(false);
		modifyBtn.setFocusPainted(false);
		modifyBtn.setPreferredSize(new Dimension(50, 30));
		cancelBtn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setFocusPainted(false);
		cancelBtn.setPreferredSize(new Dimension(50, 30));
		
			
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				passwordModify.requestFocusInWindow();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

	}
	
}
