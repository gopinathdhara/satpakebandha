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
		        
		          <div class="col-md-6">
		          
		          		<div>
		          		<img src="<c:url value="/resources/images/phverify.png" />" height="100px"  alt=""/>
		          		</div>
				       <div class="alert alert-success">
						  <strong>Success!</strong> We have sent account activation link to your email. Please verify your email to login. 
						 
						</div>
						
						<div class="alert alert-danger">
						  <strong>Note!</strong> You must verify both your email and mobile no to login in 7pakebandha.in. 
						    
						  <a class="text-theme-colored font-weight-600 font-12" href="login" style="padding: 7px;">Login</a>
						</div>
		          </div>
		        
		          <div class="col-md-6 ">
				 
				
		            <h4 class="mytxtdesgn" >Phone Verification </h4>
		            <hr>
		            
		         
		            
		            
		            <h5 style="color:red;font-weight: bold;font-family: cursive;" >
		            
		            	<%
		            	String useriddb=request.getAttribute("useriddb").toString();
		            	%>
		                <%
		                String errmsg=request.getParameter("errmsg");
		                if(errmsg!=null)
		                {
		                	out.print(errmsg);
		                	
		                }
		               
		                %>
		                
		                </h5>
		                
		            <input type="hidden" name="useriddb" value="<%=useriddb %>" id="useriddb"/>
		            
		            <h4 style="color:green;font-weight: bold;" >
		                <%
		                String succmsg=request.getParameter("successmsg");
		                if(succmsg!=null)
		                {
		                	//out.print(succmsg);
		                	%>
		                	<script>
		                	
		                	$(document).ready(function(){
		                		show_alert_success();
		                		function show_alert_success()
		                		{
		                			swal("You have registered successfully", "We have sent account activation link to your email and verification code to your mobile. Please verify both your email and mobile no to login. ", "success");
		                		}
		                	})
		                	
		                	</script>
		                	<% 
		                }
		               
		                %>
		                
		                
		                 <%
		                String verifymsg=request.getParameter("verifymsg");
		                if(verifymsg!=null)
		                {
		                	//out.print(succmsg);
		                	%>
		                	<script>
		                	
		                	$(document).ready(function(){
		                		show_alert_verify_account();
		                		function show_alert_verify_account()
		                		{
		                			swal("Your account has been activated", "Please login from here", "success");
		                		}
		                	})
		                	
		                	</script>
		                	<% 
		                }
		               
		                %>
		                
		                </h4>
		            
		            
		              <div class="row">
		                 <div class="form-group col-md-12">
		                 <p> Please enter six digit verification code that has been sent to your mobile no and click on verify now button.</p>
		                 
			                  <label>Enter Code <span class="mandtry">*</span></label>
			                 
			                  <input type="text" name="phonecode" value="" id="phonecode" class="form-control">
			                   <span style="color:red;font-weight:bold;" id="phonecode_error_msg"></span>
						 </div>
		              </div>
		              
		              <div class="form-group pull-left mt-10">
		                <button type="submit" class="btn btn-dark btn-lg chkmobilecode" style="background:rgb(59, 89, 152) !important">Verify Now</button>
		              </div>
		              
		          
		          
		              
		            
		        </div>
          
         
          
       </div>
     </section>
   </div>
  
    
  </div>
  <script>
  /*verify mobile no*/
  $(document).on("click",".chkmobilecode",function(){
		
	  	var phonecode=$("#phonecode").val();
	  	var useriddb=$("#useriddb").val();
	  	
	  	if(phonecode=="")
	  		{
	  			$("#phonecode_error_msg").html('Please enter proper code');
	  		}
	  	else{
	  				//alert(useriddb);
					$("#phonecode_error_msg").html("");
					var type='ajax';
					$.ajax({
						
						  url: "check_if_mobile_code_ok",
						  cache: false,
						  type: "POST",
						  data: "useriddb="+useriddb+"&phonecode="+phonecode,
						  async: false,
						  dataType: 'html',
						  //success callback
						  success: function(html){
							  
							  var response = JSON.parse(html);
							 
							  $.each(response, function (key, val) {
								  if(val.status==1)
								  {
								  	
								 	   swal("Your mobile no is verified", "Please login", "success");
								 	   $("#phonecode").val('');
								  }
								  else if(val.status==2)
								  {
								  	
								 	   swal("Your mobile no is already verified", "Please login", "success");
								 	   $("#phonecode").val('');
								  }
								  else
									  {
									  	swal("", "Please enter proper code", "error");
									  }
							  })
						  }
					});
	  	}
	  	
	})
  </script>
  <!-- end main-content -->
  
  <!-- Footer -->
  <%@ include file="includes/footer.jsp" %>
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>

<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->

<%@ include file="includes/footerjs.jsp" %>

</body>
</html>