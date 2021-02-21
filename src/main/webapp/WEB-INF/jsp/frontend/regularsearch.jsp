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
  <%@ page import="com.beans.Regular_Search" %>
 <%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
  
  
  <script type="text/javascript" src="<c:url value="/resources/Multiselect_dropdown/mock.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/resources/Multiselect_dropdown/jquery.dropdown.css" />">
  <script src="<c:url value="/resources/Multiselect_dropdown/jquery.dropdown.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/resources/Choosen/chosen.min.css" />">
  <script src="<c:url value="/resources/Choosen/chosen.jquery.min.js"/>"></script>
  
<style>
.chosen-choices{
padding:6px !important;
}
</style>
  <!-- Start main-content -->
  <div class="main-content mgtop1">
    <!-- Section: inner-header -->
   
	
    <section>
     
      <div class="container">
      
  	 
        <div class="row">
          
          
      <div class="col-md-7" style="border: 1px solid #d1d1d1;padding: 20px;">
            
            <form action="regularsearchsave" name="reg-form" class="register-form" method="post" >
            
		              <div class="icon-box mb-0 p-0">
		                <a href="javascript:void(0)" class="icon icon-bordered icon-rounded icon-sm pull-left mb-0 mr-10">
		                  <i class="fa fa-search rgsrcicn" aria-hidden="true"></i>
		                </a>
		                <h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn">Regular Search</h4>
		                
		              </div>
		              <hr>
              <div id="step1">
	                <div class="row">
	              		<div class="form-group col-md-12">
		             	
		              			<p class="">Welcome! Let's start your partner search.</p>
		              			
		             			<%
		             			
		             			Regular_Search rsobj=null;
		             			int countstatus=Integer.parseInt(request.getAttribute("countstatus").toString());
		             			int statelistedit_status=Integer.parseInt(request.getAttribute("statelistedit_status").toString());
		             			int citylistedit_status=Integer.parseInt(request.getAttribute("citylistedit_status").toString());
		             			if(countstatus==1)
		             			{
		             				 rsobj=(Regular_Search)request.getAttribute("rsobj");
		             			}
		             			
		             			%>
	             		</div>
	             		
	             		
	             	</div>
             	
              </div>
              
              
              <div id="step2" >
              
			              		<div class="row">
				              		
				             		<div class="form-group col-md-12"  style="border-bottom: 1px dashed #ccc;">
						             	
					             		<p style="color:#D30665;font-weight: bold;">** Profile Details</p>
				             		</div>
				             		
				             	</div>
			              
				              		<div class="row">
								                <div class="form-group col-md-6">
								                  <label for="form_name">Age From </label>
								                  
								                  	<select name="age_form" class="form-control">
														 
														 <% if(countstatus==0){ %>
														 
														 <% for(int j=18; j<=70; j++) { %>
														 	<option value="<%= j %>"><%= j %></option>
														 	<% } %>
														 	
														 <% } else { %>
														 	
														 	<% for(int j=18; j<=70; j++) { %>
														 	<option value="<%= j %>" <% if(rsobj.getAge_form()== j){ %> selected="selected" <% } %> ><%= j %></option>
														 	<% } %>
														 	
														 	<%} %>
														 	
															
															
													</select>
													
												</div>
													
												
												
												<div class="form-group col-md-6">
								                  <label for="form_name">Age To </label>
								                  
								                  	<select name="age_to" class="form-control">
														 	
														 	<% if(countstatus==0){ %>
														 	
														 	<% for(int j=18; j<=70; j++) { %>
														 	<option value="<%= j %>" <%if(j==60){ %>selected="selected"<% }%> ><%= j %></option>
														 	
														 	<% } %>
														 	 <% } else { %>
														 	 
															<% for(int j=18; j<=70; j++) { %>
														 	<option value="<%= j %>" <% if(rsobj.getAge_to()== j){ %> selected="selected" <% } %> ><%= j %></option>
														 	
														 	<% } %>
															
															<%} %>
													</select>
												
												</div>
											
											
						                </div>
						                
						 <div class="row">
						   			
					                <div class="form-group col-md-12">
					                  <label for="form_choose_password">Religion</label>
										<select name = "religion_id" class="form-control">
										
											<% if(countstatus==0){ %>
											<c:forEach items="${religionlisthashmap}" var="religion">
												<option value="${religion.key}">${religion.value}</option>
											</c:forEach>
											 <% } else { %>
											 <c:forEach items="${religionlisthashmap}" var="religion">
												<option value="${religion.key}"  <c:if test="${religion.key == rsobj.getReligion_id()}" > selected="selected" </c:if>  >${religion.value}</option>
											</c:forEach>
											 <%} %>
										</select>  
										
					                </div>
					                
					            
			              </div>
			              <div class="row">
			              		<div class="form-group col-md-12">
			              		
					                  <label for="form_choose_password">Mother Tongue</label>
					                  
			              			
					                  <div class="dropdown-mul-2">
										<select name = "mother_tongue_id" class="form-control"  id="mother_tongue_id" multiple placeholder="Please Select">
										
											<option value="0">Any</option>
											<% if(countstatus==0){ %>
										    <c:forEach items="${mothertonguelisthashmap}" var="mothertongue">
												<option value="${mothertongue.key}"  >${mothertongue.value}</option>
											</c:forEach>
											 <% } else { %>
											 
											<%
						              			//convert string into array 
						              			String mothertongu_ar[]=rsobj.getMother_tongue_id().split(",");
					              			
					              			%>
					              			<c:set var="mothertongu_ar" value="<%=mothertongu_ar%>" />
					              			
												<c:forEach items="${mothertonguelisthashmap}" var="mothertongue">
														<c:set var="caste_flag" value="0" />
														<c:forEach items="${mothertongu_ar}" var="mothertongu_ar_val">
															<c:if test = "${mothertongu_ar_val == mothertongue.key.toString()}">
																<c:set var="caste_flag" value="1" />
															</c:if>
														</c:forEach>
													<c:if test = "${caste_flag==1}">
													<option value="${mothertongue.key}"  selected="selected"> ${mothertongue.value}</option>
													</c:if>
													<c:if test = "${caste_flag==0}">
													<option value="${mothertongue.key}" > ${mothertongue.value}</option>
													</c:if>
												
												</c:forEach>
											
											  <%} %>
										</select>   
										
										</div>
										
					                </div>
					      </div>
						                
						        
						                
						   <div class="row">
			              			
			              			
					                <div class="form-group col-md-12" >
					                  <label for="form_choose_password">Caste</label>
					                  
					                  <div class="dropdown-mul-2">
											<select name = "caste_info_id" class="form-control" multiple placeholder="Please Select">
											
												<option value="0">Any</option>
												
												<% if(countstatus==0){ %>
												<c:forEach items="${castelisthashmap}" var="caste">
													<option value="${caste.key}">${caste.value}</option>
												</c:forEach>
												 <% } else { %>
													 <%
							              				//convert string into array 
							              				String caste_ar[]=rsobj.getCaste_info_id().split(",");
							              			
							              			 %>
							              			<c:set var="caste_ar" value="<%=caste_ar%>" />
												 
													<c:forEach items="${castelisthashmap}" var="caste1">
															<c:set var="caste_flag" value="0" />
															<c:forEach items="${caste_ar}" var="caste_ar_val">
																<c:if test = "${caste_ar_val == caste1.key.toString()}">
																	<c:set var="caste_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${caste_flag==1}">
														<option value="${caste1.key}"  selected="selected"> ${caste1.value}</option>
														</c:if>
														<c:if test = "${caste_flag==0}">
														<option value="${caste1.key}" > ${caste1.value}</option>
														</c:if>
													
													</c:forEach>
												  <%} %>
											</select>   
										</div>
					                </div>
					                
			              </div>
			              
			              
			              
			              <div class="row">
			              			
			              			<div class="form-group col-md-12">
					                  <label for="form_choose_password">Marital status</label>
					                  
					                  <div class="dropdown-mul-2">
					                  
					                  
										<select name = "marital_status_id" class="form-control" multiple placeholder="Please Select">
											<option value="0" >Any</option>
											
											<% if(countstatus==0){ %>
											<c:forEach items="${maritalstatuslisthashmap}" var="maritalstatus">
												<option value="${maritalstatus.key}" >${maritalstatus.value}</option>
											</c:forEach>
											<% } else { %>
											
													<%
							              				//convert string into array 
							              				String marital_ar[]=rsobj.getMarital_status_id().split(",");
							              			
							              			%>
							              			<c:set var="marital_ar" value="<%=marital_ar%>" />
											
													<c:forEach items="${maritalstatuslisthashmap}" var="marital1">
															<c:set var="marital_flag" value="0" />
															<c:forEach items="${marital_ar}" var="marital_ar_val">
																<c:if test = "${marital_ar_val == marital1.key.toString()}">
																	<c:set var="marital_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${marital_flag==1}">
														<option value="${marital1.key}"  selected="selected"> ${marital1.value}</option>
														</c:if>
														<c:if test = "${marital_flag==0}">
														<option value="${marital1.key}" > ${marital1.value}</option>
														</c:if>
													
													</c:forEach>
											
											<%} %>
										</select> 
										
										</div>
										
										  
										
					                </div>
					           </div>     
					                
			              <div class="row">
					             
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Height From</label>
										<select name = "height_info_from_id" class="form-control">
											<% if(countstatus==0){ %>
											<c:forEach items="${heightlisthashmap}" var="heightlist">
												<option value="${heightlist.key}" <c:if test="${heightlist.key == 1}"> selected="selected" </c:if> >${heightlist.value}</option>
											</c:forEach>
											<% } else { %>
												<c:forEach items="${heightlisthashmap}" var="heightlist">
													<option value="${heightlist.key}" <c:if test="${heightlist.key == rsobj.getHeight_info_from_id()}"> selected="selected" </c:if> >${heightlist.value}</option>
												</c:forEach>
											<%} %>
										</select>   
									
					                </div>
					                
					                <div class="form-group col-md-6">
					                  <label for="form_choose_password">Height To</label>
										<select name = "height_info_to_id" class="form-control">
											<% if(countstatus==0){ %>
											<c:forEach items="${heightlisthashmap}" var="heightlist">
												<option value="${heightlist.key}" <c:if test="${heightlist.key == 31}"> selected="selected" </c:if> >${heightlist.value}</option>
											</c:forEach>
											<% } else { %>
											<c:forEach items="${heightlisthashmap}" var="heightlist">
												<option value="${heightlist.key}" <c:if test="${heightlist.key == rsobj.getHeight_info_to_id()}"> selected="selected" </c:if> >${heightlist.value}</option>
											</c:forEach>
											<%} %>
										</select>   
									
					                </div>
					                
					                
					                
			              </div>
			            <div class="form-group col-md-12"  style="border-bottom: 1px dashed #ccc;">
						             	
						             	<p style="color:#1601CF;font-weight: bold;">** Location Details</p>
					             	
				        </div>
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
												<option value="${country.key}"  <c:if test="${country.key == rsobj.getCountry_id()}"> selected="selected" </c:if>>${country.value}</option>
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
												<option value="${statelistedit.getId()}" <c:if test="${rsobj.getState_id() == statelistedit.getId()}"> selected="selected" </c:if>>${statelistedit.getName()}</option>
											</c:forEach>
											<%} %>
											
										<%} %>
										</select>   
										
					                </div>
					                
					                
			              </div>
			              <div class="row">
			              
			              
			             			 
					                <div class="form-group col-md-12" >
					                
					                   <div id="city_divid">
										<% if(countstatus==0){ %>
										<% } else { %>
										
										<% if(citylistedit_status==1){ %>
										
										   
										   <label for="form_choose_password">City </label>
										   
										   <select name = "city_id" class="form-control" id="city_id" multiple="multiple"  placeholder="Please Select">
											
											
											<%
					              				//convert string into array 
					              				String city_ar[]=rsobj.getCity_id().split(",");
					              			
					              			%>
					              			<c:set var="city_ar" value="<%=city_ar%>" />
									
											<c:forEach items="${citylistedit}" var="citylistedit1">
													<c:set var="citylist_flag" value="0" />
													<c:forEach items="${city_ar}" var="city_ar_val">
														<c:if test = "${city_ar_val == citylistedit1.getId()}">
															<c:set var="citylist_flag" value="1" />
														</c:if>
													</c:forEach>
												<c:if test = "${citylist_flag==1}">
												<option value="${citylistedit1.getId()}"  selected="selected"> ${citylistedit1.getName()}</option>
												</c:if>
												
											
											</c:forEach>
											
											<c:forEach items="${citylistedit}" var="citylistedit1">
													<c:set var="citylist_flag" value="0" />
													<c:forEach items="${city_ar}" var="city_ar_val">
														<c:if test = "${city_ar_val == citylistedit1.getId()}">
															<c:set var="citylist_flag" value="1" />
														</c:if>
													</c:forEach>
												
												<c:if test = "${citylist_flag==0}">
												<option value="${citylistedit1.getId()}" > ${citylistedit1.getName()}</option>
												</c:if>
											
											</c:forEach>
											
											</select>
											
											
											<%} %>
											
										<%} %>
										</div>
											<div id="seldrop">
											
											
											</div>
					                </div>
			              
			              
			              </div>
			              
              
           </div>   
              
              
	            				 
		   		<div id="step3" >  
	              
				              
					           		<div class="form-group col-md-12"  style="border-bottom: 1px dashed #ccc;">
						             	
						             	<p style="color:#01840C;font-weight: bold;">** Career Details</p>
					             	
				             		</div>
				              
				              <div class="row">
				              		
			              			
				              			<div class="form-group col-md-12">
						                  <label for="form_choose_password">Highest Education</label>
						                  
						                  
						                  <div class="dropdown-mul-2">
						                  
											<select name = "highest_education_id" class="form-control" multiple placeholder="Please Select">
											<option value="0" >Any</option>
											<% if(countstatus==0){ %>
											<c:forEach items="${highesteducationlisthashmap}" var="highesteducation">
												<option value="${highesteducation.key}"  >${highesteducation.value}</option>
											</c:forEach>
											<% } else { %>
											
												<%
						              			//convert string into array 
						              			String highedu_ar[]=rsobj.getHighest_education_id().split(",");
						              			
						              			%>
			              						<c:set var="highedu_ar" value="<%=highedu_ar%>" />
											
												   <c:forEach items="${highesteducationlisthashmap}" var="highedu1">
															<c:set var="highedu_flag" value="0" />
															<c:forEach items="${highedu_ar}" var="highedu_ar_val">
																<c:if test = "${highedu_ar_val == highedu1.key.toString()}">
																	<c:set var="highedu_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${highedu_flag==1}">
														<option value="${highedu1.key}"  selected="selected"> ${highedu1.value}</option>
														</c:if>
														<c:if test = "${highedu_flag==0}">
														<option value="${highedu1.key}" > ${highedu1.value}</option>
														</c:if>
													
													</c:forEach>
											<%} %>
											</select>  
											
											</div>
											 
											
						                </div>
						                
				              </div>
				              
				               <div class="row">
				               
				               
			              			
				                		<div class="form-group col-md-12 ">
						        	          <label for="form_choose_password">Annual Income</label>
						        	          
						        	       <div class="dropdown-mul-2"> 
											<select name = "annual_income_id" class="form-control" multiple placeholder="Please Select">
										
												<option value="0" >Any</option>
												<% if(countstatus==0){ %>
												<c:forEach items="${annualincomelisthashmap}" var="annualincome">
													<option value="${annualincome.key}"  >${annualincome.value}</option>
												</c:forEach>
												<% } else { %>
												
												   <%
								              			//convert string into array 
								              			String annualinc_ar[]=rsobj.getAnnual_income_id().split(",");
								              			
								              	   %>
								              			<c:set var="annualinc_ar" value="<%=annualinc_ar%>" />
												
												   <c:forEach items="${annualincomelisthashmap}" var="annualinc1">
															<c:set var="annualinc_flag" value="0" />
															<c:forEach items="${annualinc_ar}" var="annualinc_ar_val">
																<c:if test = "${annualinc_ar_val == annualinc1.key.toString()}">
																	<c:set var="annualinc_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${annualinc_flag==1}">
														<option value="${annualinc1.key}"  selected="selected"> ${annualinc1.value}</option>
														</c:if>
														<c:if test = "${annualinc_flag==0}">
														<option value="${annualinc1.key}" > ${annualinc1.value}</option>
														</c:if>
													
													</c:forEach>
												<%} %>
											</select>   
											
										</div>
											
											
											
						           </div>
						       </div>
				              
				              <div class="row">
				              
				              			<div class="form-group col-md-12 ">
						                  <label for="form_choose_password">Employed In</label>
						                  
						                  <div class="dropdown-mul-2">
						                  
											<select name = "employed_in_id" class="form-control" multiple placeholder="Please Select">
												<option value="0" >Any</option>
												<%  if(countstatus==0){ %>
												<c:forEach items="${employedinlisthashmap}" var="employedin">
													<option value="${employedin.key}"  >${employedin.value}</option>
												</c:forEach>
												<% } else { %>
												
												   <%
								              			//convert string into array 
								              			String employedin_ar[]=rsobj.getEmployed_in_id().split(",");
								              			
								              	   %>
								              			<c:set var="employedin_ar" value="<%=employedin_ar%>" />
												
												<c:forEach items="${employedinlisthashmap}" var="employedin1">
															<c:set var="employedin_flag" value="0" />
															<c:forEach items="${employedin_ar}" var="employedin_ar_val">
																<c:if test = "${employedin_ar_val == employedin1.key.toString()}">
																	<c:set var="employedin_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${employedin_flag==1}">
														<option value="${employedin1.key}"  selected="selected"> ${employedin1.value}</option>
														</c:if>
														<c:if test = "${employedin_flag==0}">
														<option value="${employedin1.key}" > ${employedin1.value}</option>
														</c:if>
													
													</c:forEach>
												<%} %>
											</select> 
											
											
											</div>
											  
										
						                </div>
						                
						                
				              </div>
				              
				              <div class="row">
							            
			              	   			
							   			<div class="form-group col-md-12 ">
						                  <label for="form_choose_password">Occupation</label>
						                  
						                  <div class="dropdown-mul-2">
						                  
						                  
											<select name = "occupation_info_id" class="form-control" multiple placeholder="Please Select">
												<option value="0" >Any</option>
												<% if(countstatus==0){ %>
												<c:forEach items="${occupationlisthashmap}" var="occupationlist">
													<option value="${occupationlist.key}"  >${occupationlist.value}</option>
												</c:forEach>
												<% } else { %>
												    <%
						              					//convert string into array 
						              					String occupation_ar[]=rsobj.getOccupation_info_id().split(",");
						              			
						              	   			%>    
						              	   			<c:set var="occupation_ar" value="<%=occupation_ar%>" />
													<c:forEach items="${occupationlisthashmap}" var="occupation1">
															<c:set var="occupation_flag" value="0" />
															<c:forEach items="${occupation_ar}" var="occupation_ar_val">
																<c:if test = "${occupation_ar_val == occupation1.key.toString()}">
																	<c:set var="occupation_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${occupation_flag==1}">
														<option value="${occupation1.key}"  selected="selected"> ${occupation1.value}</option>
														</c:if>
														<c:if test = "${occupation_flag==0}">
														<option value="${occupation1.key}" > ${occupation1.value}</option>
														</c:if>
													
													</c:forEach>
													
												<%} %>
											</select>  
											
											</div>
											 
											
						                </div>
						      </div>
						      
						   
							  
						      <div class="row">
				                
				                <div class="form-group col-md-12">
				                  <label for="form_choose_username"> Save Search As </label>
				                  <input type="text" class="form-control" name="search_name" placeholder="Enter Save Search Name"/>
				                </div>
		                
		             		 </div>
						      
						      
			   					 
			   			<div class="row">
				             
		             	 
			             	 <div class="form-group  col-md-6">
				                
			                	<button class="btn btn-dark btn-lg btn-block mt-15" type="submit" style="background: rgb(245, 131, 32) !important; width:40%;">Search</button>
			             	 </div>
		             	 </div>
			   		
			   		
			   	 </div>			 
		   					 
		   					 
		             </form> 
		    </div>
		              
		        
		              
		        <div class="col-md-4 srchbyiddv" style="border: 1px solid #d1d1d1;padding: 20px;margin-left:10px;">
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
				 
				  var str='<label for="form_choose_password">City </label><select name = "city_id" class="form-control" id="city_id" multiple placeholder="Please Select"><option value="0" >Select</option>';
				  $.each(response, function (key, val) {
					   str+='<option value="'+val.id+'">'+val.name+'</option>';
				  });
				  str+='</select>';
				  
				  $("#city_divid").html(str);
				  
				  //refresh the multiselect plugin
				  $("#city_id").chosen();
					  
			  }
		});	  
	
	
	})
	
})
$(document).ready(function(){
	$('.dropdown-mul-2').dropdown({
	      //limitCount: 5,
	      searchable: true,
	      multipleMode: 'label',
	      choice: function () {
		        // console.log(arguments,this);
		      }
	    });
	
	//load multiselect plugin
	$("#city_id").chosen();
	
})

function searchbymatid()
{
		var usermatrimonyid=$("#usermatrimonyid").val();
		if(usermatrimonyid=="")
		{
		 	$("#usermatrimonyid_err").html("Please Enter Matrimony ID");
		}
		else
		{
			document.srbyidform.submit();
		}
		
}
</script>
</body>
</html>