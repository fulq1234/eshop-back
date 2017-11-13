package com.ldgx.eshop.service;


import com.ldgx.eshop.entity.Admin;
import com.ldgx.eshop.entity.PageBean;
import com.ldgx.eshop.entity.RestBean;

public interface IAdminService {
	
	/**
	 * 登录功能
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin);
	
	/**
	 * 分页显示数据
	 * @param username:用户名
	 * @param limit:一页显示几行
	 * @param offset:偏移量
	 * @return
	 */
	public PageBean<Admin> list(String username,Integer limit,Integer offset);

	/**
	 * 保存
	 * @param admin
	 * @return
	 */
	public RestBean save(Admin admin);
	
	/**
	 * 修改
	 * @param admin
	 */
	public void modify(Admin admin);
	
	/**
	 * 删除
	 * @param id
	 */
	public void del(int id);

}
