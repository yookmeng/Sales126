<!DOCTYPE HTML>
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
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'USER'}">
		<jsp:include page="_userNavigation.jsp" />
	</c:if>   
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<input type="hidden" value="company" name="company" />     
		<input type="hidden" value="branch" name="branch" />     
		<input type="hidden" value="team" name="team" />     
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBranch?companyid=${company.companyid}">Branch</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listTeam?branchid=${branch.branchid}">Team</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listMember?teamid=${team.teamid}">Member</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="member" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>
		            <form:hidden path="branchid"/>
		            <form:hidden path="teamid"/>
		            <form:hidden path="userid"/>
		            <form:hidden path="role"/>
					<div class="form-group">
						<label for="username" class="control-label col-sm-2">Member Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="username" name="username" id="username" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="control-label col-sm-2">Password</label>
						<div class="col-sm-5">
							<form:input type="text" path="password" name="password" id="password" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="mobile" class="control-label col-sm-2">Mobile</label>
						<div class="col-sm-2">
							<form:input type="text" path="mobile" name="mobile" id="mobile" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="control-label col-sm-2">Email</label>
						<div class="col-sm-5">
							<form:input type="text" path="email" name="email" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listMember?teamid=${member.teamid}'" value="Back" id="back">						
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

    	var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var password = $('#password').val(); 
	    var role = $('#role').val(); 
	    var companyid = $('#companyid').val(); 
	    var branchid = $('#branchid').val(); 
	    var teamid = $('#teamid').val(); 
	    var mobile = $('#mobile').val(); 
	    var email = $('#email').val(); 
	
	    var json = {
	    		"userid" : userid,
	    		"username" : username,
	    		"password" : password,
	    		"role" : role,
	    		"companyid" : companyid,
	    		"branchid" : branchid,
	    		"teamid" : teamid,
	    		"mobile" : mobile,
	    		"email" : email
		};
	    if (json.userid=="0"){
	        $.ajax({
	            url: base+"/user/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listMember?teamid="+teamid;
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/user/update/",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listMember?teamid="+teamid;
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