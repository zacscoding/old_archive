package fruit;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//주문 버튼
class FruitButton extends Button{
	private int fruitIdx; //인덱스 (Fruit에 있는거)
//	private Image image;
	public FruitButton(int idx) {
		fruitIdx = idx;
//		String imagePath = "images/fruit/image" + i + ".jpg";
//		image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}		
	public int getFruitIndex(){ //현재 버튼의 index를 반환
		return fruitIdx;
	}
//	@Override
//	public void paint(Graphics g) {
//		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
//	}	
}

//주문내역 새로 창 띄우는거
public class FruitPos extends Frame implements ActionListener, Runnable {
	private FruitButton[] btnFruit = new FruitButton[Fruit.SIZE];
	private OrderHistory order;	//MainFrame에서 생성된 OrderHistory 인스턴스를 같이 공유함
	
	private int totalPrice; // 판매 금액
	private String content; // MainFrame에서 TextArea에 보여줄 내용
	//UI 관련 코드
	Panel centerPanel;
	private TextArea txtDisplay; //현재 창에 띄여주는 내용
	private TextArea tableDisplay; //MainFrame의 TextArea에 띄여주는거
	private Label tableLabel;	//이름
	
	public FruitPos(int tableNumber,TextArea txt, OrderHistory order) {
		super("--Fruit Order Menu--");
		System.out.println("FruitPos : "+Thread.currentThread().getName());
		tableLabel = new Label(tableNumber+"번 테이블 주문(취소 오른쪽 버튼)",Label.CENTER);
		tableDisplay = txt;
		this.order = order;
	}

	private void initFrame() {
		///////////////////////////////////////////
		// 초기설정
		int xpos = (int) (this.getWidth() / 2.0);
		int ypos = (int) (this.getHeight() / 2.0);
		this.setBounds(xpos, ypos, 600, 600);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();				
			}
		});
		this.setLayout(new BorderLayout());
		txtDisplay = new TextArea(5, 40); //주문내역에 보여줄 것
		txtDisplay.setFont(new Font("", Font.BOLD, 13));
		tableLabel.setFont(new Font("", Font.BOLD, 15));
		tableLabel.setForeground(Color.BLUE);
		
		centerPanel = new Panel();
		centerPanel.setLayout(new GridLayout((int) Math.sqrt(Fruit.SIZE), (int) Math.sqrt(Fruit.SIZE), 5, 5));
		///////////////////////////////////////////////////////////
		for (int i = 0; i < btnFruit.length; i++) {
			btnFruit[i] = new FruitButton(i);
			centerPanel.add(btnFruit[i]);
			btnFruit[i].addActionListener(this);
			btnFruit[i].addMouseListener(new MouseAdapter() { //취소버튼핸들링
				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == 3) { //우클릭이면
						FruitButton selectedBtn = (FruitButton) e.getSource();
						int fruitIdx = selectedBtn.getFruitIndex();
						System.out.println(fruitIdx);
						order.cancel(fruitIdx);		
						editTextDisplay();
					}
				}
			});
		}
		txtDisplay.setText("");		
		this.add(tableLabel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(txtDisplay, BorderLayout.EAST);
	}
	
	@Override
	public void run() {	
		initFrame();
		this.setVisible(true);		
	}	

	//주문창 오른쪽  TextArea 및 Table의 TextArea 같이 출력
	private void editTextDisplay() {
		if(order == null)
			dispose();
		totalPrice = 0;
		txtDisplay.setText("");
		txtDisplay.setText("주문내역서\n");
		txtDisplay.append("=================================\n");		
		for (int i = 0; i < Fruit.SIZE; i++) {			
			if (order.isSaled(i)) {
				totalPrice += (Fruit.PRICE[i]*order.getCount(i));								
				txtDisplay.append(order.getSalesInfo(i)+ "\n");
			}
		}
		
		tableDisplay.append("=================================\n");
		tableDisplay.append("총합 : " + totalPrice);
		content =txtDisplay.getText();
		tableDisplay.setText(content);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //메뉴 버튼 클릭 이벤트 핸들링
		FruitButton selectedBtn = (FruitButton) e.getSource();
		order.add(selectedBtn.getFruitIndex()); //주문 개수를 늘린다
		editTextDisplay(); //TextArea를 바꿔줌
	}

	
//	public static void main(String[] args){		 
//		 SwingUtilities.invokeLater(new FruitPos(1,new TextArea(),new OrderHistory()));		 
//	}
}
