package baseball;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame implements ActionListener {
	private final int WIDTH=700,HEIGHT=800;
	private int currentIdx = 0;
	private TeamManager manager =TeamManager.getInstance();
	
	// UI
	private JButton[] btnTeam = new JButton[Team.defaultTeam.length];
	private ListPanel listPanel[] = new ListPanel[Team.defaultTeam.length];
	private JPanel viewPanel = new JPanel();
	

	public MainFrame() {
		super("야구팀 관리프로그램");		
		init();
	}

	private void init() {
		//		
		setSize(WIDTH,HEIGHT);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2.0 - this.getWidth() / 2.0);
		int ypos = (int) (screen.getHeight() / 2.0 - this.getHeight() / 2.0);
		this.setLocation(xpos, ypos);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				manager.saveToFile();
				System.exit(0);				
			}			
		});
		///////////////////////////////////////////
		setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,Team. defaultTeam.length, 10, 10));
		
		//팀버튼 생성
		for (int i = 0; i < btnTeam.length; i++) {
			btnTeam[i] = new JButton(Team.defaultTeam[i]);
			btnTeam[i].addActionListener(this);
			northPanel.add(btnTeam[i]);
		}
		
		//diplay 판넬 생성
		for(int i=0;i<listPanel.length;i++){
			listPanel[i] = new ListPanel(Team.defaultTeam[i]);
		}	
		
		add(northPanel, BorderLayout.NORTH);
		add(viewPanel,BorderLayout.CENTER);
		setVisible(true);
	}

	// 팀버튼 핸들러
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentIdx>=0 && currentIdx<btnTeam.length)
			btnTeam[currentIdx].setEnabled(true);
		
		JButton selectedBtn = (JButton)e.getSource();
		for(int i=0;i<btnTeam.length;i++){
			if(selectedBtn == btnTeam[i]){
				btnTeam[i].setEnabled(false);
				currentIdx = i;
				changeViewPanel(i);
			}
		}
	}
	
	private void changeViewPanel(int idx){		
		remove(viewPanel);
		viewPanel = listPanel[idx];
		add(viewPanel,BorderLayout.CENTER);
		listPanel[idx].displayInfo();
		invalidate();
		repaint();		
	}
	
	
	// center에 list 보여주는 static 클래스
	private static class ListPanel extends JPanel implements ActionListener {
		private String teamName;
		private TeamManager manager = TeamManager.getInstance();

		private JButton btnInput;
		private JButton btnDelete;
		private JButton btnModify;
		private JTextArea txtDisplay;
		private JScrollPane displayScroll;

		public ListPanel(String name) {
			teamName = name;
			makePanel();
			displayInfo();
		}

		private void makePanel() {
			setLayout(new BorderLayout());
			// center
			txtDisplay = new JTextArea();
			txtDisplay.setFont(new Font("", Font.BOLD, 15));
			txtDisplay.setEditable(false);
			displayScroll = new JScrollPane(txtDisplay);
			add(displayScroll, BorderLayout.CENTER);

			// south
			JPanel southPanel = new JPanel();
			southPanel.setLayout(new GridLayout(1, 3));
			btnInput = makeButton("입력");
			btnDelete = makeButton("삭제");
			btnModify = makeButton("수정");
			southPanel.add(btnInput);
			southPanel.add(btnDelete);
			southPanel.add(btnModify);
			add(southPanel, BorderLayout.SOUTH);
		}
		
		private JButton makeButton(String caption) {
			JButton btn = new JButton(caption);
			btn.addActionListener(this);
			return btn;
		}
		
		//버튼 이벤트 핸들링
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "입력")
				inputHandler();
			else if (e.getActionCommand() == "삭제")
				removeHandler();
			else if (e.getActionCommand() == "수정")
				modifyHandler();
		}
		
		private String input(String value){
			String name = JOptionPane.showInputDialog(this, value,"input",JOptionPane.QUESTION_MESSAGE);			
			if(name!=null && name.isEmpty()){
				name = null;
				JOptionPane.showMessageDialog(this, "입력이 바르지 않습니다.");
			}
			return name;
		}	
		
		//입력
		private void inputHandler() {	
			//입력받기
			String name = input("이름 : ");
			if(name==null) return;
			
			String salary = input("연봉 : ");
			if(salary == null) return;
			
			String position = input("포지션 : ");
			if(position == null) return;			
			
			//예외 검사
			Map<String,Boolean> errors = new HashMap<>();			
			manager.addPlayer(teamName, name,salary,position, errors);			
			if(errors.isEmpty()){
				JOptionPane.showMessageDialog(this,"성공적으로 저장하였습니다.");
				displayInfo();
			}else{				
				if(errors.get("duplicateName")==Boolean.TRUE)
					JOptionPane.showMessageDialog(this, "동일한 이름이 존재합니다.");
				else if(errors.get("invalidSalary")==Boolean.TRUE)
					JOptionPane.showMessageDialog(this, "연봉은 숫자만 입력해주세요.");				
			}					
		}

		//삭제
		private void removeHandler() {
			//입력받기
			String name = input("삭제 할 이름 : ");
			
			//예외검사
			Map<String,Boolean> errors = new HashMap<>();
			manager.removePlayer(teamName, name, errors);			
			if(errors.isEmpty()){
				JOptionPane.showMessageDialog(this,"성공적으로 삭제하였습니다.");
				displayInfo();
			}else{				
				if(errors.get("notExistName")==Boolean.TRUE)
					JOptionPane.showMessageDialog(this, "이름이 존재하지 않습니다.");				
			}
		}
		
		//수정
		private void modifyHandler() {
			//입력받기
			String name = input("수정 할 이름 : ");
			if(name==null) return;
			
			String salary = input("수정 할 연봉 : ");
			if(salary == null) return;
			
			String position = input("수정 할 포지션 : ");
			if(position == null) return;
			
			//예외검사
			Map<String,Boolean> errors = new HashMap<>();
			manager.modifyPlayer(teamName, name,salary,position, errors);			
			
			if(errors.isEmpty()){
				JOptionPane.showMessageDialog(this,"성공적으로 수정하였습니다.","success",JOptionPane.DEFAULT_OPTION);
				displayInfo();
			}else{							
				if(errors.get("notExistName")==Boolean.TRUE)
					JOptionPane.showMessageDialog(this, "존재하지 않는 이름입니다.");
				else if(errors.get("invalidSalary")==Boolean.TRUE)
					JOptionPane.showMessageDialog(this, "연봉은 숫자만 입력해주세요.");
			}	
		}
	
		//정보 출력
		public void displayInfo() {
			Player[] playArr = manager.getPlayersArray(teamName);
			if (playArr.length==0)
				txtDisplay.setText("등록된 선수가 없습니다.");
			else {
				txtDisplay.setText("번호\t이름\t연봉\t포지션\n");
				for (int i = 0; i < playArr.length; i++) {					
					txtDisplay.append((i + 1) + "\t" + playArr[i].getPlayerInfo());
				}
			}
		}
	}


	// main
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});		
	}
}
