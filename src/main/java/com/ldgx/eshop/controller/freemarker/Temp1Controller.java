package com.ldgx.eshop.controller.freemarker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm/temp1")
public class Temp1Controller {
	
	@RequestMapping("/space")
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		
		//1.基本数据类型,byte,short,int,long
		//float,double,char,boolean
		byte b = 1;		
		mv.addObject("byteval", b);
		mv.addObject("intval", 1);//int
		mv.addObject("longval", 9000009l);//long
		mv.addObject("booleanval", true);//boolean
		
		//date
		mv.addObject("dateval", new Date());
		mv.addObject("dateval2", new java.sql.Date(new Date().getTime()));
		
		
		
		//集合
		List<String> list = new ArrayList();
		list.add("java");
		list.add("c++");
		list.add("php");
		mv.addObject("listval", list);
		
		//map
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("java", "欢迎学习java");
		map.put("c++", "欢迎学习c++");
		map.put("php", "欢迎学习php");
		
		mv.addObject("mapval", map);
		
		//if
		mv.addObject("ifval", 1);
		

		//null
		mv.setViewName("/freemarker/temp1");
		return mv;
	}
}
