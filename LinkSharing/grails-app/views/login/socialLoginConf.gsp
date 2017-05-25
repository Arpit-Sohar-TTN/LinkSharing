<%@ page import="linksharing.Resource" %>
<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<title>Login Page</title>
    <script>
    $(document).ready(function () {
//        signOut()
    })
</script>
</head>

<body>
<div class="col-md-7 col-xs-12">
	<div class="panel-heading">
		<h3 class="panel-title">Recent Shares</h3>
	</div>
	%{--<g:render template="/login/Post" model="[topPosts: recentShares]"/>--}%
	<g:render template="/topic/post1" collection="${recentShares}" var="resource"/>
	<div class="panel-heading">
		<h3 class="panel-title">Top Post</h3>
	</div>
	%{--<g:render template="/login/Post" model="[topPosts: topPosts]"/>--}%
	<g:render template="/topic/post1" collection="${topPosts}" var="resource"/>

</div>

<div class="col-md-5 col-xs-12">
	<g:render template="/login/login"/>
    <g:render template="/login/register"/>
</div>
</body>
</html>
