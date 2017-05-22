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
		<div class="panel panel-default">
			<div class="panel-heading" style="height: 60px;">
			<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Post: ${topic.topicName}</h4>

			<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
				<input type="text" name="focus" required class="search-box" placeholder="Search"
					   style="width: 50%;margin-right:0px"/>
				<button class="close-icon redfamily" type="reset"></button>
				<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
			</div>
		</div>
			<g:render template="/topic/post1" collection="${topic.resources}" var="resource"/>
		</div>
	</div>
</div>
</body>
</html>
