<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.beans.Common_Info" %> 
  <link  rel="stylesheet" href="<c:url value="resources/css/mycustominnerf9e3.css?v=6.5" />" >
<% 
int userid=0;
try
{
	
	if(session.getAttribute("sess_usr_id").toString()!=null)
	{
		 userid=Integer.parseInt(session.getAttribute("sess_usr_id").toString());
		
	
	}
	
}catch(Exception e)
{
	
}

%>   
<% 
String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";     
%>  
<header id="header" class="header">
    <div class="header-nav navbar-fixed-top header-dark navbar-white navbar-transparent navbar-sticky-animated animated-active headerbckcol">
      <div class="header-nav-wrapper">
        <div class="container">
          <nav>
            <div id="menuzord-right" class="menuzord red">  <a class=" pull-left flip font-great-vibes " href="<%=Common_Info.liveurl %>">  <img src="<c:url value="/resources/images/logo.png" />" class="matsitelogo" alt=""/></a>
              <div id="side-panel-trigger" class="side-panel-trigger"><a href="#"><i class="fa fa-bars font-24 text-gray-silver" style="display:none"></i></a></div>
              <ul class="menuzord-menu">
              
              <!-- class="active" -->
              
                <li ><a href="<%=Common_Info.liveurl %>">Home</a>
                  
                </li>
                
               <!--  <li><a href="javascript:void(0)">Pages</a>
			                  <ul class="dropdown">
			                    <li><a href="aboutus">About Us</a></li>
			                    <li><a href="contactus">Contact Us</a></li>
			                     <li><a href="terms">Terms And Conditions</a></li>
			                     <li><a href="privacy-policy">Privacy Policy</a></li>
			                     <li><a href="pricing">Pricing</a></li>
			                    <li><a href="cancel_policy">Refund & Cancellation Policy</a></li>
			                  </ul>
				</li> -->
                
                
                <% if(userid==0){ %>
                
                <li><a href="javascript:void(0)">Pages</a>
			                  <ul class="dropdown">
			                    <li><a href="aboutus">About Us</a></li>
			                    <li><a href="contactus">Contact Us</a></li>
			                    <li><a href="terms">Terms And Conditions</a></li>
			                    <li><a href="privacy-policy">Privacy Policy</a></li>
			                    <li><a href="pricing">Pricing</a></li>
			                    <li><a href="cancel_policy">Refund & Cancellation Policy</a></li>
			                  </ul>
				</li>
                <li><a href="register">Register</a></li>
                <li><a href="login">Login</a></li>
                
                <% } else { %>
                
               <li><a href="javascript:void(0)">My Profile</a> 
			                  <ul class="dropdown">
			                    <li><a href="profiledetails">View Profile </a></li>
			                    <li><a href="editprofile">Edit Profile </a></li>
			                    <li><a href="profilepicupload"> Profile Picture Upload</a></li>
			                    <li><a href="partnerpreferences">Edit Partner Preferences </a></li>
			                    <li><a href="shortlistedbyyou?type=tab1">Shortlisted By You </a></li>
			                    
			                  </ul>
				</li>
				
				<li><a href="javascript:void(0)">Chat <span class="msg_notify_hd_count"></span></a>
			                  <ul class="dropdown">
			                    <li><a href="chat">Online Chat</a></li>
			                    <li><a href="mymessages?pageid=1">My Messages <span class="msg_notify_hd_count"></span></a></li>
			                  </ul>
				</li>
				
				<li><a href="javascript:void(0)">Payment</a>
			                  <ul class="dropdown">
			                    <li><a href="packagedetails">Payment</a></li>
			                     <li><a href="transaction?pageid=1">My Transaction</a></li>
			                   
			                  </ul>
				</li>
				
				 <li><a href="mailbox?type=pending">Mailbox <span id="mailbox_notify_hd_count"></span></a></li>
				 
				 
				<li><a href="#">Search </a>
                  <ul class="dropdown">
                    <li><a href="regularsearch">Regular Search</a></li>
                    <li><a href="regularsearch"> Search By ID</a></li>
                    <li><a href="keywordsearch"> Keyword Search </a></li>
                    <li><a href="savesearchdetails"> Saved Search</a></li>
                    
                  </ul>
                </li>
                
                <li><a href="#">Matches</a>
			                  <ul class="dropdown">
			                   <li><a href="allmatches?paramtype=allpartnermatch">All Matches</a></li>
			                   <li><a href="allmatches?paramtype=allpartnernewmatch">New Matches</a></li>
			                   <li><a href="allmatches?paramtype=allpartnerpremiummatch">Premium Matches</a></li>
			                  </ul>
				</li>
                
                <li><a href="#">Members</a>
			                  <ul class="dropdown">
			                   <li><a href="allusers?paramtype=all">All Members</a></li>
			                   <li><a href="allusers?paramtype=new">New Members</a></li>
			                   <li><a href="allusers?paramtype=premium">Premium Members</a></li>
			                    
			                  </ul>
				</li>
				
				<!--  
				<li class="dekntf">	
 								<a href="javascript:void(0)" title="" class="not-box-open">
 								    <i class="fa fa-bell" title="View Notifications" style="color:red"></i>
 								    <sup><span class="notification-badge" id="notcnt"></span></sup>
 								</a>
 								
		 					 <ul class="dropdown">
		 								<li>
		  								<div class="notification-box active">
		      									
										<div class="nott-list" id="ltstnotifycontainr">
												<p class="notfy-head_innr">Notifications</p>
											<div class="view-all-nots">
											
												<div class="nottxthd">
													
													<a href="javascript:void(0)" title="" class="nottxtinf">
													<span class="notifyimg"><img class="" src="http://localhost:8080/satpakebandhaproject/resources/images/userimages/demouser3.png" alt=""></span>
													Sumana has sent you interest 
													</a>
													
													<a href="javascript:void(0)" title="" class="nota-3">View Profile <span class="clor-grey1-notdt" href="javascript:void(0)">2020-07-25</span></a>
													
												</div>	
												
												
													
												<a href="viewallnotifications" title="" class="viewalnofy">View All Notification</a>
											</div>
											
											
										</div>
									</div>
								</li>
							</ul>

			    		
			    </li>
			    <li style="display:none"  class="mbntf">	
 								<a href="javascript:void(0)" title="" class="not-box-open">
 								     <i class="fa fa-bell" title="View Notifications" style="color:red"></i>
 								    <sup><span class="notification-badge" id="notcnt"></span></sup> Notifications
 								</a>
 				</li>
				-->
				
                <li><a href="userlogout">Logout</a></li>
                
                <% } %>
                
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </div>
  </header>
  

<script>
$(document).ready(function(){
	
	/*########Discount modal popup################*/
	
	/*
		// Get the modal
	var modal = document.getElementById("myModal");
	
	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	
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
	
	if(sessionStorage.getItem('popState') != 'shown'){
		
		setTimeout(function(){ $("#myBtn").trigger("click") }, 3000);
		
		
        sessionStorage.setItem('popState','shown')
    }
	
	$('#closemodal').click(function() {
	    $('.close').trigger('click');
	});
	*/
	/*########Discount modal popup################*/
	
	/* mail box notification */
	check_if_mailbox_comes_count();
	/* mail box notification */
	/*message box notification*/
	check_if_message_comes_count();
	/*message box notification*/
	
});

/* mail box notification */
function check_if_mailbox_comes_count()
{
	var type='ajax';
        $.ajax({
                    type: "POST",
                    url: "check_if_mailbox_comes_count",
                    data: "type="+type,  
                    async: false,
          		    dataType: 'html',
          		    cache: false,
                    beforeSend: function(){ },
                    success: function(html) 
                    {
                        
                          
                            var response = JSON.parse(html);
                          
                            $.each(response, function (key, val) {
                            	
                         		if(val.totcount>0)
                         			{
                         				$("#mailbox_notify_hd_count").html(val.totcount);
                         				$("#mailbox_notify_hd_count").css("background","#ed6402");
                         				$("#mailbox_notify_hd_count").css("color","##fff");
                         			}
                            	
          					  
          				  	})       
                              
                        
                          setTimeout(function(){
                        	  check_if_mailbox_comes_count();
                          },5000) 
                    },

            });
}


function check_if_message_comes_count()
{
	var type='ajax';
        $.ajax({
                    type: "POST",
                    url: "check_if_message_comes_count",
                    data: "type="+type,  
                    async: false,
          		    dataType: 'html',
          		    cache: false,
                    beforeSend: function(){ },
                    success: function(html) 
                    {
                        
                          
                            var response = JSON.parse(html);
                          
                            $.each(response, function (key, val) {
                            	
                         		if(val.totcount>0)
                         			{
                         				$(".msg_notify_hd_count").html(val.totcount);
                         				$(".msg_notify_hd_count").css("background","#ed6402");
                         				$(".msg_notify_hd_count").css("color","##fff");
                         			}
                            	
          					  
          				  	})       
                              
                        
                          setTimeout(function(){
                        	  check_if_message_comes_count();
                          },5000) 
                    },

            });
}
</script>