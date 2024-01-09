<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ticketlist</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript">
				function cancelticket(ticketId) {
				window.location.href='./ticketlist/cancel?ticketId=' + ticketId;
			}
			
		</script>

</head>

<body>

     <%@ include file="./header.jsp" %>


    <!-- /v2/Rail/THSR/ODFare/{OriginStationID}/to/{DestinationStationID} -->

    <div class="container mt-5">
        <div class="table-responsive">
            <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th scope="col">車票編號</th>
                        <th scope="col">列車編號</th>
                        <th scope="col">車廂編號</th>
                        <th scope="col">座位編號</th>
                        <th scope="col">出發站</th>
                        <th scope="col">到達站</th>
                        <th scope="col">出發日期</th>
                        <th scope="col">出發時間</th>
                        <th scope="col">到達時間</th>
                        <th scope="col">價錢</th>
                        <th scope="col">取消訂票</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${ tickets }" var="ticket">
                    <tr>
                        <th scope="row">1</th>
                        <td>${ ticket.tran.tranNo }</td>
                        <td>${ ticket.carNo }</td>
                        <td>${ ticket.siteNo }</td>
                        <td>出發站</td>
                        <td>到達站</td>
                        <td>${ ticket.tran.date }</td>
                        <td>${ ticket.tran.departureTime }</td>
                        <td>${ ticket.tran.arrivalTime }</td>
                        <td>${ ticket.price }</td>
                        <td>                     
                           <button onClick="cancelticket(${ ticket.ticketId })">取消訂票</button>
                        </td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>



   <%@ include file="./footer.jsp" %>

</body>

</html>