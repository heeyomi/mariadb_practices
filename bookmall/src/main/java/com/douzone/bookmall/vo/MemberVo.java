package com.douzone.bookmall.vo;

public class MemberVo {
	private String name;
	private String phoneNumber;
	private String email;
	private String password;
	private int memNo;
	
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "MemberVo [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password
				+ ", memNo=" + memNo + "]";
	}
	
	
	
}
