package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException{
		HttpServletRequest request=(HttpServletRequest)req;
		HttpSession session=request.getSession();
		
		if(session==null || session.getAttribute("authUser")==null){ //세션이 존재하지 않거나 authUser 속성이 없으면 login.do 로 리다이렉트
			HttpServletResponse response=(HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do");
		}else{ //세션에 "authUser" 속성이 존재하면 로그인한 것으로 판단하고 기능을 실행
			chain.doFilter(req, res);
		}		
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		
	}
	
	@Override
	public void destroy(){
		
	}
}
