package com.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Profile {
	
	private long id;   
	@NotNull
    @Size(min=1,max=200,message="Please enter name")
	private String name; 
	private String email; 
	private String username; 
	private String password;
	private String confirmpassword;
	@Size(min=1,message="Please select gender")
	private String gender;
	private String dob;
	@Size(min=1,message="Please select day")
	private String dobday;
	@Size(min=1,message="Please select month")
	private String dobmonth;
	@Size(min=1,message="Please select year")
	private String dobyear;
	@Size(min=1,message="Please select religion")
	private String religion_id;
	@Size(min=1,message="Please select mother tongue")
	private String mother_tongue_id;
	@Size(min=1,message="Please select marital status")
	private String marital_status_id;
	@Size(min=1,message="Please select height")
	private String height_info_id;
	@Size(min=1,message="Please select highest education")
	private String highest_education_id;
	
	//@Size(min=1,message="Please select caste") 
	private String caste_info_id;
	@Size(min=1,message="Please select annual income")
	private String annual_income_id;
	@Size(min=1,message="Please select employed in")
	private String employed_in_id;
	@Size(min=1,message="Please select occupation info")
	private String occupation_info_id;
	@NotNull
	@Size(min=1,message="Please select express yourself")
	private String express_yourself;
	private String family_type;
	private String father_occupation;
	private String mother_occupation;
	private String brother_no;
	private String sister_no;
	private String family_living_in;
	private String contact_address;
	private String about_my_family;
	@Size(min=10,max=10,message="Please enter valid phone no")
	private String phone_no;
	@Size(min=1,message="Please select country ")
	private String country_id;
	private String state_id;
	private String city_id;
	@NotNull
	@Size(min=1,message="Please enter weight ")
	private String weight_info;
	private String profile_image;
	
	
	/*for listing*/
	private String occupation_name;
	private String height_value;
	private String religion_name;
	private String caste_name;
	private String highest_education;
	private String country_name;
	private String state_name;
	private String city_name;
	private String mother_tongue_name;
	private String martial_name;
	private String employed_in_name;
	private String income_value;
	private String matrimony_id;
	/*for listing*/
	
	
	@Min(value=1, message="Please select physical status") 
	private int physical_status;
	@Min(value=1, message="Please select eating habits") 
	private int eating_habits;
	@Min(value=1, message="Please select drinking habits") 
	private int drinking_habits;
	@Min(value=1, message="Please select smoking habits") 
	private int smoking_habits;
	@Min(value=1, message="Please select body type")
	private int body_type;
	@Min(value=1, message="Please select complexion")
	private int complexion;
	
	private int gon_info_id;
	private int rashi_info_id;
	private int blood_group_id;
	
	private int update_profile_flag;
	
	
	
	public int getUpdate_profile_flag() {
		return update_profile_flag;
	}
	public void setUpdate_profile_flag(int update_profile_flag) {
		this.update_profile_flag = update_profile_flag;
	}
	public int getGon_info_id() {
		return gon_info_id;
	}
	public void setGon_info_id(int gon_info_id) {
		this.gon_info_id = gon_info_id;
	}
	
	
	public int getRashi_info_id() {
		return rashi_info_id;
	}
	public void setRashi_info_id(int rashi_info_id) {
		this.rashi_info_id = rashi_info_id;
	}
	public int getBlood_group_id() {
		return blood_group_id;
	}
	public void setBlood_group_id(int blood_group_id) {
		this.blood_group_id = blood_group_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDobday() {
		return dobday;
	}
	public void setDobday(String dobday) {
		this.dobday = dobday;
	}
	public String getDobmonth() {
		return dobmonth;
	}
	public void setDobmonth(String dobmonth) {
		this.dobmonth = dobmonth;
	}
	public String getDobyear() {
		return dobyear;
	}
	public void setDobyear(String dobyear) {
		this.dobyear = dobyear;
	}
	public String getReligion_id() {
		return religion_id;
	}
	public void setReligion_id(String religion_id) {
		this.religion_id = religion_id;
	}
	public String getMother_tongue_id() {
		return mother_tongue_id;
	}
	public void setMother_tongue_id(String mother_tongue_id) {
		this.mother_tongue_id = mother_tongue_id;
	}
	public String getMarital_status_id() {
		return marital_status_id;
	}
	public void setMarital_status_id(String marital_status_id) {
		this.marital_status_id = marital_status_id;
	}
	public String getHeight_info_id() {
		return height_info_id;
	}
	public void setHeight_info_id(String height_info_id) {
		this.height_info_id = height_info_id;
	}
	public String getHighest_education_id() {
		return highest_education_id;
	}
	public void setHighest_education_id(String highest_education_id) {
		this.highest_education_id = highest_education_id;
	}
	public String getCaste_info_id() {
		return caste_info_id;
	}
	public void setCaste_info_id(String caste_info_id) {
		this.caste_info_id = caste_info_id;
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
	public String getExpress_yourself() {
		return express_yourself;
	}
	public void setExpress_yourself(String express_yourself) {
		this.express_yourself = express_yourself;
	}
	public String getFamily_type() {
		return family_type;
	}
	public void setFamily_type(String family_type) {
		this.family_type = family_type;
	}
	
	public String getBrother_no() {
		return brother_no;
	}
	public void setBrother_no(String brother_no) {
		this.brother_no = brother_no;
	}
	public String getFather_occupation() {
		return father_occupation;
	}
	public void setFather_occupation(String father_occupation) {
		this.father_occupation = father_occupation;
	}
	public String getMother_occupation() {
		return mother_occupation;
	}
	public void setMother_occupation(String mother_occupation) {
		this.mother_occupation = mother_occupation;
	}
	public String getSister_no() {
		return sister_no;
	}
	public void setSister_no(String sister_no) {
		this.sister_no = sister_no;
	}
	public String getFamily_living_in() {
		return family_living_in;
	}
	public void setFamily_living_in(String family_living_in) {
		this.family_living_in = family_living_in;
	}
	public String getContact_address() {
		return contact_address;
	}
	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}
	public String getAbout_my_family() {
		return about_my_family;
	}
	public void setAbout_my_family(String about_my_family) {
		this.about_my_family = about_my_family;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getState_id() {
		return state_id;
	}
	public void setState_id(String state_id) {
		this.state_id = state_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getWeight_info() {
		return weight_info;
	}
	public void setWeight_info(String weight_info) {
		this.weight_info = weight_info;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getOccupation_name() {
		return occupation_name;
	}
	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	public String getHeight_value() {
		return height_value;
	}
	public void setHeight_value(String height_value) {
		this.height_value = height_value;
	}
	public String getReligion_name() {
		return religion_name;
	}
	public void setReligion_name(String religion_name) {
		this.religion_name = religion_name;
	}
	public String getCaste_name() {
		return caste_name;
	}
	public void setCaste_name(String caste_name) {
		this.caste_name = caste_name;
	}
	public String getHighest_education() {
		return highest_education;
	}
	public void setHighest_education(String highest_education) {
		this.highest_education = highest_education;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getMother_tongue_name() {
		return mother_tongue_name;
	}
	public void setMother_tongue_name(String mother_tongue_name) {
		this.mother_tongue_name = mother_tongue_name;
	}
	public String getMartial_name() {
		return martial_name;
	}
	public void setMartial_name(String martial_name) {
		this.martial_name = martial_name;
	}
	public String getEmployed_in_name() {
		return employed_in_name;
	}
	public void setEmployed_in_name(String employed_in_name) {
		this.employed_in_name = employed_in_name;
	}
	public String getIncome_value() {
		return income_value;
	}
	public void setIncome_value(String income_value) {
		this.income_value = income_value;
	}
	public String getMatrimony_id() {
		return matrimony_id;
	}
	public void setMatrimony_id(String matrimony_id) {
		this.matrimony_id = matrimony_id;
	}
	public int getComplexion() {
		return complexion;
	}
	public void setComplexion(int complexion) {
		this.complexion = complexion;
	}
	public int getPhysical_status() {
		return physical_status;
	}
	public void setPhysical_status(int physical_status) {
		this.physical_status = physical_status;
	}
	public int getEating_habits() {
		return eating_habits;
	}
	public void setEating_habits(int eating_habits) {
		this.eating_habits = eating_habits;
	}
	public int getDrinking_habits() {
		return drinking_habits;
	}
	public void setDrinking_habits(int drinking_habits) {
		this.drinking_habits = drinking_habits;
	}
	public int getSmoking_habits() {
		return smoking_habits;
	}
	public void setSmoking_habits(int smoking_habits) {
		this.smoking_habits = smoking_habits;
	}
	public int getBody_type() {
		return body_type;
	}
	public void setBody_type(int body_type) {
		this.body_type = body_type;
	}
	
	
	
	
	
}
