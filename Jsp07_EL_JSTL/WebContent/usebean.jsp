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


	<h1>jsp:useBean</h1>
	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session" ></jsp:useBean>
	<!-- Score sc = new Score();와 같은 뜻. 근데 scope세션에 이미 sc라는 객체 있으면 걔가 들어가고, 없어야 지금 애가 들어감-->
	
	<jsp:setProperty property="name" name="sc" value="hong"/>
	<!-- sc.setName("hong"); -->
	
	<jsp:getProperty property="name" name="sc"/>
	<!-- sc.getName(); -->
	
	<a href="result.jsp">scope</a>
</body>
</html>