package com.ldgx.eshop.controller.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm/temp4")
public class Temp4Controller {
	
	
	@RequestMapping("/space")
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/freemarker/temp4");
		return mv;
	}
}
