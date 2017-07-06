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
	<div id="main">
		<div class="container-fluid">
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
		   	<input type="hidden" value="${currentPeriod}" name="currentPeriod" id="currentPeriod"/>		   	
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listReview">Review</a>
					</li>
				</ul>
			</div>
			<input type="hidden" value="userTarget" name="userTarget" /> 
			<div>
				<h4>${userTarget.username}  ${userTarget.period}  Target</h4>
			</div>
			<div class="box-content nopadding">
	        	<div class="row">
					<div class="span12">
						<div class="box">
							<div class="box-content">
						        <form:form action="" method="post" modelAttribute="review" class='form-horizontal form-wizard'>
					            <form:hidden path="reviewid"/>
					            <form:hidden path="period"/>
					            <form:hidden path="userid"/>
					            <form:hidden path="username"/>
					            <form:hidden path="teamid"/>
					            <form:hidden path="teamname"/>
					            <form:hidden path="branchid"/>
					            <form:hidden path="branchname"/>
					            <form:hidden path="companyid"/>
					            <form:hidden path="companyname"/>
					            <form:hidden path="targetid"/>
					            <form:hidden path="teamtargetid"/>
					            <form:hidden path="reviewdate"/>
					            <form:hidden path="prospect"/>
					            <form:hidden path="testdrive"/>
					            <form:hidden path="closed"/>
					            <form:hidden path="reviewby"/>
								<div>
									<table class="table table-hover table-nomargin table-bordered">
							        <tr>
							            <th><p align="center">Category</p></th>
							            <th><p align="center">Target</p></th>
							            <th><p align="center">Actual As At ${review.reviewdate}</p></th>
							        </tr>
							        <tr>
						                <td><p align="center">Prospect</p></td>
							            <td><p align="center">${userTarget.prospect}</p></td>
							            <td><p align="center">${review.prospect}</p></td>
						            </tr>
							        <tr>
						                <td><p align="center">Test Drive</p></td>
							            <td><p align="center">${userTarget.testdrive}</p></td>
							            <td><p align="center">${review.testdrive}</p></td>
						            </tr>
							        <tr>
						                <td><p align="center">Closed</p></td>
							            <td><p align="center">${userTarget.closed}</p></td>
							            <td><p align="center">${review.closed}</p></td>
						            </tr>
						           	</table>						
								</div>
								<div class="form-group">
									<label for="minute" class="control-label col-sm-2">Minute</label>
									<div class="col-sm-5">
										<form:textarea type="textarea" rows="5" path="minute" name="minute" id="minute" class="form-control" />
									</div>
								</div>
								<div class="form-actions">
									<input type="reset" class="btn" onclick="history.back(-1);" value="Back" id="back">
									<c:if test="${role != 'USER'}">
										<c:if test="${review.period == currentPeriod}">
											<input id="btnSave" type="submit" class="btn btn-primary" name="Save" value="Save">
											<input id="btnDelete" type="button" class="btn" name="Delete" value="Delete">
				                    	</c:if>
			                    	</c:if>
								</div>
								</form:form>
							</div>
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
    	var reviewid = $('#reviewid').val(); 
	    var period = $('#period').val(); 
	    var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var teamid = $('#teamid').val();
	    var teamname = $('#teamname').val();
	    var branchid = $('#branchid').val();
	    var branchname = $('#branchname').val();
	    var companyid = $('#companyid').val();
	    var companyname = $('#companyname').val();
    	var reviewdate = $('#reviewdate').val(); 
	    var targetid = $('#targetid').val();
	    var teamtargetid = $('#teamtargetid').val();	    
	    var reviewdate = $('#reviewdate').val(); 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	    var minute = $('#minute').val(); 
	    var reviewby = $('#reviewby').val(); 

	    var json = {
	    		"reviewid" : reviewid,
	    		"period" : period,
	    		"userid" : userid,
	    		"username" : username,
	    		"teamid" : teamid,
	    		"teamname" : teamname,
	    		"branchid" : branchid,
	    		"branchname" : branchname,
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"targetid" : targetid,
	    		"teamtargetid" : teamtargetid,
	    		"reviewdate" : reviewdate,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed,
	    		"minute" : minute,
	    		"reviewby" : reviewby
		};

	    if (json.reviewid=="0"){
	        $.ajax({
	            url: base+"/review/create",
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
	            url: base+"/review/update/"+reviewid,
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
	
	$('#btnDelete').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	
    	var reviewid = $('#reviewid').val(); 
	    var period = $('#period').val(); 
	    var userid = $('#userid').val(); 
	    var username = $('#username').val(); 
	    var teamid = $('#teamid').val();
	    var teamname = $('#teamname').val();
	    var branchid = $('#branchid').val();
	    var branchname = $('#branchname').val();
	    var companyid = $('#companyid').val();
	    var companyname = $('#companyname').val();
    	var reviewdate = $('#reviewdate').val(); 
	    var targetid = $('#targetid').val();
	    var teamtargetid = $('#teamtargetid').val();	    
	    var reviewdate = $('#reviewdate').val(); 
	    var prospect = $('#prospect').val(); 
	    var testdrive = $('#testdrive').val(); 
	    var closed = $('#closed').val(); 
	    var minute = $('#minute').val(); 
	    var reviewby = $('#reviewby').val(); 

	    var json = {
	    		"reviewid" : reviewid,
	    		"period" : period,
	    		"userid" : userid,
	    		"username" : username,
	    		"teamid" : teamid,
	    		"teamname" : teamname,
	    		"branchid" : branchid,
	    		"branchname" : branchname,
	    		"companyid" : companyid,
	    		"companyname" : companyname,
	    		"targetid" : targetid,
	    		"teamtargetid" : teamtargetid,
	    		"reviewdate" : reviewdate,
	    		"prospect" : prospect,
	    		"testdrive" : testdrive,
	    		"closed" : closed,
	    		"minute" : minute,
	    		"reviewby" : reviewby
		};
        $.ajax({
            url: base+"/review/delete",
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
	})
	</script>		
</body>
</html>