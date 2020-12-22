package com.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Login {
	
	private long id;
	@Pattern(regexp=".+@.+\\.[a-z]+",message="Please enter valid email")
	private String email; 
	@NotNull
    @Size(min=1,max=200,message="Please enter password")
	private String password;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
}
