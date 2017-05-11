<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Topic :"Grails" </h4>
		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search" style="width: 50%;margin-right:0px"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
			<div class="col-md-3 col-xs-12">
				<img class="img-rounded img-responsive center-block profileImage"
					 src="../assets/iconProfile.png">
			</div>

			<div class="col-md-9 col-xs-12 center-block">
				<div>
					<a>${topic.topicName}</a>(${topic.visibility})
					<br/><br/>
				</div>
				<div class="row"><!--username-->
					<div class="col-xs-3 col-md-3"><!--username-->
						<i>@${topic.createdBy.userName}</i><br/><a>Subscribe</a>
					</div><!--./username-->

					<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
					Subscriptions<br/>${topic.subscriptions.size()}
					</div><!--./socialConnect-->
					<div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
						<a href="#" class=" viewPost"> Posts <br/> ${topic.resources.size()}</a>
					</div><!--./viewPost-->

				</div><!--/.username-->

			</div>
		</div>
		<div class="row">
			<span class="glyphicon glyphicon-envelope envIcon" style="padding-right: 20px; padding-left: 10px;"></span>

			<select class="form-control whySoSerious pull-right" style="width: 30%;background-color: rgba(0,0,0,0.15);">
				<option value="">Serious</option>
				<option value="">very-Serious</option>
				<option value="">Casual</option>
			</select>
		</div>
	</div>
	<br/>
</div>