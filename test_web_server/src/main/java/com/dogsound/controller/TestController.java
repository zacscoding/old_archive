package com.dogsound.controller;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogsound.config.GeneralConfig;
import com.dogsound.service.TestService;
import com.dogsound.util.DBDataTypeUtil;

@Controller
public class TestController {
private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	private static final String REDIRECT_PATH = "redirect:/crudtest";
	
	@Inject
	private TestService service;
		
	// MAIN VIEW
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {		
		model.addAttribute("dbProfile",GeneralConfig.ACTIVE_DB_PROFILE.substring(3));
		return "index";
	}
	
	@RequestMapping("redirect_test")
	public String redirect() {
		return "redirect:/crudtest";
	}
	
	@RequestMapping("add_session")
	public String addSession(HttpServletRequest req) {
		req.getProtocol();
		req.getRemoteHost();
		req.getSession().getId();
		
		req.getSession().setAttribute("testSession", "test");
		return REDIRECT_PATH;
	}
		
	// CRUD VIEW	
	@RequestMapping(value="/crudtest",method=RequestMethod.GET)
	public String view(Model model) {
		model.addAttribute("dataList", DBDataTypeUtil.getDataList());
		model.addAttribute("dbProfile",GeneralConfig.ACTIVE_DB_PROFILE.substring(3));
		return "test";
	}
			
	// INSERT
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(String count, String[] datas, RedirectAttributes  rttr) throws Exception {
		logger.info(count);
		
		int appliedRecords = service.insert(count, datas);
		
		rttr.addFlashAttribute("message", "success to insert, applied records : " + appliedRecords);
		
		return REDIRECT_PATH;
	}
	
	// UPDATE
	@RequestMapping(value="/updateAll", method=RequestMethod.POST)
	public String update(RedirectAttributes rttr) throws Exception {
		logger.info("update all");
		
		int appliedRecords = service.updateAll();
		
		rttr.addFlashAttribute("message", "success to update, applied records : " + appliedRecords);
		
		return REDIRECT_PATH;
	}	
	
	// DELETE
	@RequestMapping(value="/deleteAll", method=RequestMethod.POST)
	public String deleteAll(RedirectAttributes rttr) throws Exception {
		logger.info("delete all");
				
		int appliedRecords = service.deleteAll();
		
		rttr.addFlashAttribute("message", "success to remove all , applied records : " + appliedRecords);
		
		return REDIRECT_PATH;
	}
	
	// SELECT
	@RequestMapping(value="/selectAll", method=RequestMethod.POST)
	public String selectAll(RedirectAttributes rttr) throws Exception {
		logger.info("select all");
		
		int appliedRecords = service.selectAll();
		
		rttr.addFlashAttribute("message", "records count : " + appliedRecords);		
		return REDIRECT_PATH;
	}
	
	
	// query
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public String query(String query , RedirectAttributes rttr) throws Exception {
		logger.info("query : " + query);
		String message = null;
		try {
			int appliedRecords = service.executeQuery(query);
			message = "records count : " + appliedRecords;
		} catch(UnsupportedOperationException e) {
			message = e.getMessage();
		} catch(Exception e) {
			message = "query errors<br/>" + e.getMessage();
			logger.error("query errors",e);
		}		
		
		rttr.addFlashAttribute("message", message);		
		return REDIRECT_PATH;
	}
}
