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
  <div class="main-content mgtop">
    <!-- Section: inner-header -->
    
    
    <!-- Section: Bridesmaid -->
    <section>
      <div class="container">
        <div class="section-content text-center">
        
        
        <input type="hidden" id="demousrimg" value="<c:url value="/resources/images/userimages/" />"/>
        <input type="hidden" id="lastpostid" value="0"/>
        
        
        
        <section>
      <div class="container mt-30 mb-30 pt-30 pb-30 ">
        <div class="row multi-row-clearfix">
          <div class="blog-posts" >
          
            <div class="col-md-7 prfboxshd bordrbox">
              <div class="row list-dashed" id="containr">
              
				<c:if test="${empty sList}">
					<p>No Record Found.</p>
				</c:if>
              <c:forEach items="${sList}" var="sList1">
              
	                <article class="post clearfix mb-50" style="padding:10px;">
	                  
	                  <div class="col-sm-12">
	                    <div class="entry-content mt-0">
	                      <h4 class="entry-title mt-0 pt-0 usr_fnm">${sList1.getSearch_name()}</h4>
	                      
	                      <p class="mb-30">My Saved Search</p>
	                      <ul class="list-inline like-comment pull-left font-12">
	                        <li><a href="editusersavedsearch?savedsearchid=${sList1.getId()}" class="usr_snd_int">Edit</a></li>
	                        <li><a href="javascript:void(0)" class="usr_snd_int" onclick="deletesavesearch(${sList1.getId()});">Delete</a></li>
	                       
	                      </ul>
	                      <a class="pull-right text-gray font-13" href="savesearchresult?searchid=${sList1.getId()}"><i class="fa fa-angle-double-right text-theme-colored"></i> Search</a>
	                    </div>
	                  </div>
	                </article>
                
                </c:forEach>
                
                
                
              </div>
            </div>
            
            <div class="col-md-1"></div>
            
            	 <div class="col-md-3" style="border: 1px solid #d1d1d1;padding: 20px;">
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
      </div>
    </section>
    
        
          <div class="process-comm"  id="loaderspinn" style="display:none" >
        	<i class="fa fa-cog fa-spin" style="font-size:48px;color:red"></i>
        </div>
        </div>
      </div>
    </section>
  </div>
  <!-- end main-content -->
  
  <!-- Footer -->
  
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>
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
function deletesavesearch(sid)
{
	
	
	swal({
		  title: "Are you sure to delete?",
		  text: "Saved Search",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		    window.location.href="savedsearchdelete/"+sid;
		  } else {
		    swal("Your Saved Search is safe !");
		  }
		});
}
</script>
</body>
</html>