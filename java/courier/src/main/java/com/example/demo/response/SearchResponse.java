package com.example.demo.response;

public class SearchResponse {
	private Integer id;
	private String mobile;
	private String name;
	private String sendlocation;
	private String currentlocation;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
