<!DOCTYPE HTML">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listProspects">Prospect</a>
				</li>
			</ul>
		</div>
		<div class="box-content nopadding">
        	<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
					        <form:form action="" method="post" modelAttribute="notes" class='form-horizontal form-wizard'>
				            <form:hidden path="noteid"/>
				            <form:hidden path="notedate"/>
				            <form:hidden path="userid"/>
				            <form:hidden path="username"/>
				            <form:hidden path="teamid"/>
				            <form:hidden path="teamname"/>
				            <form:hidden path="branchid"/>
				            <form:hidden path="branchname"/>
				            <form:hidden path="companyid"/>
				            <form:hidden path="companyname"/>
				            <form:hidden path="prospectid"/>
				            <form:hidden path="reviewby"/>
				            
							<c:if test="${role == 'USER'}">
								<div class="form-group">
									<label for="note" class="control-label col-sm-2">Note</label>
									<div class="col-sm-5">
										<form:textarea type="textarea" rows="5" path="note" name="note" id="note" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="approve" class="control-label col-sm-2">Status</label>
									<div class="col-sm-1">
										<c:if test="${notes.status == 0}"><span>Pending</span></c:if>											
										<c:if test="${notes.status == 1}"><span>Approved</span></c:if>											
										<c:if test="${notes.status == 2}"><span>Rejected</span></c:if>											
									</div>
									<div class="col-sm-1">
										<form:checkbox path="status" value="-1" id="approve" class="hidden form-control"/>
									</div>
									<div class="col-sm-1">
										<form:checkbox path="status" value="-1" id="reject" class="hidden form-control"/>
									</div>
								</div>					    
								<div class="form-group">
									<label for="remark" class="control-label col-sm-2">Remark</label>
									<div class="col-sm-5">
										<form:input type="text" disabled="true" path="remark" name="remark" id="remark" class="form-control" />
									</div>
								</div>
							</c:if>
							<c:if test="${role != 'USER'}">
								<div class="form-group">
									<label for="note" class="control-label col-sm-2">Note</label>
									<div class="col-sm-5">
										<form:textarea type="textarea" rows="5" disabled="true" path="note" name="note" id="note" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="status" class="control-label col-sm-2">Status</label>
									<label for="approve" class="control-label col-sm-1">Approve</label>
									<div class="col-sm-1">
										<c:if test="${notes.status == 1}">
											<input type="checkbox" checked id="approve" class="form-control"/>
										</c:if>
										<c:if test="${notes.status != 1}">
											<input type="checkbox" id="approve" class="form-control"/>
										</c:if>
									</div>
									<label for="reject" class="control-label col-sm-1">Reject</label>
									<div class="col-sm-1">
										<c:if test="${notes.status == 2}">
											<input type="checkbox" checked id="reject" class="form-control"/>
										</c:if>
										<c:if test="${notes.status != 2}">
											<input type="checkbox" id="reject" class="form-control"/>
										</c:if>
									</div>
								</div>					    
								<div class="form-group">
									<label for="remark" class="control-label col-sm-2">Remark</label>
									<div class="col-sm-5">
										<form:input type="text" path="remark" name="remark" id="remark" class="form-control" />
									</div>
								</div>
							</c:if>
							<div class="form-actions">
								<input type="reset" class="btn" onclick="history.back(-1);" value="Back" id="back">
								<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
							</div>
							</form:form>
						</div>
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
    	var noteid = $('#noteid').val(); 
	    var notedate = $('#notedate').val(); 
	    var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var teamid = $('#teamid').val();
	    var teamname = $('#teamname').val();
	    var branchid = $('#branchid').val();
	    var branchname = $('#branchname').val();
	    var companyid = $('#companyid').val();
	    var companyname = $('#companyname').val();
	    var prospectid = $('#prospectid').val();
	    var prospectname = "";	    
	    var note = $('#note').val(); 
	    var status = "0";
	    if ($('#approve').prop('checked')==true){
	    	status = "1";
	    }; 
	    if ($('#reject').prop('checked')==true){
	    	status = "2";
	    }; 
	    var remark = $('#remark').val(); 
	    var reviewby = $('#reviewby').val(); 
	    var json = {
	    		"noteid" : noteid,
	    		"notedate" : notedate,
	    		"userid" : userid,
	    		"username" : username,
	    		"teamid" : teamid,
	    		"teamname" : teamname,
	    		"branchid" : branchid,
	    		"branchname" : branchname,
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"prospectid" : prospectid,
	    		"prospectname" : prospectname,
	    		"note" : note,
	    		"status" : status,
	    		"remark" : remark,
	    		"reviewby" : reviewby
		};

	    if (json.noteid=="0"){
	        $.ajax({
	            url: base+"/note/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
					history.back(-1);
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/note/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
					history.back(-1);
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