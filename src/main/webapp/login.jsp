<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>RepairShop</title>
        <link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/favicon/apple-touch-icon-57x57.png" />
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/favicon/apple-touch-icon-114x114.png" />
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/favicon/apple-touch-icon-72x72.png" />
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/favicon/apple-touch-icon-144x144.png" />
        <link rel="apple-touch-icon-precomposed" sizes="60x60" href="images/favicon/apple-touch-icon-60x60.png" />
        <link rel="apple-touch-icon-precomposed" sizes="120x120" href="images/favicon/apple-touch-icon-120x120.png" />
        <link rel="apple-touch-icon-precomposed" sizes="76x76" href="images/favicon/apple-touch-icon-76x76.png" />
        <link rel="apple-touch-icon-precomposed" sizes="152x152" href="images/favicon/apple-touch-icon-152x152.png" />
        <link rel="icon" type="image/png" href="images/favicon/favicon-196x196.png" sizes="196x196" />
        <link rel="icon" type="image/png" href="images/favicon/favicon-96x96.png" sizes="96x96" />
        <link rel="icon" type="image/png" href="images/favicon/favicon-32x32.png" sizes="32x32" />
        <link rel="icon" type="image/png" href="images/favicon/favicon-16x16.png" sizes="16x16" />
        <link rel="icon" type="image/png" href="images/favicon/favicon-128.png" sizes="128x128" />
        <meta name="application-name" content="&nbsp;"/>
        <meta name="msapplication-TileColor" content="#FFFFFF" />
        <meta name="msapplication-TileImage" content="images/favicon/mstile-144x144.png" />
        <meta name="msapplication-square70x70logo" content="images/favicon/mstile-70x70.png" />
        <meta name="msapplication-square150x150logo" content="images/favicon/mstile-150x150.png" />
        <meta name="msapplication-wide310x150logo" content="images/favicon/mstile-310x150.png" />
        <meta name="msapplication-square310x310logo" content="images/favicon/mstile-310x310.png" />
        <link rel="stylesheet" media="all" type="text/css" href="styles/modal.css">
        <link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
		<link rel="stylesheet" media="all" type="text/css" href="styles/sideBar.css">
        <link rel="stylesheet" media="all" type="text/css" href="styles/table.css">
        <script rel="script" media="all" type="text/javascript" src="scripts/sideBar.js"></script>
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body onpageshow="checkMobile()">
        <jsp:directive.page contentType="text/html; charset=UTF-8"/>

        <!--START user options sideBar-->
        <div id="sideBar" class="sidebar">
            <a href="javascript:void(0)" class="closebutton" onclick="closeSideBar()">&times;</a>
            <img style="width: 128px; height: 128px; border-radius: 50%;" src="images/user_128.svg">
            <!--START login-->
            <div>
                <form method="POST" action="${pageContext.request.contextPath}/googleSignInServlet">
                    <button class="button googlebutton">Sign in using Google</button>
                </form>
                <p style="color:white;">or</p>
                <form method="POST" action="${pageContext.request.contextPath}/loginServlet">
                    <input class="inputbox" placeholder="Input Username" name="loginUserName" autofocus required>
                    <input class="inputbox" type="password" placeholder="Input Password" name="loginPassword" required>
                    <button class="button greenbutton">Sign in</button><br>
                </form>
            </div>
        </div>

        <div id="main" style="transition:0.5s;">
            <div id="categoryButtons" style="display:inline; white-space:nowrap; position:absolute; top:0; right:0; margin-right:10px; transition:0.5s; padding-top: 5px;">
                <input style="height: 48px;" type="image" src="images/user_48.svg" alt="User Actions" onclick="openSideBar()" >
            </div>
            <br><br>
            <div class="standardform">
                <br>
                <h2>This is a DEMO webbapp!</h2>
                <p>WARNING: this app is not yet suitable for production environments!</p>
                <p>Feel free to explore and modify it. Feedback and questions are always welcome!</p>
                <br>
                <p>Default login credentials:</p>
                <table class="table" style="width:50%">
                    <tr class="header">
                        <th>Username</th>
                        <th>Password</th>
                    </tr>
                    <tr class="table-entry">
                        <td>admin</td>
                        <td>admin</td>
                    </tr>
                </table>
                <br>
            </div>
            <div id="bottomModal" class="modal" style="display:${modalShow}">
                <div id="bottomModalContent" class="modal-content">
                    <div id="bottomModalHeader" class="modal-header" style="background-color:${modalColor}">
                        <input type="hidden" id="companyCUIfromTable">
                        <span class="close">&times;</span>
                        <h2 id="bottomModalText">${modal}</h2>
                    </div>
                </div>
            </div>
        </div>
        <script rel="script" media="all" type="text/javascript" src="scripts/bottomModal.js"></script>
    </body>
</html>
