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
                        <h1 class="mt-4">All Transactions</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="dashboard">Dashboard</a></li>
                            
                        </ol>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table mr-1"></i>
                              All Transactions Listing
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                            	<th>Name</th>
				                                 <th>Transaction Id </th>
												<th>Valid From  </th>
												<th>Valid To </th>
												<th> Amount </th>
												<th>Payment Type</th>
												<th>Package</th>
												<th>Discount</th>
												<th>Status</th>
												<th> Date</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                            	<th>Name</th>
                                                <th>Transaction Id </th>
												<th>Valid From  </th>
												<th>Valid To </th>
												<th> Amount </th>
												<th>Payment Type</th>
												<th>Package</th>
												<th>Discount</th>
												<th>Status</th>
												<th> Date</th>
                                                
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach items="${mytransaction}" var="mytransaction">
									
											  <tr>
											   <td class="mymsgnm">${mytransaction.getName()}</td>
												<td class="mymsgnm">${mytransaction.getTransaction_id()}</td>
												<td class="mymsgnm">${mytransaction.getFrom_date_new()}</td>
												
												<td class="mymsgnm">${mytransaction.getTo_date_new()}</td>
												<td class="mymsgnm">RS ${mytransaction.getTotal_amount()}</td>
												
												<c:if test = "${mytransaction.getPayment_type()==0}">
													<td >Online Payment</td>
												</c:if>
												<c:if test = "${mytransaction.getPayment_type()==1}">
													<td >Cash on delivery </td>
												</c:if>
												
												
												<td class="mymsgnm">${mytransaction.getPackage_title()}</td>
												<td class="mymsgnm">${mytransaction.getPackage_discount_percentage()}%</td>
												
												<c:if test = "${mytransaction.getStatus()==0}">
														<c:if test = "${mytransaction.getPayment_type()==1}">
														
															<td style="color: red;font-weight: bold;"><a href="javascript:void(0)" style="color:red" class="transsts" id="transsts-${mytransaction.getId()}">Pending</a></td>
														</c:if>
														<c:if test = "${mytransaction.getPayment_type()==0}">
															<td style="color: red;font-weight: bold;">Pending</td>
														</c:if>
													
												</c:if>
												<c:if test = "${mytransaction.getStatus()==1}">
													<td style="color: green;font-weight: bold;">Done </td>
												</c:if>
												<td class="mymsgnm">${mytransaction.getCreated_date_new()}</td>
											  </tr>
											</c:forEach>
                                        </tbody>
										
                                    </table>
                                    <ul class="pagination pagination-sm">
										
										<% for(long i=1;i<=linkcount;i++){ %>
										
										<li class="page-item"><a class="page-link <% if(pageid==i){ %>pagintlnksel<% } %>" href="admintransaction?pageid=<%=i %>"  ><%=i %></a></li>
										
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
$(document).on("click",".transsts",function(){
	
	var payment_id_str=$(this).attr("id");
	var res = payment_id_str.split("-");
	var payment_id=res[1];
	//alert(payment_id);
	var type='ajax';
	$.ajax({
		
		  url: "admin_update_transaction_status",
		  cache: false,
		  type: "POST",
		  data: "payment_id="+payment_id,
		  async: false,
		  dataType: 'html',
		  //success callback
		  success: function(html){
			  
			  var response = JSON.parse(html);
			 
			  $.each(response, function (key, val) {
				  if(val.payment_flag==1)
					  {
					  	
					 	swal("", "Cash on delivery is active now.", "success");
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
   