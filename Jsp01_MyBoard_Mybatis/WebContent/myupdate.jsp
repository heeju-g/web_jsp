<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.biz.MyBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%  
	int myno = Integer.parseInt(request.getParameter("myno"));

	MyBoardBiz biz = new MyBoardBiz();
	MyBoardDto dto = biz.selectOne(myno);
%>
	<h1>UPDATE</h1>
	<form action="myupdateres.jsp" method="post">
		<input type="hidden" name="myno" value="<%=dto.getMyno()%>" />
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getMyname()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle" value="<%=dto.getMytitle()%>"></input></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="20" cols="40" name="mycontent"><%=dto.getMycontent()%></textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="mylist.jsp" />
				</td>
			</tr>

		</table>
	</form>
		
</body>
</html>