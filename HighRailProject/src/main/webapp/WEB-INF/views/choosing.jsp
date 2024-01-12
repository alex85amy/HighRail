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
                        <th scope="col">價錢</th>
                        <th scope="col">訂票</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${ trainTimes }" var="trainTime">
                    <tr>
                        <th scope="row" class="cell">${ trainTime.tranNo }</th>
                        <td class="cell">${ trainTime.startingStationName }</td>
                        <td class="cell">${ trainTime.endingStationName }</td>
                        <td class="cell">${ trainTime.departureDate }</td>
                        <td class="cell">${ trainTime.departureTime }</td>
                        <td class="cell">${ trainTime.arrivalTime }</td>
                        <td class="cell">${ price }</td>
                        <td>
                         <button id="sendDataBtn" class="btn btn-primary mt-2">訂票</button>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            </sp:form>
        </div>
    </div>


<%@ include file="./footer.jsp" %>

<script>
  $(document).ready(function() {
    // 點擊按鈕時觸發
    $("#sendDataBtn").click(function() {
      let cellData = {};

      // 遍歷每個包含 'cell' 類的 td 元素，並獲取其文本內容
      $(".cell").each(function(index) {
        cellData["value" + (index + 1)] = $(this).text();
      });

      // 使用 jQuery 的 AJAX 方法發送數據到後端
      $.ajax({
        url: '/HighRailProject/mvc/highrail/booking/choosing/result',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(cellData),
        success: function(response) {
          console.log('Data sent successfully:', response);
          // 可以在此處添加其他處理或回應
        },
        error: function(error) {
          console.error('Error sending data:', error);
        }
      });
    });
  });
</script>

</body>

</html>