<%@ page import="linksharing.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Admin Dashboard</title>
	<meta name="layout" content="main"/>
</head>

<body>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading clearfix">
			%{--<div class="row" style="padding:0px;margin: 0px">--}%
			<div class="col-md-6 ">
				<h4>Users</h4>
			</div>

			%{--</div>--}%
		</div>

		%{--<div class="panel-body">--}%
		<table class="table table-striped table-bordered">
			<thead>
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Email</th>
				<th>Firstname</th>
				<th>Lastname</th>
				<th>Active</th>
				<th>Manage</th>
				%{--<th>Update</th>--}%
			</tr>
			</thead>
			<tbody>
			<g:each in="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.email}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.isActive}</td>
					<td><button onclick="toggleActivate(this)" name="${user.id}"><ls:toggleAtivateButton
							id="${user.id}"/></button></td>
					%{--<td><button class="btn btn-success" >update</button></td>--}%
				</tr></g:each>
			</tbody>
		</table>
		<g:paginate total="${linksharing.User.count()}" max="5"/>
		%{--</div>--}%
	</div>
</div>
<script type="text/javascript">
    function toggleActivate(element) {
// alert(element.name)
        $.ajax({
            type: 'POST',
            data: {id: element.name},
            url: '/admin/toggleActive',
            success: function () {
                location.reload()
            }

        })
    }
</script>
</body>
</html>