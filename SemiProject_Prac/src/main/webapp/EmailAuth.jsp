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
			<form action = "auth.do" method="get">
				<td><input type=text name = number><td><input type="submit" value ="인증">
		</tr>
		</form>
		</table>
	</center>
	
	<%-- <form>
		<input type = hidden value ="${}">
		<input type = hidden value ="${}">
		<input type = hidden value ="${}">
		<input type = hidden value ="${}">
		<input type = hidden value ="${}">
	</form> --%>

</body>
</html>