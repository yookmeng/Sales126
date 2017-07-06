<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
	    <input type="hidden" value="prospect" name="prospect" /> 
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspects">Prospect</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listQuotation?prospectid=${prospect.prospectid}">Inquiry</a>
				</li>
			</ul>
		</div>
		<div>
			<h4>${prospect.firstname} ${prospect.lastname}</h4>
		</div>
	    <div align="center">
			<c:if test="${role == 'USER'}">
	   	    	<h5><a href="addQuotation?prospectid=${prospect.prospectid}" class='btn'>
					<i class="fa fa-plus-circle"></i>New Quotation</a></h5>
			</c:if>
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Quotation Date</th>
				    <th>Brand</th>
				    <th>Model</th>
				    <th>Quote Amount</th>
				    <th>Action</th>
				</tr>
				<c:forEach var="quotation" items="${listQuotation}" varStatus="status">
					<tr>
					    <td>${quotation.quotationdate}</td>
						<td>${quotation.brandname}</td>
						<td>${quotation.modelname}</td>
						<td><fmt:formatNumber value="${quotation.quoteamount}"/></td>
						<td>
						<button class="btn btn-small" onclick="window.location='editQuotation?quotationid=${quotation.quotationid}';" >
					    	<i class="fa fa-edit"></i></button>
						<c:if test="${role == 'USER'}">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-small" onclick="deleteQuotation(${quotation.quotationid})">
							<i class="fa fa-trash-o"></i></button>
						</c:if>
					    </td>		                 
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
	<script>
	    function deleteQuotation(quotationid) {
			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	

	    	jQuery.ajax({
	            type: "DELETE",
	            url: base+"/quotation/delete/"+quotationid,
	            contentType: "application/json",
	            data: "",
	            dataType: "",
	            success: function (data, status, jqXHR) {
					location.replace(location);
	            },	        
	            error: function (jqXHR, status) {
	            }
	        });	
	    }
	</script>	
</body>
</html>