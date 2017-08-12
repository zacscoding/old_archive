package fruit;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends Frame implements ActionListener{
	private final int TABLE_SIZE = 6;
	private OrderHistory storeOrder = new OrderHistory();
	
	//UI
	private Table[] table = new Table[TABLE_SIZE];
	private Button[] btnExit = new Button[TABLE_SIZE];	
	private Label resultTitle = new Label("합계 : ",Label.CENTER);
	private Label resultLabel = new Label("0");
	private Button btnHistory =new Button("총 판매내역");
	private Button btnAllOff = new Button("전체 테이블 퇴점");
	private Label titleLabel = new Label("Zac`s Store!");
	
	//Constructor
	public MainFrame() {
		super("Zac`s Fruit Store!!");
		System.out.println("MainFrame : "+Thread.currentThread().getName());
		init();
	}
	//init
	private void init() {
		///////////////////////////////////////////
		// 초기설정		
		this.setSize(700,800);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() - this.getWidth() - 10);
		int ypos = (int) (screen.getHeight() / 2.0 - this.getHeight() / 2.0);
		this.setLocation(xpos, ypos);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		///////////////////////////////////////////
		this.setLayout(new BorderLayout());
		
		//north
		titleLabel.setFont(new Font("",Font.BOLD,20));
		this.add(titleLabel, BorderLayout.NORTH);
		
		//center
		Panel centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout(4, 2, 10, 10));

		for (int i = 0; i < TABLE_SIZE; i++) {	
			btnExit[i] = new Button("퇴점");
			btnExit[i].addActionListener(this);
			table[i] = new Table(i + 1,btnExit[i],storeOrder);
			centerPanel.add(table[i]);
		}
		
		//south
		Panel southPanel = new Panel();
		//southPanel.setLayout(new FlowLayout());
		southPanel.setLayout(new GridLayout(1,5));
		resultLabel.setFont(new Font("", Font.BOLD, 20));
		resultTitle.setFont(new Font("", Font.BOLD, 20));
		southPanel.add(btnHistory); southPanel.add(btnAllOff);
		southPanel.add(resultTitle); southPanel.add(resultLabel); 		
		btnHistory.setFont(new Font("", Font.BOLD, 13));
		btnAllOff.setFont(new Font("", Font.BOLD, 13));
		btnAllOff.addActionListener(evnet -> {
			for(int i=0;i<table.length;i++){
				table[i].exitBtnHandler();
			}
			resultLabel.setText(String.valueOf(storeOrder.getTotal()));
		});
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel,BorderLayout.SOUTH);
	}
	//start
	public void start() {
		this.setVisible(true);
	}		
	//퇴점 버튼 핸들링
	@Override
	public void actionPerformed(ActionEvent e) {
		Button selectedBtn = (Button)e.getSource();
		int idx=0;
		for(int i=0;i<btnExit.length;i++){
			if(selectedBtn == btnExit[i])
				idx = i;
		}
		table[idx].exitBtnHandler();
		resultLabel.setText(String.valueOf(storeOrder.getTotal()));		
	}	
	
	//main 
	public static void main(String[] args) {
		System.out.println("Main : "+Thread.currentThread().getName());
		MainFrame frm = new MainFrame();
		frm.start();
	}
}
