package chat.anonymity;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


class Wait_Room extends JFrame implements KeyListener, ActionListener{
	static JTextArea showText = new JTextArea();
	static JLabel inputid;
	static String id;
	
	JTextField inputText = new JTextField();
	List IDlist = new List();
	List roomList = new List();
	JButton waitroomSend = new JButton("전송"); 
//	JButton make = new JButton("방 만들기");
//	JButton enter = new JButton("Enter");
//	JButton exit = new JButton("EXIT");
	
	JButton make = new JButton(new ImageIcon("image/anonymity/creat_1.png"));
	JButton enter = new JButton(new ImageIcon("image/anonymity/enter_1.png"));
	JButton exit = new JButton(new ImageIcon("image/anonymity/exit_1.png"));
	
	JPopupMenu waitpopup;  
	JMenuItem sendmemo, mantoman, transferFile;
	String roomtitle;
	JScrollPane waitJsp;
	JScrollPane userListJsp;
	JScrollPane roomListJsp;
	JScrollBar waitJsb;
	JScrollBar userListJsb;
	JScrollBar roomListJsb;
	
	//방목록 폰트
	Font font = new Font("TimesRoman", Font.BOLD, 20); 

	Wait_Room(String title){
		super(title);
		roomtitle = title;
	}
	
	//대기실 클라이언트
	public void showFrame(String name){

	/*===============
	 * 대기실 채팅 입력창
	 *================*/
		Wait_Room.id = name;
		inputid = new JLabel(" 사용자ID : "+id);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		inputText.addKeyListener(this);

		//대기실 채팅창에 스크롤바 추가.
		waitJsp = new JScrollPane(showText);
		waitJsp.setWheelScrollingEnabled(true);
		waitJsb = waitJsp.getVerticalScrollBar();
		showText.setEditable(false);
		showText.setLineWrap(true);
		
	/*==================
	 *  roomList
	 *====================*/
		//방 목록 스크롤바 추가.
		roomListJsp = new JScrollPane(roomList);
		/////////
		//test code
		roomList.setFont(font);
		roomList.setFont(new Font("Serif", Font.BOLD, 14));
	
	/*
		//roomList.setBackground(new Color());
		roomList.setBackground(Color.MAGENTA);
	*/	

		////////
		
		
		roomListJsp.setPreferredSize(new Dimension(340, 330)); //roomlist를 panel에 담고, list의 사이즈를 설정하기 위해 panel에 옵션을 줬음
		roomListJsp.setWheelScrollingEnabled(true);
		roomListJsb = roomListJsp.createVerticalScrollBar();
		
		JPanel left = new JPanel();
		left.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "대화방 목록"));
		left.setBackground(new Color(255, 255, 255));
		
		left.add(roomListJsp);
		
	/*==================
	 *  userList
	 *====================*/
		//접속자 스크롤바 추가.
		userListJsp = new JScrollPane(IDlist);
		userListJsp.setPreferredSize(new Dimension(220, 150));	//userlist를 panel에 담고, list의 사이즈를 설정하기 위해 panel에 옵션을 줬음
		userListJsp.setWheelScrollingEnabled(true);
		userListJsb = userListJsp.createVerticalScrollBar();
		
		JPanel right_up = new JPanel();
		right_up.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "접속자"));
		right_up.setBackground(new Color(255, 255, 255));
		right_up.add(userListJsp);
		
		/*
		 *=========================
		 * test code
		 * ==========================
		 * //오른쪽 버튼 클릭했을때
		IDlist.addMouseListener(
				new MouseAdapter(){
					@Override
					public void mouseClicked(MouseEvent e){
						//익명채팅에서는 Jpopupmenu 비활성화시킴
//						if(e.getButton() == 3){
//							waitpopup.add(sendmemo);
//							waitpopup.add(mantoman);
//							waitpopup.add(transferFile);
//							waitpopup.show(e.getComponent(), e.getX(), e.getY());
//						}
					}
				});*/
		
		JPanel right_down_south = new JPanel();
		right_down_south.setLayout(new BorderLayout());
		right_down_south.add("Center", inputText);
		right_down_south.add("East", waitroomSend);
		
		JPanel right_down = new JPanel(); 
		right_down.setLayout(new BorderLayout());
		right_down.add(inputid, "North");
		right_down.add(waitJsp, "Center");
		right_down.add(right_down_south, "South");
		
		right_down.setBorder(loweredetched);
		//(채팅창)비활성화
		showText.setEditable(false);
		
		JPanel right = new JPanel();
		right.setBorder(loweredetched);
		right.setLayout(new GridLayout(2,1));
		right.add(right_up);	
		right.add(right_down);
		right.add(left);
				
		enter.setBorderPainted(false);
		enter.setContentAreaFilled(false);
		enter.setFocusPainted(false);
		enter.setToolTipText("Enter");
		
		make.setBorderPainted(false);
		make.setContentAreaFilled(false);
		make.setFocusPainted(false);
		make.setToolTipText("Create");
		
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setToolTipText("Exit");
		///////////
		
		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		south.setBackground(new Color(255, 255, 255));
		south.add(make);	south.add(enter);	south.add(exit);
		enter.addActionListener(this);
		make.addActionListener(this);
		exit.addActionListener(this);
		waitroomSend.addActionListener(this);
		
		/*==================
		 * 		전체 레이아웃 설정
		 *=====================*/
		south.setBorder(loweredetched);
		setBounds(100, 100, 650, 450);	//대기실창 크기
		setLayout(new BorderLayout());
		//add(left, "Center");
		//add(right, "East");
		add(left,"Center");
		add(right,"East");
		add(south, "South");
		setResizable(false);
		setVisible(true);

		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				String consoleData = "EXIT";
				Send_Message.chattingStart(consoleData);			//Exit 보냄
			}
		});
	}	
	
	//키 이벤트
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == '\n'){
			waitJsp.getVerticalScrollBar().setValue(waitJsp.getVerticalScrollBar().getMaximum());//TextArea 스크롤 마지막으로 업데이트
			waitJsb.setValue(waitJsb.getMaximum());
			String consoleData = "Main"+"/"+inputText.getText();		//입력한걸 저장
			inputText.setText("");								//저장후 지워야 함.
			Send_Message.chattingStart(consoleData);			//채팅 시작
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	//버튼에 대한 이벤트
	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==make){
			dispose();	dispose();

			final JFrame makeroom = new JFrame();
			final JTextField roomname;	//방이름
			Button ok;		//확인
			JLabel title = new JLabel("  방이름을 입력하세요");
			roomname = new JTextField(10);
			ok = new Button("OK");
			ok.addActionListener(this);
			makeroom.add(title, "North");
			Panel southPanel = new Panel();
			southPanel.add(roomname);	southPanel.add(ok);
			makeroom.add(southPanel, "Center");
			makeroom.setBounds(500, 300, 200, 100);//방 이름 입력창 크기설정
			makeroom.setVisible(true);

			/*==================================================================
			 *				 방 이름 입력하고 Ok누를때
			 * ==================================================================*/
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if(e.getActionCommand().equals("OK")){
						makeroom.dispose();	//이 창은 없어져야 한다.
						//Room room = Room_Manager.roomMap.get("Main");
						//System.out.println(room);
						try {
							anonyChatFrame.sendMsg(MsgInfo.MAKEROOM, roomname.getText());
						} catch (IOException e1) { e1.printStackTrace(); }
					}
				}
			});
			/*==================================================================
			 *				 방 클릭하고 들어가기누를때
			 * ==================================================================*/
		}else if(ac.getSource()==enter){
			String roomname = roomList.getSelectedItem();
			try{
				if(roomname == null){		//방이 없는데 없는걸 선택해서 들어가기 누르는걸 방지.
					JOptionPane.showMessageDialog(null, "선택된 방이 없습니다. 방을 만드세요.");
				}else{
					this.setVisible(false);
					anonyChatFrame.sendMsg(MsgInfo.ENTER, roomname);
				}
			}catch(IOException e1) { e1.printStackTrace(); }
			/*==================================================================
			 *				 나가기 눌렀을때
			 * ==================================================================*/
		}else if(ac.getSource()==exit){
			dispose();
			String consoleData = "EXIT";
			Send_Message.chattingStart(consoleData);			//Exit 보내기
			//System.exit(0);
			dispose();
			/*==================================================================
			 * 				대기실 채팅 - 버튼으로 보낼때
			 * ==================================================================*/
		}else if(ac.getSource() == waitroomSend) {	//대기실 채팅 전송 버튼
			String consoleData = inputText.getText();
			if (consoleData.equals(""))
				return;
			showText.append(id +" : " + consoleData + "\n");
			inputText.setText("");
			waitJsp.getVerticalScrollBar().setValue(waitJsp.getVerticalScrollBar().getMaximum());//TextArea 스크롤 마지막으로 업데이트
		}		
	}
}
