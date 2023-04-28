package com.base.util.page;

 

import javax.servlet.http.HttpServletRequest;
import com.base.util.StringUtil;

public class PageUtils {
	
	public static Page getPage(HttpServletRequest request){
		String page_ = request.getParameter("page")==null?"1":request.getParameter("page");
		String limit_ = request.getParameter("limit")==null?"10":request.getParameter("limit");
		int page = StringUtil.isNum(page_)?Integer.valueOf(page_):0;
		int limit = StringUtil.isNum(limit_)?Integer.valueOf(limit_):0;
		Page pager =  new Page(page,limit,null,0);
		return pager;
	}

}
