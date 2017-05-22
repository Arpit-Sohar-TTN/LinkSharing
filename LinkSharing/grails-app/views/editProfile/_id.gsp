<div class="panel panel-default">

	<div class="panel-body" style="padding: 10px">
		<div class="col-md-3 col-xs-12">
			%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
			%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
			%{--<img src="${createLink(controller: 'login', action: 'viewImage', params: ['userName': userVO.userName])}"/>--}%
			%{--	<ls:userImage userId="${session.user.id}"/>--}%
			%{--<ls1:userImage id="${session.user.id}"/>--}%
			<ls1:userImage userId="${userVO.id}"/>
		</div>

		<div class="col-md-9 col-xs-12 center-block">
			<div class="row"><!--username-->
				<div class="col-xs-6 col-md-6"><!--username-->
					<b class="">${userVO.firstName}
						${userVO.lastName}</b> <i>@${userVO.userName}</i>
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
					<p class="pull-right viewPost">Topics <br/> ${userVO.noOfCreatedTopics}</p>
				</div><!--./viewPost-->
			</div><!--/.import link-->
		</div>
	</div>
	<br/>
</div><!--recentPosts-->