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
	
	<h1>RESULT</h1>
	<!-- 세션이랑 어플리케이션만 나옴.  -->
	page : <%=pageContext.getAttribute("pageScope") %>
	<br>
	<!-- result의 request를 부른 것이라 안나옴 . 그 값은 index의 request에 담겨있기 때문에-->
	request : <%=request.getAttribute("requestScope") %>
	<br>
	session : <%=session.getAttribute("sessionScope") %>
	<br>
	application : <%=application.getAttribute("applicationScope") %>
	<br>
	<%
		String requestVal = request.getParameter("requestVal");
	
	%>
		requestVal : <%=requestVal %>
</body>
</html>