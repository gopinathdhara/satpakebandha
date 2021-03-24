package com.controllers;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.Common_Info;
import com.beans.Email_Template;
import com.dao.UserDao;

//click send sms api
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Api.EmailMarketingApi;
import ClickSend.Api.SmsApi;
import ClickSend.Api.TransactionalEmailApi;
import ClickSend.Model.Email;
import ClickSend.Model.EmailAddress;
import ClickSend.Model.EmailFrom;
import ClickSend.Model.EmailRecipient;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import java.io.File;
import org.json.*;  

@Controller 
public class CmsController {
	
	@Autowired    
    UserDao dao;
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
	
	/*click send sms api*/
	@RequestMapping("/mysmsapi")     
    public void mysmsapi() throws IOException{ 
		
			//System.out.print("sms1");
			ApiClient defaultClient = new ApiClient();
		    defaultClient.setUsername("siteadmin@7pakebandha.in");
		    defaultClient.setPassword("31551A39-41AF-7FB3-B0AF-EE34147BEE33");
		    SmsApi apiInstance = new SmsApi(defaultClient);

		    SmsMessage smsMessage=new SmsMessage();
		    int verification_code=12345678;
		    smsMessage.body("Hi Sir, Your 7pakabandha activation code "+verification_code);
		    smsMessage.to("9804669402");
		    smsMessage.source("java");

		    List<SmsMessage> smsMessageList=Arrays.asList(smsMessage);
		    // SmsMessageCollection | SmsMessageCollection model
		    SmsMessageCollection smsMessages = new SmsMessageCollection();
		    smsMessages.messages(smsMessageList);
		    try {
		        String result = apiInstance.smsSendPost(smsMessages);
		        System.out.println(result);
		    } catch (ApiException e) {
		        System.err.println("Exception when calling SmsApi#smsSendPost");
		        e.printStackTrace();
		    }
		//System.out.print("sms");
    } 
	
	
	//email api
	@RequestMapping("/myemailapi")      
    public void myemailapi(HttpServletRequest request) throws IOException, ParseException{ 
	    
	    ApiClient defaultClient = new ApiClient();
	    defaultClient.setUsername("siteadmin@7pakebandha.in");
	    defaultClient.setPassword("31551A39-41AF-7FB3-B0AF-EE34147BEE33");
	    TransactionalEmailApi apiInstance = new TransactionalEmailApi(defaultClient);
	    EmailRecipient emailRecipient=new EmailRecipient();
	    emailRecipient.email("raghunathdhara12@gmail.com");
	    emailRecipient.name("raghu");
	    List<EmailRecipient> emailRecipientList=Arrays.asList(emailRecipient);
	    EmailFrom emailFrom=new EmailFrom();
	    emailFrom.emailAddressId("15535");
	    emailFrom.name("gopi");
	    
	    Email email = new Email(); // Email | Email model
	    email.to(emailRecipientList);
	    email.from(emailFrom);
	    email.subject("7pakebandha Email Verificaion for you");
	    
	  //###############call dao to get email templateinfo###################
	    String user_id_mail=String.valueOf(1);
	    String msg="";
	    String url = request.getRequestURL().toString();
	    String base64encodedString = Base64.getEncoder().encodeToString(user_id_mail.getBytes("utf-8"));
	    String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";     
		
		//System.out.print(baseURL);
		
		
		if(baseURL.contains("localhost"))
		{
			 msg+=" <a href='"+Common_Info.localurl+"activateaccount?usrid="+base64encodedString+"'> Activate Account </a>";
		}
		else
		{
			 msg+=" <a href='"+Common_Info.liveurl+"activateaccount?usrid="+base64encodedString+"'> Activate Account </a>";
		}
	   
        Email_Template objemltemp=dao.email_template_text();
        String template_text=objemltemp.getTemplate_text();
        
        String replaceString1=template_text.replace("{{logo}}",Common_Info.liveurl+"resources/images/logo2.png");
        String replaceString2=replaceString1.replace("{{heading}}","Activate Your Account");
        String replaceString3=replaceString2.replace("{{mytext}}","You have registered successfully. Please click this below link to activate your account.<br/> "+msg);
        String replaceString4=replaceString3.replace("{{sitelink}}",Common_Info.liveurl);
      //###############call dao to get email templateinfo###################
	    
	    email.body(replaceString4);
	    
	    try {
	        String result = apiInstance.emailSendPost(email);
	        System.out.println(result);
	    } catch (ApiException e) {
	        System.err.println("Exception when calling TransactionalEmailApi#emailSendPost");
	        e.printStackTrace();
	    }
	  }
}
