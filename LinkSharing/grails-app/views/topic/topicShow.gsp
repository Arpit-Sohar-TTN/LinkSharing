<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Topic Show</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>
<body>
<div class="col-md-5 col-xs-12">
	<g:render template="/topic/topic"/>
	<g:render template="/topic/userTopic" model = "['subscribedUsers': topic.subscriptions]" />
</div>
<div class="col-md-7 col-xs-12">
	<g:render template="/topic/post" />
</div>
</body>
</html>
