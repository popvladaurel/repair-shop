<!DOCTYPE html>
<html>
    <head>
        <title>Add New User</title>
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
        <img src="../images/avatar.png">
        <form class="standardform" action="${pageContext.request.contextPath}/userAccountManagementServlet" method="POST">
            <div>
                <input class="inputbox" type="text" placeholder="Choose a username" name="newaccountname" autofocus required>
                <input class="inputbox" type="text" placeholder="Enter your name" name="newname" required>
                <input class="inputbox" type="text" placeholder="Enter an e-mail" name="newemail" required>
                <input class="inputbox" type="password" placeholder="Enter password" name="newpassword" required>
                <button class="button" style="padding-top:0px; height:40px;" type="submit">Create User</button><br>
                <button class="button" style="background-color:#f44336; padding-top:0px; height:40px;" type="reset">Clear Form</button>
            </div>
        </form>
    </body>
</html>
