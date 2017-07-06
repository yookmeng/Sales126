<div id="navigation">
	<div class="container-fluid">
	   	<input type="hidden" value="branch" name="branch" />	
		<a href="#" id="brand">Sales Diary Pro</a>
		<a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
			<i class="fa fa-bars"></i>
		</a>
		<ul class='main-nav'>
			<li>
				<a href="home">
					<span>Home</span>
				</a>
			</li>
			<li>
				<a href="editBranch?branchid=${branch.branchid}">Branch Profile</a>
			</li>
			<li>
				<a href="listTeam?branchid=${branch.branchid}">Team</a>
			</li>
			<li>
				<a href="listBranchTargetMA">Target</a>
			</li>
			<li>
				<a href="listNotes">Notes</a>
			</li>
			<li>
				<a href="listReview">Review</a>
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
