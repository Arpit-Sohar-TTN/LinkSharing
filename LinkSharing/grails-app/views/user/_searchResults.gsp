<div class="panel panel-default">
    <div class="panel-heading" style="height: 60px;">
        <h4 class="panel-title pull-left" style="padding-top: 7.5px;">Search for : ${q}</h4>
    </div>
    <br>
    <g:if test="${topics.size()!=0}">
        <div class="panel-heading" style="height: 60px;">
            <h4>Topics found:</h4>
        </div>
    </g:if>

    <g:each in="${topics}" var="topic">

        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-xs-12">
                    <img class="img-rounded img-responsive center-block profileImage"
                         src="../assets/iconProfile.png"/>
                    %{--<g:img dir="images" file="iconProfile"></g:img>--}%
                </div>

                <div class="col-md-9 col-xs-12 center-block">
                    <div>
                        <g:link controller="topic" action="showTopic" id="${topic.id}"><b>${topic.topicName}</b> </g:link>
                        (${topic.visibility})
                        <br/><br/>
                    </div>

                    <div class="row"><!--username-->
                        <div class="col-xs-3 col-md-3"><!--username-->
                            <g:link controller="user" action="showProfile"
                                    params="[userName: topic.createdBy.userName]"><i>@${topic.createdBy.userName}</i></g:link>    <br/>
                            <ls:isSubscribe id="${topic.id}"/>

                        </div><!--./username-->

                        <div class="col-xs-3 col-md-3 center-block"><!--socialConnect-->
                        Subscriptions<br/>${topic.subscriptions.size()}
                        </div><!--./socialConnect-->
                        <div class="col-xs-3 col-md-3 pull-right"><!--viewPost-->
                            <a href="#" class=" viewPost">Posts <br/> ${topic.resources.size()}</a>
                        </div>
                    </div><!--/.username-->

                </div>
            </div>

            <div class="row">
                <span class="glyphicon glyphicon-envelope envIcon"
                      style="padding-right: 20px; padding-left: 10px;"></span>

                <select class="form-control whySoSerious pull-right"
                        style="width: 30%;background-color: rgba(0,0,0,0.15);">
                    <option value="">Serious</option>
                    <option value="">very-Serious</option>
                    <option value="">Casual</option>
                </select>
            </div>
        </div>

    </g:each>
    <br>
    <g:if test="${resources.size()!=0}">
        <div class="panel-heading" style="height: 60px;">
            <h4>Resources found:</h4>
        </div>
    </g:if>


    <g:each in="${resources}" var="resource">

        <div class="panel-body">
            <div class="col-md-2 col-xs-12">
                <img class="img-rounded img-responsive center-block profileImage" src="../assets/iconProfile.png">
            </div>

            <div class="col-md-10 col-xs-12 center-block">
                <div class="row myContent"><!--content-->
                ${resource.description}
                </div><!--/.content-->
                <div class="row"><!--import link-->
                    <div class="col-xs-4 col-md-4" style="display: inline-block"><!--socialConnect-->
                        <a class="btn btn-social-icon btn-facebook">
                            <span class="fa fa-facebook"></span>
                            <span class="fa fa-twitter" style="padding-left: 10px"></span>
                            <span class="fa fa-google-plus" style="padding-left: 10px"></span>

                        </a>
                    </div><!--./socialConnect-->
                    <br>
                    <span><a href="#" class=" viewPost">Download</a></span>
                    <span><a href="#" class=" viewPost">View Full Size</a></span>
                    <span><g:link controller="resource" action="showPost" params="[id: resource.id]"
                                  class=" viewPost">View Post</g:link></span>

                </div><!--/.import link-->
            </div>
        </div>
    </g:each>

%{--<g:each in="${topics}" var="topic">



<div class="panel-body paginate">
    <div class="col-md-2 col-xs-12">
        <img class="img-rounded img-responsive center-block profileImage"  src="../assets/iconProfile.png">
    </div>
    <div class="col-md-10 col-xs-12 center-block">
        <div class="row myContent"><!--content-->
        ${topic.topicName}  ${topic.createdBy}
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
            --}%%{--</div>
            <div class="col-xs-4 col-md-4" >--}%%{--
                <span><ls:isRead resource="${resourceVO}"/>	</span>
                <span><g:link controller="resource" action="showPost" params="[id:resourceVO.id]" class=" viewPost"> View Post</g:link></span>
            </div><!--./viewPost-->
        </div><!--/.import link-->
    </div>
</div>


</g:each>--}%
%{--<g:paginate controller="user" action="pagination" total="23"/>--}%

</div>