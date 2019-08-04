<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
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
        <h4><spring:message code="home_greeting" text="default"/></h4>
        <div class="text_align_left data_block">
            <p class="normal_p">REST Endpoints to use :</p>
            <ul>
                <li><a href="<c:url value='/rest_proj1/hello'/>">/rest_proj1/hello</a></li>
                <li><a href="<c:url value='/rest_proj1/students'/>">/rest_proj1/students</a></li>
            </ul>
        </div>
        <p><spring:message code="home_selection" text="default"/></p>
        <div id="box_container" class="data_block">
            <div class="box">
                <p>Choose Language</p>
                <div class="custom-control custom-radio">
                    <input id="radio_lang_en" type="radio" name="customRadio" class="custom-control-input">
                    <label class="custom-control-label" for="radio_lang_en">
                        <spring:message code="lang.eng" text="default"/>
                    </label>
                </div>
                <div class="custom-control custom-radio">
                    <input id="radio_lang_el" type="radio" name="customRadio" class="custom-control-input">
                    <label class="custom-control-label" for="radio_lang_el">
                        <spring:message code="lang.el" text="default"/>
                    </label>
                </div>
            </div>
            <div class="box">
                <p>Streams Training</p>
                <a  href="<c:url value='streams_training'/>">Go!</a>
            </div>
            <div class="box">
                <p>Customer Database</p>
                <a  href="<c:url value='/customer'/>">Go!</a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="<c:url value='/scripts/jquery-3.3.1.min.js'/>" ></script>
<script src="<c:url value='/scripts/bootstrap.min.js'/>" ></script>
<script src="<c:url value='/scripts/home.js'/>" ></script>
</html>