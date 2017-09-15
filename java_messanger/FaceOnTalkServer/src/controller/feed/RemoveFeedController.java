package controller.feed;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.FeedDao;
import dto.FeedVO;
import request.Request;

/*
 * 글삭제 요청 컨트롤 클래스
 */
public class RemoveFeedController implements WebCommandController {
	@Override
	public Request execute(Request request) {
		FeedDao feedDao = FeedDao.getInstance();
		Request response = new Request("removefeed");
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);			
		try{
			int feedNumber = Integer.parseInt(request.getParameter("feed_no"));
			FeedVO feed = feedDao.selectByFeedNumber(feedNumber);
			if(feed == null) {
				errors.put("cantfindfeed",Boolean.TRUE);
				return response;
			} 		
			
			if(!canRemove(request.getParameter("id"),feed)) {
				errors.put("permissiondenied",Boolean.TRUE);
				return response;
			}
			
			if(!(feedDao.delete(feedNumber)>0))
				errors.put("failed",Boolean.TRUE);			
		}catch(SQLException e) {
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}	
	
	//요청id와 글쓴이id를 비교 
	private boolean canRemove(String idVal,FeedVO feed) {
		int modifingId = Integer.parseInt(idVal);
		return (feed.getWriter().getId() == modifingId);
	}	
}
