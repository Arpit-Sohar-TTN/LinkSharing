<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Topic Show</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>

<body>
<div class="row">
	<div class="col-sm-5 col-md-5 col-xs-12">
		<g:render template="/topic/topic"/>
		<g:render template="/topic/userTopic" model="['subscribedUsers': topic.subscriptions]"/>
	</div>

	<div class="col-sm-7 col-md-7 col-xs-12">
		<g:render template="/topic/post"/>
	</div>
</div>
</body>
</html>
