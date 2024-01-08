<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ticketlist</title>
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
                        <th scope="col">車票編號</th>
                        <th scope="col">列車編號</th>
                        <th scope="col">車廂編號</th>
                        <th scope="col">座位編號</th>
                        <th scope="col">出發站</th>
                        <th scope="col">到達站</th>
                        <th scope="col">出發日期</th>
                        <th scope="col">出發時間</th>
                        <th scope="col">到達時間</th>
                        <th scope="col">張數</th>
                        <th scope="col">價錢</th>
                        <th scope="col">取消訂票</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>101</td>
                        <td>01</td>
                        <td>C</td>
                        <td>台北</td>
                        <td>台中</td>
                        <td>2023-12-25</td>
                        <td>06:15</td>
                        <td>06:33</td>
                        <td>2</td>
                        <td>1500</td>
                        <td>
                            <button onClick="">取消訂票</button>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>



   <%@ include file="./footer.jsp" %>

</body>

</html>