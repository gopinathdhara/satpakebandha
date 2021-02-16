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
  <%@ page import="com.beans.Regular_Search" %>
 <%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
  
  
  <script type="text/javascript" src="<c:url value="/resources/Multiselect_dropdown/mock.js" />"></script>
  <link rel="stylesheet" href="<c:url value="/resources/Multiselect_dropdown/jquery.dropdown.css" />">
  <script src="<c:url value="/resources/Multiselect_dropdown/jquery.dropdown.js"/>"></script>
  
  <link rel="stylesheet" href="<c:url value="/resources/Choosen/chosen.min.css" />">
  <script src="<c:url value="/resources/Choosen/chosen.jquery.min.js"/>"></script>
  
<style>
.chosen-choices{
padding:6px !important;
}
</style>
  <!-- Start main-content -->
  <div class="main-content mgtop1">
    <!-- Section: inner-header -->
   
	
    <section>
     
			      <div class="container">
			      
			  	 
					        <div class="row">
					          
					          
							      <div class="col-md-7" style="border: 1px solid #d1d1d1;padding: 20px;">
							            
									            <form action="keywordsearchsave" name="keywordsearchform" class="register-form" method="get" >
									            
											              <div class="icon-box mb-0 p-0">
												                <a href="javascript:void(0)" class="icon icon-bordered icon-rounded icon-sm pull-left mb-0 mr-10">
												                  <i class="fa fa-search rgsrcicn" aria-hidden="true"></i>
												                </a>
												                <h4 class="text-gray pt-10 mt-0 mb-30 mytxtdesgn"> Search With Keyword</h4>
											                
											              </div>
											              <hr>
									             
											              <div id="step2" >
											                     
											              		<div class="row">
															                <div class="form-group col-md-8">
															                  <label for="form_name">Keyword</label>
															                  
															                  	<input type="text" value="" name="input_keyword" id="input_keyword" placeholder="Please Enter Keyword" class="form-control" required/>
															                  	<span id="keywd_err_msg" class="keywd_err_msg"></span>
															                  	<p class="keywd_msg_hght">Example 1: hindu, Mahishya, B.Sc, 25 years, howrah</p>
																				<p class="keywd_msg_hght">Example 2: hindu, Kayastha, B.A , 25-30 years, howrah</p>
																				<p class="keywd_msg_hght_note"><b>**Note ~</b> Please Use Comma Separated String To Search As Shown Above Example.</p>
																				
																			</div> 
																			
													                </div>
																	 
											          		 </div>   
									              
										            				 
														   		<div id="step3" >  
													               
															   			<div class="row">
																             
															             	 <div class="form-group  col-md-6">
																                
															                	<button class="btn btn-dark btn-lg btn-block mt-15" type="button" style="background: rgb(245, 131, 32) !important; width:40%;" onclick="form_submit();">Search</button>
															             	 </div>
														             	 </div>
															   		
															   	 </div>			 
											   					 
											             </form> 
									    </div>
							              
							        
							              
							        <div class="col-md-4 srchbyiddv" style="border: 1px solid #d1d1d1;padding: 20px;margin-left:10px;">
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

function searchbymatid()
{
		var usermatrimonyid=$("#usermatrimonyid").val();
		if(usermatrimonyid=="")
		{
		 	$("#usermatrimonyid_err").html("Please Enter Matrimony ID");
		}
		else
		{
			document.srbyidform.submit();
		}
		
}
function form_submit()
{
	var input_keyword=document.getElementById("input_keyword").value;
	if(input_keyword=="")
		{
			document.getElementById("keywd_err_msg").innerHTML="Please Provide Keyword To Search";
		}else{
			window.location.href="keywordsearchsave?input_keyword="+encodeURIComponent(input_keyword);
		}
	
	//document.keywordsearchform.submit();
}
</script>
</body>
</html>