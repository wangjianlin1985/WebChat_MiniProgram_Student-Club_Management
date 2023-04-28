package com.base.util.page;

import java.util.List;




/**
 * 这个类主要是封装数据位list以便于返回前台
 * @author Administrator
 *
 * @param <T>
 */

public class PageResult<T> {
	
	private List<T> list; // 当前页的数据信息
	private Page page; // 当前页的信息

	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
