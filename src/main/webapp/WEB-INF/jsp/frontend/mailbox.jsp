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
  
  <% String type=request.getParameter("type"); %>
  
  <input type="hidden" name="inboxtype" id="inboxtype" value="<%=type %>"/>
  <!-- Start main-content -->
  <div class="main-content mgtop1" >
    <!-- Section: inner-header -->

    <!-- Section: Bridesmaid -->
    <section>
      
        <input type="hidden" id="demousrimg" value="<c:url value="/resources/images/userimages/" />"/>
        <input type="hidden" id="liveusrimg" value="<c:url value="/resources/userprofileimages/" />"/>
        <input type="hidden" id="urlimg" value="<c:url value="/resources/images/" />"/>
        <input type="hidden" id="lastpostid" value="0"/>
        <input type="hidden" id="sessionuid" name="sessionuid" value="${sessionuid }"/>
         
      <div class="container ">
      
      
      <div class="mailboxsbmobul" style="display:none">
                 <div class="mailboxsbmobli"><a href="mailbox?type=pending" class="mailboxsbmob <% if(type.equals("pending")){ %> mailbxselmob <% } %>"  >Pending</a></div>
                 <div class="mailboxsbmobli"><a href="mailbox?type=accepted" class="mailboxsbmob <% if(type.equals("accepted")){ %> mailbxselmob <% } %>"> Accepted</a></div>
                 <div class="mailboxsbmobli"><a href="mailbox?type=declined" class="mailboxsbmob <% if(type.equals("declined")){ %> mailbxselmob <% } %>"> Declined</a></div>
                 	                  
	   </div>
      
      
      <div class="row">
      
           <div class="col-sm-12 col-md-9 blog-pull-right prfboxshd" style="padding:20px">
            
              <div class="row list-dashed prfboxshd bordrbox" id="containr">
              
              </div>
              	<div class="process-comm"  id="loaderspinn" style="display:none;text-align:center" >
        		<i class="fa fa-cog fa-spin" style="font-size:48px;color:red"></i>
        		</div>
            </div>
            
		         <div class="col-sm-12 col-md-3 advsdbar mailboxbar" >
			            <div class="sidebar sidebar-left mt-sm-30">
			            	      
							<div class="widget">
				                <h5 class="widget-title line-bottom mailboxbartxt">Inbox</h5>
				                <ul class="list list-divider list-border">
				                  <li><a href="mailbox?type=pending" class="mailboxbartxt <% if(type.equals("pending")){ %> mailbxsel <% } %>"  >Pending</a></li>
				                  <li><a href="mailbox?type=accepted" class="mailboxbartxt <% if(type.equals("accepted")){ %> mailbxselmob <% } %>"> Accepted</a></li>
				                  <li><a href="mailbox?type=declined" class="mailboxbartxt <% if(type.equals("declined")){ %> mailbxselmob <% } %>"> Declined</a></li>
				                  
				                </ul>
				                
				                
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
<input type="hidden" name="paramtype" id="paramtype" value="0"/>
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
										
										
										type=$("#inboxtype").val();
										surl="get_all_mailbox_after_reach_bottom_of_page";
										var datavalues='lastpostid='+lastpostid+"&type="+type;
									
										
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
													 	 var gndr="She";
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
													 	var gndr="He";
													  }
														  
														  
														 var callimg=$("#urlimg").val()+'call-icon.png';
														  var cstname='';
														  
														  
														  if(val.caste_name!=undefined)
															  {
															  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
															  }
														 
														 
															 const months = ["JAN", "FEB", "MAR","APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
															 if(type=="pending")
														 		{
														 		     var lastpostid=val.sendinterestid;
																  	$("#lastpostid").val(lastpostid);
																  	
														 			let current_datetime = new Date(val.created_date)
														 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
														 		
														 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><p class="pendmsgmbx">'+gndr+' is interested in your profile. Would you like to communicate further? <span class="mailbxcrdt">'+formatted_date+'</span></p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="accept-'+val.sendinterestid+'">Accept</a></li><li><a href="javascript:void(0)" class="usr_decl" id="decline-'+val.sendinterestid+'">  Decline</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
														 		}
														 
														 	else if(type=="accepted")
													 		{
														 		var lastpostid=val.order_by_no;
															  	$("#lastpostid").val(lastpostid);
															  	
													 			let current_datetime = new Date(val.created_date)
													 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
													 		
													 			var sessionuid=$("#sessionuid").val();
													 			
														 		if(val.receiver_id==sessionuid)
														 			{
														 			 var accptxt='<p class="pendmsgmbx">You have accepted  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
														 			}
														 		else
														 			{
														 			var accptxt='<p class="pendmsgmbx">'+gndr+' has accepted your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
														 			}
													 			
													 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
													 		}
														 	
														 	else if(type=="declined")
													 		{
														 		var lastpostid=val.order_by_no;
															  	$("#lastpostid").val(lastpostid);
														 		
													 			let current_datetime = new Date(val.created_date)
													 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
													 		
													 			var sessionuid=$("#sessionuid").val();
													 			
														 		if(val.receiver_id==sessionuid)
														 			{
														 			 var accptxt='<p class="pendmsgmbx">You have declined  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
														 			}
														 		else
														 			{
														 			var accptxt='<p class="pendmsgmbx">'+gndr+' has declined your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
														 			}
													 			
													 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
													 		}
															 
													
													
		
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
									
								type=$("#inboxtype").val();
								surl="get_all_mailbox_after_reach_bottom_of_page";
								var datavalues='lastpostid='+lastpostid+"&type="+type;
							
							
							
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
                        							 	 var gndr="She";
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
                        							 	var gndr="He";
                        							  }
                        				  	  var callimg=$("#urlimg").val()+'call-icon.png';	
											  var cstname='';
											  
											  
											  if(val.caste_name!=undefined)
												  {
												  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
												  }
											 
											 
												 	
												 const months = ["JAN", "FEB", "MAR","APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
												 if(type=="pending")
											 		{
											 		     var lastpostid=val.sendinterestid;
													  	$("#lastpostid").val(lastpostid);
													  	
											 			let current_datetime = new Date(val.created_date)
											 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
											 		
											 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><p class="pendmsgmbx">'+gndr+' is interested in your profile. Would you like to communicate further? <span class="mailbxcrdt">'+formatted_date+'</span></p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="accept-'+val.sendinterestid+'">Accept</a></li><li><a href="javascript:void(0)" class="usr_decl" id="decline-'+val.sendinterestid+'">  Decline</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
											 		}
											 
											 	else if(type=="accepted")
										 		{
											 		var lastpostid=val.order_by_no;
												  	$("#lastpostid").val(lastpostid);
												  	
										 			let current_datetime = new Date(val.created_date)
										 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
										 		
										 			var sessionuid=$("#sessionuid").val();
										 			
											 		if(val.receiver_id==sessionuid)
											 			{
											 			 var accptxt='<p class="pendmsgmbx">You have accepted  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
											 			}
											 		else
											 			{
											 			var accptxt='<p class="pendmsgmbx">'+gndr+' has accepted your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
											 			}
										 			
										 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
										 		}
											 	
											 	else if(type=="declined")
										 		{
											 		var lastpostid=val.order_by_no;
												  	$("#lastpostid").val(lastpostid);
											 		
										 			let current_datetime = new Date(val.created_date)
										 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
										 		
										 			var sessionuid=$("#sessionuid").val();
										 			
											 		if(val.receiver_id==sessionuid)
											 			{
											 			 var accptxt='<p class="pendmsgmbx">You have declined  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
											 			}
											 		else
											 			{
											 			var accptxt='<p class="pendmsgmbx">'+gndr+' has declined your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
											 			}
										 			
										 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
										 		}
												 
										
										

										});
														
															
									$("#containr").append(str);  
								}
								
							});
						
						}

		}
	});

}
	
	
	
	get_all_mailbox_on_page_load();
	
	function get_all_mailbox_on_page_load()
	{
		type=$("#inboxtype").val();
		//alert(type);
		$.ajax({
			
			  url: "get_all_mailbox_on_page_load",
			  cache: false,
			  type: "POST",
			  data: "type="+type,
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
					 	 var gndr="She";
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
					 	var gndr="He";
					  }
				  
					  var callimg=$("#urlimg").val()+'call-icon.png';
					  
					  var cstname='';
					 
					  
					  if(val.caste_name!=undefined)
						  {
						  cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
						  }
					 
					 
						 	const months = ["JAN", "FEB", "MAR","APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
						 	if(type=="pending")
						 		{
						 		     var lastpostid=val.sendinterestid;
								  	$("#lastpostid").val(lastpostid);
								  	
						 			let current_datetime = new Date(val.created_date)
						 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
						 		
						 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><p class="pendmsgmbx">'+gndr+' is interested in your profile. Would you like to communicate further? <span class="mailbxcrdt">'+formatted_date+'</span></p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="accept-'+val.sendinterestid+'">Accept</a></li><li><a href="javascript:void(0)" class="usr_decl" id="decline-'+val.sendinterestid+'">  Decline</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
						 		}
						 
						 	else if(type=="accepted")
					 		{
						 		var lastpostid=val.order_by_no;
							  	$("#lastpostid").val(lastpostid);
							  	
					 			let current_datetime = new Date(val.created_date)
					 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
					 		
					 			var sessionuid=$("#sessionuid").val();
					 			
						 		if(val.receiver_id==sessionuid)
						 			{
						 			 var accptxt='<p class="pendmsgmbx">You have accepted  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
						 			}
						 		else
						 			{
						 			var accptxt='<p class="pendmsgmbx">'+gndr+' has accepted your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
						 			}
					 			
					 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
					 		}
						 	
						 	else if(type=="declined")
					 		{
						 		var lastpostid=val.order_by_no;
							  	$("#lastpostid").val(lastpostid);
						 		
					 			let current_datetime = new Date(val.created_date)
					 			let formatted_date = current_datetime.getDate() + "-" + months[current_datetime.getMonth()] + "-" + current_datetime.getFullYear();
					 		
					 			var sessionuid=$("#sessionuid").val();
					 			
						 		if(val.receiver_id==sessionuid)
						 			{
						 			 var accptxt='<p class="pendmsgmbx">You have declined  interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
						 			}
						 		else
						 			{
						 			var accptxt='<p class="pendmsgmbx">'+gndr+' has declined your interest. <span class="mailbxcrdt">'+formatted_date+'</span></p>';
						 			}
					 			
					 			str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p> '+accptxt+' <ul class="list-inline like-comment pull-left font-12"><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
					 		}
						 
						 
						
					 
				  });
				  
				  if(count_data==0)
				  {
				
				 	 str='<p style="text-align: center;font-size: 17px;">No Record Found</p>';
				 	 
				  }
				  
				  
				  $("#containr").html(str);
			  }
		});	  
	}
	
	
})

$(document).on("click",".usr_snd_int",function(){
	
		var sendinterestid_str=$(this).attr("id");
		var res = sendinterestid_str.split("-");
		var sendinterestid=res[1];
		var type='ajax';
		$.ajax({
			
			  url: "acceptinterest",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&sendinterestid="+sendinterestid,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				 
				  $.each(response, function (key, val) {
					  if(val.status==1)
						  {
						  
						 	 //swal("Good job!", "You have accepted successfully", "success");
							  swal({
								    title: "Wow!",
								    text: "You have accepted successfully !",
								    type: "success"
								}).then(function() {
								    location.reload();
								});
						  }
					  else
						  {
						  	//swal("", "Interest has been sent already", "error");
						  }
				  })
			  }
		});

})


$(document).on("click",".usr_decl",function(){
	
		var sendinterestid_str=$(this).attr("id");
		var res = sendinterestid_str.split("-");
		var sendinterestid=res[1];
		var type='ajax';
		$.ajax({
			
			  url: "declineinterest",
			  cache: false,
			  type: "POST",
			  data: "type="+type+"&sendinterestid="+sendinterestid,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				 
				  $.each(response, function (key, val) {
					  if(val.status==1)
						  {
						  
						 	 //swal("Good job!", "You have accepted successfully", "success");
							  swal({
								    title: "Sorry!",
								    text: "You have declined successfully !",
								    type: "success"
								}).then(function() {
								    location.reload();
								});
						  }
					  else
						  {
						  	//swal("", "Interest has been sent already", "error");
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
</script>


</body>
</html>