package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler{
	private static final String FORM_VIEW="/WEB-INF/view/changePwdForm.jsp";
	private static final String PWD_CHANGE_SUCCESS="/WEB-INF/view/changePwdSuccess.jsp";
	private ChangePasswordService changePwdSvc=new ChangePasswordService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")){ //GET 요청시
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){ //POST 요청시
			return processSubmit(req,res);
		}else{
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req,HttpServletResponse res){
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req,HttpServletResponse res) throws Exception{
		User user=(User)req.getSession().getAttribute("authUser"); //LoginHandler에서 로그인 성공시 세션에 authUser 속성에 사용자 정보를 저장
		
		Map<String,Boolean> errors=new HashMap<>();
		req.setAttribute("errors",errors);
		
		String curPwd=req.getParameter("curPwd");
		String newPwd=req.getParameter("newPwd");
		
		//파라미터가 없는 경우 errors 맵 객체에 에러 코드 추가
		if(curPwd==null || curPwd.isEmpty())
			errors.put("curPwd",Boolean.TRUE);
		
		if(newPwd==null || newPwd.isEmpty())
			errors.put("newPwd",Boolean.TRUE);
		
		//에러가 존재하면 폼을 위한 뷰 경로를 리턴
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		
		try{
			changePwdSvc.changePassword(user.getId(),curPwd, newPwd);
			return PWD_CHANGE_SUCCESS; //암호 변경 성공시 changePwdSuccess.jsp로 이동
		}catch(InvalidPasswordException e){ //현재 암호(curPwd)가 올바르지 않아 익센셥이 발생하면 
			errors.put("badCurPwd",Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException e){ //암호를 변경할 회원 아이디가 존재하지 않아 익셉션이 발생
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return FORM_VIEW;			
		}		
	}
}



















