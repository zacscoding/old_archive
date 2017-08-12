package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler{
	private ReadArticleService readService=new ReadArticleService();	
	
	@Override
	public String process(HttpServletRequest req,HttpServletResponse res) throws Exception{
		String noVal=req.getParameter("no");
		int articleNum=Integer.parseInt(noVal);
		
		try{
			ArticleData articleData=readService.getArticle(articleNum, true); //articleNum이 존재하지 않으면 예외 발생
			req.setAttribute("articleData",articleData); // request의 articleData 속성에 게시글 데이터를 저장.
			return "/WEB-INF/view/readArticle.jsp";			
		}catch(ArticleNotFoundException | ArticleContentNotFoundException e){ 
			req.getServletContext().log("no article",e); //예외발생하면 로그 메시지 기록
			res.sendError(HttpServletResponse.SC_NOT_FOUND); //404 에러 코드 전송
			return null;
		}		
	}
}












