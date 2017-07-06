<!DOCTYPE HTML>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_tlNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
        <input type="hidden" value="teamTarget" name="teamTarget" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeamTargetTL">Target</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listUserTarget?targetid=${teamTarget.targetid}">Member Target</a>
				</li>
			</ul>
		</div>
		<div>
			<h4>${teamTarget.teamname}  ${teamTarget.period}  Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${teamTarget.prospect}</h5>
    		<h5>Test Drive : ${teamTarget.testdrive}</h5>
    		<h5>Closed : ${teamTarget.closed}</h5>    		
        </div>
        <div align="center">
            <h5><a href="addUserTarget?targetid=${teamTarget.targetid}">Add New</a></h5>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
	            <tr>
	                <th>Member</th>
	                <th>Prospect</th>
	                <th>Test Drive</th>
	                <th>Closed</th>
	                <th>Action</th>
	            </tr>
	                <c:forEach var="userTarget" items="${listTarget}" varStatus="status">
	                <tr>
	                    <td>${userTarget.username}</td>
	                    <td><fmt:formatNumber value="${userTarget.prospect}"/></td>
	                    <td><fmt:formatNumber value="${userTarget.testdrive}"/></td>
	                    <td><fmt:formatNumber value="${userTarget.closed}"/></td>
						<td>
						<button class="btn btn-small" onclick="window.location='editUserTarget?targetid=${userTarget.targetid}';" >
					    	<i class="fa fa-edit"></i></button>
					    </td>		                 
	                </tr>
	                </c:forEach>             
	            </table>
        	</div>
		</div>
	</div>
</body>
</html>