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
            <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th scope="col">車號</th>
                        <th scope="col">出發站</th>
                        <th scope="col">到達站</th>
                        <th scope="col">出發日期</th>
                        <th scope="col">出發時間</th>
                        <th scope="col">到達時間</th>
                        <th scope="col">價錢</th>
                        <th scope="col">訂票</th>
                    </tr>
                </thead>
                <tbody>
                	
                	<c:forEach items="${ trainTimes }" var="trainTime">
                    <tr>
                        <th scope="row" class="cell" id="tranNo">${ trainTime.tranNo }</th>
                        <td class="cell">${ trainTime.startingStationName }</td>
                        <td class="cell">${ trainTime.endingStationName }</td>
                        <td class="cell">${ trainTime.departureDate }</td>
                        <td class="cell">${ trainTime.departureTime }</td>
                        <td class="cell">${ trainTime.arrivalTime }</td>
                        <td class="cell">${ trainTime.price }</td>
                        <td>
							<button class="btn btn-primary" onclick="sendData('${trainTime}')" id="">
								訂票
							</button>
						</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
         
        </div>
    </div>


<%@ include file="./footer.jsp" %>

<script>
    function choosing(tran) {
        console.log('tran = ', tran);
        
    }
    
  
   function sendData(tran) {
        // 准备要发送的数据
        var data = {
        	tran: tran
        };

        // 使用 jQuery 的 $.ajax 函数发送 POST 请求
        $.ajax({
            url: '/HighRailProject/mvc/highrail/booking/choosing/result',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                // 请求成功的逻辑
                console.log('请求成功:', response);
            },
            error: function(error) {
                // 请求失败的逻辑
                console.error('请求失败:', error);
            }
        });
    }
 
   
</script>

</body>

</html>