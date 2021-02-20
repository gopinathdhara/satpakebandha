<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html dir="ltr" lang="en">
<%@ include file="includes/headcss.jsp" %>

<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<script src="<c:url value="/resources/bodyloader/jquery.loading.js"/>"></script>
<link  rel="stylesheet" href="<c:url value="/resources/bodyloader/loading.css" />" >

<div class="body-overlay"></div>

<div id="wrapper">
  <!-- preloader -->
   <%@ include file="includes/preloader.jsp" %>
  <%@ page import="com.beans.Regular_Search" %>
  <!-- Header -->
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content mgtop1 ">
    <!-- Section: inner-header -->
    

    <!-- Section: event calendar -->
    <section >
      <div class="container">
      <div id="side-panel-trigger" class="side-panel-trigger" style="display:none"><a href="#"><i class="fa fa-filter" aria-hidden="true" class="advfiltermnu" style="color: #ff9902;font-size: 45px;"></i></a></div>
      <input type="hidden" id="demousrimg" value="<c:url value="/resources/images/userimages/" />"/>
      <input type="hidden" id="liveusrimg" value="<c:url value="/resources/userprofileimages/" />"/>
      <input type="hidden" id="lastpostid" value="0"/>
        <div class="row">
        
          
          <!--   <h5 class="allmmhd">Matches found based on your search</h5>-->
			<% 
				int searchid;
				try
				{
					searchid=Integer.parseInt(request.getParameter("searchid"));
				}
				catch(Exception e)
				{
					searchid=0;
				}
				
				String paramtype;
				try
				{
					paramtype=request.getParameter("paramtype").toString();
				}
				catch(Exception e)
				{
					paramtype="";
				}
			%>
          <% if(paramtype.equals("allpartnermatch")){%>
          	<p class="srhdlne" id="recmsg">Records found based on your partner preferences</p>
          <% } else { %>
          	<p class="srhdlne" id="recmsg">Records found based on your search</p>
          <% } %>
          
          
          <div class="col-sm-12 col-md-9 blog-pull-right prfboxshd" style="margin-top: 10px;">
              <div class="row  prfboxshd bordrbox" id="containr" >
              
              </div>
              	<div class="process-comm"  id="loaderspinn" style="display:none; text-align:center" >
        			<i class="fa fa-cog fa-spin" style="font-size:48px;color:red"></i>
        		</div>
            </div>
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
          
          <div class="col-sm-12 col-md-3 advsdbar" >
	            <div class="sidebar sidebar-left mt-sm-30">
	            		<p class="advsrhedg">  Basic Details <span id="baschdspn" class="baschdspn">+</span></p>
			            <div class="basicdetails" id="basicdetails">
					          	
					            <div class="widget" id="baschdwidget">
					                <h5 class="widget-title line-bottom">Age (in years)</h5>
					               
					                
						                <select name="age_form" class="form-control ageadvsr" id="age_form">
													
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
										
										<select name="age_to" class="form-control ageadvsr" id="age_to">
												
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
										
										<div class="clrfix"></div>
					               
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Height</h5>
					               
					                
						                <select name="height_info_from_id" class="form-control ageadvsr" id="height_info_from_id">
													
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
										
										<select name="height_info_to_id" class="form-control ageadvsr" id="height_info_to_id">
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
										
										<div class="clrfix"></div>
					               
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					            
					            
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Mother Tongue</h5>
					                <div class="reladvsr">
					                
					                <% if(countstatus==0){ %>
						                <c:forEach items="${get_mother_tongue_list_with_count}" var="mothertongue">
						                	<input type="checkbox" name="mothertongue" value="${mothertongue.id}"/> ${mothertongue.itemname}  (${mothertongue.countitem})<br/>
										
										</c:forEach>
										 <% } else { %>
										 <%
						              			//convert string into array 
						              			String mothertongu_ar[]=rsobj.getMother_tongue_id().split(",");
					              			
					              		  %>
					              		  <c:set var="mothertongu_ar" value="<%=mothertongu_ar%>" />
					              			
												<c:forEach items="${get_mother_tongue_list_with_count}" var="mothertongue">
														<c:set var="caste_flag" value="0" />
														<c:forEach items="${mothertongu_ar}" var="mothertongu_ar_val">
															<c:if test = "${mothertongu_ar_val == mothertongue.id.toString()}">
																<c:set var="caste_flag" value="1" />
															</c:if>
														</c:forEach>
													<c:if test = "${caste_flag==1}">
													
														<input type="checkbox" name="mothertongue" value="${mothertongue.id}" checked="checked"/> ${mothertongue.itemname}  (${mothertongue.countitem})<br/>
													</c:if>
													<c:if test = "${caste_flag==0}">
														<input type="checkbox" name="mothertongue" value="${mothertongue.id}" /> ${mothertongue.itemname}  (${mothertongue.countitem})<br/>
													</c:if>
												
												</c:forEach>
											
										<%} %>
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Marital status</h5>
					                <div class="reladvsr">
					              		 <% if(countstatus==0){ %>
						               <c:forEach items="${get_maritalstatus_list_with_count}" var="maritalsts">
						                	<input type="checkbox" name="maritalsts" value="${maritalsts.id}"/> ${maritalsts.itemname}  (${maritalsts.countitem})<br/>
										
										</c:forEach>
										<% } else { %>
											
													<%
							              				//convert string into array 
							              				String marital_ar[]=rsobj.getMarital_status_id().split(",");
							              			
							              			%>
							              <c:set var="marital_ar" value="<%=marital_ar%>" />
											
													<c:forEach items="${get_maritalstatus_list_with_count}" var="maritalsts">
															<c:set var="marital_flag" value="0" />
															<c:forEach items="${marital_ar}" var="marital_ar_val">
																<c:if test = "${marital_ar_val == maritalsts.id.toString()}">
																	<c:set var="marital_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${marital_flag==1}">
															
															<input type="checkbox" name="maritalsts" value="${maritalsts.id}" checked="checked"/> ${maritalsts.itemname}  (${maritalsts.countitem})<br/>
														</c:if>
														<c:if test = "${marital_flag==0}">
															<input type="checkbox" name="maritalsts" value="${maritalsts.id}" /> ${maritalsts.itemname}  (${maritalsts.countitem})<br/>
														</c:if>
													
													</c:forEach>
											
											<%} %>
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
			              
			              
			         
			            </div>
	            
	            			<p class="advsrhedg">  Religious Details <span id="religioushdspn" class="baschdspn">-</span></p>
					       <div class="religiousdetails" id="religiousdetails" style="display:none">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Religion</h5>
					                <div class="reladvsr">
					                <c:set var="relgn_ar" value="<%=rsobj.getReligion_id()%>" />
					                	<% if(countstatus==0){ %>
						                	<c:forEach items="${get_religion_list_with_count}" var="religion">
							                	<input type="checkbox" name="religionadvsrnm" value="${religion.id}"/> ${religion.itemname}  (${religion.countitem})<br/>
											
											</c:forEach>
										 <% } else { %>
											 <c:forEach items="${get_religion_list_with_count}" var="religion">
												<input type="checkbox" name="religionadvsrnm" value="${religion.id}" <c:if test = "${relgn_ar == religion.id}"> checked="checked" </c:if>/> ${religion.itemname}  (${religion.countitem})<br/>
											</c:forEach>
											
											
										 <%} %>
					                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Caste</h5>
					                <div class="reladvsr">
					                <% if(countstatus==0){ %>
					               		 <c:forEach items="${get_caste_list_with_count}" var="caste1">
						                	<input type="checkbox" name="casteadvsrnm" value="${caste1.id}"/> ${caste1.itemname}  (${caste1.countitem})<br/>
										
										</c:forEach>
									 <% } else { %>	
						                			 <%
							              				//convert string into array 
							              				String caste_ar[]=rsobj.getCaste_info_id().split(",");
							              			
							              			 %>
							              			<c:set var="caste_ar" value="<%=caste_ar%>" />
												 
													<c:forEach items="${get_caste_list_with_count}" var="caste1">
															<c:set var="caste_flag" value="0" />
															<c:forEach items="${caste_ar}" var="caste_ar_val">
																<c:if test = "${caste_ar_val == caste1.id.toString()}">
																	<c:set var="caste_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${caste_flag==1}">
															<input type="checkbox" name="casteadvsrnm" value="${caste1.id}" checked="checked" /> ${caste1.itemname}  (${caste1.countitem})<br/>
														</c:if>
														<c:if test = "${caste_flag==0}">
															<input type="checkbox" name="casteadvsrnm" value="${caste1.id}"/> ${caste1.itemname}  (${caste1.countitem})<br/>
														</c:if>
													
													</c:forEach>
										 <%} %>
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					         
					            </div>
					          
					          
					       </div>
					       
					       
					       <p class="advsrhedg">  Professional Details <span id="profhdspn" class="baschdspn">-</span></p>
					       <div class="careerdetails" style="display:none" id="careerdetails">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Highest Education</h5>
					                <div class="reladvsr">
					                <% if(countstatus==0){ %>
					                	<c:forEach items="${get_highest_education_list_with_count}" var="higheduc">
						                	<input type="checkbox" name="higheducadvsrnm" value="${higheduc.id}"/> ${higheduc.itemname}  (${higheduc.countitem})<br/>
										
										</c:forEach>
					                 <% } else { %>
											
												<%
						              			//convert string into array 
						              			String highedu_ar[]=rsobj.getHighest_education_id().split(",");
						              			
						              			%>
			              						<c:set var="highedu_ar" value="<%=highedu_ar%>" />
											
												   <c:forEach items="${get_highest_education_list_with_count}" var="higheduc">
															<c:set var="highedu_flag" value="0" />
															<c:forEach items="${highedu_ar}" var="highedu_ar_val">
																<c:if test = "${highedu_ar_val == higheduc.id.toString()}">
																	<c:set var="highedu_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${highedu_flag==1}">
															<input type="checkbox" name="higheducadvsrnm" value="${higheduc.id}" checked="checked"/> ${higheduc.itemname}  (${higheduc.countitem})<br/>
														</c:if>
														<c:if test = "${highedu_flag==0}">
															<input type="checkbox" name="higheducadvsrnm" value="${higheduc.id}"/> ${higheduc.itemname}  (${higheduc.countitem})<br/>
														</c:if>
													
													</c:forEach>
											<%} %>
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Annual Income</h5>
					                <div class="reladvsr">
					                <% if(countstatus==0){ %>
					                	<c:forEach items="${annualincome_list_with_count}" var="annlincm">
						                	<input type="checkbox" name="annlincmadvsrnm" value="${annlincm.id}"/> ${annlincm.itemname}  (${annlincm.countitem})<br/>
										
										</c:forEach>
					                
						             <% } else { %>
												
												   <%
								              			//convert string into array 
								              			String annualinc_ar[]=rsobj.getAnnual_income_id().split(",");
								              			
								              	   %>
								              			<c:set var="annualinc_ar" value="<%=annualinc_ar%>" />
												
												  <c:forEach items="${annualincome_list_with_count}" var="annlincm">
															<c:set var="annualinc_flag" value="0" />
															<c:forEach items="${annualinc_ar}" var="annualinc_ar_val">
																<c:if test = "${annualinc_ar_val == annlincm.id.toString()}">
																	<c:set var="annualinc_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${annualinc_flag==1}">
															<input type="checkbox" name="annlincmadvsrnm" value="${annlincm.id}" checked="checked"/> ${annlincm.itemname}  (${annlincm.countitem})<br/>
														</c:if>
														<c:if test = "${annualinc_flag==0}">
															<input type="checkbox" name="annlincmadvsrnm" value="${annlincm.id}"/> ${annlincm.itemname}  (${annlincm.countitem})<br/>
														</c:if>
													
												</c:forEach>
									<%} %>   
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					          
					           <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Employed In</h5>
					                <div class="reladvsr">
					                <%  if(countstatus==0){ %>
					                	<c:forEach items="${employedin_list_with_count}" var="empin">
						                	<input type="checkbox" name="empinadvsrnm" value="${empin.id}"/> ${empin.itemname}  (${empin.countitem})<br/>
										
										</c:forEach>
					                <% } else { %>
												
												   <%
								              			//convert string into array 
								              			String employedin_ar[]=rsobj.getEmployed_in_id().split(",");
								              			
								              	   %>
								              			<c:set var="employedin_ar" value="<%=employedin_ar%>" />
												
												   <c:forEach items="${employedin_list_with_count}" var="empin">
															<c:set var="employedin_flag" value="0" />
															<c:forEach items="${employedin_ar}" var="employedin_ar_val">
																<c:if test = "${employedin_ar_val == empin.id.toString()}">
																	<c:set var="employedin_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${employedin_flag==1}">
															<input type="checkbox" name="empinadvsrnm" value="${empin.id}" checked="checked"/> ${empin.itemname}  (${empin.countitem})<br/>
														</c:if>
														<c:if test = "${employedin_flag==0}">
															<input type="checkbox" name="empinadvsrnm" value="${empin.id}"/> ${empin.itemname}  (${empin.countitem})<br/>
														</c:if>
													
													</c:forEach>
									<%} %>
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Occupation</h5>
					                <div class="reladvsr">
					                <% if(countstatus==0){ %>
					                	<c:forEach items="${occupation_list_with_count}" var="occplist">
						                	<input type="checkbox" name="occplistadvsrnm" value="${occplist.id}"/> ${occplist.itemname}  (${occplist.countitem})<br/>
										
										</c:forEach>
					                
						              <% } else { %>
												    <%
						              					//convert string into array 
						              					String occupation_ar[]=rsobj.getOccupation_info_id().split(",");
						              			
						              	   			%>    
						              	   			<c:set var="occupation_ar" value="<%=occupation_ar%>" />
													<c:forEach items="${occupation_list_with_count}" var="occplist">
															<c:set var="occupation_flag" value="0" />
															<c:forEach items="${occupation_ar}" var="occupation_ar_val">
																<c:if test = "${occupation_ar_val == occplist.id.toString()}">
																	<c:set var="occupation_flag" value="1" />
																</c:if>
															</c:forEach>
														<c:if test = "${occupation_flag==1}">
															<input type="checkbox" name="occplistadvsrnm" value="${occplist.id}" checked="checked"/> ${occplist.itemname}  (${occplist.countitem})<br/>
														</c:if>
														<c:if test = "${occupation_flag==0}">
															<input type="checkbox" name="occplistadvsrnm" value="${occplist.id}"/> ${occplist.itemname}  (${occplist.countitem})<br/>
														</c:if>
													
													</c:forEach>
													
									  <%} %>  
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					          
					          
					          
					       </div>
					       
					       <p class="advsrhedg">  Location Details <span id="lochdspn" class="baschdspn">-</span></p>
	            		<div class="locationdetails" id="locationdetails" style="display:none">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Country</h5>
					                <div class="">
					                <% if(countstatus==0){ %>
					                	<c:forEach items="${country_list_with_count}" var="cntrylist">
						                	<input type="checkbox" name="cntrylistadvsrnm" value="${cntrylist.id}"/> ${cntrylist.itemname}  (${cntrylist.countitem})<br/>
										
										</c:forEach>
					                
						               <% } else { %>
											<c:forEach items="${country_list_with_count}" var="cntrylist">
												
												<input type="checkbox" name="cntrylistadvsrnm" value="${cntrylist.id}" <c:if test="${cntrylist.id == rsobj.getCountry_id()}"> checked="checked" </c:if>/> ${cntrylist.itemname}  (${cntrylist.countitem})<br/>
											</c:forEach>
											
										<%} %> 
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">State</h5>
					                <div class="reladvsr">
					                <% if(countstatus==0){ %>
					                	<c:forEach items="${state_list_with_count}" var="statelist">
						                	<input type="checkbox" name="statelistadvsrnm" value="${statelist.id}"/> ${statelist.itemname}  (${statelist.countitem})<br/>
										
										</c:forEach>
					                
						              <% } else { %>
										
										<% if(statelistedit_status==1){ %>
										
											<c:forEach items="${state_list_with_count}" var="statelist">
												
												<input type="checkbox" name="statelistadvsrnm" value="${statelist.id}" <c:if test="${rsobj.getState_id() == statelist.id}"> checked="checked" </c:if>/> ${statelist.itemname}  (${statelist.countitem})<br/>
											</c:forEach>
											<%} %>
											
										<%} %>  
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					          
					          
						</div>
						
						
						
						<%
						if(paramtype.equals("allpartnermatch") || paramtype.equals("allpartnernewmatch") || paramtype.equals("allpartnerpremiummatch"))
						{
						%>
						<form:form  modelAttribute="command">
						<p class="advsrhedg"> Lifestyle Habits <span id="otherhdspn" class="baschdspn">-</span></p>
	            		<div class="otherdetails" id="otherdetails" style="">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					              <div class="widget">
					               <h5 class="widget-title line-bottom">Body Type </h5>
						                <div class="row">
												<div class="form-group col-md-12">
												 
														  <form:checkbox path="body_type" name="body_type" value="1" /> Slim
														  <form:checkbox path="body_type" name="body_type" value="2" /> Athletic	
														  <form:checkbox path="body_type" name="body_type" value="3" /> Average 
														  <form:checkbox path="body_type" name="body_type" value="4" /> Heavy
														  <form:checkbox path="body_type" name="body_type" value="0" /> Doesn't matter
														  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
														 
												</div>
										</div>
					                
					               
					              </div>
					              
					              
					            </div>
					            <hr/>
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					              <div class="widget">
					              	 <h5 class="widget-title line-bottom">Complexion </h5>
						                <div class="row">
												<div class="form-group col-md-12">
												
														 <form:checkbox path="complexion" name="complexion" value="1" /> Very Fair
														 <form:checkbox path="complexion" name="complexion" value="2" /> Fair 	
				 										 <form:checkbox path="complexion" name="complexion" value="3" /> Wheatish  
				 										 <form:checkbox path="complexion" name="complexion" value="4" /> Wheatish Brown
				 										 <form:checkbox path="complexion" name="complexion" value="5" /> Dark
				 										 <form:checkbox path="complexion" name="complexion" value="0" /> Doesn't matter
														  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
														 
												</div>
										</div>
					                
					               
					              </div>
					              
					            </div>
					            
					             <hr/>
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					              <div class="widget">
					               	<h5 class="widget-title line-bottom">Physical Status </h5>
					                <div class="row">
											<div class="form-group col-md-12">
											
													<form:checkbox path="physical_status" name="physical_status" value="1" /> Normal

 										 			<form:checkbox path="physical_status" name="physical_status" value="2" /> Physically challenged
			               				 			<form:checkbox path="physical_status" name="physical_status" value="0" /> Doesn't matter
													  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
													 
											</div>
									</div>
					                
					               
					              </div>
					              
					            </div>
					             <hr/>
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					               <h5 class="widget-title line-bottom">Eating Habits </h5>
					                <div class="row">
											<div class="form-group col-md-12">
											
													 <form:checkbox path="eating_habits" name="eating_habits" value="1" /> Vegetarian

 										 			<form:checkbox path="eating_habits" name="eating_habits" value="2" /> Non Vegetarian
 										 
 										 			<form:checkbox path="eating_habits" name="eating_habits" value="3" /> Eggetarian
 										 			<form:checkbox path="eating_habits" name="eating_habits" value="0" /> Doesn't matter
													  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
													 
											</div>
									</div>
					                
					               
					              </div>
					              
					            </div>
					             <hr/>
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
							              <div class="widget">
							               <h5 class="widget-title line-bottom">Drinking Habits </h5>
							                <div class="row">
													<div class="form-group col-md-12">
													
															 <form:checkbox path="drinking_habits" name="drinking_habits" value="1" /> Never drinks
		
		 													 <form:checkbox path="drinking_habits" name="drinking_habits" value="2" /> Drinks socially
		 										 
		 													 <form:checkbox path="drinking_habits" name="drinking_habits" value="3" /> Drinks regularly
		 										 
		 													 <form:checkbox path="drinking_habits" name="drinking_habits" value="0" /> Doesn't matter
															  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
															 
													</div>
											</div>
							                
							               
							              </div>
					              
					            </div>
					             <hr/>
					            <div class="sidebar sidebar-left mt-sm-30">
					            
						              <div class="widget">
								               <h5 class="widget-title line-bottom">Smoking Habits </h5>
								                <div class="row">
														<div class="form-group col-md-12">
														 
																 <form:checkbox path="smoking_habits" name="smoking_habits" value="1" /> Never smokes
			
			 													 <form:checkbox path="smoking_habits" name="smoking_habits" value="2" /> Smokes occasionally
			 										 
			 													 <form:checkbox path="smoking_habits" name="smoking_habits" value="3" /> Smokes regularly
			 										
			 													 <form:checkbox path="smoking_habits" name="smoking_habits" value="0" /> Doesn't matter
																  <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
																 
														</div>
												</div>
						                
						              </div>
					              
					            </div>
					            
					          
						</div>
						</form:form> 
						<% 
						}
						%>
						
	            
	            
	          </div>
          
          </div>
          
          
          
        </div>
      </div>
    </section>
  </div>
  <!-- end main-content -->
  
  <!-- Footer -->
  
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>

<input type="hidden" name="searchid" id="searchid" value="<%= searchid %>"/>
<input type="hidden" name="paramtype" id="paramtype" value="<%= paramtype %>"/>
<input type="hidden" name="advsrcstsval" id="advsrcstsval" value="0"/>
<input type="hidden" id="urlimg" value="<c:url value="/resources/images/" />"/>
<script>

$(document).ready(function(){
	
	
	var mobscrollflag=0;
	var deskscrollflag=0;		
	var emptydeksdata=0;		
	var emptymobdata=0;
	/* for blood donation work flow */
 
	/* for blood donation work flow */
	if (window.matchMedia("(max-width: 767px)").matches)  
        { 
            
				$(document.body).on('touchmove', onScroll); // for mobile
				$(window).on('scroll', onScroll); 
		
				function onScroll(){
								//Will check if the user has reached bottom of a PAGE
								
								// for mobile view add -100
								if(Math.round($(window).scrollTop()) >=Math.round(($(document).height()-window.innerHeight-100))){
									//$('#loading').fadeIn();
									//setTimeout("appendContent()", 1000);
		
									var lastpostid= $("#lastpostid").val(); 
									var searchid=$("#searchid").val();
									var paramtype=$("#paramtype").val();
									
									if(mobscrollflag==0)
									{
										mobscrollflag=1;
										if(emptymobdata==0)									
		                                {										
		                                    //$("#loaderspinn").show(); 
											 $("body").loading();
		                                }
										
										if($("#advsrcstsval").val()==1)
										{
												var age_form=$("#age_form").val();
												var age_to=$("#age_to").val();
												var height_info_from_id=$("#height_info_from_id").val();
												var height_info_to_id=$("#height_info_to_id").val();
												
												/*life habits*/
												var body_type_Array = $('input[name="body_type"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(body_type_Array=="")
													{
													body_type_Array=0;
													}
												
												var complexion_Array = $('input[name="complexion"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(complexion_Array=="")
													{
													complexion_Array=0;
													}
												
												var physical_status_Array = $('input[name="physical_status"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(physical_status_Array=="")
													{
													physical_status_Array=0;
													}
												
												var eating_habits_Array = $('input[name="eating_habits"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(eating_habits_Array=="")
													{
													eating_habits_Array=0;
													}
												
												var drinking_habits_Array = $('input[name="drinking_habits"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(drinking_habits_Array=="")
													{
													drinking_habits_Array=0;
													}
												
												var smoking_habits_Array = $('input[name="smoking_habits"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(smoking_habits_Array=="")
													{
													smoking_habits_Array=0;
													}
												/*life habits*/
												
												var mothertongueArray = $('input[name="mothertongue"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(mothertongueArray=="")
													{
														mothertongueArray=0;
													}
												
												var religionadvsrnm = $('input[name="religionadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(religionadvsrnm=="")
													{
														religionadvsrnm=0;
													}
												
												var casteadvsrnm = $('input[name="casteadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(casteadvsrnm=="")
													{
														casteadvsrnm=0;
													}
												
												
												var maritalsts = $('input[name="maritalsts"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(maritalsts=="")
													{
														maritalsts=0;
													}
												
												var higheducadvsrnm = $('input[name="higheducadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(higheducadvsrnm=="")
													{
														higheducadvsrnm=0;
													}
													
												var annlincmadvsrnm = $('input[name="annlincmadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(annlincmadvsrnm=="")
													{
														annlincmadvsrnm=0;
													}
													
												var empinadvsrnm = $('input[name="empinadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(empinadvsrnm=="")
													{
														empinadvsrnm=0;
													}
													
												var occplistadvsrnm = $('input[name="occplistadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(occplistadvsrnm=="")
													{
														occplistadvsrnm=0;
													}
				
												var cntrylistadvsrnm = $('input[name="cntrylistadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(cntrylistadvsrnm=="")
													{
														cntrylistadvsrnm=0;
													}
				
												var statelistadvsrnm = $('input[name="statelistadvsrnm"]:checked').map(function () {  
											        return this.value;
											        }).get().join(",");
												
												if(statelistadvsrnm=="")
													{
														statelistadvsrnm=0;
													}	
												
												
												var datavalues= "lastpostid="+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm+"&body_type_Array="+body_type_Array+"&physical_status_Array="+physical_status_Array+"&eating_habits_Array="+eating_habits_Array+"&drinking_habits_Array="+drinking_habits_Array+"&smoking_habits_Array="+smoking_habits_Array+"&complexion_Array="+complexion_Array;
												surl="get_all_advanceregularsearchlist_after_reach_bottom_of_page";	
										}
									else{
										surl="get_all_regularsearchlist_after_reach_bottom_of_page";
										var datavalues='lastpostid='+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype;
									}
										
										
										$.ajax({
											url: surl,
											type: 'POST',
											cache: false,
											async: false,
											dataType: 'html',
											data: datavalues,
											success: function (data) {
												
													$("#loaderspinn").fadeOut();
													
														setTimeout(function(){ $(":loading").loading("stop") }, 2000);
													
													
													mobscrollflag=0;
												
														    var response = JSON.parse(data);
															//var demoimg=$("#demousrimg").val()+'demouser.png';
															var str='';
															if (jQuery.isEmptyObject(response))						
															   {
																	emptymobdata=1;													
															   }
												  $.each(response, function (key, val) {
													
														 if(val.gender=='Female')
		  		                      					  {
		  		                      					 	 //var demoimg=$("#demousrimg").val()+'demouser3.png';
		  		                      					 	 
										  		              if(val.profile_image=='')
															  {
												 	 			var demoimg=$("#demousrimg").val()+'demouser3.png';
															  	
															  }
														  else
															  {
															  	var demoimg=$("#liveusrimg").val()+val.profile_image;
															  }
		  		                      					 	 
		  		                      					 	 var likestr='Like Her';
		  		                      					  }
		  		                      				 	 else
		  		                      					  {
		  		                      					 	//var demoimg=$("#demousrimg").val()+'demouser2.jpg';
		  		                      					 	
											  		              if(val.profile_image=='')
																  {
															 		var demoimg=$("#demousrimg").val()+'demouser2.jpg';
																  	
																  }
															  else
																  {
																  	var demoimg=$("#liveusrimg").val()+val.profile_image;
																  }
		  		                      					 	
		  		                      					    var likestr='Like Him';
		  		                      					  }
														  var callimg=$("#urlimg").val()+'call-icon.png';
														  var cstname='';
														  var lastpostid=val.id;
														  $("#lastpostid").val(lastpostid);
														  
														  if(val.caste_name!=undefined)
															  {
															  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
															  }
														 
														     //find out match score
														 	 var minrandom=getRandomInt(80,100);
														 	 var matchscore='<div><label for="matchscore" style="color:green;font-weight:bold;">Match Score: '+minrandom+'% </label>&nbsp;<progress value="'+minrandom+'" max="100" title="'+minrandom+' Complete">'+minrandom+'</progress></div>';
														 	 
														     str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+matchscore+' <ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"><i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank" ><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
															 
													
		
													});
																	
																		
												$("#containr").append(str);  
											}
											
										});
									}
		
								}
				}

			

        } else { 
            
            // The viewport is at least 768 pixels wide 

	$(window).scroll(function(){
							//Will check if the user has reached bottom of a PAGE
							
							//Check for user has reached bottom of Page
							
					if(Math.round($(window).scrollTop()) >=Math.round($(document).height()-window.innerHeight)){ 
						//$('#loading').fadeIn();
						//setTimeout("appendContent()", 1000);

						
						var lastpostid= $("#lastpostid").val(); 
						var searchid=$("#searchid").val();
						var paramtype=$("#paramtype").val();
						
						if(deskscrollflag==0)
						{
								deskscrollflag=1;									
								//console.log('loader');	
								//console.log("..load"+emptydeksdata);
								if(emptydeksdata==0)	
									{
										//$("#loaderspinn").fadeIn();
										
										$("body").loading();
									}
									
							
							if($("#advsrcstsval").val()==1)
								{
										var age_form=$("#age_form").val();
										var age_to=$("#age_to").val();
										var height_info_from_id=$("#height_info_from_id").val();
										var height_info_to_id=$("#height_info_to_id").val();
										
										/*life habits*/
										var body_type_Array = $('input[name="body_type"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(body_type_Array=="")
											{
											body_type_Array=0;
											}
										
										var complexion_Array = $('input[name="complexion"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(complexion_Array=="")
											{
											complexion_Array=0;
											}
										
										var physical_status_Array = $('input[name="physical_status"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(physical_status_Array=="")
											{
											physical_status_Array=0;
											}
										
										var eating_habits_Array = $('input[name="eating_habits"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(eating_habits_Array=="")
											{
											eating_habits_Array=0;
											}
										
										var drinking_habits_Array = $('input[name="drinking_habits"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(drinking_habits_Array=="")
											{
											drinking_habits_Array=0;
											}
										
										var smoking_habits_Array = $('input[name="smoking_habits"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(smoking_habits_Array=="")
											{
											smoking_habits_Array=0;
											}
										/*life habits*/
										
										var mothertongueArray = $('input[name="mothertongue"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(mothertongueArray=="")
											{
												mothertongueArray=0;
											}
										
										var religionadvsrnm = $('input[name="religionadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(religionadvsrnm=="")
											{
												religionadvsrnm=0;
											}
										
										var casteadvsrnm = $('input[name="casteadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(casteadvsrnm=="")
											{
												casteadvsrnm=0;
											}
										
										
										var maritalsts = $('input[name="maritalsts"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(maritalsts=="")
											{
												maritalsts=0;
											}
										
										var higheducadvsrnm = $('input[name="higheducadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(higheducadvsrnm=="")
											{
												higheducadvsrnm=0;
											}
											
										var annlincmadvsrnm = $('input[name="annlincmadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(annlincmadvsrnm=="")
											{
												annlincmadvsrnm=0;
											}
											
										var empinadvsrnm = $('input[name="empinadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(empinadvsrnm=="")
											{
												empinadvsrnm=0;
											}
											
										var occplistadvsrnm = $('input[name="occplistadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(occplistadvsrnm=="")
											{
												occplistadvsrnm=0;
											}
		
										var cntrylistadvsrnm = $('input[name="cntrylistadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(cntrylistadvsrnm=="")
											{
												cntrylistadvsrnm=0;
											}
		
										var statelistadvsrnm = $('input[name="statelistadvsrnm"]:checked').map(function () {  
									        return this.value;
									        }).get().join(",");
										
										if(statelistadvsrnm=="")
											{
												statelistadvsrnm=0;
											}	
										
										
										var datavalues= "lastpostid="+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm+"&body_type_Array="+body_type_Array+"&physical_status_Array="+physical_status_Array+"&eating_habits_Array="+eating_habits_Array+"&drinking_habits_Array="+drinking_habits_Array+"&smoking_habits_Array="+smoking_habits_Array+"&complexion_Array="+complexion_Array;
										surl="get_all_advanceregularsearchlist_after_reach_bottom_of_page";	
								}
							else{
								surl="get_all_regularsearchlist_after_reach_bottom_of_page";
								var datavalues='lastpostid='+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype;
							}
								
								
							$.ajax({
								url: surl,
								type: 'POST',
								cache: false,
								async: false,
								dataType: 'html',
								data: datavalues,
								success: function (data) {
									
										//$("#loaderspinn").fadeOut();
										
										
										setTimeout(function(){ $(":loading").loading("stop") }, 2000);
											
										
										deskscrollflag=0;
									
                                          		 var response = JSON.parse(data);
		                                          
                        				  		 var str='';
	                         				  	if (jQuery.isEmptyObject(response))						
	                                             {
	                         				  		emptydeksdata=1;													
	                                             }
                        				  		$.each(response, function (key, val) {
										
                        				  		 if(val.gender=='Female')
  		                      					  {
  		                      					 	// var demoimg=$("#demousrimg").val()+'demouser3.png';
  		                      					 	
						  		                      if(val.profile_image=='')
													  {
										 	 			var demoimg=$("#demousrimg").val()+'demouser3.png';
													  	
													  }
												  else
													  {
													  	var demoimg=$("#liveusrimg").val()+val.profile_image;
													  }
  		                      					 	
  		                      					     var likestr='Like Her';
  		                      					  }
  		                      				 	 else
  		                      					  {
  		                      					 	//var demoimg=$("#demousrimg").val()+'demouser2.jpg';
  		                      					 	
						  		                      if(val.profile_image=='')
													  {
												 		var demoimg=$("#demousrimg").val()+'demouser2.jpg';
													  	
													  }
												  else
													  {
													  	var demoimg=$("#liveusrimg").val()+val.profile_image;
													  }
  		                      					 	
  		                      					    var likestr='Like Him';
  		                      					  }
                        				  	  var callimg=$("#urlimg").val()+'call-icon.png';	
											  var cstname='';
											  var lastpostid=val.id;
											  $("#lastpostid").val(lastpostid);
											  
											  if(val.caste_name!=undefined)
												  {
												  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
												  }
											 
												 //find out match score
											 	 var minrandom=getRandomInt(80,100);
											 	 var matchscore='<div><label for="matchscore" style="color:green;font-weight:bold;">Match Score: '+minrandom+'% </label>&nbsp;<progress value="'+minrandom+'" max="100" title="'+minrandom+' Complete">'+minrandom+'</progress></div>';
											 	 
											  	 str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+matchscore+' <ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"><i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank" ><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
												
										
										

										});
														
															
									$("#containr").append(str);  
								}
								
							});
						
						}

		}
	});

}

	function getRandomInt (min, max) {
	    return Math.floor(Math.random() * (max - min + 1)) + min;
	}
	
	
	get_all_regularsearchlist_on_page_load();
	
	function get_all_regularsearchlist_on_page_load()
	{
		var searchid=$("#searchid").val();
		var paramtype=$("#paramtype").val();
		type='ajax';
		
		$.ajax({
			
			  url: "get_all_regularsearchlist_on_page_load",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&searchid="+searchid+"&paramtype="+paramtype,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				  var str='';
				  var count_data=0;	
				  $.each(response, function (key, val) {
					  count_data++;
					  if(val.gender=='Female')
					  {
					 	 //var demoimg=$("#demousrimg").val()+'demouser3.png';
					 	 
					 	  if(val.profile_image=='')
							  {
				 	 			var demoimg=$("#demousrimg").val()+'demouser3.png';
							  	
							  }
						  else
							  {
							  	var demoimg=$("#liveusrimg").val()+val.profile_image;
							  }
					 	  
					 	 var likestr='Like Her';
					  }
				  else
					  {
					 	//var demoimg=$("#demousrimg").val()+'demouser2.jpg';
					 	
					 	if(val.profile_image=='')
							  {
						 		var demoimg=$("#demousrimg").val()+'demouser2.jpg';
							  	
							  }
						  else
							  {
							  	var demoimg=$("#liveusrimg").val()+val.profile_image;
							  }
					 	
					 	var likestr='Like Him';
					  }
				  
					  var callimg=$("#urlimg").val()+'call-icon.png';
					  
					  var matchofdayimg=$("#urlimg").val()+"matchofday.png";
					  
					  var cstname='';
					  var lastpostid=val.id;
					  $("#lastpostid").val(lastpostid);
					  
					  if(val.caste_name!=undefined)
						  {
						  cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
						  }
					  	 //find out match score
					 	 var minrandom=getRandomInt(80,100);
					  	 if(key==0)
					  	  {
					  		 if(paramtype=="allpartnermatch")
					  			 {
					  			 var matchofdayimg='<p><img src="'+matchofdayimg+'"/></p>';
					  			 }else{
					  				var matchofdayimg="";
					  			 }
					  		 
					  	  }
					  	 else
					  	 {
					  		var matchofdayimg="";
					  	 }
					 	 var matchscore='<div>'+matchofdayimg+'<label for="matchscore" style="color:green;font-weight:bold;">Match Score: '+minrandom+'% </label>&nbsp;<progress value="'+minrandom+'" max="100" title="'+minrandom+' Complete">'+minrandom+'</progress></div>';
				          
						 str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+matchscore+' <ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"><i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank" ><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
						 
						 
					 
				  });
				  
				      if(count_data==0)
					  {
					
					 	 str='<p style="text-align: center;font-size: 17px;">No Record Found</p>';
					 	 $("#recmsg").html("");
					  }
				  
				  //console.log(str);
				  
				  $("#containr").html(str);
			  }
		});	  
	}
	
/*advance search in sidebar*/	
$(".advsrapply").click(function(){
	
		$("html, body").animate({ scrollTop: 0 }, "slow");
		
		var searchid=$("#searchid").val();
		var paramtype=$("#paramtype").val();
		type='ajax';
		$("#advsrcstsval").val(1);
		
		var age_form=$("#age_form").val();
		var age_to=$("#age_to").val();
		var height_info_from_id=$("#height_info_from_id").val();
		var height_info_to_id=$("#height_info_to_id").val();
		
		/*life habits*/
		var body_type_Array = $('input[name="body_type"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(body_type_Array=="")
			{
			body_type_Array=0;
			}
		
		var complexion_Array = $('input[name="complexion"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(complexion_Array=="")
			{
			complexion_Array=0;
			}
		
		var physical_status_Array = $('input[name="physical_status"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(physical_status_Array=="")
			{
			physical_status_Array=0;
			}
		
		var eating_habits_Array = $('input[name="eating_habits"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(eating_habits_Array=="")
			{
			eating_habits_Array=0;
			}
		
		var drinking_habits_Array = $('input[name="drinking_habits"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(drinking_habits_Array=="")
			{
			drinking_habits_Array=0;
			}
		
		var smoking_habits_Array = $('input[name="smoking_habits"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(smoking_habits_Array=="")
			{
			smoking_habits_Array=0;
			}
		/*life habits*/
		
		var mothertongueArray = $('input[name="mothertongue"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(mothertongueArray=="")
			{
				mothertongueArray=0;
			}
		
		var religionadvsrnm = $('input[name="religionadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(religionadvsrnm=="")
			{
				religionadvsrnm=0;
			}
		
		var casteadvsrnm = $('input[name="casteadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(casteadvsrnm=="")
			{
				casteadvsrnm=0;
			}
		
		
		var maritalsts = $('input[name="maritalsts"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(maritalsts=="")
			{
				maritalsts=0;
			}
		
		var higheducadvsrnm = $('input[name="higheducadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(higheducadvsrnm=="")
			{
				higheducadvsrnm=0;
			}
			
		var annlincmadvsrnm = $('input[name="annlincmadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(annlincmadvsrnm=="")
			{
				annlincmadvsrnm=0;
			}
			
		var empinadvsrnm = $('input[name="empinadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(empinadvsrnm=="")
			{
				empinadvsrnm=0;
			}
			
		var occplistadvsrnm = $('input[name="occplistadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(occplistadvsrnm=="")
			{
				occplistadvsrnm=0;
			}

		var cntrylistadvsrnm = $('input[name="cntrylistadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(cntrylistadvsrnm=="")
			{
				cntrylistadvsrnm=0;
			}

		var statelistadvsrnm = $('input[name="statelistadvsrnm"]:checked').map(function () {  
	        return this.value;
	        }).get().join(",");
		
		if(statelistadvsrnm=="")
			{
				statelistadvsrnm=0;
			}	
		$("body").loading();
		
		$.ajax({
			
			  url: "get_all_advanceregularsearchlist_on_page_load",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm+"&body_type_Array="+body_type_Array+"&physical_status_Array="+physical_status_Array+"&eating_habits_Array="+eating_habits_Array+"&drinking_habits_Array="+drinking_habits_Array+"&smoking_habits_Array="+smoking_habits_Array+"&complexion_Array="+complexion_Array,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  setTimeout(function(){ $(":loading").loading("stop") }, 2000);
				  
				  var response = JSON.parse(html);
				  var str='';
				  var count_data=0;	
				  $.each(response, function (key, val) {
					  count_data++;
					  
					  if(val.gender=='Female')
					  {
					 	 //var demoimg=$("#demousrimg").val()+'demouser3.png';
					 	 if(val.profile_image=='')
							  {
				 	 			var demoimg=$("#demousrimg").val()+'demouser3.png';
							  	
							  }
						  else
							  {
							  	var demoimg=$("#liveusrimg").val()+val.profile_image;
							  }
					 	 var likestr='Like Her';
					  }
				  else
					  {
					 	//var demoimg=$("#demousrimg").val()+'demouser2.jpg';
					 	if(val.profile_image=='')
							  {
						 		var demoimg=$("#demousrimg").val()+'demouser2.jpg';
							  	
							  }
						  else
							  {
							  	var demoimg=$("#liveusrimg").val()+val.profile_image;
							  }
					 	
					 	var likestr='Like Him';
					  }
				  
					  
					  var callimg=$("#urlimg").val()+'call-icon.png';
					  var cstname='';
					  var lastpostid=val.id;
					  $("#lastpostid").val(lastpostid);
					  
					  if(val.caste_name!=undefined)
						  {
						  cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
						  }
					 
						//find out match score
					 	 var minrandom=getRandomInt(80,100);
					 	 var matchscore='<div><label for="matchscore" style="color:green;font-weight:bold;">Match Score: '+minrandom+'% </label>&nbsp;<progress value="'+minrandom+'" max="100" title="'+minrandom+' Complete">'+minrandom+'</progress></div>';
					  
					  
						 str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm"><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Mother Tongue : </span> '+val.mother_tongue_name+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+matchscore+' <ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'" ><i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
						 
					 
				  });
				  
				      if(count_data==0)
					  {
					
					 	 str='<p style="text-align: center;font-size: 17px;">No Record Found</p>';
					 	 $("#recmsg").html("");
					  }
				  
				  //console.log(str);
				  
				  $("#containr").html(str);
			  }
		});
	})
	
	$("#baschdspn").click(function(){
		
		if($("#basicdetails").css("display")=="none")
			{
				$("#basicdetails").slideDown();
				$("#baschdspn").html("+");
			}
		else
			{
				$("#basicdetails").slideUp();
				$("#baschdspn").html("-");
			}
		
	})
	
	$("#religioushdspn").click(function(){
		
		if($("#religiousdetails").css("display")=="none")
			{
				$("#religiousdetails").slideDown();
				$("#religioushdspn").html("+");
			}
		else
			{
				$("#religiousdetails").slideUp();
				$("#religioushdspn").html("-");
			}
		
	})
	
	$("#profhdspn").click(function(){
		
		if($("#careerdetails").css("display")=="none")
			{
				$("#careerdetails").slideDown();
				$("#profhdspn").html("+");
			}
		else
			{
				$("#careerdetails").slideUp();
				$("#profhdspn").html("-");
			}
		
	})
	
	$("#lochdspn").click(function(){
		
		if($("#locationdetails").css("display")=="none")
			{
				$("#locationdetails").slideDown();
				$("#lochdspn").html("+");
			}
		else
			{
				$("#locationdetails").slideUp();
				$("#lochdspn").html("-");
			}
		
	})
	
	$("#otherhdspn").click(function(){
		
		if($("#otherdetails").css("display")=="none")
			{
				$("#otherdetails").slideDown();
				$("#otherhdspn").html("+");
			}
		else
			{
				$("#otherdetails").slideUp();
				$("#otherhdspn").html("-");
			}
		
	})
	
	if ($(window).width() < 767) {
       
       $(".advsdbar").prop("id","side-panel")
    }
	else
	{
		
	}
	if ($(window).width() < 767) {
		$(".advsrapply").click(function(){
			$("#side-panel-trigger").trigger("click");
		})
	}
})

$(document).on("click",".usr_snd_int",function(){
	
		var receiver_id_str=$(this).attr("id");
		var res = receiver_id_str.split("-");
		var receiver_id=res[1];
		var type='ajax';
		$.ajax({
			
			  url: "sendinterest",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&receiver_id="+receiver_id,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				 
				  $.each(response, function (key, val) {
					  if(val.status==1)
						  {
						  
						 	 swal("Good job!", "You have sent interest successfully", "success");
						  }
					  else
						  {
						  	swal("", "Interest has been sent already", "error");
						  }
				  })
			  }
		});

})
$(document).on("click",".usr_snd_shr",function(){
	
	var receiver_id_str=$(this).attr("id");
	var res = receiver_id_str.split("-");
	var receiver_id=res[1];
	//alert(receiver_id);
	var type='ajax';
	$.ajax({
		
		  url: "shortlist",
		  cache: false,
		  type: "POST",
		  data: "type="+type+"&receiver_id="+receiver_id,
		  async: false,
		  dataType: 'html',
		  //success callback
		  success: function(html){
			  
			  var response = JSON.parse(html);
			 
			  $.each(response, function (key, val) {
				  if(val.status==1)
					  {
					  
					 	 swal("Good job!", "You have shortlisted successfully", "success");
					  }
				  else
					  {
					  	swal("", "You have shortlisted already", "error");
					  }
			  })
		  }
	});
})

/*check if premium user*/
$(document).on("click",".chckmemprm",function(){
	
	var receiver_id_str=$(this).attr("id");
	var res = receiver_id_str.split("-");
	var receiver_id=res[1];
	//alert(receiver_id);
	var type='ajax';
	$.ajax({
		
		  url: "check_ifpremium_user",
		  cache: false,
		  type: "POST",
		  data: "type="+type+"&receiver_id="+receiver_id,
		  async: false,
		  dataType: 'html',
		  //success callback
		  success: function(html){
			  
			  var response = JSON.parse(html);
			 
			  $.each(response, function (key, val) {
				  if(val.premiumflag==1)
					  {
					  	var phone_no=val.phone_no;
					 	 swal("Phone No:", phone_no, "success");
					  }
				  else
					  {
					  	swal("", "Please take membership to view phone no.", "error");
					  }
			  })
		  }
	});
})
</script>
</body>
</html>