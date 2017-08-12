package fruit;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Comparator;

public class HistoryFrame extends Frame implements Runnable{
	private OrderHistory storeOrder;
	private Button btnName = new Button("이름");
	private Button btnPrice = new Button("가격");
	private Button btnAmount = new Button("판매수량");
	private Button btnTotal = new Button("총합");
	
	private Label titleLabel = new Label("총합 : ",Label.CENTER);
	private Label resultLabel = new Label("0");
	
	private Font btnFont = new Font("",Font.BOLD,15);
	private Font labelFont = new Font("",Font.BOLD,18);
	private Font listFont = new Font("",Font.BOLD,18);
	
	public HistoryFrame(OrderHistory storeOrder){
		this.storeOrder = storeOrder;
		init();
	}	
	
	@Override
	public void run(){
		//init();
	}	
	private void init() {
		///////////////////////////////////////////
		// 초기설정		
		this.setSize(600,400);
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
		this.setLayout(new GridLayout(Fruit.SIZE+2,1));
		
		//상단
		Panel topPanel= new Panel();
		topPanel.setLayout(new GridLayout(1,4));
		topPanel.add(btnName); topPanel.add(btnPrice);
		topPanel.add(btnAmount); topPanel.add(btnTotal);
		btnName.setFont(btnFont); btnPrice.setFont(btnFont);
		btnAmount.setFont(btnFont); btnTotal.setFont(btnFont);
		
		this.add(topPanel);
		//리스트
		ListPanel[] listPanel = new ListPanel[Fruit.SIZE];
		for(int i=0;i<listPanel.length;i++){
			listPanel[i] = new ListPanel();
			this.add(listPanel[i].getPanel());
		}	
		//하단
		Panel bottomPanel = new Panel();
		bottomPanel.setLayout(new GridLayout(1,4));
		bottomPanel.add(new Label()); bottomPanel.add(new Label());
		bottomPanel.add(titleLabel); bottomPanel.add(resultLabel);		
		titleLabel.setFont(labelFont);
		resultLabel.setFont(labelFont);
		this.add(bottomPanel);
		//이벤트
		btnName.addActionListener(event ->{
			String[] name = Fruit.NAME;			
			Arrays.sort(name,new Comparator<String>(){
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}				
			});
			
			
			
			
		});
		
		
		this.setVisible(true);
	}
	
	class ListPanel{
		TextField[] txtField = new TextField[4];
		public Panel getPanel(){
			Panel panel = new Panel();			
			panel.setLayout(new GridLayout(1,4));
			for(int i=0;i<txtField.length;i++){
				txtField[i] = new TextField();
				txtField[i].setFont(listFont);
				panel.add(txtField[i]);
			}
			return panel;			
		}		
		public void setText(String[] content){
			for(int i=0;i<txtField.length;i++){
				txtField[i].setText(content[i]);
			}			
		}				
	}
	
	public static void main(String[] args){
		HistoryFrame frm = new HistoryFrame(new OrderHistory());
	}
}
