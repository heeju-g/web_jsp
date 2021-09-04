<%@page import="com.my.biz.MyBoardBiz"%>
<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="java.util.List"%>
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
<%
		MyBoardBiz biz = new MyBoardBiz();
		List<MyBoardDto> list = biz.selectList();
		
%>
<body>
	<h1>LIST</h1>
	
	
	<table border="1">
		<col width="50px"/>
		<col width="100px"/>
		<col width="500px"/>
		<col width="100px"/>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
		for(int i = 0; i < list.size();i++){
%>
		<tr>
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<td><a href="myselect.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>			
<%
         }
%>

		<tr>
			<td colspan="4" align="right">
				<input type="button" value="글작성" onclick="location.href='myinsert.jsp'"/>
			</td>
		</tr>
	</table>
</body>
</html>