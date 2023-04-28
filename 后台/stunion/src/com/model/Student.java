package com.model;

import java.io.Serializable;


// 学生
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private String name; //姓名
	private String sno; //学号
	private int clazzId; //班级
	private String password; //密码
	private String phone; //手机号
	private String idcard; //身份证
	private String email; //邮箱
	private int credit; //学分
	private String createdAt; //注册时间
	private String updatedAt; //更新时间
	
	private String clazzIdTxt; //班级
	private String searchFields = "name:like;sno:like;clazzId:=;phone:like;idcard:like;email:like";
	
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
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getClazzId() {
		return clazzId;
	}
	public void setClazzId(int clazzId) {
		this.clazzId = clazzId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
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
	public String getClazzIdTxt() {
		return clazzIdTxt;
	}
	public void setClazzIdTxt(String clazzIdTxt) {
		this.clazzIdTxt = clazzIdTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}