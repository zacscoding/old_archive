package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/newArticleForm.jsp";	
	private WriteArticleService writeService=new WriteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req,res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}		
	}
	
	private String processForm(HttpServletRequest req,HttpServletResponse res){
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req,HttpServletResponse res){
		Map<String,Boolean> errors=new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user=(User)req.getSession(false).getAttribute("authUser"); //세션에서 로그인한 사용자 정보를 구하기
		WriteRequest writeReq=createWriteRequest(user,req); //user와 HttpServletRequest 를 이용해서 WriteRequest 객체를 생성
		writeReq.validate(errors); //writeReq의 유효성 검사
		
		if(!errors.isEmpty()) //예외가 존재하면 폼을 다시 보여줌
			return FORM_VIEW; 
		
		int newArticleNo=writeService.write(writeReq); //WriteArticleService를 이용해서 게시글 등록 & 등록된 게시글의 ID를 리턴 받음
		req.setAttribute("newArticleNo",newArticleNo); //새 글의 ID를 request의 newArticleId 속성에 저장.
		
		return "/WEB-INF/view/newArticleSuccess.jsp";		
	}
	
	private WriteRequest createWriteRequest(User user,HttpServletRequest req){
		return new WriteRequest( 
				new Writer(user.getId(),user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
}
