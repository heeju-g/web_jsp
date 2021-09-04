<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBizImpl"%>
<%@page import="com.mvc.biz.MVCBiz"%>
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
	String command = request.getParameter("command");
	System.out.println("<" +command+">");
	MVCBiz biz = new MVCBizImpl();
	
	if(command.equals("list")){
		//1. 전달된 값이 있으면 받아주자.command제외하고
		
		
		//2. 필요하다면 db와 연결하자
		List<MVCDto> list = biz.selectList();
		
		//3. 보내줄 값이 있다면 request에 담자
		request.setAttribute("list",list);
		
		//4. 보내자
		pageContext.forward("mvclist.jsp");
		
	}else if(command.equals("insertform")){
		//1
		//2
		//3
		//4
		//sendRedirect: client가 request한걸 다시 insert에게 redirect 다시 요청. 주소창보면 forward랑 다른 부분 알 수 있음
		//첫 request에 담겼던 값을 redirect한 request에는 사용할 수 없다. 따라서 얘는 그냥 화면 전환 할 때만 씀. 인서트는 db에서 어떤 값을 가져올 필요 없어
		response.sendRedirect("mvcinsert.jsp");
	}else if(command.equals("insertres")){
		//1
		String writer =request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//2
		MVCDto dto = new MVCDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.insert(dto);
		//3
		//4
		if(res>0){
%>

		<script type="text/javascript">
			alert("글작성 성공");
			location.href="mvccontroller.jsp?command=list";
		</script>			
<%
		}else{
%>
		<script type="text/javascript">
			alert("글작성 실패");
			location.href="mvccontroller.jsp?command=insertform";
		</script>

<%
		}
	}else if(command.equals("detail")){
		//1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		//2.
		MVCDto dto = biz.selectOne(seq);
		//3. 
		//setAttribute 전달할 값 request에 담는 거
		// "" : 객체이름, ,뒤는 객체
		request.setAttribute("dto",dto);
		//4.
		//forward 위임 : mvcselect에게 forward했으므로 응답도 너가 해라
		//값 전달이 필요할 때 forward를 사용
		pageContext.forward("mvcselect.jsp");
		
	}else if(command.equals("updateform")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		//2
		MVCDto dto = biz.selectOne(seq);
		//3
		request.setAttribute("dto",dto);
		//4
		pageContext.forward("mvcupdate.jsp");
		
	}else if(command.equals("updateres")){
		//1
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//2
		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);
		//3
		//4
		if(res > 0){
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list",list);
			pageContext.forward("mvclist.jsp");
			
		}else{
			MVCDto updateDto = biz.selectOne(seq);
			request.setAttribute("dto",updateDto);
			pageContext.forward("mvcselect.jsp");
		}

	}else if(command.equals("delete")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		int res = biz.delete(seq);
		
		if(res > 0){
%>
		<script type="text/javascript">
		 alert("삭제성공");
		 location.href="mvccontroller.jsp?command=list";
		</script>
<%
		}else{
%>	
		<script type="text/javascript">
		 alert("삭제실패");
		 location.href="mvccontroller.jsp?command=detail&seq=<%=seq%>";
		</script>
<% 		
		}
	}
%>













		<h1>잘못왔다</h1>
</body>
</html>