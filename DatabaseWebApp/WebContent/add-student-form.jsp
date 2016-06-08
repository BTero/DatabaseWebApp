<!DOCTYPE html>
<html>
	<head>
		<title>Add Student</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
		<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
	</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add Student</h3>
		<form action="StudentControllerServlet" method="get">
			<input type="hidden" name="command" value="ADD"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label>
						<td><input type="text" name="firstName"/>
					</tr>
					<tr>
						<td><label>Last Name:</label>
						<td><input type="text" name="lastName"/>
					</tr>
					<tr>
						<td><label>Email:</label>
						<td><input type="text" name="email"/>
					</tr>
					<tr>
						<td><label></label>
						<td><input type="submit" value="Save" class="save"/>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear:both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>
</html>