package com.love.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.love.business.dao.IAdminDao;
import com.love.business.entity.Admin;
import com.love.business.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminDao adminDao;
	
	@Override
	public Admin query(String name) {
		Admin admin = adminDao.query(name);
		return admin;
	}

}
