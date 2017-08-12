package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dto.EmployeeVO;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 	
	//get
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="login.jsp";		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")!=null) { //로그인 된 경우
			url = "main.jsp";
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}
	//post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="login.jsp";
		String id = request.getParameter("id");
		String password = request.getParameter("password");	
		String lev = request.getParameter("lev");
		
		EmployeeDAO employeeDao = EmployeeDAO.getInstance();		
		int result = employeeDao.userCheck(id,password,lev);		
		if(result == 1) { //비번 일치
			EmployeeVO employee = employeeDao.getEmployee(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",employee);			
			url = "main.jsp";		
		} else if(result == -1) { //비번 불일치
			request.setAttribute("message","비밀번호가 맞지 않습니다.");
		} else if(result == 0) { //존재하지 않는 아이디
			request.setAttribute("message","존재하지 않는 회원입니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	}
}
