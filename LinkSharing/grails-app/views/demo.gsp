<!doctype html>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>
<body>

<g:uploadForm controller="user" action="upload" method="post">
    <input type="file" name="myFile" /> <input type="submit" />
</g:uploadForm>
</body>
</html>
