package com.mvc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;


@WebServlet(urlPatterns = {"/mylist","/myselect","/myupdateform","/myinsertform","/mydelete","/myinsertres","/myupdateres"})
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private MVCBiz biz = new MVCBizImpl();
	
	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getRequestURI();
		System.out.println("["+command+"]");
		
		if(command.endsWith("/mylist")) {
			doSelectList(request,response);
		}else if(command.endsWith("/myselect")) {
			doSelectOne(request, response);
		}else if(command.endsWith("/myinsertform")) {
			doInsert(request,response);
		}else if(command.endsWith("/myinsertres")) {
			doInsertRes(request,response);
		}else if(command.endsWith("/myupdateform")) {
			doUpdate(request,response);
		}else if(command.endsWith("/myupdateres")) {
			doUpdateRes(request,response);
		}else if(command.endsWith("/mydelete")) {
			doMyDelete(request,response);
		}
	}
	private void doSelectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch(request,response,"mvclist.jsp");
	}
	private void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch(request,response,"mvcselect.jsp");
	}
	public void doInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("mvcinsert.jsp");
	}
	public void doInsertRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCDto dto = new MVCDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		
		int res = biz.insert(dto);
		if(res>0) {
			jsResponse(response,"글 작성성공","./mylist");
		}else {
			jsResponse(response,"글 작성싪패","./myinsertform");
		}

	}
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch(request,response,"mvcupdate.jsp");
	}
	public void doUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = biz.update(dto);
		if(res>0) {
			//jsResponse(response,"글 수정성공","./mylist");
			jsResponse(response,"글 수정성공","./myselect?seq="+seq);
		}else {
			jsResponse(response,"글 수정실패","./myselect&seq="+seq);
		}
	}
	public void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int res = biz.delete(seq);
		if(res>0) {
			jsResponse(response,"글 삭제성공","./mylist");
			
		}else {
			jsResponse(response,"글 삭제실패","./mylist");
		}
	}
	
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequest(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequest(request, response);
	}
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		//forward or include
		//요청받은 걸 전달해주는 객체
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>"
							+ " alert('"+msg+"');"
							+ " location.href='"+url+"';"
							+" </script>";
		response.getWriter().print(responseText);
	}
}

