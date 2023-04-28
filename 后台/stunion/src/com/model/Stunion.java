package com.model;

import java.io.Serializable;


// 社团
public class Stunion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private String name; //名称
	private String logo; //logo
	private int userId; //部长
	private String tel; //电话
	private String slogan; //口号
	private String content; //简介
	private String createdAt; //创建时间
	private String updatedAt; //更新时间
	
	private String userIdTxt; //部长
	private String searchFields = "name:like;userId:=;tel:like;slogan:like";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getUserIdTxt() {
		return userIdTxt;
	}
	public void setUserIdTxt(String userIdTxt) {
		this.userIdTxt = userIdTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}