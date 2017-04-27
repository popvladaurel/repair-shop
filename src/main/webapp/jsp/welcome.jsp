<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>RepairShop</title>
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
        <h1 class="phoneContent">RepairShop</h1>
        <h2>Welcome, <%=session.getAttribute("username")%></h2>
		<div style="text-align: center">
			<button class="button opencasebuton" type="button" onclick="location.href='openCase.jsp'">Create Case</button>
			<button class="button addclientbutton" type="button" onclick="location.href='addClient.jsp'">Add Client</button>
			<button class="button additembutton" type="button" onclick="location.href='addItem.jsp'">Add Item</button>
			<button class="button viewcasesbutton" type="button" onclick="location.href='viewCases.jsp'">View Cases</button>
			<br>
			<button onclick="location.href='addUserAccount.jsp'" class="button" type="submit" style="padding-top:0px; height:40px;">New User</button>
			<form action="../logoutServlet">
			    <button class="button" type="submit" style="background-color:#f44336; padding-top:0px; height:40px;">Logout</button>
			</form>
			<img class="phoneContent" src="../images/hardware.png" alt="Hardware icons" style="position:fixed; bottom:0px; left:15%; right:5%; width:75%">
		</div>
	</body>
</html>
