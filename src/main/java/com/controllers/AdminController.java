package com.controllers;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
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
import com.beans.Message;
import com.beans.Payment_Details;
import com.beans.Profile;
import com.beans.State;
import com.beans.User;
import com.dao.AdminDao;
import com.dao.UserActivityDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller 

public class AdminController {
	@Autowired    
    UserDao udao1;//will inject dao from XML file 
	@Autowired    
	AdminDao dao;//will inject dao from XML file 
	@Autowired    
	UserActivityDao udao;//will inject dao from XML file 
	
	//used for login
	@RequestMapping("/admin")    
    public String admin(Model m){  
		
		m.addAttribute("command", new Login()); 
		
        return "backend/adminlogin";   
    } 
	
	//used for check login
		 @RequestMapping(value="/adminloginsave",method = RequestMethod.POST)    
		    public String adminloginsave(@ModelAttribute("command")@Valid Login u,BindingResult result,Model m, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		    	
		    	if (result.hasErrors()) {
		            return "backend/adminlogin";
		        }
		    	else
		    	{
		    		//check login
		    		long userid=dao.login_check(u);
		    		//System.out.print("yy"+u.getPassword());
					if(userid==0)
					{
						m.addAttribute("errmsg","Invalid email id or password");
						return "redirect:/admin";
					}
					else
					{
						
						session.setAttribute("admin_usr_id", userid);
						
						return "redirect:/dashboard";
						
					}
		    	}
		           
		    }
		 
	 @RequestMapping("/adminlogout") 
		public String adminlogout(Model m,HttpSession session)
		{
		 	long adminid=(long) 0;
			try
			{
				adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			 
			}
			catch(Exception e)
			{
				adminid=(long) 0;
				
			}
		 
			session.invalidate();
			return "redirect:/admin";
		}
		 
	 @RequestMapping("/dashboard")    
	    public String dashboard(Model m, HttpSession session){ 
		 
		//###########check login###############
			long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 return "redirect:/admin";
				 
			}
			//###########check login###############
	        return "backend/dashboard";   
	    } 
	 
	 @RequestMapping("/adminusers")    
	    public String adminusers(Model m, HttpSession session,HttpServletRequest request,HttpServletResponse response){ 
		 
		//###########check login###############
			long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 return "redirect:/admin";
				 
			}
			long pageid1=Long.parseLong(request.getParameter("pageid").toString());
			long total=Common_Info.adminperpage;    
		        if(pageid1==1){}    
		        else{    
		            pageid1=(pageid1-1)*total+1;    
		        } 
			List<User> list1= dao.admin_get_user_list(pageid1,total);
			User uobj=dao.admin_get_user_list_total_record();
			
			long totrecord=uobj.getTotalrecord();
			
			//System.out.print("tot"+totrecord);
			
			long linkcount=(long) Math.ceil(totrecord/(double)total);
			
			//System.out.print("linkcount"+linkcount);
			
			request.setAttribute("linkcount", linkcount);
			
			
			m.addAttribute("userlist", list1);
			//###########check login###############
	        return "backend/adminusers";   
	    } 
	 
	 
	 @RequestMapping("/admintransaction")    
	    public String admintransaction(Model m, HttpSession session,HttpServletRequest request,HttpServletResponse response){ 
		 
		//###########check login###############
			long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 return "redirect:/admin";
				 
			}
			
			long pageid1=Long.parseLong(request.getParameter("pageid").toString());
			long total=Common_Info.adminperpage;    
		        if(pageid1==1){}    
		        else{    
		            pageid1=(pageid1-1)*total+1;    
		        } 
			List<Payment_Details> list1= dao.admin_all_transaction(pageid1,total);
			Payment_Details uobj=dao.admin_all_transaction_total_record();
			
			long totrecord=uobj.getTotalrecord();
			
			//System.out.print("tot"+totrecord);
			
			long linkcount=(long) Math.ceil(totrecord/(double)total);
			
			//System.out.print("linkcount"+linkcount);
			
			request.setAttribute("linkcount", linkcount);
			
			
			m.addAttribute("mytransaction", list1);
			//###########check login###############
	        return "backend/admintransaction";   
	    } 
	 
	//cash on delivery payment status update
	 @RequestMapping(value="/admin_update_transaction_status",method = RequestMethod.POST)  
	    public void admin_update_transaction_status(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
			//###########check login###############
		 	long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 //return "redirect:/admin";
				 
			}
			//###########check login###############
			 
			 Gson gsonBuilder = new GsonBuilder().create();
			 long payment_id=Long.parseLong(request.getParameter("payment_id").toString());
	    	
	    	 
	    	 //#################check if cash on delivery is active####################
			 Boolean chk= dao.admin_update_transaction_status(payment_id);
	    	 
	    	 int statusflag=0;
	    	 if(chk==true)
	    	 {
	    		 statusflag=1;
	    		  
	    	 }
	    	 else
	    	 {
	    		 statusflag=0;
	    		 
	    	 }
	    	//#################check if cash on delivery is active####################
	    	 
	    	 User outputobj=new User();
	    	 outputobj.setPayment_flag(statusflag);
	    	 
	    	 List<User> l1=new ArrayList<User>();
	    	 l1.add(outputobj);
	    	 
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
	 
	 //change account status
	 
	 @RequestMapping(value="/admin_update_account_status",method = RequestMethod.POST)  
	    public void admin_update_account_status(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
			//###########check login###############
		 	long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 //return "redirect:/admin";
				 
			}
			//###########check login###############
			 
			 Gson gsonBuilder = new GsonBuilder().create();
			 long user_id=Long.parseLong(request.getParameter("user_id").toString());
			 int status=Integer.parseInt(request.getParameter("status").toString());
	    	 
	    	 //#################check if account is active####################
			 Boolean chk= dao.admin_update_account_status(user_id,status);
	    	 
	    	 int statusflag=0;
	    	 if(chk==true)
	    	 {
	    		 statusflag=1;
	    		  
	    	 }
	    	 else
	    	 {
	    		 statusflag=0;
	    		 
	    	 }
	    	//#################check if account is active####################
	    	 
	    	 User outputobj=new User();
	    	 outputobj.setPayment_flag(statusflag);
	    	 
	    	 List<User> l1=new ArrayList<User>();
	    	 l1.add(outputobj);
	    	 
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
	 
	 //change email verification status
	 
	 @RequestMapping(value="/admin_update_email_status",method = RequestMethod.POST)  
	    public void admin_update_email_status(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
			//###########check login###############
		 	long adminid=(long) 0;
			try
			{
			 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
			}
			catch(Exception e)
			{
					adminid=(long) 0;
				 //return "redirect:/admin";
				 
			}
			//###########check login###############
			 
			 Gson gsonBuilder = new GsonBuilder().create();
			 long user_id=Long.parseLong(request.getParameter("user_id").toString());
			 int status=Integer.parseInt(request.getParameter("status").toString());
	    	 
	    	 //#################check if email verification is active####################
			 Boolean chk= dao.admin_update_email_status(user_id,status);
	    	 
	    	 int statusflag=0;
	    	 if(chk==true)
	    	 {
	    		 statusflag=1;
	    		  
	    	 }
	    	 else
	    	 {
	    		 statusflag=0;
	    		 
	    	 }
	    	//#################check if email verification is active####################
	    	 
	    	 User outputobj=new User();
	    	 outputobj.setPayment_flag(statusflag);
	    	 
	    	 List<User> l1=new ArrayList<User>();
	    	 l1.add(outputobj);
	    	 
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
	 
	 		//profile image upload by admin for user
			@RequestMapping("/adminprofilepicupload")    
		    public String profilepicupload(Model m,HttpSession session,HttpServletRequest request){ 
				
				//###########check login###############
				long adminid=(long) 0;
				try
				{
				 adminid=Long.parseLong(session.getAttribute("admin_usr_id").toString());
				}
				catch(Exception e)
				{
						adminid=(long) 0;
					 return "redirect:/admin";
					 
				}
				//###########check login###############
				
				long userid=(long) 0;
				userid=Long.parseLong(request.getParameter("userid").toString());
				
				User uobj = udao1.getprofiledetails_by_id(userid);    
			    m.addAttribute("uobj",uobj); 
				
		        return "backend/userprofilepicupload";   
		    } 
			
			@RequestMapping(value="/userprofilepicsavebyadmin",method = RequestMethod.POST)    
		    public String profilepicsave(@RequestParam CommonsMultipartFile file,Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
			
				//###########check login###############
				long userid=(long) 0;
				userid=Long.parseLong(request.getParameter("userid").toString());
				System.out.println("userid");
				System.out.println(userid);
				
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
			    udao.updateprofileimage(obj);
			    
	            return "redirect:/adminprofilepicupload?userid="+userid;   
		}
	 
}
