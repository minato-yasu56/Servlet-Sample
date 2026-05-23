<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<form action="login" method="post">
		<p style="color: red;">${errorMessage}</p>

		<input type="hidden" >
		<input type="text" name="userId" placeholder="ID"><br>
		<input type="password" name="pass" placeholder="Password"><br>
		<input type="submit" value="ログイン"></input>
	</form>
</body>
</html>