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
  <div class="main-content" style="margin-top:120px">
    <!-- Section: inner-header -->
   
      
    <!-- Section: Practice Area -->
    <section>
      <div class="container">
        <div class="section-content">
          <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 pull-right pl-60">
               
                <img src="<c:url value="/resources/images/about_banner.jpg" />"  alt=""/>
              <div class="attorney-info">
               <h3 class="mt-0 mb-0">Privacy Policy</h3>
               <!-- <h5 class="text-gray">Bride</h5> -->
               <p>7pakebandha is an online matrimonial portal endeavouring constantly to provide you with matrimonial services. 
                Since we are strongly committed to your right to privacy, we have drawn out a privacy statement with regard to the 
                information we collect from you. You acknowledge that you are disclosing information voluntarily. 
                By accessing /using the website and/or by providing your information, you consent to the collection and 
                use of the info you disclose on the website in accordance with this Privacy Policy. 
                If you do not agree for use of your information, please do not use or access this website.
               </p>
               
               <h4>What information you need to give in to use this Website?</h4>
               
               <p>The information we gather from members and visitors who apply for the various services our website offers includes,
                but may not be limited to, email address, name, date of birth, educational qualifications a user-specified password, 
                mailing address, zip/pin code and telephone/mobile number or fax number.</p>
                
                <p>We use a secure server for credit card transactions to protect the credit card information of our users and 
                Session is used to store the login information.</p>
               
               <p>If you establish a credit account with us to pay the fees we charge, some additional information, 
               including a billing address, a credit/debit card number and a credit/debit card expiration date and tracking 
               information from cheques or demand drafts is collected.</p>
               
               <h4>With whom the website/apps shares the information it collects/tracks?</h4>
               
               <p>We may share such identifiable information with our associates/affiliates/subsidiaries and such associates/affiliates
               /subsidiaries may market to you as a result of such sharing. Any information you give us is held with the utmost care 
               and security. We are also bound to cooperate fully should a situation arise where we are required by law or 
               legal process to provide information about a customer/visitor.</p>
               
               <p>Where required or permitted by law, information may be provided to others, such as regulators and law enforcement
                agencies or to protect the rights ,property or personal safety of other members or the general public . 
                We may voluntarily share your information with law enforcement agencies / Gateway service providers / 
                anti-fraud solution provider(s) if we feel that the transaction is of suspicious nature.</p>
                
                <h4>How Long Do We Keep Your Information?</h4>
                
                <p>As stipulated in the Privacy Policy we will retain the information we collect from users under the following circumstances:

				For as long as the users subscribe to our services to meet their suitable purpose(s) for which it was collected, for the sake of 
				enforcing agreements, for performing audits, for resolving any form of disputes, for establishing legal defences, for pursuing 
				legitimate businesses and to comply with the relevant applicable laws.</p>
				
				<h4>What are the Security Precautions in respect of your personal information?</h4>
				
				<p>We aim to protect your personal information through a system of organizational and technical security measures. 
				We have implemented appropriate internal control measures designed to protect the security of any personal 
				information we process. However, please also remember that we cannot guarantee that the internet itself is 100% secure. 
				Once your information is in our possession, we adhere to security guidelines protecting it against unauthorised access.</p>
               
               <h4>Change of Privacy Policy</h4>
               <p>We may change this Privacy Policy without notice from time to time without any notice to you. However, 
               changes will be updated in the Privacy Policy page.</p>
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </section>
  </div>
  <!-- end main-content -->
  
  <%@ include file="includes/footer.jsp" %>
  <a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
</div>
<!-- end wrapper -->

<!-- Footer Scripts -->
<!-- JS | Custom script for all pages -->
<%@ include file="includes/footerjs.jsp" %>

</body>
</html>