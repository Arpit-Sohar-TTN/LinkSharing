<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading">
		<h3 class="panel-title">Change Password</h3>
	</div>


	<div class="panel-body" style="padding: 10px">
		<form class="" method="post" controller="user" action="updatePassword">
			<div class="form-group">
				<label for="password" class="cols-sm-2 control-label">Password</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
						<input type="password" class="form-control" name="password" id="pass" required="true"
						       maxlength="15" minlength="5"
						       placeholder="Enter your Password"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
						<input type="password" class="form-control" name="confirmPassword" id="confPass" required="true"
						       maxlength="15" minlength="5" placeholder="Confirm your Password"
						       onblur="checkPasswordMatch()"/>
					</div>
				</div>
			</div>

			<div class="form-group ">
				<input type="submit" class="btn btn-success " value="Update Password">
			</div>

		</form>
	</div>

</div>