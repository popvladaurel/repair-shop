<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>RepairShop</title>
		<link rel="icon" type="image/png" href="images/favicon.png"/>
        <link rel="stylesheet" media="all" type="text/css" href="styles/modal.css">
        <link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
		<link rel="stylesheet" media="all" type="text/css" href="styles/sidebar.css">
        <script rel="script" media="all" type="text/javascript" src="scripts/sidebar.js"></script>
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body onpageshow="checkMobile()">
        <jsp:directive.page contentType="text/html; charset=UTF-8"/>

        <%--START hide page elements before login--%>
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

        <!--START user options sideBar-->
        <div id="sidenav1" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(1)">&times;</a>
            <img src="images/user_128.svg">
            <p style="color:white; display: ${showElement}">Welcome ${username}</p>
            <!--START login-->
            <div style="display: ${hideElement}">
                <button class="button googlebutton">Sign in using Google</button><br>
                <p style="color:white;">or</p>
                <form method="POST" action="/loginServlet">
                    <input class="inputbox" type="text" placeholder="Input Username" name="loginUserName" autofocus required>
                    <input class="inputbox" type="password" placeholder="Input Password" name="loginPassword" required>
                    <button class="button greenbutton" type="submit">Sign in</button><br>
                </form>

            </div>

            <!--START available account actions-->
            <div style="display: ${showElement}">
                <!--START edit account-->
                <form method="GET" action="/personManagementServlet">
                    <input type="hidden" name="action" value="editAccountInformation">
                    <button class="button" type="submit">Edit Account</button>
                </form>
                <!--START change account password-->
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="changePassword">
                    <input id="newpasswordinput" class="inputbox" style="display:none" type="password" placeholder="Enter New Password" name="newPassword"
                           oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                    <button id="newpasswordbutton" class="button" onclick="changePassword()">Change Password</button>
                </form>
                <!--START delete account-->
                <form method="POST" action="/userAccountManagementServlet">
                    <input type="hidden" name="action" value="deleteAccount">
                    <button class="button" type="submit">Delete Account</button>
                </form>
                <!--START add new account-->
                <form method="GET" action="/userAccountManagementServlet" >
                    <input type="hidden" name="action" value="addAccount">
                    <button class="button" type="submit">New User</button>
                </form>
                <!--START logout-->
                <form action="/logoutServlet">
                    <button class="button redbutton" type="submit">Logout</button>
                </form>
            </div>
        </div>

        <!--START Customer actions sideBar-->
        <div id="sidenav2" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(2)">&times;</a>
            <img src="images/customer_128.svg">
            <p style="color:white;">Customer</p>
            <!--START add new customer/person-->
            <form method="GET" action="/personManagementServlet">
                <input type="hidden" name="action" value="addCustomer">
                <button class="button" type="submit">New Customer</button>
            </form>
<!--TODO implement javascript search for customers/person to show an input box for query-->
            <!--START a search for customer/person-->
            <form method="POST" action="/personManagementServlet">
                <input type="hidden" name="action" value="listCustomers">
                <input id="searchforcostumerinput" class="inputbox" style="display:none" type="text" placeholder="Customer name" name="customerToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcostumerbutton" class="button" onclick="searchCustomers()">Search</button>
            </form>
        </div>

        <!--START company actions sideBar-->
        <div id="sidenav3" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(3)">&times;</a>
            <img src="images/company_128.svg">
            <p style="color:white;">Company</p>
            <!--START add new company-->
            <form method="GET" action="/companyManagementServlet">
                <input type="hidden" name="action" value="addCompany">
                <button class="button" type="submit">New Company</button>
            </form>
            <!--START list companies-->
            <form method="GET" action="/companyManagementServlet">
                <input type="hidden" name="action" value="listCompanies">
                <button class="button" type="submit">View Companies</button>
            </form>
<!--TODO implement javascript search for companies to show an input box for query-->
            <!--START search list for companies-->
            <form method="GET" action="/companyManagementServlet">
                <input type="hidden" name="action" value="listCompanies">
                <input id="searchforcompanyinput" class="inputbox" style="display:none" type="text" placeholder="Company name" name="companyToSearch"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="searchforcompanyinputbutton" class="button" onclick="listCompanies()">Search</button>
            </form>
        </div>

        <!--START settings sideBar-->
        <div id="sidenav4" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav(4)">&times;</a>
            <img src="images/settings_128.svg">
            <p style="color:white;">Settings</p>
            <!--START change OpenAPI key-->
            <form method="GET" action="/CUICheckerServlet">
                <input type="hidden" name="action" value="changeKey">
                <input id="newkeyinput" class="inputbox" style="display:none" type="text" placeholder="Paste new OpenAPI key" name="openApiKey"
                       oninvalid="this.setCustomValidity('Required')" oninput="this.setCustomValidity('')" required autofocus>
                <button id="newkeybutton" class="button" onclick="changeOpenAPIKey()">Change OpenAPI Key</button>
            </form>
            <!--START show OpenAPI key-->
            <form method="GET" action="/CUICheckerServlet">
                <input type="hidden" name="action" value="viewKey">
                <button class="button" type="submit">View OpenAPI Key</button>
            </form>
            <!--START import ANAF TLS ceritificate-->
            <form method="GET" action="/InstallCertServlet">
                <input type="hidden" name="action" value="viewKey">
                <button class="button" type="submit">Import ANAF certificate</button>
            </form>

<!--TODO add method to disable the modal messages, but keep confirmations-->

        </div>
        <!--START category buttons-->
        <div id="preferences" style="display:inline; white-space:nowrap; position:absolute; top:0px; right:0px; margin-right:10px; transition:0.5s; padding-top: 5px;">
            <input style="height: 48px;" type="image" src="images/user_48.svg" alt="User Actions" onclick="openNav(1, 2, 3, 4)" >
            <br class="phoneContent" style="display: ${showElement}">
            <input style="display:${showElement}; height:48px;" type="image" src="/images/customer_48.svg" alt="Customer Actions" onclick="openNav(2, 1, 3, 4)">
            <input style="display:${showElement}; height:48px;" type="image" src="/images/company_48.svg" alt="Company Actions" onclick="openNav(3, 1, 2, 4)">
            <input style="display:${showElement}; height:48px;" type="image" src="images/settings_48.svg" alt="Settings" onclick="openNav(4, 1, 2, 3)">
        </div>

        <div id="main" style="transition:0.5s;">

            <!--START grab the page to show from servlet-->
            <c:choose>
                <c:when test="${pageToShowInTheMainBody != null}">
                    <c:import url="${pageToShowInTheMainBody}"/>
                </c:when>
            </c:choose>

<!--TODO the mesages box could look better-->

            <!--START show the messages-->
            <div id="modalDiv" class="modal" style="display:${modalShow}">
                <div id="modalContent" class="modal-content">
                    <div class="modal-header" style="background-color:${modalColor}">
                        <span class="close">&times;</span>
                        <h2 id="modalText">${modal}</h2>
                        <button id="modalButton" class="button" style="display:${modalButtonShow}; margin-left:250px; transition:0.5s;" href=${servletPath}>Yes</button>
                    </div>
                </div>
            </div>
        </div>
        <script rel="script" media="all" type="text/javascript" src="scripts/modal.js"></script>
        <script rel="script" media="all" type="text/javascript" src="jsp/company/companyActions.js" charset="UTF-8"></script>
    </body>
</html>
