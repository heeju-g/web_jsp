
<%@page import="java.util.List"%>
<%@ page import="com.dept.dao.DeptDao" %>
<%@ page import="com.dept.dto.DeptDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//scriptlet : java code 영역
	DeptDao dao = new DeptDao();
	List<DeptDto> list = dao.selectList();
	
%>

	<h1>DEPT</h1>
	<table border=1>
		<tr>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
<%
//실행해서 f12보면 자바 관련 코드는 없음 html로 컴파일했기 때문
	for(int i = 0;i < list.size(); i++){			
%>
		<tr>
		 <%--  <%=값 %> : 자바값을 html로 넣어준단 소리 --%>
		 <!--꺽새 퍼센트 안에 =값이 들어간건,자바값을 html로 넣어준단 소리 -->
			<td><%=list.get(i).getDeptno() %></td>
			<td><%=list.get(i).getDname() %></td>
			<td><%=list.get(i).getLoc() %></td>
		</tr>
<%
	}
%>
		
		
	</table>

</body>
</html>