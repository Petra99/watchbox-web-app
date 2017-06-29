<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="database" class="vso.database.storeDatabase" />
	<%
		String usernameAlreadyBeenTaken = "Sorry, this username is already taken.";
		String invalidPassword = "Sorry, the passwords you entered don't match!";
		String successfulRegistration = ", you registered successfully!";

		//tuk shte se vzemat poletataF
		//shte se slojat v baza danni
		//shte se izpishte uspeshna registraciq
		//shte ima prepratka kum log in
	%>
	<div class="wrapper">
		<h1 class="register-info">
			<%
				int minPass = 4;
				int numFilms = 0;

				String username = request.getParameter("username");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String passwordConf = request.getParameter("password-conf");

				if (password.length() > minPass) {
					if (password.equals(passwordConf)) {
						if (database.checkIsNameUnavailable(username)) {
							response.sendRedirect("registerError.html"); //TO DO
						} else {
							database.insertDataIntoUsers(username, email, password, numFilms);
							response.sendRedirect("registerConf.html");
							//out.print(username + successfulRegistration);
						}
					} else {
						response.sendRedirect("registerError.html"); //TO DO
					}
				}

			%>



		</h1>
	</div>
</body>
</html>