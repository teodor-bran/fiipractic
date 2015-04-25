<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/persons.css" rel="stylesheet">
<link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/health.js"></script>
</head>
<body>
	<div class="page-header">
		<h1>Persons</h1>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table id="persons" class="table table-striped">
				<thead>
					<tr>
						<th>Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>CNP</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<button type="button" id="listPersons" class="btn btn-sm btn-primary">List
				persons</button>
			<button type="button" id="deletePerson" class="btn btn-sm btn-danger">Delete
				person</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-1 pull-right">
			<a href="<c:url value="j_spring_security_logout" />"> Logout</a>
		</div>
	</div>
</body>
</html>
