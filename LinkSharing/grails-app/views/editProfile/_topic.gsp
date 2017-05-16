<div class="panel panel-default"><!--topPosts-->
	<div class="panel-heading" style="height: 50px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Topic :</h4>

		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search"
			       style="width: 50%;margin-right:0px;"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>

	</div>

	<g:each in="${Topics}" var="topicVO">
	%{--<p>${topicVO.createdBy.userName == session.user.userName}</p>--}%
		<g:if test="${(topicVO.createdBy.userName == session.user.userName)}">

			<div class="panel-body" style="padding: 10px">
				<g:form controller="user" action="editTopic" name="editTopic">
					<div class="row">
						<div class="col-md-3 col-xs-12">
							%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
							<ls1:userImage userId="${userVO.id}"/>
						</div>

						<div class="col-md-9 col-xs-12 center-block">
							<div class="row"><!--username-->
								<div class="col-xs-3 col-md-3"><!--username-->
									<i>@${userVO.userName}</i>
								</div><!--./username-->

								<div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
								Subscriptions<br/>${topicVO.noOfSubscribedUsers}
								</div><!--./socialConnect-->
								<div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
									<a href="#" class=" viewPost">Posts <br/> ${topicVO.count}</a>
								</div><!--./viewPost-->

							</div><!--/.username-->
							<br/>

							<div class="row"><!--content-->
								<div class="col-xs-8 col-md-8"><!--searchBox-->
								<g:hiddenField name="id" value="${topicVO.id}"/>
								<g:textField type="text" value="${topicVO.name}" name="topicName" placeholder="Topic"
								             style="width: 230px"/>
								</div><!--./searchBox-->
								<div class="col-xs-4 col-md-4"><!--button-->

								</div><!--./button-->
							</div><!--/.content-->
							<br/>
						</div>
					</div>

					<div class="row">
						<span class="glyphicon glyphicon-envelope envIcon"></span>
						<span class=" glyphicon glyphicon-file envIcon"></span>
						<span class=" glyphicon glyphicon-trash envIcon"></span>

						<g:select class="form-control access pull-right" style="margin-left: 10px; width: 30%"
						          name="seriousness" from="${linksharing.Seriousness.getEnumConstants()}"/>
						%{--<option value="${linksharing.Seriousness.SERIOUS}">SERIOUS</option>
						<option value="${linksharing.Seriousness.VERY_SERIOUS}">VERY_SERIOUS</option>
						<option value="${linksharing.Seriousness.CASUAL}">CASUAL</option>--}%


						<g:select from="${linksharing.Visibility.getEnumConstants()}"
						          class="form-control access pull-right" style="margin-left: 10px; width: 30%"
						          name="visibility"/>

					</div>
					<g:submitButton name="editTopic" value="Save" class="pull-right"/>
				</g:form>
			</div>
			<br/>
		</g:if>

	</g:each>
	<div class="panel-footer">

	</div>
</div>