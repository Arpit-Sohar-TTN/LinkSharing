<div class="panel panel-default" >

	<div class="panel-body"style="padding: 10px">
		<div class="col-md-3 col-xs-12">
			<img class="img-rounded img-responsive center-block profileImage"
			     src="../assets/iconProfile.png">
		</div>
		<div class="col-md-9 col-xs-12 center-block">
			<div class="row"><!--username-->
				<div class="col-xs-6 col-md-6"><!--username-->
					<b class="">${user}</b> <i>@${user.userName}</i>
				</div><!--./username-->
			</div><!--/.username-->
			<div><!--content-->
				<br/>
			</div><!--/.content-->
			<div class="row"><!--import link-->
				<div class="col-xs-6 col-md-6"><!--socialConnect-->
				Subscriptions<br/>${userVO.noOfSubscriptions}
				</div><!--./socialConnect-->
				<div class="col-xs-6 col-md-6"><!--viewPost-->
					<a href="#" class="pull-right viewPost"> Topics <br/> ${userVO.noOfCreatedTopics}</a>
				</div><!--./viewPost-->
			</div><!--/.import link-->
		</div>
	</div>
	<br/>
</div><!--recentPosts-->