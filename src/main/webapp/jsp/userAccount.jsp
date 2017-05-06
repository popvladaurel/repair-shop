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
            <img src="../images/user-add_128.svg">
        </div>
            <form class="standardform" action="${pathtoservlet}" method="POST">
                <div style="display:${show}">
                    <h4>Account details:</h4>
                    <input class="inputbox" type="text" placeholder="Username (required)" name="newaccountname" value="${newaccountname}" autofocus required ${disabled}>
                    <input class="inputbox inputspecial" type="number" placeholder="CNP (required)" name="newcnp"  value="${newcnp}"required ${disabled}>
                    <input class="inputbox" type="password " placeholder="Password (required)" name="newpassword" value="${newpassword}" required ${disabled}>
                </div>
                <h4>Personal details:</h4>
                <input class="inputbox" type="text" placeholder="Name (required)" name="newname" value="${newname}" required>
                <input class="inputbox inputspecial" type="email" placeholder="e-mail (required)" name="newemail"  value="${newemail}" required>
                <input class="inputbox" type="text" placeholder="Phone number" name="newphonenumber" value="${newphonenumber}" required>
                <input class="inputbox inputwide" type="text" placeholder="Address (Street, Number, City)" name="newaddress" value="${newaddress}" required>
                <button type="button" class="button" onclick="location.href='../home.jsp'">Back</button>
                <button class="button greenbutton" type="submit">${confirmbutton}</button>
                <button class="button redbutton" type="reset">Clear Form</button>
            </form>
        </div>
    </body>
</html>
