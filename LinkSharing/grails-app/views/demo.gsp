<!doctype html>
<html>
<head>
	<title>Welcome to Grails</title>
	<meta name="layout" content="main">
	<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>

<body>
<g:form controller="person" action="upload" method="post" enctype="multipart/form-data">
	<input type="file" name="myFile"/><input type="submit"/>
</g:form>

</body>
</html>
