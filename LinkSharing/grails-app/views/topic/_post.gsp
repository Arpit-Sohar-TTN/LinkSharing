<div class="panel panel-default">
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Post: ${topic.topicName} </h4>
		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search" style="width: 50%;margin-right:0px"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
	<g:each in="${topic.resources}" var="resource">


	<div class="panel-body">
		<div class="col-md-2 col-xs-12">
			<img class="img-rounded img-responsive center-block profileImage"  src="../assets/iconProfile.png">
		</div>
		<div class="col-md-10 col-xs-12 center-block">
			<div class="row myContent"><!--content-->
			${resource.description}
			</div><!--/.content-->
			<div class="row"><!--import link-->
				<div class="col-xs-4 col-md-4" style="display: inline-block"><!--socialConnect-->
					<a class="btn btn-social-icon btn-facebook">
						<span class="fa fa-facebook"></span>
						<span class="fa fa-twitter" style="padding-left: 10px"></span>
						<span class="fa fa-google-plus" style="padding-left: 10px"></span>

					</a>
				</div><!--./socialConnect-->
			<br>
			<span><a href="#" class=" viewPost"> Download</a></span>
			<span><a href="#" class=" viewPost"> View Full Size</a></span>
			<span><g:link controller="resource" action="showPost" params="[id:resource.id]" class=" viewPost"> View Post</g:link></span>

		</div><!--/.import link-->
		</div>
	</div>
	</g:each>
</div>