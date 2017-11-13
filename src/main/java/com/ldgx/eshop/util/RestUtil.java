package com.ldgx.eshop.util;

import com.ldgx.eshop.entity.RestBean;

public class RestUtil {
	/**
	 * 成功
	 * @param content
	 * @return
	 */
	public static RestBean getSuccessResult(String content) {
		RestBean bean = new RestBean();
		bean.setOk(true);
		bean.setContent(content);
		return bean;
	}
	
	/**
	 * 失败
	 * @param content
	 * @return
	 */
	public static RestBean getErrorResult(String content) {
		RestBean bean = new RestBean();
		bean.setOk(false);
		bean.setContent(content);
		return bean;
	}
}
