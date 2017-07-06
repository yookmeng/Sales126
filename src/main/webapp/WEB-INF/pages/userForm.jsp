<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="loginusername" value="${req.userPrincipal.name}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'DEV'}">
		<jsp:include page="_deNavigation.jsp" />
	</c:if>   
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
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
	   	<input type="hidden" value="${loginusername}" name="loginusername" id="loginusername"/>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form action="" method="post" modelAttribute="user" class='form-horizontal form-wizard'>
			            <form:hidden path="userid"/>
			            <form:hidden path="companyid"/>
			            <form:hidden path="branchid"/>
			            <form:hidden path="teamid"/>
						<div class="form-group">
							<label for="username" class="control-label col-sm-2">User Name</label>
							<div class="col-sm-5">
								<form:input type="text" path="username" name="username" id="username" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="control-label col-sm-2">Password</label>
							<div class="col-sm-5">
								<form:input type="password" path="password" name="password" id="password" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="control-label col-sm-2">Role</label>
							<div class="col-sm-2">
								<form:select name="role" path="role" id="role" items="${rolelist}" data-rule-required="true" />
							</div>
						</div>
						<div class="form-group">
							<label for="mobile" class="control-label col-sm-2">Mobile No</label>
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
							<input type="reset" class="btn" onclick="location.href='home'" value="Back" id="back">						
							<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
							<c:if test="${userid != '0'}">
								<input id="btnDelete" type="button" class="btn" name="Delete" value="Delete">
							</c:if>
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
	    var teamid = $('#teamid').val(); 
	    var branchid = $('#branchid').val(); 
	    var companyid = $('#companyid').val(); 
	    var mobile = $('#mobile').val(); 
	    var email = $('#email').val(); 
	
	    var json = {
	    		"userid" : userid,
	    		"username" : username,
	    		"password" : password,
	    		"role" : role,
	    		"teamid" : teamid,
	    		"branchid" : branchid,
	    		"companyid" : companyid,
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
	                if ($('#loginusername').val()!=username){
		                window.location.href = base+"/listUser";
	                }
	                else{
		                window.location.href = base+"/home";	                	
	                }	                
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/user/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                if ($('#loginusername').val()!=username){
		                window.location.href = base+"/listUser";
	                }
	                else{
		                window.location.href = base+"/home";	                	
	                }	                
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });
	    }
	    return true;
	})
	
	$('#btnDelete').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button

		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var password = $('#password').val(); 
	    var role = $('#role').val(); 
	    var teamid = $('#teamid').val(); 
	    var branchid = $('#branchid').val(); 
	    var companyid = $('#companyid').val(); 
	    var mobile = $('#mobile').val(); 
	    var email = $('#email').val(); 
	
	    var json = {
	    		"userid" : userid,
	    		"username" : username,
	    		"password" : password,
	    		"role" : role,
	    		"teamid" : teamid,
	    		"branchid" : branchid,
	    		"companyid" : companyid,
	    		"mobile" : mobile,
	    		"email" : email
		};
        $.ajax({
            url: base+"/user/delete",
            type: 'DELETE',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(json), 
            success:function(data, status, jqXHR){
                if ($('#loginusername').val()!=username){
	                window.location.href = base+"/listUser";
                }
                else{
	                window.location.href = base+"/home";	                	
                }	                
            },
            error:function(jqXhr, status){
            }
	    });
	    return true;
	})
	</script>	
</body>
</html>