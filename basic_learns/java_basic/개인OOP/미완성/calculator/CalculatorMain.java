import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



class CalculatorFrame extends Frame{
	private TextField txtDisplay=new TextField();	
	private Button[] btnNumber=new Button[10];
	private Button[] btnOperator=new Button[4];
	private String[] opVal=new String[]{"+","-","*","/"};
	private Button btnEquals = new Button("=");
	private Button btnDot = new Button(".");		
	private boolean numberFlag=false; // 숫자가 입력 들어오면 true
	private boolean wrongExpression = false;
	
	private Font defaultFont = new Font("",Font.BOLD,15);
	private Calculator cal = Calculator.getInstance();
			
	public CalculatorFrame(String title){
		super(title);
		this.init();
	}
	
	private void init(){
		///////////////////////////////////////////
		//초기 frame 설정		
		this.setSize(300,400);			
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize(); 		
		int xpos=(int)(screen.getWidth()/2.0-this.getWidth()/2.0); 
		int ypos=(int)(screen.getHeight()/2.0-this.getHeight()/2.0);		
		this.setLocation(xpos, ypos); 
		this.setResizable(false);		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}			
		});		
//		Panel topPanel = new Panel();
//		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		topPanel.add(txtDisplay);
		txtDisplay.setBackground(Color.WHITE);
				
		this.setLayout(new GridLayout(5,1,0,5));
		//txtDisplay.setEditable(false);
		
		
				
		for(int i=0;i<btnNumber.length;i++){
			btnNumber[i]=new Button(String.valueOf(i));			
			btnNumber[i].setForeground(Color.BLUE);
			btnNumber[i].setFont(defaultFont);
			btnNumber[i].addActionListener(event -> numberButtonHandler(event));
		}
		for(int i=0;i<btnOperator.length;i++){
			btnOperator[i]=new Button(opVal[i]);			
			btnOperator[i].setFont(defaultFont);
			btnOperator[i].addActionListener(event -> operatorButtonHandler(event) );
		}

		btnEquals.setFont(defaultFont);
		btnDot.setFont(defaultFont);
		txtDisplay.setFont(new Font("",Font.BOLD,15));
		
		btnEquals.addActionListener(event -> resultHandler(event));
		txtDisplay.addActionListener(event -> resultHandler(event));
		txtDisplay.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e){
				if(txtDisplay.getText().equals("Wrong Expression"))
					txtDisplay.setText("");
			}
		});
		btnDot.addActionListener(event -> operatorButtonHandler(event));
		
		
//		this.add(topPanel);
		this.add(txtDisplay);
		this.add(makePanel(btnNumber[7],btnNumber[8],btnNumber[9],btnOperator[0]));
		this.add(makePanel(btnNumber[4],btnNumber[5],btnNumber[6],btnOperator[1]));
		this.add(makePanel(btnNumber[1],btnNumber[2],btnNumber[3],btnOperator[2]));
		this.add(makePanel(btnNumber[0],btnEquals,btnDot,btnOperator[3]));		
	}	
	
	//숫자 버튼 이벤트 처리
	private void numberButtonHandler(ActionEvent event){	
		if(wrongExpression){
			txtDisplay.setText("");
			wrongExpression = false;
		}
		Button curBtn = (Button)event.getSource();
		txtDisplay.setText(txtDisplay.getText()+curBtn.getLabel());
		numberFlag = true;		
	}
	
	//연산자 버튼 이벤트 처리
	private void operatorButtonHandler(ActionEvent event){
		if(numberFlag){
			Button opBtn = (Button)event.getSource();
			txtDisplay.setText(txtDisplay.getText()+opBtn.getLabel());
			numberFlag = false;
		}
	}
	
	//= 이벤트 처리
	private void resultHandler(ActionEvent event){
		try{
			double result = cal.calculate(txtDisplay.getText());
			int resultInt = (int) result;
			if((result-resultInt)==0.0)	
				txtDisplay.setText(String.valueOf(resultInt));
			else
				txtDisplay.setText(String.valueOf(result));
		}catch(Exception e){
			txtDisplay.setText("Wrong Expression");
			wrongExpression = true;
		}
	}
	
	private Panel makePanel(Button btn1,Button btn2,Button btn3,Button btn4){
		Panel panel=new Panel();
		panel.setLayout(new GridLayout(1,4,5,0));
		panel.add(btn1); panel.add(btn2); panel.add(btn3); panel.add(btn4);
		return panel;
	}
	
	public void start(){
		this.setVisible(true);
	}	
}

public class CalculatorMain {
	public static void main(String[] args){
		CalculatorFrame frm=new CalculatorFrame("Calculator");
		frm.start();
	}
}


















