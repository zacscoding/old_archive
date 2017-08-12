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

@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	//get
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productWrite.jsp");
		dispatcher.forward(request, response);
	}
   	
   	//post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("/upload");
		System.out.println("path : "+path);
		String encType = "UTF-8";
		int sizeLimit = 20*1024*1024;		
		MultipartRequest multi = new MultipartRequest(request,path,sizeLimit,encType,new DefaultFileRenamePolicy());		
		ProductVO product = new ProductVO(
				0,
				multi.getParameter("name"),
				Integer.parseInt(multi.getParameter("price")),
				multi.getParameter("description"),
				multi.getFilesystemName("pictureUrl"));
		
		
		ProductDAO productDao = ProductDAO.getInstance();
			
		productDao.insertProduct(product);
		response.sendRedirect("productList.do");		
	}
}













