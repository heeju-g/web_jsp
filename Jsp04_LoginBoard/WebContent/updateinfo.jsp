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

	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateinfores"/>
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>" />
		<table border="1">
			<tr>
				<th>ID</th>
				<td><%=dto.getMyid()%></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="text" name="mypw" value="<%=dto.getMypw()%>"/> </td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><%=dto.getMyname()%></td>
			</tr>
			<tr>
				<th>ADDR</th>
				<td><input type="text" name="myaddr" value="<%=dto.getMyaddr()%>"/> </td>
			</tr>
			<tr>
				<th>PHONE</th>
				<td><input type="text" name="myphone" value="<%=dto.getMyphone()%>"/> </td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td><input type="text" name="myemail" value="<%=dto.getMyemail()%>"/> </td>
			</tr>
		
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="" />
				</td>
			</tr>

		</table>
	</form>
</body>
</html>