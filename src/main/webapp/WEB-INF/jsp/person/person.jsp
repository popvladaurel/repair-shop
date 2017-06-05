<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Client</title>
		<link rel="icon" type="image/png" href="../../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
	<body>
        <br><br>
        <div id="frame1" style="display:block; transition:0.5s">
            <form class="standardform" action="${pathToServlet}" method="POST">
                <div class="phoneContent">
                    <img src="images/customer_add_128.svg">
                </div>
                <div>
                    <h4>Personal details:</h4>
                    <input class="inputbox inputhalf" placeholder="Name (required)" name="newName" autofocus required>
                    <input class="inputbox inputspecial inputhalf" type="number" placeholder="CNP (required)" name="newCNP" required>
                    <input class="inputbox inputhalf" placeholder="Phone number (required)" name="newPhoneNumber">
                    <input class="inputbox inputspecial inputhalf" type="email" placeholder="e-mail" name="newEmail" required>
                    <input class="inputbox inputwide" placeholder="Address" name="newAddress">
                    <br>
                    <button class="button" onclick="location.href='../home.jsp'">Back</button>
                    <button class="button greenbutton">Add Customer</button>
                    <button class="button redbutton" type="reset">Clear Form</button>
                </div>
            </form>
        </div>
	</body>
</html>