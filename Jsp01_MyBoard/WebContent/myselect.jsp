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

<%
	//string이니까 형변환
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyBoardBiz biz = new MyBoardBiz();
	MyBoardDto dto = biz.selectOne(myno);
%>
<body>

	<h1>SELECTED</h1>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getMytitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="20" cols="40" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
																<!-- k=v형태 -->
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'"/>
				<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>'"/>
				<input type="button" value="리스트" onclick="location.href='mylist.jsp'"/>
			</td>
		</tr>
	</table>

</body>
</html>