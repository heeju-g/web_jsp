<%@page import="java.util.List"%>
<%@page import="com.login.dto.LoginDto"%>
<%@page import="com.login.dao.LoginDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String command = request.getParameter("command");
	System.out.println("[" + command + "]");

	LoginDao dao = new LoginDao();
	if (command.equals("login")) {
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");

		LoginDto dto = dao.login(myid, mypw);

		if (dto != null) {
			//session scope에 담기				//browser의 정보들을 server가 가지고 있는데 가지고 있는 객체가 session
			session.setAttribute("dto", dto);
			//특정 시간동안 활동이 없으면 세션 만료
			session.setMaxInactiveInterval(10 * 60);
			if (dto.getMyrole().equals("ADMIN")) {
					response.sendRedirect("adminmain.jsp"); //request객체를 sendRedirect하면 응답 중 다시 요청하기 때문에 새request객체를 응답하기 때문에 값을 사용 못했지만,
			} else if(dto.getMyrole().equals("USER")) {		// session은 만료되기 전까지 사용가능하기 때문에 sendRidirect해서 값을 전달 시킬 수 있다!!!!
					response.sendRedirect("usermain.jsp");
			}
		} else {
			request.setAttribute("msg","로그인실패");
			
		}
	}else if(command.equals("logout")){
		// session 만료
		session.invalidate();
		response.sendRedirect("index.html");
	}else if(command.equals("userlistall")){
		//1	
		//2
		List<LoginDto> list = dao.selectAllList();
		//3
		request.setAttribute("list", list );
		//4
		pageContext.forward("userlistall.jsp");
	}else if(command.equals("userlistenabled")){
		//1여기서는 왜 받아오는 게 없는 걸까 조건 enabled=y라는 건 있는데	
		//2
		List<LoginDto> list = dao.selectEnabledList();
		//3
		request.setAttribute("list",list);
		//4
		pageContext.forward("userlistenabled.jsp");
	}else if(command.equals("updateroleform")){
		//1
		int myno = Integer.parseInt(request.getParameter("myno"));
		//2
		LoginDto dto = dao.selectOne(myno);
		//3
		request.setAttribute("dto",dto);
		//4
		pageContext.forward("updateroleform.jsp");
	} else if(command.equals("updaterole")){
		//1
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		int res = dao.updateRole(myno,myrole);
		if(res > 0){
%>
		<script type="text/javascript">
			alert("회원등급 조정 성공");
			location.href="logincontroller.jsp?command=userlistenabled";
		</script>
<%
		}else{
%>
		<script type="text/javascript">
			alert("회원등급 조정 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>

<%			
		}
	}else if(command.equals("registform")){
		response.sendRedirect("regist.jsp");
	}else if(command.equals("idchk")){
		String myid = request.getParameter("myid");
		LoginDto dto = dao.idCheck(myid);
		
		boolean idnotused = true;
		if(dto != null){
			idnotused = false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	}else if(command.equals("insertuser")){
		//1
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		LoginDto dto = new LoginDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		int res = dao.insert(dto);
		if(res > 0){
%>
		<script type="text/javascript">
			alert("회원가입 성공");
			location.href="logincontroller.jsp?command=login";
		</script>
<%
		}else{
%>
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href="loginconrtoller.jsp?command=registform";
		</script>
<% 
		}		
	
	}else if(command.equals("myinfo")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		LoginDto dto = dao.selectOne(myno);
		
		request.setAttribute("dto",dto);
	
		pageContext.forward("myinfo.jsp");

	}else if(command.equals("usermain")){		
		response.sendRedirect("usermain.jsp");
		
	}else if(command.equals("updateinfo")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		LoginDto dto = dao.selectOne(myno);
	
		request.setAttribute("dto",dto);
	
		pageContext.forward("updateinfo.jsp");
		
	}else if(command.equals("updateinfores")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		String mypw = request.getParameter("mypw");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		LoginDto dto = new LoginDto();
		dto.setMypw(mypw);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = dao.update(dto);
		if(res>0){
%>
		<script type="text/javascript">
			alert("정보수정 성공");
			location.href="logincontroller.jsp?command=myinfo";
		</script>
<%
		}else{
			
%>	
		<script type="text/javascript">
			alert("정보수정 실패");
			location.href="logincontroller.jsp?command=myinfo";
		</script>
<%
		}
	}
%>		


	<h1 style="color : red;" >잘못왔어</h1>
	<p> 
	1. command확인
	</p>
</body>
</html>