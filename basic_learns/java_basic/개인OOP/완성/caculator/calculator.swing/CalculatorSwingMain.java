package calculator.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.Calculator;

class CalculatorFrame extends Frame{
	private JTextField txtDisplay=new JTextField();	
	private JButton[] btnNumber=new JButton[10];
	private JButton[] btnOperator=new JButton[4];
	private String[] opVal=new String[]{"+","-","*","/"};
	private JButton btnEquals = new JButton("=");
	private JButton btnDot = new JButton(".");
	private JButton btnClear = new JButton("Clear");
	private JButton btnBackSpace = new JButton("←");
	private boolean isLastInputNumber=false; // 숫자가 입력 들어오면 true
	private boolean wrongExpression = false;
	
	
	private Font defaultFont = new Font("",Font.BOLD,15);
	private Calculator cal = Calculator.getInstance();
	private DecimalFormat decimalFormat = new DecimalFormat(".0000");
			
	public CalculatorFrame(String title){
		super(title);
		this.init();
	}
	
	private void init(){
		///////////////////////////////////////////
		//초기 frame 설정		
		this.setSize(300,500);			
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize(); 		
		int xpos=(int)(screen.getWidth()/2.0-this.getWidth()/2.0); 
		int ypos=(int)(screen.getHeight()/2.0-this.getHeight()/2.0);		
		this.setLocation(xpos, ypos); 
		this.setResizable(false);
		
		txtDisplay.setBackground(Color.WHITE);
		txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
				
		this.setLayout(new GridLayout(6,1,0,5));		
				
		for(int i=0;i<btnNumber.length;i++){
			btnNumber[i]=new JButton(String.valueOf(i));			
			btnNumber[i].setForeground(Color.BLUE);
			btnNumber[i].setFont(defaultFont);
			btnNumber[i].addActionListener(event -> numberButtonHandler(event));
		}
		for(int i=0;i<btnOperator.length;i++){
			btnOperator[i]=new JButton(opVal[i]);			
			btnOperator[i].setFont(defaultFont);
			btnOperator[i].addActionListener(event -> operatorButtonHandler(event) );
		}
		//글씨크기 , 굵기 설정
		btnClear.setFont(defaultFont);
		btnBackSpace.setFont(defaultFont);
		btnEquals.setFont(defaultFont);
		btnDot.setFont(defaultFont);
		txtDisplay.setFont(new Font("",Font.BOLD,30));		
		
		//이벤트처리
		btnClear.addActionListener(event -> {
			txtDisplay.setText("");
		});
		btnBackSpace.addActionListener(event -> {
			String prevExp = txtDisplay.getText();			
			txtDisplay.setText(prevExp.substring(0, prevExp.length()-1));
		});
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
		
		JPanel panel = new JPanel();
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
		JButton curBtn = (JButton)event.getSource();
		txtDisplay.setText(txtDisplay.getText()+curBtn.getText());
		isLastInputNumber = true;		
	}
	
	//연산자 버튼 이벤트 처리
	private void operatorButtonHandler(ActionEvent event){
		if(isLastInputNumber){
			JButton opBtn = (JButton)event.getSource();
			txtDisplay.setText(txtDisplay.getText()+opBtn.getText());
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
			
		}catch(Exception e){
			txtDisplay.setText("Wrong Expression");
			wrongExpression = true;
			isLastInputNumber = false;
		}		
	}
	
	private JPanel makePanel(JButton btn1,JButton btn2,JButton btn3,JButton btn4){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,4,5,0));
		panel.add(btn1); panel.add(btn2); panel.add(btn3); panel.add(btn4);
		return panel;
	}
	
	public void start(){
		this.setVisible(true);
	}	
}

public class CalculatorSwingMain {
	public static void main(String[] args){
		CalculatorFrame calFrm=new CalculatorFrame("Calculator");
		calFrm.start();
	}
}
