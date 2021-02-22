<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='css/dashboard.css'/>">
    <title>Dashboard - Central Bank</title>
</head>

<body>

<div class="nav">
    <h2 class="logo">Central Bank</h2>
</div>
<form method="GET" class="search" action="clients">
    <input type="search" placeholder="Name" name="last" id="last">
    <select name="type" id="type">
            <option value="Client">Client</option>
            <option value="Entreprise">Entreprise</option>
    </select>

    <button type="submit">Search</button>
</form>
<div class="list-actions">
    <a href="<%= request.getContextPath() %>/new">Add client</a>
</div>
<table class="customers" id="customers">
    <tr>
        <th hidden>id</th>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Account Number</th>
        <th>Account Type</th>
        <th>Balance</th>
        <th class="actions">Actions</th>
    </tr>
    <c:forEach var="compte" items="${comptes}">
        <tr>
            <td hidden><c:out value="${compte.idClient}"/></td>
            <td><c:out value="${compte.lastName}"/></td>
            <td><c:out value="${compte.firstName}"/></td>
            <td><c:out value="${compte.accountNum}"/></td>
            <td><c:out value="${compte.type}"/></td>
            <td><c:out value="${compte.balance}"/></td>
            <td>
                <a href="edit?id=<c:out value="${compte.idClient}"/>">Edit</a>
                <a href="delete?id=<c:out value="${compte.idClient}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    const select = document.getElementById('type');
    const first = document.getElementById('first');
    const last = document.getElementById('last');
    select.addEventListener('change', () => {
        first.hidden = select.value === 'Entreprise';
    });

</script>
</body>

</html>
