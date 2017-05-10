<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Link Sharing"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <asset:link href="linksharing/linkSharing.css" rel="stylesheet"/>
    <asset:link href="linkSharing.js" rel="script"/>
    <asset:link href="font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet"/>
    <asset:link href="font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <asset:link href="font-awesome-4.7.0/scss" rel="stylesheet"/>
    <asset:link href="bootstrap.min.css" rel="stylesheet"/>
    <asset:link href="bootstrap.css" rel="stylesheet"/>
    <g:javascript src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></g:javascript>
    %{--<g:javascript src="bootstrap.js"></g:javascript>--}%
    <g:layoutHead/>
</head>
<body>

   %{-- <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> Grails
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div>
--}%
%{--
<nav class="navbar navbar-default navbackground">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed makingcircle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">
            <asset:image src="linkSharing/logo.png" alt="logo" class="logo" />
        </a>
        <a class="navbar-brand" href="#">
            Link Sharing
        </a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <form class="navbar-form navbar-right">
            <div class="input-group">
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-default pull-left">
                        <span class="glyphicon glyphicon-search">
                            <span class="sr-only">Search</span>
                        </span>
                    </button>
                    <input type="search" class="form-control" name="search" id="search"
                           style="width: 300px; background-color: #e5e5e5;" placeholder="Search">
                    <button type="reset" class="btn btn-default ">
                        <span class="glyphicon glyphicon-remove">
                            <span class="sr-only">Close</span>
                        </span>
                    </button>
                </span>
            </div>
        </form>
    </div><!-- /.navbar-collapse -->
</nav>--}%



<nav class="navbar navbar-default navbackground">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed makingcircle" data-toggle="collapse"
                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">
            <asset:image alt="logo" src="linkSharing/logo.png" class="" style="height: 30px; width: 40px; " />
        </a>
        <a class="navbar-brand" href="#">
            Link Sharing
        </a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
        <g:if test="${session.getAttribute('user')}">

            <span class=" glyphicon glyphicon-user" style="font-size: 2em; padding-right: 5px;padding-top: 10px"></span>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false"><span class="caret"></span> &nbsp; User</a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Profile</a></li>
                    <li role="separator" class="divider"></li>
                    <li><g:link controller="login" action="logout">Logout</g:link></li>
                </ul>
            </li>
            </ul>
        </g:if>

        <form class="navbar-form navbar-right">
            <div class="input-group">
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-default pull-left">
                        <span class="glyphicon glyphicon-search">
                            <span class="sr-only">Search</span>
                        </span>
                    </button>
                    <input type="search" class="form-control" name="search" id="search"
                           style="width: 270px; background-color: #e5e5e5;" placeholder="Search">
                    <button type="reset" class="btn btn-default ">
                        <span class="glyphicon glyphicon-remove">
                            <span class="sr-only">Close</span>
                        </span>
                    </button>
                </span>
                <span class=" glyphicon glyphicon-paperclip pull-right" style="font-size: 2em ; padding-top: 4px;padding-left: 10px"></span>
                <span class=" glyphicon glyphicon-file pull-right" style="font-size: 2em ; padding-top: 4px; padding-left: 10px"></span>
            </div>
        </form>
    </div><!-- /.navbar-collapse -->
</nav>
<g:if test="${flash.loginError}" style="margin-left: 50px;margin-right: 500px">
    <div class="alert alert-danger" id="successMessage" style="text-align: center">
     <strong>${flash.loginError}</strong>
    </div>
</g:if>

<g:if test="${flash.error}" style="margin-left: 50px;margin-right: 500px">
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

    <div class="footer" role="contentinfo"></div>


    <asset:javascript src="application.js"/>

</body>
</html>
