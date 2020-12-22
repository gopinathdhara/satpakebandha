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
import com.beans.Message;
import com.beans.Profile;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;
import com.beans.UserActivity;
import com.dao.MessageDao;
import com.dao.SearchDao;
import com.dao.UserActivityDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@Controller 

public class MessageController {
	@Autowired    
	MessageDao dao;//will inject dao from XML file 
	@RequestMapping("/chat")    
    public String chat(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){  
		 
		//###########check login###############
		long userid=(long) 0;
		long receiverid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		 
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		List<User> u=dao.all_user_chat_list(userid);
		try
		{
			receiverid=Long.parseLong(request.getParameter("receiverid").toString());
		}
		catch(Exception e)
		{
			receiverid=(long) 0;
		}
		
		
		if(receiverid>0)
		{
			List<Message> msg=dao.individual_user_chat_message(userid,receiverid);
			User recvusr= dao.get_chat_receiver_profiledetails_by_id(receiverid);
			m.addAttribute("message_info",msg);
			m.addAttribute("receiver_info",recvusr);
		}
		
		//###########check login###############
		m.addAttribute("onlineuser", u);
		
		m.addAttribute("sessionuid", userid);
        return "frontend/chat";   
    } 
	
	
	   
	@RequestMapping(value="/check_if_message_comes",method = RequestMethod.POST)
    public void check_if_message_comes(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{  
		 
		//###########check login###############
		long userid=(long) 0;
		long receiverid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		 
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 
			 
		}
		 receiverid=Long.parseLong(request.getParameter("receiverid").toString());
		 long lastmsgid=Long.parseLong(request.getParameter("lastmsgid").toString());
		 Gson gsonBuilder = new GsonBuilder().create();
    	 String type=request.getParameter("type");
    	 List<Message> l1=dao.check_if_message_comes(userid,receiverid,lastmsgid);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
		   
    } 
	
	@RequestMapping(value="/insert_message",method = RequestMethod.POST)
    public void insert_message(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException{  
		 
		//###########check login###############
		long userid=(long) 0;
		long receiverid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		 
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 
		}
		 receiverid=Long.parseLong(request.getParameter("receiverid").toString());
		 String message_input=request.getParameter("message_input").toString();
		 Gson gsonBuilder = new GsonBuilder().create();
    	 String type=request.getParameter("type");
    	 String created_date=java.time.LocalDate.now().toString();
    	 
    	 //setter method
    	 Message obj=new Message();
    	 obj.setUserid(userid);
    	 obj.setReceiverid(receiverid);
    	 obj.setMsg(message_input);
    	 obj.setCreated_date(created_date);
    	 //call dao to insert message
    	 long lastinsertid=dao.insert_message(obj);
    	 List<Message> l1=dao.check_insert_message(userid,receiverid,lastinsertid);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
		   
    } 
	
	// chat with single
	@RequestMapping("/chatwithmember")    
    public String chatwithmember(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){  
		 
		//###########check login###############
		long userid=(long) 0;
		long receiverid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		 
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		
		try
		{
			receiverid=Long.parseLong(request.getParameter("receiverid").toString());
		}
		catch(Exception e)
		{
			receiverid=(long) 0;
		}
		
		List<User> u=dao.all_user_chat_list_single(userid,receiverid);
		
		if(receiverid>0)
		{
			List<Message> msg=dao.individual_user_chat_message(userid,receiverid);
			User recvusr= dao.get_chat_receiver_profiledetails_by_id(receiverid);
			m.addAttribute("message_info",msg);
			m.addAttribute("receiver_info",recvusr);
		}
		
		//###########check login###############
		m.addAttribute("onlineuser", u);
		
		m.addAttribute("sessionuid", userid);
        return "frontend/chat";   
    } 
	
	 
	
	@RequestMapping("/mymessages")
    public String mymessages(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){  
		 
		//###########check login###############
		long userid=(long) 0;
		long receiverid=(long) 0;
		try
		{
		 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
		 
		}
		catch(Exception e)
		{
			 userid=(long) 0;
			 return "redirect:/login";
			 
		}
		long pageid1=Long.parseLong(request.getParameter("pageid").toString());
		long total=Common_Info.perpage;    
	        if(pageid1==1){}    
	        else{    
	            pageid1=(pageid1-1)*total+1;    
	        }    
		
		
		List<Message> u=dao.my_all_message(userid,pageid1,total);
		Message mobj=dao.my_all_message_total_record(userid);
		
		//##############update message read status###################
		
		dao.update_mymessages_status_count(userid);
		
		long totrecord=mobj.getTotalrecord();
		
		//System.out.print("tot"+totrecord);
		
		long linkcount=(long) Math.ceil(totrecord/(double)total);
		
		//System.out.print("linkcount"+linkcount);
		
		request.setAttribute("linkcount", linkcount);
		m.addAttribute("mymsg", u);
		m.addAttribute("sessionuid", userid);
        return "frontend/mymessages";   
    } 
}
