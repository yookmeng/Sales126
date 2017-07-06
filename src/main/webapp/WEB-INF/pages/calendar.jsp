<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>
<html>
<jsp:include page="_menu.jsp" />
<body>
	<jsp:include page="_userNavigation.jsp" />
	<div id="main">
	   	<input type="hidden" value="${base}" name="base" id="base"/>		
	   	<input type="hidden" value="${userid}" name="userid" id="userid"/>			
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="home">Home</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="calendar">Calendar</a>
				</li>
			</ul>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-title">
						<h3>
							<i class="fa fa-calendar"></i>
							Calendar
						</h3>
				 	</div>
					<div class="box-content nopadding">
						<div class="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	function getevent(){
		var userid = $('#userid').val();
		var date = new Date();
		var m = date.getMonth()+1;
		var y = date.getFullYear();
		var period = y+'-'+m
		var base = $('#base').val();
		if (window.location.protocol === 'https:') {
			base = base.replace("http", "https");
  		}

       return ($.ajax({
            url: base+"/userCalendar/"+userid+"/"+period,
            async: false,
            type: 'GET',
            contentType: "application/json",
            dataType: "json",
            success:function(data){
            }
         }));
	};

    if ($('.calendar').length > 0 && !$('.calendar').parent().hasClass('daterangepicker')) {
    	var allevents = '';
    	var getallevents = getevent();
    	getallevents.success(function (data) {
			allevents = data;
    	});
       	$('.calendar').fullCalendar({
            header: {
                left: '',
                center: 'prev,title,next',
                right: 'month,agendaWeek,agendaDay,today'
            },
            buttonText: {
                today: 'Today'
            },
            editable: true,
   	    	events: allevents
   	    });		
        $(".fc-button-effect").remove();
        $(".fc-button-next .fc-button-content").html("<i class='fa fa-chevron-right'></i>");
        $(".fc-button-prev .fc-button-content").html("<i class='fa fa-chevron-left'></i>");
        $(".fc-button-today").addClass('fc-corner-right');
        $(".fc-button-prev").addClass('fc-corner-left');    		
	};
	</script>
</body>
</html>