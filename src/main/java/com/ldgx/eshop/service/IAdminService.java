package com.ldgx.eshop.service;

import java.util.List;

import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.entity.PageBean;

public interface IAdminService {
	
	/**
	 * 登录功能
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin);
	
	public PageBean<Admin> list(Admin admin);
}
