<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">


<!-- Mirrored from 7pakebandha.in/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 15 Jul 2020 13:32:46 GMT -->
<!-- Added by HTTrack --><meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" /><!-- /Added by HTTrack -->
<%@ include file="includes/headcss.jsp" %>

<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<div class="body-overlay"></div>

<%@ include file="includes/sidepanel.jsp" %>
<div id="wrapper" class="clearfix">
  <!-- preloader -->
  
<%@ include file="includes/preloader.jsp" %>
  
  <!-- Header -->
    
<!-- 7pake &nbsp;<i class="fa fa-heart font-25"></i> bandha text-theme-colored font-30 menuzord-brand-->
<%@ include file="includes/header.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content">
    <!-- Section: home -->
    <section id="home" class="divider parallax layer-overlay overlay-dark-3" data-stellar-background-ratio="0.5">
      <div class="maximage-slider">
        <div id="maximage" >
         
          <img src="<c:url value="/resources/images/banner.jpg" />" alt=""/>
       
        </div>
      
      </div>
      <div class="display-table">
        <div class="display-table-cell">
          <div class="container pt-100 pb-100">
            <div class="row">
              <div class="col-md-8 col-md-offset-2 text-center pt-60 wow fadeInUp animation-delay2">
            		<h1 class="font-Playfair text-white">Lakhs of blessed Marriages!</h1>
            		
            		<% 
						int home_verify_userid=0; 
						try
						{
							
							if(session.getAttribute("sess_usr_id").toString()!=null)
							{
								home_verify_userid=Integer.parseInt(session.getAttribute("sess_usr_id").toString());
								
							
							}
							
						}catch(Exception e)
						{
							
						}
					
					%>
            		
            		<% if(home_verify_userid==0){ %>
					<h2 class="text-white"><a href="register" class="btn btn-success btn-lg" style="font-size: 16px;background: #3f3fff;font-family: sans-serif;">REGISTER FREE</a></h2>
					<%} %>
                <br><br>
                
                <img src="<c:url value="resources/images/wedding-frame-after.png" />" alt="wedding-frame-after"/>
              </div>
            </div>
             
            
          </div>
        </div>
      </div>
    </section>
    <% 
			int userid_val=0;
			try
			{
				
				if(session.getAttribute("sess_usr_id").toString()!=null)
				{
					userid_val=Integer.parseInt(session.getAttribute("sess_usr_id").toString());
					
				
				}
				
			}catch(Exception e)
			{
				
			}
	%>
    <!-- parallax layer-overlay overlay-dark-8 -->
    <!-- Section: Contact  -->
    <section id="contact" class="divider" style="background: #f5faf8;font-family: Poppins Light;">
      <div class="container"> 
        <!-- Section Content -->
        <div class="section-content">
          <div class="row">
          <div class="col-md-12">
          <!-- text-white -->
            <h2 class="mt-0 mb-30 momes homesrchd">Please search for your perfect match :</h2>
            <form id="rsvp_form" name="rsvp_form" class="form-transparent form-text-white" method="get" action="homesearch">
              <div class="row">
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                       <span class="mt-0 mb-30" style="color:black">I am looking for :</span>
                      <select id="form_category" name="gender" class="form-control homesrcinp">
                      
                      <% if(userid_val==0){ %>
                      
                        <option value="Female">Female</option>
                        <option value="Male">Male</option>
                        
                        <% } else { 
                        
                        String gender=request.getAttribute("usergender").toString();
                        
                        %>
                        
		                        <%
		                        if(gender.equals("Male"))
		                        { 
		                        %>
		                        <option value="Female">Female</option>
		                        <% 
		                        } 
		                        else
		                        {
		                        %>
		                         <option value="Male">Male</option>
		                        <%
		                        }
		                        %>
                        <% } %>
                        
                      </select>
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                       <span class="mt-0 mb-30" style="color:black">Age From:</span>
                      <select id="form_category" name="agefrom" class="form-control homesrcinp">
                      
                        <% for(int i=18;i<=60;i++) { %>
                        <option value="<%=i%>"><%=i %></option>
                        <% } %>
                        
                      </select>
                       
                    </div>
                   
                  </div>
                   
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30" style="color:black">Age To :</span>
                      <select id="form_category" name="ageto" class="form-control homesrcinp">
                      
                        <% for(int i=18;i<=60;i++) { %>
                        <option value="<%=i%>" <% if(i==60){ %> selected="selected"<%} %> ><%=i %></option>
                        <% } %>
                        
                      </select>
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30" style="color:black">Religion :</span>
                     
                        <select id="form_category" name="religiion" class="form-control homesrcinp">
                        	
                        <c:forEach items="${religionlisthashmap}" var="religion">
                        	<option value="${religion.key}">${religion.value}</option>
                        </c:forEach>
                        
                      	</select>
                      
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30" style="color:black">Mother Tongue :</span>
                     
                        <select id="form_category" name="mother_tongue" class="form-control homesrcinp">
                        	
                        <c:forEach items="${mothertonguelisthashmap}" var="mothertongue">
                        	<option value="${mothertongue.key}" <c:if test="${mothertongue.key == 14}" > selected="selected" </c:if> >${mothertongue.value}</option>
                        </c:forEach>
                        
                      	</select>
                      
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2" style="color:black">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30">Caste :</span>
                     
                         <select id="form_category" name="caste" class="form-control homesrcinp">
                        	
                        <c:forEach items="${castelisthashmap}" var="castelist">
                        	<option value="${castelist.key}" <c:if test="${castelist.key == 493}" > selected="selected" </c:if> >${castelist.value}</option>
                        </c:forEach>
                        
                      	</select>
                      
                    </div>
                  </div>
                </div>
                
                
                <div class="col-sm-12">
                  <div class="form-group mb-0">
                    
                    <button type="submit" class="btn btn-colored btn-theme-colored btn-lg btn-submit" data-loading-text="Please wait..." style="background:#f58320 !important">Let's Begin</button>
                  </div>
                </div>
              </div>
            </form>
            
            <!-- rsvp Form Ends -->
          </div>
          </div>
          
          
        </div>
      </div>
    </section>  
    
    
    <!-- Section: Couple -->
    <section id="couple">
      <div class="container">
        <div class="section-title">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center pb-30 wow fadeInUp animation-delay1">
              <h2 style="font-size:22px;">Thousands of Bengalis have found their life partner here!</h2>
             
            </div>
            
          </div>
        </div>
        <div class="section-content couple">
          <div class="row">
            <div class="col-xs-12 col-sm-5 col-md-4 col-md-offset-1 wow fadeInLeft animation-delay2">
              <div class="best-people">
                <div class="thumb">
                  
                  
                   <img src="<c:url value="/resources/images/marry/hommarrygirl.jpeg" />" class="hmgrlcoupimg" alt=""/>
                  
                  <div class="best-people-details"><a class="font-Playfair" href="javascript:void(0)">Read More</a></div>
                </div>
                <div class="info">
                  <h4 class="name">Sondha Roy</h4>
                 
                 
                  
                  
                </div>
              </div>
            </div>
            <div class="col-xs-12 col-sm-2 col-md-2 heart text-theme-colored-1"><i class="fa fa-heart text-theme-colored-1"></i></div>
            <div class="col-xs-12 col-sm-5 col-md-4 wow fadeInRight animation-delay2">
              <div class="best-people">
                <div class="thumb">
                 
                  <img src="<c:url value="/resources/images/marry/homemarryman.jpg"  />" class="hmmancoupimg" alt=""/>
                  <div class="best-people-details"><a class="font-Playfair" href="javascript:void(0)">Read More</a></div>
                </div>
                <div class="info">
                  <h4 class="name">Kamal roy</h4>
                 
                  
                  <!-- <a class="font-great-vibes text-theme-colored font-23" href="#">Read More</a> -->
                 
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <!-- Divider: Call To Action  -->
    <section class="divider parallax layer-overlay overlay-dark-7" data-stellar-background-ratio="0.5"   style="background-color:#2eb16b;">
      <div class="container">
     
        <!-- Section Content -->
        <div class="section-content">
        
        <div class="row">
          
            <div class="col-md-12 col-sm-12 wow fadeInUp animation-delay1" style="visibility: visible;">
              <div class="features text-center mb-md-60 mb-md-60">
                <h5 class="mt-20 mb-20"><a class="" href="page-venues.html" style="color:#fff">Find your special someone.</a></h5>
                <p class="text-white "><img alt="" class="img-responsive centered img-auto" src="resources/images/section-title-after.png" ></p>
              </div>
            </div>
            </div>
        
          <div class="row">
          
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay1" style="visibility: visible;">
              <div class="features text-center mb-md-60 mb-md-60"> <img src="<c:url value="resources/icons/1.jpg" />" alt=""/>
                <h5 class="mt-20 mb-20"><a class="" href="page-venues.html" style="color:#fff">Signup & Build Your Profile </a></h5>
                <p class="text-white"style="font-size: 15px;"> Build detailed & accurate profile to get right matches</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay2" style="visibility: visible;">
              <div class="features text-center mb-md-60"> <img src="<c:url value="resources/icons/2.jpg" />" alt=""/>
                <h5 class="mt-20 mb-20"><a class="" href="page-checklist.html" style="color:#fff">Activate Your Profile</a></h5>
                <p class="text-white" style="font-size: 15px;"> upload your photo id to get more responses</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay3" style="visibility: visible;">
              <div class="features text-center mb-md-60"> <img src="<c:url value="resources/icons/3.jpg" />" alt=""/>
                <h5 class="mt-20 mb-20"><a class="" href="page-budget.html" style="color:#fff">Connect with Matches</a></h5>
                <p class="text-white"style="font-size: 15px;">Directly Send Interest and take membership to call or chat with matches</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay4" style="visibility: visible;">
              <div class="features text-center mb-md-60"> 
              <img src="<c:url value="resources/icons/4.jpg" />" alt=""/>
                <h5 class="mt-20 mb-20"><a class="" href="shop-category.html" style="color:#fff">Meet & Marry </a></h5>
                <p class="text-white"style="font-size: 15px;">Best wishes and hearty congratulations for you from 7pakebandha Website</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Section: Our Story -->
    <section>
      <div class="container pb-80">
        <div class="section-title">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center pb-30 wow fadeInUp animation-delay1">
              <h2>Millions have found their life partner here!</h2>
              
              <img src="resources/images/section-title-after.png" alt="">
              <p><em>Success Stories</em></p>
            </div>
          </div>
        </div>
        <!-- Section Content -->
        <div class="section-content">
          <div class="cd-container cd-timeline">
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img -->
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-right">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Very Lucky</a></h4>
                  <p>I am very lucky because I found my life partner on 7pakebandha.</p>
                  <!--  <a class="text-theme-colored" href="javascript:void(0)">Read More</a>--> </div>
                <div class="discription">
                  
                   <img class="img-circle1" src="<c:url value="/resources/images/marry/marry1.jpg"  />" class="commmarryimg" alt=""/>
                </div>
              </div>
              <!-- cd-timeline-content --> 
            </div>
            <!-- cd-timeline-block -->
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img -->
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-left">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Extremely cheerful</a></h4>
                  <p>We married within 1 month after meeting on 7pakebandha.</p>
                   </div>
                <div class="discription">
                 
                  <img class="img-circle1" src="<c:url value="/resources/images/marry/marry2.jpg" />" alt="" class="commmarryimg"/>
                </div>
              </div>
              <!-- cd-timeline-content img-circle --> 
            </div>
            <!-- cd-timeline-block -->
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img -->
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-right">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Highly satisfied</a></h4>
                  <p>We are thankful to 7pakebandha.Now our arrange marriage seems like love marriage.</p>
                   </div>
                <div class="discription">
           
                  
                  <img class="img-circle1" src="<c:url value="/resources/images/marry/marry3.jpg" />" alt="" class="commmarryimg"/>
                  
                </div>
              </div>
              <!-- cd-timeline-content --> 
            </div>
            <!-- cd-timeline-block -->
            
            <!--  <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
             
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-left">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Extremely  pleased</a></h4>
                  <p> Thank u so much 7pakebandha for helping me to find my beloved. Today we are together because of you guys only.</p>
                   </div>
                <div class="discription">
                  
                  
                  <img class="img-circle1" src="<c:url value="/resources/images/marry/marry4.jpg" />" alt="" class="commmarryimg"/>
                </div>
              </div>
              
            </div>
            
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
            
              <div class="cd-timeline-content no-border bg-lighter">
                  <div class="photo-timeline date-img text-right">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Thanks a lot</a></h4>
                  <p>I have found my soulmate using 7pakebandha. I am extremely happy about the fact that 7pakebandha happened to be the medium through which I found my love.</p>
                   </div>
                <div class="discription">
              
                  
                  <img class="img-circle1" src="<c:url value="/resources/images/marry/marry5.jpg" />" alt="" class="commmarryimg"/>
                </div>
              </div>
             
            </div>-->
           
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img --> 
              <!-- cd-timeline-block --> 
              <!-- cd-timeline --> 
            </div>
          </div>
        </div>
      </div>
    </section>

   
    
    <!-- Section: Event  -->
    <section id="event" class="bg-lighter">
      <div class="container">
        <div class="section-title">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 text-center pb-30 wow fadeInUp animation-delay1">
              <h2  style="color:red;">Membership Plans</h2>
              <!-- <img src="images/section-title-after.html" alt=""> -->
              <p><em>Upgrade your membership plan now as per your customized requirements. With a paid membership, you can seamlessly connect with your prospects and get Read More responses. Here are some key benefits for membership:</em></p>
            </div>
          </div>
        </div>
        
        
        <div class="col-sm-6 col-md-6 col-lg-6">
        	<div class="schedule-box maxwidth500 bg-light mb-30">
                
                <div class="schedule-details clearfix p-15 pt-10">
                  <h5 class="font-16 title"><a href="#" style="color:red;">Free</a></h5>
                  <ul class="font-11 mb-20">
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Browse Profiles</li> 
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Shortlist & Send Interest</li>
                    <li class="memlli"> <i class="fa fa-times" aria-hidden="true"></i> Message and chat with unlimited users</li> 
                    <li class="memlli"> <i class="fa fa-times" aria-hidden="true"></i> View contacts of members you like</li>
                    <li class="memlli"> <i class="fa fa-times" aria-hidden="true"></i> Priority customer support</li> 
                    <li class="memlli"> <i class="fa fa-times" aria-hidden="true"></i> Make your contacts visible to others</li>
                    <li class="memlli"> <i class="fa fa-times" aria-hidden="true"></i> Profile Boost</li> 
                  </ul>
                  
                  
                </div>
			</div>
		</div>
		
		<div class="col-sm-6 col-md-6 col-lg-6">
        	<div class="schedule-box maxwidth500 bg-light mb-30">
                
                <div class="schedule-details clearfix p-15 pt-10">
                  <h5 class="font-16 title" ><a href="#" style="color:red;">Paid</a></h5>
                  <ul class="font-11 mb-20">
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Browse Profiles</li> 
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Shortlist & Send Interest</li>
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Message and chat with unlimited users</li> 
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> View contacts of members you like</li>
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Priority customer support</li> 
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Make your contacts visible to others</li>
                    <li class="memlli"> <i class="fa fa-check" aria-hidden="true"></i> Profile Boost</li> 
                    
                  </ul>
                  
                  
                </div>
			</div>
		</div>
        
        
      </div>
    </section>
    
    <!-- Divider: Testimonials  -->
    
    
    
  
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

</body>

<!-- Mirrored from 7pakebandha.in/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 15 Jul 2020 13:33:59 GMT -->
</html>