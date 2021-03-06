<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html dir="ltr" lang="en">
<%@ page import="java.util.List" %>
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

    <!-- Section: Bridesmaid -->
    <section>
      
        <input type="hidden" id="demousrimg" value="<c:url value="/resources/images/userimages/" />"/>
        <input type="hidden" id="liveusrimg" value="<c:url value="/resources/userprofileimages/" />"/>
        <input type="hidden" id="urlimg" value="<c:url value="/resources/images/" />"/>
        <input type="hidden" id="lastpostid" value="0"/>
         
      <div class="container ">
      
      
      
      
      <p style="font-size: 20px;color: #212529;letter-spacing: 1.5px;text-align: left;">All Shortlisted Members </p>
      
      <div class="row">
      
           <div class="col-sm-12 col-md-9 blog-pull-right prfboxshd" style="padding:20px">
            
              <div class="row prfboxshd bordrbox" id="containr">
              
              
                
              </div>
              
              
              
              	<div class="process-comm"  id="loaderspinn" style="display:none;text-align:center" >
        		<i class="fa fa-cog fa-spin" style="font-size:48px;color:red"></i>
        		</div>
            </div>
            
            <div class="col-sm-12 col-md-3 advsdbar mailboxbar shrtbar" >
			            <div class="sidebar sidebar-left mt-sm-30">
			            	      
							<div class="widget">
				                <%
				                	String type=request.getParameter("type");
				                %>
				                <ul class="list list-divider list-border">
				                  <li><a href="shortlistedbyyou?type=tab1" class="mailboxbartxt <% if(type.equals("tab1")){ %> mailbxsel <% } %>" >Shortlist</a></li>
				                  
				                  <li><a href="chat" class="mailboxbartxt"  >Online Members</a></li>
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
										
										
									
										surl="get_all_usershortlist_after_reach_bottom_of_page";
										var datavalues='lastpostid='+lastpostid;
									
										
										
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
														  var lastpostid=val.shortlist_id;
														  $("#lastpostid").val(lastpostid);
														  
														  if(val.caste_name!=undefined)
															  {
															  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
															  }
														 
														 
														  str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
															 
													
													
		
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
									
								
							
								surl="get_all_usershortlist_after_reach_bottom_of_page";
								var datavalues='lastpostid='+lastpostid;
							
							
							
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
											  var lastpostid=val.shortlist_id;
											  $("#lastpostid").val(lastpostid);
											  
											  if(val.caste_name!=undefined)
												  {
												  	cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
												  }
											 
											 
												 	
											  str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
												
										
										

										});
														
															
									$("#containr").append(str);  
								}
								
							});
						
						}

		}
	});

}
	
	
	
	get_all_usershortlist_on_page_load();
	
	function get_all_usershortlist_on_page_load()
	{
		type='ajax';
		
		$.ajax({
			
			  url: "get_all_usershortlist_on_page_load",
			  cache: false,
			  type: "POST",
			  data: "type="+type,
			  async: false,
			  dataType: 'html',
			  //success callback
			  success: function(html){
				  
				  var response = JSON.parse(html);
				  
				  var str='';
				  
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
					  var lastpostid=val.shortlist_id;
					  //console.log("lastpostid"+lastpostid);
					  $("#lastpostid").val(lastpostid);
					  
					  if(val.caste_name!=undefined)
						  {
						  cstname+=" ,<span class='texthdbld'> Caste : </span>"+val.caste_name;
						  }
					 
					
						 	str+='<article class="post clearfix mb-50"><div class="col-sm-2"><div class="entry-header"><div class="post-thumb"> <img class="img-responsive img-fullwidth rspnsurimg" src="'+demoimg+'" alt=""> </div></div></div><div class="col-sm-10"><div class="entry-content mt-0"><a href="javascript:void(0)"><h4 class="entry-title mt-0 pt-0 usr_fnm">'+val.name+'<a href="javascript:void(0)" id="chckmemprm-'+val.id+'" class="chckmemprm" ><img src="'+callimg+'" class="callimgg"/></a></h4></a><ul class="list-inline font-12 mb-20 mt-10"><li> <a href="javascript:void(0)" class="usr_desg">'+val.occupation_name+'</a></li></ul><p class="mb-30"><span class="texthdbld"> Date of birth  : </span> '+val.dob+'  , <span class="texthdbld"> Religion : </span> '+val.religion_name+cstname+' ,<span class="texthdbld"> Education  : </span>'+val.highest_education+'  , <span class="texthdbld"> State  : </span> '+ val.state_name +' ,<span class="texthdbld"> City : </span>'+val.city_name+'</p><ul class="list-inline like-comment pull-left font-12"><li><a href="javascript:void(0)" class="usr_snd_int" id="interest-'+val.id+'">'+likestr+'</a></li><li><a href="chatwithmember?receiverid='+val.id+'" target="_blank" class="usr_snd_mssg">Message </a></li></ul><a class="pull-right text-gray font-13" href="profiledetails?usermatrimonyid='+val.matrimony_id+'" target="_blank"><i class="fa fa-angle-double-right text-theme-colored"></i> View Profile</a></div></div></article>';
						 
						 
					 
				  });
				  
				  //console.log(str);
				  
				  $("#containr").html(str);
				  
				  if (jQuery.isEmptyObject(response))						
				   {
					  $("#containr").html('<div class="post clearfix mb-50"><p style="text-align: center;font-size: 17px;">No Record Found</p></div>');												
				   }
			  }
		});	  
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