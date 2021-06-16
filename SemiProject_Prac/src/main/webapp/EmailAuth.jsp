<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<table border="1">
		<tr>
			<td>인증번호를 기입하세요.</td>
		</tr>
		<tr>
			<form action = "signup.do" method="get">
				<td><input type=text name = number><td><input type="submit" value ="인증">		
				<input type = hidden name = "email" value ="${email}">
				<input type = hidden name = "password" value ="${password}">
				<input type = hidden name = "nickname" value ="${nickname}">
				<input type = hidden name = "phone" value ="${phone}">
				<input type = hidden name = "snscheck" value ="${snscheck}">
		</tr>
		</form>
		</table>
	</center>
	



</body>
</html>