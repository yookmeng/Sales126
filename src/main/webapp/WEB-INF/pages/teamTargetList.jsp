<!DOCTYPE HTML>
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
	<jsp:include page="_maNavigation.jsp" />
	<div id="main">
		<div class="container-fluid">
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
			<input type="hidden" value="branchTarget" name="branchTarget" /> 
			<div class="breadcrumbs">
				<ul>
					<li>
						<a href="home">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listBranchTargetMA">Branch Target</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="listTeamTarget?targetid=${branchTarget.targetid}">Team Target</a>
					</li>					
				</ul>
			</div>
			<div>
				<h4>${branchTarget.branchname}  ${branchTarget.period}  Target</h4>
			</div>
	        <div>
	    		<h5>Prospect : ${branchTarget.prospect}</h5>
	    		<h5>Test Drive : ${branchTarget.testdrive}</h5>
	    		<h5>Closed : ${branchTarget.closed}</h5>    		
	        </div>
	       	<div align="center">
	   	    	<h5>
	   				<a href="addTeamTarget?targetid=${branchTarget.targetid}" class='btn'>
					<i class="fa fa-plus-circle"></i>Add New</a></h5>
			</div>
			<div class="box box-color box-bordered">
				<div class="box-title">
					<h3>Team Target</h3>
				</div>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-bordered usertable">
					<thead>
						<tr class="thefilter">
							<th class="with-checkbox"></th>
			                <th>Team</th>
			                <th>Prospect</th>
			                <th>Test Drive</th>
			                <th>Closed</th>
			                <th>Action</th>
						</tr>
						<tr>
							<th class="with-checkbox">
								<input type="checkbox" name="check_all" id="check_all">
							</th>
			                <th>Team</th>
			                <th>Prospect</th>
			                <th>Test Drive</th>
			                <th>Closed</th>
			                <th>Action</th>
						</tr>
					</thead>
					<tbody>
		                <c:forEach var="teamTarget" items="${listTarget}" varStatus="status">
			                <tr>
								<td class="with-checkbox">
									<input type="checkbox" name="check" value="1">
								</td>
			                    <td>${teamTarget.teamname}</td>
			                    <td><fmt:formatNumber value="${teamTarget.prospect}"/></td>
			                    <td><fmt:formatNumber value="${teamTarget.testdrive}"/></td>
			                    <td><fmt:formatNumber value="${teamTarget.closed}"/></td>
			                    <td>
									<button class="btn btn-small" onclick="window.location='editTeamTarget?targetid=${teamTarget.targetid}';" >
								    	<i class="fa fa-edit"></i></button>
			                    </td>
			                </tr>
		                </c:forEach>             
					</tbody>
	            	</table>
	        	</div>
        	</div>
        </div>
	</div>
	<script>
	$(document).ready(function() {
		if ($(".usertable").length > 0) {
			var l = {
	            sPaginationType: "full_numbers",
	            oLanguage: {
	                sSearch: "<span>Search:</span> ",
	                sInfo: "Showing <span>_START_</span> to <span>_END_</span> of <span>_TOTAL_</span> entries",
	                sLengthMenu: "_MENU_ <span>entries per page</span>"
	            },
	            sDom: "lfrtip",
	            aoColumnDefs: [{ bSortable: !1, aTargets: [0, 5]}]
			};
			
			l.sDom = "T" + l.sDom;
			l.oTableTools = { sSwfPath: "js/plugins/datatable/swf/copy_csv_xls_pdf.swf"};
			
	 		d = $(".usertable").dataTable(l);
	        $(".usertable").css("width", "100%");	
	        $(".dataTables_filter input").attr("placeholder", "Search here...");
	        $(".dataTables_length select").wrap("<div class='input-mini'></div>").chosen({ disable_search_threshold: 9999999 }),
	        $("#check_all").click(function (e) { $("input", d.fnGetNodes()).prop("checked", this.checked) });
	        $.datepicker.setDefaults({ dateFormat: "yy-mm-dd" });        
	        d.columnFilter({
	            sPlaceHolder: "head:after",
	            sRangeFormat: "{from}{to}",
	            aoColumns: [null, {type: "text" }, { type: "text" }, { type: "text" }, { type: "text" }, null]
	        });
		};
	})
	</script>	
</body>
</html>