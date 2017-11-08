package com.love.business.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.love.business.controller.MainController;
import com.love.business.entity.Admin;
import com.love.business.service.IAdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring-*.xml")
public class AdminServiceImplTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private MainController mainController;
	
	@Test
	public void testquery() {
		Admin admin = adminService.query("admin");
		logger.debug(admin.getUsername());
		System.out.println(admin.getUsername());
		System.out.println("=========");
		mainController.index();
	}
}
