package controller.schedule;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.ScheduleDao;
import dto.ScheduleVO;
import request.Request;

/*
 * 스케줄 삭제 컨트롤러 클래스
 */
public class RemoveScheduleController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		ScheduleDao scheduleDao = ScheduleDao.getInstance();		
		Request response = new Request(request.getType());		
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);				
		try{		
			String groupId = request.getParameter("groupid");
			int result = scheduleDao.deleteSchedule(
									groupId
									,request.getParameter("date")
									,request.getParameter("nextdate") 
									);
			if(result>0) {
				System.out.println("삭제 성공");
				Map<String,ScheduleVO> scheduleMap = scheduleDao.selectByGroupId(groupId);
				response.setAttribute("schedulemap",scheduleMap);
			} else {
				System.out.println("삭제 실패");
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
