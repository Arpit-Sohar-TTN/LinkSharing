<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Edit Profile</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>
<body>
<div class="col-md-5 col-xs-12">
	<g:render template="/editProfile/id"/>
	<g:render template="/editProfile/topic"/>
</div>
<div class="col-md-7 col-xs-12">
	<g:render template="/editProfile/editProfile"/>
	<g:render template="/editProfile/changePassword"/>
</div>
</body>
</html>
