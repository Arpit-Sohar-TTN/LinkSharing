<%@ page import="linksharing.Resource" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Login Page</title>
</head>
<body>
<div class="col-md-7 col-xs-12">
	<g:render template="/login/recentShare" model="[recentShares: recentShares]" />
<g:render template="/login/topPost" model="[topPosts: topPosts]"/>
</div>
<div class="col-md-5 col-xs-12">
<g:render template="/login/login"/>
<g:render template="/login/register"/>
</div>
</body>
</html>
