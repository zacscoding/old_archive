
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
import java.util.Date;
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
import util.RegularExpression;

public class UserInformationFrame extends JFrame implements ActionListener, FocusListener {
	
	private boolean isCheckedPwd = false;
	private MemberVO admin;	
	public UserInformationFrame(MemberVO admin) {
		super("회원정보관리");
		this.admin = admin;		
		this.init();
		this.action();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok_bt) {
			String btnVal = ok_bt.getActionCommand();
			if (btnVal.equals("확인")) {
				dispose();
			} else if (btnVal.equals("수정완료")) {
				// 수정 완료 시 String에 수정 값 담기				
				String phoneNumber = phoneNumber_tf.getText();
				if(RegularExpression.isSearchPhoneExpression(phoneNumber)) {
					/////////////
					//서버로 정보 변경 요청
					Request request = new Request("modify");
					request.setParameter("id",String.valueOf(admin.getId()));
					request.setParameter("phone",phoneNumber);
					Request response = WebConnector.connect(request);
					Map<String,Boolean> errors = (Map<String,Boolean>)response.getAttribute("errors");
					if(errors.isEmpty()) { //성공 변경
						admin.setPhone(phoneNumber);
						new MessageFrame("성공적으로 변경하였습니다.",true);
						dispose();
					} else {
						new MessageFrame("서버와의 연결이 좋지 않습니다.",false);
					}					
				} else {
					new MessageFrame("핸드폰 양식을 맞춰주세요.",false);
				}				
			}
		} else if (e.getSource() == modify_bt) {
			if(modify_bt.getActionCommand().equals("수정")) {
				String password = password_tf.getText();				
				password_tf.setText(password.replaceAll("[a-zA-Z0-9]", "*"));
				
				if(password.equals(admin.getPassword())) {
					isCheckedPwd = true;
				} else {
					new MessageFrame("비밀번호가 일치하지 않습니다.",false);
				}
				
				if(isCheckedPwd) {
					phoneNumber_tf.setEditable(true);
					ok_bt.setPreferredSize(new Dimension(70, 30));
					password_tf.setEditable(false);
					pwModify_bt.setVisible(false);
					modify_bt.setText("취소");
					ok_bt.setText("수정완료");
				}
			} else if(modify_bt.getActionCommand().equals("취소")){
				dispose();
			}
		} else if (e.getSource() == pwModify_bt){
			String password = password_tf.getText();				
			password_tf.setText(password.replaceAll("[a-zA-Z0-9]", "*"));
			if(password.equals(admin.getPassword())) {
				new PasswordEditFrame(admin);
				this.dispose();
			} else {
				new MessageFrame("비밀번호가 일치하지 않습니다.",false);
			}			
		}
	}
	
	public void focusGained(FocusEvent e) {
		if(e.getSource() == password_tf){
			if(password_tf.getText().equals("수정을 원할 시 비밀번호 입력")){
				password_tf.setText("");
				password_tf.setForeground(Color.BLACK);
			}
		}
	}

	public void focusLost(FocusEvent e) {
		if(password_tf.getText().isEmpty()){
			password_tf.setText("수정을 원할 시 비밀번호 입력");
			password_tf.setForeground(Color.GRAY);
		}
	}
	
	public void action() {
		ok_bt.addActionListener(this);
		modify_bt.addActionListener(this);
		pwModify_bt.addActionListener(this);
		password_tf.addFocusListener(this);
	}

	private JPanel name_p = new JPanel();
	private JPanel password_p = new JPanel();
	private JPanel phoneNumber_p = new JPanel();
	private JPanel email_p = new JPanel();
	private JPanel button_p = new JPanel();

	private JLabel name_lb = new JLabel("    이              름 : ");
	private JLabel phoneNumber_lb = new JLabel("    핸드폰 번호 : ");
	private JLabel email_lb = new JLabel("    메 일    주 소 : ");
	private JLabel password_lb = new JLabel("    비 밀    번 호 : ");
	private JTextField name_tf = new JTextField();
	private JTextField phoneNumber_tf = new JTextField();
	private JTextField email_tf = new JTextField();
	private JTextField password_tf = new JTextField("수정을 원할 시 비밀번호 입력");
	private JTextField password_pf = new JPasswordField(); 

	private JButton ok_bt = new JButton("확인");
	private JButton modify_bt = new JButton("수정");
	private JButton pwModify_bt = new JButton("PW 수정");

	public void init() {
		super.setSize(300, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);

		super.setLocation(xpos, ypos);
		super.setResizable(false);
		super.setVisible(true);
		
		Container con = this.getContentPane();
		con.setLayout(new GridLayout(5, 1));
		con.add(name_p);
		name_p.setLayout(new FlowLayout());
		con.add(password_p);
		password_p.setLayout(new FlowLayout());
		con.add(phoneNumber_p);
		phoneNumber_p.setLayout(new FlowLayout());
		con.add(email_p);
		email_p.setLayout(new FlowLayout());
		con.add(button_p);
		button_p.setLayout(new FlowLayout());
		name_p.setBackground(new Color(255, 255, 255));
		password_p.setBackground(new Color(255, 255, 255));
		phoneNumber_p.setBackground(new Color(255, 255, 255));
		email_p.setBackground(new Color(255, 255, 255));
		button_p.setBackground(new Color(255, 255, 255));
		name_p.add(name_lb);
		name_p.add(name_tf);
		name_tf.setPreferredSize(new Dimension(180, 30));
		password_p.add(password_lb);
		password_p.add(password_tf);
		password_tf.setPreferredSize(new Dimension(180, 30));
		password_tf.setForeground(Color.GRAY);
		password_pf.setPreferredSize(new Dimension(180, 30));
		password_pf.setForeground(Color.GRAY);
		phoneNumber_p.add(phoneNumber_lb);
		phoneNumber_p.add(phoneNumber_tf);
		phoneNumber_tf.setPreferredSize(new Dimension(180, 30));
		email_p.add(email_lb);
		email_p.add(email_tf);
		email_tf.setPreferredSize(new Dimension(180, 30));
		button_p.add(ok_bt);
		button_p.add(modify_bt);
		button_p.add(pwModify_bt);
		name_tf.setEditable(false);
		phoneNumber_tf.setEditable(false);
		email_tf.setEditable(false);
		ok_bt.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		ok_bt.setContentAreaFilled(false);
		ok_bt.setFocusPainted(false);
		ok_bt.setPreferredSize(new Dimension(50, 30));
		modify_bt.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		modify_bt.setContentAreaFilled(false);
		modify_bt.setFocusPainted(false);
		modify_bt.setPreferredSize(new Dimension(50, 30));
		modify_bt.setToolTipText("수정을 원할 시 비밀번호 입력");
		pwModify_bt.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		pwModify_bt.setContentAreaFilled(false);
		pwModify_bt.setFocusPainted(false);
		pwModify_bt.setPreferredSize(new Dimension(70, 30));
		pwModify_bt.setToolTipText("수정을 원할 시 비밀번호 입력");
		
		name_tf.setText(admin.getName());
		phoneNumber_tf.setText(changePhoneExpression(admin.getPhone()));
		email_tf.setText(admin.getEmail());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private String changePhoneExpression(String phone) {
		String val = phone.substring(0, 3)+"-";
		if(phone.length()==10) {
			val += phone.substring(3,6)+"-"+phone.substring(6);
		} else {
			val += phone.substring(3,7)+"-"+phone.substring(7);
		}
		return val;
	}	
}
