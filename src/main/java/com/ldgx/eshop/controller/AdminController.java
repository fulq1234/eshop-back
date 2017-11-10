package com.ldgx.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<Admin> list() {
		List<Admin> list = adminService.list(null);
		return list;
	}
}
