package com.beans;

import java.time.LocalDate;

public class Payment_Details {
	
	private long id;
	private long userinfo_id;
	private int membership_package_id;
	private int status;
	private LocalDate from_date;
	private LocalDate to_date;
	private int total_amount;
	private String order_id;
	private String transaction_id;
	private LocalDate created_date;
	private int duration;
	private String duration_type;
	private int payment_type;
	private String package_title;
	private int package_duration;
	private String package_duration_type;
	private int package_discount_percentage;
	private int package_original_price;
	private long totalrecord;
	private String from_date_new;
	private String to_date_new;
	private String created_date_new;
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public int getMembership_package_id() {
		return membership_package_id;
	}
	public void setMembership_package_id(int membership_package_id) {
		this.membership_package_id = membership_package_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDate getFrom_date() {
		return from_date;
	}
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	public LocalDate getTo_date() {
		return to_date;
	}
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public LocalDate getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDuration_type() {
		return duration_type;
	}
	public void setDuration_type(String duration_type) {
		this.duration_type = duration_type;
	}
	public int getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
	public String getPackage_title() {
		return package_title;
	}
	public void setPackage_title(String package_title) {
		this.package_title = package_title;
	}
	public int getPackage_duration() {
		return package_duration;
	}
	public void setPackage_duration(int package_duration) {
		this.package_duration = package_duration;
	}
	public String getPackage_duration_type() {
		return package_duration_type;
	}
	public void setPackage_duration_type(String package_duration_type) {
		this.package_duration_type = package_duration_type;
	}
	public int getPackage_discount_percentage() {
		return package_discount_percentage;
	}
	public void setPackage_discount_percentage(int package_discount_percentage) {
		this.package_discount_percentage = package_discount_percentage;
	}
	public int getPackage_original_price() {
		return package_original_price;
	}
	public void setPackage_original_price(int package_original_price) {
		this.package_original_price = package_original_price;
	}
	public String getFrom_date_new() {
		return from_date_new;
	}
	public void setFrom_date_new(String from_date_new) {
		this.from_date_new = from_date_new;
	}
	public String getTo_date_new() {
		return to_date_new;
	}
	public void setTo_date_new(String to_date_new) {
		this.to_date_new = to_date_new;
	}
	public String getCreated_date_new() {
		return created_date_new;
	}
	public void setCreated_date_new(String created_date_new) {
		this.created_date_new = created_date_new;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	
	
}
