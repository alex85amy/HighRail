<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>timetable</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>

    <%@ include file="./header.jsp" %>

<div class="container mt-5">
    <h2 class="mb-4">高鐵時刻表查詢</h2>

    <!-- /v2/Rail/THSR/DailyTimetable/OD/{OriginStationID}/to/{DestinationStationID}/{TrainDate} -->

    <form method="post" action="/HighRailProject/mvc/highrail/timetable">
        <div class="form-group">
            <label for="fromStation">出發站</label>
            <select class="form-control" id="fromStation" name="fromStation">
                <option value="0990">南港</option>
                <option value="1000">台北</option>
                <option value="1010">板橋</option>
                <option value="1020">桃園</option>
                <option value="1030">新竹</option>
                <option value="1035">苗栗</option>
                <option value="1040">台中</option>
                <option value="1043">彰化</option>
                <option value="1047">雲林</option>
                <option value="1050">嘉義</option>
                <option value="1060">台南</option>
                <option value="1070">左營</option>
            </select>
        </div>

        <div class="form-group">
            <label for="toStation">到達站</label>
            <select class="form-control" id="toStation" name="toStation">
                <option value="0990">南港</option>
                <option value="1000">台北</option>
                <option value="1010">板橋</option>
                <option value="1020">桃園</option>
                <option value="1030">新竹</option>
                <option value="1035">苗栗</option>
                <option value="1040">台中</option>
                <option value="1043">彰化</option>
                <option value="1047">雲林</option>
                <option value="1050">嘉義</option>
                <option value="1060">台南</option>
                <option value="1070">左營</option>
            </select>
        </div>
        
        <div style="color: red">${ checkingMessage }</div>

        <div class="form-group">
            <label for="departureDate">出發日期</label>
            <input type="date" class="form-control" id="departureDate" name="departureDate" required>
        </div>
        
        <button type="submit" class="btn btn-primary mt-2">確認</button>
        </form>
    </div>

    <div class="container mt-5">
        <div class="table-responsive">
            <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th scope="col">車號</th>
                        <th scope="col">出發站</th>
                        <th scope="col">到達站</th>
                        <th scope="col">出發日期</th>
                        <th scope="col">出發時間</th>
                        <th scope="col">到達時間</th>
                    </tr>
                </thead>
	                <tbody>
	                	<c:forEach items="${ trainTimes }" var="trainTime">
	                		<tr>
		                		<td>${ trainTime.tranNo }</td>
		                		<td>${ trainTime.startingStationName }</td>
		                		<td>${ trainTime.endingStationName }</td>
		                		<td>${ trainTime.departureDate }</td>
		                		<td>${ trainTime.departureTime }</td>
		                		<td>${ trainTime.arrivalTime }</td>
	                		</tr>
	                	</c:forEach>
	                </tbody>
            </table>
        </div>
    </div>


    
<%@ include file="./footer.jsp" %>

  <script>
    // 获取当前日期
    const currentDate = new Date().toISOString().split('T')[0];

    // 获取日期输入框元素
    const dateInput = document.getElementById('departureDate');

    // 设置日期输入框的最小值为当前日期
    dateInput.setAttribute('min', currentDate);
  </script>
    
</body>
</html>