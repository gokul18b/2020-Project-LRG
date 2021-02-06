package com.example.demo.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GetPunchDetailsResponse {
@JsonFormat(pattern = "dd-MM-yyyy")
private Timestamp date;

@JsonFormat(pattern = "hh:mm:ss a")
private Timestamp in;
@JsonFormat(pattern = "hh:mm:ss a")
private Timestamp out;
private String total;

private Timestamp test;
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}
public Timestamp getIn() {
	return in;
}
public void setIn(Timestamp in) {
	this.in = in;
}
public Timestamp getOut() {
	return out;
}
public void setOut(Timestamp out) {
	this.out = out;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}




}
