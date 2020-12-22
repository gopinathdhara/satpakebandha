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
		          <div class="col-md-6 col-md-push-3">
		            <h4 class="text-gray mt-0 pt-5 mytxtdesgn" > Login <img src="<c:url value="/resources/images/favicon/roase.png" />" height="60px"  alt=""/></h4>
		            <hr>
		            <p style="color:green;font-weight:bold">Login to continue.</p>
		            
		            <h5 style="color:red;font-weight: bold;font-family: cursive;" >
		                <%
		                String errmsg=request.getParameter("errmsg");
		                if(errmsg!=null)
		                {
		                	out.print(errmsg);
		                	
		                }
		               
		                %>
		                
		                </h5>
		            
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
		                			swal("You have registered successfully", "Please login from here.", "success");
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
		            <form:form action="loginsave" name="login-form" class="register-form" method="post" modelAttribute="command" >
		            
		              <div class="row">
		                 <div class="form-group col-md-12">
						                  <label>Email Address <span class="mandtry">*</span></label>
						                 
						                  <form:input path="email"  cssClass="form-control"/>
						                  <font color='red'><form:errors path='email' /></font>
						 </div>
		              </div>
		              <div class="row">
		                 <div class="form-group col-md-12">
						                  <label for="form_choose_password"> Password <span class="mandtry">*</span></label>
						                  <form:input path="password"  type="password" cssClass="form-control"/>
						                  <font color='red'><form:errors path='password' /></font>
						 </div>
		              </div>
		              <!--  <div class="checkbox pull-left mt-15">
		                <label for="form_checkbox">
		                  <input id="form_checkbox" name="form_checkbox" type="checkbox">
		                  Remember me </label>
		              </div>-->
		              <div class="form-group pull-left mt-10">
		                <button type="submit" class="btn btn-dark btn-lg" style="background:rgb(59, 89, 152) !important">Login</button>
		              </div>
		              <div class="clear text-center pt-10">
		               <p style="color:green;font-weight:bold">New on 7Pake Bandha? <a class="text-theme-colored font-weight-600 font-12" href="register" style="padding: 12px;">Register</a></p>
		                <!--  <a class="text-theme-colored font-weight-600 font-12" href="#" style="padding: 12px;">Forgot Your Password?</a>-->
		              </div>
		              <!--  <div class="clear text-center pt-10">
		                <a class="btn btn-dark btn-lg btn-block no-border mt-15 mb-15" href="#" data-bg-color="#3b5998">Login with facebook</a>
		                <a class="btn btn-dark btn-lg btn-block no-border" href="#" data-bg-color="#00acee">Login with twitter</a>
		              </div>-->
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

</body>
</html>