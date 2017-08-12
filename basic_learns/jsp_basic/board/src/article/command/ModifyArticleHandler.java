package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/modifyForm.jsp";
	
	private ReadArticleService readService=new ReadArticleService();
	private ModifyArticleService modifyService=new ModifyArticleService();
	
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
	
	//폼 요청 처리(GET)
	private String processForm(HttpServletRequest req,HttpServletResponse res) throws IOException {
		try{
			String noVal=req.getParameter("no");
			int no=Integer.parseInt(noVal);
			ArticleData articleData=readService.getArticle(no, false); //폼에 보여줄 게시글을 구함
			User authUser=(User)req.getSession().getAttribute("authUser"); //로그인한 사용자 정보를 구함
			
			if(!canModify(authUser,articleData)){ //로그인 사용자 != 게시글 작성자 => 403응답(금지 의미)를 전송
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest modReq=new ModifyRequest(authUser.getId(),no, //폼에 데이터를 보여줄 때 사용할 인스턴스 생성
					articleData.getArticle().getTitle(),
					articleData.getContent());
			req.setAttribute("modReq",modReq); //request의 modReq 속성에 저장
			return FORM_VIEW; //폼을 위한 뷰를 리턴
		}catch(ArticleNotFoundException e){
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}		
	}
	
	//폼 전송 처리(POST)
	private String processSubmit(HttpServletRequest req,HttpServletResponse res) throws Exception{
		User authUser=(User)req.getSession().getAttribute("authUser"); //게시글 수정을 요청한 사용자 정보를 구함
		String noVal=req.getParameter("no");		
		int no=Integer.parseInt(noVal);
		
		ModifyRequest modReq=new ModifyRequest(authUser.getId(),no, //요청 파라미터와 현재 사용자 정보를 이용해서 ModifyRequest 인스턴스 생성
				req.getParameter("title"),
				req.getParameter("content"));
		
		req.setAttribute("modReq",modReq); //ModifyRequest 인스턴스를 request의 modReq 속성에 저장
		
		Map<String,Boolean>  errors=new HashMap<>(); // 에러 정보를 담을 맵 인스턴스 생성
		req.setAttribute("errors",errors);
		modReq.validate(errors); //modReq가 유효한지 검사
		
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		try{
			modifyService.modify(modReq); //게시글 수정 기능 수행
			return "/WEB-INF/view/modifySuccess.jsp"; //수정 성공시 보여줄 뷰			
		}catch(ArticleNotFoundException e){ 
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e){
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
	
	//글 작성자 == 로그인 한 사용자 검사 여부
	private boolean canModify(User authUser,ArticleData articleData){
		String writerId=articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
}















