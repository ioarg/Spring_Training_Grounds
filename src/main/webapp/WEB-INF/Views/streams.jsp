<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Training Grounds</title>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap.min.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/common.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/streams_style.css'/>" >
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Training Grounds</a>
</nav>
<div class="container">
     <div class="content-container">
        <h4>Streams Training</h4>
        <p>Full Menu</p>
        <table class="table table-custom table-striped">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Vegetarian</th>
                    <th scope="col">Calories</th>
                    <th scope="col">Type</th>
                </tr>
            </thead>
            <c:forEach var="dish" items="${menu}">
                <tr>
                    <td><c:out value="${dish.name}"/></td>
                    <td><c:out value="${dish.vegetarian}"/></td>
                    <td><c:out value="${dish.calories}"/></td>
                    <td><c:out value="${dish.type}"/></td>
                </tr>
            </c:forEach>
        </table>
        <div class="button-container">
            <div id="high_cl_btn" class="btn btn-info">High Caloric Meals</div>
            <div id="veg_btn" class="btn btn-info">Vegetarian Meals</div>
        </div>
        <div id="result-container"></div>
    </div>
</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>" ></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>" ></script>
<script src="<c:url value='/scripts/streams.js'/>" ></script>
</html>