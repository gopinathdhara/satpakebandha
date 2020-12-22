package com.controllers;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class CmsController {
	
	@RequestMapping("/aboutus")    
    public String aboutus(Model m){  
	 	
        return "frontend/aboutus";   
    } 
	
	@RequestMapping("/contactus")    
    public String contactus(Model m){  
	 	
        return "frontend/contactus";   
    } 
	
	@RequestMapping("/terms")    
    public String terms(Model m){  
	 	
        return "frontend/terms";   
    } 
	
	@RequestMapping("/privacy-policy")    
    public String privacypolicy(Model m){  
	 	
        return "frontend/privacypolicy";   
    } 
	
	@RequestMapping("/pricing")    
    public String pricing(Model m){  
	 	
        return "frontend/pricing";   
    } 
	
	@RequestMapping("/cancel_policy")    
    public String cancel_policy(Model m){  
	 	
        return "frontend/cancel_policy";   
    } 
}
