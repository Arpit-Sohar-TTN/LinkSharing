<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading">
		<h3 class="panel-title">Login</h3>
	</div>

	<div class="panel-body">
		<form class="" method="post" action="/login/loginHandler">
			<div class="form-group">
				<label class="cols-sm-2 control-label namingLabels">Username</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
						<input type="text" class="form-control" name="username" id="username1"
						       placeholder="Enter your Username" required="true"
						       pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="cols-sm-2 control-label namingLabels">Password</label>

				<div class="cols-sm-10">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
						<input type="password" class="form-control" name="password" id="password1"
						       placeholder="Enter your Password" required="true"
						       minlength="5" maxlength="15"/>
					</div>
				</div>
			</div>

			<div class="form-group ">
				<input value="Login" type="submit" id="button1" class="btn btn-success btn-block "/>
			</div>

		</form>
		%{--<g:form controller="mailSending" action="sendMail" method="post" enctype="multipart/form-data">
			<input  value="Forget Passwors" type="submit" id="button2" class="btn btn-success btn-block" data-toggle="modal" data-target="#sendMail" />
		</g:form>--}%
		<input value="Forget Password" type="submit" id="button2" class="btn btn-success btn-block" data-toggle="modal"
		       data-target="#sendMail"/>
		<div class="g-signin2" data-onsuccess="onSignIn" ></div>

	</div>
</div>