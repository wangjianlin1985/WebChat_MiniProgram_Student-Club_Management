package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// 社团活动
public class Activity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private int stunionId; //社团
	private String name; //活动名称
	private String pic; //封面
	private int hits; //点赞
	private int comments; //评论
	private int collects; //收藏
	private String content; //活动明细
	private String createdAt; //创建时间
	private String updatedAt; //更新时间
	
	private List<ActivityComment> comment = new ArrayList<ActivityComment>(); 
	
	private String stunionIdTxt; //社团
	private String searchFields = "stunionId:=;name:like";
	
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getCollects() {
		return collects;
	}
	public void setCollects(int collects) {
		this.collects = collects;
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
	public List<ActivityComment> getComment() {
		return comment;
	}
	public void setComment(List<ActivityComment> comment) {
		this.comment = comment;
	}
	

}