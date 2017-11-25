package com.ldgx.eshop.controller.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm/temp3")
public class Temp3Controller {
	
	
	@RequestMapping("/space")
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/freemarker/temp3");
		return mv;
	}
}
