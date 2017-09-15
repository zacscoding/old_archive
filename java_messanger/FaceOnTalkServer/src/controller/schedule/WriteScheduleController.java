package controller.schedule;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.ScheduleDao;
import dto.ScheduleVO;
import request.Request;
import util.UserGroupParser;

/*
 * 스케줄 쓰기 컨트롤러 클래스
 */
public class WriteScheduleController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		ScheduleDao scheduleDao = ScheduleDao.getInstance();		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);				
		try{			
			String groupId = UserGroupParser.getUserStringSort(request.getParameter("groupid"));			
			String dDay = request.getParameter("date");
			int year = Integer.parseInt(dDay.substring(0,4));
			int month = Integer.parseInt(dDay.substring(4,6));
			int date = Integer.parseInt(dDay.substring(6));
			Calendar cal = Calendar.getInstance();
			cal.set(year, month-1, date);
			int result = scheduleDao.insertSchedule(new ScheduleVO(
					groupId
					,Integer.parseInt(request.getParameter("writer"))
					,request.getParameter("content")
					,new Date()
					,cal.getTime()
					));	
			if(result>0) {
				Map<String,ScheduleVO> scheduleMap = scheduleDao.selectByGroupId(groupId);
				response.setAttribute("schedulemap",scheduleMap);				
			} else {
				errors.put("failed",Boolean.TRUE);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception");
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;	
	}	
}
