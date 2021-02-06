package com.example.demo.request;

public class AddCourierRequest {
	private String mobile;
	private String name;
	private String sendlocation;
	private String currentlocation;
	private String fulladdress;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendlocation() {
		return sendlocation;
	}

	public void setSendlocation(String sendlocation) {
		this.sendlocation = sendlocation;
	}

	public String getCurrentlocation() {
		return currentlocation;
	}

	public void setCurrentlocation(String currentlocation) {
		this.currentlocation = currentlocation;
	}

	public String getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	

}
