package controller.schedule;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.ScheduleDao;
import dto.ScheduleVO;
import request.Request;

/*
 * 스케줄 읽기 컨트롤러 클래스
 */
public class ReadScheduleController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		ScheduleDao scheduleDao = ScheduleDao.getInstance();				
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);				
		try{
			String groupId = request.getParameter("groupid");			
			Map<String,ScheduleVO> scheduleMap = scheduleDao.selectByGroupId(groupId);
			response.setAttribute("schedulemap",scheduleMap);
			if(scheduleMap.isEmpty()) {
				scheduleMap = null;
			} else {
				response.setAttribute("schedulemap",scheduleMap);
			}								
		}catch(SQLException e) {
			System.out.println("SQL Exception");
			errors.put("SQLException",Boolean.TRUE);
			e.printStackTrace();
		}
		return response;	
	}	
}

