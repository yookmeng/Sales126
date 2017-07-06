<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="closed" class='form-horizontal form-wizard'>
		            <form:hidden path="closedid"/>
		            <form:hidden path="prospectid"/>
		            <form:hidden path="activityid"/>
		            <form:hidden path="closedate"/>
					<div class="form-group">
						<label for="downpayment" class="control-label col-sm-2">Down Payment</label>
						<div class="col-sm-2">
							<form:input type="text" path="downpayment" name="downpayment" id="downpayment" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="control-label col-sm-2">Remark</label>
						<div class="col-sm-5">
							<form:textarea type="textarea" row="5" path="remark" name="remark" id="remark" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listActivity?prospectid=${prospect.prospectid}'" value="Back" id="back">						
						<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
					</div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$('#btnSave').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var closedid = $('#closedid').val();
    	var closedate = $('#closedate').val();
    	var prospectid = $('#prospectid').val();
    	var activityid = $('#activityid').val();
	    var downpayment = $('#downpayment').val(); 
		var remark = $('#remark').val();	

		var json = {
	    		"closedid" : closedid,
	    		"closedate" : closedate,
	    		"prospectid" : prospectid,
	    		"activityid" : activityid,
	    		"downpayment" : downpayment,
	    		"remark" : remark	    		
		};
	    if (json.closedid=="0"){
	        $.ajax({
	            url: base+"/closed/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listActivity?prospectid="+prospectid;
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/closed/update/"+closedid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listActivity?prospectid="+prospectid;
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });
	    }
	    return true;
	})
	</script>	
</body>
</html>