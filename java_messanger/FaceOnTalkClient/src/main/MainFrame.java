package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import chat.anonymity.InputId;
import dto.MemberVO;
import frame.FeedFrame;
import frame.FriendsNode;
import frame.LoginFrame;
import frame.PendingFrame;
import frame.SearchFriendFrame;
import frame.UserInformationFrame;
import frame.ChatInviteFrame;
import request.Request;


public class MainFrame extends JFrame implements ActionListener {	
	// JTree에 친구 담는 것과 관련된 코드
	private MemberVO admin;
	private String ip;
	private FriendsNode[] logonUser;
	private FriendsNode[] logoutUser;
	private List<MemberVO> onFriendsList;
	private List<MemberVO> offFriendsList;
	private List<MemberVO> pendingList;
		
	//constructor
	@SuppressWarnings("unchecked")
	public MainFrame(Request response) {
		super("Face on talk");
		admin = (MemberVO)response.getAttribute("loginuser");
		ip = response.getParameter("ip");
		System.out.println(ip);
		onFriendsList = (List<MemberVO>)response.getAttribute("onlist");
		offFriendsList = (List<MemberVO>)response.getAttribute("offlist");
		pendingList =(List<MemberVO>) response.getAttribute("pendinglist");		
		this.init();
		this.action();		
		super.setVisible(true);
		if(!pendingList.isEmpty()) 
			new PendingFrame(admin,pendingList);			
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == info_bt) {
			new UserInformationFrame(admin);
		}else if(e.getSource() == chat_bt) {
			new ChatInviteFrame("초대하기");
		}else if(e.getSource() == msg_bt) {
			new InputId();			
		}else if (e.getSource() == search_bt) {
			new SearchFriendFrame(admin.getId());
		}else if(e.getSource() == pheed_bt) {
			new FeedFrame(admin);			
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new LoginFrame("Face On Talk").setVisible(true);
			}
		});
		//new LoginFrame("Face On Talk").setVisible(true);				
	}
	
	
	
	
	
	////////////////////////////
	//GUI
	// 전체 GUI(상태창, 좌측 버튼(쪽지, 정보창 등), wait_p)
	private JPanel logo_p = new JPanel();
	private JPanel center_p = new JPanel();
	private JPanel button_p = new JPanel();
	private JPanel waitUser = new JPanel();
	private JButton chat_bt = new JButton(new ImageIcon("image/chat.png"));
	private JButton msg_bt = new JButton(new ImageIcon("image/anony.png"));
	private JButton pheed_bt = new JButton(new ImageIcon("image/feed.png"));
	private JButton search_bt = new JButton(new ImageIcon("image/search.png"));
	private JButton info_bt = new JButton(new ImageIcon("image/info.png"));
	
	// logo_p 관련 GUI
	private JButton logo_bt = new JButton(new ImageIcon("image/usericon.png"));
	private JLabel logo_userName = new JLabel();
	private SoftBevelBorder p_soft = new SoftBevelBorder(SoftBevelBorder.RAISED); 
	
	// wait_p 관련 GUI
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("친구목록");
	private DefaultMutableTreeNode logon_root = new DefaultMutableTreeNode("로그인 된 친구");
	private DefaultMutableTreeNode logout_root = new DefaultMutableTreeNode("로그아웃 된 친구");
	
	private JTree friends_tree = new JTree(root);
	private JScrollPane friends_scroll = new JScrollPane(friends_tree);
	private DefaultTreeModel friends_model = (DefaultTreeModel)friends_tree.getModel();
	private TreePath friends_path = friends_tree.getSelectionPath();
	
	public void init() {				
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		super.setSize(400, (int)screen.getHeight()-50); 
		int xpos = (int)(screen.getWidth() - this.getWidth());				
		super.setLocation(xpos, 0);
		super.setResizable(false);
		Container con = this.getContentPane();		
		// 전체 GUI(상태창, 좌측 버튼(쪽지, 정보창 등), wait_p)
		con.setLayout(new BorderLayout());
		con.add("North", logo_p); con.add("Center", center_p);
		center_p.setLayout(new BorderLayout());
		center_p.add("West", button_p); center_p.add("Center", friends_scroll);
		button_p.setLayout(new GridLayout(5,1)); button_p.setBackground(new Color(255,255,255));
		button_p.setPreferredSize(new Dimension(70, 0));
		button_p.setBorder(BorderFactory.createEmptyBorder(0,0,400,0));
		button_p.add(chat_bt); button_p.add(msg_bt); button_p.add(pheed_bt); button_p.add(search_bt); button_p.add(info_bt);
		chat_bt.setBorderPainted(false); chat_bt.setContentAreaFilled(false); chat_bt.setFocusPainted(false); 
		msg_bt.setBorderPainted(false); msg_bt.setContentAreaFilled(false); msg_bt.setFocusPainted(false); 
		pheed_bt.setBorderPainted(false); pheed_bt.setContentAreaFilled(false); pheed_bt.setFocusPainted(false); 
		search_bt.setBorderPainted(false); search_bt.setContentAreaFilled(false); search_bt.setFocusPainted(false); 
		info_bt.setBorderPainted(false); info_bt.setContentAreaFilled(false); info_bt.setFocusPainted(false); 
		chat_bt.setToolTipText("채팅 초대창");
		msg_bt.setToolTipText("익명 채팅");
		pheed_bt.setToolTipText("피드창");
		search_bt.setToolTipText("친구 찾기");
		info_bt.setToolTipText("나의 정보 확인");
		
		// logo_p 관련 GUI
		logo_p.setLayout(new FlowLayout(FlowLayout.LEFT));
		logo_p.setBorder(new MatteBorder(0, 0, 4, 0, Color.BLACK));
		logo_p.setBackground(new Color(255,255,255));
		logo_p.setPreferredSize(new Dimension(0, 120));
		logo_p.add(logo_bt);
		logo_bt.setBorderPainted(false); logo_bt.setContentAreaFilled(false); logo_bt.setFocusPainted(false); 
		logo_p.add(logo_userName);
		
		root.add(logon_root);
		root.add(logout_root);	
		
		
		/////////////////////
		//바뀐 코드
		/////////////////
		String name = admin.getName();
		String email = admin.getEmail();
		logo_userName.setText("<html>" + name + "<br>" + email + "</html>");
		logo_userName.setFont(new Font("", Font.BOLD, 15));
		
		fillFriendsList();		
		//super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
	}
	
	public void fillFriendsList() {		
		logon_root.removeAllChildren();
		logout_root.removeAllChildren();
		logonUser = new FriendsNode[onFriendsList.size()];
		logoutUser = new FriendsNode[offFriendsList.size()];
		
		for(int i = 0; i < onFriendsList.size(); i++){
			//System.out.println(onFriendsList.get(i).getName());			
			logonUser[i] = new FriendsNode(onFriendsList.get(i));
			logon_root.add(logonUser[i]);
			
		}		

		for(int i=0;i<offFriendsList.size();i++) {
			//System.out.println(offFriendsList.get(i).getName());
			logoutUser[i] = new FriendsNode(offFriendsList.get(i));
			logout_root.add(logoutUser[i]);
		}
		
		friends_model.reload(root);//목록 펼치기
		friends_tree.expandPath(friends_path);
		invalidate();
		repaint();
	}
	
	public String getIP() {
		return ip;
	}
	
	public void action(){
		search_bt.addActionListener(this); //친구 찾기 버튼
		info_bt.addActionListener(this);
		chat_bt.addActionListener(this); //채팅 친구 추가 버튼
		msg_bt.addActionListener(this);
		pheed_bt.addActionListener(this);
		
	}		
}