package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import connector.WebConnector;
import controller.CalendarDataManager;
import dto.ScheduleVO;
import noticeframe.MessageFrame;
import request.Request;

public class MemoCalendar extends CalendarDataManager { // CalendarDataManager의
														// GUI + 메모기능 + 시계
														// 창 구성요소와 배치도
	JFrame mainFrame;
	ImageIcon icon = new ImageIcon("image/icon.png");

	JPanel calOpPanel; // GridBagLayout을 담을 매인 패널.
	JButton todayBut;
	JLabel todayLab;
	JButton lYearBut;
	JButton lMonBut;
	JLabel curMMYYYYLab;
	JButton nMonBut;
	JButton nYearBut;

	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
	// 달력 넘김버튼을 관리하는 버튼집합
	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateButs[][] = new JButton[6][7];
	listenForDateButs lForDateButs = new listenForDateButs();

	JPanel infoPanel;
	JLabel infoClock;

	JPanel memoPanel; // 일정메모 패널.
	JLabel selectedDate;
	JTextArea memoArea;
	JScrollPane memoAreaSP;
	JPanel memoSubPanel;
	JButton saveBut;
	JButton delBut;
	JButton modifyBut;

	private String writer;
	Map<String,ScheduleVO> scheduleMap;
	private Font f1;
	String groupId;

	JPanel frameBottomPanel; // 일정기록 하단에 메시지
	JLabel bottomInfo = new JLabel("Welcome to Memo Calendar!");
	final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	final String title = "일정";	
	final String SaveButMsg2 = "일정을 먼저 작성해 주세요.";
	final String SaveButMsg3 = "<html><font color=red>ERROR : 파일 쓰기 실패</html>";
	final String DelButMsg1 = "일정을 삭제하였습니다.";
	final String DelButMsg2 = "작성되지 않았거나 이미 삭제된 일정입니다.";
	final String DelButMsg3 = "<html><font color=red>ERROR : 파일 삭제 실패</html>";
	final String MdyButMsg1 = "일정을 입력합니다.";
	
	/*********************************************
	 * 생 성 자 *
	 *********************************************/

	public MemoCalendar(String writer, String groupId, Map<String,ScheduleVO> scheduleMap) {
		this.writer = writer;
		this.groupId = groupId;
		this.scheduleMap = scheduleMap;
		
		/*********************************************
		 * 달력 메인 창 *
		 *********************************************/

		mainFrame = new JFrame(title);		
		mainFrame.setSize(700, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setIconImage(icon.getImage());

		/*********************************************
		 * LookAndFeel *
		 *********************************************/
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(mainFrame);
//		} catch (Exception e) {
//			bottomInfo.setText("ERROR : LookAndFeel setting failed");
//		}

		/*********************************************
		 * 달력 넘김 버튼 *
		 *********************************************/
		calOpPanel = new JPanel();
		todayBut = new JButton("Today");
		todayBut.setToolTipText("Today");
		todayBut.addActionListener(lForCalOpButtons);
		todayLab = new JLabel(today.get(Calendar.MONTH) + 1 + "/" + today.get(Calendar.DAY_OF_MONTH) + "/"
				+ today.get(Calendar.YEAR));
		lYearBut = new JButton("<<");
		lYearBut.setToolTipText("Previous Year");
		lYearBut.addActionListener(lForCalOpButtons);
		lMonBut = new JButton("<");
		lMonBut.setToolTipText("Previous Month");
		lMonBut.addActionListener(lForCalOpButtons);
		curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
				+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
		nMonBut = new JButton(">");
		nMonBut.setToolTipText("Next Month");
		nMonBut.addActionListener(lForCalOpButtons);
		nYearBut = new JButton(">>");
		nYearBut.setToolTipText("Next Year");
		nYearBut.addActionListener(lForCalOpButtons);
		calOpPanel.setLayout(new GridBagLayout());
		GridBagConstraints calOpGC = new GridBagConstraints();

		/*********************************************
		 * 달력 메인 레이아웃(그리드백) 조정 *
		 *********************************************/
		calOpGC.gridx = 1;
		calOpGC.gridy = 1;
		calOpGC.gridwidth = 2;
		calOpGC.gridheight = 1;
		calOpGC.weightx = 1;
		calOpGC.weighty = 1;
		calOpGC.insets = new Insets(5, 5, 0, 0);
		calOpGC.anchor = GridBagConstraints.WEST;
		calOpGC.fill = GridBagConstraints.NONE;
		calOpPanel.add(todayBut, calOpGC);
		calOpGC.gridwidth = 3;
		calOpGC.gridx = 2;
		calOpGC.gridy = 1;
		calOpPanel.add(todayLab, calOpGC);
		calOpGC.anchor = GridBagConstraints.CENTER;
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 1;
		calOpGC.gridy = 2;
		calOpPanel.add(lYearBut, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 2;
		calOpGC.gridy = 2;
		calOpPanel.add(lMonBut, calOpGC);
		calOpGC.gridwidth = 2;
		calOpGC.gridx = 3;
		calOpGC.gridy = 2;
		calOpPanel.add(curMMYYYYLab, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 5;
		calOpGC.gridy = 2;
		calOpPanel.add(nMonBut, calOpGC);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 6;
		calOpGC.gridy = 2;
		calOpPanel.add(nYearBut, calOpGC);

		calPanel = new JPanel();
		weekDaysName = new JButton[7];
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if (i == 0)
				weekDaysName[i].setBackground(new Color(200, 50, 50));
			else if (i == 6)
				weekDaysName[i].setBackground(new Color(50, 100, 200));
			else
				weekDaysName[i].setBackground(new Color(150, 150, 150));
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			calPanel.add(weekDaysName[i]);
		}
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(lForDateButs);
				calPanel.add(dateButs[i][j]);
			}
		}
		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCal(); // 달력을 표시

		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoClock = new JLabel("", SwingConstants.RIGHT);
		infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		infoPanel.add(infoClock, BorderLayout.NORTH);
		selectedDate = new JLabel("<Html><font size=3>" + (today.get(Calendar.MONTH) + 1) + "/"
				+ today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.YEAR) + "&nbsp;(Today)</html>",
				SwingConstants.LEFT);
		selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		memoPanel = new JPanel();
		memoPanel.setBorder(BorderFactory.createTitledBorder("Memo"));
		memoArea = new JTextArea();
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);

		// ******************쓰기*****************/
		memoArea.setBackground(Color.WHITE);
		f1 = new Font("돋음", Font.PLAIN, 15);
		memoArea.setFont(f1);
		memoArea.setForeground(Color.BLUE);

		memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		memoSubPanel = new JPanel();

		/*********************************************
		 * 저장버튼 서버와 교류 *
		 *********************************************/
		saveBut = new JButton("저장");
		String mine = writer;
		String gId = groupId;
		saveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String memo = memoArea.getText();								
				if(!memo.isEmpty()) {
					// 보냄
					Request request = new Request("writeschedule");
					request.setParameter("groupid", gId);
					request.setParameter("writer", mine);
					request.setParameter("content", memo);
					request.setParameter("date", calYear + ((calMonth + 1) < 10 ? "0" : "") +(calMonth+1)
							+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon);												
					Thread thread = new Thread() {
			        	@Override
			        	public void run() { 
			        		// 저장 결과
							Request response = WebConnector.connect(request);
							Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
							if (errors == null || errors.isEmpty()) {
								new MessageFrame("성공적으로 저장하였습니다.", true);								
								Map<String,ScheduleVO> scheduleMap = (Map<String,ScheduleVO>)response.getAttribute("schedulemap");
								new MemoCalendar(mine, gId, scheduleMap);
								mainFrame.dispose();
							} else {
								new MessageFrame("서버와의 연결이 좋지 않습니다. 잠시 후 다시 연결해주세요.", false);						
							}	
			        	}
					};
					thread.start();						
				} else {
					bottomInfo.setText(SaveButMsg2);
				}
			}
		});

		/*********************************************
		 * 삭제버튼 서버와 교류 *
		 *********************************************/		
		delBut = new JButton("삭제");
		delBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memo = memoArea.getText();	
				if(memo.isEmpty())
					return;
				
				Request request = new Request("removeschedule");
				request.setParameter("date", ""+calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth+1) 
						+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon);
				
				int year = calYear,month = calMonth,day = calDayOfMon;
				Calendar tempCal = Calendar.getInstance();
				tempCal.set(calYear, calMonth,calDayOfMon);
				if(calDayOfMon == 
						tempCal.getActualMaximum(Calendar.DAY_OF_MONTH)) { //이달 마지막이면
					day = 1;					
					if(calMonth == 11) { 
						year = calYear+1;
					} else {						
						month = calMonth+1;
					}
				} else {
					day = calDayOfMon+1;
				}			
				request.setParameter("nextdate",""+year + ((month + 1) < 10 ? "0" : "") + (month+1) 
						+ (day < 10 ? "0" : "") + day);
				System.out.println("calYear"+calYear);
				System.out.println("calMonth"+calMonth);
				System.out.println("calDay"+calDayOfMon);
				request.setParameter("groupid", gId);
				request.setParameter("writer", mine);
				System.out.println(gId);
				System.out.println(mine);
				
				Thread thread = new Thread() {
					@Override
					public void run() {
						Request response = WebConnector.connect(request);
						Map<String, Boolean> errors = (Map<String, Boolean>) response.getAttribute("errors");
						if (errors == null || errors.isEmpty()) {
							new MessageFrame("성공적으로 삭제하였습니다.", true);								
							Map<String,ScheduleVO> scheduleMap = (Map<String,ScheduleVO>)response.getAttribute("schedulemap");
							new MemoCalendar(mine, gId, scheduleMap);
							mainFrame.dispose();
						} else {
							new MessageFrame("서버와의 연결이 좋지 않습니다. 잠시 후 다시 연결해주세요.", false);						
						}	
					}
				};
				thread.start();			
			}
		});

		/*********************************************
		 * 쓰기 버튼 *
		 *********************************************/

		modifyBut = new JButton("쓰기");
		modifyBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memoArea.setEnabled(true);
				memoArea.setBackground(Color.white);
				memoArea.setForeground(Color.BLACK);
				bottomInfo.setText(MdyButMsg1);
			}
		});

		/*********************************************
		 * 읽기 쓰기 삭제 버튼 *
		 *********************************************/
		memoSubPanel.add(saveBut);
		memoSubPanel.add(delBut);
		memoSubPanel.add(modifyBut);

		memoPanel.setLayout(new BorderLayout());
		memoPanel.add(selectedDate, BorderLayout.NORTH);
		memoPanel.add(memoAreaSP, BorderLayout.CENTER);
		memoPanel.add(memoSubPanel, BorderLayout.SOUTH);

		// calOpPanel, calPanel을 frameSubPanelWest에 배치
		JPanel frameSubPanelWest = new JPanel();
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 90;
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel, BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel, BorderLayout.CENTER);

		// infoPanel, memoPanel을 frameSubPanelEast에 배치
		JPanel frameSubPanelEast = new JPanel();
		Dimension infoPanelSize = infoPanel.getPreferredSize();
		infoPanelSize.height = 65;
		infoPanel.setPreferredSize(infoPanelSize);
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(infoPanel, BorderLayout.NORTH);
		frameSubPanelEast.add(memoPanel, BorderLayout.CENTER);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 410;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

		// 뒤늦게 추가된 bottom Panel..
		frameBottomPanel = new JPanel();
		frameBottomPanel.add(bottomInfo);

		// frame에 전부 배치
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
		mainFrame.add(frameSubPanelEast, BorderLayout.CENTER);
		mainFrame.add(frameBottomPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		focusToday(); // 현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)

		// Thread 작동(시계, bottomMsg 일정시간후 삭제)
		ThreadConrol threadCnl = new ThreadConrol();
		threadCnl.start();
		
		
	}

	/*********************************************
	 * 생 성 자 끝 *
	 *********************************************/

	
	
	private void focusToday() {
		if (today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	/*********************************************
	 * 파일 유무에 따라 버튼 굵기 *
	 *********************************************/
	private void readMemo() {	
		String date = calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth+1)
				+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon;
		ScheduleVO schedule = scheduleMap.get(date);
		if(schedule == null) {			
			memoArea.setText("");
		} else {
			memoArea.setText("");
			memoArea.append(schedule.getContent());
		}
	}

	/*********************************************
	 * 달력 날짜 클릭시 버튼화 *
	 *********************************************/
	private void showCal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0)
					fontColor = "red";
				else if (j == 6)
					fontColor = "blue";
											
				String date= calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth+1)
							+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j];				
				if (scheduleMap.get(date)==null) {
					dateButs[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else
					dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
								
				if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
						&& calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}

				if (calDates[i][j] == 0)
					dateButs[i][j].setVisible(false);
				else
					dateButs[i][j].setVisible(true);

			}
		}
	}

	/*********************************************
	 * 월 넘김 버튼 설정 *
	 *********************************************/
	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == todayBut) {
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
			} else if (e.getSource() == lYearBut)
				moveMonth(-12);
			else if (e.getSource() == lMonBut)
				moveMonth(-1);
			else if (e.getSource() == nMonBut)
				moveMonth(1);
			else if (e.getSource() == nYearBut)
				moveMonth(12);

			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
					+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
			showCal();
		}
	}

	/*********************************************
	 * 날짜버튼 눌렀을때 일정메모 주소설정 *
	 *********************************************/
	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateButs[i][j]) {
						k = i;
						l = j;
						memoArea.setEnabled(false);
						memoArea.setBackground(Color.WHITE);
					}
				}
			}
			if (!(k == 0 && l == 0))
				calDayOfMon = calDates[k][l]; // today버튼을 눌렀을때도 이
												// actionPerformed함수가 실행되기 때문에
												// 넣은 부분

			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);

			String dDayString = new String(); // DDAY 설정
			int dDay = ((int) ((cal.getTimeInMillis() - today.getTimeInMillis()) / 1000 / 60 / 60 / 24));
			if (dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR))
					&& (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)))
				dDayString = "Today";
			else if (dDay >= 0)
				dDayString = "D-" + (dDay + 1);
			else if (dDay < 0)
				dDayString = "D+" + (dDay) * (-1);

			selectedDate.setText("<Html><font size=3>" + (calMonth + 1) + "/" + calDayOfMon + "/" + calYear + "&nbsp;("
					+ dDayString + ")</html>");

			readMemo();
		}
	}

	/*********************************************
	 * 오른쪽 상단 시계 *
	 *********************************************/
	private class ThreadConrol extends Thread {
		public void run() {
			boolean msgCntFlag = false;
			int num = 0;
			String curStr = new String();
			while (true) {
				try {
					today = Calendar.getInstance();
					String amPm = (today.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
					String hour;
					if (today.get(Calendar.HOUR) == 0)
						hour = "12";
					else if (today.get(Calendar.HOUR) == 12)
						hour = " 0";
					else
						hour = (today.get(Calendar.HOUR) < 10 ? " " : "") + today.get(Calendar.HOUR);
					String min = (today.get(Calendar.MINUTE) < 10 ? "0" : "") + today.get(Calendar.MINUTE);
					String sec = (today.get(Calendar.SECOND) < 10 ? "0" : "") + today.get(Calendar.SECOND);
					infoClock.setText(amPm + " " + hour + ":" + min + ":" + sec);

					sleep(1000);
					String infoStr = bottomInfo.getText();

					if (infoStr != " " && (msgCntFlag == false || curStr != infoStr)) {
						num = 5;
						msgCntFlag = true;
						curStr = infoStr;
					} else if (infoStr != " " && msgCntFlag == true) {
						if (num > 0)
							num--;
						else {
							msgCntFlag = false;
							bottomInfo.setText(" ");
						}
					}
				} catch (InterruptedException e) {
					System.out.println("Thread:Error");
				}
			}
		}
	}
}