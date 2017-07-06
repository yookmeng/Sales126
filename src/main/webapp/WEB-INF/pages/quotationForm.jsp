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
			        <form:form action="" method="post" modelAttribute="quotation" class='form-horizontal form-wizard'>
		            <form:hidden path="quotationid"/>
		            <form:hidden path="prospectid"/>
		            <form:hidden path="activityid"/>
		            <form:hidden path="brandid"/>
		            <form:hidden path="modelid"/>
		            <form:hidden path="colour"/>
		            <form:hidden path="quotationdate"/>
		            <form:hidden path="retailprice"/>
		            <form:hidden path="registrationfee"/>
		            <form:hidden path="handlingcharges"/>
		            <form:hidden path="extendedwarranty"/>
					<div class="form-group">
						<label for="suminsured" class="control-label col-sm-2">Sum Insured</label>
						<div class="col-sm-2">
							<form:input type="text" path="suminsured" name="suminsured" id="suminsured" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="premium" class="control-label col-sm-2">Premium</label>
						<div class="col-sm-2">
							<form:input type="text" path="premium" name="premium" id="premium" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="ncd" class="control-label col-sm-2">NCD</label>
						<div class="col-sm-1">
							<form:select type="text" path="ncd" name="ncd" id="ncd" items="${ncdlist}" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="premiumafterncd" class="control-label col-sm-2">Premium After NCD</label>
						<div class="col-sm-1">
							<form:input type="text" path="premiumafterncd" name="premiumafterncd" id="premiumafterncd" items="${ncdlist}" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="roadtax" class="control-label col-sm-2">Road Tax</label>
						<div class="col-sm-1">
							<form:input type="text" path="roadtax" name="roadtax" id="roadtax" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="othercharges" class="control-label col-sm-2">Other Charges</label>
						<div class="col-sm-2">
							<form:input type="text" path="othercharges" name="othercharges" id="othercharges" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="discount" class="control-label col-sm-2">Discount</label>
						<div class="col-sm-2">
							<form:input type="text" path="discount" name="discount" id="discount" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="quoteamount" class="control-label col-sm-2">Quote Amount</label>
						<div class="col-sm-2">
							<form:input type="text" path="quoteamount" name="quoteamount" id="quoteamount" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="term" class="control-label col-sm-2">Term</label>
						<div class="col-sm-5">
							<form:input type="text" path="term" name="term" id="term" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="remark" class="control-label col-sm-2">Remark</label>
						<div class="col-sm-10">
							<form:input type="text" path="remark" name="remark" id="remark" class="form-control" />
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
	$('#premium').change(calc_quote);
	$('#ncd').change(calc_quote);
	$('#othercharges').change(calc_quote);
	$('#discount').change(calc_quote);
	function calc_quote(e){
		var retailprice = parseFloat($('#retailprice').val());
		var premium = parseFloat($('#premium').val());
		var ncd = parseFloat($('#ncd').val());	
		var newpremium = premium - ((ncd /100) * premium);
		$('#premiumafterncd').val(newpremium);		
		var othercharges = parseFloat($('#othercharges').val());
		var roadtax = parseFloat($('#roadtax').val());
		var discount = parseFloat($('#discount').val());
		var quote = Math.round((retailprice + newpremium + roadtax + othercharges - discount)*100)/100;
		$('#quoteamount').val(quote);		
	};
	
	$('#btnSave').click(function (e) {
		e.preventDefault(); // <------------------ stop default behaviour of button
		
		var base = $('#base').val();
    	if (window.location.protocol === 'https:') {
    	    base = base.replace("http", "https");
    	}	    	

    	var quotationid = $('#quotationid').val();
    	var quotationdate = $('#quotationdate').val();
    	var prospectid = $('#prospectid').val();
    	var prospectname = "";    	
    	var activityid = $('#activityid').val();
    	var brandid = $('#brandid').val();
    	var brandname = "";    	
	    var modelid = $('#modelid').val(); 
    	var modelname = "";    
    	var colour = $('#colour').val();	
	    var retailprice = $('#retailprice').val(); 	    
	    var suminsured = $('#suminsured').val(); 
	    var ncd = $('#ncd').val(); 
		var premium = $('#premium').val();	
		var premiumafterncd = $('#premiumafterncd').val();	
		var roadtax = $('#roadtax').val();	
		var registrationfee = $('#registrationfee').val();	
		var handlingcharges = $('#handlingcharges').val();	
		var extendedwarranty = $('#extendedwarranty').val();	
		var othercharges = $('#othercharges').val();	
		var discount = $('#discount').val();	
		var quoteamount = $('#quoteamount').val();	
		var term = $('#term').val();	
		var remark = $('#remark').val();	

		var json = {
	    		"quotationid" : quotationid,
	    		"quotationdate" : quotationdate,
	    		"prospectid" : prospectid,
	    		"prospectname" : prospectname,
	    		"activityid" : activityid,
	    		"brandid" : brandid,
	    		"brandname" : brandname,
	    		"modelid" : modelid,	    		
	    		"modelname" : modelname,
	    		"colour" : colour,
	    		"retailprice" : retailprice,
	    		"suminsured" : suminsured,
	    		"ncd" : ncd,
	    		"premium" : premium,
	    		"premiumafterncd" : premiumafterncd,
	    		"roadtax" : roadtax,
	    		"registrationfee" : registrationfee,
	    		"handlingcharges" : handlingcharges,
	    		"extendedwarranty" : extendedwarranty,
	    		"othercharges" : othercharges,
	    		"discount" : discount,
	    		"quoteamount" : quoteamount,
	    		"term" : term,
	    		"remark" : remark	    		
		};
	    if (json.quotationid=="0"){
	        $.ajax({
	            url: base+"/quotation/create",
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
	            url: base+"/quotation/update",
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