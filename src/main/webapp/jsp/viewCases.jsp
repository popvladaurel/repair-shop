<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>View Cases</title>
		<link rel="icon" type="image/png" href="../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
            response.sendRedirect("../login.jsp");
            System.out.println("\n" + "Not authenticated, returning to login.");}
        %>
	</body>
</html>