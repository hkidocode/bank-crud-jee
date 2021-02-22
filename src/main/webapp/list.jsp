<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/dashboard.css">
    <title>Dashboard - Central Bank</title>
</head>

<body>

<div class="nav">
    <h2 class="logo">Central Bank</h2>
    <a>Logout</a>
</div>
<div class="list-actions">
    <a href="<%= request.getContextPath() %>/new">Add client</a>
    <div class="btns">
        <a>Personal Account</a>
        <a>Entreprise Account</a>
    </div>
</div>
<table class="customers" id="customers">
    <tr>
        <th class="name">Full Name</th>
        <th class="address">Address</th>
        <th class="account-type">Account Type</th>
        <th class="role">Role</th>
        <th class="email">Email</th>
        <th class="password">Password</th>
        <th class="actions">Actions</th>
    </tr>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td><c:out value="${client.fullName}"/></td>
            <td><c:out value="${client.addresse}"/></td>
            <td><c:out value="${client.accountType}"/></td>
            <td><c:out value="${client.role}"/></td>
            <td><c:out value="${client.email}"/></td>
            <td><c:out value="${client.password}"/></td>
            <td>
                <a href="edit?id=<c:out value="${client.idClient}"/>">Edit</a>
                <a href="delete?id=<c:out value="${client.idClient}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>
