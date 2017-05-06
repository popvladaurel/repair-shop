<!DOCTYPE html>
<html>
    <head>
        <title>Add New User</title>
  		<link rel="icon" type="image/png" href="../images/favicon.png"/>
        <link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
    </head>
    <body>
        <br>
        <br>
        <div class="phoneContent">
            <img src="../images/user_add_128.svg">
        </div>
            <form class="standardform" action="${pathToServlet}" method="POST">
                <div style="display:${show}">
                    <h4>Account details:</h4>
                    <input class="inputbox" type="text" placeholder="Username (required)" name="newAccountName" value="${newAccountName}" autofocus required ${disabled}>
                    <input class="inputbox inputspecial" type="number" placeholder="CNP (required)" name="newCNP"  value="${newCNP}"required ${disabled}>
                    <input class="inputbox" type="password " placeholder="Password (required)" name="newPassword" value="${newPassword}" required ${disabled}>
                </div>
                <h4>Personal details:</h4>
                <input class="inputbox" type="text" placeholder="Name (required)" name="newName" value="${newName}" required>
                <input class="inputbox inputspecial" type="email" placeholder="e-mail (required)" name="newEmail"  value="${newEmail}" required>
                <input class="inputbox" type="text" placeholder="Phone number" name="newPhoneNumber" value="${newPhoneNumber}" required>
                <input class="inputbox inputwide" type="text" placeholder="Address (Street, Number, City)" name="newAddress" value="${newAddress}" required>
                <button type="button" class="button" onclick="location.href='../home.jsp'">Back</button>
                <button class="button greenbutton" type="submit">${confirmButton}</button>
                <button class="button redbutton" type="reset">Clear Form</button>
            </form>
        </div>
    </body>
</html>
