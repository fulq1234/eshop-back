package com.ldgx.eshop.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldgx.eshop.dao.IAdminDao;
import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.entity.RestBean;
import com.ldgx.eshop.service.IAdminService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:config/spring-*.xml")
public class AdminServiceImplTest {
	
	@Autowired
	private IAdminDao adminDao;
	
	@Autowired
	private IAdminService adminService;
	
	@Test
	public void login() {
		Admin admin = adminDao.query("admin");
		System.out.println(admin.getUsername());
	}
	
	@Test
	public void ullll() {
		Admin admin = new Admin();
		admin.setUsername("dkkdkdkd");
		admin.setStatus(1);
		admin.setPassword("ddd");
		//adminDao.insert(admin);
		
		//adminDao.del(220);
		
		admin.setId(219);
		adminDao.update(admin);
	}
	@Test
	public void findAdminByUserName() {
		Integer findAdminByUserName = adminDao.findAdminByUserName("admin");
		System.out.println(findAdminByUserName);
	}
	
	
	@Test
	public void testSSS() {
		Admin admin = new Admin();
		admin.setUsername("adminadminadmaa");
		admin.setStatus(1);
		admin.setPassword("xx");
		RestBean bean = adminService.save(admin);
		System.out.println(bean.getContent());
	}
	

}