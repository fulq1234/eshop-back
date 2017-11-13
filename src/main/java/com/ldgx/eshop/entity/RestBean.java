package com.ldgx.eshop.entity;

public class RestBean {
	
	//是否成功
	private boolean isOk;
	
	//提示内容
	private String content;

	
	public RestBean() {
		super();
	}

	
	public RestBean(boolean isOk, String content) {
		super();
		this.isOk = isOk;
		this.content = content;
	}


	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
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
