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
	<jsp:include page="_mdNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
	   	<input type="hidden" value="company" name="company"/>
		<input type="hidden" value="${company.companyname}" name="companyname" id="companyname"/>		   	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listCompanyTarget">Target</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="companyTarget" class='form-horizontal form-wizard'>
		            <form:hidden path="companyid"/>        
		            <form:hidden path="targetid"/>			            
					<div class="form-group">
						<label for="period" class="control-label col-sm-2">Period</label>
						<div class="col-sm-2">
							<form:select type = "text" name="period" path="period" id="period" items="${periodlist}" data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="prospect" class="control-label col-sm-2">Prospect</label>
						<div class="col-sm-2">
							<form:input type="text" path="prospect" name="prospect" id="prospect" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="testdrive" class="control-label col-sm-2">Test Drive</label>
						<div class="col-sm-2">
							<form:input type="text" path="testdrive" name="testdrive" id="testdrive" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="closed" class="control-label col-sm-2">Closed</label>
						<div class="col-sm-2">		
							<form:input type="text" path="closed" name="closed" id="closed" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listCompanyTarget?username=${pageContext.request.userPrincipal.name}'" value="Back" id="back">
						<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
						<input id="btnDelete" type="button" class="btn" name="Delete" value="Delete">
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

    	var targetid = $('#targetid').val(); 
	    var companyid = $('#companyid').val(); 
	    var companyname = $('#companyname').val();
	    var period = $('#period').val(); 
	    var displayperiod = ""; 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	
	    var json = {
	    		"targetid" : targetid,
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"period" : period,
	    		"displayperiod" : displayperiod,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed
		};
	    if (json.targetid=="0"){
	        $.ajax({
	            url: base+"/companytarget/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listCompanyTarget";
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/companytarget/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listCompanyTarget";
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

    	var targetid = $('#targetid').val(); 
	    var companyid = $('#companyid').val(); 
	    var companyname = $('#companyname').val();
	    var period = $('#period').val(); 
	    var displayperiod = ""; 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	
	    var json = {
	    		"targetid" : targetid,
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"period" : period,
	    		"displayperiod" : displayperiod,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed
		};	
        $.ajax({
            url: base+"/companytarget/delete",
            type: 'DELETE',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(json), 
            success:function(data, Textstatus, jqXHR){
                window.location.href = base+"/listCompanyTarget";
            },
            error:function(jqXhr, Textstatus){
            }
        });    	
	    return true;
	})
	    
	</script>	
</body>
</html>