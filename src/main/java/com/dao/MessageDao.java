package com.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.beans.City;
import com.beans.Login;
import com.beans.Message;
import com.beans.Profile;
import com.beans.State;
import com.beans.User;
import com.beans.UserActivity;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
public class MessageDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}  
	
	//#############chat section###################
	// all users online or offline for chat
	
	public List<User> all_user_chat_list(long userid)
	{
		
		String oppositegender=this.getoppositegender(userid);
		
		return template.query("select t.* from (select id,name,online_status,profile_image,gender,u.matrimony_id from userinfo u where u.email_verification_status=1 and u.status=1 and u.online_status=1 and u.gender='"+oppositegender+"') t order by t.name asc", new ResultSetExtractor<List<User>>() {
			
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<User> list1=new ArrayList<User>();
				while(rs.next())
				{
					User stobj=new User();
					stobj.setId(rs.getLong(1));
					stobj.setName(rs.getString(2));
					stobj.setOnline_status(rs.getInt(3));
					stobj.setProfile_image(rs.getString(4));
					stobj.setGender(rs.getString(5));
					stobj.setMatrimony_id(rs.getString(6));
					list1.add(stobj);
					
					
				}
				return list1;
			}
			
		});
	}
			
	 public String getoppositegender(long userid)
	   {
		   String oppositegender="";
			//get user details
			String sql1 = "SELECT u.gender FROM userinfo u WHERE email_verification_status=1 and status=1 and id=?";
			try {
			String gender =template.queryForObject(sql1, new Object[] { userid },String.class);
		
			if(gender.equals("Male"))
			{
				 oppositegender="Female";
			}
			else
			{
				oppositegender="Male";
			}
			
			}catch (Exception e) {
				
			}
			
			return oppositegender;
		   
	   }
			 
		// get msg info on page load	 
     public List<Message> individual_user_chat_message(long senderid, long receiverid)
	{
		
		 String sql="select m.id as messageid,DATE_FORMAT((m.created_date_time),'%b %d %Y %H:%i:%s'),u.name,u.id as usrid,u.online_status,u.profile_image,m.msg,u.gender from message m,userinfo u where u.email_verification_status=1 and u.status=1 and u.id=m.sender_id and ((m.sender_id='"+senderid+"' and m.receiver_id='"+receiverid+"') or (m.sender_id='"+receiverid+"' and m.receiver_id='"+senderid+"')) order by m.id asc ";
		
		return template.query(sql, new ResultSetExtractor<List<Message>>() {
			
			public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<Message> list1=new ArrayList<Message>();
				while(rs.next())
				{
					
					
					String created_date = rs.getString(2);
					
					Message stobj=new Message();
					stobj.setMessageid(rs.getLong(1));
					stobj.setCreated_date(created_date);
					stobj.setUsername(rs.getString(3));
					stobj.setUserid(rs.getLong(4));
					stobj.setOnline_status(rs.getInt(5));
					stobj.setProfile_image(rs.getString(6));
					stobj.setMsg(rs.getString(7));
					stobj.setGender(rs.getString(8));
					list1.add(stobj);
					
				}
				return list1;
			}
			
		});
	}
     // get receiver details
     public User get_chat_receiver_profiledetails_by_id(long uid){    
			String sql="select u.matrimony_id,u.id,u.name,u.dob,u.gender,u.profile_image from userinfo u where u.email_verification_status=1 and u.status=1 and u.id=?";
		    
			return template.queryForObject(sql, new Object[]{uid},new BeanPropertyRowMapper<User>(User.class));    
		} 
     
     //check if new msg comes after 5sec
     public List<Message> check_if_message_comes(long senderid, long receiverid,long lastmsgid)
 	{
 		
 		 String sql="select m.id as messageid,DATE_FORMAT((m.created_date_time),'%b %d %Y %H:%i:%s'),u.name,u.id as usrid,u.online_status,u.profile_image,m.msg,u.gender from message m,userinfo u where u.email_verification_status=1 and u.status=1 and u.id=m.sender_id and (m.sender_id='"+receiverid+"' and m.receiver_id='"+senderid+"') and m.id >"+lastmsgid+" order by m.id asc ";
 		
 		return template.query(sql, new ResultSetExtractor<List<Message>>() {
 			
 			public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
 				// TODO Auto-generated method stub
 			
 				List<Message> list1=new ArrayList<Message>();
 				while(rs.next())
 				{
 					Message stobj=new Message();
 					stobj.setMessageid(rs.getLong(1));
 					stobj.setCreated_date(rs.getString(2));
 					stobj.setUsername(rs.getString(3));
 					stobj.setUserid(rs.getLong(4));
 					stobj.setOnline_status(rs.getInt(5));
 					stobj.setProfile_image(rs.getString(6));
 					stobj.setMsg(rs.getString(7));
 					stobj.setGender(rs.getString(8));
 					list1.add(stobj);
 					
 				}
 				return list1;
 			}
 			
 		});
 	}
	  // insert message
     public Long insert_message(final Message e){  
 		
 	    String query="insert into message(sender_id,receiver_id,msg,created_date,created_date_time) values(?,?,?,?,?)";  
 	    return template.execute(query,new PreparedStatementCallback<Long>(){  
 	      
 	    public Long doInPreparedStatement(PreparedStatement ps)  
 	            throws SQLException, DataAccessException { 
 	    	
	 	    	ps.setLong(1,e.getUserid());        
	 	        ps.setLong(2,e.getReceiverid());  
	 	        ps.setString(3,e.getMsg());
	 	        ps.setString(4,e.getCreated_date());
	 	        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
	 	        ps.execute(); 
 	        
 	         ResultSet rs = ps.getGeneratedKeys();
 	         long last_inserted_id=(long) 0;
             if(rs.next())
             {
                  last_inserted_id = rs.getLong(1);
                 
             }
 			return last_inserted_id;
 	              
 	    }  
 	    });  
 	}
     //fetch inserted msg
     public List<Message> check_insert_message(long senderid, long receiverid,long lastinsertid)
  	{
    	
  		 String sql="select m.id as messageid,DATE_FORMAT((m.created_date_time),'%b %d %Y %H:%i:%s'),u.name,u.id as usrid,u.online_status,u.profile_image,m.msg,u.gender from message m,userinfo u where u.email_verification_status=1 and u.status=1 and u.id=m.sender_id and (m.sender_id='"+senderid+"' and m.receiver_id='"+receiverid+"') and m.id ="+lastinsertid+" order by m.id asc ";
  		
  		return template.query(sql, new ResultSetExtractor<List<Message>>() {
  			
  			public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
  				// TODO Auto-generated method stub
  			
  				List<Message> list1=new ArrayList<Message>();
  				while(rs.next())
  				{
  					Message stobj=new Message();
  					stobj.setMessageid(rs.getLong(1));
  					stobj.setCreated_date(rs.getString(2));
  					stobj.setUsername(rs.getString(3));
  					stobj.setUserid(rs.getLong(4));
  					stobj.setOnline_status(rs.getInt(5));
  					stobj.setProfile_image(rs.getString(6));
  					stobj.setMsg(rs.getString(7));
  					stobj.setGender(rs.getString(8));
  					list1.add(stobj);
  					
  				}
  				return list1;
  			}
  			
  		});
  	}
     
     // chat with single
     public List<User> all_user_chat_list_single(long userid,long receiverid)
 	{
 		
 		String oppositegender=this.getoppositegender(userid);
 		
 		return template.query("select t.* from (select id,name,online_status,profile_image,gender,u.matrimony_id from userinfo u where  u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.id='"+receiverid+"') t order by t.name asc", new ResultSetExtractor<List<User>>() {
 			
 			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
 				// TODO Auto-generated method stub
 			
 				List<User> list1=new ArrayList<User>();
 				while(rs.next())
 				{
 					User stobj=new User();
 					stobj.setId(rs.getLong(1));
 					stobj.setName(rs.getString(2));
 					stobj.setOnline_status(rs.getInt(3));
 					stobj.setProfile_image(rs.getString(4));
 					stobj.setGender(rs.getString(5));
 					stobj.setMatrimony_id(rs.getString(6));
 					list1.add(stobj);
 					
 					
 				}
 				return list1;
 			}
 			
 		});
 	}
     
	  //#############chat section###################
     
     //my messages
     public List<Message> my_all_message(long userid,long pageid,long total)
 	{
 		
 		 String sql="select m.id as messageid,DATE_FORMAT((m.created_date_time),'%b %d %Y %H:%i:%s'),m.sender_id as sender_id,m.receiver_id as receiver_id,u1.profile_image as sender_image,u2.profile_image as receiver_image,m.msg,u1.name as sendername,u2.name as receivername,u1.matrimony_id as sendermatrimonyid, u2.matrimony_id as receivermatrimonyid from message m inner join userinfo u1 on u1.id=m.sender_id inner join userinfo u2 on u2.id=m.receiver_id and u1.email_verification_status=1 and u1.status=1 and u2.email_verification_status=1 and u2.status=1 and (m.sender_id='"+userid+"' or m.receiver_id='"+userid+"') order by m.id desc limit "+(pageid-1)+","+total;
 		
 		return template.query(sql, new ResultSetExtractor<List<Message>>() {
 			
 			public List<Message> extractData(ResultSet rs) throws SQLException, DataAccessException {
 				// TODO Auto-generated method stub
 			
 				List<Message> list1=new ArrayList<Message>();
 				while(rs.next())
 				{
 					
 					
 					String created_date = rs.getString(2);
 					
 					Message stobj=new Message();
 					stobj.setMessageid(rs.getLong(1));
 					stobj.setCreated_date(created_date);
 					
 					stobj.setSenderid(rs.getLong(3));
 					stobj.setReceiverid(rs.getLong(4));
 					stobj.setSender_image(rs.getString(5));
 					stobj.setReceiver_image(rs.getString(6));
 					stobj.setMsg(rs.getString(7));
 					
 					stobj.setSendername(rs.getString(8));
 					stobj.setReceivername(rs.getString(9));
 					
 					stobj.setSendermatrimonyid(rs.getString(10));
 					stobj.setReceivermatrimonyid(rs.getString(11));
 					
 					list1.add(stobj);
 					
 				}
 				return list1;
 			}
 			
 		});
 	}
     // total record
     public Message my_all_message_total_record(long userid){    
    	 
			String sql="select count(*) as totalrecord from message m inner join userinfo u1 on u1.id=m.sender_id inner join userinfo u2 on u2.id=m.receiver_id and u1.email_verification_status=1 and u1.status=1 and u2.email_verification_status=1 and u2.status=1 and (m.sender_id=? or m.receiver_id=?)";
		    
			return template.queryForObject(sql, new Object[]{userid,userid},new BeanPropertyRowMapper<Message>(Message.class));    
		} 
     
     // message notification
     public int update_mymessages_status_count(long userid){
			
			
		    String query="update message set read_status=1 where receiver_id='"+userid+"' and read_status=0 ";  
		 		
			return template.update(query);
		}
}