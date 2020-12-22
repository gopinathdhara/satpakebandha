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
  
  <%@ page import="com.beans.Profile" %>
  
  <!-- Header -->
  <%@ include file="includes/headerinner.jsp" %>
  
  <!-- Start main-content -->
  <div class="main-content">
    <!-- Section: inner-header -->
    

    <section style="margin-top:100px">
     
      <div class="container">
        <div class="row">
          
          <!-- mytext-color -->
          
          <% Profile userdt=(Profile)request.getAttribute("user"); %>
          
      <div class="col-md-7" style="border: 1px solid #d1d1d1;padding: 20px; margin:10px">
            
           
            <form:form method="post" action="profilepicsave" enctype="multipart/form-data" modelAttribute="command"> 
		              <div class="icon-box mb-0 p-0">
		                <a href="#" class="icon icon-bordered icon-rounded icon-sm pull-left mb-0 mr-10">
		                  <i class="fa fa-pencil-square-o rgsrcicn" aria-hidden="true"></i>
		                </a>
		                <h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn">Upload Profile Image.</h4>
		                
		              </div>
		              <hr>
              <div id="step1">
	                <div class="row">
	              		
	             		<div class="form-group col-md-3">
			             	
			             	<p>  Mandatory <span class="mandtry">*</span></p>
		             	
	             		</div>
	             	</div>
             	
		             <div class="row">
				                
				                
				                <div class="form-group col-md-6">
				                  
				                  
									<label for="form_name">Profile Image <span class="mandtry">*</span></label>
									<input name="file" id="fileToUpload" type="file" />
								 
									
				                </div>
				               
		                
		              </div>
		              
		              
              
              </div>
              
              
	            <div id="step3" >  
	              
			   					 
			   			<div class="row">
				             
		             	 
			             	 <div class="form-group  col-md-6">
				                
			                	<button class="btn btn-dark btn-lg btn-block mt-15" type="submit" style="background: rgb(245, 131, 32) !important;width:50%;" id="step3butn">Upload</button>
			             	 </div>
		             	 </div>
			   		
			   		
			   	 </div>				 
		   					 
		             </form:form> 
		    </div>
		              
		     	<div class="col-md-4" style="border: 1px solid #d1d1d1;padding: 20px; margin:10px;">
				        <form action="profiledetails" method="get" name="srbyidform">
				        		<div class="form-group col-md-12">
						                  <label for="form_choose_username"> Search By ID </label>
						                  <input name="usermatrimonyid"  Class="form-control" placeholder="Enter Matrimony ID" id="usermatrimonyid"/>
						                  <span id="usermatrimonyid_err" style="color:red"></span>
						                 <button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(245, 131, 32) !important;width:40%;" id="step3butn" onclick="searchbymatid();">Search</button>
						        </div>
		          		</form>
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
<script>

$(document).ready(function(){
	
	
})

</script>
</body>
</html>