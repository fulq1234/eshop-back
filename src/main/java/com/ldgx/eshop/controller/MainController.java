package com.ldgx.eshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.service.IAdminService;

/**
 * 
 * @author fu
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private IAdminService adminService;
	
	/**
	 * 访问路径  <项目名>/main/index
	 * @return
	 */
	@RequestMapping("/")
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
			
			//保存到session中
			HttpSession session = this.saveAdminAtSession();	

	        session.setAttribute("currentAdmin", admin);
	        
			mv.setViewName("main_index");
			return mv;
		}
	}
	
	/**
	 * 把admin保存到session中
	 * @param admin
	 */
	private HttpSession saveAdminAtSession() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
       //request
        HttpServletRequest request = sra.getRequest();
        
        //session
        HttpSession session = request.getSession();
        return session;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView();
		
		//清空session内容
		HttpSession session = this.saveAdminAtSession();
		session.removeAttribute("currentAdmin");
		//页面
		mv.setViewName("login");
		return mv;
	}
	
	/**
	 * 跳转到错误页码
	 * @return
	 */
	@RequestMapping("/error")
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView();
		
		//清空session内容
		HttpSession session = this.saveAdminAtSession();
		session.removeAttribute("currentAdmin");
		//页面
		mv.setViewName("error");
		return mv;
	}
}
