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
						height: 650px;
					}
				</style>
			</head>

			<body>
				<div id="headerContainer">
				</div>

				<script>
					$(document).ready(function () {
						$.ajax({
							url: '../../header.html',
							type: 'GET',
							dataType: 'html',
							success: function (data) {
								$('#headerContainer').html(data);
							},
							error: function () {
								console.error('Failed to load header.html');
							}
						});
					});
				</script>

				<div class="d-flex align-items-center justify-content-center vh-100 ">
					<sp:form modelAttribute="user" class="needs-validation"
						action="/HighRailProject/mvc/highrail/register" method="post">
						<h4 class="text-center">Register</h4>
						<p class="text-center text-danger fw-bold"> 請輸入個人資料 </p>
						<div style="color: red">${ registerMessage }</div>
						<div>
							<label for="username" class="form-label">Username</label>
							<sp:input type="text" class="form-control" id="username" path="username" name="username"
								value="" required />
							<div class="invalid-feedback">請輸入Username</div>
						</div>
						<div>
							<label for="password" class="form-label">Password</label>
							<sp:input type="text" class="form-control" id="password" path="password" name="password"
								value="" required />
							<div class="invalid-feedback">請輸入Password</div>
						</div>
						<div>
							<label for="email" class="form-label">Email</label>
							<sp:input type="text" class="form-control" id="email" path="email" name="email" value=""
								required />
							<div class="invalid-feedback">請輸入Email</div>
						</div>
						<div>
							<label for="phone" class="form-label">Phone</label>
							<sp:input type="text" class="form-control" id="phone" path="phone" name="phone" value=""
								required />
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

				<div id="footerContainer">
				</div>

				<script>
					$(document).ready(function () {
						$.ajax({
							url: '../../footer.html',
							type: 'GET',
							dataType: 'html',
							success: function (data) {
								$('#footerContainer').html(data);
							},
							error: function () {
								console.error('Failed to load header.html');
							}
						});
					});
				</script>
			</body>

			</html>