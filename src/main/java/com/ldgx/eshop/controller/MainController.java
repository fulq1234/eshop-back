package com.ldgx.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.service.IAdminService;

/**
 * 
 * @author fu
 *
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private IAdminService adminService;
	
	/**
	 * 访问路径  <项目名>/main/index
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView goindex() {
		ModelAndView mv = new ModelAndView();
		//页面
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(Admin admin) {
		ModelAndView mv = new ModelAndView();
		
		Admin dbadmin = adminService.login(admin);
		if(dbadmin == null) {//登录失败
			mv.setViewName("error");
			return mv;
		}else {//登录成功
			mv.setViewName("main_index");
			return mv;
		}
	}
}
