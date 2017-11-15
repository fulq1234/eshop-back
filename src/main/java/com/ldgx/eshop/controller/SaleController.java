package com.ldgx.eshop.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldgx.eshop.entity.RestBean;
import com.ldgx.eshop.util.RestUtil;

/**
 * 卖家
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sale")
public class SaleController {
	
	/**
	 * 跳转到卖家账号管理系统
	 * @return
	 */
	@RequestMapping("/account")
	public ModelAndView space() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sale/saleaccount");
		return mv;
	}
	
	/**
	 * 上传图片
	 */
	@ResponseBody
	@RequestMapping("/upload-image")
	public RestBean uploadImage(MultipartFile file){
		try {
			String filename = file.getOriginalFilename();		
			File tempFile = new File("E:\\"+filename);
			
			file.transferTo(tempFile);
			return RestUtil.getSuccessResult("上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RestUtil.getErrorResult("操作失败");
	}
}
