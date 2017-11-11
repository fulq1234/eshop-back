package com.ldgx.eshop.controller;


/**
 * 分页显示的基本类
 * @author ZSCW
 *
 */
public class GridController {
	
	//一页显示几行数据
	private Integer limit;

	//偏移量
	private Integer offset;
	
	
	//排序字段
	private String order;
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
}
