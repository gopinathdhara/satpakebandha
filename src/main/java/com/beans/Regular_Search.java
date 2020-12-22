package com.beans;

import javax.validation.constraints.NotNull;

public class Regular_Search {
	
	private long id;
	private long userinfo_id;
	private int age_form;
	private int age_to;
	private int religion_id;
	private String mother_tongue_id;
	private String caste_info_id;
	private String marital_status_id;
	private int height_info_from_id;
	private int height_info_to_id;
	private int country_id;
	private int state_id;
	private String city_id;
	private String highest_education_id;
	private String annual_income_id;
	private String employed_in_id;
	private String occupation_info_id;
	private String search_name;
	
	private String[] physical_status;
	
	private String[] eating_habits;
	
	private String[] drinking_habits;
	
	private String[] smoking_habits;
	
	private String[] body_type;
	
	private String[] complexion;
	
	private String body_type1;
	
	private String complexion1;
	
	private String smoking_habits1;
	
	private String drinking_habits1;
	
	private String eating_habits1;
	
	private String physical_status1;
	
	private int countitem;
	
	private String itemname;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserinfo_id() {
		return userinfo_id;
	}
	public void setUserinfo_id(long userinfo_id) {
		this.userinfo_id = userinfo_id;
	}
	public int getAge_form() {
		return age_form;
	}
	public void setAge_form(int age_form) {
		this.age_form = age_form;
	}
	public int getAge_to() {
		return age_to;
	}
	public void setAge_to(int age_to) {
		this.age_to = age_to;
	}
	public int getReligion_id() {
		return religion_id;
	}
	public void setReligion_id(int religion_id) {
		this.religion_id = religion_id;
	}
	public String getMother_tongue_id() {
		return mother_tongue_id;
	}
	public void setMother_tongue_id(String mother_tongue_id) {
		this.mother_tongue_id = mother_tongue_id;
	}
	public String getCaste_info_id() {
		return caste_info_id;
	}
	public void setCaste_info_id(String caste_info_id) {
		this.caste_info_id = caste_info_id;
	}
	public String getMarital_status_id() {
		return marital_status_id;
	}
	public void setMarital_status_id(String marital_status_id) {
		this.marital_status_id = marital_status_id;
	}
	public int getHeight_info_from_id() {
		return height_info_from_id;
	}
	public void setHeight_info_from_id(int height_info_from_id) {
		this.height_info_from_id = height_info_from_id;
	}
	public int getHeight_info_to_id() {
		return height_info_to_id;
	}
	public void setHeight_info_to_id(int height_info_to_id) {
		this.height_info_to_id = height_info_to_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getHighest_education_id() {
		return highest_education_id;
	}
	public void setHighest_education_id(String highest_education_id) {
		this.highest_education_id = highest_education_id;
	}
	public String getAnnual_income_id() {
		return annual_income_id;
	}
	public void setAnnual_income_id(String annual_income_id) {
		this.annual_income_id = annual_income_id;
	}
	public String getEmployed_in_id() {
		return employed_in_id;
	}
	public void setEmployed_in_id(String employed_in_id) {
		this.employed_in_id = employed_in_id;
	}
	public String getOccupation_info_id() {
		return occupation_info_id;
	}
	public void setOccupation_info_id(String occupation_info_id) {
		this.occupation_info_id = occupation_info_id;
	}
	public String getSearch_name() {
		return search_name;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}
	public String[] getPhysical_status() {
		return physical_status;
	}
	public void setPhysical_status(String[] physical_status) {
		this.physical_status = physical_status;
	}
	public String[] getEating_habits() {
		return eating_habits;
	}
	public void setEating_habits(String[] eating_habits) {
		this.eating_habits = eating_habits;
	}
	public String[] getDrinking_habits() {
		return drinking_habits;
	}
	public void setDrinking_habits(String[] drinking_habits) {
		this.drinking_habits = drinking_habits;
	}
	public String[] getSmoking_habits() {
		return smoking_habits;
	}
	public void setSmoking_habits(String[] smoking_habits) {
		this.smoking_habits = smoking_habits;
	}
	public String[] getBody_type() {
		return body_type;
	}
	public void setBody_type(String[] body_type) {
		this.body_type = body_type;
	}
	public String[] getComplexion() {
		return complexion;
	}
	public void setComplexion(String[] complexion) {
		this.complexion = complexion;
	}
	public String getBody_type1() {
		return body_type1;
	}
	public void setBody_type1(String body_type1) {
		this.body_type1 = body_type1;
	}
	public String getComplexion1() {
		return complexion1;
	}
	public void setComplexion1(String complexion1) {
		this.complexion1 = complexion1;
	}
	public String getSmoking_habits1() {
		return smoking_habits1;
	}
	public void setSmoking_habits1(String smoking_habits1) {
		this.smoking_habits1 = smoking_habits1;
	}
	public String getDrinking_habits1() {
		return drinking_habits1;
	}
	public void setDrinking_habits1(String drinking_habits1) {
		this.drinking_habits1 = drinking_habits1;
	}
	public String getEating_habits1() {
		return eating_habits1;
	}
	public void setEating_habits1(String eating_habits1) {
		this.eating_habits1 = eating_habits1;
	}
	public String getPhysical_status1() {
		return physical_status1;
	}
	public void setPhysical_status1(String physical_status1) {
		this.physical_status1 = physical_status1;
	}
	public int getCountitem() {
		return countitem;
	}
	public void setCountitem(int countitem) {
		this.countitem = countitem;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	
	
}
