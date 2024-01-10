<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class="container mt-5">
    <h2 class="mb-4">高鐵訂票系統</h2>

    <form method="post" action="/HighRailProject/mvc/highrail/booking/choosing">
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
        
        <div style="color: red">${ bookingMessage }</div>

        <div class="form-group">
            <label for="departureDate">出發日期</label>
            <input type="date" class="form-control" id="departureDate" name="departureDate" required>
        </div>

        <div class="form-group">
            <label for="quantity">張數</label>
            <select class="form-control" id="quantity" name="quantity">
                <option>1</option>
                <option>2</option>
                
            </select>
        </div>

        <!-- <div>
            <label>價錢</label>
            <p  class="fs-3">多少錢</p>

        </div> -->

        <button type="submit" class="btn btn-primary">確認</button>
    </form>
</div>

<%@ include file="./footer.jsp" %>

</body>
</html>