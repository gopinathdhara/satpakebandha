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
<%
long linkcount=Long.parseLong(request.getAttribute("linkcount").toString());
long pageid=Long.parseLong(request.getParameter("pageid").toString());
%>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">All Users</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="dashboard">Dashboard</a></li>
                            <!-- <li class="breadcrumb-item active">Tables</li> -->
                        </ol>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                              All Users Listing
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                               <th>Gender</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                 <th>Phone No</th>
                                                 <th>Email Verification </th>
                                                 <th>Status</th>
                                                 <th>Profile Image</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Gender</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                 <th>Phone No</th>
                                                  <th>Email Verification </th>
                                                 <th>Status</th>
                                                 <th>Profile Image</th>
                                                
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                         <c:forEach items="${userlist}" var="mylist">
	                                            <tr>
	                                            	<td>${mylist.getGender()}</td>
	                                                <td>${mylist.getName()}</td>
	                                                <td>${mylist.getEmail()}</td>
	                                                <td>${mylist.getPhone_no()}</td>
	                                                <c:if test = "${mylist.getEmail_verification_status()==1}">
													<td><a href="javascript:void(0)" class="emailsts" id="emailsts-${mylist.getId()}-0" style="color: green;font-weight: bold;">Active</a></td>
													</c:if>
													<c:if test = "${mylist.getEmail_verification_status()==0}">
													<td><a href="javascript:void(0)" class="emailsts" id="emailsts-${mylist.getId()}-1" style="color: red;font-weight: bold;">Pending</a></td>
													</c:if>
													
													<c:if test = "${mylist.getStatus()==1}">
													<td ><a href="javascript:void(0)" class="actsts" id="actsts-${mylist.getId()}-0" style="color: green;font-weight: bold;">Active</a></td>
													</c:if>
													<c:if test = "${mylist.getStatus()==0}">
													<td ><a href="javascript:void(0)" class="actsts" id="actsts-${mylist.getId()}-1" style="color: red;font-weight: bold;">Inactive</a></td>
													</c:if>
													<td><a href="adminprofilepicupload?userid=${mylist.getId()}" style="color: blue;font-weight: bold;"><i class="fas fa-edit"></i></a></td>
	                                            </tr>
                                           </c:forEach> 
                                        </tbody>
										
                                    </table>
                                    <ul class="pagination pagination-sm">
										
										<% for(long i=1;i<=linkcount;i++){ %>
										
										<li class="page-item"><a class="page-link <% if(pageid==i){ %>pagintlnksel<% } %>" href="adminusers?pageid=<%=i %>"  ><%=i %></a></li>
										
										<% } %>
										</ul>
                                </div>
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
  <script>
$(document).on("click",".actsts",function(){
	
	var user_id_str=$(this).attr("id");
	var res = user_id_str.split("-");
	var user_id=res[1];
	var status=res[2];
	//alert(user_id);
	//alert(status);
	var type='ajax';
	$.ajax({
		
		  url: "admin_update_account_status",
		  cache: false,
		  type: "POST",
		  data: "user_id="+user_id+"&status="+status,
		  async: false,
		  dataType: 'html',
		  //success callback
		  success: function(html){
			  
			  var response = JSON.parse(html);
			 
			  $.each(response, function (key, val) {
				  if(val.payment_flag==1)
					  {
					  	
					 	swal("", "Account status has been changed.", "success");
					 	setTimeout(function(){ window.location.reload() }, 3000);
					  }
				  else
					  {
					  	swal("", "Error.", "error");
					  }
			  })
		  }
	});
})



$(document).on("click",".emailsts",function(){
	
	var user_id_str=$(this).attr("id");
	var res = user_id_str.split("-");
	var user_id=res[1];
	var status=res[2];
	//alert(user_id);
	//alert(status);
	var type='ajax';
	$.ajax({
		
		  url: "admin_update_email_status",
		  cache: false,
		  type: "POST",
		  data: "user_id="+user_id+"&status="+status,
		  async: false,
		  dataType: 'html',
		  //success callback
		  success: function(html){
			  
			  var response = JSON.parse(html);
			 
			  $.each(response, function (key, val) {
				  if(val.payment_flag==1)
					  {
					  	
					 	swal("", "Email verification status has been changed.", "success");
					 	setTimeout(function(){ window.location.reload() }, 3000);
					  }
				  else
					  {
					  	swal("", "Error.", "error");
					  }
			  })
		  }
	});
})
</script>  