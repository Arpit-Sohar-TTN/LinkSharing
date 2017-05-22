<%@ page import="linksharing.Resource" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Login Page</title>
</head>

<body>
<div class="col-md-5 col-xs-12">
	%{--<g:render template="/login/recentShare" model="[recentShares: recentShares]" />--}%
	<ls:trendingTopics/>
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Top Posts</h4>
	</div>
	<g:render template="/topic/post1" collection="${topPosts}" var="resource"/>

</div>

<div class="col-md-7 col-xs-12">
	<g:render template="searchResults"/>
</div>
</body>
</html>
html