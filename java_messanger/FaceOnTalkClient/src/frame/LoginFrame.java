package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClientHandler;
import util.ButtonCursorHandler;



public class LoginFrame extends JFrame implements ActionListener,ItemListener,FocusListener {
   //final variables
   private final int WIDTH = 390,HEIGHT = 480;
   private final String[] emailValue = { "naver.com", "nate.com", "google.com", "daum.net", "직접입력" };   
   private JButton mainLogo = new JButton(new ImageIcon("image/logo.png"));
   //GUI variables
   private JPanel contentPane = new JPanel();
   private JPanel panel = new JPanel();
//   private JLabel emailLabel = new JLabel("이 메 일 : ");
   private JTextField txtInputId = new JTextField("이 메 일(ID)");
   private JTextField txtInputEmailAddr = new JTextField(emailValue[0]);
   
   private JLabel atLabel = new JLabel("@"); // @ 표기
//   private JLabel passwordLabel = new JLabel("비밀번호 : ");
   private JPasswordField txtInputPwd = new JPasswordField("비 밀 번 호 입 력");
  
   private JComboBox<String> emailComboBox = new JComboBox<>(emailValue);   
   private JButton btnLogin = makeButton("로그인");
   private JButton btnJoin = makeButton("회 원 가 입");
   private JButton btnPwFind = makeButton("아이디패스워드찾기");
   
   public LoginFrame(String title) {
      super(title);
      init();
   }

   private void init() {
      /////////////////////
      ////// Frame Size
      setSize(WIDTH,HEIGHT);
      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
      int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
      setLocation(xpos, ypos);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      Container con = this.getContentPane();
      panel.setBounds(0, 0, WIDTH, HEIGHT);
      //
 
      contentPane.add(panel);
      panel.setLayout(null);      
      
      panel.setBackground(new Color(255,255,255));

      // ===============IDtext구간,IDtext입력구간==============      
      //email label
//      panel.add(emailLabel);      
//      emailLabel.setBounds(30, 258, 65, 21);   
      
      panel.add(mainLogo);
      mainLogo.setBounds(120, 40, 150, 150);
      mainLogo.setBorderPainted(false);
      mainLogo.setContentAreaFilled(false);
      mainLogo.setFocusPainted(false);
      //id text field
      panel.add(txtInputId);
      txtInputId.setBounds(20, 258, 105, 21);
      txtInputId.setColumns(16);    
      txtInputId.setForeground(Color.GRAY);
	  txtInputId.addFocusListener(this);
      txtInputId.setColumns(30);
      
      //at(@) label
      panel.add(atLabel);      
      atLabel.setBounds(130, 258, 20, 21);
      //email textfield      
      panel.add(txtInputEmailAddr);
      txtInputEmailAddr.setBounds(150, 258, 100, 21);
      txtInputEmailAddr.setEnabled(false); 
      txtInputEmailAddr.setFont(new Font("",Font.BOLD,13));
      
      //emailComboBox
      emailComboBox.setBounds(260, 258, 100, 21);
      emailComboBox.addItemListener(this);
      //emailComboBox.setBackground(new Color(0,0,0));
      emailComboBox.setBackground(Color.blue);
      emailComboBox.setForeground(Color.white);
      panel.add(emailComboBox);
      
      // ====================================================

      // ===============pwtext구간,pwtext입력구간==============
//      passwordLabel.setBounds(30, 292, 65, 21);
//      panel.add(passwordLabel);
      txtInputPwd.setBounds(20, 292, 232, 21);
      panel.add(txtInputPwd);
      txtInputPwd.setForeground(Color.GRAY);
	  txtInputPwd.addFocusListener(this);
      txtInputPwd.addActionListener( (e) -> {
         loginHandler();
      });

      // ==============로그인버튼구간====================
      btnLogin.setBounds(260, 292, 100, 21);
      panel.add(btnLogin);
      //btnLogin.setBackground(new Color(0,0,0));      
      btnLogin.setBackground(Color.BLUE);
      
      btnLogin.setForeground(Color.WHITE);

      // ================회원가입버튼구간======================
      btnJoin.setBounds(50, 350, 100, 23);
      panel.add(btnJoin);btnJoin.setBorderPainted(false); btnJoin.setContentAreaFilled(false); btnJoin.setFocusPainted(false); 

      // ================버튼 커서 변경 이벤트 등록==================
      btnJoin.addMouseListener(ButtonCursorHandler.getInstance());
      btnPwFind.addMouseListener(ButtonCursorHandler.getInstance());
            
      // ================idpw찾기버튼구간==================
      btnPwFind.setBounds(180, 350, 160, 23);
      panel.add(btnPwFind);btnPwFind.setBorderPainted(false); btnPwFind.setContentAreaFilled(false); btnPwFind.setFocusPainted(false); 
      
      // 포커스 주는 구간
      addWindowFocusListener(new WindowAdapter() {
          public void windowGainedFocus(WindowEvent e) {
        	  txtInputEmailAddr.requestFocusInWindow();
          }
      });
      //////////////
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
   }
      
   private JButton makeButton(String value) {
      JButton btn = new JButton(value);
      btn.addActionListener(this);
      return btn;
   }

   // ==============이벤트 처리구간============
   @Override
   public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "회 원 가 입") {
			joinHandler();							
		} else if (e.getActionCommand() == "아이디패스워드찾기") {
			 new FindFrame(this).setVisible(true);
			 setVisible(false);
		} else if (e.getActionCommand() == "로그인") {
			loginHandler();
		}
	}
   
   public void joinHandler() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				new JoinFrame(LoginFrame.this).setVisible(true); //JoinFrame 활성화			
				LoginFrame.this.setVisible(false); //로그인창 비활성화
				txtInputId.setText("이 메 일(ID)");
				txtInputPwd.setText("비 밀 번 호 입 력");	
			}
		};
		thread.start();
   }
   
   public void loginHandler() {
		Thread thread = new Thread(){
			@Override
			public void run() {
				String email = txtInputId.getText()+"@"+txtInputEmailAddr.getText();
				String password = new String(txtInputPwd.getPassword());				
				ClientHandler.getInstance().login(email,password,LoginFrame.this);
			}
		};
		thread.start();
	}

   // ==========text Item
   @Override
   public void itemStateChanged(ItemEvent e) {		
		clearField(txtInputEmailAddr);// 초기화		
		if (!((String)emailComboBox.getSelectedItem() == "직접입력")) {
			txtInputEmailAddr.setEnabled(false); // 텍스트 필드의 enable 속성을 false로 하여 직접 입력하지 못하게 한다.			
			txtInputEmailAddr.setText((String)emailComboBox.getSelectedItem());
			// 콤보박스에 선택된 아이템에 문자열을 텍스트 필드에 text로 설정한다.
		} else {
			// 만약 직접 입력이라면 직접 텍스트 필드에 데이터를 수정하게 끔 enable 속성을 true로 활성화
			txtInputEmailAddr.setEnabled(true);			
//			emailComboBox.setToolTipText("123");		
		}
	}	
	
   private void clearField(JTextField field){
		field.setText("");
   }

   @Override
   public void focusGained(FocusEvent e) {
	  //String confirmPassword = new String(txtInputPwd.getPassword());
	   if (e.getSource() == txtInputId) {
			if (txtInputId.getText().equals("이 메 일(ID)")) {
				txtInputId.setText("");
				txtInputId.setForeground(Color.BLACK);
			}
	   }else if(e.getSource()==txtInputPwd){
		   if (new String(txtInputPwd.getPassword()).equals("비 밀 번 호 입 력")) {
				txtInputPwd.setText("");
				txtInputPwd.setForeground(Color.BLACK);
			}
	   }
   }
   @Override
   public void focusLost(FocusEvent e) {
   		if (txtInputId.getText().isEmpty()) {
			txtInputId.setForeground(Color.GRAY);
			txtInputId.setText("이 메 일(ID)");
		}else if(new String(txtInputPwd.getPassword()).isEmpty()){
			txtInputPwd.setForeground(Color.GRAY);			
			txtInputPwd.setText("비 밀 번 호 입 력");
		}   		
   	}
}
