package com.base.util.page;


public class Page {
	private int page = 1;// 前台查询第几页
	private int limit = 10;// 每页显示几条
	private Object data; // 查询结果
	private int count = 0;// 满足条件的总数

	public Page() {
		super();
	}

	public Page(int page, int limit, Object data, int count) {
		super();
		this.page = page;
		this.limit = limit;
		this.data = data;
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
