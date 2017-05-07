<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>RepairShop</title>
		<link rel="icon" type="image/png" href="images/favicon.png"/>
        <link rel="stylesheet" media="all" type="text/css" href="styles/modal.css">
        <link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
		<link rel="stylesheet" media="all" type="text/css" href="styles/sidenav.css">
        <script rel="script" media="all" type="text/javascript" src="scripts/sidenav.js"></script>
        <script rel="script" media="all" type="text/javascript" src="scripts/userAccountActions.js"></script>
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
    <body onpageshow="checkMobile()">
        <c:choose><%--Hide page elements before login--%>
            <c:when test="${sessionScope.authenticatedUser != null}">
                <c:set value="block" var="showElement"></c:set>
                <c:set value="none" var="hideElement"></c:set>
            </c:when>
            <c:when test="${sessionScope.authenticatedUser == null}">
                <c:set value="none" var="showElement"></c:set>
                <c:set value="block" var="hideElement"></c:set>
            </c:when>
        </c:choose><%--Hide page elements before login--%>
        <div id="sidenav1" class="sidenav"><!--User options navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(1)">&times;</a>
            <img src="images/user_128.svg">
            <p style="color:white; display: ${showElement}">Welcome ${username}</p>
            <div style="display: ${hideElement}"><!--Login-->
                <form method="POST" action="/loginServlet">
                    <input class="inputbox" type="text" placeholder="Enter Username" name="loginUserName" autofocus required>
                    <input class="inputbox" type="password" placeholder="Enter Password" name="loginPassword" required>
                    <button class="button greenbutton" type="submit">Login</button><br>
                </form>
            </div><!--Login-->
            <div style="display: ${showElement}"><!--Available account actions-->
                <form method="POST" action="/personManagementServlet">
                    <input type="hidden" name="action" value="editAccountInformation1">
                    <button class="button" type="submit">Edit Account</button>
                </form>
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="changePassword">
                    <input id="newpasswordinput" class="inputbox" style="display:none" type="password" placeholder="Enter New Password" name="newPassword"
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
            </div><!--Available account actions-->
        </div><!--User options navigation bar-->
        <div id="sidenav2" class="sidenav"><!--Customer actions navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(2)">&times;</a>
            <img src="images/customer_128.svg">
            <p style="color:white;">Customer</p>
            <form method="POST" action="/personManagementServlet">
                <input type="hidden" name="action" value="addCustomer">
                <button class="button" type="submit">New Customer</button>
            </form>
            <form method="POST" action="/personManagementServlet">
                <input type="hidden" name="action" value="listCustomers">
                <input id="searchforcostumerinput" class="inputbox" style="display:none" type="text" placeholder="Customer name" name="customerToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcostumerbutton" class="button" onclick="listCustomers()">Search</button>
            </form>
        </div><!--Customer actions navigation bar-->
        <div id="sidenav3" class="sidenav"><!--Company actions navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(3)">&times;</a>
            <img src="images/company_128.svg">
            <p style="color:white;">Company</p>
            <form method="POST" action="/companyManagementServlet">
                <input type="hidden" name="action" value="addCompany1">
                <button class="button" type="submit">New Company</button>
            </form>
            <form method="POST" action="/companyManagementServlet">
                <input type="hidden" name="action" value="listCompanies">
                <input id="searchforcompanyinput" class="inputbox" style="display:none" type="text" placeholder="Company name" name="companyToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcompanyinputbutton" class="button" onclick="listCompanies()">Search</button>
            </form>
        </div><!--Company actions navigation bar-->
        <div id="sidenav4" class="sidenav"><!--Settings navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(4)">&times;</a>
            <img src="images/settings_128.svg">
            <p style="color:white;">Settings</p>
            <form method="POST" action="/openAPIManagerServlet">
                <input type="hidden" name="action" value="changeKey">
                <input id="newkey" class="inputbox" style="display:none" type="text" placeholder="Paste new OpenAPI key" name="newKey"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button class="button" onclick="changeOpenAPIKey()">Change OpenAPI Key</button>
            </form>
        </div><!--Settings navigation bar-->
        <div id="main">
            <div id="preferences" style="display:inline; white-space:nowrap; position:absolute; top:0px; right:0px; margin-right:10px; transition:0.5s"><!--Category buttons-->
                <input style="margin-top:10px; margin-bottom:10px;" type="image" src="images/user_48.svg" alt="User Actions" onclick="openNav(1, 2, 3, 4)" >
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="/images/customer_48.svg" alt="Customer Actions" onclick="openNav(2, 1, 3, 4)">
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="/images/company_48.svg" alt="Company Actions" onclick="openNav(3, 1, 2, 4)">
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="images/settings_48.svg" alt="Settings" onclick="openNav(4, 1, 2, 3)">
                <br class="phoneContent" style="display: ${showElement}">
            </div><!--Category buttons-->
            <c:choose><%--Grab the page to show from servlet--%>
                <c:when test="${pageToShowInTheMainBody != null}">
                    <c:import url="${pageToShowInTheMainBody}"/>
                </c:when>
            </c:choose><%--Grab the page to show from servlet--%>
            <div id="modalDiv" class="modal" style="display:${modalShow}"><!--SHow the messages-->
                <div id="modalContent" class="modal-content">
                    <div class="modal-header">
                        <span class="close">&times;</span>
                        <h2 id="modalText">${modalMessage}</h2>
                    </div>
                </div>
            </div><!--Show the messages-->
        </div>
        <script rel="script" media="all" type="text/javascript" src="scripts/modal.js"></script>
    </body>
</html>
