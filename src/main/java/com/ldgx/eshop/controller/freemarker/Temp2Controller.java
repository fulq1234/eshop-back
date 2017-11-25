package com.ldgx.eshop.controller.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/fm/temp2")
public class Temp2Controller {
	
	
	@RequestMapping("/space")
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("sort_int", new SortMethod2());
		
		mv.setViewName("/freemarker/temp2");
		return mv;
	}
}
