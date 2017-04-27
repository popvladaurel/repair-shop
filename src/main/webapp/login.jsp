<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
  		  <link rel="icon" type="image/png" href="images/favicon.png"/>
        <link rel="stylesheet" media="all" type="text/css" href="styles/style.css">
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
    </head>
    <body>
        <img src="images/avatar.png">
        <form class="standardform" action="loginServlet" method="POST">
            <div>
                <input class="inputbox" type="text" placeholder="Enter Username" name="username" autofocus required>
                <input class="inputbox" type="password" placeholder="Enter Password" name="password" required>
                <button class="button" style="padding-top:0px; height:40px;" type="submit">Login</button><br>
                <button class="button" style="background-color:#f44336; padding-top:0px; height:40px;" type="reset">Clear</button>
            </div>
        </form>
    </body>
</html>
