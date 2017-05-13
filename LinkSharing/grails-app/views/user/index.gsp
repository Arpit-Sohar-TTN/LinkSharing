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
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Subscription</h4>
		<a class="pull-right">view all</a>

	</div>
	<g:render template="subscription"/>
	%{--<g:render template="/dashboard/subscriptionProp"/>--}%
	%{--<g:render template="trendingTopic"/>--}%
	<ls:trendingTopics />
	%{--<g:render template="/dashboard/trendingTopicProp"/>--}%
</div>
<div class="col-md-7 col-xs-12 paginate">
	<g:render template="inbox" />
</div>

</body>
</html>
