package com.ldgx.eshop.entity;

import java.util.List;

public class PageBean<T> {
	//当前页面
	private int page;
	//数据总量
	private int total;
	//总页数
	private int totalPage;
	//每页显示的记录数
	private int limit;
	//每页显示数据积累的集合
	private List<T> rows;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
}
