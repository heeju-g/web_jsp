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
	
	 <h1>NEW</h1>

	<!-- 입력받은 값을 myinsertres.jsp로 보내기 -->
	<form action="myinsertres.jsp" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="myname" /></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="20" cols="40" name="mycontent" /></textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
				<!-- submit해서 보내기 -->
					<input type="submit" value="업로드" />
					<input type="button" value="취소" onclick="mylist.jsp" />
				</td>
			</tr>
		</table>
	</form>
	 
	 
</body>
</html>