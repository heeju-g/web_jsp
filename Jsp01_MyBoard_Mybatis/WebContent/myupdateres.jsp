<%@page import="com.my.biz.MyBoardBiz"%>
<%@page import="com.my.dto.MyBoardDto"%>
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
	//myno, mytitle, mycontent 받아오기
	int myno = Integer.parseInt(request.getParameter("myno"));
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	//MyBoardDto dto = new MyBoardDto(myno, null, mytitle, mycontent, null); 도 가능
	MyBoardDto dto = new MyBoardDto();
	dto.setMyno(myno);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	
	MyBoardBiz biz = new MyBoardBiz();
	int res =biz.update(dto);
	if(res > 0){
%>
	<script type="text/javascript">
		alert("수정완료");
		location.href="mylist.jsp";
	</script>

<%
	}else{
%>
	<script type="text/javascript">
	alert("수정실패");
	location.href="myselect.jsp?myno=<%=myno%>";
	</script>
<%
	}
%>




</body>
</html>