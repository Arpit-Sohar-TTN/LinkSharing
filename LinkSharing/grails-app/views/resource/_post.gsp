<%@ page import="linksharing.LinkResource" %>
<div class="panel-body profileSidePanel">
	<div class="col-md-3 col-xs-12">
		%{--<img class="img-rounded img-responsive center-block profileImage" src="../assets/iconProfile.png">--}%
		<ls1:userImage userId="${resource.createdBy.id}"/>
	</div>

	<div class="col-md-9 col-xs-12">
		<div class="row">
			<div class="col-xs-6" style="float: left;">
				<h4>${resource.createdBy.firstName} ${resource.createdBy.lastName}</h4>

				<p>@${resource.createdBy.userName}</p>

			</div>

			<div class="col-xs-6" style="float: right ">
				<h4><g:link controller="topic" action="showTopic" id="${resource.topic.id}"><b
						style="margin-left: 30px;">${resource.topic.topicName}</b></g:link></h4>

				%{--<h4 ><a href="">${resource.topic}</a></h4>--}%
				<h4>${resource.lastUpdated}</h4>
				<g:if test="${ratingInfoVO.averageScore}">
					<g:each in="${(1..ratingInfoVO.averageScore.round())}">

						<span class="glyphicon glyphicon-heart"></span>
					</g:each>
				</g:if>
				<g:else>
					<p style="margin-top: 10px"><b>First one to rate this post</b></p>
				</g:else>
				<g:if test="${session.user != null && linksharing.ResourceRating.findByUserAndResource(session.user, resource) == null}">
					<g:form controller="resource" action="ratePost">
						<g:hiddenField name="resourceId" value="${resource.id}"/>
						<select name="score" id="" style="background-color: #79b94c    ">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
						<g:submitButton name="rate" value="Rate"/>
					</g:form>
				</g:if>

			%{--<span class="glyphicon glyphicon-heart"></span>--}%
			%{--<span class="glyphicon glyphicon-heart"></span>--}%
			%{--<span class="glyphicon glyphicon-heart"></span>--}%
			%{--<span class="glyphicon glyphicon-heart"></span>--}%
			</div>

		</div>
		<br>
	</div>

	<div class="row" style="margin-left: 10px;margin-right: 10px">
		<g:form controller="resource" action="editResourceDescription">
			<g:if test="${session.user.userName == resource.createdBy.userName || session.user.isAdmin == true}">
				<g:textArea type="text" value="${resource.description}" name="description"
				            placeholder="Resource Description" style="width: 650px;height: 100px;"/>
			</g:if>
			<g:else>

				<p class="para" style="padding-left: 10px; padding-right: 10px;">${resource.description}</p>
			</g:else>
			<div class="row"><!--import link-->
			   <div class="col-xs-6">
				  <a class="btn btn-social-icon btn-facebook">
					 <span class="fa fa-facebook"></span>
				  </a>
				  <a class="btn btn-social-icon btn-twitter">
					 <span class="fa fa-twitter"></span>
				  </a>
				  <a class="btn btn-social-icon btn-google" >
					 <span class="fa fa-google-plus"></span>
				  </a>
			   </div>
			<br>
			   <div class="col-xs-6" style="font-size: smaller">
			<g:if test="${resource instanceof linksharing.LinkResource}">
				<a href="${resource.url}"><p class="post-option">View-Site</p></a>
			</g:if>
			<g:else>
				<g:link controller="resource" action="download"
						params="[resourceId: resource.id]"><p
						class="post-option">Download</p></g:link>
			</g:else>
		%{--<span><a href="#" class="pull-right viewPost"> View Post</a></span>--}%
			<g:if test="${session.user.userName == resource.createdBy.userName || session.user.isAdmin == true}">

				<g:hiddenField name="resourceId" value="${resource.id}"/>

				<g:submitButton class="pull-right viewPost" name="editResource" value="Save"
				                style="background-color: transparent;color: #1b6d85"/>
				<g:link controller="resource" action="delete"
				        params="[topicId: resource.topic.id, resourceId: resource.id]">Delete</g:link>
			</g:if>
		</g:form>
	</div><!--./viewPost-->
	</div><!--/.import link-->

	</div>
</div>