<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>View Companies</title>
		<link rel="icon" type="image/png" href="../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body>
    <br><br>
        <div class="standardform" style="transition:0.5s">
            <div class="phoneContent">
                <img src="../images/company_add_128.svg">
            </div>
            <div>
                <h4>Stored Companies:</h4>
                <table>
                    <tr>
                        <th>CUI</th>
                        <th>ONRC</th>
                        <th>Name</th>
                        <th>Address</th>
                    </tr>
                    <c:forEach items="${companiesList}" var="company">
                        <tr>
                            <td><c:out value="${company.CUI}"/></td>
                            <td><c:out value="${company.j}"/></td>
                            <td><c:out value="${company.name}"/></td>
                            <td><c:out value="${company.address.address}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
	</body>
</html>