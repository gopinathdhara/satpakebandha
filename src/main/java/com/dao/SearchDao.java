package com.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.beans.City;
import com.beans.Common_Info;
import com.beans.Login;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;  
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
public class SearchDao {
	
	private JdbcTemplate template;   
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {   
	    this.template = jdbcTemplate;  
	}    
	
	//PreparedStatement to prevent sql injection
	// save regular search
	public Boolean save_regular_search(final Regular_Search e){  
		
		String sId;
		String sql = "SELECT r.id FROM regular_search_info r WHERE r.userinfo_id=?";
		try {
		  sId =template.queryForObject(sql, new Object[] {e.getUserinfo_id() },String.class);
		
		}catch (Exception e1) {
			sId="0";
		} 
		
		//if already record exist then update it
		if(!sId.equals("0"))
		{
			String query="update regular_search_info set age_form=?,age_to=?,religion_id=?,mother_tongue_id=?,caste_info_id=?,marital_status_id=?,height_info_from_id=?,height_info_to_id=?,country_id=?,state_id=?,city_id=?,highest_education_id=?,annual_income_id=?,employed_in_id=?,occupation_info_id=? where  userinfo_id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		        ps.setInt(1,e.getAge_form());  
		        ps.setInt(2,e.getAge_to());
		        ps.setInt(3,e.getReligion_id());
		       
		        
		        ps.setString(4,e.getMother_tongue_id());        
		        ps.setString(5,e.getCaste_info_id());  
		        ps.setString(6,e.getMarital_status_id());
		      
		        ps.setInt(7,e.getHeight_info_from_id());   
		        
		        ps.setInt(8,e.getHeight_info_to_id());        
		        ps.setInt(9,e.getCountry_id());  
		        ps.setInt(10,e.getState_id());
		        ps.setString(11,e.getCity_id());
		        ps.setString(12,e.getHighest_education_id());
		        ps.setString(13,e.getAnnual_income_id());
		        ps.setString(14,e.getEmployed_in_id());
		        ps.setString(15,e.getOccupation_info_id());
		        
		        ps.setLong(16,e.getUserinfo_id());
		        return ps.execute();  
		        
		    }

		    });  
		}
		else
		{
			//insert record
			String query="insert into regular_search_info(userinfo_id,age_form,age_to,religion_id,mother_tongue_id,caste_info_id,marital_status_id,height_info_from_id,height_info_to_id,country_id,state_id,city_id,highest_education_id,annual_income_id,employed_in_id,occupation_info_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		    	ps.setLong(1,e.getUserinfo_id());        
		        ps.setInt(2,e.getAge_form());  
		        ps.setInt(3,e.getAge_to());
		        ps.setInt(4,e.getReligion_id());
		       
		        
		        ps.setString(5,e.getMother_tongue_id());        
		        ps.setString(6,e.getCaste_info_id());  
		        ps.setString(7,e.getMarital_status_id());
		      
		        ps.setInt(8,e.getHeight_info_from_id());   
		        
		        ps.setInt(9,e.getHeight_info_to_id());        
		        ps.setInt(10,e.getCountry_id());  
		        ps.setInt(11,e.getState_id());
		        ps.setString(12,e.getCity_id());
		        ps.setString(13,e.getHighest_education_id());
		        ps.setString(14,e.getAnnual_income_id());
		        ps.setString(15,e.getEmployed_in_id());
		        ps.setString(16,e.getOccupation_info_id());
		        
		        return ps.execute();  
		        
		              
		      }

			 
		    }); 
		} 
	    
	}
	
//save multiple search info	
public boolean save_search_info(final Regular_Search e){  
		
	    String query="insert into save_search_info(userinfo_id,age_form,age_to,religion_id,mother_tongue_id,caste_info_id,marital_status_id,height_info_from_id,height_info_to_id,country_id,state_id,city_id,highest_education_id,annual_income_id,employed_in_id,occupation_info_id,search_name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	      
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	    	ps.setLong(1,e.getUserinfo_id());        
	        ps.setInt(2,e.getAge_form());  
	        ps.setInt(3,e.getAge_to());
	        ps.setInt(4,e.getReligion_id());
	       
	        
	        ps.setString(5,e.getMother_tongue_id());        
	        ps.setString(6,e.getCaste_info_id());  
	        ps.setString(7,e.getMarital_status_id());
	      
	        ps.setInt(8,e.getHeight_info_from_id());   
	        
	        ps.setInt(9,e.getHeight_info_to_id());        
	        ps.setInt(10,e.getCountry_id());  
	        ps.setInt(11,e.getState_id());
	        ps.setString(12,e.getCity_id());
	        ps.setString(13,e.getHighest_education_id());
	        ps.setString(14,e.getAnnual_income_id());
	        ps.setString(15,e.getEmployed_in_id());
	        ps.setString(16,e.getOccupation_info_id());
	        ps.setString(17,e.getSearch_name());
	        
	        return ps.execute();  
	            
	    }  
	    });  
	    
	}
	
	//get all regular search result data on page load
	public List<User> get_all_regularsearchlist_on_page_load(long userid,long searchid,String paramtype)
	{
		Regular_Search rsobj=null;
		
		String bodytype1="";
		String complxval1="";
		String phystval1="";
		String eathabval1="";
		String drnhabval1="";
		String smhabval1="";
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//call partner_preferences  table
			 rsobj=this.get_partnermatch_details(userid);
			 String bodytype=Arrays.toString(rsobj.getBody_type());
			 bodytype1= bodytype.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();  
			 //System.out.println("bodytyyype"+bodytype1);
					    
					    
		    String complxval=Arrays.toString(rsobj.getComplexion());
		    complxval1= complxval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    
		    
		    String phystval=Arrays.toString(rsobj.getPhysical_status());
		    phystval1= phystval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String eathabval=Arrays.toString(rsobj.getEating_habits());
		    eathabval1= eathabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String drnhabval=Arrays.toString(rsobj.getDrinking_habits());
		    drnhabval1= drnhabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String smhabval=Arrays.toString(rsobj.getSmoking_habits());
		    smhabval1= smhabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    
		}
		else if(searchid==0)
		{
			//call regular search table
			 rsobj=this.get_regular_searchdetails(userid);
		}else {
			//call save search table
			 rsobj=this.get_save_search_info(userid,searchid);
		}
		
		
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
		
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"'";
		
		//for new partner match
		if(paramtype.equals("allpartnernewmatch"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}
		//for premium matches
		if(paramtype.equals("allpartnerpremiummatch"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
		//age
		if(rsobj.getAge_form()>0 && rsobj.getAge_to()>0)
		{
			sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+rsobj.getAge_form()+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+rsobj.getAge_to();
		}
		
		//religion
		if(rsobj.getReligion_id()!=0)
		{
			sql+=" and u.religion_id="+rsobj.getReligion_id();
		}
		
		//mother tongue
		if(!rsobj.getMother_tongue_id().equals("0"))
		{
			sql+=" and u.mother_tongue_id in ( "+rsobj.getMother_tongue_id()+" )";
		}
		//caste
		if(!rsobj.getCaste_info_id().equals("0"))
		{
			sql+=" and u.caste_info_id in ( "+rsobj.getCaste_info_id()+" )";
		}
		//marital status
		if(!rsobj.getMarital_status_id().equals("0"))
		{
			sql+=" and u.marital_status_id in ( "+rsobj.getMarital_status_id()+" )";
		}
		//height
		if(rsobj.getHeight_info_from_id()>0 && rsobj.getHeight_info_to_id()>0)
		{
			sql+=" and u.height_info_id >= "+rsobj.getHeight_info_from_id()+" and u.height_info_id <="+rsobj.getHeight_info_to_id();
		}
		//country
		if(rsobj.getCountry_id()>0)
		{
			sql+=" and u.country_id = "+rsobj.getCountry_id();
		}
		//state
		if(rsobj.getState_id()>0)
		{
			sql+=" and u.state_id = "+rsobj.getState_id();
		}
		//city
		if(!rsobj.getCity_id().equals("0"))
		{
			sql+=" and u.city_id in ( "+rsobj.getCity_id()+" )";
		}
		//highest education 
		if(!rsobj.getHighest_education_id().equals("0"))
		{
			sql+=" and u.highest_education_id in ( "+rsobj.getHighest_education_id()+" )";
		}
		//annual income
		if(!rsobj.getAnnual_income_id().equals("0"))
		{
			sql+=" and u.annual_income_id in ( "+rsobj.getAnnual_income_id()+" )";
		}
		//employed in
		if(!rsobj.getEmployed_in_id().equals("0"))
		{
			sql+=" and u.employed_in_id in ( "+rsobj.getEmployed_in_id()+" )";
		}
		//occupation
		if(!rsobj.getOccupation_info_id().equals("0"))
		{
			sql+=" and u.occupation_info_id in ( "+rsobj.getOccupation_info_id()+" )";
		}
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//other basic criteria
			if(!bodytype1.equals("0"))
			{
				
				sql+=" and u.body_type in ( "+bodytype1+" )";
			}
			if(!complxval1.equals("0"))
			{
				sql+=" and u.complexion in ( "+complxval1+" )";
			}
			if(!phystval1.equals("0"))
			{
				sql+=" and u.physical_status in ( "+phystval1+" )";
			}
			if(!eathabval1.equals("0"))
			{
				sql+=" and u.eating_habits in ( "+eathabval1+" )";
			}
			if(!drnhabval1.equals("0"))
			{
				sql+=" and u.drinking_habits in ( "+drnhabval1+" )";
			}
			if(!smhabval1.equals("0"))
			{
				sql+=" and u.smoking_habits in ( "+smhabval1+" )";
			}
		}
		
		
		//System.out.print(sql);
		sql+=" order by u.id desc limit 0,"+Common_Info.perpage;
		
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
					
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	
	//get regular search list when reach bottom of page
	public List<User> get_all_regularsearchlist_after_reach_bottom_of_page(long lastpostid,long userid,long searchid,String paramtype)
	{
		//Regular_Search rsobj=this.get_regular_searchdetails(userid);
		
		Regular_Search rsobj=null;
		
		
		String bodytype1="";
		String complxval1="";
		String phystval1="";
		String eathabval1="";
		String drnhabval1="";
		String smhabval1="";
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//call partner_preferences  table
			 rsobj=this.get_partnermatch_details(userid);
			 String bodytype=Arrays.toString(rsobj.getBody_type());
			 bodytype1= bodytype.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();  
			 //System.out.println("bodytyyype"+bodytype1);
					    
					    
		    String complxval=Arrays.toString(rsobj.getComplexion());
		    complxval1= complxval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    
		    
		    String phystval=Arrays.toString(rsobj.getPhysical_status());
		    phystval1= phystval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String eathabval=Arrays.toString(rsobj.getEating_habits());
		    eathabval1= eathabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String drnhabval=Arrays.toString(rsobj.getDrinking_habits());
		    drnhabval1= drnhabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    String smhabval=Arrays.toString(rsobj.getSmoking_habits());
		    smhabval1= smhabval.replace("[", "")  //remove the right bracket
					    .replace("]", "")  //remove the left bracket
					    .trim();
		    
		    
		}
		else if(searchid==0)
		{
			//call regular search table
			 rsobj=this.get_regular_searchdetails(userid);
		}else {
			//call save search table
			 rsobj=this.get_save_search_info(userid,searchid);
		}
		
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
		
		
String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.id <'"+lastpostid+"' ";
		
		//for new partner match
		if(paramtype.equals("allpartnernewmatch"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}

		//for premium matches
		if(paramtype.equals("allpartnerpremiummatch"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
				
		//age
		if(rsobj.getAge_form()>0 && rsobj.getAge_to()>0)
		{
			sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+rsobj.getAge_form()+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+rsobj.getAge_to();
		}
		
		//religion
		if(rsobj.getReligion_id()!=0)
		{
			sql+=" and u.religion_id="+rsobj.getReligion_id();
		}
		
		//mother tongue
		if(!rsobj.getMother_tongue_id().equals("0"))
		{
			sql+=" and u.mother_tongue_id in ( "+rsobj.getMother_tongue_id()+" )";
		}
		//caste
		if(!rsobj.getCaste_info_id().equals("0"))
		{
			sql+=" and u.caste_info_id in ( "+rsobj.getCaste_info_id()+" )";
		}
		//marital status
		if(!rsobj.getMarital_status_id().equals("0"))
		{
			sql+=" and u.marital_status_id in ( "+rsobj.getMarital_status_id()+" )";
		}
		//height
		if(rsobj.getHeight_info_from_id()>0 && rsobj.getHeight_info_to_id()>0)
		{
			sql+=" and u.height_info_id >= "+rsobj.getHeight_info_from_id()+" and u.height_info_id <="+rsobj.getHeight_info_to_id();
		}
		//country
		if(rsobj.getCountry_id()>0)
		{
			sql+=" and u.country_id = "+rsobj.getCountry_id();
		}
		//state
		if(rsobj.getState_id()>0)
		{
			sql+=" and u.state_id = "+rsobj.getState_id();
		}
		//city
		if(!rsobj.getCity_id().equals("0"))
		{
			sql+=" and u.city_id in ( "+rsobj.getCity_id()+" )";
		}
		//highest education 
		if(!rsobj.getHighest_education_id().equals("0"))
		{
			sql+=" and u.highest_education_id in ( "+rsobj.getHighest_education_id()+" )";
		}
		//annual income
		if(!rsobj.getAnnual_income_id().equals("0"))
		{
			sql+=" and u.annual_income_id in ( "+rsobj.getAnnual_income_id()+" )";
		}
		//employed in
		if(!rsobj.getEmployed_in_id().equals("0"))
		{
			sql+=" and u.employed_in_id in ( "+rsobj.getEmployed_in_id()+" )";
		}
		//occupation
		if(!rsobj.getOccupation_info_id().equals("0"))
		{
			sql+=" and u.occupation_info_id in ( "+rsobj.getOccupation_info_id()+" )";
		}
		
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//other basic criteria
			
			if(!bodytype1.equals("0"))
			{
				
				sql+=" and u.body_type in ( "+bodytype1+" )";
			}
			if(!complxval1.equals("0"))
			{
				sql+=" and u.complexion in ( "+complxval1+" )";
			}
			if(!phystval1.equals("0"))
			{
				sql+=" and u.physical_status in ( "+phystval1+" )";
			}
			if(!eathabval1.equals("0"))
			{
				sql+=" and u.eating_habits in ( "+eathabval1+" )";
			}
			if(!drnhabval1.equals("0"))
			{
				sql+=" and u.drinking_habits in ( "+drnhabval1+" )";
			}
			if(!smhabval1.equals("0"))
			{
				sql+=" and u.smoking_habits in ( "+smhabval1+" )";
			}
		}
		
		
		//System.out.print(sql);
		sql+=" order by u.id desc limit "+Common_Info.perpage;
		
		
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
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});    
	}
	
	
	//get regular search info 
	public Regular_Search get_regular_searchdetails(long userid){    
	    String sql="select * from regular_search_info where userinfo_id=?";
	    try {
	    	return template.queryForObject(sql, new Object[]{userid},new BeanPropertyRowMapper<Regular_Search>(Regular_Search.class)); 
	    }catch(Exception e) {
	    	return null;
	    }
	       
	}  
	//get single saved search info 
		public Regular_Search get_save_search_info(long userid,long searchid){    
		    String sql="select * from save_search_info where userinfo_id=? and id=?";    
		    return template.queryForObject(sql, new Object[]{userid,searchid},new BeanPropertyRowMapper<Regular_Search>(Regular_Search.class));    
		}  
	
	//get state list for editing regular search
	public List<State> get_state_list_by_country_id(String cid)
	{
		return template.query("select id,name from states where country_id='"+cid+"' order by name asc", new ResultSetExtractor<List<State>>() {
			public List<State> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<State> list1=new ArrayList<State>();
				while(rs.next())
				{
					State stobj=new State();
					stobj.setId(rs.getInt(1));
					stobj.setName(rs.getString(2));
					
					list1.add(stobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	//get city list for editing regular search
	public List<City> get_city_list_by_state_id(String sid)
	{
		return template.query("select id,name from cities where state_id='"+sid+"' order by name asc", new ResultSetExtractor<List<City>>() {
			public List<City> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
			
				List<City> list1=new ArrayList<City>();
				while(rs.next())
				{ 
					City stobj=new City();
					stobj.setId(rs.getInt(1));
					stobj.setName(rs.getString(2));
					
					list1.add(stobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	//get multiple save search details
	public List<Regular_Search> getsavesearchdetails(long uid){  
		 return template.query("select id,search_name from save_search_info where userinfo_id="+uid+" order by id desc",new ResultSetExtractor<List<Regular_Search>>(){  
		    public List<Regular_Search> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Regular_Search> list=new ArrayList<Regular_Search>();  
		        while(rs.next()){  
			        Regular_Search e=new Regular_Search();  
			        e.setId(rs.getInt(1));  
			        e.setSearch_name(rs.getString(2));  
			        list.add(e);  
		        }  
		        	return list;  
		        }  
		    });  
		  }
	
	//saved search details
	public Regular_Search get_regular_saved_searchdetails(long userid,long id){    
	    String sql="select * from save_search_info where userinfo_id=? and id=?";    
	    return template.queryForObject(sql, new Object[]{userid,id},new BeanPropertyRowMapper<Regular_Search>(Regular_Search.class));    
	} 
	
	//update saved search details
	
	public Boolean update_saved_search(final Regular_Search e){  
		
	    String query="update save_search_info set age_form=?,age_to=?,religion_id=?,mother_tongue_id=?,caste_info_id=?,marital_status_id=?,height_info_from_id=?,height_info_to_id=?,country_id=?,state_id=?,city_id=?,highest_education_id=?,annual_income_id=?,employed_in_id=?,occupation_info_id=? where id=? and userinfo_id=?";  
	    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
	      
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	        ps.setInt(1,e.getAge_form());  
	        ps.setInt(2,e.getAge_to());
	        ps.setInt(3,e.getReligion_id());
	       
	        
	        ps.setString(4,e.getMother_tongue_id());        
	        ps.setString(5,e.getCaste_info_id());  
	        ps.setString(6,e.getMarital_status_id());
	      
	        ps.setInt(7,e.getHeight_info_from_id());   
	        
	        ps.setInt(8,e.getHeight_info_to_id());        
	        ps.setInt(9,e.getCountry_id());  
	        ps.setInt(10,e.getState_id());
	        ps.setString(11,e.getCity_id());
	        ps.setString(12,e.getHighest_education_id());
	        ps.setString(13,e.getAnnual_income_id());
	        ps.setString(14,e.getEmployed_in_id());
	        ps.setString(15,e.getOccupation_info_id());
	      
	        ps.setLong(16,e.getId());
	        ps.setLong(17, e.getUserinfo_id());
	        return ps.execute();  
	        
	    }

	    });  
	    
	}
	
	//delete saved search
	public int savedsearchdelete(long userid,long id){    
	    String sql="delete from save_search_info where id="+id+" and userinfo_id="+userid;    
	    return template.update(sql);    
	}  
	
	//partner preferences
	public Regular_Search get_partner_preferences(long userid){    
	    String sql="select * from partner_preferences where userinfo_id=?";    
	    return template.queryForObject(sql, new Object[]{userid},new BeanPropertyRowMapper<Regular_Search>(Regular_Search.class));    
	} 
	
	//save partner preferences
	
public Boolean partner_preferences_save(final Regular_Search e){  
		
		String sId;
		String sql = "SELECT r.id FROM partner_preferences r WHERE r.userinfo_id=?";
		try {
		  sId =template.queryForObject(sql, new Object[] {e.getUserinfo_id() },String.class);
		
		}catch (Exception e1) {
			sId="0";
		}
		
		try
		{
			String[] body_type =e.getBody_type();
			String body_type1="";
			if(body_type.length>0)
			{
				 body_type1 = String.join(",", body_type);
				 e.setBody_type1(body_type1);
			}
			else
			{
				body_type1="0";
				e.setBody_type1(body_type1);
			}
		}catch(Exception e1)
		{
			e.setBody_type1("0");
		}
		try
		{
			String[] physical_sts =e.getPhysical_status();
			String physical_sts1="";
			if(physical_sts.length>0)
			{
				 physical_sts1 = String.join(",", physical_sts);
				 e.setPhysical_status1(physical_sts1);
			}
			else
			{
				physical_sts1="0";
				e.setPhysical_status1(physical_sts1);
			}
		}catch(Exception e1)
		{
			e.setPhysical_status1("0");
		}
		
		try
		{
			String[] eating_habts =e.getEating_habits();
			String eating_habts1="";
			if(eating_habts.length>0)
			{
				 eating_habts1 = String.join(",", eating_habts);
				 e.setEating_habits1(eating_habts1);
			}
			else
			{
				eating_habts1="0";
				e.setEating_habits1(eating_habts1);
			}
		}catch(Exception e1)
		{
			e.setEating_habits1("0");
		}
		
		try
		{
			String[] drink_habts =e.getDrinking_habits();
			String drink_habts1="";
			if(drink_habts.length>0)
			{
				 drink_habts1 = String.join(",", drink_habts);
				 e.setDrinking_habits1(drink_habts1);
			}
			else
			{
				drink_habts1="0";
				e.setDrinking_habits1(drink_habts1);
			}
		}catch(Exception e1)
		{
			e.setDrinking_habits1("0");
		}
		
		try
		{
			String[] smoke_habts =e.getSmoking_habits();
			String smoke_habts1="";
			if(smoke_habts.length>0)
			{
				 smoke_habts1 = String.join(",", smoke_habts);
				 e.setSmoking_habits1(smoke_habts1);
			}
			else
			{
				 smoke_habts1="0";
				 e.setSmoking_habits1(smoke_habts1);
			}
		}catch(Exception e1)
		{
			e.setSmoking_habits1("0");
		}
		
		
		try
		{
			String[] complx =e.getComplexion();
			String complx1="";
			if(complx.length>0)
			{
				 complx1 = String.join(",", complx);
				 e.setComplexion1(complx1);
			}
			else
			{
				 complx1="0";
				 e.setComplexion1(complx1);
			}
		}catch(Exception e1)
		{
			 e.setComplexion1("0");
		}
		
		
		//if already record exist then update it
		if(!sId.equals("0"))
		{
			String query="update partner_preferences set age_form=?,age_to=?,religion_id=?,mother_tongue_id=?,caste_info_id=?,marital_status_id=?,height_info_from_id=?,height_info_to_id=?,country_id=?,state_id=?,city_id=?,highest_education_id=?,annual_income_id=?,employed_in_id=?,occupation_info_id=?,body_type=?,complexion=?,physical_status=?,eating_habits=?,drinking_habits=?,smoking_habits=? where  userinfo_id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		        ps.setInt(1,e.getAge_form());  
		        ps.setInt(2,e.getAge_to());
		        ps.setInt(3,e.getReligion_id());
		       
		        
		        ps.setString(4,e.getMother_tongue_id());        
		        ps.setString(5,e.getCaste_info_id());  
		        ps.setString(6,e.getMarital_status_id());
		      
		        ps.setInt(7,e.getHeight_info_from_id());   
		        
		        ps.setInt(8,e.getHeight_info_to_id());        
		        ps.setInt(9,e.getCountry_id());  
		        ps.setInt(10,e.getState_id());
		        ps.setString(11,e.getCity_id());
		        ps.setString(12,e.getHighest_education_id());
		        ps.setString(13,e.getAnnual_income_id());
		        ps.setString(14,e.getEmployed_in_id());
		        ps.setString(15,e.getOccupation_info_id());
		        
		        ps.setString(16,e.getBody_type1());
		        ps.setString(17,e.getComplexion1());
		        ps.setString(18,e.getPhysical_status1());
		        ps.setString(19,e.getEating_habits1());
		        ps.setString(20,e.getDrinking_habits1());
		        ps.setString(21,e.getSmoking_habits1());
		        
		        ps.setLong(22, e.getUserinfo_id());
		        return ps.execute();  
		        
		    }

		    });  
		}
		else
		{
			//insert record
			String query="insert into partner_preferences(userinfo_id,age_form,age_to,religion_id,mother_tongue_id,caste_info_id,marital_status_id,height_info_from_id,height_info_to_id,country_id,state_id,city_id,highest_education_id,annual_income_id,employed_in_id,occupation_info_id,body_type,complexion,physical_status,eating_habits,drinking_habits,smoking_habits) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		    	ps.setLong(1,e.getUserinfo_id());        
		        ps.setInt(2,e.getAge_form());  
		        ps.setInt(3,e.getAge_to());
		        ps.setInt(4,e.getReligion_id());
		       
		        
		        ps.setString(5,e.getMother_tongue_id());        
		        ps.setString(6,e.getCaste_info_id());  
		        ps.setString(7,e.getMarital_status_id());
		      
		        ps.setInt(8,e.getHeight_info_from_id());   
		        
		        ps.setInt(9,e.getHeight_info_to_id());        
		        ps.setInt(10,e.getCountry_id());  
		        ps.setInt(11,e.getState_id());
		        ps.setString(12,e.getCity_id());
		        ps.setString(13,e.getHighest_education_id());
		        ps.setString(14,e.getAnnual_income_id());
		        ps.setString(15,e.getEmployed_in_id());
		        ps.setString(16,e.getOccupation_info_id());
		        
		        ps.setString(17,e.getBody_type1());
		        ps.setString(18,e.getComplexion1());
		        ps.setString(19,e.getPhysical_status1());
		        ps.setString(20,e.getEating_habits1());
		        ps.setString(21,e.getDrinking_habits1());
		        ps.setString(22,e.getSmoking_habits1());
		        
		        return ps.execute();  
		        
		              
		      }

			 
		    }); 
		} 
	    
	}

	//get partner match details

	public Regular_Search get_partnermatch_details(long userid){ 
		try {
			String sql="select * from partner_preferences where userinfo_id=?";    
		    return template.queryForObject(sql, new Object[]{userid},new BeanPropertyRowMapper<Regular_Search>(Regular_Search.class)); 
		}catch(Exception e) {
			return null;
		}
	       
	} 
	
	
	
	//########################advance search in sidebar##########################
	
	public List<User> get_all_advanceregularsearchlist_on_page_load(long userid,long searchid,String paramtype,String mothertongueArray,String religionadvsrnm,String casteadvsrnm,int age_form,int age_to,int height_info_from_id,int height_info_to_id,String maritalsts,String higheducadvsrnm,String annlincmadvsrnm,String empinadvsrnm,String occplistadvsrnm,String cntrylistadvsrnm,String statelistadvsrnm,String bodytype1,String complxval1,String phystval1,String eathabval1,String drnhabval1,String smhabval1)
	{
		
		String oppositegender=this.getoppositegender(userid);
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,mt.name as mother_tonue_name from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id  left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"'";
		
		//for new user
		if(paramtype.equals("new"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}
		
		//for premium user
		if(paramtype.equals("premium"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
		
		
		//for new partner match
		if(paramtype.equals("allpartnernewmatch"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}
		
		//for premium matches
		if(paramtype.equals("allpartnerpremiummatch"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
		
		//age
		
		if(age_form>0 && age_to>0)
		{
			sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+age_form+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+age_to;
		}
		
		if(height_info_from_id>0 && height_info_to_id>0)
		{
			sql+=" and u.height_info_id >="+height_info_from_id+" and u.height_info_id <="+height_info_to_id;
		}
		
		if(!mothertongueArray.equals("0"))
		{
			sql+=" and u.mother_tongue_id in ("+mothertongueArray+")";
		}
		
		if(!religionadvsrnm.equals("0"))
		{
			sql+=" and u.religion_id in ("+religionadvsrnm+")";
		}
		
		if(!casteadvsrnm.equals("0"))
		{
			sql+=" and u.caste_info_id in ("+casteadvsrnm+")";
		}
		
		if(!maritalsts.equals("0"))
		{
			sql+=" and u.marital_status_id in ("+maritalsts+")";
		}
		
		//country
		if(!cntrylistadvsrnm.equals("0"))
		{
			sql+=" and u.country_id in ("+cntrylistadvsrnm+")";
		}
		//state
		if(!statelistadvsrnm.equals("0"))
		{
			sql+=" and u.state_id in ("+statelistadvsrnm+")";
		}
		
		//highest education 
		if(!higheducadvsrnm.equals("0"))
		{
			sql+=" and u.highest_education_id in ( "+higheducadvsrnm+" )";
		}
		//annual income
		if(!annlincmadvsrnm.equals("0"))
		{
			sql+=" and u.annual_income_id in ( "+annlincmadvsrnm+" )";
		}
		//employed in
		if(!empinadvsrnm.equals("0"))
		{
			sql+=" and u.employed_in_id in ( "+empinadvsrnm+" )";
		}
		//occupation
		if(!occplistadvsrnm.equals("0"))
		{
			sql+=" and u.occupation_info_id in ( "+occplistadvsrnm+" )";
		}
		
		/*#################life habits###################*/
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//other basic criteria
			
			if(!bodytype1.equals("0"))
			{
				
				sql+=" and u.body_type in ( "+bodytype1+" )";
			}
			
			if(!complxval1.equals("0"))
			{
				sql+=" and u.complexion in ( "+complxval1+" )";
			}
			if(!phystval1.equals("0"))
			{
				sql+=" and u.physical_status in ( "+phystval1+" )";
			}
			if(!eathabval1.equals("0"))
			{
				sql+=" and u.eating_habits in ( "+eathabval1+" )";
			}
			if(!drnhabval1.equals("0"))
			{
				sql+=" and u.drinking_habits in ( "+drnhabval1+" )";
			}
			if(!smhabval1.equals("0"))
			{
				sql+=" and u.smoking_habits in ( "+smhabval1+" )";
			}
		}
		/*#################life habits###################*/
		//System.out.print(sql);
		sql+=" order by u.id desc limit 0, "+Common_Info.perpage;
		
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
					uobj.setMother_tongue_name(rs.getString(16));
					
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	
	public List<User> get_all_advanceregularsearchlist_after_reach_bottom_of_page(long lastpostid,long userid,long searchid,String paramtype,String mothertongueArray,String religionadvsrnm,String casteadvsrnm,int age_form,int age_to,int height_info_from_id,int height_info_to_id,String maritalsts,String higheducadvsrnm,String annlincmadvsrnm,String empinadvsrnm,String occplistadvsrnm,String cntrylistadvsrnm,String statelistadvsrnm,String bodytype1,String complxval1,String phystval1,String eathabval1,String drnhabval1,String smhabval1)
	{
		
		String oppositegender=this.getoppositegender(userid);
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,mt.name as mother_tonue_name from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id  left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.id <'"+lastpostid+"'";
		
		//for new user
		if(paramtype.equals("new"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}
		
		//for premium user
		if(paramtype.equals("premium"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
		
		//for new partner match
		if(paramtype.equals("allpartnernewmatch"))
		{
			sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
		}
		
		//for premium matches
		if(paramtype.equals("allpartnerpremiummatch"))
		{
			sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
		}
		
		//age
		
		if(age_form>0 && age_to>0)
		{
			sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+age_form+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+age_to;
		}
		
		if(height_info_from_id>0 && height_info_to_id>0)
		{
			sql+=" and u.height_info_id >="+height_info_from_id+" and u.height_info_id <="+height_info_to_id;
		}
		
		if(!mothertongueArray.equals("0"))
		{
			sql+=" and u.mother_tongue_id in ("+mothertongueArray+")";
		}
		
		if(!religionadvsrnm.equals("0"))
		{
			sql+=" and u.religion_id in ("+religionadvsrnm+")";
		}
		
		if(!casteadvsrnm.equals("0"))
		{
			sql+=" and u.caste_info_id in ("+casteadvsrnm+")";
		}
		
		if(!maritalsts.equals("0"))
		{
			sql+=" and u.marital_status_id in ("+maritalsts+")";
		}
		
		//country
		if(!cntrylistadvsrnm.equals("0"))
		{
			sql+=" and u.country_id in ("+cntrylistadvsrnm+")";
		}
		//state
		if(!statelistadvsrnm.equals("0"))
		{
			sql+=" and u.state_id in ("+statelistadvsrnm+")";
		}
		
		//highest education 
		if(!higheducadvsrnm.equals("0"))
		{
			sql+=" and u.highest_education_id in ( "+higheducadvsrnm+" )";
		}
		//annual income
		if(!annlincmadvsrnm.equals("0"))
		{
			sql+=" and u.annual_income_id in ( "+annlincmadvsrnm+" )";
		}
		//employed in
		if(!empinadvsrnm.equals("0"))
		{
			sql+=" and u.employed_in_id in ( "+empinadvsrnm+" )";
		}
		//occupation
		if(!occplistadvsrnm.equals("0"))
		{
			sql+=" and u.occupation_info_id in ( "+occplistadvsrnm+" )";
		}
		
		/*#################life habits###################*/
		if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
		{
			//other basic criteria
			
			if(!bodytype1.equals("0"))
			{
				
				sql+=" and u.body_type in ( "+bodytype1+" )";
			}
			
			if(!complxval1.equals("0"))
			{
				sql+=" and u.complexion in ( "+complxval1+" )";
			}
			if(!phystval1.equals("0"))
			{
				sql+=" and u.physical_status in ( "+phystval1+" )";
			}
			if(!eathabval1.equals("0"))
			{
				sql+=" and u.eating_habits in ( "+eathabval1+" )";
			}
			if(!drnhabval1.equals("0"))
			{
				sql+=" and u.drinking_habits in ( "+drnhabval1+" )";
			}
			if(!smhabval1.equals("0"))
			{
				sql+=" and u.smoking_habits in ( "+smhabval1+" )";
			}
		}
		/*#################life habits###################*/
		
		//System.out.print(sql);
		sql+=" order by u.id desc limit  "+Common_Info.perpage;
		
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
					uobj.setMother_tongue_name(rs.getString(16));
					
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	//used to get mother tongue list with count
	
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
	   //##########################advance search in sidebar###############################
	   //get mother tongue list with count
		public List<Regular_Search> get_mother_tongue_list_with_count(long userid,String paramtype,long searchid)
		{
			
			Regular_Search rsobj=null;
			String oppositegender=this.getoppositegender(userid);
			
			String sql="select t.* from (select m.id,m.name,count(m.id) as countitem from mother_tongue m inner join userinfo u on u.mother_tongue_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
			
			//all user
			if(paramtype.equals("all"))
			{
				
			}
			//new user
			else if(paramtype.equals("new"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
			}
			
			//for premium user
			else if(paramtype.equals("premium"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
			}
			
			//for new partner match
			else if(paramtype.equals("allpartnernewmatch"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}

			//for premium matches
			else if(paramtype.equals("allpartnerpremiummatch"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(paramtype.equals("allpartnermatch"))
			{
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(searchid!=0)
			{
				//call save search table
				
				 rsobj=this.get_save_search_info(userid,searchid);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
				 
				 
			}else {
				
				 rsobj=this.get_regular_searchdetails(userid);
				 //System.out.println("param"+paramtype);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
			}
			sql+=" group by u.mother_tongue_id) t order by t.name asc";
			
			return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
				public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					List<Regular_Search> mother_tongue_List = new ArrayList();
					while(rs.next())
					{
						
						Regular_Search obj=new Regular_Search();
						obj.setId(rs.getInt(1));
						obj.setItemname(rs.getString(2));
						obj.setCountitem(rs.getInt(3));
						mother_tongue_List.add(obj);
						
					}
					return mother_tongue_List;
				}
				
			});
		}
		
		// get marital status list with count
		public List<Regular_Search> get_maritalstatus_list_with_count(long userid,String paramtype,long searchid)
		{
			
			Regular_Search rsobj=null;
			String oppositegender=this.getoppositegender(userid);
			
			String sql="select t.* from (select m.id,m.name,count(m.id) as countitem from marital_status m inner join userinfo u on u.marital_status_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
			
			//all user
			if(paramtype.equals("all"))
			{
				
			}
			//new user
			else if(paramtype.equals("new"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
			}
			
			//for premium user
			else if(paramtype.equals("premium"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
			}
			
			//for new partner match
			else if(paramtype.equals("allpartnernewmatch"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}

			//for premium matches
			else if(paramtype.equals("allpartnerpremiummatch"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(paramtype.equals("allpartnermatch"))
			{
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(searchid!=0)
			{
				//call save search table
				 System.out.println("search"+searchid);
				 rsobj=this.get_save_search_info(userid,searchid);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
				 
				 
			}else {
				
				 rsobj=this.get_regular_searchdetails(userid);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
			}
			sql+=" group by u.marital_status_id) t order by t.name asc";
			
			return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
				public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					List<Regular_Search> mother_tongue_List = new ArrayList();
					while(rs.next())
					{
						
						Regular_Search obj=new Regular_Search();
						obj.setId(rs.getInt(1));
						obj.setItemname(rs.getString(2));
						obj.setCountitem(rs.getInt(3));
						mother_tongue_List.add(obj);
						
					}
					return mother_tongue_List;
				}
				
			});
		}
		
		
		// get religion list with count
		public List<Regular_Search> get_religion_list_with_count(long userid,String paramtype,long searchid)
		{
			
			Regular_Search rsobj=null;
			String oppositegender=this.getoppositegender(userid);
			
			String sql="select t.* from (select m.id,m.name,count(m.id) as countitem from religion m inner join userinfo u on u.religion_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
			
			//all user
			if(paramtype.equals("all"))
			{
				
			}
			//new user
			else if(paramtype.equals("new"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
			}
			
			//for premium user
			else if(paramtype.equals("premium"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
			}
			
			//for new partner match
			else if(paramtype.equals("allpartnernewmatch"))
			{
				sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}

			//for premium matches
			else if(paramtype.equals("allpartnerpremiummatch"))
			{
				sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(paramtype.equals("allpartnermatch"))
			{
				//##########check partner criteria############
				rsobj=this.get_partnermatch_details(userid);
				String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				sql+=sql_criteria;
			}
			
			else if(searchid!=0)
			{
				//call save search table
				 System.out.println("search"+searchid);
				 rsobj=this.get_save_search_info(userid,searchid);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
				 
				 
			}else {
				
				 rsobj=this.get_regular_searchdetails(userid);
				 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
				 sql+=sql_criteria;
			}
			
			sql+=" group by u.religion_id) t order by t.name asc";
			
			return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
				public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					List<Regular_Search> mother_tongue_List = new ArrayList();
					while(rs.next())
					{
						
						Regular_Search obj=new Regular_Search();
						obj.setId(rs.getInt(1));
						obj.setItemname(rs.getString(2));
						obj.setCountitem(rs.getInt(3));
						mother_tongue_List.add(obj);
						
					}
					return mother_tongue_List;
				}
				
			});
		}
		
		
		// get caste list with count
				public List<Regular_Search> get_caste_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name,count(m.id) as countitem from caste_info m inner join userinfo u on u.caste_info_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"'  ";
					
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}		
					sql+=" group by u.caste_info_id) t order by t.name asc"	;	
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				// get highest education list with count
				public List<Regular_Search> get_highest_education_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name,count(m.id) as countitem from highest_education m inner join userinfo u on u.highest_education_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
					
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					sql+=" group by u.highest_education_id) t order by t.name asc";
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				
				// get annual income list with count
				public List<Regular_Search> annualincome_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.income_value as name,count(m.id) as countitem from annual_income m inner join userinfo u on u.annual_income_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					sql+=" group by u.annual_income_id) t order by t.name asc";
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				// get employed in list with count
				public List<Regular_Search> employedin_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name as name,count(m.id) as countitem from employed_in m inner join userinfo u on u.employed_in_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
					
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					sql+=" group by u.employed_in_id) t order by t.name asc";
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				//get occupation list with count
				public List<Regular_Search> occupation_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name as name,count(m.id) as countitem from occupation_info m inner join userinfo u on u.occupation_info_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
					
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					sql+=" group by u.occupation_info_id) t order by t.name asc";
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				//get country list with count
				public List<Regular_Search> country_list_with_count(long userid,String paramtype,long searchid)
				{
					
					Regular_Search rsobj=null;
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name as name,count(m.id) as countitem from countries m inner join userinfo u on u.country_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.country_id=101 ";
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 //System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					
					sql+=" group by u.country_id) t order by t.name asc";
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
				
				//get state list with count
				public List<Regular_Search> state_list_with_count(long userid,String paramtype,long searchid)
				{
					Regular_Search rsobj=null;
					System.out.println(paramtype);
					String oppositegender=this.getoppositegender(userid);
					
					String sql="select t.* from (select m.id,m.name as name,count(m.id) as countitem from states m inner join userinfo u on u.state_id=m.id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.country_id=101 ";
					
					//all user
					if(paramtype.equals("all"))
					{
						
					}
					
					//new user
					else if(paramtype.equals("new"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
					}
					
					//for premium user
					else if(paramtype.equals("premium"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
					}
					
					//for new partner match
					else if(paramtype.equals("allpartnernewmatch"))
					{
						sql+="and (date(u.created_date) >= date_sub(curdate(),interval 90 day))";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}

					//for premium matches
					else if(paramtype.equals("allpartnerpremiummatch"))
					{
						sql+="and EXISTS (select id from payment_details where CURDATE()>=from_date and CURDATE()<=to_date and status=1 and  userinfo_id=u.id)";
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(paramtype.equals("allpartnermatch"))
					{
						//##########check partner criteria############
						rsobj=this.get_partnermatch_details(userid);
						String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						sql+=sql_criteria;
					}
					
					else if(searchid!=0)
					{
						//call save search table
						 //System.out.println("search"+searchid);
						 rsobj=this.get_save_search_info(userid,searchid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
						 
						 
					}else {
						
						 rsobj=this.get_regular_searchdetails(userid);
						 String sql_criteria=this.check_partner_criteria(rsobj,paramtype);
						 sql+=sql_criteria;
					}
					
					sql+=" group by u.state_id) t order by t.name asc";
					//System.out.println("sql"+sql);
					
					return template.query(sql, new ResultSetExtractor<List<Regular_Search>>() {
						public List<Regular_Search> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							List<Regular_Search> mother_tongue_List = new ArrayList();
							while(rs.next())
							{
								
								Regular_Search obj=new Regular_Search();
								obj.setId(rs.getInt(1));
								obj.setItemname(rs.getString(2));
								obj.setCountitem(rs.getInt(3));
								mother_tongue_List.add(obj);
								
							}
							return mother_tongue_List;
						}
						
					});
				}
	//##########check partner criteria common function#################
	public String check_partner_criteria(Regular_Search rsobj,String paramtype) {
			
			String bodytype1="";
			String complxval1="";
			String phystval1="";
			String eathabval1="";
			String drnhabval1="";
			String smhabval1="";
			String sql="";
			if(rsobj==null) {
				return sql;
			}
			if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
			{
					
					String bodytype=Arrays.toString(rsobj.getBody_type());
					 bodytype1= bodytype.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();  
					 //System.out.println("bodytyyype"+bodytype1);
							    
							    
				    String complxval=Arrays.toString(rsobj.getComplexion());
				    complxval1= complxval.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();
				    
				    
				    
				    String phystval=Arrays.toString(rsobj.getPhysical_status());
				    phystval1= phystval.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();
				    
				    String eathabval=Arrays.toString(rsobj.getEating_habits());
				    eathabval1= eathabval.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();
				    
				    String drnhabval=Arrays.toString(rsobj.getDrinking_habits());
				    drnhabval1= drnhabval.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();
				    
				    String smhabval=Arrays.toString(rsobj.getSmoking_habits());
				    smhabval1= smhabval.replace("[", "")  //remove the right bracket
							    .replace("]", "")  //remove the left bracket
							    .trim();
			
			}
			
			
	 		//age
			if(rsobj.getAge_form()>0 && rsobj.getAge_to()>0)
			{
				sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+rsobj.getAge_form()+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+rsobj.getAge_to();
			}
			
			//religion
			if(rsobj.getReligion_id()!=0)
			{
				sql+=" and u.religion_id="+rsobj.getReligion_id();
			}
			
			//mother tongue
			if(!rsobj.getMother_tongue_id().equals("0"))
			{
				sql+=" and u.mother_tongue_id in ( "+rsobj.getMother_tongue_id()+" )";
			}
			//caste
			if(!rsobj.getCaste_info_id().equals("0"))
			{
				sql+=" and u.caste_info_id in ( "+rsobj.getCaste_info_id()+" )";
			}
			//marital status
			if(!rsobj.getMarital_status_id().equals("0"))
			{
				sql+=" and u.marital_status_id in ( "+rsobj.getMarital_status_id()+" )";
			}
			//height
			if(rsobj.getHeight_info_from_id()>0 && rsobj.getHeight_info_to_id()>0)
			{
				sql+=" and u.height_info_id >= "+rsobj.getHeight_info_from_id()+" and u.height_info_id <="+rsobj.getHeight_info_to_id();
			}
			//country
			if(rsobj.getCountry_id()>0)
			{
				sql+=" and u.country_id = "+rsobj.getCountry_id();
			}
			//state
			if(rsobj.getState_id()>0)
			{
				sql+=" and u.state_id = "+rsobj.getState_id();
			}
			//city
			if(!rsobj.getCity_id().equals("0"))
			{
				sql+=" and u.city_id in ( "+rsobj.getCity_id()+" )";
			}
			//highest education 
			if(!rsobj.getHighest_education_id().equals("0"))
			{
				sql+=" and u.highest_education_id in ( "+rsobj.getHighest_education_id()+" )";
			}
			//annual income
			if(!rsobj.getAnnual_income_id().equals("0"))
			{
				sql+=" and u.annual_income_id in ( "+rsobj.getAnnual_income_id()+" )";
			}
			//employed in
			if(!rsobj.getEmployed_in_id().equals("0"))
			{
				sql+=" and u.employed_in_id in ( "+rsobj.getEmployed_in_id()+" )";
			}
			//occupation
			if(!rsobj.getOccupation_info_id().equals("0"))
			{
				sql+=" and u.occupation_info_id in ( "+rsobj.getOccupation_info_id()+" )";
			}
			
			if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
			{
				//other basic criteria
				
				if(!bodytype1.equals("0"))
				{
					
					sql+=" and u.body_type in ( "+bodytype1+" )";
				}
				if(!complxval1.equals("0"))
				{
					sql+=" and u.complexion in ( "+complxval1+" )";
				}
				if(!phystval1.equals("0"))
				{
					sql+=" and u.physical_status in ( "+phystval1+" )";
				}
				if(!eathabval1.equals("0"))
				{
					sql+=" and u.eating_habits in ( "+eathabval1+" )";
				}
				if(!drnhabval1.equals("0"))
				{
					sql+=" and u.drinking_habits in ( "+drnhabval1+" )";
				}
				if(!smhabval1.equals("0"))
				{
					sql+=" and u.smoking_habits in ( "+smhabval1+" )";
				}
			}
			return sql;
		}
				
	//########################advance search in sidebar##########################
	
	//###################################keyword search ####################################  
	
	public List<User> get_all_userlist_on_page_load_keyword_search(long userid,String input_keyword,int order_by_no)
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
		
		
		String sql="select t.* from ( (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
		
		String[] searchwords = input_keyword.split(",");
		String[] searchwords_yrs_chk_arr;
		String search_keyword="";
		String search_keyword1="";
		String search_keyword_edu="";
		int form_age=0;
		int to_age=0;
		for(int i=0;i<searchwords.length;i++)
		{
			boolean chk_sub_str=searchwords[i].toLowerCase().contains("-");
			String replace_search_string="";
			replace_search_string=searchwords[i].replace("years","");
			replace_search_string=replace_search_string.replace("year","");
			if(chk_sub_str)
			{
				searchwords_yrs_chk_arr = replace_search_string.split("-");
				form_age=Integer.parseInt(searchwords_yrs_chk_arr[0].trim());
				to_age=Integer.parseInt(searchwords_yrs_chk_arr[1].trim());
				
			}
			search_keyword=searchwords[i].trim(); 
			search_keyword_edu=searchwords[i].trim().replace(".","");
			//System.out.print("edu"+search_keyword_edu);
			sql+=" and (REPLACE(TRIM(hghed.name),'.','') = '"+search_keyword_edu+"' or rg.name like '%"+search_keyword+"%' or ca.name like '%"+search_keyword+"%' or cte.name = '"+search_keyword+"' or sts.name like '%"+search_keyword+"%' or TIMESTAMPDIFF(YEAR, u.dob, CURDATE()) = '"+search_keyword+"' or (TIMESTAMPDIFF(YEAR, u.dob, CURDATE()) between  '"+form_age+"' and '"+to_age+"')  )"; 
		}
		
		
		
		 sql+=" ) ";
		 sql+="union (select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and (u.id>0 and (";
		
		String[] searchwords2 = input_keyword.split(",");
		//System.out.println(Arrays.toString(words));
		
		for(int i=0;i<searchwords2.length;i++)
		{
			
			search_keyword1=searchwords2[i].trim();
			sql+="  (REPLACE(TRIM(hghed.name),'.','') = '"+search_keyword_edu+"' or rg.name like '%"+search_keyword1+"%' or ca.name like '%"+search_keyword1+"%' or cte.name = '"+search_keyword1+"' or sts.name like '%"+search_keyword1+"%' or TIMESTAMPDIFF(YEAR, u.dob, CURDATE()) = '"+search_keyword1+"' or (TIMESTAMPDIFF(YEAR, u.dob, CURDATE()) between  '"+form_age+"' and '"+to_age+"') )  ";
			
			if(i+1<=(searchwords2.length-1))
			{
				sql+=" or ";
			}
				
			
		}
		
		sql+=" ))   ) ) t  limit "+order_by_no+","+Common_Info.perpage;
		
		System.out.println(sql);
		order_by_no=order_by_no+Common_Info.perpage;
		final int order_by_no_new=order_by_no;
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
					uobj.setOrder_by_no(order_by_no_new);
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	} 
	   //################################keyword search ###########################################
 }

