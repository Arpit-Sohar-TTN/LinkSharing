<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Trending Topics</h4>

	</div>
	<g:each in="${topic}" var="trendingTopic">

		<div class="panel-body" style="padding: 10px">
			<div class="row">
				<div class="col-md-3 col-xs-12">
					%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
					<ls1:userImage userId="${trendingTopic.createdBy.id}"/>
				</div>

				<div class="col-md-9 col-xs-12 center-block">
					<div class="row"><!--username-->
						<div class="col-xs-3 col-md-3"><!--username-->
							<g:link controller="topic" action="showTopic"
							        id="${trendingTopic.id}"><b>${trendingTopic.name}</b></g:link>
						</div><!--./username-->
					</div><!--/.username-->
					<br/>

					<div class="row"><!--content-->
						<div class="col-xs-4 col-md-4"><!--button-->
						@${trendingTopic.createdBy.userName} <br/>
							%{-- <g:if test="${trendingTopic.isLoggedInUserSubscribed}">
									 <a>unsbscribe</a>
							 </g:if>
								 <g:if test="${!trendingTopic.isLoggedInUserSubscribed}">
									 <a>subscribe</a>
								 </g:if>--}%
							<ls:isSubscribe id="${trendingTopic.id}"/>

						</div><!--./button-->
						<div class="col-xs-4 col-md-4"><!--button-->
						Subscriptions<br/>${trendingTopic.noOfSubscribedUsers}
						</div><!--./button-->
						<div class="col-xs-4 col-md-4 pull-right"><!--button-->
						Posts <br/> ${trendingTopic.count}
						</div><!--./button-->
					</div><!--/.content-->
				</div>
			</div>
		</div>
	</g:each>
</div>