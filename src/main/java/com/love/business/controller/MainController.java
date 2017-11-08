package com.love.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.love.business.entity.Admin;
import com.love.business.service.IAdminService;
import com.love.business.util.AESUtil;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private IAdminService adminService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(String username,String password) {
		ModelAndView mv = new ModelAndView();
		Admin admin = adminService.query(username);
		if(admin == null) {
			logger.error("admin can not be null");
			return null;
		}
		logger.debug(admin.getUsername());
		String admin_pwd = admin.getPassword();
		try {
			String jmpwd = AESUtil.encrypt(password, "ldgx", null);
			if(admin_pwd != null && admin_pwd.equals(jmpwd)) {
				mv.setViewName("main_index");
				return mv;
			}
		} catch (Exception e) {
			logger.error("e", e);
			System.out.println("exception dddd");
			e.printStackTrace();
		}
		System.out.println("last last last");
		return null;
	}
}
