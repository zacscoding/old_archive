package com.faceontalk.mvc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.mvc.controller.CommandHandler;
import com.faceontalk.mvc.controller.NullHandler;

public class ControllerUsingURI extends HttpServlet {
	//key : ??.do , value : Controller 클래스 
	private Map<String,CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		String configFile = getInitParameter("configFile"); //web.xml에 initParam 중 configFile
		System.out.println("configFile : "+configFile);
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath("configFile"); //실제 경로		
		try (FileReader fr = new FileReader(configFilePath)) {
			prop.load(fr); //prop에 로드
		} catch(IOException e) {
			throw new ServletException(e);
		}		
		Iterator keyIter = prop.keySet().iterator(); //XXX.do
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next(); //XXX.do
			String handlerClassName = prop.getProperty(command); //com.~~~.~Handler\
			System.out.println("command : "+command+",class : "+handlerClassName);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler inst = (CommandHandler)handlerClass.newInstance();
				commandHandlerMap.put(command, inst);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	@Override	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}
	
	@Override	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}
	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String command = req.getRequestURI(); //URI전체
		if(command.indexOf(req.getContextPath()) == 0) { //contextPath포함하면
			command = command.substring(req.getContextPath().length()); // XXX.do만 꺼냄
		}		
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) 
			handler = new NullHandler();
		String viewPage = null;
		try {
			viewPage = handler.process(req, res);
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		if(viewPage != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
	        dispatcher.forward(req, res);
		}
	}
}
