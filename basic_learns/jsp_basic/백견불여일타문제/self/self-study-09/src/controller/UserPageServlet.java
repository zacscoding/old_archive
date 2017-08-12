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

@WebServlet("/mypage.do")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="login.jsp";
		
		HttpSession session = request.getSession();		
		if(session.getAttribute("loginUser") != null) {
			url = "mypage.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage.jsp";
		EmployeeVO employee = (EmployeeVO) (request.getSession().getAttribute("loginUser"));
		request.setCharacterEncoding("utf-8");		
		employee.setLev(request.getParameter("lev"));
		employee.setPhone(request.getParameter("phone"));
		employee.setPassword(request.getParameter("password"));
		
		EmployeeDAO employeeDao = EmployeeDAO.getInstance();
		int result = employeeDao.updateEmployee(employee);
		
		if(result==1) {
			url="update_success.jsp";
			request.setAttribute("message", "회원 정보가 수정되었습니다.");
		} else {
			request.setAttribute("message", "수정에 실패하였습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}
}
