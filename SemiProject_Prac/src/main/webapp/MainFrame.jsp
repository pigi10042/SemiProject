<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="ScriptJSP.jsp"></c:import>
<c:import url="Css.jsp"></c:import>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file ='FrontFrame.jsp' %>
    <div class="container" id = d_body>
    	<div>
    	<form action="${pageContext.request.contextPath}/enroll.do" method=post> 
            <div class="input-group-prepend">
              <span class="input-group-text">내용</span>
            </div>
            <div>
            <textarea class="form-control" name = comments aria-label="With textarea" rows="6" id = text></textarea>
          </div>
          <button type="submit" class="btn btn-primary mb-2" id = send>등록</button>
          <button type="submit" class="btn btn-primary mb-2" id = reset>초기화</button>
    	</form>
    	</div>
    	<c:forEach var="item" items="${defalut}">
	    	<div class="card">
				  <div class="card-body">
				    <h5 class="card-title">${item.write}</h5>
				    <p class="card-text">${item.contents}</p>
				  </div>
			</div>
		</c:forEach>
		</div>
</body>
</html>