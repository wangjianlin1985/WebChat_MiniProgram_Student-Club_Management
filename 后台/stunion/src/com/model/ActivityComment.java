package com.model;

import java.io.Serializable;


// 活动评论
public class ActivityComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private String studentId; //学生
	private int activityId; //活动
	private String content; //评论
	private String createdAt; //评论时间
	private String updatedAt; //更新时间
	
	private String studentIdTxt; //学生
	private String activityIdTxt; //活动
	private String searchFields = "studentId:=";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
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
	public String getStudentIdTxt() {
		return studentIdTxt;
	}
	public void setStudentIdTxt(String studentIdTxt) {
		this.studentIdTxt = studentIdTxt;
	}
	public String getActivityIdTxt() {
		return activityIdTxt;
	}
	public void setActivityIdTxt(String activityIdTxt) {
		this.activityIdTxt = activityIdTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}