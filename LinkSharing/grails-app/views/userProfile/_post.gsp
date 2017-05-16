<div class="panel-heading" style="height: 50px;">
	<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Posts</h4>

	<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
		<input type="text" name="focus" required class="search-box" placeholder="Search"
		       style="width: 50%;margin-right:0px"/>
		<button class="close-icon redfamily" type="reset"></button>
		<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
	</div>
</div>
<g:each in="${userVO.resourceVOList}" var="post">

	<div class="panel-body">
		<div class="row">
			<a style="margin-left: 30px">${post.topic.topicName}</a>
		</div>

		<div class="col-md-12    col-xs-12 center-block">
			<div class="row myContent"><!--content-->
			${post.description}
			</div><!--/.content-->
		</div>

		<div class="row"><!--import link-->
			<div class="col-xs-4 col-md-4" style="display: inline-block"><!--socialConnect-->
				<a class="btn btn-social-icon btn-facebook">
					<span class="fa fa-facebook"></span>
					<span class="fa fa-twitter" style="padding-left: 10px"></span>
					<span class="fa fa-google-plus" style="padding-left: 10px"></span>
				</a>
			</div><!--./socialConnect-->
			<br>

			<div class="col-xs-8 col-md-8" style="font-size: small"><!--viewPost-->
				<span><a href="#" class=" viewPost">Download</a></span>
				<span><a href="#" class=" viewPost">View Full Size</a></span>
				%{--</div>
				<div class="col-xs-4 col-md-4" >--}%

				<span><g:link controller="resource" action="showPost" params="[id: post.id]"
				              class=" viewPost">View Post</g:link></span>
			</div><!--./viewPost-->
		</div><!--/.import link-->
	</div>
</g:each>
