<!DOCTYPE html>
<html>
<head>
	<title><%= session.getAttribute("login") %></title>

</head>
<body>

<center><h1>Hello <%= session.getAttribute("firstName") %></h1></center>

</body>
</html>