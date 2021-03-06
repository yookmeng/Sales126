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
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="listActivities">Activity</a>
				</li>
			</ul>
		</div>
        <div align="center">
			<div class="box-content nopadding">
				<table class="table table-hover table-nomargin table-colored-header">
				<tr>
				    <th>Date</th>
				    <th>Prospect</th>
				    <th>Activities</th>
				    <th>Links</th>
				</tr>
				<c:forEach var="activity" items="${listActivity}" varStatus="status">
					<tr>
					    <td><a href="editActivity?activityid=${activity.activityid}&step=1">${activity.activitydate}</a></td>
						<td>${activity.prospectname}</td>
						<td>
							<ul class="minitiles">
								<c:if test="${activity.demo==true && activity.demostatus==false}">
									<li class="lightred">
										<i class="fa glyphicon-display"></i>
										<span>Presentation</span>
									</li>
								</c:if>
								<c:if test="${activity.demo==true && activity.demostatus==true}">
									<li class="satgreen">
										<i class="fa glyphicon-display"></i>
										<span>Presentation</span>
									</li>
								</c:if>
								<c:if test="${activity.testdrive==true && activity.testdrivestatus==false}">
									<li class="lightred">
										<i class="fa glyphicon-car"></i>
										<span>Test Drive</span>
									</li>
								</c:if>
								<c:if test="${activity.testdrive==true && activity.testdrivestatus==true}">
									<li class="satgreen">
										<i class="fa glyphicon-car"></i>
										<span>Test Drive</span>
									</li>
								</c:if>
								<c:if test="${activity.quotation==true && activity.quotationid==0}">
									<li class="lightred">
										<i class="fa glyphicon-calculator"></i>
										<span>Quotation</span>
									</li>
								</c:if>
								<c:if test="${activity.quotation==true && activity.quotationid!=0}">
									<li class="satgreen">
										<i class="fa glyphicon-calculator"></i>
										<span>Quotation</span>
									</li>
								</c:if>
								<c:if test="${activity.followup==true && activity.followupstatus==false}">
									<li class="lightred">
										<i class="fa glyphicon-calendar"></i>
										<span>Follow Up</span>
									</li>
								</c:if>
								<c:if test="${activity.followup==true && activity.followupstatus==true}">
									<li class="satgreen">
										<i class="fa glyphicon-calendar"></i>
										<span>Follow Up</span>
									</li>
								</c:if>
								<c:if test="${activity.closed==true}">
									<li class="satgreen">
										<i class="fa glyphicon-ok_2"></i>
										<span>Close</span>
									</li>
								</c:if>
								<c:if test="${activity.lost==true}">
									<li class="satgreen">
										<i class="fa glyphicon-remove_2"></i>
										<span>Close</span>
									</li>
								</c:if>					
							</ul>			
						</td>
						<td>
							<c:if test="${activity.quotation==true && activity.quotationid!=0}">
								<a href="${activity.quotationpdflink}">${activity.quotationpdflink}</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>             
				</table>
			</div>
		</div>
	</div>
</body>
</html>