package com.ldgx.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ldgx.eshop.entity.Admin;

@Mapper
public interface IAdminDao {

	public Admin query(String username);
	
	/**
	 * 分页显示管理员列表
	 * @param username
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Admin> list(
			@Param(value="username")String username,
			@Param(value="limit")int limit,
			@Param(value="offset")int offset);
	
	/**
	 * 根据用户名称查询全部记录
	 * @param username
	 * @return
	 */
	public int querycount(String username);
	
	/**
	 * 新增
	 * @param admin
	 */
	public void insert(Admin admin);
	
	/**
	 * 删除
	 * @param username
	 */
	public void del(int id);
	
	/**
	 * 更新
	 * @param admin
	 */
	public void update(Admin admin);
	/**
	 * 查找用户名是否重复
	 * @param username
	 * @return
	 */
	public int findAdminByUserName(String username);
}
