package com.beans;

public class Message {
	
	private long messageid;
	private String created_date;
	private long userid;
	private String username;
	private int online_status;
	private String profile_image;
	private String msg;
	private String gender;
	private long receiverid;
	private long senderid;
	private String sender_image;
	private String receiver_image;
	private String sendername;
	private String receivername;
	private String sendermatrimonyid;
	private String receivermatrimonyid;
	private long totalrecord;
	
	
	
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
	public String getSendermatrimonyid() {
		return sendermatrimonyid;
	}
	public void setSendermatrimonyid(String sendermatrimonyid) {
		this.sendermatrimonyid = sendermatrimonyid;
	}
	public String getReceivermatrimonyid() {
		return receivermatrimonyid;
	}
	public void setReceivermatrimonyid(String receivermatrimonyid) {
		this.receivermatrimonyid = receivermatrimonyid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(long receiverid) {
		this.receiverid = receiverid;
	}
	public long getSenderid() {
		return senderid;
	}
	public void setSenderid(long senderid) {
		this.senderid = senderid;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getSender_image() {
		return sender_image;
	}
	public void setSender_image(String sender_image) {
		this.sender_image = sender_image;
	}
	public String getReceiver_image() {
		return receiver_image;
	}
	public void setReceiver_image(String receiver_image) {
		this.receiver_image = receiver_image;
	}
	
	public long getMessageid() {
		return messageid;
	}
	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getOnline_status() {
		return online_status;
	}
	public void setOnline_status(int online_status) {
		this.online_status = online_status;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}
