package calendar.swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import calendar.ScheduleHandler;

class ShowCalendarSwing extends JFrame implements ActionListener{	//UI
	private final int WIDTH = 400,HEIGHT =500;
	private JButton btnBefore = new JButton("Before");
	private JButton btnAfter = new JButton("After");
	
	private String[] dayVal = new String[]{"일","월","화","수","목","금","토"};
	private JLabel[] JLabelDate = new JLabel[7];
	private JButton[] btnDay = new JButton[42];	
	
	private JPanel topJPanel = new JPanel();
	private JPanel centerJPanel = new JPanel();
	private JPanel[] JPanel;
	
	//꾸미기
	private Font dayFont = new Font("",Font.BOLD,15);
	private Font yearMonthFont = new Font("",Font.BOLD,20);
	
	private JLabel labelYear;
	private JLabel labelMonth;
	private Color defualtColor = new JButton().getBackground();
	
	//
	private int year;
	private int month;
	private ScheduleHandler scheduleManager = ScheduleHandler.getInstance();
	private static Calendar cal = Calendar.getInstance();	
	
	//constructor
	public ShowCalendarSwing(String title,int year,int month){
		super(title);
		this.year = year;
		this.month = month;
		this.init();		
	}
	public ShowCalendarSwing(){
		this("Zac`s Calendar",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);		
	}
	
	//start method
	private void init(){
		///////////////////////////////////////////
		//초기 설정		
		
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize(); 		
		int xpos=(int)(screen.getWidth()/2.0-this.getWidth()/2.0); 
		int ypos=(int)(screen.getHeight()/2.0-this.getHeight()/10.0);		
		this.setBounds(120,200,WIDTH,HEIGHT);
//		this.setLocation(xpos, ypos);
//		this.setSize(WIDTH,HEIGHT);			
			
		this.setResizable(false);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		///////////////////////////////////////////
		
		this.setLayout(new BorderLayout());
		
		//초기화
		labelYear = new JLabel(String.valueOf(year));
		labelMonth = new JLabel(String.valueOf(month));
		labelYear.setFont(yearMonthFont);
		labelMonth.setFont(yearMonthFont);
		
		//날짜 (숫자)
		for(int i=0;i<btnDay.length;i++){
			btnDay[i] = new JButton("");
			btnDay[i].addActionListener(event -> dateBtnHandler(event) );
		}
		//날짜 (문자열 월,화,수..)
		for(int i=0;i<JLabelDate.length;i++){
			JLabelDate[i] = new JLabel(dayVal[i],JLabel.CENTER);	
			JLabelDate[i].setFont(dayFont);		
			if(i==6)
				JLabelDate[i].setForeground(Color.BLUE);
			if(i==0)
				JLabelDate[i].setForeground(Color.RED);
		}
		
		topJPanel.setLayout(new FlowLayout());
		topJPanel.add(btnBefore);
		topJPanel.add(labelYear);
		topJPanel.add(labelMonth);
		topJPanel.add(btnAfter);
		
		centerJPanel.setLayout(new GridLayout(7,7));
		JPanel=new JPanel[7];
		for(int i=0;i<JPanel.length;i++){
			JPanel[i] = new JPanel();
			JPanel[i].setLayout(new GridLayout(1,7));
			centerJPanel.add(JPanel[i]);
		}
		
		//일~ 토 라벨 삽입
		for(int i=0;i<JLabelDate.length;i++){
			JPanel[0].add(JLabelDate[i]);
		}
		
		//날짜 숫자 버튼 등록
		int pos=0;
		for(int i=1;i<JPanel.length;i++){
			for(int j=0;j<7;j++){
				JPanel[i].add(btnDay[pos++]);
			}			
		}
		
		makeCalendar();
		
		this.add(topJPanel,BorderLayout.NORTH);	
		this.add(centerJPanel, BorderLayout.CENTER);		
		btnBefore.addActionListener(this);		
		btnAfter.addActionListener(this);		
		this.setVisible(true);
	}		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Before"){
			if(month == 1){
				this.year-=1;
				this.month=12;
			}else{
				this.month-=1;
			}
			makeCalendar();
		}else if(e.getActionCommand() == "After"){
			if(month == 12){
				this.year+=1;
				this.month = 1;
			}else{
				this.month +=1;
			}
			makeCalendar();
		}		
	}
	
	
	private void makeCalendar(){		
		labelYear.setText(String.valueOf(year)+"년");
		labelMonth.setText(String.valueOf(month)+"월");
		//날짜버튼(숫자) 처리		
		cal.set(year, month - 1, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int pos=0;
		//첫주의 빈 날짜
		for (; pos < dayOfWeek-1; pos++) {			
			btnDay[pos].setEnabled(false);
			btnDay[pos].setText("");	
			btnDay[pos].setBackground(Color.WHITE);
			btnDay[pos].setForeground(Color.black);
		}

		//1~ 마지막날까지
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);		
		for (int i=1; i <= lastDay; pos++,i++) {
			if(dayOfWeek%7 == 1)
				btnDay[pos].setForeground(Color.RED);
			else if (dayOfWeek%7 == 0)
				btnDay[pos].setForeground(Color.BLUE);
			dayOfWeek++;
			btnDay[pos].setText(String.valueOf(i));	
			btnDay[pos].setEnabled(true);
			btnDay[pos].setFont(dayFont);			
			btnDay[pos].setBackground(defualtColor);						
		}		
		
		//나머지 채움
		for(;pos<btnDay.length;pos++){			
			btnDay[pos].setEnabled(false);
			btnDay[pos].setText("");	
			btnDay[pos].setBackground(Color.WHITE);
			btnDay[pos].setForeground(Color.black);
		}		
	}
	
	private void dateBtnHandler(ActionEvent event){		
		int day = Integer.parseInt(event.getActionCommand());
		
		String addZeroMonth = "", addZeroDay = "";
		if (month < 10)
			addZeroMonth = "0";
		if (day < 10)
			addZeroDay = "0";
		final String zm = addZeroMonth;
		final String zd = addZeroDay;
		
		SwingUtilities.invokeLater(new Runnable(){			
			@Override
			public void run(){
				new ScheduleFrame("" + year + zm + month + zd + day);
			}
		});
		//new ScheduleFrame("" + year + zeroMonth + month + zeroDay + day);			
	}
}