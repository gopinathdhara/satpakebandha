<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html dir="ltr" lang="en">
<%@ include file="includes/headcss.jsp" %>

<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<div class="body-overlay"></div>
<%@ include file="includes/sidepanel.jsp" %>
<div id="wrapper">
  <!-- preloader -->
  <%@ include file="includes/preloader.jsp" %>
  
  <!-- Header -->
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content" style="margin-top:120px">
    <!-- Section: inner-header -->
     

    <section >
     
      <div class="container">
        <div class="row">
          
          <div class="col-md-4">
          		<h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn" >Why Register</h4>
          		<p class="text-gray pt-10 mt-0 mb-30" ><i class="fa fa-user" aria-hidden="true" style="font-size: 30px;color: #f58320;"></i>  Lakhs of Genuine Profiles</p>
          		<p class="text-gray pt-10 mt-0 mb-30" ><i class="fa fa-adjust" aria-hidden="true" style="font-size: 30px;color: #f58320;"></i>  Many Verified by Personal Visit</p>
          		<p class="text-gray pt-10 mt-0 mb-30" ><i class="fa fa-check-circle" aria-hidden="true" style="font-size: 30px;color: #f58320;"></i> Secure and Family Friendly</p>
          		<p class="text-gray pt-10 mt-0 mb-30" ><i class="fa fa-database" aria-hidden="true" style="font-size: 30px;color: #f58320;"></i> Strict Privacy Control</p>
          </div>
          
          
      <div class="col-md-8">
            
            <form:form action="registersave" name="regform" class="register-form" method="post" modelAttribute="command">
            
		              <div class="icon-box mb-0 p-0">
		               
		                
		                <h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn">Don't have an Account? Register Now. <img src="<c:url value="/resources/images/favicon/roase.png" />" height="60px"  alt=""/></h4>
		                <%
		                String errormsg=request.getParameter("errormsg");
		                if(errormsg!=null)
		                {
		                	//out.print(succmsg);
		                	%>
		                	<script>
		                	
		                	$(document).ready(function(){
		                		show_alert_email_exist();
		                		function show_alert_email_exist()
		                		{
		                			swal("Registration failed", "Eamil ID already exist", "error");
		                		}
		                	})
		                	
		                	</script>
		                	<% 
		                }
		               
		                %>
		              </div>
		              <hr>
              <div id="step1">
	                <div class="row">
	              		<div class="form-group col-md-12">
		             	
		              			<p class="text-gray">Welcome! Let's start your partner search with this Sign up.</p>
		             	
	             		</div>
	             		<div class="form-group col-md-6">
			             	<p class="mytext-color"> Account Details</p>
			             	
		             		
	             		</div>
	             		<div class="form-group col-md-3">
			             	
			             	<p>  Mandatory <span class="mandtry">*</span></p>
		             	
	             		</div>
	             	</div>
             	
             	
             	
             	
             	
		             <div class="row">
				                <div class="form-group col-md-3">
				                  <label for="form_username_email">Gender <span class="mandtry">*</span></label><br/>
				                  
				                  		Male <form:radiobutton path="gender" value="Male" checked="checked"/>  
				                  		&nbsp;&nbsp;FeMale <form:radiobutton path="gender" value="Female"/>  
										 
										 
										 
										 <!--<form:select path="gender" class="form-control">
										 	<form:option value = "" label = "Select"/>
											<form:option value="Male" label="Male"></form:option>
											<form:option value="Female" label="Female"></form:option>
										</form:select>-->
										
										
										
										<font color='red'><form:errors path='gender' /></font>
				                </div>
				                
				                
				               <input type="hidden" name="username" value="">
		                
		              </div>
		              
		              <div class="row">
		              
		              
		              
				                <div class="form-group col-md-6">
				                  <label for="form_name">Name <span class="mandtry">*</span></label>
				                  
				                  <form:input path="name"  cssClass="form-control"/>
				                  <font color='red'><form:errors path='name' id="name"/></font>
				                  <span style="color:red;font-weight:bold;" id="name_error_msg"></span>
				                </div>
				                <div class="form-group col-md-6">
				                  <label>Email Address <span class="mandtry">*</span></label>
				                 
				                  <form:input path="email"  cssClass="form-control" id="email"/>
				                  <font color='red'><form:errors path='email' /></font>
				                  <span style="color:red;font-weight:bold;" id="email_error_msg"></span>
				                </div>
		              </div>
		              
		              <div class="row">
				                <div class="form-group col-md-6">
				                  <label for="form_choose_password"> Password <span class="mandtry">*</span></label>
				                  <form:input path="password"  type="password" cssClass="form-control" id="password"/>
				                  <font color='red'><form:errors path='password' /></font>
				                  <span style="color:red;font-weight:bold;" id="password_error_msg"></span>
				                </div>
				                <div class="form-group col-md-6">
				                  <label>Re-enter Password <span class="mandtry">*</span></label>
				                  <form:input path="confirmpassword"  type="password" cssClass="form-control" id="confirmpassword"/>
				                   <font color='red'><form:errors path='confirmpassword' /></font>
				                   <span style="color:red;font-weight:bold;" id="confirmpassword_error_msg"></span>
				                </div>
		              </div>
		              
		              <div class="row">
			              <div class="form-group  col-md-6">
				              <div class="form-group">
				                <button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(59, 89, 152) !important;" id="step1butn">Register For Free</button>
				              </div>
			              </div>
			           </div>
              
              
              </div>
              
              
              <div id="step2" style="display:none">
              
			              		<div class="row">
				              		
				             		<div class="form-group col-md-6">
						             	<p class="mytext-color"> Profile Details</p>
						             	
					             	
				             		</div>
				             		
				             	</div>
			              
				              		<div class="row">
								                <div class="form-group col-md-4">
								                  <label for="form_name">Date Of Birth <span class="mandtry">*</span></label>
								                  
								                  	<form:select path="dobday" class="form-control" id="dobday">
														 	<form:option value = "" label = "Select Day"/>
														 	
														 	<% for(int j=1; j<=31; j++) { %>
														 	<form:option value="<%= j %>"></form:option>
														 	<% } %>
														 	
													</form:select>
													<span style="color:red;font-weight:bold;" id="dobday_error_msg" class="error_msg"></span>
													<font color='red'><form:errors path='dobday' /></font>
												</div>
													
												<div class="form-group col-md-4">	
												<label for="form_name"></label>
													<form:select path="dobmonth" class="form-control" id="dobmonth">
														 	<form:option value = "" label = "Select Month"/>
														 	
														 	<form:option value="01" label = "Jan"></form:option>
														 	<form:option value="02" label = "Feb"></form:option>
														 	<form:option value="03" label = "Mar"></form:option>
														 	<form:option value="04" label = "April"></form:option>
														 	<form:option value="05" label = "May"></form:option>
														 	<form:option value="06" label = "Jun"></form:option>
														 	<form:option value="07" label = "Jul"></form:option>
														 	<form:option value="08" label = "Aug"></form:option>
														 	<form:option value="09" label = "Sep"></form:option>
														 	<form:option value="10" label = "Oct"></form:option>
														 	<form:option value="11" label = "Nov"></form:option>
														 	<form:option value="12" label = "Dec"></form:option>
															
													</form:select>
													<span style="color:red;font-weight:bold;" id="dobmonth_error_msg" class="error_msg"></span>
													<font color='red'><form:errors path='dobmonth' /></font>
												</div>	
												
												<div class="form-group col-md-4">	
												<label for="form_name"></label>
													<form:select path="dobyear" class="form-control" id="dobyear">
														 	<form:option value = "" label = "Select Year"/>
														 	
														 	<% for(int j=1960; j<=3000; j++) { %>
														 	<form:option value="<%= j %>"></form:option>
														 	<% } %>
														 	
															
													</form:select>
													<span style="color:red;font-weight:bold;" id="dobyear_error_msg" class="error_msg"></span>
													<font color='red'><form:errors path='dobyear' /></font>
												</div>
											
											
						                </div>
						                
						                
						   <div class="row">
						   			<div class="form-group col-md-6">
						                  <label for="form_name">Phone No <span class="mandtry">*</span></label>
						                  
						                  <form:input path="phone_no"  cssClass="form-control" id="phone_no"/>
						                  <span style="color:red;font-weight:bold;" id="phone_no_error_msg" class="error_msg"></span>
						                  <font color='red'><form:errors path='phone_no' /></font>
				               		 </div>
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Religion<span class="mandtry">*</span></label>
										<form:select path = "religion_id" class="form-control" id="religion">
										<form:option value = "" label = "Select"/>
										<form:options items = "${religionlisthashmap}" />
										</form:select>  
										<span style="color:red;font-weight:bold;" id="religion_error_msg" class="error_msg"></span>
										 <font color='red'><form:errors path='religion_id' /></font> 
					                </div>
					                
			              </div>
			              
			              <div class="row">
			              			<div class="form-group col-md-6">
					                  <label for="form_choose_password">Mother Tongue<span class="mandtry">*</span></label>
										<form:select path = "mother_tongue_id" class="form-control" id="mother_tongue">
										<form:option value = "" label = "Select"/>
										<form:options items = "${mothertonguelisthashmap}" />
										</form:select>   
										<span style="color:red;font-weight:bold;" id="mother_tongue_error_msg" class="error_msg"></span>
										<font color='red'><form:errors path='mother_tongue_id' /></font>
					                </div>
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Caste</label>
										<form:select path = "caste_info_id" class="form-control">
										<form:option value = "0" label = "Select"/>
										<form:options items = "${castelisthashmap}" />
										</form:select>   
										<font color='red'><form:errors path='caste_info_id' /></font>
					                </div>
			              </div>
			              
			              <div class="row">
					                <div class="form-group col-md-4">
					                  <label for="form_choose_password">Marital status<span class="mandtry">*</span></label>
										<form:select path = "marital_status_id" class="form-control" id="marital_status">
										<form:option value = "" label = "Select"/>
										<form:options items = "${maritalstatuslisthashmap}" />
										</form:select>   
										<span style="color:red;font-weight:bold;" id="marital_status_error_msg" class="error_msg"></span>
										<font color='red'><form:errors path='marital_status_id' /></font>
					                </div>
					                <div class="form-group col-md-4">
					                  <label for="form_choose_password">Height<span class="mandtry">*</span></label>
										<form:select path = "height_info_id" class="form-control" id="height_info">
										<form:option value = "" label = "Select"/>
										<form:options items = "${heightlisthashmap}" />
										</form:select>   
										<span style="color:red;font-weight:bold;" id="height_info_error_msg" class="error_msg"></span>
										<font color='red'><form:errors path='height_info_id' /></font>
					                </div>
					                
					                <div class="form-group col-md-4">
						                  <label for="form_name">Weight<span class="mandtry">*</span></label>
						                  
						                  <form:input path="weight_info"  cssClass="form-control" id="weight_info"/>
						                  
						                  <font color='red'><form:errors path='weight_info' /></font>
						                  <span style="color:red;font-weight:bold;" id="weight_info_error_msg" class="error_msg"></span>
				               		 </div>
					                
			              </div>
			              
			              
			              <div class="row">
					                <div class="form-group col-md-4">
					                  <label for="form_choose_password">Country <span class="mandtry">*</span></label>
										<form:select path = "country_id" class="form-control" id="country_id">
										<form:option value = "" label = "Select"/>
										<form:options items = "${countrylisthashmap}" />
										</form:select>   
										<span style="color:red;font-weight:bold;" id="country_error_msg" class="error_msg"></span>
										<font color='red'><form:errors path='country_id' /></font>
					                </div>
					                
					                <div class="form-group col-md-4">
					                  <label for="form_choose_password">State <span class="mandtry">*</span></label>
										<form:select path = "state_id" class="form-control" id="state_id">
										
										</form:select>   
										<span style="color:red;font-weight:bold;" id="state_error_msg" class="error_msg"></span>
					                </div>
					                
					                <div class="form-group col-md-4">
					                  <label for="form_choose_password">City <span class="mandtry">*</span></label>
										<form:select path = "city_id" class="form-control" id="city_id">
										
										</form:select>   
										<span style="color:red;font-weight:bold;" id="city_error_msg" class="error_msg"></span>
					                </div>
					                
			              </div>
			              
			              
			              
			               <div class="row">
				             <div class="form-group  col-md-6">
				                <button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(0, 172, 238) !important;" id="step2bckbutn">Back</button>
			                	
			             	 </div>
		             	 
			             	 <div class="form-group  col-md-6">
				                
			                	<button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(59, 89, 152) !important;" id="step2butn">Continue</button>
			             	 </div>
		             	 </div>
              
              
              
              
           </div>   
              
              
	            <div id="step3" style="display:none">  
	              
				              <div class="row">
					              		
					             		<div class="form-group col-md-6">
							             	<p class="mytext-color"> Career Details</p>
							             	
						             	
					             		</div>
					             		
					           </div>
				              
				              <div class="row">
				              
				              			<div class="form-group col-md-6">
						                  <label for="form_choose_password">Highest Education<span class="mandtry">*</span></label>
											<form:select path = "highest_education_id" class="form-control" id="highest_education">
											<form:option value = "" label = "Select"/>
											<form:options items = "${highesteducationlisthashmap}" />
											</form:select>   
											<span style="color:red;font-weight:bold;" id="highest_education_error_msg" class="error_msg"></span>
											<font color='red'><form:errors path='highest_education_id' /></font>
						                </div>
						                
						                <div class="form-group col-md-6">
						                  <label for="form_choose_password">Annual Income<span class="mandtry">*</span></label>
											<form:select path = "annual_income_id" class="form-control" id="annual_income">
											<form:option value = "" label = "Select"/>
											<form:options items = "${annualincomelisthashmap}" />
											</form:select>   
											<span style="color:red;font-weight:bold;" id="annual_income_error_msg" class="error_msg"></span>
											<font color='red'><form:errors path='annual_income_id' /></font>
						                </div>
						                
						                
						               
				              </div>
				              
				              <div class="row">
				              
				              			<div class="form-group col-md-6">
						                  <label for="form_choose_password">Employed In<span class="mandtry">*</span></label>
											<form:select path = "employed_in_id" class="form-control" id="employed_in">
											<form:option value = "" label = "Select"/>
											<form:options items = "${employedinlisthashmap}" />
											</form:select>   
											<span style="color:red;font-weight:bold;" id="employed_in_error_msg" class="error_msg"></span>
											<font color='red'><form:errors path='employed_in_id' /></font>
						                </div>
						                
						                
						                <div class="form-group col-md-6">
						                  <label for="form_choose_password">Occupation<span class="mandtry">*</span></label>
											<form:select path = "occupation_info_id" class="form-control" id="occupation_info">
											<form:option value = "" label = "Select"/>
											<form:options items = "${occupationlisthashmap}" />
											</form:select>   
											<span style="color:red;font-weight:bold;" id="occupation_info_error_msg" class="error_msg"></span>
											<font color='red'><form:errors path='occupation_info_id' /></font>
						                </div>
						                
						               
				              </div>
							                
							                
							     <div class="row">
				              
				              			<div class="form-group col-md-12">
						                  <label for="form_choose_password"> Express Yourself  <span class="mandtry">*</span></label>
						                  <form:textarea path="express_yourself"  cssClass="form-control" rows="6"  id="express_yourself"/>
						                  <span style="color:red;font-weight:bold;" id="express_yourself_error_msg" class="error_msg"></span>
						                  <font color='red'><form:errors path='express_yourself' /></font>
						                </div>
						                
				              	 </div>          
							                
							                
						  
			   					 
			   			<div class="row">
				             <div class="form-group  col-md-6">
				                <button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(0, 172, 238) !important;" id="step3bckbutn">Back</button>
			                	
			             	 </div>
		             	 
			             	 <div class="form-group  col-md-6">
				                
			                	<button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(59, 89, 152) !important;" id="step3butn">Submit</button>
			             	 </div>
		             	 </div>
			   		
			   		
			   	 </div>				 
		   					 
		   					 
		   					 
		             </form:form> 
		    </div>
		              
		              
		              
		    
              
            
        </div>
          
         
          
       </div>
     </section>
   </div>
  
    
  </div>
  <!-- end main-content -->
  
  <!-- Footer -->
  <%@ include file="includes/footer.jsp" %>
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>

<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->

<%@ include file="includes/footerjs.jsp" %>
<script>
/*registration form validation*/
$(document).ready(function(){
	
	$("#step1butn").click(function(){
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
		var namePattern=/^[a-zA-Z ]*$/;
		var name=$("#name").val();
		var email=$("#email").val();
		var password=$("#password").val();
		var confirmpassword=$("#confirmpassword").val();
		if(name==""){
			$("#name_error_msg").html("Please Enter Name");
			$("#name").focus();
		}
		else if(!namePattern.test(name))
		{
			$("#name_error_msg").html("Name Contains Only Letters");
			$("#name").focus();
		}
		else if(email==""){
			$("#name_error_msg").html("");
			$("#email_error_msg").html("Please Enter Email");
			$("#email").focus();
		}
		else if(!emailPattern.test(email))
		{
			$("#name_error_msg").html("");
			$("#email_error_msg").html("Please Enter Valid Email");
			$("#email").focus();
		}
		else if(password==""){
			$("#email_error_msg").html("");
			$("#name_error_msg").html("");
			$("#password_error_msg").html("Please Enter Password");
			$("#password").focus();
		}
		else if(confirmpassword==""){
			$("#email_error_msg").html("");
			$("#name_error_msg").html("");
			$("#password_error_msg").html("");
			$("#confirmpassword_error_msg").html("Please Enter Confirm Password");
			$("#confirmpassword").focus();
		}
		else if(password!=confirmpassword){
			$("#email_error_msg").html("");
			$("#name_error_msg").html("");
			$("#password_error_msg").html("");
			$("#confirmpassword_error_msg").html("Passwords Are Not Same");
			$("#confirmpassword").focus();
		}
		else{
			$("#email_error_msg").html("");
			$("#name_error_msg").html("");
			$("#password_error_msg").html("");
			$("#confirmpassword_error_msg").html("");
			$("#step1").hide();
			$("#step2").show();
		}
		
	})
	$("#step2butn").click(function(){
		var dobday=$("#dobday").val();
		var dobmonth=$("#dobmonth").val();
		var dobyear=$("#dobyear").val();
		var phone_no=$("#phone_no").val();
		var religion=$("#religion").val();
		var mother_tongue=$("#mother_tongue").val();
		var marital_status=$("#marital_status").val();
		var height_info=$("#height_info").val();
		var weight_info=$("#weight_info").val();
		var country_id=$("#country_id").val();
		var state_id=$("#state_id").val();
		var city_id=$("#city_id").val();
		var phonepattern = /^[0-9]{10}$/;
		if(dobday==""){
			$(".error_msg").html("");
			$("#dobday_error_msg").html("Please Enter Day");
			$("#dobday").focus();
		}
		else if(dobmonth==""){
			$(".error_msg").html("");
			$("#dobmonth_error_msg").html("Please Enter Month");
			$("#dobmonth").focus();
		}
		else if(dobyear==""){
			$(".error_msg").html("");
			$("#dobyear_error_msg").html("Please Enter Year");
			$("#dobyear").focus();
		}
		else if(phone_no==""){
			$(".error_msg").html("");
			$("#phone_no_error_msg").html("Please Enter Phone No");
			$("#phone_no").focus();
		}
		else if(!phonepattern.test(phone_no))
		{
			$("#phone_no_error_msg").html("Please Enter 10 Digit Phone No");
			$("#phone_no").focus();
		}
		else if(religion==""){
			$(".error_msg").html("");
			$("#religion_error_msg").html("Please Enter Religion");
			$("#religion").focus();
		}
		else if(mother_tongue==""){
			$(".error_msg").html("");
			$("#mother_tongue_error_msg").html("Please Enter Mother Tongue");
			$("#mother_tongue").focus();
		}
		else if(marital_status==""){
			$(".error_msg").html("");
			$("#marital_status_error_msg").html("Please Enter Marital Status");
			$("#marital_status").focus();
		}
		else if(height_info==""){
			$(".error_msg").html("");
			$("#height_info_error_msg").html("Please Enter Height Info");
			$("#height_info").focus();
		}
		else if(weight_info==""){
			$(".error_msg").html("");
			$("#weight_info_error_msg").html("Please Enter Weight Info");
			$("#weight_info").focus();
		}
		else if(country_id==""){
			$(".error_msg").html("");
			$("#country_error_msg").html("Please Enter Country");
			$("#country_id").focus();
		}
		else if(state_id==0){
			$(".error_msg").html("");
			$("#state_error_msg").html("Please Enter State");
			$("#state_id").focus();
		}
		else if(city_id==0){
			$(".error_msg").html("");
			$("#city_error_msg").html("Please Enter City");
			$("#city_id").focus();
		}
		else{
			$(".error_msg").html("");
			$("#step2").hide();
			$("#step3").show();
		}
		
	})
	$("#step3butn").click(function(){
		var highest_education=$("#highest_education").val();
		var annual_income=$("#annual_income").val();
		var highest_education=$("#highest_education").val();
		var employed_in=$("#employed_in").val();
		var occupation_info=$("#occupation_info").val();
		var express_yourself=$("#express_yourself").val();
		
		if(highest_education==""){
			$(".error_msg").html("");
			$("#highest_education_error_msg").html("Please Enter Highest Education");
			$("#highest_education").focus();
		}
		else if(annual_income==""){
			$(".error_msg").html("");
			$("#annual_income_error_msg").html("Please Enter Annual Income");
			$("#annual_income").focus();
		}
		else if(employed_in==""){
			$(".error_msg").html("");
			$("#employed_in_error_msg").html("Please Enter Employed In");
			$("#employed_in").focus();
		}
		else if(occupation_info==""){
			$(".error_msg").html("");
			$("#occupation_info_error_msg").html("Please Enter Occupation Info");
			$("#occupation_info").focus();
		}
		else if(express_yourself==""){
			$(".error_msg").html("");
			$("#express_yourself_error_msg").html("Please Enter Express Yourself");
			$("#express_yourself").focus();
		}
		else{
			$(".error_msg").html("");
			document.regform.submit();
		}
	})
	$("#step2bckbutn").click(function(){
		$("#step2").hide();
		$("#step1").show();
	})
	
	$("#step3bckbutn").click(function(){
		$("#step3").hide();
		$("#step2").show();
	})
	/*registration form validation*/
	$("#country_id").change(function(){
		
		cid=$("#country_id").val();
		
		$.ajax({
			
			  url: "get_state_list_by_country",
			  cache: false,
			  type: "POST",
			  data: "cid="+cid,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				  
				  var str='<option value="0">Select</option>';
				  $.each(response, function (key, val) {
					   str+='<option value="'+val.id+'">'+val.name+'</option>';
				  });
				  
				  $("#state_id").html(str);
			  }
		});	  
	
	
	})
	
	
	$(document).on("change","#state_id",function(){
		
		sid=$("#state_id").val();
		
		$.ajax({
			
			  url: "get_city_list_by_state",
			  cache: false,
			  type: "POST",
			  data: "sid="+sid,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				  
				  var str='<option value="0">Select</option>';
				  $.each(response, function (key, val) {
					   str+='<option value="'+val.id+'">'+val.name+'</option>';
				  });
				  
				  $("#city_id").html(str);
			  }
		});	  
	
	
	})
	
	
	
})

</script>
</body>
</html>