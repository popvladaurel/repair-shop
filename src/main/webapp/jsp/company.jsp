<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Add Client</title>
		<meta name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=0.5 maximum-scale=1.0">
	</head>
	<body>
        <c:choose>
            <c:when test="${radiata == null || radiata == false}">
                <c:set value="greenbutton" var="radiataError"></c:set>
                <c:set value="Add Company" var="addButtonText"></c:set>
                <c:set value="submit" var="addButtonAction"></c:set>
            </c:when>
            <c:when test="${radiata == true}">
                <c:set value="redbutton" var="radiataError"></c:set>
                <c:set value="Companie dizolvata!" var="addButtonText"></c:set>
                <c:set value="reset" var="addButtonAction"></c:set>
            </c:when>
        </c:choose>
        <div class="standardform" style="transition:0.5s">
            <div class="phoneContent">
                <img src="../images/company_add_128.svg">
            </div>
            <div>
                <h4>Company Information (required):</h4>
                <form action="/CUICheckerServlet" method="POST">
                    <input  id="CuiToCheck" class="inputbox" type="text" placeholder="CUI" value="${newCompanyCUI}" name="newCompanyCUI" autofocus required>
                    <input class="inputbox" type="text" placeholder="ONRC" value="${newJ}" name="newJ">
                    <button class="button" style="margin-left:2px" type="submit">Verify Company</button>
                </form>
                <form action="${pathToServlet}" method="POST">
                    <input class="inputbox inputwide" type="text" placeholder="Name" value="${newCompanyName}" name="newCompanyName" required>
                    <input class="inputbox inputwide" type="text" placeholder="Address" value="${newCompanyAddress}" name="newCompanyAddress" required>
                    <input class="inputbox inputwide" type="text" placeholder="IBAN" name="newCompanyIBAN">
                    <input class="inputbox inputhalf" type="text" placeholder="Phone" value="${newCompanyPhone}" name="newCompanyPhone">
                    <input class="inputbox inputspecial inputhalf" type="email" placeholder="email" name="newCompanyEmail">
                    <h4>Validation Messages:</h4>
                    <input class="inputbox inputhalf" type="text" placeholder="OpenAPI validation message" value="${newCompanyState}" name="newCompanyState">
                    <input class="inputbox inputhalf" type="text" placeholder="ANAF validation message" value="${newAnafMessage}" name="newAnafMessage">
                    <input style="display:none" type="text" value="${radiata}" name="radiata">
                    <br>
                    <button type="button" class="button" onclick="location.href='../home.jsp'">Back</button>
                    <button class="button ${radiataError}" type="${addButtonAction}">${addButtonText}</button>
                    <button class="button redbutton" type="reset">Clear Form</button>
                </form>
            </div>
        </div>
	</body>
</html>