package com.model;

import java.io.Serializable;


// 社团申请
public class StunionApply implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id; //主键
	private int studentId; //学生
	private int stunionId; //社团
	private String reason; //申请理由
	private String status; //申请状态
	private String createdAt; //申请时间
	private String updatedAt; //审核时间
	
	private String studentIdTxt; //学生
	private String stunionIdTxt; //社团
	private String statusTxt; //申请状态
	private String searchFields = "studentId:=;status:like";
	
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
	public int getStunionId() {
		return stunionId;
	}
	public void setStunionId(int stunionId) {
		this.stunionId = stunionId;
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
	public String getStunionIdTxt() {
		return stunionIdTxt;
	}
	public void setStunionIdTxt(String stunionIdTxt) {
		this.stunionIdTxt = stunionIdTxt;
	}
	public String getStatusTxt() {
		return statusTxt;
	}
	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}