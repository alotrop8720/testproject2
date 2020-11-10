<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3>Welcome, Enter The User Details</h3>
<form:form method="POST"
           action="/users" modelAttribute="userCreateRequest">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/>${userCreateRequest.name}</td>
        </tr>
        <tr>
            <td><form:label path="surname">Surname</form:label></td>
            <td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">
                Birth Date</form:label></td>
            <td><form:input path="birthDate"/></td>
        </tr>
        <tr>
            <td><form:label path="gender">
                Gender</form:label></td>
            <td><form:input path="gender"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>