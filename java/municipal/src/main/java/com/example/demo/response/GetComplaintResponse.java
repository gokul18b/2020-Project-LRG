package com.example.demo.response;

public class GetComplaintResponse {
	Integer id;
Integer citizen_id;
	String mobile;
	String address;
	String reason;
	String status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCitizen_id() {
		return citizen_id;
	}
	public void setCitizen_id(Integer citizen_id) {
		this.citizen_id = citizen_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	
}
