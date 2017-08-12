package com.faceonshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceonshop.dao.ProductDAO;
import com.faceonshop.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code = Integer.parseInt(request.getParameter("code"));
		ProductDAO productDao = ProductDAO.getInstance();
		ProductVO product = productDao.selectProductByCode(code);
		request.setAttribute("product",product);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productUpdate.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(request,path,sizeLimit,encType,new DefaultFileRenamePolicy());
		
		String pictureUrl = multi.getParameter("pictureUrl");
		if(pictureUrl==null)
			pictureUrl = multi.getParameter("nonmakeImg");
		
		ProductVO product = new ProductVO(
				Integer.parseInt(multi.getParameter("code")),
				multi.getParameter("name"),
				Integer.parseInt(multi.getParameter("price")),
				multi.getParameter("description"),
				pictureUrl);
		
		ProductDAO productDao = ProductDAO.getInstance();
		productDao.updateProduct(product);		
		response.sendRedirect("productList.do");
	}
}









