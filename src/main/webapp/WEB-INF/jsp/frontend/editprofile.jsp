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
  
  <%@ page import="com.beans.Profile" %>
  
  <!-- Header -->
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content">
    <!-- Section: inner-header -->
    

    <section style="margin-top:100px">
     
      <div class="container">
        <div class="row">
         			 <%
		                String notifymsg=request.getParameter("notifymsg");
         			    String validationmsg="";
         			    try
         			    {
         			    	 validationmsg=request.getAttribute("validationmsg").toString();
         			    }catch(Exception e){
         			    	validationmsg=null;
         			    }
         			    
		                if(notifymsg!=null)
		                {
		                	//out.print(succmsg);
		                	%>
		                	<script>
		                	
		                	$(document).ready(function(){
		                		show_alert_profile_update_pending();
		                		function show_alert_profile_update_pending()
		                		{
		                			swal("Please update your profile", "Your profile is 80% complete! Please complete it 100% for better partner search.", "success");
		                		}
		                	})
		                	
		                	</script>
		                	<% 
		                }
		               
		                %>
          <!-- mytext-color -->
          
          <% Profile userdt=(Profile)request.getAttribute("user"); %>
          
      <div class="col-md-7" style="border: 1px solid #d1d1d1;padding: 20px; margin:10px">
            
            <form:form action="updateprofiledetails" name="reg-form" class="register-form" method="post" modelAttribute="command">
            
		              <div class="icon-box mb-0 p-0">
		                <a href="#" class="icon icon-bordered icon-rounded icon-sm pull-left mb-0 mr-10">
		                  <i class="fa fa-pencil-square-o rgsrcicn" aria-hidden="true"></i>
		                </a>
		                <h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn">Edit Profile.</h4>
		                <%
		                
		                if(validationmsg!=null)
		                {
		                	%>
		                	<div>
		                		<p style="color:red; font-weight:bold;">* Update Failed. Please Fill Up Mandatory Fields. </p>
		                		
		                	</div>
		                	
		                	<% 
		                }
		                if(notifymsg!=null)
		                {
		                	%>
		                	<div>
		                		<label for="file" style="color:red">Please Update Your Profile Now. Profile Completed 80% : </label>
		                		<progress id="file" value="80" max="100" title="80% Complete"> 80% </progress>
		                	</div>
		                	
		                <% } %>	
		                	
		                
		              </div>
		              <hr>
              <div id="step1">
	                <div class="row">
	              		<div class="form-group col-md-12">
		             	
		              			<p class="text-gray">You can edit your profile here.</p>
		             	
	             		</div>
	             		<div class="form-group col-md-12" style="border-bottom: 1px dotted green;">
			             	<p class="" style="color: #D30665;font-weight: bold;"> ** Account Details</p>
			             	
		             		
	             		</div>
	             		<div class="form-group col-md-3">
			             	
			             	<p>  Mandatory <span class="mandtry">*</span></p>
		             	
	             		</div>
	             	</div>
             	
             	
             	
             	
             	
		             <div class="row">
				                <div class="form-group col-md-6">
				                  <label for="form_username_email">Gender <span class="mandtry">*</span></label><br/>
				                  
										 <form:select path="gender" class="form-control">
										 	<form:option value = "" label = "Select"/>
											<form:option value="Male" label="Male"></form:option>
											<form:option value="Female" label="Female"></form:option>
										</form:select>
										<font color='red'><form:errors path='gender' /></font>
				                </div>
				                
				                <div class="form-group col-md-6">
				                  <label for="form_name">Name <span class="mandtry">*</span></label>
				                  
				                  <form:input path="name"  cssClass="form-control"/>
				                  <font color='red'><form:errors path='name' /></font>
				                </div>
				               
		                
		              </div>
		              
		              <div class="row">
				                
				                <div class="form-group col-md-12">
				                  <label>Email Address <span class="mandtry">*</span></label>
				                  
				                 <p> <%= userdt.getEmail() %> </p> 
				                </div>
		              </div>
		              
		              
              
              </div>
              
              
              <div id="step2" >
              
			              		<div class="row">
				              		
				             		<div class="form-group col-md-12" style="border-bottom: 1px dotted green;">
						             	<p class="" style="color: #1601CF;font-weight: bold;"> ** Basic Details</p>
						             	
					             	
				             		</div>
				             		
				             	</div>
			              
				              		<div class="row">
				              		
				              		<% String dateofbirth= userdt.getDob().toString();
				              		   String[] dateofbirthar=dateofbirth.split("-");
				              		   int yr=Integer.parseInt(dateofbirthar[0].toString());
				              	  	   int dy=Integer.parseInt(dateofbirthar[2].toString());
				              	  	   int mont=Integer.parseInt(dateofbirthar[1].toString());
				              	  	   int countstatus=Integer.parseInt(request.getAttribute("countstatus").toString());
			             			   int statelistedit_status=Integer.parseInt(request.getAttribute("statelistedit_status").toString());
			             			   int citylistedit_status=Integer.parseInt(request.getAttribute("citylistedit_status").toString());
				              		%>
				              		
				              		
								                <div class="form-group col-md-4">
								                  <label for="form_name">Date Of Birth <span class="mandtry">*</span></label>
								                  
								                  	<select name="dobday" class="form-control">
														 	<option value = "" label = "Select Day"></option>
														 	
														 	<% for(int j=1; j<=31; j++) { %>
														 	<option value="<%= j %>" <% if(j==dy) { %> selected="selected" <% } %> ><%= j %></option>
														 	<% } %>
														 	
															
															
													</select>
													<font color='red'><form:errors path='dobday' /></font>
												</div>
													
												<div class="form-group col-md-4">	
												<label for="form_name"></label>
													<select name="dobmonth" class="form-control">
														 	<option value = "" label = "Select Month"></option>
														 	
														 	
														 	<option value="01" label = "Jan" <% if(mont==1) { %> selected="selected" <% } %> >Jan</option>
														 	<option value="02" label = "Feb" <% if(mont==2) { %> selected="selected" <% } %> > Feb </option>
														 	<option value="03" label = "Mar" <% if(mont==3) { %> selected="selected" <% } %> >  Mar </option>
														 	<option value="04" label = "April" <% if(mont==4) { %> selected="selected" <% } %> > April  </option>
														 	<option value="05" label = "May" <% if(mont==5) { %> selected="selected" <% } %> > May </option>
														 	<option value="06" label = "Jun" <% if(mont==6) { %> selected="selected" <% } %> > Jun </option>
														 	<option value="07" label = "Jul" <% if(mont==7) { %> selected="selected" <% } %> > Jul </option>
														 	<option value="08" label = "Aug" <% if(mont==8) { %> selected="selected" <% } %> >Aug</option>
														 	<option value="09" label = "Sep" <% if(mont==9) { %> selected="selected" <% } %> > Sep</option>
														 	<option value="10" label = "Oct" <% if(mont==10) { %> selected="selected" <% } %> > Oct  </option>
														 	<option value="11" label = "Nov" <% if(mont==11) { %> selected="selected" <% } %> > Nov </option>
														 	<option value="12" label = "Dec" <% if(mont==12) { %> selected="selected" <% } %> > Dec  </option>
															
															
													</select>
													<font color='red'><form:errors path='dobmonth' /></font>
												</div>	
												
												<div class="form-group col-md-4">	
												<label for="form_name"></label>
													<select name="dobyear" class="form-control">
														 	<option value = "" label = "Select Year"></option>
														 	
														 	<% for(int j=1960; j<=3000; j++) { %>
														 	<option value="<%= j %>" <% if(j==yr) { %> selected="selected" <% } %> ><%=j %></option>
														 	<% } %>
														 	
															
															
													</select>
													<font color='red'><form:errors path='dobyear' /></font>
												</div>
											
											
						                </div>
						                
						   <div class="row">
			               		<div class="form-group col-md-12">
			               		 
			               				 <form:radiobutton path="is_mangalik" value="1" /> <span class="prfismangalik">Mangalik</span> 
										 <form:radiobutton path="is_mangalik" value="2" /> 	<span class="prfismangalik">Non-Mangalik</span> 
 										 <form:radiobutton path="is_mangalik" value="3 " /> <span class="prfismangalik">Part-Mangalik</span>  
 										
 										 <font color='red'><form:errors path='is_mangalik' /></font>
			               		</div>
			               </div>             
						   
						   <div class="row">
						   			<div class="form-group col-md-6">
						                  <label for="form_name">Phone No <span class="mandtry">*</span></label>
						                  
						                  <form:input path="phone_no"  cssClass="form-control"/>
						                  <font color='red'><form:errors path='phone_no' /></font>
				               		 </div>
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Religion<span class="mandtry">*</span></label>
										<form:select path = "religion_id" class="form-control">
										<form:option value = "" label = "Select"/>
										<form:options items = "${religionlisthashmap}" />
										</form:select>  
										 <font color='red'><form:errors path='religion_id' /></font> 
					                </div>
					                
			              </div>
			              
			              <div class="row">
			              			<div class="form-group col-md-6">
					                  <label for="form_choose_password">Mother Tongue<span class="mandtry">*</span></label>
										<form:select path = "mother_tongue_id" class="form-control">
										<form:option value = "" label = "Select"/>
										<form:options items = "${mothertonguelisthashmap}" />
										</form:select>   
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
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Marital status<span class="mandtry">*</span></label>
										<form:select path = "marital_status_id" class="form-control">
										<form:option value = "" label = "Select"/>
										<form:options items = "${maritalstatuslisthashmap}" />
										</form:select>   
										<font color='red'><form:errors path='marital_status_id' /></font>
					                </div>
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Height<span class="mandtry">*</span></label>
										<form:select path = "height_info_id" class="form-control">
										<form:option value = "" label = "Select"/>
										<form:options items = "${heightlisthashmap}" />
										</form:select>   
										<font color='red'><form:errors path='height_info_id' /></font>
					                </div>
					                
			              </div>
			              
			              <div class="row">
			              
			              			<div class="form-group col-md-6">
						                  <label for="form_name">Weight<span class="mandtry">*</span></label>
						                  
						                  <form:input path="weight_info"  cssClass="form-control"/>
						                  
						                  <font color='red'><form:errors path='weight_info' /></font>
				               		 </div>
				               		 
			              			<div class="form-group col-md-6">
					                  <label for="form_choose_password">Blood Group</label>
										  
										<form:select path = "blood_group_id" class="form-control">
										<form:option value = "0" label = "Select"/>
										<form:options items = "${bloodgrouplist}" />
										</form:select> 
										
					                </div>
					                
					              
			              </div>
			              
			              <div class="row">
			              			<div class="form-group col-md-6">
					                  <label for="form_choose_password">Gon</label>
										  
										<form:select path = "gon_info_id" class="form-control">
										<form:option value = "0" label = "Select"/>
										<form:options items = "${gonlist}" />
										</form:select> 
										
					                </div>
					                
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Rashi</label>
										  
										<form:select path = "rashi_info_id" class="form-control">
										<form:option value = "0" label = "Select"/>
										<form:options items = "${rashilist}" />
										</form:select> 
										
					                </div>
					                
			              </div>
			              <div class="row">
				              		
				             		<div class="form-group col-md-12" style="border-bottom: 1px dotted green;">
						             	<p class="" style="color: red ;font-weight: bold;"> ** Lifestyle Habits</p>
						             	
					             	
				             		</div>
				             		
				          </div>
			              <div class="row">
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Body Type : </label>
			               				 <form:radiobutton path="body_type" value="1" /> Slim
										 <form:radiobutton path="body_type" value="2" /> Athletic	
 										 <form:radiobutton path="body_type" value="3 " /> Average 
 										 <form:radiobutton path="body_type" value="4" /> Heavy
 										 <font color='red'><form:errors path='body_type' /></font>
			               		</div>
			               </div>
			               
			               <div class="row">
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Complexion : </label>
			               				 <form:radiobutton path="complexion" value="1" /> Very Fair
										 <form:radiobutton path="complexion" value="2" /> Fair 	
 										 <form:radiobutton path="complexion" value="3 " /> Wheatish  
 										 <form:radiobutton path="complexion" value="4" /> Wheatish Brown
 										 <form:radiobutton path="complexion" value="5" /> Dark
 										 <font color='red'><form:errors path='complexion' /></font>
			               		</div>
			               </div>
			              
			               <div class="row">
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Physical Status : </label>
			               				 <form:radiobutton path="physical_status" value="1" /> Normal

 										 <form:radiobutton path="physical_status" value="2" /> Physically challenged
			               				<font color='red'><form:errors path='physical_status' /></font>	
			               		</div>
			               </div>
			               	<div class="row">	
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Eating Habits : </label>
			               				 <form:radiobutton path="eating_habits" value="1" /> Vegetarian

 										 <form:radiobutton path="eating_habits" value="2" /> Non Vegetarian
 										 
 										 <form:radiobutton path="eating_habits" value="3" /> Eggetarian
 										 <font color='red'><form:errors path='eating_habits' /></font>
			               		</div>
			               	</div>
			               	<div class="row">	
			               		
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Drinking Habits : </label>
			               				 <form:radiobutton path="drinking_habits" value="1" /> Never drinks

 										 <form:radiobutton path="drinking_habits" value="2" /> Drinks socially
 										 
 										 <form:radiobutton path="drinking_habits" value="3" /> Drinks regularly
 										 
 										 <font color='red'><form:errors path='drinking_habits' /></font>
			               		</div>
			               </div>
			               
			               <div class="row">	
			               		
			               		<div class="form-group col-md-12">
			               		 <label for="form_choose_password">Smoking Habits : </label>
			               				 <form:radiobutton path="smoking_habits" value="1" /> Never smokes

 										<form:radiobutton path="smoking_habits" value="2" /> Smokes occasionally
 										 
 										<form:radiobutton path="smoking_habits" value="3" /> Smokes regularly
 										
 										<font color='red'><form:errors path='smoking_habits' /></font>
			               		</div>
			               </div>
			              
			              <div class="row">
				              		
				             		<div class="form-group col-md-12" style="border-bottom: 1px dotted green;">
						             	<p class="" style="color: #f58320 ;font-weight: bold;"> ** Location Details</p>
						             	
					             	
				             		</div>
				             		
				          </div>
			              
			              <c:set var="userdt" value="<%=userdt%>" />
			              <div class="row">
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Country </label>
										<select name = "country_id" class="form-control" id="country_id">
											<option value="" selected="selected">Select</option>
											<% if(countstatus==0){ %>
											<c:forEach items="${countrylisthashmap}" var="country">
												<option value="${country.key}"  >${country.value}</option>
											</c:forEach>
											<% } else { %>
											<c:forEach items="${countrylisthashmap}" var="country">
												<option value="${country.key}"  <c:if test="${country.key == userdt.getCountry_id()}"> selected="selected" </c:if>>${country.value}</option>
											</c:forEach>
											
											<%} %>
										</select>   
									
					                </div>
					                
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">State </label>
										<select name = "state_id" class="form-control" id="state_id">
										<% if(countstatus==0){ %>
										<% } else { %>
										
										<% if(statelistedit_status==1){ %>
											<option value="0">Select</option>
											<c:forEach items="${statelistedit}" var="statelistedit">
												<option value="${statelistedit.getId()}" <c:if test="${userdt.getState_id() == statelistedit.getId()}"> selected="selected" </c:if>>${statelistedit.getName()}</option>
											</c:forEach>
											<%} %>
											
										<%} %>
										</select>   
										
					                </div>
					                
					                
			              </div>
			              
              			<div class="row">
					                
					                
					                <div class="form-group col-md-12">
					                  <label for="form_choose_password">City </label>
										<form:select path = "city_id" class="form-control" id="city_id">
										<% if(countstatus==0){ %>
											
										<% } else { %>
										
										
											<c:forEach items="${citylistedit}" var="citylistedit1">
													
												<option value="${citylistedit1.getId()}"  <c:if test="${userdt.getCity_id() == citylistedit1.getId()}"> selected="selected" </c:if> > ${citylistedit1.getName()}</option>
											
											</c:forEach>
										<% } %>
										</form:select>   
										
					                </div>
					                
			              </div>
              
           </div>   
              
              
	            <div id="step3" >  
	              
				              <div class="row">
					              		
					             		<div class="form-group col-md-12" style="border-bottom: 1px dotted green;">
							             	<p class="" style="color: #01840C;font-weight: bold;"> ** Career Details</p>
							             	
						             	
					             		</div>
					             		
					           </div>
				              
				              <div class="row">
				              
				              			<div class="form-group col-md-6">
						                  <label for="form_choose_password">Highest Education<span class="mandtry">*</span></label>
											<form:select path = "highest_education_id" class="form-control">
											<form:option value = "" label = "Select"/>
											<form:options items = "${highesteducationlisthashmap}" />
											</form:select>   
											<font color='red'><form:errors path='highest_education_id' /></font>
						                </div>
						                
						                <div class="form-group col-md-6">
						                  <label for="form_choose_password">Annual Income<span class="mandtry">*</span></label>
											<form:select path = "annual_income_id" class="form-control">
											<form:option value = "" label = "Select"/>
											<form:options items = "${annualincomelisthashmap}" />
											</form:select>   
											<font color='red'><form:errors path='annual_income_id' /></font>
						                </div>
						                
						                
						               
				              </div>
				              
				              <div class="row">
				              
				              			<div class="form-group col-md-6">
						                  <label for="form_choose_password">Employed In<span class="mandtry">*</span></label>
											<form:select path = "employed_in_id" class="form-control">
											<form:option value = "" label = "Select"/>
											<form:options items = "${employedinlisthashmap}" />
											</form:select>   
											<font color='red'><form:errors path='employed_in_id' /></font>
						                </div>
						                
						                
						                <div class="form-group col-md-6">
						                  <label for="form_choose_password">Occupation<span class="mandtry">*</span></label>
											<form:select path = "occupation_info_id" class="form-control">
											<form:option value = "" label = "Select"/>
											<form:options items = "${occupationlisthashmap}" />
											</form:select>   
											<font color='red'><form:errors path='occupation_info_id' /></font>
						                </div>
						                
						               
				              </div>
							                
							                
							     <div class="row">
				              
				              			<div class="form-group col-md-12">
						                  <label for="form_choose_password"> Express Yourself  <span class="mandtry">*</span></label>
						                  <form:textarea path="express_yourself"  cssClass="form-control" rows="6"  />
						                  <font color='red'><form:errors path='express_yourself' /></font>
						                </div>
						                
				              	 </div>          
							                
							                
						  
			   					 
			   			<div class="row">
				             
		             	 
			             	 <div class="form-group  col-md-6">
				                
			                	<button class="btn btn-dark btn-lg btn-block mt-15" type="submit" style="background: rgb(245, 131, 32) !important;width:50%;" id="step3butn">Update</button>
			             	 </div>
		             	 </div>
			   		
			   		
			   	 </div>				 
		   					 
		   					 
		   					 
		             </form:form> 
		    </div>
		              
		            
		              
		     	<div class="col-md-4" style="border: 1px solid #d1d1d1;padding: 20px; margin:10px;">
				        <form action="profiledetails" method="get" name="srbyidform">
				        		<div class="form-group col-md-12">
						                  <label for="form_choose_username"> Search By ID </label>
						                  <input name="usermatrimonyid"  Class="form-control" placeholder="Enter Matrimony ID" id="usermatrimonyid"/>
						                  <span id="usermatrimonyid_err" style="color:red"></span>
						                 <button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(245, 131, 32) !important;width:40%;" id="step3butn" onclick="searchbymatid();">Search</button>
						        </div>
		          		</form>
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

$(document).ready(function(){
	
	$("#step1butn").click(function(){
		$("#step1").hide();
		$("#step2").show();
	})
	$("#step2butn").click(function(){
		$("#step2").hide();
		$("#step3").show();
	})
	
	$("#step2bckbutn").click(function(){
		$("#step2").hide();
		$("#step1").show();
	})
	
	$("#step3bckbutn").click(function(){
		$("#step3").hide();
		$("#step2").show();
	})

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