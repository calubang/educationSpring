package com.kitri.member.model;

public class MemberDetailDto extends MemberDto{
	
	private String tel1;
	private String tel2;
	private String tel3;
	private String zipcode;
	private String address;
	private String address_detail;
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	@Override
	public String toString() {
		return "MemberDetailDto [tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", zipcode=" + zipcode
				+ ", address=" + address + ", address_detail=" + address_detail + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getPass()=" + getPass() + ", getEmailid()=" + getEmailid()
				+ ", getEmaildomain()=" + getEmaildomain() + ", getJoindate()=" + getJoindate() + "]";
	}
	
}
