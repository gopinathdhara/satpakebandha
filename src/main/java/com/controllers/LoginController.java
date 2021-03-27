package com.controllers;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
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
import com.beans.State;
import com.beans.User;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller 
public class LoginController {
	
	@Autowired    
    UserDao dao;//will inject dao from XML file 
	
	//used for login
	@RequestMapping("/login")    
    public String RegisterLogin(Model m){  
		m.addAttribute("command", new Login()); 
		
        return "frontend/login";   
    } 
	//used for check login
	 @RequestMapping(value="/loginsave",method = RequestMethod.POST)    
	    public String loginsave(@ModelAttribute("command")@Valid Login u,BindingResult result,Model m, HttpSession session,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
	    	
	    	if (result.hasErrors()) {
	            return "frontend/login";
	        }
	    	else
	    	{
	    		//check login
	    		
	    		long userid=dao.login_check(u);
	    		
				if(userid==0)
				{
					m.addAttribute("errmsg","Invalid email id or password");
					return "redirect:/login";
				}
				else
				{
					Profile pobj=dao.editprofiledetails(userid);
					if(pobj.getPhone_verification_status()==0)
					{
						m.addAttribute("errmsg","Mobile no is not verified");
						return "redirect:/login";
					}
					else if(pobj.getEmail_verification_status()==0)
					{
						m.addAttribute("errmsg","Email is not verified");
						return "redirect:/login";
					}
					else if(pobj.getStatus()==0)
					{
						m.addAttribute("errmsg","Your account is deactivated.");
						return "redirect:/login";
					}
					
					session.setAttribute("sess_usr_id", userid);
					session.setAttribute("sess_usr_type",0);
					//int userid=(Integer) session.getAttribute("sess_usr_id");
					
					
					if(pobj.getUpdate_profile_flag()==0)
					{
						return "redirect:/editprofile?notifymsg=profile-update-pending";
						//return "redirect:/partnerpreferences";
					}
					else
					{
						return "redirect:/allusers?paramtype=all";
					}
					
				}
	    	}
	           
	    }
	 
	 
	 @RequestMapping("/userlogout") 
		public String userlogout(Model m,HttpSession session)
		{
		 	long userid=(long) 0;
			try
			{
			 userid=Long.parseLong(session.getAttribute("sess_usr_id").toString());
			 
			}
			catch(Exception e)
			{
				 userid=(long) 0;
				
			}
		 	dao.logout_check(userid);
			session.invalidate();
			return "redirect:/login";
		} 
	
}
