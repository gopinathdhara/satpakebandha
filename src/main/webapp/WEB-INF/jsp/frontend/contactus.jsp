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
   <style>
      /* Set the size of the div element that contains the map */
      #map {
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
       }
    </style>
      
    <!-- Section: Practice Area -->
    <section>
      <div class="container">
        <div class="section-content">
          <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 pull-right pl-60">
               
                <img src="<c:url value="/resources/images/about_banner.jpg" />"  alt=""/>
              <div class="attorney-info">
               <h3 class="mt-0 mb-0">Contact Us</h3>
               <!-- <h5 class="text-gray">Bride</h5> -->
               <p>The No. 1 Matrimony Site for Bengalis - 7pakebandha,  Office is present in howrah of India. 
                  You may call us or walk into  our office to make payments or for any other assistance
                  related to your partner search.
               </p>
               
               <h5>Office Address:</h5>
               <p>Mira Apartment, Andul Road, Nimtala (Near Mourigram Station, S E RLY) , Duilya, Howrah 711302, West Bengal</p>
               
               <div id="mapcontent">
               
               		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d5210.065821717452!2d88.25739094042189!3d22.576377635247706!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3a02798f0b544a25%3A0xab5102a7a2f3b6f4!2sMIRA%20APARTMENT!5e0!3m2!1sen!2sin!4v1614520122750!5m2!1sen!2sin" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
               
               </div>
              
               <!--<div id="map"></div>-->  
               
               <h5> Email Address:</h5>
               <p>sampa19721@gmail.com</p>
               
               <h5>Contact No:</h5>
               <p>7209156259 / 9294949351</p>
               
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
 <script>
// Initialize and add the map
function initMap() {
  // The location of Uluru
  var uluru = {lat: -22.584110, lng: 88.247850};
  // The map, centered at Uluru
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 4, center: uluru});
  // The marker, positioned at Uluru
  var marker = new google.maps.Marker({position: uluru, map: map});
}
    </script>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBLvmXv8ta2Owtuk9KUQzpd1yZnqmlWQBU&callback=initMap"> </script>

</body>
</html>