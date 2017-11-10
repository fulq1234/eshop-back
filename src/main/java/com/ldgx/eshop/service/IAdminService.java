package com.ldgx.eshop.service;

import java.util.List;

import com.ldgx.eshop.entity.Admin;

public interface IAdminService {
	
	/**
	 * 登录功能
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin);
	
	public List<Admin> list(Admin admin);
}
