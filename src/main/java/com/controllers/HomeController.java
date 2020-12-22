package com.controllers;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Home;
import com.beans.Regular_Search;
import com.beans.User;
import com.dao.SearchDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dao.HomeDao;

@Controller 
public class HomeController {
	@Autowired    
    UserDao dao;//will inject dao from XML file 
	@Autowired
	SearchDao SrchDao;
	@Autowired
	HomeDao HomeObjDao;
	@RequestMapping("/")    
    public String indexpage(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		
		//###########check login###############
		long userid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			
			 
		}
		//###########check login###############
		if(userid>0)
		{
			User uobj = dao.getprofiledetails_by_id(userid);
			String gender=uobj.getGender();
			request.setAttribute("usergender",gender);
			
		}
		else
		{
			request.setAttribute("usergender","");
		}
        return "frontend/home";   
    } 
	
	//home page search
	@RequestMapping("/homesearch")    
    public String homesearch(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session){  
		
        return "frontend/homesearch";   
    }
	//home page search
	@RequestMapping(value="/get_all_userlist_on_page_load_home",method = RequestMethod.POST)  
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
		 
		 Gson gsonBuilder = new GsonBuilder().create();
		 
		 //fetch parameters 
    	 String gender=request.getParameter("gender");
    	 int agefrom=Integer.parseInt(request.getParameter("agefrom").toString());
    	 int ageto=Integer.parseInt(request.getParameter("ageto").toString());
    	 int religiion=Integer.parseInt(request.getParameter("religiion").toString());
    	 int mother_tongue=Integer.parseInt(request.getParameter("mother_tongue").toString());
    	 int caste=Integer.parseInt(request.getParameter("caste").toString());
    	 //setter methods
    	 Home obj=new Home();
    	 obj.setGender(gender);
    	 obj.setAgefrom(agefrom);
    	 obj.setAgeto(ageto);
    	 obj.setReligiion(religiion);
    	 obj.setMother_tongue(mother_tongue);
    	 obj.setCaste(caste);
    	 
    	 List<User> l1=HomeObjDao.get_all_userlist_on_page_load_home(userid,obj);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	//home page search after reach bottom of page
	@RequestMapping(value="/get_all_userlist_after_reach_bottom_of_page_home",method = RequestMethod.POST)  
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
		
		//fetch parameters 
		 String gender=request.getParameter("gender");
		 int agefrom=Integer.parseInt(request.getParameter("agefrom").toString());
		 int ageto=Integer.parseInt(request.getParameter("ageto").toString());
		 int religiion=Integer.parseInt(request.getParameter("religiion").toString());
		 int mother_tongue=Integer.parseInt(request.getParameter("mother_tongue").toString());
		 int caste=Integer.parseInt(request.getParameter("caste").toString());
		 //setter methods
		 Home obj=new Home();
		 obj.setGender(gender);
		 obj.setAgefrom(agefrom);
		 obj.setAgeto(ageto);
		 obj.setReligiion(religiion);
		 obj.setMother_tongue(mother_tongue);
		 obj.setCaste(caste);
		
		
		 Gson gsonBuilder = new GsonBuilder().create();
		 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
    	 List<User> l1=HomeObjDao.get_all_userlist_after_reach_bottom_of_page_home(lastpostid,userid,obj);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    }
	
	@RequestMapping(value="/get_all_advanceregularsearchlist_on_page_load_home",method = RequestMethod.POST) 
    public void get_all_advanceregularsearchlist_on_page_load_home(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
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
		 
		 //advance search details
		 String gender=request.getParameter("gender");
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
    	 
    	 List<User> l1=HomeObjDao.get_all_advanceregularsearchlist_on_page_load_home(gender,userid,searchid,paramtype,mothertongueArray,religionadvsrnm,casteadvsrnm,age_form,age_to,height_info_from_id,height_info_to_id,maritalsts,higheducadvsrnm,annlincmadvsrnm,empinadvsrnm,occplistadvsrnm,cntrylistadvsrnm,statelistadvsrnm);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	@RequestMapping(value="/get_all_advanceregularsearchlist_after_reach_bottom_of_page_home",method = RequestMethod.POST) 
    public void get_all_advanceregularsearchlist_after_reach_bottom_of_page_home(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
	
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
    	 
    	 //advance search details
		 String gender=request.getParameter("gender");
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
    	 
    	 List<User> l1=HomeObjDao.get_all_advanceregularsearchlist_after_reach_bottom_of_page_home(gender, lastpostid, userid, searchid, paramtype, mothertongueArray, religionadvsrnm, casteadvsrnm, age_form, age_to, height_info_from_id, height_info_to_id, maritalsts, higheducadvsrnm, annlincmadvsrnm, empinadvsrnm, occplistadvsrnm, cntrylistadvsrnm, statelistadvsrnm);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    }
	//used to get religion list 
    @ModelAttribute("religionlisthashmap")
    public Map<Integer, String> myreligionlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> religionlisthashmap=dao.getreligionlist();
    	
       return religionlisthashmap;
    }
  //used to get mother tongue list
    @ModelAttribute("mothertonguelisthashmap")
    public Map<Integer, String> mymothertonguelisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> mothertonguelisthashmap=dao.get_mother_tongue_list();
    	
       return mothertonguelisthashmap;
    }
    
 // used for caste list
    @ModelAttribute("castelisthashmap")
    public Map<Integer, String> mycastelisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> castelisthashmap=dao.get_caste_list();
    	
       return castelisthashmap;
    }
 
  //##########################advance search in sidebar###############################  
 // used for height  list
    @ModelAttribute("heightlisthashmap")
    public Map<Integer, String> myheightlisthashmap(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
    	
    	Map<Integer, String> heightlisthashmap=dao.get_height_list();
    	
       return heightlisthashmap;
    }
    
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
  		
  		
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> get_mother_tongue_list_with_count=HomeObjDao.get_mother_tongue_list_with_count(userid,gender);
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> get_maritalstatus_list_with_count=HomeObjDao.get_maritalstatus_list_with_count(userid,gender);
      	
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> get_religion_list_with_count=HomeObjDao.get_religion_list_with_count(userid,gender);
      	
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
  		
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> get_caste_list_with_count=HomeObjDao.get_caste_list_with_count(userid,gender);
      	
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
  		
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> get_highest_education_list_with_count=HomeObjDao.get_highest_education_list_with_count(userid,gender);
      	
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
  		
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> annualincome_list_with_count=HomeObjDao.annualincome_list_with_count(userid,gender);
      	
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> employedin_list_with_count=HomeObjDao.employedin_list_with_count(userid,gender);
      	
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> occupation_list_with_count=HomeObjDao.occupation_list_with_count(userid,gender);
      	
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> country_list_with_count=HomeObjDao.country_list_with_count(userid,gender);
      	
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
  		String gender=request.getParameter("gender");
  		
      	List<Regular_Search> state_list_with_count=HomeObjDao.state_list_with_count(userid,gender);
      	
         return state_list_with_count;
      }
    //##########################advance search in sidebar###############################
}
