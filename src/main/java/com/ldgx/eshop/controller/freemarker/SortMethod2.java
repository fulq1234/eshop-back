package com.ldgx.eshop.controller.freemarker;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class SortMethod2 implements TemplateMethodModelEx {

	/**
	 * 自定义排序
	 * @param arguments
	 * freemark，自己的模板引擎，入参和出参都是自己的类型
	 */
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		SimpleSequence arg0 = (SimpleSequence) arguments.get(0);
		List<BigDecimal> list = arg0.toList();
		
		Collections.sort(list, new Comparator<BigDecimal>() {

			@Override
			public int compare(BigDecimal o1, BigDecimal o2) {
				return o1.intValue() - o2.intValue();//升序
			}
			
		});
		return list;
	}

}
