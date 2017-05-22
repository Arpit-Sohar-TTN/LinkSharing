<div class="panel panel-default"><!--topPosts-->

	<g:each in="${userVO.topicSubscriptionList}" var="subscribedTopic">

		<div class="panel-body" style="padding: 10px">
			<div class="row">
				<div class="col-md-3 col-xs-12">
					%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
					<ls1:userImage userId="${subscribedTopic.createdBy.id}"/>
				</div>

				<div class="col-md-9 col-xs-12 center-block">
					<div class="row"><!--username-->
						<br>
						<g:link controller="topic" action="showTopic" id="${subscribedTopic.id}"><b
								style="margin-left: 30px;">${subscribedTopic.name}</b></g:link>

					</div><!--/.username-->
					<br/>

					<div class="col-xs-3 col-md-3"><!--username-->
						<g:link controller="user" action="showProfile"
						        params="[userName: subscribedTopic.createdBy.userName]"><i>@${subscribedTopic.createdBy}</i></g:link>
						<i><ls:isSubscribe id="${subscribedTopic.id}"/></i>

					</div><!--./username-->

					<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
					Subscriptions<br/>${subscribedTopic.noOfSubscribedUsers}
					</div><!--./socialConnect-->
					<div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
						<p class=" viewPost">Posts <br/> ${subscribedTopic.count}</p>
					</div><!--./viewPost-->
					<br/>
				</div>
			</div>

			<div class="row" style="margin-top: 20px">
				<a href="#sendInvitation" data-toggle="modal"><span class="glyphicon glyphicon-envelope envIcon"></span>
				</a>
				<g:if test="${session.user.userName == subscribedTopic.createdBy.userName}">
					<span class=" glyphicon glyphicon-file envIcon"></span>
					<a href="\topic\delete?id=${subscribedTopic.id}"><span
							class=" glyphicon glyphicon-trash envIcon"></span></a>

					<select class="form-control access pull-right"
					        style="margin-left: 10px; margin-right: 10px; width: 30%">
						<option value="">PRIVATE</option>
						<option value="">PUBLIC</option>
					</select><!--/.serious-->
				</g:if>


				<select class="form-control access pull-right" style="margin-left: 10px;margin-right: 10px; width: 30%">
					<option value="">SERIOUS</option>
					<option value="">VERY_SERIOUS</option>
					<option value="">CASUAL</option>
				</select><!--/.access-->

			</div>
		</div>
	</g:each>
</div>