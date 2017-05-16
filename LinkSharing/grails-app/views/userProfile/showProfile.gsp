<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Topic Show</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>

<body>
<div class="col-md-5 col-xs-12">
	<g:render template="/editProfile/id"/>
	<g:render template="/userProfile/topic"/>
	<g:render template="/userProfile/subscription"/>

</div>

<div class="col-md-7 col-xs-12">
	<g:render template="/userProfile/post"/>
</div>
</body>
</html>
