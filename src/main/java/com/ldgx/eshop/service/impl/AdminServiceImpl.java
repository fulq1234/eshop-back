package com.ldgx.eshop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldgx.eshop.dao.IAdminDao;
import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.entity.PageBean;
import com.ldgx.eshop.entity.RestBean;
import com.ldgx.eshop.service.IAdminService;
import com.ldgx.eshop.util.AESUtil;
import com.ldgx.eshop.util.RestUtil;

@Service
public class AdminServiceImpl implements IAdminService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IAdminDao adminDao;
	
	/**
	 * 是否登录
	 */
	@Override
	public Admin login(Admin admin)  {
		
		//根据用户名查询数据库里面的管理员信息
		Admin dbAdmin = adminDao.query(admin.getUsername());
		if(dbAdmin == null) {
			return null;
		}
		String dbpwd = dbAdmin.getPassword();
		
		
		//页面的传递的密码
		String pagepwd = admin.getPassword();
		//加密
		try {
			pagepwd = AESUtil.encrypt(pagepwd, "ldgx", null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("登录失败", e);
			e.printStackTrace();
		}
		
		if(pagepwd.equals(dbpwd)) {//登录成功
			return dbAdmin;
		}
		
		
		return null;
	}

	/*
	 * 分页显示列表
	 * @param username:查询条件：用户名
	 * @param limit:一页显示几行
	 * @param offset:偏移量
	 * 
	 * (non-Javadoc)
	 * @see com.ldgx.eshop.service.IAdminService#list(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean<Admin> list(String username,Integer limit,Integer offset) {
		if(limit == null){
			limit = 10;
		}
		if(offset == null){
			offset = 0;
		}
		
		List<Admin> list = adminDao.list(username,limit,offset);
		
		PageBean<Admin> page = new PageBean<Admin>();
		page.setRows(list);//数据
		
		int count = adminDao.querycount(username);
		page.setTotal(count);//总页数
		
		int ipage = offset/limit +1;
		
		if(ipage > count) {//如果页码大于1，就
			ipage = 1;
		}
		page.setPage(ipage);//第几页;
		return page;
	}

	@Override
	//@Transactional	
	public RestBean save(Admin admin) {
		
		//1.先判断用户名是否重复
		int findAdminByUserName = adminDao.findAdminByUserName(admin.getUsername());
		System.out.println(findAdminByUserName);
		if(findAdminByUserName>0) {
			return RestUtil.getErrorResult("用户名重复");
		}
		
		//2.插入
		adminDao.insert(admin);
		
		return RestUtil.getSuccessResult("操作成功");
	}

	@Override
	public void modify(Admin admin) {
		adminDao.update(admin);
		
	}

	@Override
	public void del(int id) {
		adminDao.del(id);
		
	}

}
