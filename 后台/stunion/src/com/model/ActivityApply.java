package com.model;

import java.io.Serializable;


// 活动申请
public class ActivityApply implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private int studentId; //学生
	private int activityId; //活动
	private String reason; //申请理由
	private String status; //申请状态
	private String iscredit; //是否打分
	private int credit; //学分
	private String createdAt; //申请时间
	private String updatedAt; //审核时间
	
	private String studentIdTxt; //学生
	private String activityIdTxt; //活动
	private String statusTxt; //申请状态
	private String iscreditTxt; //是否打分
	private String searchFields = "studentId:=;status:like;iscredit:=;credit:=";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIscredit() {
		return iscredit;
	}
	public void setIscredit(String iscredit) {
		this.iscredit = iscredit;
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
	public String getStatusTxt() {
		return statusTxt;
	}
	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}
	public String getIscreditTxt() {
		return iscreditTxt;
	}
	public void setIscreditTxt(String iscreditTxt) {
		this.iscreditTxt = iscreditTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}