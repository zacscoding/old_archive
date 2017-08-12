package calendar.awt;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import calendar.ScheduleHandler;

class ShowCalendar extends Frame{
	//UI
	private Button btnBefore = new Button("Before");
	private Button btnAfter = new Button("After");
	
	private String[] dayVal = new String[]{"일","월","화","수","목","금","토"};
	private Label[] labelDate = new Label[7];
	private Button[] btnDay = new Button[42];	
	
	private Panel topPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Panel[] panel;
	
	//꾸미기
	private Font dayFont = new Font("",Font.BOLD,15);
	private Font yearMonthFont = new Font("",Font.BOLD,20);
	
	private Label labelYear;
	private Label labelMonth;
	
	//
	private int year;
	private int month;
	private ScheduleHandler scheduleManager = ScheduleHandler.getInstance();
	private static Calendar cal = Calendar.getInstance();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	//constructor
	public ShowCalendar(String title,int year,int month){
		super(title);
		this.year = year;
		this.month = month;
		this.init();
	}
	public ShowCalendar(){
		this("Zac`s Calendar",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);		
	}
	
	//start method
	public void start(){
		this.setVisible(true);
	}	
	
	private void init(){
		///////////////////////////////////////////
		//초기 설정		
		this.setSize(300,400);			
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize(); 		
		int xpos=(int)(screen.getWidth()/2.0-this.getWidth()/2.0); 
		int ypos=(int)(screen.getHeight()/2.0-this.getHeight()/2.0);		
		this.setLocation(xpos, ypos); 
		this.setResizable(false);		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				scheduleManager.saveFile();
				System.exit(0);
			}			
		});
		///////////////////////////////////////////
		
		this.setLayout(new BorderLayout());
		
		//초기화
		labelYear = new Label(String.valueOf(year));
		labelMonth = new Label(String.valueOf(month));
		labelYear.setFont(yearMonthFont);
		labelMonth.setFont(yearMonthFont);
		
		//날짜 (숫자)
		for(int i=0;i<btnDay.length;i++){
			btnDay[i] = new Button("");
			btnDay[i].addActionListener(event -> btnHandler(event) );
		}
		//날짜 (문자열 월,화,수..)
		for(int i=0;i<labelDate.length;i++){
			labelDate[i] = new Label(dayVal[i],Label.CENTER);	
			labelDate[i].setFont(dayFont);			
		}
		
		topPanel.setLayout(new FlowLayout());
		topPanel.add(btnBefore);
		topPanel.add(labelYear);
		topPanel.add(labelMonth);
		topPanel.add(btnAfter);
		
		centerPanel.setLayout(new GridLayout(7,7));
		panel=new Panel[7];
		for(int i=0;i<panel.length;i++){
			panel[i] = new Panel();
			panel[i].setLayout(new GridLayout(1,7));
			centerPanel.add(panel[i]);
		}
		
		//일~ 토 라벨 삽입
		for(int i=0;i<labelDate.length;i++){
			panel[0].add(labelDate[i]);
		}
		
		//날짜 숫자 버튼 등록
		int pos=0;
		for(int i=1;i<panel.length;i++){
			for(int j=0;j<7;j++){
				panel[i].add(btnDay[pos++]);
			}			
		}
		
		makeCalendar();
		
		this.add(topPanel,BorderLayout.NORTH);	
		this.add(centerPanel, BorderLayout.CENTER);
		
		btnBefore.addActionListener(event -> {
			if(month == 1){
				this.year-=1;
				this.month=12;
			}else{
				this.month-=1;
			}
			makeCalendar();
		});
		
		btnAfter.addActionListener(event -> {
			if(month == 12){
				this.year+=1;
				this.month = 1;
			}else{
				this.month +=1;
			}
			makeCalendar();
			
		});	
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
			btnDay[pos].setLabel("");	
			btnDay[pos].setBackground(Color.WHITE);
		}

		//1~ 마지막날까지
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);		
		for (int i=1; i <= lastDay; pos++,i++) {			
			btnDay[pos].setLabel(String.valueOf(i));	
			btnDay[pos].setEnabled(true);
			btnDay[pos].setFont(dayFont);
			btnDay[pos].setBackground(Color.GRAY);
			btnDay[pos].setForeground(Color.YELLOW);			
		}		
		
		//나머지 채움
		for(;pos<btnDay.length;pos++){			
			btnDay[pos].setEnabled(false);
			btnDay[pos].setLabel("");	
			btnDay[pos].setBackground(Color.WHITE);
		}		
	}
	
	private void btnHandler(ActionEvent event){		
		Button curBtn = (Button) event.getSource();
		int day = Integer.parseInt(curBtn.getActionCommand());
		String zeroMonth = "", zeroDay = "";
		if (month < 10)
			zeroMonth = "0";
		if (day < 10)
			zeroDay = "0";
		ScheduleFrame frm = new ScheduleFrame("" + year + zeroMonth + month + zeroDay + day);
		frm.start();
			
	}
}