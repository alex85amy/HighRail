<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f4f4f4;
			margin: 0;
			padding: 0;
		}

		form {
			background-color: #fff;
			padding: 20px;
			padding-top: 40px;
			padding-bottom: 10px;
			border-radius: 8px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
			width: 500px;
			height: 500px;
		}
	</style>
</head>

<body>

 <%@ include file="./header.jsp" %>
    
	<div class="d-flex align-items-center justify-content-center vh-100 ">
		<form id="loginForm"  class="needs-validation"  action="/HighRailProject/mvc/highrail/login" method="post">
			<h4 class="text-center">Login</h4>
			<p class="text-center text-danger fw-bold"> 請輸入帳號密碼 </p>
			
			<div style="color: red">${ loginMessage }</div>
			
			<div>
				<label for="username" class="form-label">Username</label> <input type="text" class="form-control"
					id="username" name="username" value="" required>
				<div class="invalid-feedback">請輸入Username</div>
			</div>
			<div>
				<label for="password" class="form-label">Password</label> <input type="text" class="form-control"
					id="password" name="password" value="" required>
				<div class="invalid-feedback">請輸入Password</div>
			</div>
			<div class="d-flex justify-content-center mt-5">
			</div> 
			<div class="d-flex justify-content-center mt-5">
				<button class="btn btn-primary w-100" type="submit">Login</button>
			</div>
			<div>
				<p class="text-center text-danger fw-bold m-0"> 沒有帳號?請先註冊 </p>
				<a class="btn btn-primary w-100" href="/HighRailProject/mvc/highrail/register" >Register</a>
			</div>
		</form>
	</div>
	
<%@ include file="./footer.jsp" %>

</body>

</html>