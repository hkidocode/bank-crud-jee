<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
    <title>Add/Update Client - Central Bank</title>
</head>

<body>
    <div class="nav">
        <h2 class="logo">Central Bank</h2>
    </div>

    <c:if test="${compte != null}">
    <form action="update" method="post">
        <h3>Edit Client</h3>
        <input type="hidden" name="id-client" value="${compte.idClient}">
        <c:if test="${compte.type == 'Client'}">
            <label for="first-name">First Name *</label>
            <input type="text" id="first-name" name="first-name" value="${compte.firstName}" required>
            <label for="last-name">Last Name *</label>
            <input type="text" id="last-name" name="last-name" value="${compte.lastName}" required>
        </c:if>
        <c:if test="${compte.type == 'Entreprise'}">
            <label for="last-name">Name *</label>
            <input type="text" id="last-name" name="last-name" value="${compte.lastName}" required>
        </c:if>
        </c:if>
        <c:if test="${compte == null}">
        <form action="insert" method="post">
            <h3>Create Client</h3>
            <label for="first-name">First Name</label>
            <input type="text" id="first-name" name="first-name" value="${compte.firstName}">
            <label for="last-name">Last Name *</label>
            <input type="text" id="last-name" name="last-name" value="${compte.lastName}" required>
        </c:if>

            <label for="account-number">Account Number *</label>
            <input type="number" id="account-number" name="account-number" value="${compte.accountNum}" required>
            <label for="balance">Balance *</label>
            <input type="number" id="balance" name="balance" value="${compte.balance}" required>
            <c:if test="${compte == null}">
                <label for="type">Type *</label>
                <select name="type" id="type" required>
                    <c:forEach items="${types}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select>
                <button type="submit">Add</button>
            </c:if>
            <c:if test="${compte != null}">
                <button type="submit">Update</button>
            </c:if>
        </form>
        <script>
            const select = document.querySelector('select');
            const label = document.querySelectorAll('label')[0];
            const input  = document.querySelectorAll('input')[0];
            if (select.hidden === false) {
                label.hidden = false;
                input.hidden = false;
            }
        </script>
</body>
</html>
