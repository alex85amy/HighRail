<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>高鐵訂票系統</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

    <%@ include file="./header.jsp" %>

    <!-- /v2/Rail/THSR/ODFare/{OriginStationID}/to/{DestinationStationID} -->

    <div class="container mt-5">
        <div class="table-responsive">
        <sp:form modelAttribute="user" method="post" action="/HighRailProject/mvc/highrail/booking/choosing/result">
            <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th scope="col">車號</th>
                        <th scope="col">出發站</th>
                        <th scope="col">到達站</th>
                        <th scope="col">出發日期</th>
                        <th scope="col">出發時間</th>
                        <th scope="col">到達時間</th>
                        <th scope="col">張數</th>
                        <th scope="col">價錢</th>
                        <th scope="col">訂票</th>
                    </tr>
                </thead>
                <tbody>
                
                    <tr>
                        <th scope="row">103</th>
                        <td>台北</td>
                        <td>台中</td>
                        <td>2023-12-25</td>
                        <td>06:15</td>
                        <td>06:33</td>
                        <td>1</td>
                        <td id="price">1000</td>
                        <td>
                         <button type="submit" class="btn btn-primary mt-2">訂票</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            </sp:form>
        </div>
    </div>


<%@ include file="./footer.jsp" %>

</body>

</html>