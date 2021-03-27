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
import com.beans.Profile;
import com.beans.State;
import com.beans.User;  
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class UserDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}    
	
	//PreparedStatement to prevent sql injection
	// used for registration
	public long saveuserinfo(final User e){  
		
		//set date of birth
		String dob=e.getDobyear()+'-'+e.getDobmonth()+'-'+e.getDobday();
		e.setDob(dob);
		
		// if city is null set to 0
		if(e.getCity_id()==null)
		{
			
			e.setCity_id("0");
		}
		
		// if state is null set to 0
		if(e.getState_id()==null)
		{
			e.setState_id("0");
		}
		
		
	    String query="insert into userinfo(gender,name,email,username,password,dob,religion_id,mother_tongue_id,marital_status_id,height_info_id,highest_education_id,caste_info_id,annual_income_id,employed_in_id,occupation_info_id,express_yourself,phone_no,country_id,state_id,city_id,weight_info,email_verification_status,phone_verification_status,phone_verification_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
	    return template.execute(query,new PreparedStatementCallback<Long>(){  
	      
	    public Long doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException { 
	    	
	    	ps.setString(1,e.getGender());        
	        ps.setString(2,e.getName());  
	        ps.setString(3,e.getEmail());
	        ps.setString(4,e.getUsername());
	        ps.setString(5,getMd5(e.getPassword()));   
	        
	        ps.setString(6,e.getDob());        
	        ps.setString(7,e.getReligion_id());  
	        ps.setString(8,e.getMother_tongue_id());
	        ps.setString(9,e.getMarital_status_id());
	        ps.setString(10,e.getHeight_info_id());   
	        
	        ps.setString(11,e.getHighest_education_id());        
	        ps.setString(12,e.getCaste_info_id());  
	        ps.setString(13,e.getAnnual_income_id());
	        ps.setString(14,e.getEmployed_in_id());
	        ps.setString(15,e.getOccupation_info_id());
	        ps.setString(16,e.getExpress_yourself());
	        ps.setString(17,e.getPhone_no());
	        
	        ps.setString(18,e.getCountry_id());
	        ps.setString(19,e.getState_id());
	        ps.setString(20,e.getCity_id());
	        
	        ps.setString(21,e.getWeight_info());
	        ps.setInt(22,0);
	        ps.setInt(23,0);
	        ps.setString(24,e.getPhone_verification_code());
	        ps.execute(); 
	        
	        ResultSet rs = ps.getGeneratedKeys();
	        long last_inserted_id=(long) 0;
            if(rs.next())
            {
                 last_inserted_id = rs.getLong(1);
                // create instance of Random class 
                Random rand = new Random(); 
          
                // Generate random integers in range 0 to 999 
                int rand_int1 = rand.nextInt(1000); 
          
                // Print random integers 
                //System.out.println("Random Integers: "+rand_int1);
                String matrimony_uid=last_inserted_id+"7PB"+rand_int1;
                String query="update userinfo set matrimony_id='"+matrimony_uid+"' where id='"+last_inserted_id+"' ";  
                template.update(query);
                 //return true;
            }
			return last_inserted_id;
	              
	    }  
	    });  
	}
	
	//used to get religion list 
	public Map<Integer, String> getreligionlist()
	{
		return template.query("select id,name from religion order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				Map<Integer, String> religionList = new HashMap<Integer, String>();
				while(rs.next())
				{
					religionList.put(rs.getInt(1), rs.getString(2));
					
					
					
				}
				return religionList;
			}
			
		});
	}
	
	//used to get mother tongue list
	public Map<Integer, String> get_mother_tongue_list()
	{
		return template.query("select id,name from mother_tongue order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				Map<Integer, String> mother_tongue_List = new HashMap<Integer, String>();
				while(rs.next())
				{
					mother_tongue_List.put(rs.getInt(1), rs.getString(2));
					
					
					
				}
				return mother_tongue_List;
			}
			
		});
	}
	// used for marital status list
	public Map<Integer, String> get_marital_status_list()
	{
		return template.query("select id,name from marital_status order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				Map<Integer, String> marital_status_List = new HashMap<Integer, String>();
				while(rs.next())
				{
					marital_status_List.put(rs.getInt(1), rs.getString(2));
					
					
					
				}
				return marital_status_List;
			}
			
		});
	}
	
	// used for height  list
		public Map<Integer, String> get_height_list()
		{
			return template.query("select id,height_value from height_info order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
				public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
					Map<Integer, String> height_List = new HashMap<Integer, String>();
					while(rs.next())
					{
						height_List.put(rs.getInt(1), rs.getString(2));
						
						
						
					}
					return height_List;
				}
				
			});
		}
		
		
		// used for heighest  education
				public Map<Integer, String> get_highest_education_list()
				{
					return template.query("select id,name from highest_education order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> highest_education_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								highest_education_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							Map<Integer, String> map = sortValues(highest_education_List);  
							return map;
						}
						
					});
				}
				
				
				
				//method to sort values  
				 
				public  Map<Integer, String> sortValues(Map<Integer, String> map)   
				{   
						List list = new LinkedList(map.entrySet());  
						//Custom Comparator  
						Collections.sort(list, new Comparator()   
						{  
							public int compare(Object o1, Object o2)   
							{  
							return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());  
							}  
						});  
						//copying the sorted list in HashMap to preserve the iteration order  
						HashMap sortedHashMap = new LinkedHashMap();  
						for (Iterator it = list.iterator(); it.hasNext();)   
						{  
							 Map.Entry entry = (Map.Entry) it.next();  
							sortedHashMap.put(entry.getKey(), entry.getValue());  
						}   
						return sortedHashMap;  
				}
				
				
				// used for caste
				public Map<Integer, String> get_caste_list()
				{
					return template.query("select id,name from caste_info order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> caste_info_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								caste_info_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							return caste_info_List;
						}
						
					});
				}
				
				// used for annual income
				public Map<Integer, String> get_annual_income_list()
				{
					return template.query("select id,income_value from annual_income order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> income_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								income_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							return income_List;
						}
						
					});
				}
				
				// used for employed in 
				public Map<Integer, String> get_employed_in_list()
				{
					return template.query("select id,name from employed_in order by id asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> employed_in_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								employed_in_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							return employed_in_List;
						}
						
					});
				}
				
				// used for occupation 
				public Map<Integer, String> get_occupation_list()
				{
					return template.query("select id,name from occupation_info order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> occupation_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								occupation_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							Map<Integer, String> map = sortValues(occupation_List);  
							return map;
							
						}
						
					});
				}
				
				//return md5 encrypted password
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
				
				//get country list
				public Map<Integer, String> get_country_list()
				{
					return template.query("select id,name from countries order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
						public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
							// TODO Auto-generated method stub
							Map<Integer, String> country_List = new HashMap<Integer, String>();
							while(rs.next())
							{
								country_List.put(rs.getInt(1), rs.getString(2));
								
								
								
							}
							return country_List;
						}
						
					});
				}
				
				//get state list
				public List<State> get_state_list(String cid)
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
				
				
				//get city list
				public List<City> get_city_list(String sid)
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
				//check login
				public Long  login_check(Login obj)
				{
					//check if user is email verified and active status
					String sql = "SELECT u.id FROM userinfo u WHERE email=? AND password=?";
					try {
						long userId =template.queryForObject(sql, new Object[] { obj.getEmail(), getMd5(obj.getPassword()) },Long.class);
					
					if(userId >0)
					{
						String sql1 = "SELECT count(*) as count FROM userinfo u WHERE email=? AND password=? AND email_verification_status=1 AND phone_verification_status=1 AND status=1 ";
						int count =template.queryForObject(sql, new Object[] { obj.getEmail(), getMd5(obj.getPassword()) },int.class);
						if(count==1)
						{
							String query="update userinfo set online_status=1 where id='"+userId+"' ";  
						    template.update(query);  
						}
						
					}
					
					return userId;
					}catch (Exception e) {
						return (long) 0;
					}
				}
				
				public int logout_check(long userid)
				{
					String query="update userinfo set online_status=0 where id='"+userid+"' "; 
					//System.out.print(query);
				    return template.update(query);  
				}
				
				//get all user list on page load
				public List<User> get_all_userlist_on_page_load(long userid,String paramtype)
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
					
					
					String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' ";
					
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
				
				//get list when reach bottom of page
				public List<User> get_all_userlist_after_reach_bottom_of_page(long lastpostid,long userid,String paramtype)
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
					
					String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where u.email_verification_status=1 and u.status=1 and u.gender='"+oppositegender+"' and u.id <'"+lastpostid+"' ";
					
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
				
				//profile details by username
				
		public User getprofiledetails_by_username(String uname){ 
			try {
				String sql="select g.name as gonname,rash.name as rashiname, bdg.name as bloodgroupname,u.matrimony_id,u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.email,u.phone_no,mt.name as mother_tongue_name,marst.name as martial_name,u.weight_info,ei.name as employed_in_name,u.express_yourself,ainc.income_value,u.is_mangalik  from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id inner join marital_status marst on marst.id=u.marital_status_id inner join employed_in ei on ei.id=u.employed_in_id inner join annual_income ainc on ainc.id=u.annual_income_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id left join gon_info g on u.gon_info_id=g.id left join rashi_info rash on u.rashi_info_id=rash.id left join blood_group bdg on u.blood_group_id=bdg.id where u.email_verification_status=1 and u.status=1 and u.matrimony_id=?";
			    
				return template.queryForObject(sql, new Object[]{uname},new BeanPropertyRowMapper<User>(User.class));   
			}catch(Exception e)
			{
				return null;
			}
			 
		}  
		
		//profile details by id
		
		public User getprofiledetails_by_id(long uid){    
			String sql="select g.name as gonname,rash.name as rashiname, bdg.name as bloodgroupname, u.matrimony_id,u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.email,u.phone_no,mt.name as mother_tongue_name,marst.name as martial_name,u.weight_info,ei.name as employed_in_name,u.express_yourself,ainc.income_value,u.is_mangalik  from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id inner join marital_status marst on marst.id=u.marital_status_id inner join employed_in ei on ei.id=u.employed_in_id inner join annual_income ainc on ainc.id=u.annual_income_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id left join gon_info g on u.gon_info_id=g.id left join rashi_info rash on u.rashi_info_id=rash.id left join blood_group bdg on u.blood_group_id=bdg.id  where u.email_verification_status=1 and u.status=1 and u.id=?";
		    
			return template.queryForObject(sql, new Object[]{uid},new BeanPropertyRowMapper<User>(User.class));    
		}  
		
		 // edit profile details  
		public Profile editprofiledetails(long id){    
		    String sql="select * from userinfo where id=?";    
		    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Profile>(Profile.class));    
		}
		
		//update profile 
		public Boolean updateprofiledetails(final Profile e,final long userid){  
			
			//set date of birth
			String dob=e.getDobyear()+'-'+e.getDobmonth()+'-'+e.getDobday();
			e.setDob(dob);
			
			// if city is null set to 0
			if(e.getCity_id()==null)
			{
				
				e.setCity_id("0");
			}
			
			// if state is null set to 0
			if(e.getState_id()==null)
			{
				e.setState_id("0");
			}
			
			
		    String query="update userinfo set gender=?,name=?,dob=?,religion_id=?,mother_tongue_id=?,marital_status_id=?,height_info_id=?,highest_education_id=?,caste_info_id=?,annual_income_id=?,employed_in_id=?,occupation_info_id=?,express_yourself=?,phone_no=?,country_id=?,state_id=?,city_id=?,weight_info=?,body_type=?,complexion=?,physical_status=?,eating_habits=?,drinking_habits=?,smoking_habits=?,gon_info_id=?,rashi_info_id=?,blood_group_id=?,update_profile_flag=?,is_mangalik=?   where id=?";  
		    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
		      
		    public Boolean doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException { 
		    	
		    	ps.setString(1,e.getGender());        
		        ps.setString(2,e.getName());  
		          
		        ps.setString(3,e.getDob());        
		        ps.setString(4,e.getReligion_id());  
		        ps.setString(5,e.getMother_tongue_id());
		        ps.setString(6,e.getMarital_status_id());
		        ps.setString(7,e.getHeight_info_id());   
		        
		        ps.setString(8,e.getHighest_education_id());        
		        ps.setString(9,e.getCaste_info_id());  
		        ps.setString(10,e.getAnnual_income_id());
		        ps.setString(11,e.getEmployed_in_id());
		        ps.setString(12,e.getOccupation_info_id());
		        ps.setString(13,e.getExpress_yourself());
		        ps.setString(14,e.getPhone_no());
		        
		        ps.setString(15,e.getCountry_id());
		        ps.setString(16,e.getState_id());
		        ps.setString(17,e.getCity_id());
		        
		        ps.setString(18,e.getWeight_info());
		        
		        ps.setInt(19,e.getBody_type());
		        ps.setInt(20,e.getComplexion());
		        ps.setInt(21,e.getPhysical_status());
		        ps.setInt(22,e.getEating_habits());
		        ps.setInt(23,e.getDrinking_habits());
		        ps.setInt(24,e.getSmoking_habits());
		        
		        ps.setInt(25,e.getGon_info_id());
		        ps.setInt(26,e.getRashi_info_id());
		        ps.setInt(27,e.getBlood_group_id());
		        
		        ps.setInt(28,1);
		        ps.setInt(29,e.getIs_mangalik());
		        ps.setLong(30,userid); 
		        ps.execute(); 
		        
				return true;
		              
		    }  
		    });  
		}
		
		// gon list
		
		public Map<Integer, String> get_gon_list()
		{
			return template.query("select id,name from gon_info order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
				public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
				
					Map<Integer, String> gon_List = new HashMap<Integer, String>();
					while(rs.next())
					{
						gon_List.put(rs.getInt(1), rs.getString(2));
						
						
					}
					Map<Integer, String> map = sortValues(gon_List);  
					return map;
				}
				
			});
		}
		
		
		// blood group
		
		public Map<Integer, String> get_blood_group_list()
		{
			return template.query("select id,name from blood_group order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
				public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
				
					Map<Integer, String> bldgrp_List = new HashMap<Integer, String>();
					while(rs.next())
					{
						bldgrp_List.put(rs.getInt(1), rs.getString(2));
						
						
					}
					Map<Integer, String> map = sortValues(bldgrp_List);  
					return map;
				}
				
			});
		}
		// rashi list
		public Map<Integer, String> get_rashi_list()
		{
			return template.query("select id,name from rashi_info order by name asc", new ResultSetExtractor<Map<Integer, String>>() {
				public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					// TODO Auto-generated method stub
				
					Map<Integer, String> rashi_List = new HashMap<Integer, String>();
					while(rs.next())
					{
						rashi_List.put(rs.getInt(1), rs.getString(2));
						
						
					}
					Map<Integer, String> map = sortValues(rashi_List);  
					return map;
				}
				
			});
		}
		
		//########update email varification###########
		public Boolean update_email_verification_status(final long userid)
		{
			 String query="update userinfo set email_verification_status=1  where status=1 and id=?";  
			    return template.execute(query,new PreparedStatementCallback<Boolean>(){  
			      
			    public Boolean doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException { 
			    	
			        ps.setLong(1,userid);
			        ps.execute(); 
			        
					return true;
			              
			    }  
			    }); 
		}
		
		 // email template details  
		public Email_Template email_template_text(){    
		    String sql="select * from email_template where id=?";    
		    return template.queryForObject(sql, new Object[]{1},new BeanPropertyRowMapper<Email_Template>(Email_Template.class));    
		}
		
		//check email if already exist
		public User check_email_exist(String email){    
		    String sql="select count(*) as totalrecord from userinfo where email=?";    
		    return template.queryForObject(sql, new Object[]{email},new BeanPropertyRowMapper<User>(User.class));    
		}
		
		//check if mobile code is ok for phone verification
		
		public int check_if_mobile_code_ok(final long userid,String phcode)
		{
			String sql1 = "SELECT count(*) as count FROM userinfo u WHERE id=? AND phone_verification_status=1";
			
			try {
				int count1 =template.queryForObject(sql1, new Object[] { userid },int.class);
			
						if(count1==1)
						{
							
							return 2;
						    
						}
						
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return 0;
			}
			
			String sql = "SELECT count(*) as count FROM userinfo u WHERE phone_verification_code=? AND id=?";
			try {
				int count =template.queryForObject(sql, new Object[] { phcode, userid },int.class);
			
						if(count==1)
						{
							String query="update userinfo set phone_verification_status=1  where id=?";  
						    return template.execute(query,new PreparedStatementCallback<Integer>(){  
						      
						    public Integer doInPreparedStatement(PreparedStatement ps)  
						            throws SQLException, DataAccessException { 
						    	
						        ps.setLong(1,userid);
						        ps.execute(); 
						        
								return 1;
						              
						    }  
						    }); 
						    
						}
						else {
							return 0;
						}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return 0;
			}
			 
		}
}


