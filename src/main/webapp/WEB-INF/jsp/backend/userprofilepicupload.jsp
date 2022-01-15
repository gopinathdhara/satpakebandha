<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<html lang="en">
    <%@ include file="includes/head.jsp" %>
    <body class="sb-nav-fixed">
        <%@ include file="includes/nav.jsp" %>
        <div id="layoutSidenav">
        
        <%@ include file="includes/sidebar.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h6 class="mt-4"></h6>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="dashboard">Dashboard</a></li>
                            
                        </ol>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                              Profile Image
                            </div>
                            <style>
								  .srhdprofimg {
								    width: 50%;
    								height: 50%;
								    border-radius:10px
								}
                            </style>
            <% 
				
				String userid;
				try
				{
					userid=request.getParameter("userid").toString();
				}
				catch(Exception e)
				{
					userid="";
				}
			%>
                            <div class="card-body">
                                
                                    <form:form method="post" action="userprofilepicsavebyadmin" enctype="multipart/form-data" modelAttribute="command"> 
								              <div class="icon-box mb-0 p-0">
								               
								                <h6 >Upload Profile Image.</h6>
								                
								              </div>
								              
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
		             							 <hr>
								              		<input type="hidden" name="userid" id="userid" value="<%=userid%>"/>
										             <div class="row">
												              
												                <div class="form-group col-md-6">
												                  
																	<label for="form_name"><span style="color:blue">Profile Image </span><span style="color:red">*</span></label>
																	<input name="file" id="fileToUpload" type="file" required/>
																 
												                </div>
												               
										              </div>
										              
										   					 
										   			<div class="row">
											            
										             	 <div class="form-group  col-md-6">
											                
										                	<button class="btn btn-primary" type="submit" >Upload</button>
										             	 </div>
									             	 </div>
										   		
		   					 
		             					</form:form> 
                               
                            </div>
                        </div>
                    </div>
                </main>
                 <%@ include file="includes/footer.jsp" %>
            </div>
        </div>
         <%@ include file="includes/footerjs.jsp" %>
    </body>
</html>

    