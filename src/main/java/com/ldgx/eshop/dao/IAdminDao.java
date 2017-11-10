package com.ldgx.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ldgx.eshop.entity.Admin;

@Mapper
public interface IAdminDao {

	public Admin query(String username);
	
	public List<Admin> list(Admin admin);
}
