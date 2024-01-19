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
                        <input id="trainTime_${ trainTime.tranNo }" type="hidden" value='${trainTime.getJson()}' />
                        <td>
							<button class="btn btn-primary" onclick="sendData('${ trainTime.tranNo }')" id="">
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
       
   function sendData(tranNo) {

	   let tran = document.getElementById('trainTime_'+tranNo).value;
	   
	   var result = confirm("確定訂票？");

	   if (result) {
       $.ajax({
           url: '/HighRailProject/mvc/highrail/booking/choosing/result',
           type: 'POST',
           contentType: 'application/json',
           data: tran,
           success: function(response) {
               console.log('成功:', response);
               window.location = '/HighRailProject/mvc/highrail/ticketlist';
           },
           error: function(error) {
              
               console.error('失敗:', error);
           }
       });
	       
	   } else {
		   
	   }
	   
	   <%--
	   function sendData(tranNo) {
	    let tran = document.getElementById('trainTime_' + tranNo).value;

	    fetch('/HighRailProject/mvc/highrail/booking/choosing/result', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(tran)
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error('網路響應不正確');
	        }
	        return response.json();
	    })
	    .then(data => {
	        console.log('成功:', data);
	        window.location = '/HighRailProject/mvc/highrail/ticketlist';
	    })
	    .catch(error => {
	        console.error('失敗:', error);
	    });
	}	   
	   --%> 

    }
   
 
</script>

</body>

</html>