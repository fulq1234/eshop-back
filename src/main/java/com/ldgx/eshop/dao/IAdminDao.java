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
}
