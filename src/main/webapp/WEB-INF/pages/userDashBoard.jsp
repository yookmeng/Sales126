<!DOCTYPE HTML>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
		<input type="hidden" value="userMonthlySummary" name="userMonthlySummary" /> 
		<div class="row margin-top">
			<ul class="stats">
				<li class="green long" >
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big"><fmt:formatNumber value="${userMonthlySummary.commission}"></fmt:formatNumber></span>
						<span>Commission</span>
					</div>
				</li>
			</ul>
			<button class="btn btn-large btn-lightred " onclick="window.location='listProspects';">
				<i class="fa fa-user"></i>
					<span><fmt:formatNumber value="${userMonthlySummary.totalhot}"></fmt:formatNumber></span>
					<span>Hot</span>
			</button>
			<button class="btn btn-large btn-orange" onclick="window.location='listActivities';">
				<i class="fa fa-bell"></i>
					<span><fmt:formatNumber value="${userMonthlySummary.pendingactivity}"></fmt:formatNumber></span>
					<span>Activity</span>
			</button>
		</div>
		<div>
			<ul class="pagestats style-3">
				<li>
					<div class="spark" align="center">
						<div class="chart" data-percent="${userMonthlySummary.percentprospect}" data-color="#f96d6d" data-trackcolor="#fae2e2">${userMonthlySummary.percentprospect}%</div>
					</div>
					<div class="bottom" align="center">
						<span class="name">Prospect (${userMonthlySummary.actualprospect}/${userMonthlySummary.targetprospect})</span>						
					</div>
				</li>
			</ul>
		</div>
		<div>
			<ul class="pagestats style-3">
				<li>
					<div class="spark" align="center">
						<div class="chart" data-percent="${userMonthlySummary.percenttestdrive}" data-color="#368ee0" data-trackcolor="#d5e7f7">${userMonthlySummary.percenttestdrive}%</div>
					</div>
					<div class="bottom" align="center">
						<span class="name">Test Drive (${userMonthlySummary.actualtestdrive}/${userMonthlySummary.targettestdrive})</span>
					</div>
				</li>
			</ul>
		</div>
		<div>
			<ul class="pagestats style-3">
				<li>
					<div class="spark" align="center">
						<div class="chart" data-percent="${userMonthlySummary.percentclosed}" data-color="#56af45" data-trackcolor="#dcf8d7">${userMonthlySummary.percentclosed}%</div>
					</div>
					<div class="bottom" align="center">
						<span class="name">Closed (${userMonthlySummary.actualclosed}/${userMonthlySummary.targetclosed})</span>
					</div>
				</li>
			</ul>
		</div>
		<br>
		<div align="center">
			<a href="addQuestionaire" class='btn'>
				<i class="fa fa-plus-circle"></i>New Prospect</a>
		</div>
	</div>
</body>
</html>