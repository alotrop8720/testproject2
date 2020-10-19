<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bye</title>
</head>
<body>
<div>
    <h1>System Cars</h1>
</div>
<div>
    <table>
        <tr>
            <td>Car Id</td>
            <td>Model</td>
            <td>Creation year</td>
            <td>User id</td>
            <td>Price</td>
            <td>Color</td>
        </tr>
        <c:forEach var="cars" items="${cars}">
            <tr>
                <td>${cars.id}</td>
                <td>${cars.model}</td>
                <td>${cars.creatingYear}</td>
                <td>${cars.user}</td>
                <td>${cars.price}</td>
                <td>${cars.color}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
