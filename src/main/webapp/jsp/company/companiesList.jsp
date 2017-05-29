<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>View Companies</title>
		<link rel="icon" type="image/png" href="../../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../../styles/style.css">
        <link rel="stylesheet" media="all" type="text/css" href="../../styles/modal.css">
        <link rel="stylesheet" media="all" type="text/css" href="../../styles/table.css">
        <script rel="script" media="all" type="text/javascript" src="../../scripts/companyActions.js"></script>
        <script rel="script" media="all" type="text/javascript" src="../../scripts/modal.js"></script>
        <meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    </head>
    <body>
    <br><br>
    <div class="standardform" style="transition:0.5s;">
        <img style="float:left; margin-left: 59px; margin-right: -11px;" class="phoneContent" src="../../images/company_128.svg"><br>
        <input type="text" id="searchBox" class="inputbox inputdouble" onkeyup="searchCompanies()" placeholder="Search companies..." autofocus>
        <button type="button" class="button" onclick="sortCompanies(0)">Sort by CUI</button>
        <button type="button" class="button" onclick="sortCompanies(2)">Sort by Name</button><br>
    </div><br>
    <div class="standardform">
        <table id="searchableTable" class=table">
            <tr class="header">
                <th style="width:10%;">CUI</th>
                <th class="hideOnMobile" style="width:10%;" >ONRC</th>
                <th style="width:30%;">Name</th>
                <th class="hideOnMobile" style="width:50%;">Address</th>
            </tr>
            <c:forEach items="${companiesList}" var="company">
                <tr class="table-entry" style="cursor:pointer" onclick="showActionsModal(this)">
                    <td><c:out value="${company.CUI}"/></td>
                    <td class="hideOnMobile"><c:out value="${company.j}"/></td>
                    <td><c:out value="${company.name}"/></td>
                    <td class="hideOnMobile"><c:out value="${company.address.address}"/></td>
                </tr>
<!--TODO add methods to edit or delete entries -->
            </c:forEach>
        </table>
    </div>
	</body>
</html>