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
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
 	<div id="main">
 		<div class="container-fluid">
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listPeriod">Closing</a>
					</li>
				</ul>
			</div>
	        <br>
	        <div align="center">
				<c:if test="${role == 'SA'}">
	   	    	<h5>
	   				<a href="addClosingPeriod" class='btn'>
					<i class="fa fa-plus-circle"></i>Add New</a></h5>
				</c:if>
				<div class="box box-color box-bordered">
					<div class="box-title">
						<h3>Closing Period</h3>
					</div>
					<div class="box-content nopadding">
						<table class="table table-hover table-nomargin table-bordered">
						<tr>
						    <th>Period</th>
						    <th>Opening Date</th>
						    <th>Closing Date</th>
						    <th>Closed</th>
						</tr>
						<c:forEach var="closingPeriod" items="${listClosingPeriod}" varStatus="status">
							<tr>
							    <td><a href="editClosingPeriod?id=${closingPeriod.id}">${closingPeriod.period}</a></td>
							    <td>${closingPeriod.opendate}</td>
							    <td>${closingPeriod.closedate}</td>
							    <td>${closingPeriod.closed}</td>
							</tr>
						</c:forEach>             
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>