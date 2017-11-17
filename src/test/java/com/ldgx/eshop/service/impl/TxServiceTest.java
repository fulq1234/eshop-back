package com.ldgx.eshop.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldgx.eshop.service.ITxService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:config/spring-*.xml")
public class TxServiceTest {
	
	@Autowired
	private ITxService ts;
	
	@Test
	void test() {
		ts.tx1();
	}

}
