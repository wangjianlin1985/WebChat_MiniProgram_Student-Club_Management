package com.model;

import java.io.Serializable;


// 社团风采
public class Scenery implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private int stunionId; //社团
	private String name; //名称
	private String pic; //封面
	private String content; //内容
	private String createdAt; //创建时间
	private String updatedAt; //更新时间
	
	private String stunionIdTxt; //社团
	private String searchFields = "name:like";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStunionId() {
		return stunionId;
	}
	public void setStunionId(int stunionId) {
		this.stunionId = stunionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public String getStunionIdTxt() {
		return stunionIdTxt;
	}
	public void setStunionIdTxt(String stunionIdTxt) {
		this.stunionIdTxt = stunionIdTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}