<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test Form</title>
</head>
<body>
	<form action = "TestServlet" method = "POST">
		First Name: <input type = "text" name = "firstName">
		<br />
		Last Name: <input type = "text" name = "lastName">
		<input type = "submit" value = "Submit" />
	</form>
</body>
</html>