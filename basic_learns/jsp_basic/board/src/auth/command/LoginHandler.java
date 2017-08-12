package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler{
	
	private static final String FORM_VIEW="/WEB-INF/view/loginForm.jsp";
	private LoginService loginService=new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)	throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET"))
			return processForm(req,res);
		else if(req.getMethod().equalsIgnoreCase("POST"))
			return processSubmit(req,res);
		else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}			
	}
	
	private String processForm(HttpServletRequest req,HttpServletResponse res){
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req,HttpServletResponse res) throws Exception{
		//폼에서 전송한 id와 password 파라미터의 값 구하기
		String id=trim(req.getParameter("id"));
		String password=trim(req.getParameter("password"));
		
		//에러 정보를 담을 맵 객체를 생성하고 errors 속성에 저장
		Map<String,Boolean> errors=new HashMap<>();
		req.setAttribute("errors",errors);
		
		//id나 password가 없을 경우 에러 추가
		if(id==null || id.isEmpty())
			errors.put("id",Boolean.TRUE);
		
		if(password==null || password.isEmpty())
			errors.put("password",Boolean.TRUE);
		
		//에러가 존재하면 폼 뷰를 리턴
		if(!errors.isEmpty())
			return FORM_VIEW;
						
		//
		try{
			User user=loginService.login(id, password); // 로긴에 성공시 User 객체를 리턴
			req.getSession().setAttribute("authUser",user); //User 객체를 세션의 authUser 속성에 저장
			res.sendRedirect(req.getContextPath()+"/index.jsp"); // /index.jsp로 리다이렉트 			
			return null;			
		}catch(LoginFailException e){ // 로긴 실패시 LoginFailException 발생
			errors.put("idOrPwNotMatch",Boolean.TRUE); //에러 추가
			return FORM_VIEW; //폼을 위한 뷰 리턴
		}		
	}
	
	private String trim(String str){
		return str==null?null:str.trim();
	}
}












