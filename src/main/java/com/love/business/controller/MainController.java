package com.love.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.love.business.entity.Admin;
import com.love.business.service.IAdminService;
import com.love.business.util.AESUtil;

@Controller
@RequestMapping("/main")
public class MainController {
	
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
		try {
			String jmpwd = AESUtil.encrypt(admin.getPassword(), "abc", null);
			if(password != null && password.equals(jmpwd)) {
				mv.setViewName("main");
				return mv;
			}
		} catch (Exception e) {
			logger.error("e", e);
			e.printStackTrace();
		}
		return null;
	}
}
