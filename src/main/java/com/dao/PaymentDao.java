package com.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.beans.City;
import com.beans.Login;
import com.beans.Membership_Package;
import com.beans.Message;
import com.beans.Payment_Details;
import com.beans.Profile;
import com.beans.State;
import com.beans.User;  
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class PaymentDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}    
	
	//membership package details
	public List<Membership_Package> get_membership_package_list()
	{
		return template.query("select id,title,duration,duration_type,discount_percentage,original_price,paid_amount,status,type from membership_package where status=1 and type=0 order by id desc", new ResultSetExtractor<List<Membership_Package>>() {
			public List<Membership_Package> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<Membership_Package> list1=new ArrayList<Membership_Package>();
				while(rs.next())
				{
					Membership_Package obj=new Membership_Package();
					obj.setId(rs.getInt(1));
					obj.setTitle(rs.getString(2));
					obj.setDuration(rs.getInt(3));
					obj.setDuration_type(rs.getString(4));
					obj.setDiscount_percentage(rs.getInt(5));
					obj.setOriginal_price(rs.getInt(6));
					obj.setPaid_amount(rs.getInt(7));
					obj.setStatus(rs.getInt(8));
					obj.setType(rs.getInt(9));
					list1.add(obj);
					
					
				}
				return list1;
			}
			
		});
	}
	//single package details
	public Membership_Package getmembership_package_by_id(int packid){    
		String sql="select id,title,duration,duration_type,discount_percentage,original_price,paid_amount,status,type from membership_package where status=1 and id=?";
	    
		return template.queryForObject(sql, new Object[]{packid},new BeanPropertyRowMapper<Membership_Package>(Membership_Package.class));    
	}  
	
	// insert into online payment
public Boolean saveorderinfo(final Payment_Details e){  
		
		
	    String query="insert into payment_details(userinfo_id,membership_package_id,status,from_date,to_date,total_amount,order_id,created_date,payment_type,package_title,package_duration,package_duration_type,package_discount_percentage,package_original_price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	      
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());//created date
	    	String createddate_string = java.sql.Date.valueOf(e.getCreated_date()).toString();
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar c = Calendar.getInstance();
	    	try{
	    		   //Setting the date to the given date
	    		   c.setTime(sdf.parse(createddate_string));
	    	}catch(ParseException e){
	    			e.printStackTrace();
	    	}
	    	//add month or year
	    	if(e.getDuration_type().equals("month"))
	    	{
	    		
	    	    	c.add(Calendar.MONTH, e.getDuration());
	    		
	    	}
	    	else if(e.getDuration_type().equals("year"))
	    	{
	    		
	    	    	c.add(Calendar.YEAR, e.getDuration());
	    		
	    	}
	    	  
	    	String toDate = sdf.format(c.getTime()); // todate after adding duration
	    	
	    	ps.setLong(1,e.getUserinfo_id());        
	        ps.setInt(2,e.getMembership_package_id());  
	        ps.setInt(3,0);
	        ps.setDate(4,(java.sql.Date) createddate);
	        ps.setDate(5,(java.sql.Date) java.sql.Date.valueOf(toDate));  
	        ps.setInt(6,e.getTotal_amount());        
	        ps.setString(7,e.getOrder_id());  
	        ps.setDate(8,(java.sql.Date) createddate);
	        ps.setInt(9,e.getPayment_type());
	        ps.setString(10,e.getPackage_title());
	        ps.setInt(11,e.getPackage_duration());
	        ps.setString(12,e.getPackage_duration_type());
	        ps.setInt(13,e.getPackage_discount_percentage());
	        ps.setInt(14,e.getPackage_original_price());
	        ps.execute(); 
	        
			return true;
	              
	    }  
	    });  
	}

	// edit profile details  
	public Profile userdetails(long id){    
	    String sql="select * from userinfo u where u.email_verification_status=1 and u.status=1 and id=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Profile>(Profile.class));    
	}
	
	// get payment details  
		public String paymentdetails(Payment_Details pdobjct){ 
			
		    String sql="select * from payment_details where order_id=?";    
		    Payment_Details obj= template.queryForObject(sql, new Object[]{pdobjct.getOrder_id()},new BeanPropertyRowMapper<Payment_Details>(Payment_Details.class));
		    
			return obj.getOrder_id();    
		}
		// update payment status
		public Boolean update_payment_status(final Payment_Details pdobjct)
		{
			String query="update payment_details set status=?,transaction_id=? where order_id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		        ps.setInt(1,1); // Make active payment 
		        ps.setString(2,pdobjct.getTransaction_id());
		        ps.setString(3,pdobjct.getOrder_id());
		        ps.execute(); 
		        
				return true;
		              
		    }  
		    }); 
		}
		
		//transaction list
		public List<Payment_Details> my_all_transaction(long userid,long pageid,long total)
	 	{
	 		
	 		 String sql="select id,from_date,to_date,total_amount,transaction_id,payment_type,package_title,package_duration,package_duration_type,package_discount_percentage,created_date,status from payment_details where userinfo_id='"+userid+"'  order by id desc limit "+(pageid-1)+","+total;
	 		
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
	 					
	 					list1.add(stobj);
	 					
	 				}
	 				return list1;
	 			}
	 			
	 		});
	 	}
	     // total record
	     public Payment_Details my_all_transaction_total_record(long userid){    
	    	 
				String sql="select count(*) as totalrecord from payment_details where userinfo_id=?";
			    
				return template.queryForObject(sql, new Object[]{userid},new BeanPropertyRowMapper<Payment_Details>(Payment_Details.class));    
			} 
	     
	     //insert into cash on delivery payment
	     
	     public Boolean saveorderinfo_cash(final Payment_Details e){  
	 		
	 		
	 	    String query="insert into payment_details(userinfo_id,membership_package_id,status,from_date,to_date,total_amount,transaction_id,created_date,payment_type,package_title,package_duration,package_duration_type,package_discount_percentage,package_original_price) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
	 	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	 	      
	 	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	 	            throws SQLException, DataAccessException { 
	 	    	
	 	    	Date createddate = java.sql.Date.valueOf(e.getCreated_date());//created date
	 	    	String createddate_string = java.sql.Date.valueOf(e.getCreated_date()).toString();
	 	    	
	 	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 	    	Calendar c = Calendar.getInstance();
	 	    	try{
	 	    		   //Setting the date to the given date
	 	    		   c.setTime(sdf.parse(createddate_string));
	 	    	}catch(ParseException e){
	 	    			e.printStackTrace();
	 	    	}
	 	    	//add month or year
	 	    	if(e.getDuration_type().equals("month"))
	 	    	{
	 	    		
	 	    	    	c.add(Calendar.MONTH, e.getDuration());
	 	    		
	 	    	}
	 	    	else if(e.getDuration_type().equals("year"))
	 	    	{
	 	    		
	 	    	    	c.add(Calendar.YEAR, e.getDuration());
	 	    		
	 	    	}
	 	    	  
	 	    	String toDate = sdf.format(c.getTime()); // todate after adding duration
	 	    	
	 	    	ps.setLong(1,e.getUserinfo_id());        
	 	        ps.setInt(2,e.getMembership_package_id());  
	 	        ps.setInt(3,0);
	 	        ps.setDate(4,(java.sql.Date) createddate);
	 	        ps.setDate(5,(java.sql.Date) java.sql.Date.valueOf(toDate));  
	 	        ps.setInt(6,e.getTotal_amount());        
	 	        ps.setString(7,e.getTransaction_id());  
	 	        ps.setDate(8,(java.sql.Date) createddate);
	 	        ps.setInt(9,e.getPayment_type());
	 	        ps.setString(10,e.getPackage_title());
	 	        ps.setInt(11,e.getPackage_duration());
	 	        ps.setString(12,e.getPackage_duration_type());
	 	        ps.setInt(13,e.getPackage_discount_percentage());
	 	        ps.setInt(14,e.getPackage_original_price());
	 	        ps.execute(); 
	 	        
	 			return true;
	 	              
	 	    }  
	 	    });  
	 	}
	   //check if user is premium user
	     public Boolean check_ifpremium_user(long userid){    
	    	 
				String sql="select count(*) as totalrecord from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=?";
			    
				User uobj= template.queryForObject(sql, new Object[]{userid},new BeanPropertyRowMapper<User>(User.class));
				if(uobj.getTotalrecord()>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				
			} 
	     
	     //view phone no if user is premium user
	     public User get_phoneno_user(long receiver_id){ 
	    	 
				 String sql="select * from userinfo u where u.email_verification_status=1 and u.status=1 and id=?";    
				 return template.queryForObject(sql, new Object[]{receiver_id},new BeanPropertyRowMapper<User>(User.class));    
			
	     }
}