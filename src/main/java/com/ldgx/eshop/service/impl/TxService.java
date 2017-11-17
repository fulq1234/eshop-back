package com.ldgx.eshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldgx.eshop.dao.IAdminDao;
import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.service.ITxService;

@Service
public class TxService implements ITxService {

	@Autowired
	private IAdminDao adminDao;
	
	@Transactional
	@Override
	public void tx1() {
		Admin admin = new Admin();
		admin.setUsername("admina1");
		admin.setPassword("pp");
		adminDao.insert(admin);
		
		/*admin.setUsername("adminb");
		admin.setPassword(null);
		adminDao.insert(admin);*/
		
	}
}
