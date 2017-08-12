package calculator.awt;
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
import java.text.DecimalFormat;

import calculator.Calculator;

class CalculatorFrame extends Frame{
	private TextField txtDisplay=new TextField();	
	private Button[] btnNumber=new Button[10];
	private Button[] btnOperator=new Button[4];
	private String[] opVal=new String[]{"+","-","*","/"};
	private Button btnEquals = new Button("=");
	private Button btnDot = new Button(".");
	private Button btnClear = new Button("Clear");
	private Button btnBackSpace = new Button("←");
	private boolean isLastInputNumber = false; // 숫자가 입력 들어오면 true
	private boolean wrongExpression = false;
	
	private Font defaultFont = new Font("",Font.BOLD,20);
	private Calculator cal = Calculator.getInstance();
	private DecimalFormat decimalFormat = new DecimalFormat(".0000");
			
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
		this.setLayout(new GridLayout(6,1,0,5));
				
		//number 버튼 생성 및 색상,크기 ,이벤트
		for(int i=0;i<btnNumber.length;i++){
			btnNumber[i]=new Button(String.valueOf(i));			
			btnNumber[i].setForeground(Color.BLUE);
			btnNumber[i].setFont(defaultFont);
			btnNumber[i].addActionListener(event -> numberButtonHandler(event));
		}
		
		//operator 버튼 생성 및 색상,크기 ,이벤트
		for(int i=0;i<btnOperator.length;i++){
			btnOperator[i]=new Button(opVal[i]);			
			btnOperator[i].setFont(defaultFont);
			btnOperator[i].addActionListener(event -> operatorButtonHandler(event) );
		}
		
		//그외 버튼
		btnClear.setFont(defaultFont);
		btnEquals.setFont(defaultFont);
		btnDot.setFont(defaultFont);
		btnBackSpace.setFont(defaultFont);
		//display
		txtDisplay.setFont(new Font("",Font.BOLD,30));
		txtDisplay.setBackground(Color.WHITE);
		
		btnDot.addActionListener(event -> operatorButtonHandler(event));
		btnEquals.addActionListener(event -> resultHandler(event));
		btnClear.addActionListener(event -> {
			txtDisplay.setText("");
		});
		btnBackSpace.addActionListener(event -> {
			String prevExp = txtDisplay.getText();			
			txtDisplay.setText(prevExp.substring(0, prevExp.length()-1));
		});
		
		txtDisplay.addActionListener(event -> resultHandler(event));
		txtDisplay.addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e){
				if(txtDisplay.getText().equals("Wrong Expression"))
					txtDisplay.setText("");
			}
		});
		
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1,2,0,5));
		panel.add(btnClear); panel.add(btnBackSpace);
		
		this.add(txtDisplay);
		this.add(panel);		
		this.add(makePanel(btnNumber[7],btnNumber[8],btnNumber[9],btnOperator[0]));
		this.add(makePanel(btnNumber[4],btnNumber[5],btnNumber[6],btnOperator[1]));
		this.add(makePanel(btnNumber[1],btnNumber[2],btnNumber[3],btnOperator[2]));
		this.add(makePanel(btnNumber[0],btnEquals,btnDot,btnOperator[3]));
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}			
		});
	}	
	
	//숫자 버튼 이벤트 처리
	private void numberButtonHandler(ActionEvent event){	
		if(wrongExpression){
			txtDisplay.setText("");
			wrongExpression = false;
		}
		Button curBtn = (Button)event.getSource();
		txtDisplay.setText(txtDisplay.getText()+curBtn.getLabel());
		isLastInputNumber = true;		
	}
	
	//연산자 버튼 이벤트 처리
	private void operatorButtonHandler(ActionEvent event){
		if(isLastInputNumber){
			Button curBtn = (Button)event.getSource();
			txtDisplay.setText(txtDisplay.getText()+curBtn.getLabel());
			isLastInputNumber = false;
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
				txtDisplay.setText(decimalFormat.format(result));
			
			txtDisplay.setCaretPosition(txtDisplay.getText().length()); // 커서 맨 마지막으로
			
		}catch(Exception e){
			txtDisplay.setText("Wrong Expression");
			wrongExpression = true;
			isLastInputNumber = false;
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
		CalculatorFrame calFrm=new CalculatorFrame("Calculator");
		calFrm.start();
	}
}


















