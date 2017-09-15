package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.ClientHandler;
import controller.MemberController;
import dto.MemberVO;
import request.Request;

public class PendingFrame extends JFrame implements ActionListener{
	private MemberVO admin;
	private java.util.List<MemberVO> pendingList;

	public PendingFrame(MemberVO admin,java.util.List<MemberVO> pendingList) {
		this.admin = admin;
		this.pendingList = pendingList;
		initFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "추가") {
			addHandler();
		} else if (e.getActionCommand() == "취소") {
			dispose();
		}
	}

	private void addHandler() {
		Request request = new Request("responseadd");
		int selectedIdx = displayList.getSelectedIndex();
		MemberVO fromUser = pendingList.remove(selectedIdx);
		int to = fromUser.getId();
		request.setParameter("from", String.valueOf(admin.getId()));
		request.setParameter("to", String.valueOf(to));
		request.setParameter("status","1");
		ClientHandler.getInstance().send(request);		
		MemberController.getInstance().permitFriend(fromUser);		
		fillFriend();	
	}

	////////////////////////////
	// GUI
	private final int WIDTH = 330, HEIGHT = 400;
	private final String DEFAULT_EXPALIN = " 이메일 or 핸드폰 or 이름";
	private JPanel contentPane = new JPanel();
	private JPanel panel = new JPanel();
	// 메인라벨
	private JLabel mainLabel = new JLabel("친구 요청\n 리스트");
	// center
	private List displayList = new List();
	private JScrollPane scrollPane = new JScrollPane(displayList);
	// 하단버튼
	private JButton btnAdd = makeButton("추가");
	private JButton btnCancel = makeButton("취소");

	private void initFrame() {
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
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		// =====================
		// =====메인라벨
		panel.add(mainLabel);
		mainLabel.setFont(new Font("굴림체", Font.BOLD, 18));
		mainLabel.setBounds(105, 21, 116, 31);
		// =====================
		// =====친구 리스트
		panel.add(scrollPane);
		scrollPane.setBounds(20, 100, 285, 180);
		// =====================
		// ===== 추가,취소btn
		panel.add(btnAdd); // 추가
		btnAdd.setBounds(70, 305, 65, 23);
		panel.add(btnCancel); // 취소
		btnCancel.setBounds(190, 305, 65, 23);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		fillFriend();
		setVisible(true);
	}
	
	private void fillFriend() {
		displayList.removeAll();
		for (int i = 0; i < pendingList.size(); i++) {
			MemberVO searchMember = pendingList.get(i);
			displayList.add(searchMember.getName() + "(" + searchMember.getEmail() + ")");
		}
		invalidate();
		repaint();
	}

	private JButton makeButton(String value) { // btn
		JButton btn = new JButton(value);
		btn.addActionListener(this);
		btn.setBackground(new Color(255, 255, 255));
		btn.setForeground(Color.BLACK);
		btn.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
		return btn;
	}	
}
