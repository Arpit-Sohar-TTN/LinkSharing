<%@ page import="linksharing.LinkResource" %>
<div class="panel panel-default">
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Post: ${topic.topicName}</h4>

		<div class="input-group pull-right" style="margin-right: -100px; margin-top: -5px">
			<input type="text" name="focus" required class="search-box" placeholder="Search"
			       style="width: 50%;margin-right:0px"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
	<g:each in="${topic.resources}" var="resource">

		<div class="panel-body">
			<div class="col-md-2 col-xs-12">
				%{--<g:img dir="assets/images" file="iconProfile.png" class="img-rounded img-responsive center-block profileImage"/>--}%
				<ls1:userImage userId="${resource.createdBy.id}"/>
			</div>

			<div class="col-md-10 col-xs-12 center-block">
				<div class="row myContent"><!--content-->
				${resource.description}
				</div><!--/.content-->
				<div class="row"><!--import link-->
					<div class="col-xs-4 col-md-4" style="font-size: smaller"><!--socialConnect-->
						<a class="btn btn-social-icon btn-facebook">
							<span class="fa fa-facebook"></span>
							<span class="fa fa-twitter" style="padding-left: 10px"></span>
							<span class="fa fa-google-plus" style="padding-left: 10px"></span>

						</a>
					</div><!--./socialConnect-->
					<br>

					<div class="col-xs-4 col-md-4" style="font-size: smaller">
						<g:if test="${resource instanceof linksharing.LinkResource}">
							<a href="${resource.url}"><p class="post-option">View-Site</p></a>
						</g:if>
						<g:else>
							<g:link controller="resource" action="download"
									params="[resourceId: resource.id]"><p
									class="post-option">Download</p></g:link>
						</g:else>
					</div>

					<div class="col-xs-4 col-md-4" style="font-size: smaller">
						<span><g:link controller="resource" action="showPost" params="[id: resource.id]"
						              class=" viewPost">View Post</g:link></span>
					</div>
				</div><!--/.import link-->
			</div>
		</div>
	</g:each>
</div>