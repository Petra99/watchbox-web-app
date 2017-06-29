<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<body>
	<jsp:useBean id="database" class="vso.database.storeDatabase" />
	<div class="wrapper">
		<h1 class="register-info">
			<%
				String username = request.getParameter("username");
					String password = request.getParameter("password");

					if (username.length() == 0 || password.length() == 0 || !database.checkNameInDb(username) || !database.checkPasswordInDb(password)) {
								response.sendRedirect("loginResponse.jsp?success=false");
								
					} else {
						response.sendRedirect("loginResponse.jsp?success=true&username="+ request.getParameter("username"));
					}
			%>



		</h1>
		<h2>hellooooo</h2>
	</div>

</body>
</html>