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
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body onpageshow="checkMobile()">
        <jsp:directive.page contentType="text/html; charset=UTF-8" />
        <c:choose><%--START hide page elements before login--%>
            <c:when test="${sessionScope.authenticatedUser != null}">
                <c:set value="block" var="showElement"></c:set>
                <c:set value="none" var="hideElement"></c:set>
            </c:when>
            <c:when test="${sessionScope.authenticatedUser == null}">
                <c:set value="none" var="showElement"></c:set>
                <c:set value="block" var="hideElement"></c:set>
            </c:when>
        </c:choose><%--END hide page elements before login--%>
        <div id="sidenav1" class="sidenav"><!--START user options navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(1)">&times;</a>
            <img src="images/user_128.svg">
            <p style="color:white; display: ${showElement}">Welcome ${username}</p>
            <div style="display: ${hideElement}"><!--START login-->
                <form method="POST" action="/loginServlet">
                    <input class="inputbox" type="text" placeholder="Enter Username" name="loginUserName" autofocus required>
                    <input class="inputbox" type="password" placeholder="Enter Password" name="loginPassword" required>
                    <button class="button greenbutton" type="submit">Login</button><br>
                </form>
            </div><!--END login-->
            <div style="display: ${showElement}"><!--START available account actions-->
                <form method="GET" action="/personManagementServlet"><!--START edit account-->
                    <input type="hidden" name="action" value="editAccountInformation">
                    <button class="button" type="submit">Edit Account</button>
                </form>
                <form method="POST" action="/userAccountManagementServlet"><!--START change account password-->
                    <input type="hidden" name="action" value="changePassword">
                    <input id="newpasswordinput" class="inputbox" style="display:none" type="password" placeholder="Enter New Password" name="newPassword"
                           oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                    <button id="newpasswordbutton" class="button" onclick="changePassword()">Change Password</button>
                </form><!--END change account password-->
                <form method="POST" action="/userAccountManagementServlet"><!--START delete account-->
                    <input type="hidden" name="action" value="deleteAccount">
                    <button class="button" type="submit">Delete Account</button>
                </form><!--END delete account-->
                <form method="GET" action="/userAccountManagementServlet" ><!--START add new account-->
                    <input type="hidden" name="action" value="addAccount">
                    <button class="button" type="submit">New User</button>
                </form><!--END add new account-->
                <form action="/logoutServlet"><!--START logout-->
                    <button class="button redbutton" type="submit">Logout</button>
                </form><!--END logout-->
            </div><!--END available account actions-->
        </div><!--END user options navigation bar-->
        <div id="sidenav2" class="sidenav"><!--START Customer actions navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(2)">&times;</a>
            <img src="images/customer_128.svg">
            <p style="color:white;">Customer</p>
            <form method="GET" action="/personManagementServlet"><!--START add new customer/person-->
                <input type="hidden" name="action" value="addCustomer">
                <button class="button" type="submit">New Customer</button>
            </form><!--END add new customer/person-->
<!--TODO implement javascript search for customers/person to show an input box for query-->
            <form method="POST" action="/personManagementServlet"><!--START a search for customer/person-->
                <input type="hidden" name="action" value="listCustomers">
                <input id="searchforcostumerinput" class="inputbox" style="display:none" type="text" placeholder="Customer name" name="customerToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcostumerbutton" class="button" onclick="searchCustomers()">Search</button>
            </form><!--END a search for customer/person-->
        </div><!--END customer actions navigation bar-->
        <div id="sidenav3" class="sidenav"><!--START company actions navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(3)">&times;</a>
            <img src="images/company_128.svg">
            <p style="color:white;">Company</p>
            <form method="GET" action="/companyManagementServlet"><!--START add new company-->
                <input type="hidden" name="action" value="addCompany">
                <button class="button" type="submit">New Company</button>
            </form><!--END add new company-->
            <form method="GET" action="/companyManagementServlet"><!--START list companies-->
                <input type="hidden" name="action" value="listCompanies">
                <button class="button" type="submit">View Companies</button>
            </form><!--END list companies-->
<!--TODO implement javascript search for companies to show an input box for query-->
            <form method="GET" action="/companyManagementServlet"><!--START search list for companies-->
                <input type="hidden" name="action" value="listCompanies">
                <input id="searchforcompanyinput" class="inputbox" style="display:none" type="text" placeholder="Company name" name="companyToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcompanyinputbutton" class="button" onclick="listCompanies()">Search</button>
            </form><!--END search list for companies-->
        </div><!--END ompany actions navigation bar-->
        <div id="sidenav4" class="sidenav"><!--START settings navigation bar-->
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(4)">&times;</a>
            <img src="images/settings_128.svg">
            <p style="color:white;">Settings</p>
            <form method="GET" action="/CUICheckerServlet"><!--START change OpenAPI key-->
                <input type="hidden" name="action" value="changeKey">
                <input id="newkeyinput" class="inputbox" style="display:none" type="text" placeholder="Paste new OpenAPI key" name="openApiKey"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="newkeybutton" class="button" onclick="changeOpenAPIKey()">Change OpenAPI Key</button>
            </form><!--END change OpenAPI key-->
            <form method="GET" action="/CUICheckerServlet"><!--START show OpenAPI key-->
                <input type="hidden" name="action" value="viewKey">
                <button class="button" type="submit">View OpenAPI Key</button>
            </form><!--END show OpenAPI key-->
        </div><!--END settings navigation bar-->
        <div id="main">
            <div id="preferences" style="display:inline; white-space:nowrap; position:absolute; top:0px; right:0px; margin-right:10px; transition:0.5s"><!--START category buttons-->
                <input style="margin-top:10px; margin-bottom:10px;" type="image" src="images/user_48.svg" alt="User Actions" onclick="openNav(1, 2, 3, 4)" >
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="/images/customer_48.svg" alt="Customer Actions" onclick="openNav(2, 1, 3, 4)">
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="/images/company_48.svg" alt="Company Actions" onclick="openNav(3, 1, 2, 4)">
                <br class="phoneContent" style="display: ${showElement}">
                <input style="display: ${showElement};" type="image" src="images/settings_48.svg" alt="Settings" onclick="openNav(4, 1, 2, 3)">
                <br class="phoneContent" style="display: ${showElement}">
            </div><!--END category buttons-->
            <c:choose><%--START grab the page to show from servlet--%>
                <c:when test="${pageToShowInTheMainBody != null}">
                    <c:import url="${pageToShowInTheMainBody}"/>
                </c:when>
            </c:choose><%--END grab the page to show from servlet--%>
<!--TODO the mesages box could look better-->
            <div id="modalDiv" class="modal" style="display:${modalShow}"><!--START show the messages-->
                <div id="modalContent" class="modal-content">
                    <div class="modal-header" style="background-color:${modalColor}">
                        <span class="close">&times;</span>
                        <h2 id="modalText">${modalMessage}</h2>
                    </div>
                </div>
            </div><!--END show the messages-->
        </div>
        <script rel="script" media="all" type="text/javascript" src="scripts/modal.js"></script>
    </body>
</html>
