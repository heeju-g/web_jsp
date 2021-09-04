<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>
	
	<jsp:getProperty property="name" name="sc"/><br>
	<!-- 세션 스코프에 담겨있는 거 찾아서 가져옴 -->
	${sc.name }
	
</body>
</html>