<div class="panel-body profileSidePanel">
	<div class="col-md-3 col-xs-12">
		<img class="img-rounded img-responsive center-block profileImage" src="../assets/iconProfile.png">
	</div>
	<div class="col-md-9 col-xs-12">
		<div class="row">
			<div class="col-xs-6" style="float: left;">
				<h4>${resource.createdBy.firstName} ${resource.createdBy.lastName} </h4>
				<p >@${resource.createdBy.userName}  </p>

			</div>
			<div class="col-xs-6" style="float: right ">
				<h4 ><a href="">${resource.topic}</a></h4>
				<h4 >${resource.lastUpdated}</h4>

				<g:each in="${ratingInfoVO.totalScore}">

                    <span class="glyphicon glyphicon-heart"></span>
                </g:each>
				%{--<span class="glyphicon glyphicon-heart"></span>--}%
				%{--<span class="glyphicon glyphicon-heart"></span>--}%
				%{--<span class="glyphicon glyphicon-heart"></span>--}%
				%{--<span class="glyphicon glyphicon-heart"></span>--}%
			</div>

		</div>
		<br>
	</div>
	<div class="row" style="margin-left: 10px;margin-right: 10px">
		<p class="para" style="padding-left: 10px; padding-right: 10px;">${resource.description}</p>
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
			<div class="col-xs-6" style="font-size: smaller">
				<span><a href="#" class="pull-right viewPost"> Download</a></span>
				<span><a href="#" class="pull-right viewPost"> View Full Size</a></span>
				<span><a href="#" class="pull-right viewPost"> Mark as Read</a></span>
				<span><a href="#" class="pull-right viewPost"> View Post</a></span>
			</div><!--./viewPost-->
		</div><!--/.import link-->

	</div>
</div>