<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_tlNavigation.jsp" />
	<div id="main" class="container-fluid">
		<input type="hidden" value="teamTarget" name="teamTarget" /> 
		<div>
			<h4>${teamTarget.teamname}  ${teamTarget.period}  Target</h4>
		</div>
        <div>
    		<h5>Prospect : ${teamTarget.prospect}</h5>
    		<h5>Test Drive : ${teamTarget.testdrive}</h5>
    		<h5>Closed : ${teamTarget.closed}</h5>    		
        </div>
       	<div align="center">
			<div class="box box-color box-bordered">
				<div class="box-title">
					<h3>${teamTarget.teamname} ${teamTarget.period} Monthly Summary</h3>
				</div>
				<div class="box-content nopadding">
					<table class="table table-hover table-nomargin table-bordered usertable">
						<thead>
							<tr class="thefilter">
								<th class="with-checkbox"></th>
				                <th>Member</th>						
				                <th><p align="center">Actual</p></th>
				                <th><p align="center">Target</p></th>
				                <th><p align="center">Percent</p></th>
				                <th><p align="center">Actual</p></th>
				                <th><p align="center">Target</p></th>
				                <th><p align="center">Percent</p></th>
				                <th><p align="center">Actual</p></th>
				                <th><p align="center">Target</p></th>
				                <th><p align="center">Percent</p></th>
				                <th><p align="right">Commission</p></th>
				                <th><p align="center">Hot</p></th>
				                <th><p align="center">Review</p></th>
							</tr>
							<tr>
								<th></th>
				                <th></th>
				                <th colspan=3><p align="center">Prospect</p></th>
				                <th colspan=3><p align="center">Test Drive</p></th>
				                <th colspan=3><p align="center">Closed</p></th>
				                <th></th>
				                <th></th>
				                <th></th>
							</tr>
							<tr>
								<th class="with-checkbox">
									<input type="checkbox" name="check_all" id="check_all">
								</th>
				                <th>Member</th>
				                <th>Actual</th>
				                <th>Target</th>
				                <th>Percent</th>
				                <th>Actual</th>
				                <th>Target</th>
				                <th>Percent</th>
				                <th>Actual</th>
				                <th>Target</th>
				                <th>Percent</th>
				                <th>Commission</th>
				                <th>Hot</th>
				                <th>Review</th>
							</tr>
						</thead>
						<tbody>
			                <c:forEach var="listSummary" items="${listUserMonthlySummary}" varStatus="status">
				                <tr>
									<td class="with-checkbox">
										<input type="checkbox" name="check" value="1">
									</td>
				                    <td>${listSummary.username}</td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.actualprospect}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.targetprospect}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.percentprospect}"/>%</p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.actualtestdrive}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.targettestdrive}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.percenttestdrive}"/>%</p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.actualclosed}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.targetclosed}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.percentclosed}"/>%</p></td>
				                    <td><p align="right"><fmt:formatNumber value="${listSummary.commission}"/></p></td>
				                    <td><p align="center"><fmt:formatNumber value="${listSummary.totalhot}"/></p></td>
				                    <td><p align="center">
										<button class="btn btn-small" title="Add Review" onclick="window.location='addReview?userid=${listSummary.userid}&targetid=${listSummary.targetid}';" >
									    	<i class="fa fa-plus-circle"></i></button>
									    </p>
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
            aoColumnDefs: [{ bSortable: !1, aTargets: [0, 13]}]
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
	            aoColumns: [null, { type: "text" }, null, null, null, null, null, null, null, null, null, { type: "text" }, {type: "text" }, null]
	        });
		};
	})
	</script> 
</body>
</html>