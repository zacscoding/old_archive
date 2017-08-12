package controller.feed;

import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import controller.WebCommandController;
import dao.FeedDao;
import dto.FeedVO;
import dto.WriterVO;
import request.Request;

/*
 * 글쓰기 요청 처리 컨트롤 클래스
 */
public class WriteFeedController implements WebCommandController {
	@Override
	public Request execute(Request request) {		
		FeedDao feedDao = FeedDao.getInstance();
		Request response = new Request(request.getType());
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);		
		try {
			FeedVO feed = toFeed(request);
			if(!(feedDao.insert(feed)>0)) {
				errors.put("failed",Boolean.TRUE);
			}
		}catch(SQLException e) {
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}	
	
	//Request -> Feed 인스턴스생성
	private FeedVO toFeed(Request request) { 		
		Date now = new Date();		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("쓰기 요청 id : "+id);
		return new FeedVO(new WriterVO(id,request.getParameter("name"),request.getParameter("email"))
						,request.getParameter("content")
						,now,now,0);		
	}	
}
















