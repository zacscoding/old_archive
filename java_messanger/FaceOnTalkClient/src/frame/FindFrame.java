package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import connector.WebConnector;
import dto.MemberVO;
import noticeframe.MessageFrame;
import request.Request;
import util.RegularExpression;

public class FindFrame extends JFrame implements ActionListener,FocusListener {
	private final int WIDTH = 650, HEIGHT=350;
	private JPanel contentPane = new JPanel();
	private JPanel idPanel = new JPanel();
	private JPanel pwPanel = new JPanel();	
	private JLabel idMainLabel = new JLabel("아이디찾기");
	
	private JLabel birthdayLabel = new JLabel("ex) 890202 - 1****** (앞 1자리)");
	private JLabel slashBirthLabel = new JLabel("-");
	private JLabel starBirthLabel = new JLabel("****** (앞 1자리)");
	private JTextField idTxtInputFirstBirth = new JTextField("생년월일(BIRTH)");
	private JTextField txtInputSecondBirth = new JTextField();
	private JLabel idPhoneLabel = new JLabel("ex) 010-7777-7777");
	private JTextField idTxtInputPhone = new JTextField("핸 드 폰(PHONE)");

	
	private JLabel passwordMainLabel = new JLabel("비밀번호 찾기");
	private JLabel passwordIdLabel = new JLabel("ex) hyungss71@naver.com");
	private JTextField passwordTxtInputId = new JTextField("이 메 일(ID)");
	private JLabel passwordPhoneLabel = new JLabel("ex) 010-7777-7777");
	private JTextField passwordTxtInputPhone = new JTextField("핸 드 폰(PHONE)");

	
	private JFrame loginFrm;
	private JButton btnIdFind = makeButton("ID찾기");
	private JButton btnIdCancel = makeButton("취소");
	private JButton btnPwFind = makeButton("PW찾기");
	private JButton btnPwCancel = makeButton("취소");
	
	public FindFrame(JFrame loginFrm){
		super("Face On Talk Find");
		this.loginFrm = loginFrm;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				loginFrm.setVisible(true);
			}
		});
		this.init();				
	}
		
	public void init(){
		setSize(WIDTH, HEIGHT);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		idPanel.setBounds(0,0,325,HEIGHT);
		contentPane.add(idPanel);
		idPanel.setLayout(null);
		 idPanel.setBackground(new Color(255,255,255));
		
		pwPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pwPanel.setBounds(325,0,WIDTH,HEIGHT);
		contentPane.add(pwPanel);
		pwPanel.setLayout(null);
		 pwPanel.setBackground(new Color(255,255,255));
	
		
//		아뒤찾기 ==
		 //메인라벨
		 idPanel.add(idMainLabel);
		 idMainLabel.setFont(new Font("굴림체",Font.BOLD,18));
		 idMainLabel.setBounds(110, 21, 115, 31);
		 
		//생년월일라벨
		idPanel.add(birthdayLabel);
		birthdayLabel.setBounds(50, 100, 200, 21);
		birthdayLabel.setForeground(Color.GRAY);
		//생년월일 입력칸
		idPanel.add(idTxtInputFirstBirth);
		idTxtInputFirstBirth.setBounds(50, 80, 96, 21);
		idTxtInputFirstBirth.setForeground(Color.GRAY);
		idTxtInputFirstBirth.addFocusListener(this);		
		//ㅡ
		slashBirthLabel.setBounds(150,80,15,21);
		idPanel.add(slashBirthLabel);
		//두번째생년월일입력
		txtInputSecondBirth.setBounds(160,80, 15, 21);
		idPanel.add(txtInputSecondBirth);
		txtInputSecondBirth.setColumns(2);
		//*
		starBirthLabel.setBounds(170,80,100,21);
		idPanel.add(starBirthLabel);
		
		//핸드폰라벨
		idPanel.add(idPhoneLabel);
		idPhoneLabel.setBounds(50,170, 200, 21);
		idPhoneLabel.setForeground(Color.GRAY);
		
		//핸드폰txt
		idPanel.add(idTxtInputPhone);
		idTxtInputPhone.setColumns(20);
		idTxtInputPhone.setForeground(Color.GRAY);
		idTxtInputPhone.setBounds(50, 150, 180, 21);
		idTxtInputPhone.addFocusListener(this);
		
		
//		아뒤찾기 버튼 ==
		btnIdFind.setBounds(85,230,80,21);
		idPanel.add(btnIdFind);//찾기
				
		btnIdCancel.setBounds(175,230,80,21);
		idPanel.add(btnIdCancel);//취소
		
//		================================
		
//		패스워드 찾기========
		//pw메인라벨
		pwPanel.add(passwordMainLabel);
		passwordMainLabel.setFont(new Font("굴림체",Font.BOLD,18));
		passwordMainLabel.setBounds(105, 21, 130, 31);
		
		//pw id라벨
		pwPanel.add(passwordIdLabel);
		passwordIdLabel.setBounds(50, 100, 200, 21);
		passwordIdLabel.setForeground(Color.GRAY);
		
		//pw Id입력 txt구간
		pwPanel.add(passwordTxtInputId);
		passwordTxtInputId.setBounds(50, 80, 180, 21);
		passwordTxtInputId.setForeground(Color.GRAY);
		passwordTxtInputId.addFocusListener(this);
		passwordTxtInputId.setColumns(30);
		
		//pw핸드폰라벨
		pwPanel.add(passwordPhoneLabel);
		passwordPhoneLabel.setBounds(50,170, 200, 21);
		passwordPhoneLabel.setForeground(Color.GRAY);
		
		//pw핸드폰txt		
		pwPanel.add(passwordTxtInputPhone);
		passwordTxtInputPhone.setColumns(15);
		passwordTxtInputPhone.setForeground(Color.GRAY);
		passwordTxtInputPhone.setBounds(50, 150, 180, 21);
		passwordTxtInputPhone.addFocusListener(this);
		
//		비밀번호찾기버튼		
		btnPwFind.setBounds(85,230,80,21);
		pwPanel.add(btnPwFind);
				
		btnPwCancel.setBounds(175,230,80,21);
		pwPanel.add(btnPwCancel);
		
//		====================GUI 끝
		//버튼에 포커스 주는 구간
	      addWindowFocusListener(new WindowAdapter() {
	          public void windowGainedFocus(WindowEvent e) {
	        	  idMainLabel.requestFocusInWindow();
	          }
	      });
	      //////////////
	}
	private JButton makeButton(String value) {
		JButton btn = new JButton(value);
		btn.addActionListener(this);
		btn.setBackground(new Color(0,0,0));
		btn.setForeground(Color.WHITE);
		return btn;
	}
	
	private boolean checkEmpty(String ... val){		
		for(int i=0;i<val.length;i++) {
			if(val[i]==null || val[i].isEmpty() || checkDefaultMessage(val[i]))
				return true;
		}
		return false;
	}
	
	private boolean checkDefaultMessage(String value) {		
		switch(value) {
		case "생년월일(BIRTH)" :
		case "핸 드 폰(PHONE)" :	
		case "이 메 일(ID)" :		
			return true;
		default :
			return false;
		}		
	}
	
	private void findIdHandler(Request request) {
		String birth = idTxtInputFirstBirth.getText();
		String gender = txtInputSecondBirth.getText();
		String phone = idTxtInputPhone.getText();
		if(checkEmpty(birth,gender,phone)) {
			new MessageFrame("모든 값을 입력해주세요.",false);			
		}
		
		if(gender.equals("1"))
			gender = "m";
		else if(gender.equals("2"))
			gender = "f";
		
		if(!RegularExpression.isPhoneExpression(phone)) {
			new MessageFrame("핸드폰번호 형식을 지켜주세요\n" + "ex) 010-5091-7946 ",false);
		} else if (!RegularExpression.isSecondBirthExpression(gender)) {
			new MessageFrame("주민등록번호 뒷자리 형식을 지켜주세요\n" + "ex) 1 or 2",false);
		} else if (!RegularExpression.isFirstBirthExpression(birth)) {
			new MessageFrame("주민등록번호 앞자리 형식을 지켜주세요\n" + "ex) 890202",false);
		} else {
			request.setParameter("searchtype","email");			
			request.setParameter("birth",birth);
			request.setParameter("phone",phone.replaceAll("-",""));
			request.setParameter("gender",gender);
			
			Thread thread = new Thread() {
				@Override
				public void run() {
					Request response = WebConnector.connect(request);					
					Map<String,Boolean> errors = (Map<String,Boolean>)response.getAttribute("errors");
					if(errors.isEmpty()) { //찾기 성공
						new MessageFrame(response.getParameter("result"),true);					
					} else { //찾기 실패
						if(errors.get("SQLException") == Boolean.TRUE) {
							new MessageFrame("서버와의 연결이 좋지 않습니다.\n 잠시 후 연결해주세요.",false);
						}
						else {
							new MessageFrame("존재하지 않는 회원입니다.",false);
						}
					}
				}
			};
			thread.start();
		}		
	}
	
	private void findPwdHandler(Request request) {				
		String email = passwordTxtInputId.getText();
		String phone = passwordTxtInputPhone.getText();
		
		if(checkEmpty(email,phone)) {
			new MessageFrame("모든 값을 입력해주세요.",false);			
		} else if(!RegularExpression.isEmailExpression(email)) {
			new MessageFrame("이메일 형식을 지켜주세요\n" + "ex) hyungss71@naver.com",false);
		} else if(!RegularExpression.isPhoneExpression(phone)) {
			new MessageFrame("핸드폰번호 형식을 지켜주세요\n" + "ex)010-7777-7777",false);
		} else {
			request.setParameter("searchtype","password");
			request.setParameter("email", email);
			request.setParameter("phone",phone.replaceAll("-",""));
			
			Thread thread = new Thread() {
				@Override
				public void run() {
					Request response = WebConnector.connect(request);					
					Map<String,Boolean> errors = (Map<String,Boolean>)response.getAttribute("errors");
					if(errors.isEmpty()) { //찾기 성공
						//System.out.println((MemberVO)response.getAttribute("user"));
						new PasswordEditFrame((MemberVO)response.getAttribute("user"));
					} else { //찾기 실패
						if(errors.get("SQLException")==Boolean.TRUE) {
							new MessageFrame("서버와의 연결이 좋지 않습니다.\n 잠시 후 연결해주세요.",false);
						}
						else {
							new MessageFrame("존재하지 않는 회원입니다.",false);
						}
					}
				}
			};
			thread.start();
		}
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="취소"){
			dispose();
			loginFrm.setVisible(true);
		}  else {		
				Request request = new Request("finduser");
				Request response = null;
				if(e.getActionCommand() == "ID찾기") {
					findIdHandler(request);
				} else if(e.getActionCommand() == "PW찾기") {
					findPwdHandler(request);
				}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == passwordTxtInputId) {  //pw
			if (passwordTxtInputId.getText().equals("이 메 일(ID)")) {
				passwordTxtInputId.setText("");
				passwordTxtInputId.setForeground(Color.BLACK);
			}

		} else if (e.getSource() == passwordTxtInputPhone) {  //pw
			if (passwordTxtInputPhone.getText().equals("핸 드 폰(PHONE)")) {
				passwordTxtInputPhone.setText("");
				passwordTxtInputPhone.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == idTxtInputFirstBirth) {  //id
			if (idTxtInputFirstBirth.getText().equals("생년월일(BIRTH)")) {
				idTxtInputFirstBirth.setText("");
				idTxtInputFirstBirth.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == idTxtInputPhone) {  //id
			if (idTxtInputPhone.getText().equals("핸 드 폰(PHONE)")) {
				idTxtInputPhone.setText("");
				idTxtInputPhone.setForeground(Color.BLACK);
			}
		}
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (passwordTxtInputId.getText().isEmpty()) {
			passwordTxtInputId.setForeground(Color.GRAY);
			passwordTxtInputId.setText("이 메 일(ID)");
		}

		else if (passwordTxtInputPhone.getText().isEmpty()) {
			passwordTxtInputPhone.setForeground(Color.GRAY);
			passwordTxtInputPhone.setText("핸 드 폰(PHONE)");
		} 
		else if (idTxtInputFirstBirth.getText().isEmpty()) {
			idTxtInputFirstBirth.setForeground(Color.GRAY);
			idTxtInputFirstBirth.setText("생년월일(BIRTH)");
		}
		else if (idTxtInputPhone.getText().isEmpty()) {
			idTxtInputPhone.setForeground(Color.GRAY);
			idTxtInputPhone.setText("핸 드 폰(PHONE)");
		} 
		
	}

}
