<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta name="layout" content="main">
	<title>Profile</title>
	%{--<asset:image src="icon.png"></asset:image>--}%
</head>

<body>
<div class="col-md-5 col-xs-12">
	<g:render template="/editProfile/id"/>
	<g:render template="/userProfile/topic" model="[Topics: userVO.createdTopics]"/>
	<g:render template="/userProfile/subscription"/>

</div>

<div class="col-md-7 col-xs-12">
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Posts</h4>

		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search"
				   style="width: 50%;margin-right:0px"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
	<g:render template="/topic/post1" collection= "${userVO.resourceVOList}" var="resource"/>

</div>
</body>
</html>
