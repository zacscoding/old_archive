package chat.anonymity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.GregorianCalendar;

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

public class ChatRoom extends JFrame implements KeyListener, ActionListener {
	static JTextArea showText = new JTextArea();
	JTextField inputText = new JTextField();
	List IDlist = new List();
	// 버튼 추가작업

	static JLabel inputid;// 사용자 ID표시용
	static String title; // 채팅방 이름
	String id;
	JPopupMenu chatpopup; // 우클릭하여 팝업메뉴창
	JMenuItem kick;
	JScrollPane chatJsp;
	JScrollBar chatJsb;
	JScrollPane userJsp;
	JScrollBar userJsb;
	int chiefcode = 0;
	

	///////////////////
	// test code
	////////////////////
	JButton userInvite = new JButton("초대");
	JButton send = new JButton("전송");
	JButton talkSave = new JButton(new ImageIcon("image/anonymity/talkSave.png"));
	JButton userlistAll = new JButton(new ImageIcon("image/anonymity/userlistall.png"));
	JButton exit = new JButton(new ImageIcon("image/anonymity/chatExit.jpg"));

	//////////////////////

	ChatRoom(String title) {
		super("방 이름 : " + title);
		ChatRoom.title = title;
	}

	public void showFrame(String name) {
		id = name;
		inputid = new JLabel(id);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		inputText.addKeyListener(this);

		// 오른쪽 버튼을 클릭 했을때.
		chatpopup = new JPopupMenu();
		kick = new JMenuItem("강퇴");
		kick.addActionListener(this);

		// 스크롤바 추가
		chatJsp = new JScrollPane(showText);
		chatJsp.setWheelScrollingEnabled(true);
		chatJsb = chatJsp.getVerticalScrollBar();

		// JTextArea(대화창) 비활성화
		showText.setEditable(false);
		// 대화창 자동개행
		showText.setLineWrap(true);

		JPanel leftinputText = new JPanel();
		leftinputText.setLayout(new BorderLayout());
		leftinputText.add(inputText, "Center");
		leftinputText.add(send, "East");

		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(inputid, "North");
		left.add(chatJsp, "Center");
		left.add(leftinputText, "South");
		left.setBorder(loweredetched);

		/*
		 * =================== 방 접속자 목록 설정 =====================
		 */
		// 스크롤바 추가
		userJsp = new JScrollPane(IDlist);
		userJsp.setWheelScrollingEnabled(true);
		userJsb = userJsp.getVerticalScrollBar();

		JPanel right = new JPanel();
		right.setLayout(new GridLayout(1, 1));
		right.add(userJsp);
		right.setBorder(loweredetched);

		IDlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					// System.out.println("방장 코드 : " + chiefcode);
					if (chiefcode == 1) { // 방장이면
						chatpopup.add(kick); // 강퇴기능 추가
						chatpopup.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		});

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.LEFT));
		south.setBackground(new Color(255, 255, 255));

		south.add(talkSave);
		south.add(userlistAll);
		south.add(exit);
		south.setBorder(loweredetched);

		// Button Action
		send.addActionListener(this);
		talkSave.addActionListener(this);
		userlistAll.addActionListener(this);
		exit.addActionListener(this);

		talkSave.setBorderPainted(false);
		talkSave.setContentAreaFilled(false);
		talkSave.setFocusPainted(false);
		talkSave.setToolTipText("대화내용 저장");
		userlistAll.setBorderPainted(false);
		userlistAll.setContentAreaFilled(false);
		userlistAll.setFocusPainted(false);
		userlistAll.setToolTipText("사용자 초대");
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setToolTipText("방 나가기");

		/*
		 * =================== 전체 레이아웃 설정 ============================
		 */
		setBounds(100, 100, 500, 421);
		setLayout(new BorderLayout());
		add(left, "Center");
		add(right, "East");
		add(south, "South");
		setVisible(true);
		// X를 눌렀을때는 현재 대화방에서 나가기.
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.out.println("채팅방 종료");// "ChatRoom Frame 종료");
				try {
					anonyChatFrame.sendMsg(MsgInfo.GOWAIT, inputid.getText() + "/" + title);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/*
	 * ========================== 대화내용 내려받기 위한 메서드 ============================
	 */
	public void talkSave() {
		FileDialog fdial = new FileDialog(this, "저장", FileDialog.SAVE); // 저장모드.
		fdial.setVisible(true);
		/*
		 * 날짜와 시각을 위해 GregorianCalendar와 DateFormat을 이용한다.
		 */
		GregorianCalendar gc = new GregorianCalendar();
		DateFormat df = DateFormat.getInstance();
		String data = df.format(gc.getTime()) + "\r\n[" + title + "]" + "에서의 대화내용" + "\r\n"; // 시간
																								// 추가
		data += ChatRoom.showText.getText().replaceAll("\n", "\r\n");
		// \n의 값을 \r\n으로 해야 메모장에서 엔터 기능이 이루어 진다.
		BufferedWriter bw;
		try {
			try {
				// BufferedWriter를 저장 위치로 설정.
				bw = new BufferedWriter(new FileWriter(fdial.getDirectory() + "\\" + fdial.getFile()));
				bw.write(data);
				bw.close();
			} catch (FileNotFoundException file) {
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '\n') {

			chatJsb.setValue(chatJsb.getMaximum()); // 엔터를 치면 스크롤바도
													// 밑으로 내려오도록 함
			String consoleData = title + "/" + inputText.getText(); // 입력한걸 저장
			inputText.setText(""); // 저장후 지워야 함.
			Send_Message.chattingStart(consoleData); // 채팅 시작
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String receiveid = IDlist.getSelectedItem();
		if (e.getSource() == exit) {
			this.setVisible(false);
			// System.out.println("ChatRoom Frame 종료"); //확인
			try {
				anonyChatFrame.sendMsg(MsgInfo.GOWAIT, inputid.getText() + "/" + title);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			showText.setText("");// 방을 나갈때 채팅내용 초기화

			/*
			 * ================== 대화내용 저장 ====================
			 */
		} else if (e.getSource() == talkSave) {
			talkSave();
			/*
			 * ================== 전송 버튼 ====================
			 */
		} else if (e.getSource() == send) {
			String msg = inputText.getText();
			if (msg.equals(""))
				return;
			showText.append("id : " + msg + "\n");
			showText.setCaretPosition(showText.getDocument().getLength());
			inputText.setText("");

			/*
			 * ================== 접속자 보기 클릭 시 ====================
			 */
		} else if (e.getSource() == userlistAll) {
			try {
				anonyChatFrame.sendMsg(MsgInfo.SHOWUSER, null);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			/*
			 * ================== 방장이 (오른쪽 버튼) 강퇴 눌렀을때 ====================
			 */
		} else if (e.getSource() == kick) {
			if (receiveid == null) { // 받는 사람이 없을때
				JOptionPane.showMessageDialog(null, "대상을 선택하세요.");
			} else if (receiveid.startsWith("[방장]")) {
				JOptionPane.showMessageDialog(null, "방장을 강퇴시킬 수 없습니다.");// "자신을
																		// 강퇴 시킬
																		// 수 없음
			} else {
				try {
					anonyChatFrame.sendMsg(MsgInfo.KICK, receiveid + "/" + title); // 강퇴
																					// 명령어
																					// 전송
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
