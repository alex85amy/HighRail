<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
			<!DOCTYPE html>
			<html>

			<head>
				<meta charset="UTF-8">
				<title>Register</title>
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
						height: 550px;
					}
				</style>
			</head>

			<body>
				 <%@ include file="./header.jsp" %>

				<div class="d-flex align-items-center justify-content-center vh-100 ">
					<sp:form modelAttribute="user" class="needs-validation"
						action="/HighRailProject/mvc/highrail/register" method="post">
						<h4 class="text-center">Register</h4>
						<p class="text-center text-danger fw-bold"> 請輸入個人資料 </p>
						
						<div style="color: red">${ registerMessage }</div>
						
						<div>
							<label for="username" class="form-label">Username</label>
							<sp:input type="text" class="form-control" path="userName"
								value="" required="required" />
							<div class="invalid-feedback">請輸入Username</div>
						</div>
						<div>
							<label for="password" class="form-label">Password</label>
							<sp:input type="password" class="form-control" path="userPassword"
								value="" required="required" />
							<div class="invalid-feedback">請輸入Password</div>
						</div>
						<div>
							<label for="email" class="form-label">Email</label>
							<sp:input type="text" class="form-control" path="userEmail" value=""
								required="required" />
							<div class="invalid-feedback">請輸入Email</div>
						</div>
						<div>
							<label for="phone" class="form-label">Phone</label>
							<sp:input type="text" class="form-control" path="userPhone" value=""
								required="required" />
							<div class="invalid-feedback">請輸入Phone</div>
						</div>
						<!-- <div>
					<label for="password" class="form-label">Addresss</label> <input
						type="text" class="form-control" id="password" name="password" value="" required>
					<div class="invalid-feedback">請輸入Addresss</div>
				</div> -->
						<div class="d-flex justify-content-center mt-5">
							<button class="btn btn-primary w-100" type="submit">Register</button>
						</div>
					</sp:form>

				</div>

			<%@ include file="./footer.jsp" %>
			
			</body>

			</html>