package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dto.EmployeeVO;

@WebServlet("/custom.do")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="login.jsp";		
		HttpSession session = request.getSession();
		EmployeeVO admin = (EmployeeVO)session.getAttribute("loginUser");		
		if(admin!=null && admin.getLev().equals("A")) { //로그인 && 관리자
			url = "insertEmployeeForm.jsp";
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "insertEmployeeForm.jsp";
		EmployeeVO emp = new EmployeeVO(
				request.getParameter("id"),
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("lev"),
				new Date(),
				request.getParameter("gender"),
				request.getParameter("phone"));
		EmployeeDAO employeeDao = EmployeeDAO.getInstance();
		int result = employeeDao.insertEmployee(emp);
		if(result>0) {
			url = "insertEmployee.jsp";
			request.setAttribute("employee",emp);
		} else {
			request.setAttribute("message","저장에 실패하였습니다.");
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);	
	}
}
