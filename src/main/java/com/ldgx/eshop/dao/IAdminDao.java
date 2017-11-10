package com.ldgx.eshop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ldgx.eshop.entity.Admin;

@Mapper
public interface IAdminDao {

	public Admin query(Admin admin);
}
