<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Post Page</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>
<body>
<div class="col-md-7 col-xs-12">
<g:render template="/resource/post"/>
</div>
<div class="col-md-5 col-xs-12">
%{--<g:render template="/resource/tredingTopic"/>--}%
%{--<g:render template="/resource/trendingProp"/>--}%
<ls:trendingTopics />
</div>
</body>
</html>
