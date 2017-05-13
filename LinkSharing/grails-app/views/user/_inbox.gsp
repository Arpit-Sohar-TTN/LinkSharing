<div class="panel panel-default">
	<div class="panel-heading" style="height: 60px;">
		<h4 class="panel-title pull-left" style="padding-top: 7.5px;">Inbox</h4>
		<div class="input-group pull-right" style="margin-top: -5px;margin-right: -120px;">
			<input type="text" name="focus" required class="search-box" placeholder="Search" style="width: 50%;margin-right:0px
			;background-color: white !important;border-color: black;"/>
			<button class="close-icon redfamily" type="reset"></button>
			<label class="glyphicon glyphicon-search redfamily searchIcon" rel="tooltip" title="Search"></label>
		</div>
	</div>
	<g:each in="${userVO.resourceVOList}" var="resourceVO">

	<g:if test="${resourceVO.isRead==false }">

	<div class="panel-body paginate">
		<div class="col-md-2 col-xs-12">
			<img class="img-rounded img-responsive center-block profileImage"  src="../assets/iconProfile.png">
		</div>
		<div class="col-md-10 col-xs-12 center-block">
			<div class="row myContent"><!--content-->
			${resourceVO.description}
			</div><!--/.content-->
			<div class="row"><!--import link-->
				<div class="col-xs-4 col-md-4" style="display: inline-block"><!--socialConnect-->
					<a class="btn btn-social-icon btn-facebook">
						<span class="fa fa-facebook"></span>
						<span class="fa fa-twitter" style="padding-left: 10px"></span>
						<span class="fa fa-google-plus" style="padding-left: 10px"></span>
						<!--</a>
                                    <a class="btn btn-social-icon btn-twitter">
                                        <span class="fa fa-twitter"></span>
                                    </a>
                                    <a class="btn btn-social-icon btn-google">
                                        <span class="fa fa-google-plus"></span>-->
					</a>
				</div><!--./socialConnect-->
			<br>
				<div class="col-xs-8 col-md-8" style="font-size: smaller"><!--viewPost-->
					<span><a href="#" class=" viewPost"> Download</a></span>
					<span><a href="#" class=" viewPost"> View Full Size</a></span>
				%{--</div>
				<div class="col-xs-4 col-md-4" >--}%
					<span><ls:isRead resource="${resourceVO}"/>	</span>
					<span><g:link controller="resource" action="showPost" params="[id:resourceVO.id]" class=" viewPost"> View Post</g:link></span>
				</div><!--./viewPost-->
			</div><!--/.import link-->
		</div>
	</div>
	</g:if>

	</g:each>
	%{--<g:paginate controller="user" action="pagination" total="23"/>--}%

</div>