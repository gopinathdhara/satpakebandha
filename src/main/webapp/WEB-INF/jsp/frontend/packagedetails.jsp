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
			
         <c:set var="renewal_status" value="<%=renewal_status%>" />
         
      <div class="container ">
      
      <!-- <div id="side-panel-trigger" class="side-panel-trigger" style="display:none"><a href="#"><i class="fa fa-filter" aria-hidden="true" class="advfiltermnu" style="color: #ff9902;font-size: 45px;"></i></a></div> -->
      
      <p style="font-size: 20px;color: #212529;letter-spacing: 1.5px;text-align: left;">Membership Package Details </p>
      
      <h5>Payment Process :</h5>
      
      	 <h4>1. <b>Online Payment</b></h4>
         <p>You can take membership through online payment. Your membership will be activated after payment.</p> 
         
         <h4>2. <b>Cash On Delivery</b></h4>
         <p>You can take membership through Cash On Delivery. You need to visit our office for payment. Your membership will be activated after payment.</p> 
      	 <a href="contactus" style="color:blue;font-weight:bold;" target="_blank">View Office Address</a>
      <div class="row">
      
           <div class="col-sm-12 col-md-12 blog-pull-right prfboxshd" style="padding:20px">
           
           
            <c:forEach items="${packagedetails}" var="packagedetails">
            
            <c:if test = "${packagedetails.getType()==0}">
            	<c:if test = "${renewal_status!=1}">
            	<div class="col-sm-4 col-md-4 col-lg-4">
	                	<div class="schedule-box maxwidth500 bg-light mb-30">
                
				                <div class="schedule-details clearfix p-15 pt-10 memshp">
				                  <h5 class="font-16 title memshiptxthd"><c:out value="${packagedetails.getTitle()}"/></h5>
				                  <p class="memshiptxt">Duration: ${packagedetails.getDuration()} ${packagedetails.getDuration_type()}</p>
				                  <p class="memshiptxt">Discount: ${packagedetails.getDiscount_percentage()}% </p>
				                   <p class="memshiptxtorg"> Rs. ${packagedetails.getOriginal_price()} </p>
				                  <p class="memshiptxt">Amount to be paid: Rs. ${packagedetails.getPaid_amount()} </p>
				                  <a href="payment?packageid=${packagedetails.getId()}" class="btn btn-warning makepaybtn">Online Payment</a>
				                   <a href="cashondelivery?packageid=${packagedetails.getId()}" class="btn btn-warning makepaybtn">Cash On Delivery</a>
				                </div>
						</div>
				</div>
				</c:if>
			</c:if>
			
			<c:if test = "${packagedetails.getType()==1}">
			
            	<c:if test = "${renewal_status==1}">
	            	<div class="col-sm-4 col-md-4 col-lg-4">
		                	<div class="schedule-box maxwidth500 bg-light mb-30">
	                
					                <div class="schedule-details clearfix p-15 pt-10 memshp">
					                  <h5 class="font-16 title memshiptxthd"><c:out value="${packagedetails.getTitle()}"/></h5>
					                  <p class="memshiptxt">Duration: ${packagedetails.getDuration()} ${packagedetails.getDuration_type()}</p>
					                  <p class="memshiptxt">Discount: ${packagedetails.getDiscount_percentage()}% </p>
					                   <p class="memshiptxtorg"> Rs. ${packagedetails.getOriginal_price()} </p>
					                  <p class="memshiptxt">Amount to be paid: Rs. ${packagedetails.getPaid_amount()} </p>
					                  <a href="payment?packageid=${packagedetails.getId()}" class="btn btn-warning makepaybtn">Online Payment</a>
					                   <a href="cashondelivery?packageid=${packagedetails.getId()}" class="btn btn-warning makepaybtn">Cash On Delivery</a>
					                </div>
							</div>
					</div>
				</c:if>
				
			</c:if>
			
				
				</c:forEach>
              	
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