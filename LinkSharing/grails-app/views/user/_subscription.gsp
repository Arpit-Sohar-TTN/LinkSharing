<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Subscription</h4>
		<a class="pull-right">view all</a>

	</div>
	<g:each in="${userVO.topicSubscriptionList}" var="subscribedTopic">


	<div class="panel-body" style="padding: 10px">
		<div class="row">
			<div class="col-md-3 col-xs-12">
				<g:img dir="images" class="img-rounded img-responsive center-block profileImage"
					   src="../assets/iconProfile.png"/>
			</div>

			<div class="col-md-9 col-xs-12 center-block">
				<div class="row"><!--username-->
					%{--<div class="col-xs-3 col-md-3"><!--username-->
						<i>@ ${session.user}</i>
					</div><!--./username-->

					<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
					Subscriptions<br/>${subscribedTopic.noOfSubscribedUsers}
					</div><!--./socialConnect-->
					<div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
						<a href="#" class=" viewPost"> Posts <br/> ${userVO.noOfCreatedTopics}</a>
					</div><!--./viewPost-->--}%
					<g:link controller="topic" action="showTopic" id="${subscribedTopic.id}"><b style="margin-left: 30px;">${subscribedTopic.name}</b> </g:link>

				</div><!--/.username-->
				<br/>
				<div class="col-xs-3 col-md-3"><!--username-->
					<i>@${session.user}</i>
					<i><a href="">Unsubscribe</a></i>

				</div><!--./username-->

				<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
				Subscriptions<br/>${subscribedTopic.noOfSubscribedUsers}
				</div><!--./socialConnect-->
				<div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
					<a href="#" class=" viewPost"> Posts <br/> ${userVO.noOfCreatedTopics}</a>
				</div><!--./viewPost-->
				<br/>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<span class="glyphicon glyphicon-envelope envIcon" ></span>
			<g:if test="${session.user.userName==subscribedTopic.createdBy}">
				<span class=" glyphicon glyphicon-file envIcon"></span>
				<span class=" glyphicon glyphicon-trash envIcon"></span>

				<select class="form-control access pull-right" style="margin-left: 10px; margin-right: 10px;background-color:#2aabd2; width: 30%">
					<option value="">PRIVATE</option>
					<option value="">PUBLIC</option>
				</select><!--/.serious-->
			</g:if>


			<select class="form-control access pull-right" style="margin-left: 10px;margin-right: 10px; background-color:#2aabd2; width: 30%">
				<option value="">SERIOUS</option>
				<option value="">VERY_SERIOUS</option>
				<option value="">CASUAL</option>
			</select><!--/.access-->


		</div>
	</div>
	</g:each>
</div>