package com.controllers;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.City;
import com.beans.Common_Info;
import com.beans.Email_Template;
import com.beans.Login;
import com.beans.State;
import com.beans.User;
import com.beans.UserActivity;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Api.SmsApi;
import ClickSend.Api.TransactionalEmailApi;
import ClickSend.Model.Email;
import ClickSend.Model.EmailFrom;
import ClickSend.Model.EmailRecipient;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator; 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import org.springframework.mail.javamail.MimeMessageHelper;

@Controller 
public class RegisterController {
	
	@Autowired    
    UserDao dao;//will inject dao from XML file 
	@Autowired
    private JavaMailSender mailSender;
	@RequestMapping("/register")    
    public String RegisterLogin(Model m){  
		m.addAttribute("command", new User()); 
		
        return "frontend/register";   
    } 
	
	  /*It saves object into database. The @ModelAttribute puts request data  
     *  into model object. You need to mention RequestMethod.POST method   
     *  because default request is GET*/    
	// used for registration
    @RequestMapping(value="/registersave",method = RequestMethod.POST)    
    public String save(@ModelAttribute("command")@Valid User u,BindingResult result,Model m,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
    	
    	if (result.hasErrors()) {
            return "frontend/register";
        }
    	
    	String verification_code=this.getRandomNumberString();
    	u.setPhone_verification_code(verification_code);
    	//##########check email already exist##############
    	User usrchkemlobj=dao.check_email_exist(u.getEmail());
    	long user_id=0;
    	if(usrchkemlobj.getTotalrecord()==0)
    	{
    	    user_id=dao.saveuserinfo(u);   
            
            m.addAttribute("successmsg","You have registered successfully");
    	}
    	else
    	{
    		m.addAttribute("errormsg","Email already exist");
    		return "redirect:/register";
    	}
        
        
        /*################send mail parameter##########################*/
    	
        String user_id_mail=String.valueOf(user_id);
        String recipientAddress = u.getEmail();
        
        String subject = "7pakebandha Registration";
        
        String msg="";
        
        // Encode using basic encoder
         String base64encodedString = Base64.getEncoder().encodeToString(user_id_mail.getBytes("utf-8"));
         
         //##########get info from common setting class############
         
         
         	String url = request.getRequestURL().toString();
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
         
         
         //###############call dao to get email templateinfo###################
         Email_Template objemltemp=dao.email_template_text();
         String template_text=objemltemp.getTemplate_text();
         
         String replaceString1=template_text.replace("{{logo}}",Common_Info.liveurl+"resources/images/logo2.png");
         String replaceString2=replaceString1.replace("{{heading}}","Activate Your Account");
         String replaceString3=replaceString2.replace("{{mytext}}","You have registered successfully. Please click this below link to activate your account.<br/> "+msg);
         String replaceString4=replaceString3.replace("{{sitelink}}",Common_Info.liveurl);
         String replaceString5=replaceString4.replace("{{username}}",u.getName());
       //###############call dao to get email templateinfo###################
        
        String from=Common_Info.from_email;
        
        
      //##########get info from common setting class############
        //sendMail(from,recipientAddress,subject,replaceString4);
        
        
        /*################send mail parameter##########################*/
        
        //###################click send email api##########################
        
        ApiClient defaultClient = new ApiClient();
	    defaultClient.setUsername(Common_Info.clicksend_email);
	    defaultClient.setPassword(Common_Info.clicksend_apikey);
	    TransactionalEmailApi apiInstance = new TransactionalEmailApi(defaultClient);
	    EmailRecipient emailRecipient=new EmailRecipient();
	    emailRecipient.email(recipientAddress);
	    emailRecipient.name(u.getName());
	    List<EmailRecipient> emailRecipientList=Arrays.asList(emailRecipient);
	    EmailFrom emailFrom=new EmailFrom();
	    emailFrom.emailAddressId("15535");
	    emailFrom.name("7pakebanhda.in");
	    
	    Email email = new Email(); // Email | Email model
	    email.to(emailRecipientList);
	    email.from(emailFrom);
	    email.subject(subject);
	    email.body(replaceString5);
	    
	    try {
	        String api_result = apiInstance.emailSendPost(email);
	        System.out.println(api_result);
	    } catch (ApiException e) {
	        System.err.println("Exception when calling TransactionalEmailApi#emailSendPost");
	        e.printStackTrace();
	    }
	  
	    
	  //###################click send email api##########################
	    
	  //###################click send sms api######################
	    
		
	    SmsApi apiInstance_sms = new SmsApi(defaultClient);

	    SmsMessage smsMessage=new SmsMessage();
	    
	    smsMessage.body("Hi from 7pakebandha.in, Your OTP is "+verification_code+".Verify your mobile using it.");
	    smsMessage.to(u.getPhone_no());
	    smsMessage.source("java");

	    List<SmsMessage> smsMessageList=Arrays.asList(smsMessage);
	    // SmsMessageCollection | SmsMessageCollection model
	    SmsMessageCollection smsMessages = new SmsMessageCollection();
	    smsMessages.messages(smsMessageList);
	    try {
	        String result_sms_api = apiInstance_sms.smsSendPost(smsMessages);
	        System.out.println(result_sms_api);
	    } catch (ApiException e) {
	        System.err.println("Exception when calling SmsApi#smsSendPost");
	        e.printStackTrace();
	    }
	    
	  //###################click send sms api######################
        
        return "redirect:/verify-phoneno?usrid="+base64encodedString;//will redirect to verify phone no    
    }
    
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
    
    @RequestMapping("/verify-phoneno")    
    public String verifyphoneno(Model m,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{  
		//m.addAttribute("command", new Login()); 
		String userid=request.getParameter("usrid");
    	// Decode
        byte[] base64decodedBytes = Base64.getDecoder().decode(userid);
        String originaluserid=new String(base64decodedBytes, "utf-8");
        //System.out.print("kk"+originaluserid);
        long useriddb=Long.parseLong(originaluserid.toString());
        request.setAttribute("useriddb",useriddb);
        return "frontend/verifyphoneno";   
    } 
    // for phone no verify
    @RequestMapping("/check_if_mobile_code_ok")
    public void check_if_mobile_code_ok(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	long useriddb=Long.parseLong(request.getParameter("useriddb").toString());
    	String phonecode=request.getParameter("phonecode");
        int status=dao.check_if_mobile_code_ok(useriddb,phonecode);
        
		 List<User> l1=new ArrayList();
		 User uaobj1=new User();
		 uaobj1.setStatus(status);
		 l1.add(uaobj1);
		 
		 Gson gsonBuilder = new GsonBuilder().create();
		 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(jsonFromJavaArrayList);
    	
    }
    
    
    /*################send mail##########################*/
    public void sendMail(final String from, final String to,final String subject,final String msg) {  
        
		 try {

	            MimeMessage message = mailSender.createMimeMessage();

	            message.setSubject(subject);
	            MimeMessageHelper helper;
	            helper = new MimeMessageHelper(message, true);
	            helper.setFrom(from);
	            helper.setTo(to);
	            helper.setText(msg, true);
	            mailSender.send(message);
	        } catch (MessagingException ex) {
	            //Logger.getLogger(HTMLMail.class.getName()).log(Level.SEVERE, null, ex);
	        	System.out.print("Mail sent failed");
	        } 
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
    
    //get state list as response 
    @RequestMapping("/get_state_list_by_country")    
    public void get_state_list_by_country(HttpServletRequest request,HttpServletResponse response) throws IOException{  
    	 Gson gsonBuilder = new GsonBuilder().create();
    	 String cid=request.getParameter("cid");
    	 List<State> l1=dao.get_state_list(cid);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
    } 
    
    //get city list as response 
    @RequestMapping("/get_city_list_by_state")    
    public void get_city_list_by_state(HttpServletRequest request,HttpServletResponse response) throws IOException{  
    	 Gson gsonBuilder = new GsonBuilder().create();
    	 String sid=request.getParameter("sid");
    	 List<City> l1=dao.get_city_list(sid);
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
    } 
    
  //########update email varification###########
    @RequestMapping("/activateaccount")
    public String activateaccount(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
    	String userid=request.getParameter("usrid");
    	// Decode
        byte[] base64decodedBytes = Base64.getDecoder().decode(userid);
        String originaluserid=new String(base64decodedBytes, "utf-8");
        //System.out.print("kk"+originaluserid);
        long useriddb=Long.parseLong(originaluserid.toString());
        dao.update_email_verification_status(useriddb);
    	return "redirect:/login?verifymsg=activate";//will redirect to login request mapping    
    }
}
