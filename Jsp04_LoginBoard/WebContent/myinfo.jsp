<%@page import="com.login.dto.LoginDto"%>
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
<%
	LoginDto dto = (LoginDto)request.getAttribute("dto");
%>

	<h1>MY INFO</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>ADDR</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>PHONE</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td><%=dto.getMyemail() %></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="내정보 수정" onclick="location.href='logincontroller.jsp?command=updateinfo&myno<%=dto.getMyno()%>'"/>
				<input type="button" value="삭제" onclick="location.href='logincontroller.jsp?command=delete&myno=<%=dto.getMyno()%>'"/>
				<input type="button" value="이전 페이지로 돌아가기" onclick="location.href='logincontroller.jsp?command=usermain&myno<%=dto.getMyno()%>'"/>
			 </td>
		</tr>
	</table>
</body>
</html>