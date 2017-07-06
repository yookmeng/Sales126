<div id="navigation">
	<div class="container-fluid">
		<a href="#" id="brand">Sales Diary Pro</a>
		<a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
			<i class="fa fa-bars"></i>
		</a>
		<ul class='main-nav'>
			<li>
				<a href="listUser">User</a>
			</li>
			<li>
				<a href="listCompany">Company</a>
			</li>
		</ul>
		<form action="${pageContext.request.contextPath}/logout" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<div class="user">
			<div class="dropdown">
				<a href="#" class='dropdown-toggle' data-toggle="dropdown">${pageContext.request.userPrincipal.name}
					<img src="img/demo/user-avatar.jpg" alt="">
				</a>
				<ul class="dropdown-menu pull-right">
					<li>
						<a href="editUser?username=${pageContext.request.userPrincipal.name}">Edit profile</a>
					</li>
					<li>
						<a href="javascript:formSubmit()">Sign out</a>
					</li>
				</ul>
			</div>			
		</div>
	</div>
</div>