package com.controllers;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.beans.City;
import com.beans.Common_Info;
import com.beans.Login;
import com.beans.Profile;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;
import com.beans.UserActivity;
import com.dao.SearchDao;
import com.dao.UserActivityDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@Controller 
public class UserActivityController {
	@Autowired    
	UserActivityDao dao;//will inject dao from XML file 
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 5;  // 5MB
	//send interest
	@RequestMapping(value="/sendinterest",method = RequestMethod.POST)  
    public void sendinterest(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		
		 
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
		 
		 Gson gsonBuilder = new GsonBuilder().create();
		 long receiver_id=Long.parseLong(request.getParameter("receiver_id").toString());
		 //setter method
		 UserActivity uaobj=new UserActivity();
		 uaobj.setSender_id(userid);
		 uaobj.setReceiver_id(receiver_id);
		 uaobj.setCreated_date(java.time.LocalDate.now());
		 uaobj.setStatus(0); // 0 for pending
		 
    	 int result=dao.sendinterest(uaobj);
    	 
    	 List<UserActivity> l1=new ArrayList();
    	 UserActivity uaobj1=new UserActivity();
    	 uaobj1.setStatus(result);
    	 l1.add(uaobj1);
    	 
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	@RequestMapping("/mailbox")    
    public String mailbox(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){  
		 
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
		
		String type=request.getParameter("type");
		dao.update_mailbox_status_count(userid, type);
		
		m.addAttribute("sessionuid", userid);
        return "frontend/mailbox";   
    } 
	//get all mailbox list on page load
	@RequestMapping(value="/get_all_mailbox_on_page_load",method = RequestMethod.POST)  
    public void get_all_mailbox_on_page_load(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
		 
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
		 Gson gsonBuilder = new GsonBuilder().create();
    	 String type=request.getParameter("type");
    	 List<User> l1=dao.get_all_mailbox_on_page_load(userid,type);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	//get all mailbox list after reach bottom of page
	@RequestMapping(value="/get_all_mailbox_after_reach_bottom_of_page",method = RequestMethod.POST)  
    public void get_all_mailbox_after_reach_bottom_of_page(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
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
		 Gson gsonBuilder = new GsonBuilder().create();
		 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
    	 String type=request.getParameter("type");
    	 List<User> l1=dao.get_all_mailbox_after_reach_bottom_of_page(userid,type,lastpostid);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    }
	//accept interest
	@RequestMapping(value="/acceptinterest",method = RequestMethod.POST)  
    public void acceptinterest(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		
		 
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
		 Gson gsonBuilder = new GsonBuilder().create();
		 long sendinterestid=Long.parseLong(request.getParameter("sendinterestid").toString());
		 //setter method
		 UserActivity uaobj=new UserActivity();
		 
		 uaobj.setId(sendinterestid);
		 uaobj.setCreated_date(java.time.LocalDate.now());
		 uaobj.setStatus(1); // 1 for accept
		 
    	 Boolean result=dao.acceptinterest(uaobj);
    	 
		 List<UserActivity> l1=new ArrayList();
    	 UserActivity uaobj1=new UserActivity();
    	 uaobj1.setStatus(1);
    	 l1.add(uaobj1);
    	 
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	//decline interest
	@RequestMapping(value="/declineinterest",method = RequestMethod.POST)  
    public void declineinterest(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		
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
		 Gson gsonBuilder = new GsonBuilder().create();
		 long sendinterestid=Long.parseLong(request.getParameter("sendinterestid").toString());
		 //setter method
		 UserActivity uaobj=new UserActivity();
		 
		 uaobj.setId(sendinterestid);
		 uaobj.setCreated_date(java.time.LocalDate.now());
		 uaobj.setStatus(2); // 2 for reject
		 
    	 Boolean result=dao.declineinterest(uaobj);
    	 
		 List<UserActivity> l1=new ArrayList();
    	 UserActivity uaobj1=new UserActivity();
    	 uaobj1.setStatus(1);
    	 l1.add(uaobj1);
    	 
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	//shortlist
	
		@RequestMapping(value="/shortlist",method = RequestMethod.POST)  
	    public void shortlist(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			
			 
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
			 
			 Gson gsonBuilder = new GsonBuilder().create();
			 long receiver_id=Long.parseLong(request.getParameter("receiver_id").toString());
			 //setter method
			 UserActivity uaobj=new UserActivity();
			 uaobj.setSender_id(userid);
			 uaobj.setReceiver_id(receiver_id);
			 uaobj.setCreated_date(java.time.LocalDate.now());
			 
			 
	    	 int result=dao.shortlist(uaobj);
	    	 
	    	 List<UserActivity> l1=new ArrayList();
	    	 UserActivity uaobj1=new UserActivity();
	    	 uaobj1.setStatus(result);
	    	 l1.add(uaobj1);
	    	 
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
		//shortlist users
		@RequestMapping("/shortlistedbyyou")    
	    public String shortlistedbyyou(Model m,HttpSession session){  
			
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
			
	        return "frontend/allshortlist";   
	    } 
		
		@RequestMapping(value="/get_all_usershortlist_on_page_load",method = RequestMethod.POST)  
	    public void get_all_usershortlist_on_page_load(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
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
	    	 //String sid=request.getParameter("sid");
	    	 List<User> l1=dao.get_all_usershortlist_on_page_load(userid);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
		
		@RequestMapping(value="/get_all_usershortlist_after_reach_bottom_of_page",method = RequestMethod.POST)  
	    public void get_all_usershortlist_after_reach_bottom_of_page(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
			 
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
			 long lastpostid=Long.parseLong(request.getParameter("lastpostid").toString());
	    	 List<User> l1=dao.get_all_usershortlist_after_reach_bottom_of_page(lastpostid,userid);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    }
		//profile image upload
		@RequestMapping("/profilepicupload")    
	    public String profilepicupload(Model m,HttpSession session){  
			
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
			
	        return "frontend/profilepicupload";   
	    } 
		
		//pic save into folder and database
		
		@RequestMapping(value="/profilepicsave",method = RequestMethod.POST)    
	    public String profilepicsave(@RequestParam CommonsMultipartFile file,Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		
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
			
			String currentDirectory = System.getProperty("user.dir");
			
		      //System.out.println("The current working directory is " + currentDirectory);
			
			ServletContext context = session.getServletContext();
			
			
		    //String path = context.getRealPath(UPLOAD_DIRECTORY); 
			//String path = "C:/Users/GOPINATH/eclipse-workspace1/satpakebandhaproject/src/main/webapp/resources/styles/userprofileimages";
			//String path = context.getRealPath("/resources/styles/userprofileimages/");
			
			
			
			String url = request.getRequestURL().toString();
			String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";     
			
			//System.out.print(baseURL);
			String path="";
			
			if(baseURL.contains("localhost"))
			{
				 path = Common_Info.imgupload_local_url;
			}
			else
			{
				 path = context.getRealPath("/resources/styles/userprofileimages/"); 
			}
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String filename = timestamp.getTime()+file.getOriginalFilename();  
		  
		    //System.out.println(path+" "+filename);        
		  
		    byte[] bytes = file.getBytes();  
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
		         new File(path + File.separator + filename)));  
		    stream.write(bytes);  
		    stream.flush();  
		    stream.close();  
		    
		    //setter method
		    User obj=new User();
		    obj.setProfile_image(filename);
		    obj.setId(userid);
		    //call dao
		    dao.updateprofileimage(obj);
		    
            return "redirect:/profiledetails";   
	}
		
   // mail box notification
		
		@RequestMapping(value="/check_if_mailbox_comes_count",method = RequestMethod.POST)  
	    public void check_if_mailbox_comes_count(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
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
	    	 //String sid=request.getParameter("sid");
	    	 int totcount=dao.check_if_mailbox_comes_count(userid);
	    	 UserActivity objact=new UserActivity();
	    	 objact.setTotcount(totcount);
	    	 List<UserActivity> l1=new ArrayList<UserActivity>();
	    	 l1.add(objact);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
		
		// message notify
		
		@RequestMapping(value="/check_if_message_comes_count",method = RequestMethod.POST)  
	    public void check_if_message_comes_count(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
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
	    	 //String sid=request.getParameter("sid");
	    	 int totcount=dao.check_if_message_comes_count(userid);
	    	 UserActivity objact=new UserActivity();
	    	 objact.setTotcount(totcount);
	    	 List<UserActivity> l1=new ArrayList<UserActivity>();
	    	 l1.add(objact);
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
}
