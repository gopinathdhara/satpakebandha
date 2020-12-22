package com.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.beans.City;
import com.beans.Common_Info;
import com.beans.Email_Template;
import com.beans.Login;
import com.beans.Message;
import com.beans.Payment_Details;
import com.beans.Profile;
import com.beans.State;
import com.beans.User;  
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class AdminDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}  
	
	//check login
	public Long  login_check(Login obj)
	{
		//check if user is email verified and active status
		String sql = "SELECT u.id FROM admininfo u WHERE email=? AND password=?";
		try {
			long userId =template.queryForObject(sql, new Object[] { obj.getEmail(), getMd5(obj.getPassword()) },Long.class);
		
		
		return userId;
		}catch (Exception e) {
			return (long) 0;
		}
	}
	public static String getMd5(String input) 
    { 
	        try { 
	  
	            // Static getInstance method is called with hashing MD5 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	  
	            // digest() method is called to calculate message digest 
	            //  of an input digest() return array of byte 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	  
	            // Convert byte array into signum representation 
	            BigInteger no = new BigInteger(1, messageDigest); 
	  
	            // Convert message digest into hex value 
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }
	     // For specifying wrong message digest algorithms 
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
    }
	
	//get all user list
	public List<User> admin_get_user_list(long pageid,long total)
	{
		return template.query("select id,name,email,phone_no,gender,email_verification_status,status from userinfo order by id desc limit "+(pageid-1)+","+total, new ResultSetExtractor<List<User>>() {
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<User> list1=new ArrayList<User>();
				while(rs.next())
				{
					User stobj=new User();
					stobj.setId(rs.getInt(1));
					stobj.setName(rs.getString(2));
					stobj.setEmail(rs.getString(3));
					stobj.setPhone_no(rs.getString(4));
					stobj.setGender(rs.getString(5));
					stobj.setEmail_verification_status(rs.getInt(6));
					stobj.setStatus(rs.getInt(7));
					list1.add(stobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	// total record
    public User admin_get_user_list_total_record(){    
   	 
			String sql="select count(*) as totalrecord from userinfo";
		    
			return template.queryForObject(sql, new Object[]{},new BeanPropertyRowMapper<User>(User.class));    
		} 
    
    
  //transaction list
  		public List<Payment_Details> admin_all_transaction(long pageid,long total)
  	 	{
  	 		
  	 		 String sql="select p.id,p.from_date,p.to_date,p.total_amount,p.transaction_id,p.payment_type,p.package_title,p.package_duration,p.package_duration_type,p.package_discount_percentage,p.created_date,p.status,u.name from payment_details p inner join userinfo u on p.userinfo_id=u.id where transaction_id!='' order by id desc limit "+(pageid-1)+","+total;
  	 		
  	 		return template.query(sql, new ResultSetExtractor<List<Payment_Details>>() {
  	 			
  	 			public List<Payment_Details> extractData(ResultSet rs) throws SQLException, DataAccessException {
  	 				// TODO Auto-generated method stub
  	 			
  	 				List<Payment_Details> list1=new ArrayList<Payment_Details>();
  	 				while(rs.next())
  	 				{
  	 					
  	 					Payment_Details stobj=new Payment_Details();
  	 					stobj.setId(rs.getLong(1));
  	 					stobj.setFrom_date_new(rs.getString(2));
  	 					
  	 					stobj.setTo_date_new(rs.getString(3));
  	 					stobj.setTotal_amount(rs.getInt(4));
  	 					stobj.setTransaction_id(rs.getString(5));
  	 					stobj.setPayment_type(rs.getInt(6));
  	 					stobj.setPackage_title(rs.getString(7));
  	 					
  	 					stobj.setPackage_duration(rs.getInt(8));
  	 					stobj.setPackage_duration_type(rs.getString(9));
  	 					
  	 					stobj.setPackage_discount_percentage(rs.getInt(10));
  	 					stobj.setCreated_date_new(rs.getString(11));
  	 					stobj.setStatus(rs.getInt(12));
  	 					stobj.setName(rs.getString(13));
  	 					
  	 					list1.add(stobj);
  	 					
  	 				}
  	 				return list1;
  	 			}
  	 			
  	 		});
  	 	}
  	     // total record
  	     public Payment_Details admin_all_transaction_total_record(){    
  	    	 
  				String sql="select count(*) as totalrecord from payment_details where transaction_id!=''";
  			    
  				return template.queryForObject(sql, new Object[]{},new BeanPropertyRowMapper<Payment_Details>(Payment_Details.class));    
  			} 
  	     
  	     
  	     //cash on delivery payment status update
  	   public Boolean admin_update_transaction_status(final long payment_id)
		{
			String query="update payment_details set status=? where id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		        ps.setInt(1,1); // Make active payment 
		        ps.setLong(2,payment_id);
		        ps.execute(); 
				return true;
		              
		    }  
		    }); 
		}
  	   
  	   //change account status
  	 public Boolean admin_update_account_status(final long user_id,final int status)
		{
			String query="update userinfo set status=? where id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		        ps.setInt(1,status); // Make active payment 
		        ps.setLong(2,user_id);
		        ps.execute(); 
				return true;
		              
		    }  
		    }); 
		}
  	 
  	 //change email verification status
  	public Boolean admin_update_email_status(final long user_id,final int status)
	{
		String query="update userinfo set email_verification_status=? where id=?";  
	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	      
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	        ps.setInt(1,status); // Make active payment 
	        ps.setLong(2,user_id);
	        ps.execute(); 
			return true;
	              
	    }  
	    }); 
	}
  	 
}