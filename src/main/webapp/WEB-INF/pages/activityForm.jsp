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
<!-- Wizard -->
<script src="js/plugins/wizard/jquery.form.wizard.min.js"></script>
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
					<a href="listActivity?prospectid=${prospect.prospectid}">Activity</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
				        <form:form id="entry-form" action="" method="post" modelAttribute="activity" class='form-horizontal form-wizard'>
			            <form:hidden path="userid"/>        
			            <form:hidden path="prospectid"/>        
			            <form:hidden path="activityid"/>
			            <form:hidden path="quotationid"/>
			            <form:hidden path="closedid"/>
					    <div class="step" id="firstStep">
							<div class="form-group">
								<label for="requestdate" class="control-label col-sm-2">Activity Date</label>
								<div class="col-sm-2">
									<form:input type="date" path="activitydate" name="activitydate" id="activitydate" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Brand</label>
								<div class="col-sm-2">
									<form:select name="brandname" path="brandname" id="brandname" items="${brandlist}" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="text" class="control-label col-sm-2">Model</label>
								<div class="col-sm-2">
									<form:select name="modelname" path="modelname" id="modelname" items="${modellist}" class="form-control"/>
								</div>
							</div>
					    </div>
					    <div class="step" id="secondStep">
							<div class="form-group">
								<label for="demo" class="control-label col-sm-1">Presentation</label>
								<div class="col-sm-1">
									<form:checkbox name="demo" path="demo" id="demo" class="form-control"/>
								</div>
								<label for="demostatus" class="control-label col-sm-1">Done</label>
								<div class="col-sm-1">
									<form:checkbox path="demostatus" id="demostatus" class="form-control"/>
								</div>
							</div>					    
							<div class="form-group">
								<label for="testdrive" class="control-label col-sm-1">Test Drive</label>
								<div class="col-sm-1">
									<form:checkbox name="testdrive" path="testdrive" id="testdrive" class="form-control"/>
								</div>
								<label for="testdrivestatus" class="control-label col-sm-1">Done</label>
								<div class="col-sm-1">
									<form:checkbox path="testdrivestatus" id="testdrivestatus" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="quotation" class="control-label col-sm-1">Quotation</label>
								<div class="col-sm-1">
									<form:checkbox name="quotation" path="quotation" id="quotation" class="form-control"/>
								</div>
							</div>					    
							<div class="form-group">
								<label for="closed" class="control-label col-sm-1">Closed</label>
								<div class="col-sm-1">
									<form:checkbox name="closed" path="closed" id="closed" class="form-control" />
								</div>
							</div>					    							
					    </div>
					    <div class="step" id="thirdStep">
							<div class="form-group">
								<label for="followup" class="control-label col-sm-2">Follow Up</label>
								<div class="col-sm-1">
									<form:checkbox name="followup" path="followup" id="followup" class="form-control" />
								</div>
							</div>					    
							<div class="form-group">
								<label for="followupremark" class="control-label col-sm-2">Remark</label>
								<div class="col-sm-5">
									<form:input type="text" path="followupremark" name="followupremark" id="followupremark" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label for="followupstatus" class="control-label col-sm-2">Done</label>
								<div class="col-sm-1">
									<form:checkbox path="followupstatus" id="followupstatus" class="form-control"/>
								</div>
							</div>
					    </div>
					    <div class="step" id="fourthStep">
							<div class="form-group">
								<label for="lost" class="control-label col-sm-1">Lost</label>
								<div class="col-sm-1">
									<form:checkbox name="lost" path="lost" id="lost" class="form-control"/>
								</div>
							</div>					    
							<div class="form-group">
								<label for="lostremark" class="control-label col-sm-1">Remark</label>
								<div class="col-sm-5">
									<form:textarea type="textarea" rows="5" path="lostremark" name="lostremark" id="lostremark" class="form-control"/>
								</div>
							</div>						
					    </div>
						<div>
							<input type="reset" class="btn" onclick="location.href='listActivity?prospectid=${activity.prospectid}'" value="Back" id="back">
							<input type="submit" class="btn btn-primary" value="Save" id="next">
							<input type="button" class="btn" value="Done" id="done">
							<input type="button" class="btn" value="Delete" id="delete">
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
			activitydate: "required",
			brandname: "required",
			modelname: "required",
			followupremark: { 
				required: $('#followup').prop('checked') 
			},
			lostremark: { 
				required: $('#lost').prop('checked') 
			} 
	},
		messages: { 
			followupremark: "Please enter follow up remark.",
			lostremark: "Please enter lost remark."
		}
	});

	$('#done').click(function (e) {
		if(!$('#entry-form').valid()) return;

		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var activityid = $('#activityid').val(); 
	    var userid = $('#userid').val(); 
	    var prospectid = $('#prospectid').val(); 
	    var activitydate = $('#activitydate').val(); 
	    var brandid = ""; 
	    var brandname = $('#brandname').val(); 
	    var modelid = ""; 
	    var modelname = $('#modelname').val(); 
	    var demo = $('#demo').prop('checked'); 
	    var testdrive = $('#testdrive').prop('checked'); 
	    var quotation = $('#quotation').prop('checked'); 
	    var followup = $('#followup').prop('checked'); 
	    var closed = $('#closed').prop('checked'); 
	    var lost = $('#lost').prop('checked'); 
	    var demostatus = $('#demostatus').prop('checked');  
	    var testdrivestatus = $('#testdrivestatus').prop('checked');  
	    var followupremark = $('#followupremark').val(); 
	    var followupstatus = $('#followupstatus').prop('checked');  
	    var quotationid = $('#quotationid').val(); 
	    var closedid = $('#closedid').val(); 
	    var lostremark = $('#lostremark').val();  

	    var json = {
	    		"activityid" : activityid,
	    		"userid" : userid,
	    		"prospectid" : prospectid,
	    		"activitydate" : activitydate,
	    		"brandid" : brandid,
	    		"brandname" : brandname,
	    		"modelid" : modelid,
	    		"modelname" : modelname,
	    		"demo" : demo,
	    		"testdrive" : testdrive,
	    		"quotation" : quotation,
	    		"followup" : followup,
	    		"closed" : closed,
	    		"lost" : lost,
	    		"demostatus" : demostatus,
	    		"testdrivestatus" : testdrivestatus,
	    		"followupremark" : followupremark,
	    		"followupstatus" : followupstatus,
	    		"quotationid" : quotationid,
	    		"closedid" : closedid,
	    		"lostremark" : lostremark
	    		
		};
	    if (json.activityid=="0"){
	        $.ajax({
	            url: base+"/activity/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	            	if (quotation==true){
	            		window.location.href = base+"/addQuotation?prospectid="+prospectid;	            		
	            	}
	            	else if(closed==true){
	            		window.location.href = base+"/addClosed?prospectid="+prospectid;	            			            		
	            	}
	            	else {
		                window.location.href = base+"/listActivity?prospectid="+prospectid;	            		
	            	}
	            },
	            error:function(jqXhr, Textstatus){
		            alert(JSON.stringify(jqXhr));
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/activity/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	            	if (quotation==true){
	            		if (quotationid==0){
		            		window.location.href = base+"/addQuotation?prospectid="+prospectid;	            			            			
	            		}
	            		else {
		            		window.location.href = base+"/editQuotation?quotationid="+quotationid;	            			            			
	            		}
	            	}
	            	else if(closed==true){
	            		if (closedid==0){
		            		window.location.href = base+"/addClosed?prospectid="+prospectid;	            			            			            			
	            		}
	            		else {
		            		window.location.href = base+"/editClosed?closedid="+closedid;	            			            			            			
	            		}
	            	}
	            	else {
		                window.location.href = base+"/listActivity?prospectid="+prospectid;	            		
	            	}
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
	
	    	var activityid = $('#activityid').val(); 
		    var userid = $('#userid').val(); 
		    var prospectid = $('#prospectid').val(); 
		    var activitydate = $('#activitydate').val(); 
		    var brandid = ""; 
		    var brandname = $('#brandname').val(); 
		    var modelid = ""; 
		    var modelname = $('#modelname').val(); 
		    var demo = $('#demo').prop('checked'); 
		    var testdrive = $('#testdrive').prop('checked'); 
		    var quotation = $('#quotation').prop('checked'); 
		    var followup = $('#followup').prop('checked'); 
		    var closed = $('#closed').prop('checked'); 
		    var lost = $('#lost').prop('checked'); 
		    var demostatus = $('#demostatus').prop('checked');  
		    var testdrivestatus = $('#testdrivestatus').prop('checked');  
		    var followupremark = $('#followupremark').val(); 
		    var followupstatus = $('#followupstatus').prop('checked');  
		    var quotationid = $('#quotationid').val(); 
		    var closedid = $('#closedid').val(); 
		    var lostremark = $('#lostremark').val();  
	
		    var json = {
		    		"activityid" : activityid,
		    		"userid" : userid,
		    		"prospectid" : prospectid,
		    		"activitydate" : activitydate,
		    		"brandid" : brandid,
		    		"brandname" : brandname,
		    		"modelid" : modelid,
		    		"modelname" : modelname,
		    		"demo" : demo,
		    		"testdrive" : testdrive,
		    		"quotation" : quotation,
		    		"followup" : followup,
		    		"closed" : closed,
		    		"lost" : lost,
		    		"demostatus" : demostatus,
		    		"testdrivestatus" : testdrivestatus,
		    		"followupremark" : followupremark,
		    		"followupstatus" : followupstatus,
		    		"quotationid" : quotationid,
		    		"closedid" : closedid,
		    		"lostremark" : lostremark
		    		
			};
		    if (json.activityid=="0"){
		        $.ajax({
		            url: base+"/activity/create",
		            type: 'POST',
		            contentType: "application/json",
		            dataType: "json",
		            data: JSON.stringify(json), 
		            success:function(data, Textstatus, jqXHR){
		                window.location.href = base+"/listActivity?prospectid="+prospectid;
		            },
		            error:function(jqXhr, Textstatus){
			            alert(JSON.stringify(jqXhr));
		            }
		        });    	
		    }
		    else {
		        $.ajax({
		            url: base+"/activity/update",
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
		}
	});
	
	$('#done').click(function (e) {
		if(!$('#entry-form').valid()) return;

		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var activityid = $('#activityid').val(); 
	    var userid = $('#userid').val(); 
	    var prospectid = $('#prospectid').val(); 
	    var activitydate = $('#activitydate').val(); 
	    var brandid = ""; 
	    var brandname = $('#brandname').val(); 
	    var modelid = ""; 
	    var modelname = $('#modelname').val(); 
	    var demo = $('#demo').prop('checked'); 
	    var testdrive = $('#testdrive').prop('checked'); 
	    var quotation = $('#quotation').prop('checked'); 
	    var followup = $('#followup').prop('checked'); 
	    var closed = $('#closed').prop('checked'); 
	    var lost = $('#lost').prop('checked'); 
	    var demostatus = $('#demostatus').prop('checked');  
	    var testdrivestatus = $('#testdrivestatus').prop('checked');  
	    var followupremark = $('#followupremark').val(); 
	    var followupstatus = $('#followupstatus').prop('checked');  
	    var quotationid = $('#quotationid').val(); 
	    var closedid = $('#closedid').val(); 
	    var lostremark = $('#lostremark').val();  

	    var json = {
	    		"activityid" : activityid,
	    		"userid" : userid,
	    		"prospectid" : prospectid,
	    		"activitydate" : activitydate,
	    		"brandid" : brandid,
	    		"brandname" : brandname,
	    		"modelid" : modelid,
	    		"modelname" : modelname,
	    		"demo" : demo,
	    		"testdrive" : testdrive,
	    		"quotation" : quotation,
	    		"followup" : followup,
	    		"closed" : closed,
	    		"lost" : lost,
	    		"demostatus" : demostatus,
	    		"testdrivestatus" : testdrivestatus,
	    		"followupremark" : followupremark,
	    		"followupstatus" : followupstatus,
	    		"quotationid" : quotationid,
	    		"closedid" : closedid,
	    		"lostremark" : lostremark
	    		
		};
        $.ajax({
            url: base+"/activity/delete",
            type: 'POST',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(json),
            success:function(data, Textstatus, jqXHR){
                window.location.href = base+"/listActivity?prospectid="+prospectid;
            },
            error:function(jqXhr, Textstatus){
            }
		})
	});	    
	</script>	
</body>
</html>