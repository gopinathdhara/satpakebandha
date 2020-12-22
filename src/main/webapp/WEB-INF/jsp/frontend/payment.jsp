<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html dir="ltr" lang="en">

<%@ include file="includes/headcss.jsp" %>

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
      
       
         
      <div class="container ">
      
      <!-- <div id="side-panel-trigger" class="side-panel-trigger" style="display:none"><a href="#"><i class="fa fa-filter" aria-hidden="true" class="advfiltermnu" style="color: #ff9902;font-size: 45px;"></i></a></div> -->
      
      <p style="font-size: 20px;color: #212529;letter-spacing: 1.5px;text-align: center;"> Payment For Membership </p>
      
      <div class="row">
      
           <div class="col-sm-12 col-md-9 blog-pull-right prfboxshd" style="padding:20px">
            
         <p>We are commited to provide you good services at minimum cost and good quality. 
         Please pay us and be a premium user to start your chat with other members and view phone no.</p>
        
         
          
              
					<form action="paymentsuccess" method="POST"> 
					<script
					src="https://checkout.razorpay.com/v1/checkout.js"
					data-key="${apikey}" // Enter the Key ID generated from the Dashboard
					data-amount="${total_amount}" // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
					data-currency="INR"
					data-order_id="${order_id}"//This is a sample Order ID. Pass the `id` obtained in the response of the previous step.
					data-buttontext="Online Payment"
					data-name="7pakebandha"
					data-description="Membership Transaction"
					data-image="${site_my_url}/resources/images/logo2.png"
					data-prefill.name="${myprofobj.getName()}"
					data-prefill.email="${myprofobj.getEmail()}"
					data-prefill.contact="${myprofobj.getPhone_no()}"
					data-theme.color="#F37254"
					></script>
					<input type="hidden" custom="myid" name="1">
					</form>
                
              	 
              
              	
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



</body>
</html>