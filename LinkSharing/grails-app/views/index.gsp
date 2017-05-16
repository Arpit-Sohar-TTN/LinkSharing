<!doctype html>
<html>
<head>
	<title>Welcome to Grails</title>
</head>

<body>
<div id="content" role="main">
	<section class="row colset-2-its">
		<h1>Work Under Construction</h1>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>

		<div id="controllers" role="navigation">
			<h2>Available Controllers:</h2>
			<ul>
				<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
					<li class="controller">
						<g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
					</li>
				</g:each>
			</ul>
		</div>
	</section>
</div>

</body>
</html>
