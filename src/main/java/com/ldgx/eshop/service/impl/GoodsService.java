package com.ldgx.eshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldgx.eshop.dao.IGoodsDao;
import com.ldgx.eshop.entity.Goods;
import com.ldgx.eshop.service.IGoodsService;
import com.ldgx.eshop.util.LuceneUtil;

@Service
public class GoodsService implements IGoodsService {

	@Autowired
	private IGoodsDao goodsDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	/*public List<Goods> query(String name,int limit) throws Exception {

		List<Goods> list = goodsDao.queryGoods();
		logger.debug("lucene save goods");
		//创建到lucene
		LuceneUtil.saveGoods(list);
		return list;
	}*/
	public List<Goods> query(String name,int limit) throws Exception {
		List<Goods> list = new ArrayList<Goods>();
		
			boolean docNum = false;
			try {
				docNum = LuceneUtil.ifExists();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//lucene有没有文档
			//logger.debug("docNum",docNum);
			System.out.println("docNum:"+docNum);
			if(!docNum) {
				list = goodsDao.queryGoods();
				//logger.debug("lucene save goods");
				System.out.println("lucene save goods");
				//创建到lucene
				LuceneUtil.saveGoods(list);
			}else {
				System.out.println("lucene from lucene");
				list = LuceneUtil.queryByName(name, limit);
			}
			
		
		return list;
	}

}
