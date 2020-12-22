<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>7pakebandha Admin</title>
        <link href="<c:url value="/resources/adminpages/dist/css/styles.css"/>" rel="stylesheet" />
        <script src="<c:url value="/resources/adminpages/dist/js/all.min.js"/>" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                            
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">
                                    <img src="<c:url value="/resources/images/logo2.png" />"  style="height:60px;" alt=""/> <br/>Login</h3>
                                    </div>
                                    
										<h5 style="color:red;font-weight: bold;font-family: cursive;margin-left:10px;" >
										<%
										String errmsg=request.getParameter("errmsg");
										if(errmsg!=null)
										{
											out.print(errmsg);
											
										}
										
										%>
										
										</h5>
                                    <div class="card-body">
                                        <form:form action="adminloginsave" name="login-form" class="register-form" method="post" modelAttribute="command" >
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">Email</label>                  
                                            	
                                            	<form:input path="email"  cssClass="form-control py-4"/>
                                            	 <font color='red'><form:errors path='email' /></font>
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputPassword">Password</label>
                                                
                                            	<form:input path="password"  type="password" cssClass="form-control py-4"/>
                                            	<font color='red'><form:errors path='password' /></font>
                                            </div>
                                            
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                               
                                                <input type="submit" value="Login" class="btn btn-primary">
                                            </div>
                                        </form:form>
                                    </div>
                                  
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy;  7pakebandha 2020</div>
                            
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="<c:url value="/resources/adminpages/dist/js/jquery-3.5.1.min.js"/>" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/adminpages/dist/js/bootstrap.bundle.min.js"/>" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/adminpages/dist/js/scripts.js"/>"></script>
    </body>
</html>
    