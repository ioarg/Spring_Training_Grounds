<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Training Grounds</title>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap.min.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/common.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/customers.css'/>" >
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Training Grounds</a>
</nav>
<div class="container">
     <div class="content-container">
        <h4>Hibernate Training</h4>
        <p>Customers</p>
        <table class="table table-custom table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Email</th>
                </tr>
            </thead>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td><c:out value="${customer.first_name}"/></td>
                    <td><c:out value="${customer.last_name}"/></td>
                    <td><c:out value="${customer.email}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>" ></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>" ></script>
</html>