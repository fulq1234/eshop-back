package com.ldgx.eshop.service;

import java.util.List;

import com.ldgx.eshop.entity.Goods;

public interface IGoodsService {
	
	public List<Goods> query(String name,int limit) throws Exception;
}
