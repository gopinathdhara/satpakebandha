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
  <div class="main-content mgtop1">
    <!-- Section: inner-header -->
  
      
    <!-- Section: Practice Area -->
    <section>
      <div class="container">
        <div class="section-content">
          <div class="row">
          
          
          <div class="col-sx-12 col-sm-2 col-md-2 sidebar pull-left">
              <div class="bride-thumb">
                
                		 <c:if test = "${uobj.getGender()=='Female' }">
                		 	<c:if test = "${uobj.getProfile_image()=='' }">
         						<img src="<c:url value="/resources/images/userimages/demouser3.png"  />"  class="srhdprofimg" alt="">
     			 			</c:if>
     			 			<c:if test = "${uobj.getProfile_image()!='' }">
         						<img src="<c:url value="/resources/userprofileimages/${uobj.getProfile_image()}" />" alt="" class="srhdprofimg">
     			 			</c:if>
     			 		
     			 		</c:if>
     			 		
     			 		<c:if test = "${uobj.getGender()=='Male' }">
     			 			
     			 			<c:if test = "${uobj.getProfile_image()=='' }">
         						<img src="<c:url value="/resources/images/userimages/demouser2.jpg" />" alt="" class="srhdprofimg">
     			 			</c:if>
     			 			<c:if test = "${uobj.getProfile_image()!='' }">
         						<img src="<c:url value="/resources/userprofileimages/${uobj.getProfile_image()}" />" alt="" class="srhdprofimg">
     			 			</c:if>
     			 		</c:if>
              </div>
              <!--  <div class="bride-detail mt-30">
                <ul class="social-icons icon-dark icon-theme-colored icon-circled">
                  <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                  <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                  <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                  <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                </ul>
              </div>-->
            </div>
            
            
            <div class="col-xs-12 col-sm-10 col-md-10 pull-right pl-60 " >
              <div class="attorney-info">
               <h3 class="mt-0 mb-0" style="text-transform:capitalize;font-family: serif;">${uobj.getName()}
               
              
              		 <c:if test="${not empty usermatrimonyid}">
               				<a href="javascript:void(0)" id="chckmemprm-${uobj.getId()}" class="chckmemprm" title="View Phone No">
               				<img src="<c:url value="/resources/images/call-icon.png" />" class="callimgg"/></a>
               				
               				<a href="chatwithmember?receiverid=${uobj.getId()}" target="_blank" title="Chat"><i class='fa fa-comment' style='font-size:44px;color:#ff9902' ></i> </a>
					</c:if>	                  	
		                  	
               
               </h3>
               
               
               <h5 class="text-gray">${uobj.getOccupation_name()}</h5>
                <h5 class="text-gray">${uobj.getMatrimony_id()}</h5>
                
                 <c:if test="${not empty usermatrimonyid}">
		                <p>
		                
		                <a href="javascript:void(0)" class="usr_snd_int" id="interest-${uobj.getId()}">Like</a>
		                <a href="javascript:void(0)" class="usr_snd_shr" id="shortlist-${uobj.getId()}"> <i class="fa fa-star" aria-hidden="true"></i> Shortlist</a>
		                
		                </p>
                </c:if>	   
                
               <!-- bg-light -->
		               
		              <div class="schedule-box maxwidth500  mb-30 prfboxshd bordrbox">
		                <div class="thumb">
		                  <img class="img-fullwidth" alt="" src="images/portfolio/2.jpg">
		                  
		                </div>
		                <div class="schedule-details clearfix p-15 pt-10">
		                  <h5 class="font-16 title"><a href="javascript:void(0)" style="color: #f58320;font-weight: bold;">Account Details</a></h5>
		                  <!--<ul class="list-inline font-11 mb-20">
		                    <li><i class="fa fa-calendar mr-5"></i> DEC 31/2016</li>
		                    <li><i class="fa fa-map-marker mr-5"></i> 89 Newyork City.</li>
		                  </ul>  -->
		                  	<p><span class="texthdbld">Gender : </span>${uobj.getGender()}</p>
		                  	<p><span class="texthdbld">Name : </span>${uobj.getName()}</p>
		                  
		                  
		                </div>
		                
		                
		              </div>
		              
		              <div class="schedule-box maxwidth500  mb-30 prfboxshd bordrbox">
		                <div class="thumb">
		                  <img class="img-fullwidth" alt="" src="images/portfolio/2.jpg">
		                  
		                </div>
		                <div class="schedule-details clearfix p-15 pt-10 ">
		                  <h5 class="font-16 title"><a href="javascript:void(0)" style="color: #f58320;font-weight: bold;">Profile Details</a></h5>
		                  <!--<ul class="list-inline font-11 mb-20">
		                    <li><i class="fa fa-calendar mr-5"></i> DEC 31/2016</li>
		                    <li><i class="fa fa-map-marker mr-5"></i> 89 Newyork City.</li>
		                  </ul>  -->
		                  	<p><span class="texthdbld">Date of birth : </span>${uobj.getDob()}</p>
		                  	
		                 	<c:if test="${uobj.getIs_mangalik()==1}">
		                  		<p class="prfismangalik">Mangalik</p>
		                  	</c:if>
		                  	<c:if test="${uobj.getIs_mangalik()==2}">
		                  		<p class="prfismangalik"> Non Mangalik</p>
		                  	</c:if>
		                  	<c:if test="${uobj.getIs_mangalik()==3}">
		                  		<p class="prfismangalik"> Part Mangalik</p>
		                  	</c:if>
		                  	
		                  	<p><span class="texthdbld">Religion : </span>${uobj.getReligion_name()}</p>
		                  	<p><span class="texthdbld">Caste : </span>${uobj.getCaste_name()}</p>
		                  	<p><span class="texthdbld">Mother Tongue : </span>${uobj.getMother_tongue_name()}</p>
		                  	<p><span class="texthdbld">Marital status : </span>${uobj.getMartial_name()}</p>
		                  	<p><span class="texthdbld">Height : </span>${uobj.getHeight_value()}</p>
		                  	<p><span class="texthdbld">Weight : </span>${uobj.getWeight_info()}</p>
		                  	<p><span class="texthdbld">Country : </span>${uobj.getCountry_name()}</p>
		                  	<p><span class="texthdbld">State : </span>${uobj.getState_name()}</p>
		                  	<p><span class="texthdbld">City : </span>${uobj.getCity_name()}</p>
		                  	<c:if test="${not empty uobj.getGonname()}">
		                  		<p><span class="texthdbld">Gon : </span>${uobj.getGonname()}</p>
		                  	</c:if>
		                  	<c:if test="${not empty uobj.getRashiname()}">
		                  		<p><span class="texthdbld">Rashi : </span>${uobj.getRashiname()}</p>
		                  	</c:if>
		                  	<c:if test="${not empty uobj.getBloodgroupname()}">
		                  		<p><span class="texthdbld">Blood Group : </span>${uobj.getBloodgroupname()}</p>
		                  	</c:if>
		                </div>
		                
		                
		              </div>
		              
		              
		              
		              <div class="schedule-box maxwidth500  mb-30 prfboxshd bordrbox">
		                <div class="thumb">
		                  <img class="img-fullwidth" alt="" src="images/portfolio/2.jpg">
		                  
		                </div>
		                <div class="schedule-details clearfix p-15 pt-10">
		                  <h5 class="font-16 title"><a href="javascript:void(0)" style="color: #f58320;font-weight: bold;">Career Details</a></h5>
		                  <!--<ul class="list-inline font-11 mb-20">
		                    <li><i class="fa fa-calendar mr-5"></i> DEC 31/2016</li>
		                    <li><i class="fa fa-map-marker mr-5"></i> 89 Newyork City.</li>
		                  </ul>  -->
		                  	<p><span class="texthdbld">Highest Education : </span>${uobj.getHighest_education()}</p>
		                  	<p><span class="texthdbld">Occupation : </span>${uobj.getOccupation_name()}</p>
		                  	<p><span class="texthdbld">Employed In : </span>${uobj.getEmployed_in_name()}</p>
		                  	<p><span class="texthdbld">Annual Income : </span>${uobj.getIncome_value()}</p>
		                  	<p><span class="texthdbld">Express Yourself : </span>${uobj.getExpress_yourself()}</p>
		                  	
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
  
  <!-- Footer -->
  
   <%@ include file="includes/footer.jsp" %>

  
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>

<script>

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
</script>

</body>
</html>