<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<!-- Wizard -->
<script src="js/plugins/wizard/jquery.form.wizard.min.js"></script>
<body>
	<jsp:include page="_userNavigation.jsp" />
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
						<a href="listProspects">Prospect</a>
					</li>
				</ul>
			</div>		
			<div class="row">
				<div class="span12">
					<div class="box">
						<div class="box-content">
							<form:form id="entry-form" action="" method="post" modelAttribute="prospect" class='form-horizontal form-wizard'>
				            <form:hidden path="prospectid"/>
				            <form:hidden path="userid"/>
							<div class="step" id="firstStep">
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2">First Name</label>
									<div class="col-sm-5">
										<form:input type="text" path="firstname" name="firstname" id="firstname" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="lastname" class="control-label col-sm-2">Last Name</label>
									<div class="col-sm-5">
										<form:input type="text" path="lastname" name="lastname" id="lastname" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="mobile" class="control-label col-sm-2">Mobile</label>
									<div class="col-sm-3">
										<form:input type="text" path="mobile" name="mobile" id="mobile" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="text" class="control-label col-sm-2">Status</label>
									<div class="col-sm-1">
										<form:select name="status" path="status" id="status" items="${statuslist}" />
									</div>
								</div>
								<div class="form-group">
									<label for="text" class="control-label col-sm-2">Gender</label>
									<div class="col-sm-1">
										<form:select name="gender" path="gender" id="gender" items="${genderlist}"/>
									</div>
								</div>
								<div class="form-group">
									<label for="email" class="control-label col-sm-2">Email</label>
									<div class="col-sm-5">
										<form:input type="text" name="email" path="email" id="email" class="form-control"/>
									</div>
								</div>
							</div>
							<div class="step" id="secondStep">
								<div class="form-group">
									<label for="text" class="control-label col-sm-1">Home</label>
								</div>
								<div class="form-group">
									<label for="country" class="control-label col-sm-1">Country</label>
									<div class="col-sm-3">
										<form:input type="text" path="homeaddress.country" name="homeaddress.country" id="hcountry" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="zipcode" class="control-label col-sm-1">Zip Code</label>
									<div class="col-sm-3">
										<form:input type="text" name="homeaddress.zipcode" path="homeaddress.zipcode" id="hzipcode" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="state" class="control-label col-sm-1">State</label>
									<div class="col-sm-3">
										<form:input type="text" path="homeaddress.state" name="homeaddress.state" id="hstate" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="city" class="control-label col-sm-1">City</label>
									<div class="col-sm-3">
										<form:input type="text" path="homeaddress.city" name="homeaddress.city" id="hcity" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="street" class="control-label col-sm-1">Street</label>
									<div class="col-sm-5">
										<form:input type="text" path="homeaddress.street" name="homeaddress.street" id="hstreet" class="form-control" />
									</div>
								</div>		
								<div class="form-group">
									<label for="htelno" class="control-label col-sm-1">Tel. No</label>
									<div class="col-sm-3">
										<form:input type="text" path="htelno" name="htelno" id="htelno" class="form-control" />
									</div>
								</div>
							</div>
							<div class="step" id="thirdStep">
								<div class="form-group">
									<label for="text" class="control-label col-sm-1">Office</label>
								</div>
								<div class="form-group">
									<label for="country" class="control-label col-sm-1">Country</label>
									<div class="col-sm-3">
										<form:input type="text" path="workaddress.country" name="workaddress.country" id="wcountry" class="form-control" />
									</div>
								</div>								
								<div class="form-group">
									<label for="zipcode" class="control-label col-sm-1">Zip Code</label>
									<div class="col-sm-3">
										<form:input type="text" name="workaddress.zipcode" path="workaddress.zipcode" id="wzipcode" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="state" class="control-label col-sm-1">State</label>
									<div class="col-sm-3">
										<form:input type="text" path="workaddress.state" name="workaddress.state" id="wstate" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="city" class="control-label col-sm-1">City</label>
									<div class="col-sm-3">
										<form:input type="text" path="workaddress.city" name="workaddress.city" id="wcity" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="street" class="control-label col-sm-1">Street</label>
									<div class="col-sm-5">
										<form:input type="text" path="workaddress.street" name="workaddress.street" id="wstreet" class="form-control" />
									</div>
								</div>		
								<div class="form-group">
									<label for="wtelno" class="control-label col-sm-1">Tel. No</label>
									<div class="col-sm-3">
										<form:input type="text" path="wtelno" name="wtelno" id="wtelno" class="form-control" />
									</div>
								</div>						
							</div>
							<div class="step" id="fourthStep">
								<div class="form-group">
									<label for="text" class="control-label col-sm-2">Source</label>
									<div class="col-sm-3">
										<form:select name="source" path="source" id="source" items="${sourcelist}" />
									</div>
								</div>
							</div>
							
							<div class="form-actions">
								<input type="reset" class="btn" onclick="location.href='listProspects'" value="Back" id="back">
								<input type="submit" class="btn btn-primary" value="Save" id="next">
								<input type="button" class="btn" value="Done" id="done">
							</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$("#entry-form").validate({ 
		rules: { 
			firstname: "required",
			mobile: "required"
		}
	});
	
	$('#done').click(function (e) {
		if(!$('#entry-form').valid()) return;

		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var prospectid = $('#prospectid').val(); 
	    var firstname = $('#firstname').val(); 
	    var lastname = $('#lastname').val(); 
	    var userid = $('#userid').val(); 
	    var source = $('#source').val(); 
	    var hcountry = $('#hcountry').val(); 
	    var hzipcode = $('#hzipcode').val(); 
	    var hstate = $('#hstate').val(); 
	    var hcity = $('#hcity').val(); 
	    var hstreet = $('#hstreet').val(); 
	    var mobile = $('#mobile').val(); 
	    var htelno = $('#htelno').val(); 
	    var wcountry = $('#wcountry').val(); 
	    var wzipcode = $('#wzipcode').val(); 
	    var wstate = $('#wstate').val(); 
	    var wcity = $('#wcity').val(); 
	    var wstreet = $('#wstreet').val(); 
	    var wtelno = $('#wtelno').val(); 
	    var email = $('#email').val(); 
	    var gender = $('#gender').val(); 
	    var email = $('#email').val(); 
	    var status = $('#status').val(); 
	
	    var json = {
	    		"prospectid" : prospectid,
	    		"firstname" : firstname,
	    		"lastname" : lastname,
	    		"userid" : userid,
	    		"source" : source,
	    		"homeaddress" : {
		    		"country" : hcountry,
		    		"zipcode" : hzipcode,
		    		"state" : hstate,
		    		"city" : hcity,
	    			"street" : hstreet
	    		},
	    		"mobile" : mobile,
	    		"htelno" : htelno,
	    		"workaddress" : {
		    		"country" : wcountry,
		    		"zipcode" : wzipcode,
		    		"state" : wstate,
		    		"city" : wcity,
	    			"street" : wstreet
	    		},
	    		"wtelno" : wtelno,
	    		"gender" : gender,
	    		"email" : email,
	    		"status" : status
		};
	    if (json.prospectid=="0"){
	        $.ajax({
	            url: base+"/prospect/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listProspects";
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/prospect/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listProspects";
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });
	    }
	    return true;
	});
	
	$('#next').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		if ($('#next').val()=="Submit"){
			if(!$('#entry-form').valid()) return;

			var base = $('#base').val();
	    	if (window.location.protocol === 'https:') {
	    	    base = base.replace("http", "https");
	    	}	    	
	
	    	var prospectid = $('#prospectid').val(); 
		    var firstname = $('#firstname').val(); 
		    var lastname = $('#lastname').val(); 
		    var userid = $('#userid').val(); 
		    var source = $('#source').val(); 
		    var hcountry = $('#hcountry').val(); 
		    var hzipcode = $('#hzipcode').val(); 
		    var hstate = $('#hstate').val(); 
		    var hcity = $('#hcity').val(); 
		    var hstreet = $('#hstreet').val(); 
		    var mobile = $('#mobile').val(); 
		    var htelno = $('#htelno').val(); 
		    var wcountry = $('#wcountry').val(); 
		    var wzipcode = $('#wzipcode').val(); 
		    var wstate = $('#wstate').val(); 
		    var wcity = $('#wcity').val(); 
		    var wstreet = $('#wstreet').val(); 
		    var wtelno = $('#wtelno').val(); 
		    var gender = $('#gender').val(); 
		    var email = $('#email').val(); 
		    var status = $('#status').val(); 
		
		    var json = {
		    		"prospectid" : prospectid,
		    		"firstname" : firstname,
		    		"lastname" : lastname,
		    		"userid" : userid,
		    		"source" : source,
		    		"homeaddress" : {
			    		"country" : hcountry,
			    		"zipcode" : hzipcode,
			    		"state" : hstate,
			    		"city" : hcity,
		    			"street" : hstreet
		    		},
		    		"mobile" : mobile,
		    		"htelno" : htelno,
		    		"workaddress" : {
			    		"country" : wcountry,
			    		"zipcode" : wzipcode,
			    		"state" : wstate,
			    		"city" : wcity,
		    			"street" : wstreet
		    		},
		    		"wtelno" : wtelno,
		    		"gender" : gender,
		    		"email" : email,
		    		"status" : status
			};
		    if (json.prospectid=="0"){
		        $.ajax({
		            url: base+"/prospect/create",
		            type: 'POST',
		            contentType: "application/json",
		            dataType: "json",
		            data: JSON.stringify(json), 
		            success:function(data, Textstatus, jqXHR){
		                window.location.href = base+"/listProspects";
		            },
		            error:function(jqXhr, Textstatus){
		            }
		        });    	
		    }
		    else {
		        $.ajax({
		            url: base+"/prospect/update",
		            type: 'POST',
		            contentType: "application/json",
		            dataType: "json",
		            data: JSON.stringify(json),
		            success:function(data, Textstatus, jqXHR){
		                window.location.href = base+"/listProspects";
		            },
		            error:function(jqXhr, Textstatus){
		            }
		        });
		    }
		    return true;
		}
	});
	</script>	
</body>
</html>