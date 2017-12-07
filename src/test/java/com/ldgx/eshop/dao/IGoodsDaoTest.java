package com.ldgx.eshop.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldgx.eshop.entity.Goods;
import com.ldgx.eshop.service.IGoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:config/spring-*.xml")
public class IGoodsDaoTest {

	/*@Autowired
	private IGoodsDao goodsDao;*/
	
	@Autowired
	private IGoodsService goodsService;
	
	@Test
	public void test() {
		List<Goods> list;
		try {
			list = goodsService.query("name1",10);
			System.out.println(list.size());
			for(int i =0;i<list.size();i++) {
				Goods goods = list.get(i);
				System.out.println(i + ": " +goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
