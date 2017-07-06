<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_deNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div align="center">
			<h5><a href="addCompany">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
		        <tr>
		            <th>Company Name</th>
		            <th>Registration No</th>
		            <th>Person In Charge</th>
		            <th>Telephone</th>
		        </tr>
	            <c:forEach var="Company" items="${listCompany}" varStatus="status">
					<tr>
					    <td><a href="editCompany?companyid=${Company.companyid}">${Company.companyname}</a></td>
					    <td>${Company.regno}</td>
					    <td>${Company.mdname}</td>
					    <td>${Company.telephone}</td>
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>	
	</div>
</body>
</html>