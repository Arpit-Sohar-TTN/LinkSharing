<div class="panel panel-default">
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title" style="font-size: large;">User :"Grails" </h4>
	</div>
	<g:each in="${subscribedUsers}" var="subscribedUser">


	<div class="panel-body">
		<div class="col-md-3 col-xs-12">
			<img class="img-rounded img-responsive center-block profileImage"
			     src="../assets/iconProfile.png">
		</div>
		<div class="col-md-9 col-xs-12 center-block">
			<div class="row"><!--username-->
				<div class="col-xs-6 col-md-6"><!--username-->
					<b class=""></b> <g:link controller="user" action="showProfile" params="[userName:subscribedUser.user.userName]"><i>@${subscribedUser.user.userName}</i></g:link>
				</div><!--./username-->
			</div><!--/.username-->
			<div><!--content-->
				<br/>
			</div><!--/.content-->
			<div class="row"><!--import link-->
				<div class="col-xs-6 col-md-6"><!--socialConnect-->
				Subscriptions<br/>${subscribedUser.user.subscriptions.size()}
				</div><!--./socialConnect-->
				<div class="col-xs-6 col-md-6"><!--viewPost-->
					<a href="#" class="pull-right viewPost"> Topics <br/> ${subscribedUser.user.topics.size()}</a>
				</div><!--./viewPost-->
			</div><!--/.import link-->
		</div>
	</div>
	<br/>
	</g:each>
</div><!--recentPosts-->