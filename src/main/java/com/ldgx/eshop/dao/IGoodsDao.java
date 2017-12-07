package com.ldgx.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ldgx.eshop.entity.Goods;

@Mapper
public interface IGoodsDao {

	public List<Goods> queryGoods();
}
