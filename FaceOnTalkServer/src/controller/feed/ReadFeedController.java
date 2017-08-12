package controller.feed;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import controller.WebCommandController;
import dao.FeedDao;
import dto.FeedPage;
import dto.FeedVO;
import request.Request;

/*
 * feed 페이지를 요청하면 처리하는 컨트롤 클래스
 */
public class ReadFeedController implements WebCommandController{
	private static final int DEFAULT_SIZE = 10;
	@Override
	public Request execute(Request request) {		
		FeedDao feedDao = FeedDao.getInstance();
		Request response = new Request(request.getType());				
		Map<String,Boolean> errors = new Hashtable<>();
		response.setAttribute("errors",errors);
		
		////////////////////////////
		//요청 아이디 & 요청 페이지 & 보여줄 개수 parameter 얻기
		////////////////////////////
		//요청아이디
		int id = Integer.parseInt(request.getParameter("id"));
		
		//요청 페이지
		String pageVal = request.getParameter("pageNo");
		int pageNo = 1;
		if(pageVal!=null){
			pageNo = Integer.parseInt(pageVal);
		}		
		//보여줄 개수
		String sizeVal = request.getParameter("listsize");		
		int listSize = DEFAULT_SIZE;
		if(sizeVal!=null)
			listSize = Integer.parseInt(sizeVal);			
		try {
			String readType = request.getParameter("readType");
			System.out.println("피드 요청 타입"+readType);
			int total = 0;
			List<FeedVO> feedList = null;
			
			if(readType.equals("all")) { //친구 + 본인 피드
				total = feedDao.selectAllCount(id);				
				feedList = feedDao.select(id,(pageNo-1)*listSize+1, listSize);
			} else if(readType.equals("admin")) { //본인 피드
				total = feedDao.selectCount(id);
				System.out.println("total  : "+total);
				feedList = feedDao.selectOnlyUser(id, (pageNo-1)*listSize+1,listSize);			
			}			
			//전체 게시글 구하기					
			response.setAttribute("feedPage",new FeedPage(total,pageNo,listSize,feedList));						
		}catch(SQLException e) { 
			e.printStackTrace();
			errors.put("SQLException",Boolean.TRUE);
		}
		return response;
	}
}
























