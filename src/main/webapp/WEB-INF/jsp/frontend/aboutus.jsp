<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html dir="ltr" lang="en">
<%@ include file="includes/headcss.jsp" %>

<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<div class="body-overlay"></div>
<%@ include file="includes/sidepanel.jsp" %>
<div id="wrapper" class="clearfix">
  <!-- preloader -->
  <%@ include file="includes/preloader.jsp" %>
  
  <!-- Header -->
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content" style="margin-top:120px">
    <!-- Section: inner-header -->
   
      
    <!-- Section: Practice Area -->
    <section>
      <div class="container">
        <div class="section-content">
          <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 pull-right pl-60">
               
                <img src="<c:url value="/resources/images/about_banner.jpg" />"  alt=""/>
              <div class="attorney-info">
               <h3 class="mt-0 mb-0">About Us</h3>
               <!-- <h5 class="text-gray">Bride</h5> -->
               <!-- <p>The No. 1 Matrimony Site for Bengalis - 7pakebandha, India's leading provider of online matchmaking services delivers matchmaking services to users in India 
               through its websites. We are commited to provide you good services at minimum cost and good quality. Our dream is to build a better India through happy marriages. We wish to give 
               you better services through any circumstances and obstacles. We need your hearty cooperation and congratulation to fulfill our dream.
               Thanks you so much.
               </p>-->
               
               <p class="homabtp">
							 <span class="wlcmhom"> Welcome to 7pakebandha.in</span><br/>

									The No. 1 Matrimony Site for Bengalis - 7pakebandha.in, 
									is one of the leading and most trusted matrimony websites in India.
									
									It is a free Indian matrimony with the mission of providing 
									high quality matrimonial services in affordable prices to marriage seekers.
									
									7pakebandha.in has helped lakhs of 
									Bengali singles to find suitable Bengali brides and grooms and get married.
									
									It has earned the goodwill of millions of Bengalis worldwide and
									has become the most trusted choice when it comes to finding a life partner. 
									
									
									<br/><br/>You can easily find lakhs of quality profiles from across cities in West Bengal 
									including Kolkata, Howrah, Bakura, Medinipur, Bardhaman and Hooghly 
									besides Bengalis from other 
									leading states in India.
									
									More than 1 lakhs Kolkata-based matrimonial profiles have found their soul mates 
									on the most trusted matchmaking site 7pakebandha.in.
									
									It believes in providing the most secure and 
									convenient matchmaking experience to all its members.<br/><br/>
									
									We are commited to provide you
									good services at minimum cost and good quality.
									
									Our dream is to build a better 
									India through happy marriages. 
									
									We wish to give you better services through any circumstances
									and obstacles. 
									
									We need your hearty cooperation and congratulation to fulfill our dream. 
									
									<br/><br/>
									7pakebandha.in acts as a platform to enable any user to themselves register on it 
									(by filling the mandatory fields and optional fields, if any) to voluntarily search
									for profile(s) from the database of already registered users. 
									you must have a valid/operational mobile phone number and an email id. 
									7pakebandha Members are provided with free/paid access for searching profiles
									from the database of 7pakebandha, as per the partner preference set by you on 
									the Web site and you can shortlist the profiles in which you are interested. 
									You can also send interest to other members. if you take membership then you 
									can chat or call other members. 
									<br/><br/>
									
									You can also be a part of all those love stories that blossomed on this matchmaking site.
									
									Ready for marriage? Get started now! All it takes is a few simple clicks to register 
									and start the search for your life partner. Register today as early as possible!
									
									
							</p>
               
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </section>
  </div>
  <!-- end main-content -->
  
  <%@ include file="includes/footer.jsp" %>
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>

</body>
</html>