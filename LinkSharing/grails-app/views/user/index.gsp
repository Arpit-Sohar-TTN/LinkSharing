<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Dashboard</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>
<body>
<div class="col-md-5 col-xs-12">
	<g:render template="/editProfile/id"/>
	<g:render template="subscription"/>
	%{--<g:render template="/dashboard/subscriptionProp"/>--}%
	%{--<g:render template="trendingTopic"/>--}%
	<ls:trendingTopics />
	%{--<g:render template="/dashboard/trendingTopicProp"/>--}%
</div>
<div class="col-md-7 col-xs-12">
	<g:render template="inbox"/>
</div>
</body>
</html>
