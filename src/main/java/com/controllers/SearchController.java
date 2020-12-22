package com.controllers;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.City;
import com.beans.Login;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;
import com.dao.SearchDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Controller 
public class SearchController {
	@Autowired    
    UserDao dao;//will inject dao from XML file 
	@Autowired
	SearchDao SrchDao;
	
	@RequestMapping("/savesearchdetails")    
    public String savesearchdetails(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		List<Regular_Search> list=SrchDao.getsavesearchdetails(userid);
		request.setAttribute("sList", list);
		return "frontend/savesearchdetails"; 
		
	}
	
	//regular search
	@RequestMapping("/regularsearch")    
    public String regularsearch(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		m.addAttribute("command", new User()); 
		
		
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
	    //###########check login###############
		
		
		try
		{
			Regular_Search shobj=SrchDao.get_regular_searchdetails(userid);
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				request.setAttribute("statelistedit_status", 0);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			
		}
        return "frontend/regularsearch";   
    } 
	
	@RequestMapping("/regularsearchresult")    
    public String regularsearchresult(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
	
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		
		try
		{
			Regular_Search shobj=SrchDao.get_regular_searchdetails(userid);
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				//request.setAttribute("statelistedit_status", 0);
				String cid=Integer.toString(101);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			
		}
		
        return "frontend/regularsearch_result";   
    } 
	
	//regular search
	@RequestMapping(value="/regularsearchsave",method = RequestMethod.POST)    
    public String regularsearchsave(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		
		int age_form=Integer.parseInt(request.getParameter("age_form").toString());
		int age_to=Integer.parseInt(request.getParameter("age_to").toString());
		int religion_id=Integer.parseInt(request.getParameter("religion_id").toString());
		
		String[] mother_tongue_id;
		String mother_tongue_id1;
		try
		{
			 mother_tongue_id = request.getParameterValues("mother_tongue_id");
			 mother_tongue_id1 = String.join(",", mother_tongue_id);
		}
		catch(Exception e)
		{
			mother_tongue_id1="0";
		}
		
		String[] caste_info_id;
		String caste_info_id1;
		
		try
		{
			 caste_info_id = request.getParameterValues("caste_info_id");
			 caste_info_id1 = String.join(",", caste_info_id);
		}
		catch(Exception e)
		{
			caste_info_id1="0";
		}
		
		String[] marital_status_id;
		String marital_status_id1;
		
		try
		{
			 marital_status_id = request.getParameterValues("marital_status_id");
			 marital_status_id1 = String.join(",", marital_status_id);
		}
		catch(Exception e)
		{
			marital_status_id1="0";
		}
		
		
		int height_info_from_id=Integer.parseInt(request.getParameter("height_info_from_id").toString());
		int height_info_to_id=Integer.parseInt(request.getParameter("height_info_to_id").toString());
		int country_id=0;
		int state_id=0;
		try
		{
			 country_id=Integer.parseInt(request.getParameter("country_id").toString());
		}
		catch(Exception e)
		{
			 country_id=0;
		}
		try
		{
			state_id=Integer.parseInt(request.getParameter("state_id").toString());
		}
		catch(Exception e)
		{
			 state_id=0;
		}
		String[] city_id;
		String city_id1;
		try
		{
			 city_id = request.getParameterValues("city_id");
			 //System.out.print("cityidar "+city_id);
			 city_id1 = String.join(",", city_id);
		}
		catch(Exception e)
		{
			city_id1="0";
		}
		//System.out.print("cityid "+city_id1);
		String[] highest_education_id;
		String highest_education_id1;
		
		try
		{
				highest_education_id = request.getParameterValues("highest_education_id");
			    highest_education_id1 = String.join(",", highest_education_id);
		}
		catch(Exception e)
		{
			highest_education_id1="0";
		}
		
		String[] annual_income_id;
		String annual_income_id1;
		
		try
		{
			 annual_income_id = request.getParameterValues("annual_income_id");
			 annual_income_id1 = String.join(",", annual_income_id);
		}
		catch(Exception e)
		{
			annual_income_id1="0";
		}
		
		String[] employed_in_id;
		String employed_in_id1;
		
		try
		{
		
			employed_in_id = request.getParameterValues("employed_in_id");
			employed_in_id1 = String.join(",", employed_in_id);
		
		}
		catch(Exception e)
		{
			employed_in_id1="0";
		}
		
		String[] occupation_info_id;
		String occupation_info_id1;
		
		try
		{
			 occupation_info_id = request.getParameterValues("occupation_info_id");
			 occupation_info_id1 = String.join(",", occupation_info_id);
		}
		catch(Exception e)
		{
			occupation_info_id1="0";
		}
		
		
		//save search
		String search_name;
		try
		{
			search_name=request.getParameter("search_name").toString();
		}
		catch(Exception e)
		{
			search_name="";
		}
		
		//setter methods
		Regular_Search shobj=new Regular_Search();
		shobj.setUserinfo_id(userid);
		shobj.setAge_form(age_form);
		shobj.setAge_to(age_to);
		shobj.setReligion_id(religion_id);
		shobj.setMother_tongue_id(mother_tongue_id1);
		shobj.setCaste_info_id(caste_info_id1);
		shobj.setMarital_status_id(marital_status_id1);
		shobj.setHeight_info_from_id(height_info_from_id);
		shobj.setHeight_info_to_id(height_info_to_id);
		shobj.setCountry_id(country_id);
		shobj.setState_id(state_id);
		shobj.setCity_id(city_id1);
		shobj.setHighest_education_id(highest_education_id1);
		shobj.setAnnual_income_id(annual_income_id1);
		shobj.setEmployed_in_id(employed_in_id1);
		shobj.setOccupation_info_id(occupation_info_id1);
		shobj.setSearch_name(search_name);
		//call dao
		SrchDao.save_regular_search(shobj);
		if(!shobj.getSearch_name().equals(""))
 	    {
			SrchDao.save_search_info(shobj);
 	    }
		
        return "redirect:/regularsearchresult";//will redirect to login request mapping    
    }
	
	// get all regularsearch list on page load
		
		@RequestMapping(value="/get_all_regularsearchlist_on_page_load",method = RequestMethod.POST)  
	    public void get_all_regularsearchlist_on_page_load(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
			long searchid=Long.parseLong(request.getParameter("searchid").toString());
			 
			//###########check login###############
			long userid=(long) 0;
				try
				{
				 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
				}
				catch(Exception e)
				{
					 userid=(long) 0;
					 //return "redirect:/login";
					 
				}
			//###########check login###############
			 String paramtype=request.getParameter("paramtype");
			 Gson gsonBuilder = new GsonBuilder().create();
	    	 //String sid=request.getParameter("sid");
	    	 List<User> l1=SrchDao.get_all_regularsearchlist_on_page_load(userid,searchid,paramtype);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
		
		 
		@RequestMapping(value="/get_all_regularsearchlist_after_reach_bottom_of_page",method = RequestMethod.POST) 
	    public void get_all_regularsearchlist_after_reach_bottom_of_page(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		
			 
			long searchid=Long.parseLong(request.getParameter("searchid").toString());
			 
			//###########check login###############
			long userid=(long) 0;
				try
				{
				 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
				}
				catch(Exception e)
				{
					 userid=(long) 0;
					 //return "redirect:/login";
					 
				}
			//###########check login###############
			 String paramtype=request.getParameter("paramtype");
			 Gson gsonBuilder = new GsonBuilder().create();
			 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
	    	 List<User> l1=SrchDao.get_all_regularsearchlist_after_reach_bottom_of_page(lastpostid,userid,searchid,paramtype);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    }
		
	//used to get religion list 
    @ModelAttribute("religionlisthashmap")
    public Map<Integer, String> myreligionlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> religionlisthashmap=dao.getreligionlist();
    	//System.out.print(religionlisthashmap);
       return religionlisthashmap;
    }
    @ModelAttribute("countryList")
    public Map<String, String> getCountryList() {
       Map<String, String> countryList = new HashMap<String, String>();
       countryList.put("US", "United States");
       countryList.put("CH", "China");
       countryList.put("SG", "Singapore");
       countryList.put("MY", "Malaysia");
       return countryList;
    }
    
    //used to get mother tongue list
    @ModelAttribute("mothertonguelisthashmap")
    public Map<Integer, String> mymothertonguelisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> mothertonguelisthashmap=dao.get_mother_tongue_list();
    	
       return mothertonguelisthashmap;
    }
    
    //used to get marital status list
    @ModelAttribute("maritalstatuslisthashmap")
    public Map<Integer, String> mymaritalstatuslisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> maritalstatuslisthashmap=dao.get_marital_status_list();
    	
       return maritalstatuslisthashmap;
    }
    
   // used for height  list
    @ModelAttribute("heightlisthashmap")
    public Map<Integer, String> myheightlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> heightlisthashmap=dao.get_height_list();
    	
       return heightlisthashmap;
    }
    
 // used for heighest  education
    @ModelAttribute("highesteducationlisthashmap")
    public Map<Integer, String> highesteducationlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> highesteducationlisthashmap=dao.get_highest_education_list();
    	
       return highesteducationlisthashmap;
    }
    
 // used for caste list
    @ModelAttribute("castelisthashmap")
    public Map<Integer, String> mycastelisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> castelisthashmap=dao.get_caste_list();
    	
       return castelisthashmap;
    }
    
 // used for annual income
    @ModelAttribute("annualincomelisthashmap")
    public Map<Integer, String> myannualincomelisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> annualincomelisthashmap=dao.get_annual_income_list();
    	
       return annualincomelisthashmap;
    }
    
 // used for employed in
    @ModelAttribute("employedinlisthashmap")
    public Map<Integer, String> myemployedinlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> employedinlisthashmap=dao.get_employed_in_list();
    	
       return employedinlisthashmap;
    }
    
 // used for occupation
    @ModelAttribute("occupationlisthashmap")
    public Map<Integer, String> myoccupationlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> occupationlisthashmap=dao.get_occupation_list();
    	
       return occupationlisthashmap;
    }
    
 // used for country
    @ModelAttribute("countrylisthashmap")
    public Map<Integer, String> mycountrylisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> countrylisthashmap=dao.get_country_list();
    	
       return countrylisthashmap;
    }
    
    //saved search result
    @RequestMapping("/savesearchresult")    
    public String savesearchresult(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
    			
		//saved search id
		long searchid=Long.parseLong(request.getParameter("searchid").toString());
		try
		{
			Regular_Search shobj=SrchDao.get_save_search_info(userid,searchid);
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				request.setAttribute("statelistedit_status", 1);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			return "frontend/regularsearch_result"; 
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			return "frontend/regularsearch_result"; 
			
		}
		
       
    } 
    
    //save search edit
    @RequestMapping("/editusersavedsearch")    
    public String editusersavedsearch(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		m.addAttribute("command", new User()); 
		
		long savedsearchid=Long.parseLong(request.getParameter("savedsearchid").toString());
		
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		try
		{
			
			Regular_Search shobj=SrchDao.get_regular_saved_searchdetails(userid,savedsearchid);
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				request.setAttribute("statelistedit_status", 0);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			
		}
        return "frontend/editusersavedsearch";   
    } 
    
    //update save search
  	@RequestMapping(value="/savedsearchupdate",method = RequestMethod.POST)    
      public String savedsearchupdate(HttpServletRequest request,HttpServletResponse response,HttpSession session){
  		
  		long savedsearchid=Long.parseLong(request.getParameter("savedsearchid").toString());
  		
		  //###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
  		int age_form=Integer.parseInt(request.getParameter("age_form").toString());
  		int age_to=Integer.parseInt(request.getParameter("age_to").toString());
  		int religion_id=Integer.parseInt(request.getParameter("religion_id").toString());
  		
  		String[] mother_tongue_id;
  		String mother_tongue_id1;
  		try
  		{
  			 mother_tongue_id = request.getParameterValues("mother_tongue_id");
  			 mother_tongue_id1 = String.join(",", mother_tongue_id);
  		}
  		catch(Exception e)
  		{
  			mother_tongue_id1="0";
  		}
  		
  		String[] caste_info_id;
  		String caste_info_id1;
  		
  		try
  		{
  			 caste_info_id = request.getParameterValues("caste_info_id");
  			 caste_info_id1 = String.join(",", caste_info_id);
  		}
  		catch(Exception e)
  		{
  			caste_info_id1="0";
  		}
  		
  		String[] marital_status_id;
  		String marital_status_id1;
  		
  		try
  		{
  			 marital_status_id = request.getParameterValues("marital_status_id");
  			 marital_status_id1 = String.join(",", marital_status_id);
  		}
  		catch(Exception e)
  		{
  			marital_status_id1="0";
  		}
  		
  		
  		int height_info_from_id=Integer.parseInt(request.getParameter("height_info_from_id").toString());
  		int height_info_to_id=Integer.parseInt(request.getParameter("height_info_to_id").toString());
  		int country_id=0;
  		int state_id=0;
  		try
  		{
  			 country_id=Integer.parseInt(request.getParameter("country_id").toString());
  		}
  		catch(Exception e)
  		{
  			 country_id=0;
  		}
  		try
  		{
  			state_id=Integer.parseInt(request.getParameter("state_id").toString());
  		}
  		catch(Exception e)
  		{
  			 state_id=0;
  		}
  		String[] city_id;
  		String city_id1;
  		try
  		{
  			 city_id = request.getParameterValues("city_id");
  			 //System.out.print("cityidar "+city_id);
  			 city_id1 = String.join(",", city_id);
  		}
  		catch(Exception e)
  		{
  			city_id1="0";
  		}
  		//System.out.print("cityid "+city_id1);
  		String[] highest_education_id;
  		String highest_education_id1;
  		
  		try
  		{
  				highest_education_id = request.getParameterValues("highest_education_id");
  			    highest_education_id1 = String.join(",", highest_education_id);
  		}
  		catch(Exception e)
  		{
  			highest_education_id1="0";
  		}
  		
  		String[] annual_income_id;
  		String annual_income_id1;
  		
  		try
  		{
  			 annual_income_id = request.getParameterValues("annual_income_id");
  			 annual_income_id1 = String.join(",", annual_income_id);
  		}
  		catch(Exception e)
  		{
  			annual_income_id1="0";
  		}
  		
  		String[] employed_in_id;
  		String employed_in_id1;
  		
  		try
  		{
  		
  			employed_in_id = request.getParameterValues("employed_in_id");
  			employed_in_id1 = String.join(",", employed_in_id);
  		
  		}
  		catch(Exception e)
  		{
  			employed_in_id1="0";
  		}
  		
  		String[] occupation_info_id;
  		String occupation_info_id1;
  		
  		try
  		{
  			 occupation_info_id = request.getParameterValues("occupation_info_id");
  			 occupation_info_id1 = String.join(",", occupation_info_id);
  		}
  		catch(Exception e)
  		{
  			occupation_info_id1="0";
  		}
  		
  		
  		//save search
  		String search_name;
  		try
  		{
  			search_name=request.getParameter("search_name").toString();
  		}
  		catch(Exception e)
  		{
  			search_name="";
  		}
  		
  		//setter methods
  		Regular_Search shobj=new Regular_Search();
  		shobj.setUserinfo_id(userid);
  		shobj.setAge_form(age_form);
  		shobj.setAge_to(age_to);
  		shobj.setReligion_id(religion_id);
  		shobj.setMother_tongue_id(mother_tongue_id1);
  		shobj.setCaste_info_id(caste_info_id1);
  		shobj.setMarital_status_id(marital_status_id1);
  		shobj.setHeight_info_from_id(height_info_from_id);
  		shobj.setHeight_info_to_id(height_info_to_id);
  		shobj.setCountry_id(country_id);
  		shobj.setState_id(state_id);
  		shobj.setCity_id(city_id1);
  		shobj.setHighest_education_id(highest_education_id1);
  		shobj.setAnnual_income_id(annual_income_id1);
  		shobj.setEmployed_in_id(employed_in_id1);
  		shobj.setOccupation_info_id(occupation_info_id1);
  		shobj.setSearch_name(search_name);
  		//set id
  		shobj.setId(savedsearchid);
  		//call dao
  		SrchDao.update_saved_search(shobj);
  		
  		
          return "redirect:/savesearchresult?searchid="+shobj.getId();//will redirect to login request mapping    
      }
  	
  	//delete saved search
  	
  	@RequestMapping("/savedsearchdelete/{id}")    
    public String savedsearchdelete(@PathVariable long id,Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
  		//###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		SrchDao.savedsearchdelete(userid,id);
		
		return "redirect:/savesearchdetails"; 
		
	}
  	
  	//partner preferences
  	@RequestMapping("/partnerpreferences")    
    public String partnerpreferences(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		
		
		//###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		Regular_Search shobj=new Regular_Search();
		try
		{
			 shobj=SrchDao.get_partner_preferences(userid);
			//call for partner preferences
			
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				request.setAttribute("statelistedit_status", 0);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			
		
		}
		finally
		{
			m.addAttribute("command", shobj); 
		}
		
		
		
        return "frontend/partnerpreferences";   
    }
  	
  	//save partner preferences
  	
  	@RequestMapping(value="/partnerpreferencessave",method = RequestMethod.POST)    
    public String partnerpreferencessave(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model m){
		
		
		//###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		int age_form=Integer.parseInt(request.getParameter("age_form").toString());
		int age_to=Integer.parseInt(request.getParameter("age_to").toString());
		int religion_id=Integer.parseInt(request.getParameter("religion_id").toString());
		
		String[] mother_tongue_id;
		String mother_tongue_id1;
		try
		{
			 mother_tongue_id = request.getParameterValues("mother_tongue_id");
			 mother_tongue_id1 = String.join(",", mother_tongue_id);
		}
		catch(Exception e)
		{
			mother_tongue_id1="0";
		}
		
		String[] caste_info_id;
		String caste_info_id1;
		
		try
		{
			 caste_info_id = request.getParameterValues("caste_info_id");
			 caste_info_id1 = String.join(",", caste_info_id);
		}
		catch(Exception e)
		{
			caste_info_id1="0";
		}
		
		String[] marital_status_id;
		String marital_status_id1;
		
		try
		{
			 marital_status_id = request.getParameterValues("marital_status_id");
			 marital_status_id1 = String.join(",", marital_status_id);
		}
		catch(Exception e)
		{
			marital_status_id1="0";
		}
		
		
		int height_info_from_id=Integer.parseInt(request.getParameter("height_info_from_id").toString());
		int height_info_to_id=Integer.parseInt(request.getParameter("height_info_to_id").toString());
		int country_id=0;
		int state_id=0;
		try
		{
			 country_id=Integer.parseInt(request.getParameter("country_id").toString());
		}
		catch(Exception e)
		{
			 country_id=0;
		}
		try
		{
			state_id=Integer.parseInt(request.getParameter("state_id").toString());
		}
		catch(Exception e)
		{
			 state_id=0;
		}
		String[] city_id;
		String city_id1;
		try
		{
			 city_id = request.getParameterValues("city_id");
			 //System.out.print("cityidar "+city_id);
			 city_id1 = String.join(",", city_id);
		}
		catch(Exception e)
		{
			city_id1="0";
		}
		//System.out.print("cityid "+city_id1);
		String[] highest_education_id;
		String highest_education_id1;
		
		try
		{
				highest_education_id = request.getParameterValues("highest_education_id");
			    highest_education_id1 = String.join(",", highest_education_id);
		}
		catch(Exception e)
		{
			highest_education_id1="0";
		}
		
		String[] annual_income_id;
		String annual_income_id1;
		
		try
		{
			 annual_income_id = request.getParameterValues("annual_income_id");
			 annual_income_id1 = String.join(",", annual_income_id);
		}
		catch(Exception e)
		{
			annual_income_id1="0";
		}
		
		String[] employed_in_id;
		String employed_in_id1;
		
		try
		{
		
			employed_in_id = request.getParameterValues("employed_in_id");
			employed_in_id1 = String.join(",", employed_in_id);
		
		}
		catch(Exception e)
		{
			employed_in_id1="0";
		}
		
		String[] occupation_info_id;
		String occupation_info_id1;
		
		try
		{
			 occupation_info_id = request.getParameterValues("occupation_info_id");
			 occupation_info_id1 = String.join(",", occupation_info_id);
		}
		catch(Exception e)
		{
			occupation_info_id1="0";
		}
		
		
		//save search
		String search_name;
		try
		{
			search_name=request.getParameter("search_name").toString();
		}
		catch(Exception e)
		{
			search_name="";
		}
		
		String[] body_type= {};
		
		try
		{
			body_type = request.getParameterValues("body_type");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		String[] complexion= {};
		
		try
		{
			complexion = request.getParameterValues("complexion");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		String[] smoking_habits= {};
		
		try
		{
			smoking_habits = request.getParameterValues("smoking_habits");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		String[] drinking_habits= {};
		
		try
		{
			drinking_habits = request.getParameterValues("drinking_habits");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		String[] eating_habits= {};
		
		try
		{
			eating_habits = request.getParameterValues("eating_habits");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		String[] physical_status= {};
		
		try
		{
			physical_status = request.getParameterValues("physical_status");
			
		}
		catch(Exception e)
		{
			
			
		}
		
		//setter methods
		Regular_Search shobj=new Regular_Search();
		shobj.setUserinfo_id(userid);
		shobj.setAge_form(age_form);
		shobj.setAge_to(age_to);
		shobj.setReligion_id(religion_id);
		shobj.setMother_tongue_id(mother_tongue_id1);
		shobj.setCaste_info_id(caste_info_id1);
		shobj.setMarital_status_id(marital_status_id1);
		shobj.setHeight_info_from_id(height_info_from_id);
		shobj.setHeight_info_to_id(height_info_to_id);
		shobj.setCountry_id(country_id);
		shobj.setState_id(state_id);
		shobj.setCity_id(city_id1);
		shobj.setHighest_education_id(highest_education_id1);
		shobj.setAnnual_income_id(annual_income_id1);
		shobj.setEmployed_in_id(employed_in_id1);
		shobj.setOccupation_info_id(occupation_info_id1);
		shobj.setSearch_name(search_name);
		
		//partner special criteria
		shobj.setBody_type(body_type);
		shobj.setPhysical_status(physical_status);
		shobj.setEating_habits(eating_habits);
		shobj.setDrinking_habits(drinking_habits);
		shobj.setSmoking_habits(smoking_habits);
		shobj.setComplexion(complexion);
		
		//call dao
		SrchDao.partner_preferences_save(shobj);
		
		//m.addAttribute("successmsg","Partner Preferences saved successfully");
		  
        session.setAttribute("successmsg","Partner Preferences saved successfully");  
		
        return "redirect:/partnerpreferences";    
    }
  	
  	@RequestMapping("/allmatches")    
    public String allmatches(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
	
		//###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		//###########check login###############
		
		Regular_Search shobj=new Regular_Search();
		try
		{
			shobj=SrchDao.get_partner_preferences(userid);
			int countryid=shobj.getCountry_id();
			int stateid=shobj.getState_id(); 
			if(countryid>0)
			{
				
				String cid=Integer.toString(countryid);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			else
			{
				//request.setAttribute("statelistedit_status", 0);
				String cid=Integer.toString(101);
				List statelistedit= SrchDao.get_state_list_by_country_id(cid);
				request.setAttribute("statelistedit", statelistedit);
				request.setAttribute("statelistedit_status", 1);
			}
			
			if(stateid>0)
			{
				String sid=Integer.toString(stateid);
				List citylistedit= SrchDao.get_city_list_by_state_id(sid);
				request.setAttribute("citylistedit", citylistedit);
				request.setAttribute("citylistedit_status", 1);
			}
			else
			{
				request.setAttribute("citylistedit_status", 0);
			}
			
			
			request.setAttribute("rsobj", shobj);
			request.setAttribute("countstatus", 1);
			return "frontend/regularsearch_result"; 
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			return "redirect:/partnerpreferences";  
			
		}
		finally
		{
			m.addAttribute("command", shobj); 
		}
          
    } 
  	
  //##########################advance search in sidebar###############################
  	
  	 
  	@RequestMapping(value="/get_all_advanceregularsearchlist_on_page_load",method = RequestMethod.POST) 
    public void get_all_advanceregularsearchlist_on_page_load(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
  		long searchid=Long.parseLong(request.getParameter("searchid"));
		
		//###########check login###############
  		long userid=(long) 0;
			try
			{
			 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
			}
			catch(Exception e)
			{
				 userid=(long) 0;
				 //return "redirect:/login";
				 
			}
			//###########check login###############
			
			
		 String paramtype=request.getParameter("paramtype");
		 
		 /*life habits*/
		 String bodytype1=request.getParameter("body_type_Array");
		 String complxval1=request.getParameter("complexion_Array");
		 String phystval1=request.getParameter("physical_status_Array");
		 String eathabval1=request.getParameter("eating_habits_Array");
		 String drnhabval1=request.getParameter("drinking_habits_Array");
		 String smhabval1=request.getParameter("smoking_habits_Array");
		 
		 
		 //advance search details
		 String mothertongueArray=request.getParameter("mothertongueArray");
		 String religionadvsrnm=request.getParameter("religionadvsrnm");
		 String casteadvsrnm=request.getParameter("casteadvsrnm");
		 int age_form=Integer.parseInt(request.getParameter("age_form").toString());
		 int age_to=Integer.parseInt(request.getParameter("age_to").toString());
		 int height_info_from_id=Integer.parseInt(request.getParameter("height_info_from_id").toString());
		 int height_info_to_id=Integer.parseInt(request.getParameter("height_info_to_id").toString());
		 
		 String maritalsts=request.getParameter("maritalsts");
		 String higheducadvsrnm=request.getParameter("higheducadvsrnm");
		 
		 String annlincmadvsrnm=request.getParameter("annlincmadvsrnm");
		 String empinadvsrnm=request.getParameter("empinadvsrnm");
		 
		 String occplistadvsrnm=request.getParameter("occplistadvsrnm");
		 String cntrylistadvsrnm=request.getParameter("cntrylistadvsrnm");
		 String statelistadvsrnm=request.getParameter("statelistadvsrnm");
		 
		 Gson gsonBuilder = new GsonBuilder().create();
    	 
    	 List<User> l1=SrchDao.get_all_advanceregularsearchlist_on_page_load(userid,searchid,paramtype,mothertongueArray,religionadvsrnm,casteadvsrnm,age_form,age_to,height_info_from_id,height_info_to_id,maritalsts,higheducadvsrnm,annlincmadvsrnm,empinadvsrnm,occplistadvsrnm,cntrylistadvsrnm,statelistadvsrnm,bodytype1,complxval1,phystval1,eathabval1,drnhabval1,smhabval1);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
  	
  	 
  	@RequestMapping(value="/get_all_advanceregularsearchlist_after_reach_bottom_of_page",method = RequestMethod.POST) 
    public void get_all_advanceregularsearchlist_after_reach_bottom_of_page(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
	
  		long searchid=Long.parseLong(request.getParameter("searchid"));
	
		//###########check login###############
  		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
			
			
		 String paramtype=request.getParameter("paramtype");
		 Gson gsonBuilder = new GsonBuilder().create();
		 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
		 
		 /*life habits*/
		 String bodytype1=request.getParameter("body_type_Array");
		 String complxval1=request.getParameter("complexion_Array");
		 String phystval1=request.getParameter("physical_status_Array");
		 String eathabval1=request.getParameter("eating_habits_Array");
		 String drnhabval1=request.getParameter("drinking_habits_Array");
		 String smhabval1=request.getParameter("smoking_habits_Array");
    	 
    	 //advance search details
		 String mothertongueArray=request.getParameter("mothertongueArray");
		 String religionadvsrnm=request.getParameter("religionadvsrnm");
		 String casteadvsrnm=request.getParameter("casteadvsrnm");
		 int age_form=Integer.parseInt(request.getParameter("age_form").toString());
		 int age_to=Integer.parseInt(request.getParameter("age_to").toString());
		 int height_info_from_id=Integer.parseInt(request.getParameter("height_info_from_id").toString());
		 int height_info_to_id=Integer.parseInt(request.getParameter("height_info_to_id").toString());
		 
		 String maritalsts=request.getParameter("maritalsts");
		 String higheducadvsrnm=request.getParameter("higheducadvsrnm");
		 
		 String annlincmadvsrnm=request.getParameter("annlincmadvsrnm");
		 String empinadvsrnm=request.getParameter("empinadvsrnm");
		 
		 String occplistadvsrnm=request.getParameter("occplistadvsrnm");
		 String cntrylistadvsrnm=request.getParameter("cntrylistadvsrnm");
		 String statelistadvsrnm=request.getParameter("statelistadvsrnm");
    	 
    	 List<User> l1=SrchDao.get_all_advanceregularsearchlist_after_reach_bottom_of_page( lastpostid, userid, searchid, paramtype, mothertongueArray, religionadvsrnm, casteadvsrnm, age_form, age_to, height_info_from_id, height_info_to_id, maritalsts, higheducadvsrnm, annlincmadvsrnm, empinadvsrnm, occplistadvsrnm, cntrylistadvsrnm, statelistadvsrnm,bodytype1,complxval1,phystval1,eathabval1,drnhabval1,smhabval1);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    }
  	
  //##########################advance search in sidebar###############################
  	 //used to get mother tongue list
    @ModelAttribute("get_mother_tongue_list_with_count")
    public List<Regular_Search> get_mother_tongue_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> get_mother_tongue_list_with_count=SrchDao.get_mother_tongue_list_with_count(userid,paramtype,searchid);
    	/*
    	for (int i = 0; i < get_mother_tongue_list_with_count.size(); i++) {
			System.out.print(get_mother_tongue_list_with_count.get(i)+"\t");
		}*/
       return get_mother_tongue_list_with_count;
    }
    
    // get marital status list with count
    @ModelAttribute("get_maritalstatus_list_with_count")
    public List<Regular_Search> get_maritalstatus_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
    	//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> get_maritalstatus_list_with_count=SrchDao.get_maritalstatus_list_with_count(userid,paramtype,searchid);
    	
       return get_maritalstatus_list_with_count;
    }
    
 // get religion list with count
    @ModelAttribute("get_religion_list_with_count")
    public List<Regular_Search> get_religion_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> get_religion_list_with_count=SrchDao.get_religion_list_with_count(userid,paramtype,searchid);
    	
       return get_religion_list_with_count;
    }
    
 // get caste list with count
    @ModelAttribute("get_caste_list_with_count")
    public List<Regular_Search> get_caste_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
    	//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> get_caste_list_with_count=SrchDao.get_caste_list_with_count(userid,paramtype,searchid);
    	
       return get_caste_list_with_count;
    }
    
 // get highest education  list with count
    @ModelAttribute("get_highest_education_list_with_count")
    public List<Regular_Search> get_highest_education_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> get_highest_education_list_with_count=SrchDao.get_highest_education_list_with_count(userid,paramtype,searchid);
    	
       return get_highest_education_list_with_count;
    }
    
 //  get annual income list with count
    @ModelAttribute("annualincome_list_with_count")
    public List<Regular_Search> annualincome_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
    	//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> annualincome_list_with_count=SrchDao.annualincome_list_with_count(userid,paramtype,searchid);
    	
       return annualincome_list_with_count;
    }
    
 // get employed in list with count
    @ModelAttribute("employedin_list_with_count")
    public List<Regular_Search> employedin_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> employedin_list_with_count=SrchDao.employedin_list_with_count(userid,paramtype,searchid);
    	
       return employedin_list_with_count;
    }
    
  //get occupation list with count
    @ModelAttribute("occupation_list_with_count")
    public List<Regular_Search> occupation_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> occupation_list_with_count=SrchDao.occupation_list_with_count(userid,paramtype,searchid);
    	
       return occupation_list_with_count;
    }
    
  //get country list with count
    @ModelAttribute("country_list_with_count")
    public List<Regular_Search> country_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
    	//###########check login###############
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> country_list_with_count=SrchDao.country_list_with_count(userid,paramtype,searchid);
    	
       return country_list_with_count;
    }
    
    @ModelAttribute("state_list_with_count")
    public List<Regular_Search> state_list_with_count(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	
    	//###########check login###############
    	long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 //return "redirect:/login";
			 
		}
		//###########check login###############
		
		String paramtype=request.getParameter("paramtype");
		if(paramtype==null)
		{
			paramtype="nodata";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
    	List<Regular_Search> state_list_with_count=SrchDao.state_list_with_count(userid,paramtype,searchid);
    	
       return state_list_with_count;
    }
  //##########################advance search in sidebar###############################
}
