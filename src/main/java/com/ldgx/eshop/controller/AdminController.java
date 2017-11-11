package com.ldgx.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.entity.PageBean;
import com.ldgx.eshop.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends GridController{
	
	@Autowired
	private IAdminService adminService;
	
	
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	/**
	 * 分页显示
	 * 数据格式举例
sidePagination: "server"，对应的json格式必须为：
{
   "total":20,
   "rows":[
        {
          "id":1,
          "name":"张三",
          "age":22
        },
       ...
    ]
}
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public PageBean<Admin> list(String username) {
		PageBean<Admin> list = adminService.list(username,this.getLimit(),this.getOffset());		
		return list;
	}
}
