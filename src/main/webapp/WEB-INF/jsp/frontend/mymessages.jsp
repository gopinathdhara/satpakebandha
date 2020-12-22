<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html dir="ltr" lang="en">
<%@ page import="java.util.List" %>
<%@ include file="includes/headcss.jsp" %>
<script src="<c:url value="/resources/bodyloader/jquery.loading.js"/>"></script>
<link  rel="stylesheet" href="<c:url value="/resources/bodyloader/loading.css" />" >
<body class="has-side-panel side-panel-right fullwidth-page side-push-panel">
<div class="body-overlay"></div>
<style>
.dtHorizontalExampleWrapper {
max-width: 600px;
margin: 0 auto;
}
#dtHorizontalExample th, td {
white-space: nowrap;
}

table.dataTable thead .sorting:after,
table.dataTable thead .sorting:before,
table.dataTable thead .sorting_asc:after,
table.dataTable thead .sorting_asc:before,
table.dataTable thead .sorting_asc_disabled:after,
table.dataTable thead .sorting_asc_disabled:before,
table.dataTable thead .sorting_desc:after,
table.dataTable thead .sorting_desc:before,
table.dataTable thead .sorting_desc_disabled:after,
table.dataTable thead .sorting_desc_disabled:before {
bottom: .5em;
}
.sorting_disabled{
color:#fff;
}
</style>
<div id="wrapper" class="clearfix">
  <!-- preloader -->
  <%@ include file="includes/preloader.jsp" %>
  
  <!-- Header -->
  
  <%@ include file="includes/headerinner.jsp" %>
<input type="hidden" id="sessionuid" name="sessionuid" value="${sessionuid }"/>
<c:set var="sessionuid" value="${sessionuid }" />
<%
long linkcount=Long.parseLong(request.getAttribute("linkcount").toString());
long pageid=Long.parseLong(request.getParameter("pageid").toString());
%>
  <!-- Start main-content -->
  <div class="main-content mgtop1" >
  
   
				  <section id="schedule" class="divider parallax layer-overlay overlay-light" >
				  
				  <div class="container pt-80 pb-60">
					<div class="section-content">
					  <div class="row">
						<div class="col-md-12">
						<p style="font-size: 20px;color: #212529;letter-spacing: 1.5px;text-align: left;text-align:center">My Messages </p>
						  <table id="dtHorizontalExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
							<thead>
							  <tr class="bg-theme-colored">
								<th>Sender </th>
								<th>Receiver </th>
								<th>Message</th>
								<th>DateTime</th>
								<th>Profile</th>
								<th>Chat</th>
							  </tr>
							</thead>
							<tbody>
							
       						<c:if test="${empty mymsg}">
       							<tr ><td colspan="6"><p style="text-align:center">You have not started your conversation. Please take membership to start chat with other members.</p></td></tr>
       						</c:if>
							 
							<c:forEach items="${mymsg}" var="mymsg">
									
							  <tr>
								<td class="mymsgnm">${mymsg.getSendername()}</td>
								<td class="mymsgnm">${mymsg.getReceivername()}</td>
								
								<td class="mymsgnm">${mymsg.getMsg()}</td>
								<td class="mymsgnm">${mymsg.getCreated_date()}</td>
								
								<c:if test = "${mymsg.getSenderid()==sessionuid}">
									<td ><a href="profiledetails?usermatrimonyid=${mymsg.getReceivermatrimonyid()}" target="_blank" class="btn-primary chtlnkmymsg">View Profile</a></td>
								</c:if>
								<c:if test = "${mymsg.getReceiverid()==sessionuid}">
									<td ><a href="profiledetails?usermatrimonyid=${mymsg.getSendermatrimonyid()}" target="_blank" class="btn-primary chtlnkmymsg">View Profile</a></td>
								</c:if>
								
								
								<c:if test = "${mymsg.getSenderid()==sessionuid}">
									<td ><a href="chatwithmember?receiverid=${mymsg.getReceiverid()}" target="_blank" class="btn-success chtlnkmymsg">Chat</a></td>
								</c:if>
								<c:if test = "${mymsg.getReceiverid()==sessionuid}">
									<td ><a href="chatwithmember?receiverid=${mymsg.getSenderid()}"  target="_blank" class="btn-success chtlnkmymsg">Chat</a></td>
								</c:if>
							  </tr>
							</c:forEach>
							 
							</tbody>
						  </table>
						  
							<ul class="pagination pagination-sm">
								
								<% for(long i=1;i<=linkcount;i++){ %>
							
							        <li class="page-item"><a class="page-link <% if(pageid==i){ %>pagintlnksel<% } %>" href="mymessages?pageid=<%=i %>"  ><%=i %></a></li>
							        
							        <% } %>
						    </ul>
						  
						  
						  
						</div>
					  </div>
					</div>
				  </div>
				</section>
	  
	  
	  
      
   
  </div>
  <!-- end main-content -->
  <!-- Footer -->
  
 
</div>
<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>

<link rel="stylesheet" href="<c:url value="/resources/css/datatable/jquery.dataTables.min.css" />" >
<script src="<c:url value="/resources/css/datatable/jquery.dataTables.min.js" />"></script>
<script>
$(document).ready(function () {
	$('#dtHorizontalExample').DataTable({
	"scrollX": true,
	searching: false, 
	paging: false, 
	info: false,
	ordering:false
	});
	$('.dataTables_length').addClass('bs-select');
	});
	
</script>


</body>
</html>