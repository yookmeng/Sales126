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
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listContacts">Contact</a>
				</li>
			</ul>
		</div>		
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
						<form:form id="entry-form" action="" method="post" modelAttribute="contact" class='form-horizontal form-wizard'>
						    <form:hidden path="contactid"/>
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
										<form:input type="text" name="mobile" path="mobile" id="mobile" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="home" class="control-label col-sm-2">Home</label>
									<div class="col-sm-3">
										<form:input type="text" name="home" path="home" id="home" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="work" class="control-label col-sm-2">Work</label>
									<div class="col-sm-3">
										<form:input type="text" name="work" path="work" id="work" class="form-control"/>
									</div>
								</div>
							</div>
							<div class="step" id="secondStep">
								<div class="form-group">
									<label for="country" class="control-label col-sm-2">Country</label>
									<div class="col-sm-3">
										<form:input type="text" path="address.country" name="address.country" id="country" class="form-control" />
									</div>
								</div>								
								<div class="form-group">
									<label for="zipcode" class="control-label col-sm-2">Zip Code</label>
									<div class="col-sm-3">
										<form:input type="text" name="address.zipcode" path="address.zipcode" id="zipcode" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="state" class="control-label col-sm-2">State</label>
									<div class="col-sm-3">
										<form:input type="text" path="address.state" name="address.state" id="state" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="city" class="control-label col-sm-2">City</label>
									<div class="col-sm-3">
										<form:input type="text" path="address.city" name="address.city" id="city" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="street" class="control-label col-sm-2">Street</label>
									<div class="col-sm-10">
										<form:input type="text" path="address.street" name="address.street" id="street" class="form-control" />
									</div>
								</div>		
							</div>
							<div class="step" id="thirdStep">
								<div class="form-group">
									<label for="birthday" class="control-label col-sm-2">Birthday</label>
									<div class="col-sm-2">
										<form:input type="date" path="birthday" name="birthday" id="birthday" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for="email" class="control-label col-sm-2">Email</label>
									<div class="col-sm-5">
										<form:input type="text" path="email" name="email" id="email" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="company" class="control-label col-sm-2">Company</label>
									<div class="col-sm-5">
										<form:input type="text" path="company" name="company" id="company" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="title" class="control-label col-sm-2">Title</label>
									<div class="col-sm-5">
										<form:input type="text" path="title" name="title" id="title" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="note" class="control-label col-sm-2">Note</label>
									<div class="col-sm-5">
										<form:input type="text" name="note" path="note" id="note" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<label for="website" class="control-label col-sm-2">Web Site</label>
									<div class="col-sm-5">
										<form:input type="text" path="website" name="website" id="website" class="form-control" />
									</div>
								</div>
							</div>
							<div>
								<input type="reset" class="btn" name="Back" value="Back" onclick="location.href='listContacts'">
								<input type="submit" class="btn btn-primary" value="Save" id="next">
								<input type="button" class="btn" value="Done" id="done">
							</div>
						</form:form>
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

    	var contactid = $('#contactid').val(); 
	    var userid = $('#userid').val(); 
	    var firstname = $('#firstname').val(); 
	    var lastname = $('#lastname').val(); 
	    var mobile = $('#mobile').val(); 
	    var home = $('#home').val(); 
	    var work = $('#work').val(); 
	    var birthday = $('#birthday').val(); 
	    var email = $('#email').val(); 
	    var website = $('#website').val(); 
	    var country = $('#country').val(); 
	    var zipcode = $('#zipcode').val(); 
	    var state = $('#state').val(); 
	    var city = $('#city').val(); 
	    var street = $('#street').val(); 
	    var company = $('#company').val(); 
	    var title = $('#title').val(); 
	    var note = $('#note').val(); 
	    var json = {
	    		"contactid" : contactid,
	    		"userid" : userid,
	    		"firstname" : firstname,
	    		"lastname" : lastname,
	    		"mobile" : mobile,
	    		"work" : work,
	    		"home" : home,
	    		"email" : email,
	    		"birthday" : birthday,
	    		"address" : {
		    		"country" : country,
		    		"zipcode" : zipcode,
		    		"state" : state,
		    		"city" : city,
	    			"street" : street
	    		},
	    		"company" : company,
	    		"title" : title,
	    		"note" : note,
	    		"website" : website
		};
	    if (json.contactid=="0"){
	        $.ajax({
	            url: base+"/contact/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listContacts";
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/contact/update/"+contactid,
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listContacts";
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
	
	    	var contactid = $('#contactid').val(); 
		    var userid = $('#userid').val(); 
		    var firstname = $('#firstname').val(); 
		    var lastname = $('#lastname').val(); 
		    var mobile = $('#mobile').val(); 
		    var home = $('#home').val(); 
		    var work = $('#work').val(); 
		    var birthday = $('#birthday').val(); 
		    var email = $('#email').val(); 
		    var website = $('#website').val(); 
		    var country = $('#country').val(); 
		    var zipcode = $('#zipcode').val(); 
		    var state = $('#state').val(); 
		    var city = $('#city').val(); 
		    var street = $('#street').val(); 
		    var company = $('#company').val(); 
		    var title = $('#title').val(); 
		    var note = $('#note').val(); 
		    var json = {
		    		"contactid" : contactid,
		    		"userid" : userid,
		    		"firstname" : firstname,
		    		"lastname" : lastname,
		    		"mobile" : mobile,
		    		"work" : work,
		    		"home" : home,
		    		"email" : email,
		    		"birthday" : birthday,
		    		"address" : {
			    		"country" : country,
			    		"zipcode" : zipcode,
			    		"state" : state,
			    		"city" : city,
		    			"street" : street
		    		},
		    		"company" : company,
		    		"title" : title,
		    		"note" : note,
		    		"website" : website
			};
		    if (json.contactid=="0"){
		        $.ajax({
		            url: base+"/contact/create",
		            type: 'POST',
		            contentType: "application/json",
		            dataType: "json",
		            data: JSON.stringify(json), 
		            success:function(data, Textstatus, jqXHR){
		                window.location.href = base+"/listContacts";
		            },
		            error:function(jqXhr, Textstatus){
		            }
		        });    	
		    }
		    else {
		        $.ajax({
		            url: base+"/contact/update/"+contactid,
		            type: 'POST',
		            contentType: "application/json",
		            dataType: "json",
		            data: JSON.stringify(json),
		            success:function(data, Textstatus, jqXHR){
		                window.location.href = base+"/listContacts";
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