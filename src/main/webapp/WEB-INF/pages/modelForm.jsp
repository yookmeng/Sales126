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
	<jsp:include page="_saNavigation.jsp" />
	<div id="main" class="container-fluid">
	   	<input type="hidden" value="${base}" name="base" id="base"/>	
	   	<input type="hidden" value="brand" name="brand" />
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listBrand">Brand</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listModel?brandid=${brand.brandid}">Model</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<div class="box">
					<div class="box-content">
			        <form:form action="" method="post" modelAttribute="model" class='form-horizontal form-wizard'>
		            <form:hidden path="brandid"/>        
		            <form:hidden path="modelid"/>
					<div class="form-group">
						<label for="modelname" class="control-label col-sm-2">Model Name</label>
						<div class="col-sm-5">
							<form:input type="text" path="modelname" name="modelname" id="modelname" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="sellingmodel" class="control-label col-sm-2">Selling Model</label>
						<div class="col-sm-1">
							<form:checkbox path="sellingmodel" id="sellingmodel" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="commission" class="control-label col-sm-2">Commission</label>
						<div class="col-sm-2">
							<form:input type="number" step="0.01" min="0" path="commission" name="commission" id="commission" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="price" class="control-label col-sm-2">Price</label>
						<div class="col-sm-2">
							<form:input type="number" step="0.01" min="0" path="price" name="price" id="price" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="suminsured" class="control-label col-sm-2">Recommended Sum Insured</label>
						<div class="col-sm-2">
							<form:input type="number" step="0.01" min="0" path="suminsured" name="suminsured" id="suminsured" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="premium" class="control-label col-sm-2">Premium</label>
						<div class="col-sm-2">
							<form:input type="number" step="0.01" min="0" path="premium" name="premium" id="premium" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="roadtax" class="control-label col-sm-2">Road Tax</label>
						<div class="col-sm-2">
							<form:input type="number" step="0.01" min="0" path="roadtax" name="roadtax" id="roadtax" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="colour" class="control-label col-sm-2">Colour</label>
						<div class="col-sm-5">
							<form:input type="text" path="colour" name="colour" id="colour" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="enginetype" class="control-label col-sm-2">Engine Type</label>
						<div class="col-sm-5">
							<form:input type="text" path="enginetype" name="enginetype" id="enginetype" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="fuelsupplysystem" class="control-label col-sm-2">Fuel Supply System</label>
						<div class="col-sm-5">
							<form:input type="text" path="fuelsupplysystem" name="fuelsupplysystem" id="fuelsupplysystem" class="form-control" />
						</div>
					</div>					
					<div class="form-group">
						<label for="displacement" class="control-label col-sm-2">Displacement</label>
						<div class="col-sm-5">
							<form:input type="text" path="displacement" name="displacement" id="displacement" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="maxpower" class="control-label col-sm-2">Maximum Power</label>
						<div class="col-sm-5">
							<form:input type="text" path="maxpower" name="maxpower" id="maxpower" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="maxtorque" class="control-label col-sm-2">Maximum Torque</label>
						<div class="col-sm-5">
							<form:input type="text" path="maxtorque" name="maxtorque" id="maxtorque" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="transmission" class="control-label col-sm-2">Transmission</label>
						<div class="col-sm-5">
							<form:input type="text" path="transmission" name="transmission" id="transmission" class="form-control" />
						</div>
					</div>
					<div class="form-actions">
						<input type="reset" class="btn" onclick="location.href='listModel?brandid=${model.brandid}'" value="Back" id="back">						
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

    	var brandid = $('#brandid').val(); 
	    var modelid = $('#modelid').val(); 
	    var modelname = $('#modelname').val(); 	    
	    var colour = $('#colour').val(); 	    
	    var sellingmodel = $('#sellingmodel').prop('checked');
	    var commission = $('#commission').val(); 
	    var price = $('#price').val(); 
	    var suminsured = $('#suminsured').val(); 
	    var premium = $('#premium').val(); 
	    var roadtax = $('#roadtax').val(); 
		var enginetype = $('#enginetype').val();	
		var fuelsupplysystem = $('#fuelsupplysystem').val();	
		var displacement = $('#displacement').val();	
		var maxpower = $('#maxpower').val();	
		var maxtorque = $('#maxtorque').val();	
		var transmission = $('#transmission').val();	

		var json = {
	    		"modelid" : modelid,
	    		"modelname" : modelname,	    		
	    		"brandid" : brandid,
	    		"sellingmodel" : sellingmodel,
	    		"commission" : commission,
	    		"price" : price,
	    		"suminsured" : suminsured,
	    		"premium" : premium,
	    		"roadtax" : roadtax,
	    		"colour" : colour,
	    		"enginetype" : enginetype,
	    		"fuelsupplysystem" : fuelsupplysystem,
	    		"displacement" : displacement,
	    		"maxpower" : maxpower,
	    		"maxtorque" : maxtorque,
	    		"transmission" : transmission

		};
	    if (json.modelid=="0"){
	        $.ajax({
	            url: base+"/model/create",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json), 
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listModel?brandid="+brandid;
	            },
	            error:function(jqXhr, Textstatus){
	            }
	        });    	
	    }
	    else {
	        $.ajax({
	            url: base+"/model/update",
	            type: 'POST',
	            contentType: "application/json",
	            dataType: "json",
	            data: JSON.stringify(json),
	            success:function(data, Textstatus, jqXHR){
	                window.location.href = base+"/listModel?brandid="+brandid;
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
	
    	var brandid = $('#brandid').val(); 
	    var modelid = $('#modelid').val(); 
	    var modelname = $('#modelname').val(); 	    
	    var colour = $('#colour').val(); 	    
	    var sellingmodel = $('#sellingmodel').prop('checked');
	    var commission = $('#commission').val(); 
	    var price = $('#price').val(); 
	    var suminsured = $('#suminsured').val(); 
	    var premium = $('#premium').val(); 
	    var roadtax = $('#roadtax').val(); 
		var enginetype = $('#enginetype').val();	
		var fuelsupplysystem = $('#fuelsupplysystem').val();	
		var displacement = $('#displacement').val();	
		var maxpower = $('#maxpower').val();	
		var maxtorque = $('#maxtorque').val();	
		var transmission = $('#transmission').val();	

		var json = {
	    		"modelid" : modelid,
	    		"modelname" : modelname,	    		
	    		"brandid" : brandid,
	    		"sellingmodel" : sellingmodel,
	    		"commission" : commission,
	    		"price" : price,
	    		"suminsured" : suminsured,
	    		"premium" : premium,
	    		"roadtax" : roadtax,
	    		"colour" : colour,
	    		"enginetype" : enginetype,
	    		"fuelsupplysystem" : fuelsupplysystem,
	    		"displacement" : displacement,
	    		"maxpower" : maxpower,
	    		"maxtorque" : maxtorque,
	    		"transmission" : transmission

		};
		
        $.ajax({
            url: base+"/model/delete",
            type: 'DELETE',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(json),
            success:function(data, Textstatus, jqXHR){
                window.location.href = base+"/listModel?brandid="+brandid;
            },
            error:function(jqXhr, Textstatus){
            }
        });
	})		
	</script>	
</body>
</html>