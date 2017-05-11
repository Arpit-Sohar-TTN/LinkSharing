
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Recent Shares</h3>
    </div>
    <g:each in="${recentShares}" var="post">
                <div class="panel-body" style="padding: 10px">
                    <div class="col-md-3 col-xs-12">
                        <img class="img-rounded img-responsive center-block profileImage"
                             src="../assets/iconProfile.png">
                    </div>
                    <div class="col-md-9 col-xs-12 center-block">
                        <div class="row"><!--username-->
                            <div class="col-xs-6 col-md-6"><!--username-->
                            <b>${post.createdBy.firstName+' '+post.createdBy.lastName}</b> &nbsp;&nbsp;&nbsp;<i>@ ${post.createdBy.userName}</i>
                            </div><!--./username-->
                            <div class="col-xs-6 col-md-6"><!--competency-->
                                <a href="#" class="pull-right mycompetency">JVM</a>
                            </div><!--./competency-->
                        </div><!--/.username-->
                        <div class="row "><!--content-->
                            ${post.description}
                        </div><!--/.content-->
                        <div class="row"><!--import link-->
                            <div class="col-xs-6 col-md-6"><!--socialConnect-->
                                <a class="btn btn-social-icon btn-facebook">
                                    <span class="fa fa-facebook"></span>
                                </a>
                                <a class="btn btn-social-icon btn-twitter">
                                    <span class="fa fa-twitter"></span>
                                </a>
                                <a class="btn btn-social-icon btn-google">
                                    <span class="fa fa-google-plus"></span>
                                </a>
                            </div><!--./socialConnect-->
                            <div class="col-xs-6 col-md-6"><!--viewPost-->
                                <a href="#" class="pull-right viewPost"> View Post</a>
                            </div><!--./viewPost-->
                        </div><!--/.import link-->
                    </div>
                </div>
    </g:each>
            </div><!--recentPosts-->