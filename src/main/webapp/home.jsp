<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>RepairShop</title>
		<link rel="icon" type="image/png" href="images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
		<link rel="stylesheet" media="all" type="text/css" href="styles/sidenav.css">
        <link rel="stylesheet" media="all" type="text/css" href="styles/modal.css">
        <script rel="script" media="all" type="text/javascript" src="scripts/sidenav.js"></script>
        <script rel="script" media="all" type="text/javascript" src="scripts/userAccountActions.js"></script>
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
	<body onpageshow="openNav(1, 2)">
        <c:choose>
            <c:when test="${sessionScope.authenticatedUser != null}">
                <c:set value="block" var="showElement"></c:set>
                <c:set value="none" var="hideElement"></c:set>
            </c:when>
            <c:when test="${sessionScope.authenticatedUser == null}">
                <c:set value="none" var="showElement"></c:set>
                <c:set value="block" var="hideElement"></c:set>
            </c:when>
        </c:choose>
        <div id="sidenav1" class="sidenav"><!--user actions navbar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(1)">&times;</a>
            <img src="images/user_128.svg">
            <p style="color:white; display: ${showElement}">Welcome ${username}</p>
            <div style="display: ${hideElement}"><!--input Login information and trigger the loginServlet-->
                <form method="POST" action="/loginServlet">
                    <input class="inputbox" type="text" placeholder="Enter Username" name="username" autofocus required>
                    <input class="inputbox" type="password" placeholder="Enter Password" name="password" required>
                    <button class="button greenbutton"  type="submit">Login</button><br>
                </form>
            </div>
            <div style="display: ${showElement}"><!--present available account actions and trigger userAccountManagementServlet-->
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="editAccountInformation1">
                    <button class="button" type="submit">Edit Account</button>
                </form>
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="changePassword">
                    <input id="newpasswordinput" class="inputbox" style="display:none" type="password" placeholder="Enter New Password" name="newpassword"
                           oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                    <button id="newpasswordbutton" class="button" onclick="changePassword()">Change Password</button>
                </form>
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="deleteAccount">
                    <button class="button" type="submit">Delete Account</button>
                </form>
                <form method="POST" action="/userAccountManagementServlet" >
                    <input type="hidden" name="action" value="addAccount1">
                    <button class="button" type="submit">New User</button>
                </form>
                <form action="/logoutServlet">
                    <button class="button redbutton" type="submit">Logout</button>
                </form>
            </div>
        </div>
        <div id="sidenav2" class="sidenav"><!--client actions navbar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(2)">&times;</a>
            <img src="images/settings_128.svg">
            <p style="color:white;">Settings</p>
            <button class="button largebutton" style="background-image: url(images/newtask.png); display: ${showElement}" type="button" onclick="location.href='jsp/openCase.jsp'">Create Case</button>
            <button class="button largebutton" style="background-image: url(images/addclient.png); display: ${showElement}" type="button" onclick="location.href='jsp/addCustomer.jsp'">Add Client</button>
            <button class="button largebutton" style="background-image: url(images/additem.png); display: ${showElement}" type="button" onclick="location.href='jsp/addItem.jsp'">Add Item</button>
            <button class="button largebutton" style="background-image: url(images/viewcases.png); display: ${showElement}" type="button" onclick="location.href='jsp/viewCases.jsp'">View Cases</button>
        </div>
        <div id="main">
			<div id="preferences" style="display:inline; white-space:nowrap; position:absolute; top:0px; right:0px; margin-right:10px; transition:0.5s">
                <input style="margin-top:10px;" type="image" src="images/user_64.svg" alt="User Actions" onclick="openNav(1, 2)" >
                <br class="phoneContent">
				<input style="display: ${showElement};" type="image" src="images/settings_48.svg" alt="Preferences" onclick="openNav(2, 1)">
			</div>
			<h1 class="phoneContent">RepairShop</h1>
            <br>
            <br>
			<div style="text-align: center; font-size: 25px; color: rgb(129, 129, 129);">
				<p>Under construction</p>
			</div>
            <h2>Bottom Modal</h2>
            <button id="openModal">Open Modal</button>
            <div id="modalDiv" class="modal" style="display:${modalshow}">
                <div id="modalContent" class="modal-content">
                    <div class="modal-header">
                        <span class="close">&times;</span>
                        <h2 id="modalText">${modalmessage}</h2>
                    </div>
                </div>
            </div>
        </div>
        <script rel="script" media="all" type="text/javascript" src="scripts/modal.js"></script>
    </body>
</html>
