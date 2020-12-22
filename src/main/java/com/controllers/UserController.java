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
import com.beans.Profile;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;
import com.dao.SearchDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Controller 
public class UserController {
	@Autowired    
    UserDao dao;//will inject dao from XML file 
	@Autowired
	SearchDao SrchDao;
	@RequestMapping("/allusers")    
    public String allusers(Model m,HttpSession session){  
		
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
		
        return "frontend/allusers";   
    } 
	
	// get all user list on page load
	 
	@RequestMapping(value="/get_all_userlist_on_page_load",method = RequestMethod.POST)  
    public void get_all_userlist_on_page_load(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			// return "redirect:/login";
			 
		}
		//###########check login###############
		 String paramtype=request.getParameter("paramtype").toString();
		 Gson gsonBuilder = new GsonBuilder().create();
    	 //String sid=request.getParameter("sid");
    	 List<User> l1=dao.get_all_userlist_on_page_load(userid,paramtype);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	
	@RequestMapping(value="/get_all_userlist_after_reach_bottom_of_page",method = RequestMethod.POST)  
    public void get_all_userlist_after_reach_bottom_of_page(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
		 
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			// return "redirect:/login";
			 
		}
		//###########check login###############
		 Gson gsonBuilder = new GsonBuilder().create();
		 String paramtype=request.getParameter("paramtype").toString();
		 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
    	 List<User> l1=dao.get_all_userlist_after_reach_bottom_of_page(lastpostid,userid,paramtype);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    }
	
	@RequestMapping("/profiledetails")    
    public String profiledetails(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		String usermatrimonyid=request.getParameter("usermatrimonyid");
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
		
		if(usermatrimonyid!=null)
		{
			User uobj = dao.getprofiledetails_by_username(usermatrimonyid);    
	        m.addAttribute("uobj",uobj); 
	        
		}
		else
		{
			
			User uobj = dao.getprofiledetails_by_id(userid);    
	        m.addAttribute("uobj",uobj); 
		}
		m.addAttribute("session_userid",userid); 
		m.addAttribute("usermatrimonyid",usermatrimonyid);
		return "frontend/profiledetails"; 
    } 
	
	 // edit profile details  
    @RequestMapping(value="/editprofile")    
    public String editprofile(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){
    	
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
		System.out.print("gonkk");
		Profile u=dao.editprofiledetails(userid); 
		System.out.print("gon"+u.getGon_info_id());
		try
		{
			
			int countryid=Integer.parseInt(u.getCountry_id().toString());
			int stateid=Integer.parseInt(u.getState_id()); 
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
			
			
			request.setAttribute("countstatus", 1);
			
			
		}catch(Exception e)
		{
			request.setAttribute("countstatus", 0);
			request.setAttribute("statelistedit_status", 0);
			request.setAttribute("citylistedit_status", 0);
			
		}
        
        m.addAttribute("command",u);  
        request.setAttribute("user",u);
        return "frontend/editprofile";    
    } 
    
    //used for gon
    @ModelAttribute("gonlist")
    public Map<Integer, String> gonlist() {
    	
    	Map<Integer, String> gonlist=dao.get_gon_list();
    	//System.out.print(religionlisthashmap);
       return gonlist;
    }
    
  //used for rashi
    @ModelAttribute("rashilist")
    public Map<Integer, String> rashilist() {
    	
    	Map<Integer, String> rashilist=dao.get_rashi_list();
    	//System.out.print(religionlisthashmap);
       return rashilist;
    }
    
  //used for blood group
    @ModelAttribute("bloodgrouplist")
    public Map<Integer, String> bloodgrouplist() {
    	
    	Map<Integer, String> bloodgrouplist=dao.get_blood_group_list();
    	//System.out.print(religionlisthashmap);
       return bloodgrouplist;
    }
    
  //used to get religion list 
    @ModelAttribute("religionlisthashmap")
    public Map<Integer, String> myreligionlisthashmap() {
    	
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
    public Map<Integer, String> mymothertonguelisthashmap() {
    	
    	Map<Integer, String> mothertonguelisthashmap=dao.get_mother_tongue_list();
    	
       return mothertonguelisthashmap;
    }
    
    //used to get marital status list
    @ModelAttribute("maritalstatuslisthashmap")
    public Map<Integer, String> mymaritalstatuslisthashmap() {
    	
    	Map<Integer, String> maritalstatuslisthashmap=dao.get_marital_status_list();
    	
       return maritalstatuslisthashmap;
    }
    
   // used for height  list
    @ModelAttribute("heightlisthashmap")
    public Map<Integer, String> myheightlisthashmap() {
    	
    	Map<Integer, String> heightlisthashmap=dao.get_height_list();
    	
       return heightlisthashmap;
    }
    
 // used for heighest  education
    @ModelAttribute("highesteducationlisthashmap")
    public Map<Integer, String> highesteducationlisthashmap() {
    	
    	Map<Integer, String> highesteducationlisthashmap=dao.get_highest_education_list();
    	
       return highesteducationlisthashmap;
    }
    
 // used for heighest  education
    @ModelAttribute("castelisthashmap")
    public Map<Integer, String> mycastelisthashmap() {
    	
    	Map<Integer, String> castelisthashmap=dao.get_caste_list();
    	
       return castelisthashmap;
    }
    
 // used for annual income
    @ModelAttribute("annualincomelisthashmap")
    public Map<Integer, String> myannualincomelisthashmap() {
    	
    	Map<Integer, String> annualincomelisthashmap=dao.get_annual_income_list();
    	
       return annualincomelisthashmap;
    }
    
 // used for employed in
    @ModelAttribute("employedinlisthashmap")
    public Map<Integer, String> myemployedinlisthashmap() {
    	
    	Map<Integer, String> employedinlisthashmap=dao.get_employed_in_list();
    	
       return employedinlisthashmap;
    }
    
 // used for occupation
    @ModelAttribute("occupationlisthashmap")
    public Map<Integer, String> myoccupationlisthashmap() {
    	
    	Map<Integer, String> occupationlisthashmap=dao.get_occupation_list();
    	
       return occupationlisthashmap;
    }
    
 // used for country
    @ModelAttribute("countrylisthashmap")
    public Map<Integer, String> mycountrylisthashmap() {
    	
    	Map<Integer, String> countrylisthashmap=dao.get_country_list();
    	
       return countrylisthashmap;
    }
    
    @RequestMapping(value="/updateprofiledetails",method = RequestMethod.POST)    
    public String updateprofiledetails(@ModelAttribute("command")@Valid Profile uobj,BindingResult result,Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){
    	
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
    	
    	
    	if (result.hasErrors()) {
    		Profile u=dao.editprofiledetails(userid);
    		
    	
    		request.setAttribute("user",u);
    		try
    		{
    			
    			int countryid=Integer.parseInt(u.getCountry_id().toString());
    			int stateid=Integer.parseInt(u.getState_id()); 
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
    			
    			
    			request.setAttribute("countstatus", 1);
    			
    			
    		}catch(Exception e)
    		{
    			request.setAttribute("countstatus", 0);
    			request.setAttribute("statelistedit_status", 0);
    			request.setAttribute("citylistedit_status", 0);
    			
    		}
    		request.setAttribute("validationmsg", "update failed");
            return "frontend/editprofile";
        }
        dao.updateprofiledetails(uobj,userid);    
        return "redirect:/profiledetails";    
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
		}
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
		//###########check login###############
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
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
		String paramtype="";
		try {
			 paramtype=request.getParameter("paramtype").toString();
			
		}catch(Exception e) {
			 paramtype="no data";
		}
		
		long searchid=0;
		try {
			searchid=Long.parseLong(request.getParameter("searchid").toString());
			
		}catch(Exception e) {
			searchid=0;
		}
		
		 //paramtype=request.getParameter("paramtype");
		
		//System.out.print("kkkkk"+paramtype);
		
    	List<Regular_Search> state_list_with_count=SrchDao.state_list_with_count(userid,paramtype,searchid);
    	
       return state_list_with_count;
    }
  //##########################advance search in sidebar###############################
}
