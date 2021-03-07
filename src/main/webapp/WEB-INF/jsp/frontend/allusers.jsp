<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html dir="ltr" lang="en">

<%@ include file="includes/headcss.jsp" %>
<script src="<c:url value="/resources/bodyloader/jquery.loading.js"/>"></script>
<link  rel="stylesheet" href="<c:url value="/resources/bodyloader/loading.css" />" >
<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<div class="body-overlay"></div>

<div id="wrapper" class="clearfix">
  <!-- preloader -->
  <%@ include file="includes/preloader.jsp" %>
  
  <!-- Header -->
  
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content mgtop1" >
    <!-- Section: inner-header -->
	<% 
				
				int renewal_status=0;
				try
				{
					renewal_status=Integer.parseInt(request.getAttribute("renewal_status").toString());
				}
				catch(Exception e)
				{
					renewal_status=0;
				}
	%>
	
	<input type="hidden" value="<%=renewal_status %>" id="renewal_status"/>
	
    <!-- Section: Bridesmaid -->
    <section>
      
        <input type="hidden" id="demousrimg" value="<c:url value="/resources/images/userimages/" />"/>
        <input type="hidden" id="liveusrimg" value="<c:url value="/resources/userprofileimages/" />"/>
        <input type="hidden" id="urlimg" value="<c:url value="/resources/images/" />"/>
        <input type="hidden" id="lastpostid" value="0"/>
         
      <div class="container ">
      
      <div id="side-panel-trigger" class="side-panel-trigger" style="display:none"><a href="#"><i class="fa fa-filter" aria-hidden="true" class="advfiltermnu" style="color: #ff9902;font-size: 45px;"></i></a></div>
      
      <p class="srhdlne">All Registered Members </p>
      
      <div class="row">
      
           <div class="col-sm-12 col-md-9 blog-pull-right prfboxshd" style="padding:20px">
            
              <div class="row  prfboxshd bordrbox" id="containr">
              
              
              
                <!--  <article class="post clearfix mb-50">
                  <div class="col-sm-3">
                    <div class="entry-header">
                      <div class="post-thumb"> <img class="img-responsive img-fullwidth" src="https://placehold.it/460x240" alt=""> </div>
                    </div>
                  </div>
                  <div class="col-sm-9">
                    <div class="entry-content mt-0">
                      <a href="#">
                      <h4 class="entry-title mt-0 pt-0" class="usr_fnm">Sonali</h4>
                      </a>
                      <ul class="list-inline font-12 mb-20 mt-10">
                        <li> <a href="#" class="usr_desg">Software Engineer</a></li>
                        
                      </ul>
                      <p class="mb-30">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna et sed aliqua <a href="#">[...]</a></p>
                      <ul class="list-inline like-comment pull-left font-12">
                        <li><a href="javascript:void(0)" class="usr_snd_int">Send Interest</a></li>
                        <li><a href="javascript:void(0)" class="usr_snd_shr">Shortlist</a></li>
                        <li><a href="javascript:void(0)" class="usr_snd_blc">Block</a></li>
                      </ul>
                      <a class="pull-right text-gray font-13" href="#"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a>
                    </div>
                  </div>
                </article>-->
                
                
              </div>
              	<div class="process-comm"  id="loaderspinn" style="display:none;text-align:center" >
        		<i class="fa fa-cog fa-spin" style="font-size:48px;color:red"></i>
        		</div>
            </div>
            
            <% 
				
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
            
            
            
		         <div class="col-sm-12 col-md-3 advsdbar" >
	            <div class="sidebar sidebar-left mt-sm-30">
	            		<p class="advsrhedg">  Basic Details <span id="baschdspn" class="baschdspn">+</span></p>
			            <div class="basicdetails comtab" id="basicdetails">
					          	
					            <div class="widget" id="baschdwidget">
					                <h5 class="widget-title line-bottom">Age (in years)</h5>
					               
					                
						                <select name="age_form" class="form-control ageadvsr" id="age_form">
																			 
													 <% for(int j=18; j<=70; j++) { %>
												 	<option value="<%= j %>"><%= j %></option>
												 	<% } %>
												 	
																				
										</select>
										
										<select name="age_to" class="form-control ageadvsr" id="age_to">
																			 	
											 	<% for(int j=18; j<=70; j++) { %>
											 	<option value="<%= j %>" <%if(j==60){ %>selected="selected"<% }%> ><%= j %></option>
											 	
											 	<% } %>
																			 	 
										</select>
										
										<div class="clrfix"></div>
					               
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Height</h5>
					               
					                
						                <select name="height_info_from_id" class="form-control ageadvsr" id="height_info_from_id">
																			 
													 <c:forEach items="${heightlisthashmap}" var="heightlist">
																	<option value="${heightlist.key}" <c:if test="${heightlist.key == 1}"> selected="selected" </c:if> >${heightlist.value}</option>
													</c:forEach>
												 	
																				
										</select>
										
										<select name="height_info_to_id" class="form-control ageadvsr" id="height_info_to_id">
																			 	
											 	<c:forEach items="${heightlisthashmap}" var="heightlist">
																	<option value="${heightlist.key}" <c:if test="${heightlist.key == 31}"> selected="selected" </c:if> >${heightlist.value}</option>
												</c:forEach>
																			 	 
										</select>
										
										<div class="clrfix"></div>
					               
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					            
					            
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Mother Tongue</h5>
					                <div class="reladvsr">
					                
					                
						                <c:forEach items="${get_mother_tongue_list_with_count}" var="mothertongue">
						                	<input type="checkbox" name="mothertongue" value="${mothertongue.id}"/> ${mothertongue.itemname}  (${mothertongue.countitem})<br/>
										
										</c:forEach>
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Marital status</h5>
					                <div class="reladvsr">
					                
						               <c:forEach items="${get_maritalstatus_list_with_count}" var="maritalsts">
						                	<input type="checkbox" name="maritalsts" value="${maritalsts.id}"/> ${maritalsts.itemname}  (${maritalsts.countitem})<br/>
										
										</c:forEach>
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
			              
			              
			         
			            </div>
	            
	            			<p class="advsrhedg">  Religious Details <span id="religioushdspn" class="baschdspn">-</span></p>
					       <div class="religiousdetails comtab" id="religiousdetails" style="display:none">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Religion</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${get_religion_list_with_count}" var="religion">
						                	<input type="checkbox" name="religionadvsrnm" value="${religion.id}"/> ${religion.itemname}  (${religion.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					              
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Caste</h5>
					                <div class="reladvsr">
					                
					               		 <c:forEach items="${get_caste_list_with_count}" var="caste1">
						                	<input type="checkbox" name="casteadvsrnm" value="${caste1.id}"/> ${caste1.itemname}  (${caste1.countitem})<br/>
										
										</c:forEach>
										
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					         
					            </div>
					          
					          
					       </div>
					       
					       
					       <p class="advsrhedg">  Professional Details <span id="profhdspn" class="baschdspn">-</span></p>
					       <div class="careerdetails comtab" style="display:none" id="careerdetails">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Highest Education</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${get_highest_education_list_with_count}" var="higheduc">
						                	<input type="checkbox" name="higheducadvsrnm" value="${higheduc.id}"/> ${higheduc.itemname}  (${higheduc.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Annual Income</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${annualincome_list_with_count}" var="annlincm">
						                	<input type="checkbox" name="annlincmadvsrnm" value="${annlincm.id}"/> ${annlincm.itemname}  (${annlincm.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					          
					           <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Employed In</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${employedin_list_with_count}" var="empin">
						                	<input type="checkbox" name="empinadvsrnm" value="${empin.id}"/> ${empin.itemname}  (${empin.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Occupation</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${occupation_list_with_count}" var="occplist">
						                	<input type="checkbox" name="occplistadvsrnm" value="${occplist.id}"/> ${occplist.itemname}  (${occplist.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					          
					          
					          
					       </div>
					       
					       <p class="advsrhedg">  Location Details <span id="lochdspn" class="baschdspn">-</span></p>
	            		<div class="locationdetails comtab" id="locationdetails" style="display:none">
					          
					          
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">Country</h5>
					                <div class="">
					                
					                	<c:forEach items="${country_list_with_count}" var="cntrylist">
						                	<input type="checkbox" name="cntrylistadvsrnm" value="${cntrylist.id}"/> ${cntrylist.itemname}  (${cntrylist.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            <div class="sidebar sidebar-left mt-sm-30">
					            
					            
					              <div class="widget">
					                <h5 class="widget-title line-bottom">State</h5>
					                <div class="reladvsr">
					                
					                	<c:forEach items="${state_list_with_count}" var="statelist">
						                	<input type="checkbox" name="statelistadvsrnm" value="${statelist.id}"/> ${statelist.itemname}  (${statelist.countitem})<br/>
										
										</c:forEach>
					                
						                
										
					                </div>
					                <div class="advsrapplydv"> <a href="javascript:void(0)" class="text-theme-colored font-weight-600 font-12 advsrapply">Apply</a></div>
					               
					              </div>
					              
					             
					         
					            </div>
					            
					            
					          
					          
						</div>
	            
	            
	          </div>
          
          </div>
            
        </div>
      </div>
    </section>
  </div>
  <!-- end main-content -->
  <input type="hidden" name="advsrcstsval" id="advsrcstsval" value="0"/>
  <input type="hidden" name="searchid" id="searchid" value="0"/>
  <input type="hidden" name="paramtype" id="paramtype" value="<%=paramtype%>"/>

  <!-- Footer -->
  
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>


<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>




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
												
												
												var datavalues= "lastpostid="+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm;
												surl="get_all_advanceregularsearchlist_after_reach_bottom_of_page";	
										}
									else{
										surl="get_all_userlist_after_reach_bottom_of_page";
										var datavalues='lastpostid='+lastpostid+"&paramtype="+paramtype;
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
														 
														 
																 str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm"><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"> <i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank" ><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
															
													
													
		
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
							
							
					if(Math.round($(window).scrollTop()) >=Math.round($(document).height()-window.innerHeight)){
						//$('#loading').fadeIn();
						//setTimeout("appendContent()", 1000);

						
						var lastpostid= $("#lastpostid").val(); 
						var searchid=$("#searchid").val();
						var paramtype=$("#paramtype").val();
						
						if(deskscrollflag==0)
						{
								deskscrollflag=1;									
								console.log('loader');								
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
										
										
										var datavalues= "lastpostid="+lastpostid+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm;
										surl="get_all_advanceregularsearchlist_after_reach_bottom_of_page";	
										
								}
							else{
								surl="get_all_userlist_after_reach_bottom_of_page";
								var datavalues='lastpostid='+lastpostid+"&paramtype="+paramtype;
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
											 
												 	
											str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm"><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"> <i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
												 
											 

										});
														
															
									$("#containr").append(str);  
								}
								
							});
						
						}

		}
	});

}
	
	
	
	get_all_userlist_on_page_load();
	
	function get_all_userlist_on_page_load()
	{
		type='ajax';
		var paramtype=$("#paramtype").val();
		$.ajax({
			
			  url: "get_all_userlist_on_page_load",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&paramtype="+paramtype,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				  
				  var str='';
				
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
					 	 		//console.log(demoimg);
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
					 
						str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm"><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"> <i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
						 
					 
				  });
				  
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
				  data: "type="+type+"&searchid="+searchid+"&paramtype="+paramtype+"&mothertongueArray="+mothertongueArray+"&religionadvsrnm="+religionadvsrnm+"&casteadvsrnm="+casteadvsrnm+"&age_form="+age_form+"&age_to="+age_to+"&height_info_from_id="+height_info_from_id+"&height_info_to_id="+height_info_to_id+"&maritalsts="+maritalsts+"&higheducadvsrnm="+higheducadvsrnm+"&annlincmadvsrnm="+annlincmadvsrnm+"&empinadvsrnm="+empinadvsrnm+"&occplistadvsrnm="+occplistadvsrnm+"&cntrylistadvsrnm="+cntrylistadvsrnm+"&statelistadvsrnm="+statelistadvsrnm,
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
						 
							 str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Mother Tongue : </span> '+val.mother_tongue_name+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+' </a></li><li><a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-'+val.id+'"><i class="fa fa-star" aria-hidden="true"></i> Shortlist</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
							 
						 
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
		$(".comtab").slideUp(); //close all the tabs
		$(".baschdspn").html("-");
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
		$(".comtab").slideUp(); //close all the tabs
		$(".baschdspn").html("-");
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
		$(".comtab").slideUp(); //close all the tabs
		$(".baschdspn").html("-");
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
		$(".comtab").slideUp(); //close all the tabs
		$(".baschdspn").html("-");
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
<% if(renewal_status==1) {%>
<button id="myBtnRenew" style="display:none" >Open popup</button>
<div class="modal" id="myModalRenew">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header ofrmodhd1">
          
          <button type="button" class="close ofrmodhdcls" data-dismiss="modal"><!--&times;  -->&nbsp;&nbsp;&nbsp;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body ofrmodbd1"  style="background-image:url(<c:url value="/resources/images/renew/renew.png" />);">
          <h4 style="" class="discnth2">Your Membership Expired<br>
			 Special Offer 85% Discount! <br/>
				<a href="packagedetails" class="btn btn-success btn-sm"> Renew Now</a><img src="<c:url value="/resources/images/membership/redrose.jpg" />" style="width:28%;"/>
				
				</h4> 
      		
      		
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer ofrmodft1">
          <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" id="renewCloseModal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
<%} %>
<script>
$(document).ready(function(){
	//Renewal popup
	/*########Discount modal popup################*/
	
	var renewal_status=$("#renewal_status").val();
	
	if(renewal_status==1)
		{
				var modal = document.getElementById("myModalRenew");
				
				// Get the button that opens the modal
				var btn = document.getElementById("myBtnRenew");
				
				// Get the <span> element that closes the modal
				var span = document.getElementsByClassName("close")[0];
				
				// When the user clicks the button, open the modal 
				btn.onclick = function() {
				  modal.style.display = "block";
				}
				
				// When the user clicks on <span> (x), close the modal
				span.onclick = function() {
				  modal.style.display = "none";
				}
				
				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
				  if (event.target == modal) {
				    modal.style.display = "none";
				  }
				}
				
				if(sessionStorage.getItem('popRenewState') != 'shown'){
					
					setTimeout(function(){ $("#myBtnRenew").trigger("click") }, 3000);
					
					
			        sessionStorage.setItem('popRenewState','shown')
			    }
				
				
				/*########Discount modal popup################*/
				
				$('#renewCloseModal').click(function() {
				    $('.close').trigger('click');
				});
		}
		// Get the modal
	
});

</script>
</body>
</html>