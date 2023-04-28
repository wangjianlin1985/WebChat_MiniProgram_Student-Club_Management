package com.model;

import java.io.Serializable;


// 用户
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private String name; //姓名
	private String code; //账号
	private String password; //密码
	private String role; //角色
	private String idcard; //身份证
	private String phone; //手机号
	private String email; //邮箱
	private String address; //地址
	private String notes; //备注
	private String createdAt; //创建时间
	private String updatedAt; //更新时间
	
	private String roleTxt; //角色
	private String searchFields = "name:like;code:like;role:like;phone:like";
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
	public String getRoleTxt() {
		return roleTxt;
	}
	public void setRoleTxt(String roleTxt) {
		this.roleTxt = roleTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}