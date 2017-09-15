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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import connector.WebConnector;
import noticeframe.MessageFrame;
import request.Request;
import util.RegularExpression;

public class JoinFrame extends JFrame implements ActionListener,FocusListener, CaretListener {	
	private boolean isChecked = false;
	private String checkedEmail;
	
	public JoinFrame(JFrame loginFrm) {
		super("Face On Talk join");
		this.loginFrm = loginFrm;		
		this.init();
	}	
	///////////////////
	//GUI
	private final int WIDTH = 370, HEIGHT = 500;
	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();
	private JLabel mainLabel = new JLabel("회 원 가 입");

	private JTextField txtInputName = new JTextField("이     름(NAME)");
	private JLabel nameLabel = new JLabel("ex) 홍길동");
	
	private JTextField txtInputId = new JTextField("이 메 일(ID)");
	private JLabel idLabel = new JLabel("ex) hyungss71@naver.com");	

	private JPasswordField txtInputPassword = new JPasswordField("비 밀 번 호 입 력");
	private JLabel ex_pwLabel = new JLabel("ex) 영문+숫자8~12자");	

	private JPasswordField txtInputRpwd = new JPasswordField();
	private JLabel rpwComparisonLabel = new JLabel("");
	private JLabel repasswordLabel = new JLabel("ex) 비밀번호 재입력 ");

	private JTextField txtInputPhone = new JTextField("핸 드 폰(PHONE)");
	private JLabel phoneLabel = new JLabel("ex) 010-7777-7777");

	private JLabel birthdayLabel = new JLabel("ex) 890202 - 1****** (앞 1자리)");
	private JTextField txtInputFirstBirth = new JTextField("생년월일(BIRTH)");
	private JTextField txtInputSecondBirth = new JTextField();
	private JLabel slashBirthLabel = new JLabel("-");
	private JLabel starBirthLabel = new JLabel("****** (앞 1자리)");

	private JFrame loginFrm;
	private JButton btnIdCheck = makeButton("ID중복체크");
	private JButton btnJoin = makeButton("가입");
	private JButton btnCancel = makeButton("취소");	

	public void init() {
		setSize(WIDTH, HEIGHT);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.setBackground(new Color(255,255,255));
		

		// ===================회원가입
		panel.add(mainLabel);
		
		mainLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		mainLabel.setBounds(135, 21, 116, 31);

		// ==============이름입력
		panel.add(txtInputName);//입력란
		txtInputName.addFocusListener(this);
		txtInputName.setColumns(10);
		txtInputName.setBounds(50, 80, 175, 21);

		panel.add(nameLabel);//라벨
		nameLabel.setBounds(50, 100, 300, 21);
		nameLabel.setForeground(Color.GRAY);
		
		// =======아이디
		panel.add(txtInputId);   //입력
		txtInputId.setBounds(50, 130, 175, 21);
		txtInputId.setForeground(Color.GRAY);
		txtInputId.addFocusListener(this);
		txtInputId.setColumns(30);
		
		
		panel.add(idLabel);		//라벨
		idLabel.setBounds(50, 150, 300, 21);
		idLabel.setForeground(Color.GRAY);
		
		// ========비밀번호
		panel.add(txtInputPassword);
		txtInputPassword.addFocusListener(this);
		txtInputPassword.setBounds(50, 180, 175, 21);
		txtInputPassword.setForeground(Color.GRAY);
		txtInputPassword.setColumns(20);

		panel.add(ex_pwLabel);
		ex_pwLabel.setForeground(Color.gray);
		ex_pwLabel.setBounds(50, 200, 300, 21);

		
		// 재입력 비밀번호구간==
		panel.add(txtInputRpwd); 
		txtInputRpwd.addFocusListener(this);
		txtInputRpwd.setColumns(10);
		txtInputRpwd.addCaretListener(this); //일치불일치 이벤트처리구간
		txtInputRpwd.setBounds(50, 230, 175, 21);

		panel.add(rpwComparisonLabel); //일치 불일치 라벨구간
		rpwComparisonLabel.setForeground(Color.BLUE);
		//rpwComparisonLabel.setBounds(235, 230, 180, 21);
		rpwComparisonLabel.setBounds(235, 230, 200, 21);

		panel.add(repasswordLabel);  //라벨
		repasswordLabel.setBounds(50, 250, 300, 21);
		repasswordLabel.setForeground(Color.gray);
		
		
		// ==============핸드폰
		panel.add(txtInputPhone);
		txtInputPhone.addFocusListener(this);
		txtInputPhone.setColumns(15);
		txtInputPhone.setForeground(Color.GRAY);
		txtInputPhone.setBounds(50, 280, 175, 21);
		

		panel.add(phoneLabel);  //라벨
		phoneLabel.setBounds(50, 300, 300, 21);		
		phoneLabel.setForeground(Color.GRAY);
		
		// ==============주민번호

		panel.add(txtInputFirstBirth);
		txtInputFirstBirth.setBounds(50, 330, 96, 21);
		txtInputFirstBirth.setForeground(Color.GRAY);
		txtInputFirstBirth.addFocusListener(this);
		txtInputFirstBirth.setColumns(10);

	
		panel.add(slashBirthLabel);
		slashBirthLabel.setBounds(148, 330, 20, 21);  //-구간
		
		panel.add(txtInputSecondBirth);
		txtInputSecondBirth.setBounds(156, 330, 15, 21);
		txtInputSecondBirth.addFocusListener(this);
		txtInputSecondBirth.setColumns(1);
		
		
		panel.add(starBirthLabel);
		starBirthLabel.setBounds(172, 330, 180, 21);  //*구간

		panel.add(birthdayLabel);   //라벨
		birthdayLabel.setBounds(50, 350, 300, 21);
		birthdayLabel.setForeground(Color.GRAY);
		
		
		// ========버튼구간=======
		panel.add(btnIdCheck);
		btnIdCheck.setBounds(235, 130, 100, 23);
		
		btnJoin.setBounds(90, 400, 73, 23);
		panel.add(btnJoin);

		btnCancel.setBounds(210, 400, 73, 23);
		panel.add(btnCancel);
		
		txtInputId.addCaretListener(e -> {
			if(isChecked) {
				if(!txtInputId.getText().equals(checkedEmail)){
					btnIdCheck.setEnabled(true);
					isChecked = false;
					checkedEmail = "";
				}
			} 
		});
		
		//버튼에 포커스 주는 구간
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				mainLabel.requestFocusInWindow();
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				loginFrm.setVisible(true);
			}
		});	
		setVisible(true);		
	}
	
	private JButton makeButton(String value) {
		JButton btn = new JButton(value);
		btn.addActionListener(this);
		btn.setBackground(new Color(0,0,0));
		btn.setForeground(Color.WHITE);
		return btn;
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "취소") {
			dispose();
			loginFrm.setVisible(true);
		} else if (e.getActionCommand() == "가입") {
			joinHandler();
		} else if (e.getActionCommand() == "ID중복체크") {
			confirmIdHandler();
		}
	}
	
	private void joinHandler() {
		String name = txtInputName.getText();
		String id = txtInputId.getText();
		String pw = (new String(txtInputPassword.getPassword()));
		String hp = txtInputPhone.getText(); //.replaceAll("-", "");//빈문자열 교체하기
		String birth = txtInputFirstBirth.getText();
		String gender = txtInputSecondBirth.getText();
		
		if(!isChecked) {
			new MessageFrame("아이디 중복체크를 해주세요.",false);			
		}else if (checkEmpty(id,hp,birth,pw,gender,name,gender)) {			
			new MessageFrame("모든 값을 입력해주세요",false);
		} else {
			if (gender.equals("1"))
				gender = "m";
			else if (gender.equals("2"))
				gender = "f";

			if (!RegularExpression.isEmailExpression(id)) {
				new MessageFrame("이메일(ID) 형식을 지켜주세요\n " + "ex) hyungss71@naver.com", false);
			} else if (!RegularExpression.isPhoneExpression(hp)) {
				new MessageFrame("핸드폰번호 형식을 지켜주세요\n" + "ex) 010-5091-7946 ", false);
			} else if (!RegularExpression.isSecondBirthExpression(gender)) {
				new MessageFrame("주민등록번호 뒷자리 형식을 지켜주세요\n" + "ex) 1 or 2", false);
			} else if (!RegularExpression.isFirstBirthExpression(birth)) {
				new MessageFrame("주민등록번호 앞자리 형식을 지켜주세요\n" + "ex) 890202", false);
			} else if (new String(txtInputRpwd.getPassword())
					.equals(new String(txtInputPassword.getPassword())) == false) {
				new MessageFrame("재입력 비밀번호가 틀립니다", false);
			} else if (!RegularExpression.isPasswordExpression(pw)) {
				new MessageFrame("영문+숫자조합 8~12자리 형식을 지켜주세요", false);
			} else {
				Request request = new Request("join");
				request.setParameter("name", name);
				request.setParameter("email", id);
				request.setParameter("password", pw);
				request.setParameter("phone", hp);
				request.setParameter("birth", birth);
				request.setParameter("gender", gender);
				Thread thread = new Thread() {
					@Override
					public void run() {
						Request res = WebConnector.connect(request);
						if (res == null) {
							new MessageFrame("서버와의 연결이 좋지 않습니다.", false);
						} else if (res.getType().equals("join")) {
							Map<String, Boolean> errors = (Map<String, Boolean>) res.getAttribute("errors");
							if (errors.isEmpty()) {
								new MessageFrame("회원가입 성공", true);
								loginFrm.setVisible(true);
								dispose();
							} else {
								new MessageFrame("서버와의 연결이 좋지 않습니다.", false);
							}
						}
					}
				};
				thread.start();
			}
		}
	}
	
	private void confirmIdHandler() {		
		String id = txtInputId.getText();
		if (id.isEmpty()) {
			new MessageFrame("아이디를 입력해주세요.",false);
		} else if (!RegularExpression.isEmailExpression(id)) {
			new MessageFrame("이메일(ID) 형식을 지켜주세요\n " + "ex) hyungss71@naver.com",false);//
		} else {
			Request request = new Request("confirmid");
			request.setParameter("email", id);
			Thread thread = new Thread() {
				@Override
				public void run() {
					Request res = WebConnector.connect(request);
					if (res == null) {
						new MessageFrame("서버와의 연결이 좋지 않습니다.",false);
					} else if (res.getType().equals("confirmid")) {
						Map<String, Boolean> errors = (Map<String, Boolean>) res.getAttribute("errors");
						if (errors.isEmpty()) {
							new MessageFrame("사용가능한 아이디 입니다.",true);
							isChecked = true;
							checkedEmail = id;
							btnIdCheck.setEnabled(false);						
						} else {
							new MessageFrame("이미 존재하는 아이디 입니다.",false);
						}
					}
				}
			};
			thread.start();		
		}
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
		case "이     름(NAME)" :
		case "이 메 일(ID)" :
		case "핸 드 폰(PHONE)" :
		case "생년월일(BIRTH)" :
			return true;
		default :
			return false;
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == txtInputId) {
			if (txtInputId.getText().equals("이 메 일(ID)")) {
				txtInputId.setText("");
				txtInputId.setForeground(Color.BLACK);
			}

		} else if (e.getSource() == txtInputPhone) {
			if (txtInputPhone.getText().equals("핸 드 폰(PHONE)")) {
				txtInputPhone.setText("");
				txtInputPhone.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == txtInputFirstBirth) {
			if (txtInputFirstBirth.getText().equals("생년월일(BIRTH)")) {
				txtInputFirstBirth.setText("");
				txtInputFirstBirth.setForeground(Color.BLACK);
			}
		} else if (e.getSource() == txtInputName) {
			if (txtInputName.getText().equals("이     름(NAME)")) {
				txtInputName.setText("");
				txtInputName.setForeground(Color.BLACK);
			}
		} else if(e.getSource()==txtInputPassword){
			   if (new String(txtInputPassword.getPassword()).equals("비 밀 번 호 입 력")) {
					txtInputPassword.setText("");
					txtInputPassword.setForeground(Color.BLACK);
				}
		}		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (txtInputId.getText().isEmpty()) {
			txtInputId.setForeground(Color.GRAY);
			txtInputId.setText("이 메 일(ID)");
		}

		else if (txtInputPhone.getText().isEmpty()) {
			txtInputPhone.setForeground(Color.GRAY);
			txtInputPhone.setText("핸 드 폰(PHONE)");
		} 
		else if (txtInputFirstBirth.getText().isEmpty()) {
			txtInputFirstBirth.setForeground(Color.GRAY);
			txtInputFirstBirth.setText("생년월일(BIRTH)");
		}
		else if (txtInputName.getText().isEmpty()) {
			txtInputName.setForeground(Color.GRAY);
			txtInputName.setText("이     름(NAME)");
		}
		else if(new String(txtInputPassword.getPassword()).isEmpty()){
			txtInputPassword.setForeground(Color.GRAY);
			txtInputPassword.setText("비 밀 번 호 입 력");
		}

	}

	// 키보드 하나하나 입력될때 마다 이벤트 처리되는 구분 !!
	@Override
	public void caretUpdate(CaretEvent e) {
		String confirmPassword = new String(txtInputRpwd.getPassword());
		if (confirmPassword.isEmpty()) {
			rpwComparisonLabel.setText("");
		} else {
			if (!new String(txtInputPassword.getPassword()).equals(confirmPassword)) {
				rpwComparisonLabel.setText("비밀번호 불일치");
				rpwComparisonLabel.setForeground(Color.RED);
			} else {
				rpwComparisonLabel.setText("비밀번호 일치");
				rpwComparisonLabel.setForeground(Color.BLUE);
			}
		}
	}
}
// ===============================================