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
import com.beans.Home;
import com.beans.Login;
import com.beans.Profile;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;  
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class HomeDao {
	
	private JdbcTemplate template;  
	  
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
	    this.template = jdbcTemplate;  
	}    
	//get all user list on page load home page search
	public List<User> get_all_userlist_on_page_load_home(long userid,Home obj)
	{
		String oppositegender=obj.getGender();
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where  u.status=1 and u.gender='"+oppositegender+"'";
		
		//age
		if(obj.getAgefrom()>0 && obj.getAgeto()>0)
		{
			sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+obj.getAgefrom()+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+obj.getAgeto();
		}
		
		//religion
		if(obj.getReligiion()>0)
		{
			sql+=" and u.religion_id in ("+obj.getReligiion()+")";
		}
		//mother tongue
		if(obj.getMother_tongue()>0)
		{
			sql+=" and u.mother_tongue_id in ("+obj.getMother_tongue()+")";
		}
		//caste
		if(obj.getCaste()>0)
		{
			sql+=" and u.caste_info_id in ("+obj.getCaste()+")";
		}
		
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
					
					list1.add(uobj);
					
					
				}
				return list1;
			}
			
		});
	}
	
	//get list when reach bottom of page home page search
	public List<User> get_all_userlist_after_reach_bottom_of_page_home(long lastpostid,long userid,Home obj)
	{
		
		String oppositegender=obj.getGender();
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where  u.status=1 and u.gender='"+oppositegender+"' and u.id <'"+lastpostid+"'";
		
				//age
				if(obj.getAgefrom()>0 && obj.getAgeto()>0)
				{
				sql+=" and FLOOR(DATEDIFF(curdate(),u.dob)/365) >="+obj.getAgefrom()+" and FLOOR(DATEDIFF(curdate(),u.dob)/365) <="+obj.getAgeto();
				}
				
				//religion
				if(obj.getReligiion()>0)
				{
					sql+=" and u.religion_id in ("+obj.getReligiion()+")";
				}
				//mother tongue
				if(obj.getMother_tongue()>0)
				{
					sql+=" and u.mother_tongue_id in ("+obj.getMother_tongue()+")";
				}
				//caste
				if(obj.getCaste()>0)
				{
					sql+=" and u.caste_info_id in ("+obj.getCaste()+")";
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
	
	//advance search in home
	public List<User> get_all_advanceregularsearchlist_on_page_load_home(String gender,long userid,long searchid,String paramtype,String mothertongueArray,String religionadvsrnm,String casteadvsrnm,int age_form,int age_to,int height_info_from_id,int height_info_to_id,String maritalsts,String higheducadvsrnm,String annlincmadvsrnm,String empinadvsrnm,String occplistadvsrnm,String cntrylistadvsrnm,String statelistadvsrnm)
	{
		
		String oppositegender=gender;
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,mt.name as mother_tonue_name from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id  left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where  u.status=1 and u.gender='"+oppositegender+"'";
		
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
	
	public List<User> get_all_advanceregularsearchlist_after_reach_bottom_of_page_home(String gender,long lastpostid,long userid,long searchid,String paramtype,String mothertongueArray,String religionadvsrnm,String casteadvsrnm,int age_form,int age_to,int height_info_from_id,int height_info_to_id,String maritalsts,String higheducadvsrnm,String annlincmadvsrnm,String empinadvsrnm,String occplistadvsrnm,String cntrylistadvsrnm,String statelistadvsrnm)
	{
		
		String oppositegender=gender;
		
		String sql="select u.id,u.name,u.dob,u.gender,u.profile_image,ocpi.name as occupation_name,heig.height_value,rg.name as religion_name,ca.name as caste_name,hghed.name as highest_education,cunt.name as country_name,sts.name as state_name,cte.name as city_name,u.username,u.matrimony_id,mt.name as mother_tonue_name from userinfo u inner join occupation_info ocpi on u.occupation_info_id=ocpi.id inner join height_info heig on heig.id=u.height_info_id inner join religion rg on rg.id=u.religion_id inner join highest_education hghed on hghed.id=u.highest_education_id inner join countries cunt on cunt.id=u.country_id inner join mother_tongue mt on mt.id=u.mother_tongue_id  left join states sts on sts.id=u.state_id left join cities cte on cte.id=u.city_id left join caste_info ca on ca.id=u.caste_info_id where  u.status=1 and u.gender='"+oppositegender+"' and u.id <'"+lastpostid+"'";
		
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
	
	//########################advance search in sidebar##########################
	//get mother tongue list with count
			public List<Regular_Search> get_mother_tongue_list_with_count(long userid,String gender)
			{
				
				
				String oppositegender=gender;
				
				return template.query("select t.* from (select m.id,m.name,count(m.id) as countitem from mother_tongue m inner join userinfo u on u.mother_tongue_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.mother_tongue_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
			public List<Regular_Search> get_maritalstatus_list_with_count(long userid,String gender)
			{
				
				
				String oppositegender=gender;
				
				return template.query("select t.* from (select m.id,m.name,count(m.id) as countitem from marital_status m inner join userinfo u on u.marital_status_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.marital_status_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
			public List<Regular_Search> get_religion_list_with_count(long userid,String gender)
			{
				
				
				String oppositegender=gender;
				
				return template.query("select t.* from (select m.id,m.name,count(m.id) as countitem from religion m inner join userinfo u on u.religion_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.religion_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> get_caste_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name,count(m.id) as countitem from caste_info m inner join userinfo u on u.caste_info_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.caste_info_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> get_highest_education_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name,count(m.id) as countitem from highest_education m inner join userinfo u on u.highest_education_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.highest_education_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> annualincome_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.income_value as name,count(m.id) as countitem from annual_income m inner join userinfo u on u.annual_income_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.annual_income_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> employedin_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name as name,count(m.id) as countitem from employed_in m inner join userinfo u on u.employed_in_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.employed_in_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> occupation_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name as name,count(m.id) as countitem from occupation_info m inner join userinfo u on u.occupation_info_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' group by u.occupation_info_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> country_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name as name,count(m.id) as countitem from countries m inner join userinfo u on u.country_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' and u.country_id=101 group by u.country_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					public List<Regular_Search> state_list_with_count(long userid,String gender)
					{
						
						
						String oppositegender=gender;
						
						return template.query("select t.* from (select m.id,m.name as name,count(m.id) as countitem from states m inner join userinfo u on u.state_id=m.id where  u.status=1 and u.gender='"+oppositegender+"' and u.country_id=101 group by u.state_id) t order by t.name asc", new ResultSetExtractor<List<Regular_Search>>() {
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
					
		//########################advance search in sidebar##########################
}
