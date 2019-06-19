package com.kitri.member.model;

public class ZipcodeDto {
	
	private String zipcode;
	private String sido;
	private String gugun;
	private String upmyon;
	private String doro;
	private String building_number;
	private String sigugun_building_name;
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getUpmyon() {
		return upmyon;
	}
	public void setUpmyon(String upmyon) {
		this.upmyon = upmyon;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public String getBuilding_number() {
		return building_number;
	}
	public void setBuilding_number(String building_number) {
		this.building_number = building_number;
	}
	public String getSigugun_building_name() {
		return sigugun_building_name;
	}
	public void setSigugun_building_name(String sigugun_building_name) {
		this.sigugun_building_name = sigugun_building_name;
	}
	@Override
	public String toString() {
		return "ZipcodeDto [zipcode=" + zipcode + ", sido=" + sido + ", gugun=" + gugun + ", upmyon=" + upmyon
				+ ", doro=" + doro + ", building_number=" + building_number + ", sigugun_building_name="
				+ sigugun_building_name + "]";
	}
	
}
