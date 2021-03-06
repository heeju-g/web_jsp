<%@page import="com.mvc.dto.MVCDto"%>
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
// 형변환 하는 이유 : setAttribute에서 object객체로 담아놨기 때문에. 다시 자식 타입으로 형변환 해야함(object는 가장 큰 타입이기 때문에 명시적 형변환)
	MVCDto dto = (MVCDto)request.getAttribute("dto");
%>

	<h1>DETAIL</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='mvccontroller.jsp?command=updateform&seq<%=dto.getSeq()%>'"/>
				<input type="button" value="삭제" onclick="location.href='mvccontroller.jsp?command=delete&seq=<%=dto.getSeq()%>'"/>
				<input type="button" value="목록" onclick=""/>
			 </td>
		</tr>
	</table>
	
</body>
</html>