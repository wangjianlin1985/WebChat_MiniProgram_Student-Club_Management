package com.base.util;

import org.springframework.stereotype.Component;

@Component
public class JsonResult {

	private String code = "1"; // 是否成功 1成功，0失败
	private Object data = null; // 结果集，json字符串
	private String msg = "success";// 失败原因

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
