<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading">
		<h3 class="panel-title">Profile</h3>
	</div>

	<g:uploadForm controller="user" action="updateProfile" enctype="multipart/form-data">
		<div class="panel-body" style="padding: 10px">

			<div class="form-group">
				<label class="cols-sm-2 control-label">First Name</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
						<input type="text" class="form-control" name="firstName" required="true"
						       value="${session.user.firstName}" pattern="^[a-zA-Z]*$" placeholder="First Name"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="cols-sm-2 control-label">Last Name</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
						<input type="text" class="form-control" name="lastName" pattern="^[a-zA-Z]*$"
						       value="${session.user.lastName}" required="true"
						       placeholder="Last Name"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="cols-sm-2 control-label">Username</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
						<input type="text" class="form-control" name="userName" value="${session.user.userName}"
						       required="true"
						       pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Enter your Username"/>
					</div>
				</div>
			</div>

			<label class="control-label">Select File</label>
			%{--<input id="input-1a" type="file" class="file" data-show-preview="false">--}%
			<g:field id="input-1a" type="file" name="image" class="file" data-show-preview="false"/><br/>

			<div class="form-group ">
				%{--<a href="#" target="_blank" type="button" id="button" class="btn btn-primary btn-lg btn-block login-button outline">Register</a>--}%
				<input type="submit" class="btn btn-success " value="Update">
			</div>

		</div>
	</g:uploadForm>
</div>