<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Training Grounds</title>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap.min.css'/>" >
    <link rel="stylesheet" href="<c:url value='/styles/common.css'/>" >
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Training Grounds</a>
</nav>
<div class="container">
    <div class="content-container">
        <h4>Welcome to Spring Training Grounds</h4>
        <p>Click a box to view a selected training result</p>
        <div class="box-container">
            <div class="box">
                <p>Streams Training</p>
                <a  href="<c:url value='streams_training'/>">Go!</a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>" ></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>" ></script>
</html>