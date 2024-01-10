<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <title>高鐵訂票系統</title>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-warning">
        <h2>寶島高鐵</h2>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item active">
                    <a class="nav-link fs-4" href="/HighRailProject/mvc/highrail/main">首頁</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link fs-4" href="/HighRailProject/mvc/highrail/booking">訂票系統</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link fs-4" href="/HighRailProject/mvc/highrail/timetable">時刻表</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link fs-4" href="/HighRailProject/mvc/highrail/ticketlist">票夾</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
				<c:if test="${ empty sessionScope.user }">
                 <a class="btn btn-outline-success fs-3 text-black submit" href="<%= request.getContextPath() %>/mvc/highrail/login">登入</a>
                </c:if>

                <c:if test="${ not empty sessionScope.user }">
                 <p class="text-black me-3 mb-0">Welcome, ${ user.userName } ! 
                 <a href="${ pageContext.request.contextPath }/mvc/highrail/logout" class="btn btn-outline-danger">Logout</a></p>
                </c:if> 
                <!--
                <a href="/HighRailProject/mvc/highrail/login" class="btn btn-dark">登入</a>              
				<a href="/HighRailProject/mvc/highrail/logout" class="btn btn-dark">登出</a>
				-->              
            </ul>
        </div>
    </nav>

</body>
</html>