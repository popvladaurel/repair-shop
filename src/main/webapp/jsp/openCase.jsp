<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Open Case</title>
		<link rel="icon" type="image/png" href="../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
    <body>
    <%
        String user = (String) session.getAttribute("username");
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userusername")) userName = cookie.getValue();
                if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();}}
    %>
        <h1>Open Case</h1>
        <br>
        <h2>(under construction)</h2>
	</body>
</html>