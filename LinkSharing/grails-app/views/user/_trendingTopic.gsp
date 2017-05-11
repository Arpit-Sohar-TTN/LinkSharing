<div class="panel panel-default"><!--topPosts-->
    <div class="panel-heading" style="height: 50px;">
        <h4 class="panel-title pull-left" style="padding-top: 7.5px;">Trending Topics</h4>

        <div class="input-group pull-right">
            <a>View All</a>
        </div>
    </div>
    <g:each in="${topic}" var="trendingTopic">

        <div class="panel-body" style="padding: 10px">
            <div class="row">
                <div class="col-md-3 col-xs-12">
                    <img class="img-rounded img-responsive center-block profileImage"
                         src="../assets/iconProfile.png">
                </div>

                <div class="col-md-9 col-xs-12 center-block">
                    <div class="row"><!--username-->
                        <div class="col-xs-3 col-md-3"><!--username-->
                            <i>${trendingTopic.name}</i>
                        </div><!--./username-->
                    </div><!--/.username-->
                    <br/>

                    <div class="row"><!--content-->
                        <div class="col-xs-4 col-md-4"><!--button-->
                        @${session.user.userName} <br/>
                    <g:if test="${trendingTopic.isLoggedInUserSubscribed}">
                            <a>unsbscribe</a>
                    </g:if>
                        <g:if test="${!trendingTopic.isLoggedInUserSubscribed}">
                            <a>subscribe</a>
                        </g:if>


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