<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Client</title>
		<link rel="icon" type="image/png" href="../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
        <script rel="script" media="all" type="text/javascript" src="../scripts/sidenav.js"></script>
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
	<body>
        <div id="frame1" style="display:block; transition:0.5s">
            <form class="standardform" action="${pathToServlet}" method="POST">
                <div class="phoneContent">
                    <img src="../images/user_add_128.svg">
                </div>
                <div>
                    <h4>Account details:</h4>
                    <input class="inputbox inputhalf" type="text" placeholder="Name (required)" name="newName" autofocus required>
                    <input class="inputbox inputspecial inputhalf" type="number" placeholder="CNP (required)" name="newCNP" required>
                    <input class="inputbox inputhalf" type="text" placeholder="Phone number (required)" name="newPhoneNumber">
                    <input class="inputbox inputspecial inputhalf" type="email" placeholder="e-mail" name="newEmail" required>
                    <input class="inputbox inputwide" type="text" placeholder="Address (Street, Number, City)" name="newAddress">
                    <br>
                    <button class="button" onclick="location.href='../home.jsp'">Back</button>
                    <button class="button greenbutton" type="submit" onclick="location.href='../home.jsp'">Add Customer</button>
                    <button class="button redbutton" type="reset">Clear Form</button>
                </div>
            </form>
        </div>
	</body>
</html>