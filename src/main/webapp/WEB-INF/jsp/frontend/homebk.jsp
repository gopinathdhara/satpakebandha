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
  <%@ include file="includes/header.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content">
    <!-- Section: home -->
    <section id="home" class="divider parallax layer-overlay overlay-dark-3" data-stellar-background-ratio="0.5">
      <div class="maximage-slider">
        <div id="maximage" >
          <img src="<c:url value="/resources/images/banner/banner.jpg" />" alt=""/>
          <!--  <img src="http://placehold.it/1920x1277" alt="" />
          <img src="http://placehold.it/1920x1277" alt=""/> matrimony1.jpg-->
          
        </div>
        <div class="fullscreen-controls"> <a class="img-prev"><img src="images/arrow-left.png" alt=""></a> <a class="img-next"><img src="images/arrow-right.png" alt=""></a> </div>
      </div>
      <div class="display-table">
        <div class="display-table-cell">
          <div class="container pt-100 pb-100">
            <div class="row">
              <div class="col-md-8 col-md-offset-2 text-center pt-60 wow fadeInUp animation-delay2">
                <h1 class="font-Playfair text-white">Most Trusted Matrimony Website</h1>
                <h2 class="text-white">For Happy Marriage</h2>
                <img src="<c:url value="/resources/images/wedding-frame-after.png" />" alt="wedding-frame-after">
              </div>
            </div>
             <!-- <div class="row p-0">
              <div class="col-md-8 col-md-offset-2 text-center">
                <div class="soon" id="countdown-timer-soon-glow"
                  data-layout="group overlap"
                  data-face="slot doctor glow"
                  data-padding="false"
                  data-scale-max="l"
                  data-due="2016-06-01"
                  data-visual="ring color-light width-thin glow-progress length-70 gap-0 offset-65">
                </div>
              </div>
            </div>-->
            
            
          </div>
        </div>
      </div>
    </section>
    
    <!-- parallax layer-overlay overlay-dark-8 -->
    <!-- Section: Contact  -->
    <section id="contact" class="divider" style="background: #f5faf8;font-family: Poppins Light;">
      <div class="container"> 
        <!-- Section Content -->
        <div class="section-content">
          <div class="row">
          <div class="col-md-12">
          <!-- text-white -->
            <h2 class="mt-0 mb-30 momes homesrchd">Please search for your perfect match</h2>
            <form id="rsvp_form" name="rsvp_form" class="form-transparent form-text-white" method="post" action="includes/rsvp.php">
              <div class="row">
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                       <span class="mt-0 mb-30" style="color:black">I am looking for :</span>
                      <select id="form_category" name="form_category" class="form-control homesrcinp">
                        <option value="AllEvents">Woman</option>
                        <option value="Ceremony">Man</option>
                        
                      </select>
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                       <span class="mt-0 mb-30" style="color:black">Age From:</span>
                      <select id="form_category" name="form_category" class="form-control homesrcinp">
                      
                       <% for(int i=18;i<=80;i++) { %>
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
                      <select id="form_category" name="form_category" class="form-control homesrcinp">
                      
                       <% for(int i=18;i<=80;i++) { %>
                        <option value="<%=i%>"><%=i %></option>
                        <% } %>
                      
                        
                      </select>
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30" style="color:black">Religion :</span>
                     
                      
                        <select id="form_category" name="form_category" class="form-control homesrcinp">
                        <option value="AllEvents">Hindu</option>
                        <option value="Ceremony">Muslim</option>
                        <option value="Ceremony">Christian</option>
                        <option value="Ceremony">Sikh</option>
                      	</select>
                      
                        
                    
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30" style="color:black">Mother Tongue :</span>
                     
                      
                        <select id="form_category" name="form_category" class="form-control homesrcinp">
                        <option value="AllEvents">Bengali</option>
                        <option value="Ceremony">English</option>
                        <option value="Ceremony">Hindi</option>
                        <option value="Ceremony">Gujrati</option>
                      	</select>
                      
                        
                    
                    </div>
                  </div>
                </div>
                
                <div class="col-sm-2" style="color:black">
                  <div class="form-group">
                    <div class="styled-select">
                      <span class="mt-0 mb-30">Caste :</span>
                     
                      
                        <select id="form_category" name="form_category" class="form-control homesrcinp">
                        <option value="AllEvents">Mahishya</option>
                        <option value="Ceremony">Brahmin</option>
                        <option value="Ceremony">Kayastha</option>
                        
                      	</select>
                      
                        
                     
                    </div>
                  </div>
                </div>
                
                
                <div class="col-sm-12">
                  <div class="form-group mb-0">
                    <input id="form_botcheck" name="form_botcheck" class="form-control" type="hidden" value="">
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
              <h2>The Couple</h2>
              <img src="images/section-title-after.png" alt="">
              <p><em>Love from heart !</em></p>
            </div>
          </div>
        </div>
        <div class="section-content couple">
          <div class="row">
            <div class="col-xs-12 col-sm-5 col-md-4 col-md-offset-1 wow fadeInLeft animation-delay2">
              <div class="best-people">
                <div class="thumb">
                  <img src="<c:url value="/resources/images/userimages/c1.jpg" />" alt="">
                  <div class="best-people-details"><a class="font-Playfair" href="page-about-her.html">Know more</a></div>
                </div>
                <div class="info">
                  <h2 class="name">Jenny</h2>
                  <img src="images/about-title-bg.png" alt="">
                  <p>Hi I am Jenny</p>
                  <a class="font-great-vibes text-theme-colored font-weight-700 font-23" href="">Read more</a>
                  <ul class="social-icons icon-dark icon-theme-colored icon-sm">
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-xs-12 col-sm-2 col-md-2 heart text-theme-colored-1"><i class="fa fa-heart text-theme-colored-1"></i></div>
            <div class="col-xs-12 col-sm-5 col-md-4 wow fadeInRight animation-delay2">
              <div class="best-people">
                <div class="thumb">
                  <img src="<c:url value="/resources/images/userimages/c2.jpg" />" alt="">
                  <div class="best-people-details"><a class="font-Playfair" href="page-about-him.html">Know more</a></div>
                </div>
                <div class="info">
                  <h2 class="name">Jon Doe</h2>
                  <img src="images/about-title-bg.png" alt="">
                  <p>Hi I am Jon Doe</p>
                  <a class="font-great-vibes text-theme-colored font-weight-700 font-23" href="">Read more</a>
                  <ul class="social-icons icon-dark icon-theme-colored icon-sm">
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                    <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <!-- Divider: Call To Action  -->
    <section class="divider parallax layer-overlay overlay-dark-7" data-stellar-background-ratio="0.5" data-bg-img="<c:url value="/resources/images/bg16.jpg" />">
      <div class="container">
     
        <!-- Section Content -->
        <div class="section-content">
        
        <div class="row">
          
            <div class="col-md-12 col-sm-12 wow fadeInUp animation-delay1" style="visibility: visible;">
              <div class="features text-center mb-md-60 mb-md-60">
                <h4 class="mt-20 mb-20"><a class="text-theme-colored" href="page-venues.html">Find your special someone.</a></h4>
                <p class="text-white "><img alt="" class="img-responsive centered img-auto" src="<c:url value="/resources/images/icon.png" />" ></p>
              </div>
            </div>
            </div>
        
          <div class="row">
          
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay1" style="visibility: visible;">
              <div class="features text-center mb-md-60 mb-md-60"> <i class="fa fa-pencil-square-o" aria-hidden="true" style="font-size: 40px;
    color: #fff;"></i>
                <h4 class="mt-20 mb-20"><a class="text-theme-colored" href="page-venues.html">Signup</a></h4>
                <p class="text-white">Please Register for free here.</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay2" style="visibility: visible;">
              <div class="features text-center mb-md-60"> <i class="fa fa-sign-in" aria-hidden="true" style="font-size: 40px;
    color: #fff;"></i>
                <h4 class="mt-20 mb-20"><a class="text-theme-colored" href="page-checklist.html">Login</a></h4>
                <p class="text-white">Login and update your profile.</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay3" style="visibility: visible;">
              <div class="features text-center mb-md-60"> <i class="fa fa-user" aria-hidden="true" style="font-size: 40px;
    color: #fff;"></i>
                <h4 class="mt-20 mb-20"><a class="text-theme-colored" href="page-budget.html">Connect</a></h4>
                <p class="text-white">Select and Connect with Matches you like.</p>
              </div>
            </div>
            <div class="col-md-3 col-sm-6 wow fadeInUp animation-delay4" style="visibility: visible;">
              <div class="features text-center mb-md-60"> <i class="fa fa-comments-o" aria-hidden="true" style="font-size: 40px;
    color: #fff;"></i>
                <h4 class="mt-20 mb-20"><a class="text-theme-colored" href="shop-category.html">Interact </a></h4>
                <p class="text-white">Become a Premium Member and Start a Conversation.</p>
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
              
              <img src="<c:url value="/resources/images/section-title-after.png" />" alt="">
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
                  <p>I am very lucky because I have gotten Kobita in my life as my partner.</p>
                  <a class="text-theme-colored" href="javascript:void(0)">Read More</a> </div>
                <div class="discription">
                  <img class="img-circle" alt="" src="<c:url value="/resources/images/1.jpg"/> ">
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
                  <h4><a class="" href="single-post-onepage-nav-light.html">Very Happy</a></h4>
                  <p>I have connected to my partner by using the service of this website.</p>
                  <a class="text-theme-colored" href="javascript:void(0)">Read More</a> </div>
                <div class="discription">
                  <img class="img-circle" alt="" src="<c:url value="/resources/images/2.jpg"/>" >
                </div>
              </div>
              <!-- cd-timeline-content --> 
            </div>
            <!-- cd-timeline-block -->
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img -->
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-right">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Very Happy</a></h4>
                  <p>Most trusted and favourite website.</p>
                  <a class="text-theme-colored" href="javascript:void(0)">Read More</a> </div>
                <div class="discription">
                  <img class="img-circle" alt="" src="<c:url value="/resources/images/3.jpg"/>" >
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
                  <h4><a class="" href="single-post-onepage-nav-light.html">Very Lucky</a></h4>
                  <p>I am grateful to the service. Thanks a lot.</p>
                  <a class="text-theme-colored" href="single-post-onepage-nav-light.html">Read More</a> </div>
                <div class="discription">
                  <img class="img-circle" alt="" src="<c:url value="/resources/images/4.jpg"/>" >
                </div>
              </div>
              <!-- cd-timeline-content --> 
            </div>
            <!-- cd-timeline-block -->
            
            <div class="cd-timeline-block">
              <div class="cd-timeline-img bg-theme-colored"> <i class="fa fa-heart"></i> </div>
              <!-- cd-timeline-img -->
              <div class="cd-timeline-content no-border bg-lighter">
                <div class="photo-timeline date-img text-right">
                  <h4><a class="" href="single-post-onepage-nav-light.html">Thanks a lot</a></h4>
                  <p>Rina sent me request and I accepted.</p>
                  <a class="text-theme-colored" href="javascript:void(0)">Read More</a> </div>
                <div class="discription">
                  <img class="img-circle" alt="" src="<c:url value="/resources/images/5.jpg"/>" >
                </div>
              </div>
              <!-- cd-timeline-content --> 
            </div>
            <!-- cd-timeline-block -->
            
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
              <img src="images/section-title-after.png" alt="">
              <p><em>Upgrade your membership plan now as per your customized requirements. With a paid membership, you can seamlessly connect with your prospects and get more responses. Here are some key benefits for membership:</em></p>
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
    <section class="divider parallax layer-overlay overlay-dark-8" data-stellar-background-ratio="0.5" data-bg-img="">
      <div class="container pt-70 pb-60"> 
        <!-- Section title -->
        <div class="section-title">
          <div class="row">
            <div class="col-md-6 col-md-offset-3 testimonial text-center">
              <h2 class="title text-white">Testimonials</h2>
              <div class="quote"> <i class="fa fa-quote-left icon text-white"></i> </div>
            </div>
          </div>
        </div>
        <!-- Section Content -->
        <div class="section-content">
          <div class="row">
            <div class="col-md-8 col-md-offset-2 mb-30">
              <div class="testimonial-carousel">
                <div class="item xs-text-center">
                  <div class="content pt-10">
                    <p>I have developed this website to arrange <br/> happy and trusted weddings for all over the world. </p>
                    <div class="thumb"><img width="75" class="img-circle" alt="" src="<c:url value="/resources/images/testimonial/gopi1.jpg" />" ></div>
                    <h5 class="text-theme-colored-1 mt-20 mb-0">Gopinath Dhara</h5>
                    <h3 class="text-theme-colored-1 mt-20 mb-0">Developer</h3>
                  </div>
                </div>
                <div class="item xs-text-center">
                  <div class="content pt-10">
                    <p>Happy and most trusted website </p>
                    <div class="thumb"><img width="75" class="img-circle" alt="" src="<c:url value="/resources/images/testimonial/1.jpg" />" ></div>
                    <h5 class="text-theme-colored-1 mt-20 mb-0">Esmin Shelly</h5>
                    <h3 class="text-theme-colored-1 mt-20 mb-0">Designer</h3>
                  </div>
                </div>
                <div class="item xs-text-center">
                  <div class="content pt-10">
                    <p>Please make an account  and do membership <br/> in this website. </p>
                    <div class="thumb"><img width="75" class="img-circle" alt="" src="<c:url value="/resources/images/testimonial/2.jpg" />" ></div>
                    <h5 class="text-theme-colored-1 mt-20 mb-0">JJohn Doe</h5>
                    <h3 class="text-theme-colored-1 mt-20 mb-0">Designer</h3>
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

</body>
</html>