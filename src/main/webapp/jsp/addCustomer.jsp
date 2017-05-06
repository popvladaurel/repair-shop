<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Client</title>
		<link rel="icon" type="image/png" href="../images/favicon.png"/>
		<link rel="stylesheet" media="all" type="text/css" href="../styles/style.css">
        <script rel="script" media="all" type="text/javascript" src="../scripts/sidenav.js"></script>
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
	<body>
        <c:choose>
            <c:when test="${newcompanycui == null}">
                <script> openFrame(1, 2) </script>
            </c:when>
            <c:otherwise>
                <script> oopenFrame(2, 1) </script>
            </c:otherwise>
        </c:choose>
        <br><br><br>
        <div id="preferences" style="display:inline; white-space:nowrap; position:absolute; top:0px; right:0px; margin-right:10px; transition:0.5s">
            <input style="margin-top:10px;" type="image" src="/images/customer_48.svg" alt="Add new Customer" onclick="openFrame(1, 2)">
            <br class="phoneContent">
            <input type="image" src="/images/company_48.svg" tooltip="Add new Company" onclick="openFrame(2, 1)">
        </div>
        <div id="frame1" style="display:block; transition:0.5s">
            <form class="standardform" action="/customerManagementServlet" method="POST">
                <div class="phoneContent">
                    <img src="../images/user-add_128.svg">
                </div>
                <div>
                    <h4>Account details:</h4>
                    <input class="inputbox inputhalf" type="text" placeholder="Name (required)" name="newname" autofocus required>
                    <input class="inputbox inputspecial inputhalf" type="number" placeholder="CNP (required)" name="newcnp" required>
                    <input class="inputbox inputhalf" type="text" placeholder="Phone number (required)" name="newphonenumber">
                    <input class="inputbox inputspecial inputhalf" type="email" placeholder="e-mail" name="newemail" required>
                    <input class="inputbox inputwide" type="text" placeholder="Address (Street, Number, City)" name="newaddress">
                    <br>
                    <button class="button" onclick="location.href='../home.jsp'">Back</button>
                    <form>
                        <button class="button greenbutton" type="submit" onclick="location.href='../home.jsp'">Add Customer</button>
                    </form>
                    <button class="button redbutton" type="reset">Clear Form</button>
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${radiata == null || radiata == false}">
                <c:set value="greenbutton" var="radiataError"></c:set>
                <c:set value="Add Company" var="addButtonText"></c:set>
                <c:set value="submit" var="addButtonAction"></c:set>
            </c:when>
            <c:when test="${radiata == true}">
                <c:set value="redbutton" var="radiataError"></c:set>
                <c:set value="Disolved Company" var="addButtonText"></c:set>
                <c:set value="reset" var="addButtonAction"></c:set>
            </c:when>
        </c:choose>
        <div id="frame2" class="standardform" style="display:none; transition:0.5s">
            <div class="phoneContent">
                <img src="../images/company-add_128.svg">
            </div>
            <div>
                <h4>Company Information (required):</h4>
                <form action="/CUICheckerServlet" method="POST">
                    <input class="inputbox" type="text" placeholder="CUI" value="${newcompanycui}" name="newcompanycui" autofocus required>
                    <input class="inputbox inputspecial" type="text" placeholder="ONRC" value="${newj}" name="newj">
                    <button class="button" style="margin-left:2px" type="submit">Verify Company</button>
                </form>
                <form action="/companyManagementServlet" method="POST">
                    <input type="hidden" name="action" value="addCompany">
                    <input class="inputbox inputwide" type="text" placeholder="Name" value="${newcompanyname}" name="newcompanyname" required>
                    <input class="inputbox inputwide" type="text" placeholder="Address (Street, Number, City)" value="${newcompanyaddress}" name="newcompanyaddress">
                    <input class="inputbox inputwide" type="text" placeholder="IBAN" name="newcompanyiban">
                    <input class="inputbox inputhalf" type="text" placeholder="Phone" value="${newcompanyphone}" name="newcompanyphone">
                    <input class="inputbox inputspecial inputhalf" type="email" placeholder="email" name="newcompanyemail">
                    <h4>Validation Messages:</h4>
                    <input class="inputbox inputhalf" type="text" placeholder="State" value="${companystate}" name="companystate">
                    <input class="inputbox inputhalf" type="text" placeholder="Message" value="${anafmessage}" name="anafmessage">
                    <input style="display:none" type="text" value="${radiata}" name="radiata">
                    <br>
                    <button class="button" onclick="location.href='../home.jsp'">Back</button>
                    <button class="button ${radiataError}" type="${addButtonAction}">${addButtonText}</button>
                    <button class="button redbutton" type="reset">Clear Form</button>
                </form>
            </div>
        </div>
	</body>
</html>