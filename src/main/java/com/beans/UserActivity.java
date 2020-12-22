package com.beans;

import java.time.LocalDate;
import java.util.Date;

public class UserActivity {
	private long id; 
	private long sender_id; 
	private long receiver_id; 
	private int status; 
	private LocalDate created_date;
	private int totcount;
	private long order_by_no;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSender_id() {
		return sender_id;
	}
	public void setSender_id(long sender_id) {
		this.sender_id = sender_id;
	}
	public long getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}
	public long getOrder_by_no() {
		return order_by_no;
	}
	public void setOrder_by_no(long order_by_no) {
		this.order_by_no = order_by_no;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDate getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDate localDate) {
		this.created_date = localDate;
	}
	public int getTotcount() {
		return totcount;
	}
	public void setTotcount(int totcount) {
		this.totcount = totcount;
	}
	
	
}
