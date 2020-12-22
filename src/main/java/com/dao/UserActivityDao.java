package com.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.beans.City;
import com.beans.Common_Info;
import com.beans.Login;
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
public class UserActivityDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}    
	
	//send interest
	public int sendinterest(final UserActivity e){
		
		String sql="select count(*) as totcount from send_interest s where((s.sender_id=? and s.receiver_id=?) or (s.sender_id=? and s.receiver_id=?))";
	    
		UserActivity obj= template.queryForObject(sql, new Object[]{e.getSender_id(),e.getReceiver_id(),e.getReceiver_id(),e.getSender_id()},new BeanPropertyRowMapper<UserActivity>(UserActivity.class));  
		
		if(obj.getTotcount()==0)
		{ 
					
					String query="insert into send_interest(sender_id,receiver_id,status,created_date,created_date_time) values(?,?,?,?,?)";  
				    return template.execute(query,new PreparedStatementCallback<Integer>(){  
				      
				    public Integer doInPreparedStatement(PreparedStatement ps)  
				            throws SQLException, DataAccessException { 
				    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());
				    	ps.setLong(1,e.getSender_id());        
				        ps.setLong(2,e.getReceiver_id());  
				        ps.setInt(3,e.getStatus());
				        ps.setDate(4,(java.sql.Date) createddate);
				        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
				        ps.execute();   
				        
				        
						return 1;
				              
				    }  
				});  
		}
		else
		{
			return 0;
		}
		
		
		
	}
	
	//get all mailbox list on page load
	public List<User> get_all_mailbox_on_page_load(long userid,String type)
	{
		String oppositegender="";
		//get user details
		String sql1 = "SELECT u.gender FROM userinfo u WHERE u.email_verification_status=1 and u.status=1 and id=?";
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
		
		String sql="";
		
		if(type.equals("pending"))
		{
			 sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=0 order by sint.id desc limit 0, "+Common_Info.perpage;
		}
		else if(type.equals("accepted"))
		{
			sql="select t.* from ((select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=1) union (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.receiver_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.sender_id='"+userid+"' and sint.status=1) ) as t order by t.order_by_no desc limit 0, "+Common_Info.perpage;
			
		}
		
		else if(type.equals("declined"))
		{
			sql="select t.* from ((select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=2) union (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.receiver_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.sender_id='"+userid+"' and sint.status=2) ) as t order by t.order_by_no desc limit 0, "+Common_Info.perpage;
			
		}
		
		
		return template.query(sql, new ResultSetExtractor<List<User>>() {
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<User> list1=new ArrayList<User>();
				while(rs.next())
				{
					//check profile image
					String profile_img=rs.getString(5);
					if(profile_img==null)
					{
						profile_img="";
					}
					String state_name=rs.getString(12);
					if(state_name==null)
					{
						state_name="";
					}
					String city_name=rs.getString(13);
					if(city_name==null)
					{
						city_name="";
					}
					User uobj=new User();
					uobj.setId(rs.getLong(1));
					uobj.setName(rs.getString(2));
					uobj.setDob(rs.getString(3));
					uobj.setGender(rs.getString(4));
					uobj.setProfile_image(profile_img);
					uobj.setOccupation_name(rs.getString(6));
					uobj.setHeight_value(rs.getString(7));
					uobj.setReligion_name(rs.getString(8));
					uobj.setCaste_name(rs.getString(9));
					uobj.setHighest_education(rs.getString(10));
					uobj.setCountry_name(rs.getString(11));
					uobj.setState_name(state_name);
					uobj.setCity_name(city_name);
					uobj.setUsername(rs.getString(14));
					uobj.setMatrimony_id(rs.getString(15));
					uobj.setCreated_date(rs.getString(16));
					uobj.setSendinterestid(rs.getLong(17));
					uobj.setReceiver_id(rs.getLong(18));
					uobj.setCreated_date_time(rs.getString(19));
					uobj.setOrder_by_no(rs.getLong(20));
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	//get all mailbox list after reach bottom of page
	public List<User> get_all_mailbox_after_reach_bottom_of_page(long userid,String type,long lastpostid)
	{
		String oppositegender="";
		//get user details
		String sql1 = "SELECT u.gender FROM userinfo u WHERE u.email_verification_status=1 and u.status=1 and id=?";
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
		
		String sql="";
		
		if(type.equals("pending"))
		{
			 sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=0 and sint.id < '"+lastpostid+"' order by sint.id desc limit    "+Common_Info.perpage;
		}
		else if(type.equals("accepted"))
		{
			sql="select t.* from ((select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=1) union (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.receiver_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.sender_id='"+userid+"' and sint.status=1) ) as t  where t.order_by_no < '"+lastpostid+"' order by t.order_by_no desc limit  "+Common_Info.perpage;
		}
		
		else if(type.equals("declined"))
		{
			sql="select t.* from ((select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.sender_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.receiver_id='"+userid+"' and sint.status=2) union (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sint.created_date,sint.id as sendinterestid,sint.receiver_id as receiverid,sint.created_date_time,sint.order_by_no from userinfo u inner join send_interest sint on sint.receiver_id=u.id   inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sint.sender_id='"+userid+"' and sint.status=2) ) as t  where t.order_by_no < '"+lastpostid+"' order by t.order_by_no desc limit "+Common_Info.perpage;
			
		}
		
		return template.query(sql, new ResultSetExtractor<List<User>>() {
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<User> list1=new ArrayList<User>();
				while(rs.next())
				{
					//check profile image
					String profile_img=rs.getString(5);
					if(profile_img==null)
					{
						profile_img="";
					}
					String state_name=rs.getString(12);
					if(state_name==null)
					{
						state_name="";
					}
					String city_name=rs.getString(13);
					if(city_name==null)
					{
						city_name="";
					}
					User uobj=new User();
					uobj.setId(rs.getLong(1));
					uobj.setName(rs.getString(2));
					uobj.setDob(rs.getString(3));
					uobj.setGender(rs.getString(4));
					uobj.setProfile_image(profile_img);
					uobj.setOccupation_name(rs.getString(6));
					uobj.setHeight_value(rs.getString(7));
					uobj.setReligion_name(rs.getString(8));
					uobj.setCaste_name(rs.getString(9));
					uobj.setHighest_education(rs.getString(10));
					uobj.setCountry_name(rs.getString(11));
					uobj.setState_name(state_name);
					uobj.setCity_name(city_name);
					uobj.setUsername(rs.getString(14));
					uobj.setMatrimony_id(rs.getString(15));
					uobj.setCreated_date(rs.getString(16));
					uobj.setSendinterestid(rs.getLong(17));
					uobj.setReceiver_id(rs.getLong(18));
					uobj.setCreated_date_time(rs.getString(19));
					uobj.setOrder_by_no(rs.getLong(20));
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	//accept interest
	public Boolean acceptinterest(final UserActivity e){
		
		String sql="select max(order_by_no) as order_by_no from send_interest";
	    
		UserActivity obj= template.queryForObject(sql, new Object[]{},new BeanPropertyRowMapper<UserActivity>(UserActivity.class)); 
		
		long order_by_no=obj.getOrder_by_no();
		final long new_order_by_no=order_by_no+1;
		
		String query="update send_interest set status=?,created_date=?,created_date_time=?,order_by_no=? where id=?";  
	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	      
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());
	    	ps.setInt(1,e.getStatus());        
	        ps.setDate(2,(java.sql.Date)createddate);
	        ps.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
	        ps.setLong(4,new_order_by_no);  
	        ps.setLong(5,e.getId());        
	        ps.execute(); 
	        return true;
	              
	    }  
	    });
		
	}
	
	//decline interest
		public Boolean declineinterest(final UserActivity e){
			String sql="select max(order_by_no) as order_by_no from send_interest";
		    
			UserActivity obj= template.queryForObject(sql, new Object[]{},new BeanPropertyRowMapper<UserActivity>(UserActivity.class)); 
			
			long order_by_no=obj.getOrder_by_no();
			final long new_order_by_no=order_by_no+1;
			
			String query="update send_interest set status=?,created_date=?,created_date_time=?,order_by_no=? where id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());
		    	ps.setInt(1,e.getStatus());        
		        ps.setDate(2,(java.sql.Date)createddate);
		        ps.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
		        ps.setLong(4,new_order_by_no);  
		        ps.setLong(5,e.getId());        
		        ps.execute(); 
		        return true;
		              
		    }  
		    });
			
		}
		
		//shortlist
		
		public int shortlist(final UserActivity e){
			
			String sql="select count(*) as totcount from shortlist s where (s.from_id=? and s.to_id=?)";
		    
			UserActivity obj= template.queryForObject(sql, new Object[]{e.getSender_id(),e.getReceiver_id()},new BeanPropertyRowMapper<UserActivity>(UserActivity.class));  
			
			if(obj.getTotcount()==0)
			{ 
						
						String query="insert into shortlist(from_id,to_id,created_date) values(?,?,?)";  
					    return template.execute(query,new PreparedStatementCallback<Integer>(){  
					      
					    public Integer doInPreparedStatement(PreparedStatement ps)  
					            throws SQLException, DataAccessException { 
					    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());
					    	ps.setLong(1,e.getSender_id());        
					        ps.setLong(2,e.getReceiver_id());  
					        ps.setDate(3,(java.sql.Date) createddate);
					        ps.execute();   
					        
					        
							return 1;
					              
					    }  
					});  
			}
			else
			{
				return 0;
			}
			
			
			
		}
		
		//get all shortlist user list on page load
		public List<User> get_all_usershortlist_on_page_load(long userid)
		{
			String oppositegender="";
			//get user details
			String sql1 = "SELECT u.gender FROM userinfo u WHERE u.email_verification_status=1 and u.status=1 and id=?";
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
			
			
			
			
			String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sht.id as shortlistid from userinfo u inner join shortlist sht on sht.to_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sht.from_id='"+userid+"' order by sht.id desc limit 0, "+Common_Info.perpage;
			
			return template.query(sql, new ResultSetExtractor<List<User>>() {
				public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
				
					List<User> list1=new ArrayList<User>();
					while(rs.next())
					{
						//check profile image
						String profile_img=rs.getString(5);
						if(profile_img==null)
						{
							profile_img="";
						}
						String state_name=rs.getString(12);
						if(state_name==null)
						{
							state_name="";
						}
						String city_name=rs.getString(13);
						if(city_name==null)
						{
							city_name="";
						}
						User uobj=new User();
						uobj.setId(rs.getLong(1));
						uobj.setName(rs.getString(2));
						uobj.setDob(rs.getString(3));
						uobj.setGender(rs.getString(4));
						uobj.setProfile_image(profile_img);
						uobj.setOccupation_name(rs.getString(6));
						uobj.setHeight_value(rs.getString(7));
						uobj.setReligion_name(rs.getString(8));
						uobj.setCaste_name(rs.getString(9));
						uobj.setHighest_education(rs.getString(10));
						uobj.setCountry_name(rs.getString(11));
						uobj.setState_name(state_name);
						uobj.setCity_name(city_name);
						uobj.setUsername(rs.getString(14));
						uobj.setMatrimony_id(rs.getString(15));
						uobj.setShortlist_id(rs.getLong(16));
						
						list1.add(uobj);
						
						
					}
					return list1;
				}
				
			});
		}
		//get all shortlist user list after reach bottom of page
		public List<User> get_all_usershortlist_after_reach_bottom_of_page(long lastpostid,long userid)
		{
			
			String oppositegender="";
			//get user details
			String sql1 = "SELECT u.gender FROM userinfo u WHERE u.email_verification_status=1 and u.status=1 and id=?";
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
			
			String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,sht.id as shortlistid from userinfo u inner join shortlist sht on sht.to_id=u.id inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and sht.from_id='"+userid+"' and sht.id <'"+lastpostid+"' order by sht.id desc limit  "+Common_Info.perpage;
			
			//System.out.print(sql);
			
			return template.query(sql, new ResultSetExtractor<List<User>>() {
				public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
				
					List<User> list1=new ArrayList<User>();
					while(rs.next())
					{
						String state_name=rs.getString(12);
						if(state_name==null)
						{
							state_name="";
						}
						String city_name=rs.getString(13);
						if(city_name==null)
						{
							city_name="";
						}
						
						//check profile image
						String profile_img=rs.getString(5);
						if(profile_img==null)
						{
							profile_img="";
						}
						User uobj=new User();
						uobj.setId(rs.getLong(1));
						uobj.setName(rs.getString(2));
						uobj.setDob(rs.getString(3));
						uobj.setGender(rs.getString(4));
						uobj.setProfile_image(profile_img);
						uobj.setOccupation_name(rs.getString(6));
						uobj.setHeight_value(rs.getString(7));
						uobj.setReligion_name(rs.getString(8));
						uobj.setCaste_name(rs.getString(9));
						uobj.setHighest_education(rs.getString(10));
						uobj.setCountry_name(rs.getString(11));
						uobj.setState_name(state_name);
						uobj.setCity_name(city_name);
						uobj.setUsername(rs.getString(14));
						uobj.setMatrimony_id(rs.getString(15));
						uobj.setShortlist_id(rs.getLong(16));
						
						list1.add(uobj);
						
						
					}
					return list1;
				}
				
			});    
		}
		
		//update profile image
		
		public Boolean updateprofileimage(final User e){
			
			String query="update userinfo u set profile_image=? where u.email_verification_status=1 and u.status=1 and id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		    	
		    	ps.setString(1,e.getProfile_image());   
		        ps.setLong(2,e.getId());        
		        ps.execute(); 
		        return true;
		              
		    }  
		    });
			
		}
		
		/*mail box notification*/
		public int check_if_mailbox_comes_count(long userid){
			
			String sql="select count(*) as totcount from send_interest s where ((s.receiver_id=? and s.status=? and pending_read_status=?) or (s.sender_id=? and s.status=? and accept_read_status=?) or (s.sender_id=? and s.status=? and reject_read_status=?))";
		    
			UserActivity obj= template.queryForObject(sql, new Object[]{userid,0,0,userid,1,0,userid,2,0},new BeanPropertyRowMapper<UserActivity>(UserActivity.class));  
			return obj.getTotcount();
		}
		
		public int update_mailbox_status_count(long userid,String type){
			
			String query="";
			if(type.equals("pending"))
			{
				 query="update send_interest set pending_read_status=1 where receiver_id='"+userid+"' and status=0 ";  
		 		
			}
			else if(type.equals("accepted"))
			{
				 query="update send_interest set accept_read_status=1 where sender_id='"+userid+"' and status=1 ";  
		 		
			}
			else if(type.equals("declined"))
			{
				 query="update send_interest set reject_read_status=1 where sender_id='"+userid+"' and status=2 ";  
		 		
			}
			return template.update(query);
		}
		
		/*message notify*/
		public int check_if_message_comes_count(long userid){
			
			String sql="select count(*) as totcount from message m where m.receiver_id=? and m.read_status=?";
		    
			UserActivity obj= template.queryForObject(sql, new Object[]{userid,0},new BeanPropertyRowMapper<UserActivity>(UserActivity.class));  
			return obj.getTotcount();
		}
}
	