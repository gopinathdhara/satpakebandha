package com.controllers;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
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
import com.beans.Common_Info;
import com.beans.Login;
import com.beans.Membership_Package;
import com.beans.Message;
import com.beans.Payment_Details;
import com.beans.Profile;
import com.beans.Regular_Search;
import com.beans.State;
import com.beans.User;
import com.dao.PaymentDao;
import com.dao.SearchDao;
import com.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

@Controller 
public class PaymentController {
	@Autowired    
    PaymentDao pdao;//will inject dao from XML file 
	
	
	
	@RequestMapping("/cashondelivery")
	public String cashondelivery(Model m,HttpSession session,HttpServletRequest request)
	{
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
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
		int packageid=Integer.parseInt(request.getParameter("packageid").toString());
		Membership_Package obj1=pdao.getmembership_package_by_id(packageid);
		int total_amount=obj1.getPaid_amount();
		int total_amount_paisa=Integer.parseInt(total_amount+"00");
		int duration=obj1.getDuration();
		String duration_type=obj1.getDuration_type();
		String transaction_id="CASH_"+timestamp.getTime();
		//setter method
		Payment_Details pdobjct=new Payment_Details();
		pdobjct.setUserinfo_id(userid);
		pdobjct.setMembership_package_id(packageid);
		pdobjct.setTotal_amount(total_amount);
		//pdobjct.setOrder_id(order_id);
		pdobjct.setTransaction_id(transaction_id);
		pdobjct.setCreated_date(java.time.LocalDate.now());
		pdobjct.setDuration(duration);
		pdobjct.setDuration_type(duration_type);
		pdobjct.setPackage_title(obj1.getTitle());
		pdobjct.setPackage_duration(obj1.getDuration());
		pdobjct.setPackage_duration_type(obj1.getDuration_type());
		pdobjct.setPackage_discount_percentage(obj1.getDiscount_percentage());
		pdobjct.setPackage_original_price(obj1.getOriginal_price());
		pdobjct.setPayment_type(1);
		
		//##############call dao to insert payment with pending status###################
		
		pdao.saveorderinfo_cash(pdobjct);
		
        
        return "redirect:/transaction?pageid=1";
		
		
		
	}
	
	@RequestMapping("/payment")    
    public String payment(Model m,HttpSession session,HttpServletRequest request) throws RazorpayException, ParseException{  
		
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
		
		
		
		int packageid=Integer.parseInt(request.getParameter("packageid").toString());
		Membership_Package obj1=pdao.getmembership_package_by_id(packageid);
		int total_amount=obj1.getPaid_amount();
		int total_amount_paisa=Integer.parseInt(total_amount+"00");
		int duration=obj1.getDuration();
		String duration_type=obj1.getDuration_type();
		
		// ###########################razor pay implementation###########################
		
		
		
		//##################### Make an order#####################
		
		RazorpayClient razorpayClient = new RazorpayClient(Common_Info.apikey,Common_Info.secretkey);
		Map<String, String> headers = new HashMap<String, String>();
		razorpayClient.addHeaders(headers); 
		JSONObject options = new JSONObject();
		options.put("amount", total_amount_paisa); // Note: The amount should be in paise.
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.Orders.create(options);
		//System.out.print("orderinfo"+order.toString());
		
		org.json.simple.JSONObject jsonObject = null;
		JSONParser parser=new JSONParser(); // this needs the "json-simple" library

		Object obj = parser.parse(order.toString());
		jsonObject=(org.json.simple.JSONObject)obj;
		  
		String order_id = (String) jsonObject.get("id");
		//session.setAttribute("myorderid", id);
		//System.out.print("order_id"+id);
		
		
		//setter method
		Payment_Details pdobjct=new Payment_Details();
		pdobjct.setUserinfo_id(userid);
		pdobjct.setMembership_package_id(packageid);
		pdobjct.setTotal_amount(total_amount);
		pdobjct.setOrder_id(order_id);
		pdobjct.setCreated_date(java.time.LocalDate.now());
		pdobjct.setDuration(duration);
		pdobjct.setDuration_type(duration_type);
		pdobjct.setPackage_title(obj1.getTitle());
		pdobjct.setPackage_duration(obj1.getDuration());
		pdobjct.setPackage_duration_type(obj1.getDuration_type());
		pdobjct.setPackage_discount_percentage(obj1.getDiscount_percentage());
		pdobjct.setPackage_original_price(obj1.getOriginal_price());
		pdobjct.setPayment_type(0);
		
		//##############call dao to insert payment with pending status###################
		
		pdao.saveorderinfo(pdobjct);
		Profile myprofobj=pdao.userdetails(userid);
		m.addAttribute("apikey",Common_Info.apikey);
		m.addAttribute("site_my_url",Common_Info.liveurl);
        m.addAttribute("order_id",order_id);
        m.addAttribute("total_amount",total_amount);
        m.addAttribute("myprofobj",myprofobj);
        
        return "frontend/payment";   
    } 
	// payment success
	@RequestMapping("/paymentsuccess")    
    public String paymentsuccess(Model m,HttpSession session,HttpServletRequest request) throws RazorpayException{  
		
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
		
		//@@@@@@@@@@ Razor pay returns these @@@@@@@@@@@@@@@@
		String razorpay_payment_id=request.getParameter("razorpay_payment_id");
		String order_id=request.getParameter("razorpay_order_id");
		String razorpay_signature=request.getParameter("razorpay_signature");
		
		//@@@@@@@@@@@@@@@@@@@@@@ Payment Verification @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		Payment_Details pdobjct1=new Payment_Details();
		pdobjct1.setOrder_id(order_id);
		String order_id_db=pdao.paymentdetails(pdobjct1);
		
		JSONObject options = new JSONObject();
		options.put("razorpay_order_id",order_id_db);
		options.put("razorpay_payment_id",razorpay_payment_id);
		options.put("razorpay_signature",razorpay_signature);
		boolean verf=Utils.verifyPaymentSignature(options,Common_Info.secretkey);
		
		//verification is true
		if(verf==true)
		{
			Payment_Details pdobjct=new Payment_Details();
			pdobjct.setOrder_id(order_id);
			pdobjct.setTransaction_id(razorpay_payment_id);
			//@@@@@@@@@ Update payment status @@@@@@@@@@@@@@@@@@ 
			pdao.update_payment_status(pdobjct);
		}
		
		
		//m.addAttribute("order_id",order_id);
		//m.addAttribute("razorpay_payment_id",razorpay_payment_id);
		//m.addAttribute("razorpay_signature",razorpay_signature);
		//m.addAttribute("verf",verf);
		
		return "redirect:/transaction?pageid=1"; 
    } 
	
	// get all membership package details
	@RequestMapping("/packagedetails")    
    public String packagedetails(Model m,HttpSession session,HttpServletRequest request)
    {
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
		 List packagedetails= pdao.get_membership_package_list();
		 m.addAttribute("packagedetails",packagedetails);
		 return "frontend/packagedetails";
		
    }
	
	// my transaction list
	@RequestMapping("/transaction")
    public String transaction(Model m,HttpSession session,HttpServletRequest request,HttpServletResponse response){  
		 
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
		//###########check login###############
		
		long pageid1=Long.parseLong(request.getParameter("pageid").toString());
		long total=Common_Info.perpage;    
	        if(pageid1==1){}    
	        else{    
	            pageid1=(pageid1-1)*total+1;    
	        }    
		
		
		List<Payment_Details> u=pdao.my_all_transaction(userid,pageid1,total);
		Payment_Details mobj=pdao.my_all_transaction_total_record(userid);
		
		long totrecord=mobj.getTotalrecord();
		
		//System.out.print("tot"+totrecord);
		
		long linkcount=(long) Math.ceil(totrecord/(double)total);
		
		//System.out.print("linkcount"+linkcount);
		
		request.setAttribute("linkcount", linkcount);
		m.addAttribute("mytransaction", u);
		m.addAttribute("sessionuid", userid);
        return "frontend/transaction";   
    } 
	
	//check if user is premium user to view phone no
	@RequestMapping(value="/check_ifpremium_user",method = RequestMethod.POST)  
    public void check_ifpremium_user(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
		 
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
		 long receiver_id=Long.parseLong(request.getParameter("receiver_id").toString());
    	
    	 
    	 //#################check if user is premium user####################
		 Boolean chk= pdao.check_ifpremium_user(userid);
    	 String phoneno=null;
    	 int premiumflag=0;
    	 if(chk==true)
    	 {
    		  premiumflag=1;
    		  //###############view phone no######################
    		  User uobj=pdao.get_phoneno_user(receiver_id);
    		  phoneno=uobj.getPhone_no();
    	 }
    	 else
    	 {
    		 premiumflag=0;
    		 phoneno="";
    	 }
    	//#################check if user is premium user####################
    	 
    	 User outputobj=new User();
    	 outputobj.setPremiumflag(premiumflag);
    	 outputobj.setPhone_no(phoneno);
    	 
    	 List<User> l1=new ArrayList<User>();
    	 l1.add(outputobj);
    	 
    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().write(jsonFromJavaArrayList);
           
    } 
	
	//check if premium user for chat
	
	@RequestMapping(value="/check_ifpremium_user_message",method = RequestMethod.POST)  
      public void check_ifpremium_user_message(Model m,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{  
			 
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
			
	    	 //#################check if user is premium user####################
			 Boolean chk= pdao.check_ifpremium_user(userid);
	    	 
	    	 int premiumflag=0;
	    	 if(chk==true)
	    	 {
	    		  premiumflag=1;
	    		  
	    	 }
	    	 else
	    	 {
	    		 premiumflag=0;
	    		
	    	 }
	    	//#################check if user is premium user####################
	    	 
	    	 User outputobj=new User();
	    	 outputobj.setPremiumflag(premiumflag);
	    	 
	    	 List<User> l1=new ArrayList<User>();
	    	 l1.add(outputobj);
	    	 
	    	 String jsonFromJavaArrayList = gsonBuilder.toJson(l1);
			 response.setContentType("application/json");
		     response.setCharacterEncoding("UTF-8");
		     response.getWriter().write(jsonFromJavaArrayList);
	           
	    } 
}
	