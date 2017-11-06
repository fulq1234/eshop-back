package com.love.business.dao;

import org.apache.ibatis.annotations.Mapper;

import com.love.business.entity.Admin;

@Mapper
public interface IAdminDao {
	public Admin query(String name);
}
