<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'icon.png')}" type="image/x-icon" />
    <g:layoutHead />
    <g:javascript library="application" />
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="css/font-awesome.min.css"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

    <g:layoutHead/>
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-default "style="background-color:#ace3aa;margin-bottom: 20px ">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            %{--<a class="navbar-brand" href="#">
                <asset:image src="logo.png" class="logo pull-left" style = "height: 35px;width: 50px"></asset:image>
            </a>--}%
            <asset:image src="logo.png" href="/user/index" class="logo pull-left" style = "height: 45px;width: 40px;padding-top: 10px"></asset:image>
            <a class="navbar-brand" href="/user/index" style="padding-left: 30px">Link Sharing</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right" style="margin-right: 0px;margin-top: 5px">

<g:if test="${session.getAttribute('user')}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><span class="caret"></span> &nbsp; User</a>
                    <ul id="main-dropdown" class="dropdown-menu">
                        <g:if test="${session.user.isAdmin   == true}">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        </g:if>
                        <li><g:link controller="user" action="editProfile">Profile</g:link></li>
                        <li role="separator" class="divider"></li>
                        <li><g:link controller="login" action="logout">Logout</g:link></li>
                    </ul>
                </li>
</g:if>
            </ul>
            <form class="navbar-form navbar-right" style="margin-right: 10px" action="/user/search" >
                <div class="input-group" style="">

                    %{--<input type="text" name="q"  required class="search-box" id="newer-txtbox" onblur="${remoteFunction( controller:'user',action:'search')}"--}%

                        <input type="text" name="q"   class="search-box" id="newer-txtbox"
                           placeholder="Search" style="width: 50%;background-color: white !important;border-color: black;"/>

                    <button class="close-icon redfamily" type="reset"></button>
                    <label  class="glyphicon glyphicon-search redfamily searchIcon"  rel="tooltip" title="Search"></label>
                    %{--<input type="submit" value="search"/>--}%


<g:if test="${session.getAttribute('user')}">

    <a href="#" data-toggle="modal"> <span class=" glyphicon glyphicon-user redfamily pull-right" style="font-size: 2em;margin-top: 10px;margin-left: 40px;margin-right: -20px"></span></a>
                    <a href="#shareDocuments" data-toggle="modal"> <span class=" glyphicon glyphicon-file redfamily pull-right" style="font-size: 2em;margin-top: 10px;margin-left: 10px"></span></a>
                    <a href="#shareLink" data-toggle="modal"> <span class=" glyphicon glyphicon-paperclip redfamily pull-right" style="font-size: 2em;margin-top: 4px;margin-left: 10px"></span></a>
                    <a href="#sendInvitation" data-toggle="modal"> <span class=" glyphicon glyphicon-envelope redfamily pull-right" style="font-size: 2em;margin-top: 10px;margin-left: 10px"></span></a>
                    <a href="#createTopic" data-toggle="modal"> <span class=" fa fa-comment redfamily pull-right" style="font-size: 2em;margin-top: 10px;margin-left: 10px"></span></a>
</g:if>
                </div>


            </form>
        </div>

    <!-- /.container -->
</nav>
<!-- /.navbar -->
<g:if test="${flash.singleError}" style="margin-left: 50px;margin-right: 500px">
    <div class="alert alert-danger" id="successMessage" style="text-align: center">
        <strong>${flash.singleError}</strong>
    </div>
</g:if>

<g:if test="${flash.error && flash.error.size()>1}" style="margin-left: 50px;margin-right: 500px">
    <div class="alert alert-danger" id="errorMessage" style="text-align: center">
        <g:each in="${flash.error}">
            <strong>${it}</strong>
            <br>
        </g:each>
    </div>
</g:if>
<g:if test="${flash.message}" style="margin-left: 50px;margin-right: 500px">
    <div class="alert alert-success" id="successMessage" style="text-align: center">
        <strong>${flash.message}</strong>
    </div>
</g:if>

<g:layoutBody/>

    <div class="footer" role="contentinfo"  style="height: 50px;color: black;background-color:  #ace3aa !important;position: relative;bottom:0;left:0;right:0;padding-top: 10px">
    %{--&copy;TO The New
    </div>--}%
    <g:render template="/user/modalSendInvitation"></g:render>
    <g:render template="/user/modalShareDocuments"></g:render>
    <g:render template="/user/modalShareLink"></g:render>
    <g:render template="/user/modalCreateTopic"></g:render>
    <asset:javascript src="application.js"/>

</body>
</html>
