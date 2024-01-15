<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
		<head>
			<meta charset="UTF-8">
			<title>修改密碼</title>
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		</head>
	<body>
		<%@ include file="./header.jsp" %>
			<div style="padding: 15px">
			<form class="pure-form" method="post" action="./change_password">
				<fieldset style="text-align: center">
					<legend>使用者密碼變更</legend>
					<p />
					舊密碼: <input type="password" class="mx-auto" id="oldPassword" name="oldPassword" required><p />
					新密碼: <input type="password" id="newPassword" name="newPassword" required><p />
					新密碼: <input type="password" id="newPassword" name="newPassword" required><p />
					<button type="submit" class="pure-button pure-button-primary">密碼變更</button>
					<p />
					<div style="color: red">${ errorMessage }</div>
				</fieldset>
			</form>
			</div>
		
		<%@ include file="./footer.jsp" %>
	</body>
</html>