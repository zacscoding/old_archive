package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarDataManager{ 
		public static final int CAL_WIDTH = 7;
		public final static int CAL_HEIGHT = 6;
		public int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
		public int calYear;
		public int calMonth;
		public int calDayOfMon;
		public final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
		public int calLastDate;
		public Calendar today = Calendar.getInstance();
		public Calendar cal;
				
		public CalendarDataManager(){ 
			setToday(); 
		}
		public void setToday(){                                                               //오늘 날짜
			calYear = today.get(Calendar.YEAR); 
			calMonth = today.get(Calendar.MONTH);
			calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
			makeCalData(today);
		}
		
		
		private void makeCalData(Calendar cal){                                        // 1일의 위치와 마지막 날짜를 구함		 
			int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7- (cal.get(Calendar.DAY_OF_MONTH))%7)%7;
			if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
			else calLastDate = calLastDateOfMonth[calMonth];                      
			                                                                                          
			for(int i = 0 ; i<CAL_HEIGHT ; i++){                                        // 달력 배열 초기화.. 하지 않으면 월이 바뀔때마다 남아있는 숫자가 생긴다.
				for(int j = 0 ; j<CAL_WIDTH ; j++){
					calDates[i][j] = 0;
				}
			}
			
			//*********** 달력 배열에 값 채워넣기***************
			
			for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
				if(i == 0) k = calStartingPos;
				else k = 0;
				for(int j = k ; j<CAL_WIDTH ; j++){
					if(num <= calLastDate) calDates[i][j]=num++;
				}
			}
		}
		
		
		
		//*****************윤년인지*********************
		private int leapCheck(int year){ 
			if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
			else return 0;
		}
		public void moveMonth(int mon){                                               // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
			calMonth += mon;
			if(calMonth>11) while(calMonth>11){
				calYear++;
				calMonth -= 12;
			} else if (calMonth<0) while(calMonth<0){
				calYear--;
				calMonth += 12;
			}
			cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);   //전세계 공통으로 사용하고 있는 그레고리력.
			makeCalData(cal);
		}
	}//class CalendarDataManager


