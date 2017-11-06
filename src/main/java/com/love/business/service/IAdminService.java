package com.love.business.service;

import com.love.business.entity.Admin;

public interface IAdminService {
	/**
	 * 查询数据库
	 * @param name
	 * @return
	 */
	public Admin query(String name);
}
