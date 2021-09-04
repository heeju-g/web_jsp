<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("pageScope","page scope value");
	request.setAttribute("requestScope","request scope value");
	session.setAttribute("sessionScope","session scope value");
	application.setAttribute("applicationScope","application scope value");
%>
</head>
<body>
	<h1>INDEX</h1>
	page : <%=pageContext.getAttribute("pageScope") %>
	<br>
	request : <%=request.getAttribute("requestScope") %>
	<br>
	<!-- 세션은 해당 프로젝트 전체에서 유효. 근데 이제 만료시간이 있는 -->
	session : <%=session.getAttribute("sessionScope") %>
	<br>
	application : <%=application.getAttribute("applicationScope") %>
	<br>
	 <a href="result.jsp">result</a>
	
	<br>
	<a href="scope.do">servlet</a>
	<br>
	<form action="scope.do" method="get">
	<!-- indec는 scope.do(서블릿)에게 요청했지만 서블릿이 result에게 forward했기 때문에 그대로 get방식임. -->
		<input type="hidden" name="requestVal" value="my request value"/>
		<input type="submit" value="request"/>
	</form>
</body>
</html>