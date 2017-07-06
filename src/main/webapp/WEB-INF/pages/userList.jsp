<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/> 
<html>
<jsp:include page="_menu.jsp" />
<body>
	<c:if test="${role == 'TL'}">
		<jsp:include page="_tlNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MA'}">
		<jsp:include page="_maNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'MD'}">
		<jsp:include page="_mdNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'SA'}">
		<jsp:include page="_saNavigation.jsp" />
	</c:if>
	<c:if test="${role == 'DEV'}">
		<jsp:include page="_deNavigation.jsp" />
	</c:if>
	<div id="main">	
		<div class="container-fluid">
		   	<input type="hidden" value="${base}" name="base" id="base"/>	
			<div align="center">
	   	    	<h5>
	   				<a href="addUser" class='btn'>
					<i class="fa fa-plus-circle"></i>Add New</a></h5>
			</div>
			<div class="box box-color box-bordered">
				<div class="box-title">
					<h3>User</h3>
				</div>
				<div class="box-content nopadding">
				  <table class="table table-hover table-nomargin table-bordered usertable">
					<thead>
						<tr class="thefilter">
							<th class="with-checkbox"></th>
						    <th>Name</th>
						    <th>Role</th>
						    <th>Company</th>
						    <th>Branch</th>
						    <th>Team</th>	
						    <th>Mobile</th>	
						    <th>Email</th>	
						</tr>
						<tr>
							<th class="with-checkbox">
								<input type="checkbox" name="check_all" id="check_all">
							</th>
						    <th>Name</th>
						    <th>Role</th>
						    <th>Company</th>
						    <th>Branch</th>
						    <th>Team</th>	
						    <th>Mobile</th>	
						    <th>Email</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listUser}" varStatus="status">
							<tr>
								<td class="with-checkbox">
									<input type="checkbox" name="check" value="1">
								</td>
						        <td><a href="editUser?username=${user.username}">${user.username}</a></td>
						        <td>${user.role}</td>
						        <td>${user.companyname}</td>
						        <td>${user.branchname}</td>
						        <td>${user.teamname}</td>
						        <td>${user.mobile}</td>
						        <td>${user.email}</td>
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
	            aoColumnDefs: [{ bSortable: !1, aTargets: [0, 7]}]
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
	            aoColumns: [null, { type: "text" }, { type: "text" }, { type: "text" }, {type: "text" }, { type: "text" }, { type: "text" }, { type: "text" }]
	        });
		};
	})
	</script>
</body>
</html>