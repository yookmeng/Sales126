<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="_menu.jsp" />
</head>
<body class='login'>
	<div class="wrapper">
		<div class="login-body">
			<h2>Sign In</h2>
			   
			<c:if test="${param.error == 'true'}">
			    <div style="color:red;margin:10px 0px;">
			           Login Failed!!!<br />
			           Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			    </div>
			</c:if>
	       
<%-- 	   		<form name='f' action="${pageContext.request.contextPath}/j_spring_security_check?targetUrl=${targetUrl}" method='POST'>
 --%>
			<form id ='loginForm'>
 			<div class="form-group">
				<input type="text" name='username' placeholder="User Name" class='form-control' data-rule-required="true">
			</div>
			<div class="form-group">
				<div class="pw controls">
					<input type="password" name="password" placeholder="Password" class='form-control' data-rule-required="true">
				</div>
			</div>
			<div class="submit">
					<!-- if this is login for update, ignore remember me check -->
					<c:if test="${empty loginUpdate}">			
						<div class="remember">
							<div class="col-sm-1">
								<input type="checkbox" name="remember" id="remember">
							</div>
							<label for="remember">Remember me</label>
						</div>
					</c:if>
				<input type="submit" value="Sign me in" class='btn btn-primary'>
			</div>
<%-- 			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
 --%>			
 			</form>
			<div class="forget">
				<a href="#">
					<span>Forgot password?</span>
				</a>
			</div>
		</div>
	</div>
<script src="js/login.js"></script>	
</body>
</html>