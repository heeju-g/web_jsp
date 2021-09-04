package com.mvc.controller;

import java.io.IOException;
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


@WebServlet("/MVCController")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public MVCController() {
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("{"+command+"}");
		
		MVCBiz biz = new MVCBizImpl();
		 try {
		if(command.equals("list")) {
			//1
			//2
			List<MVCDto> list = biz.selectList();
			//3 보내줄 값이 있다면 request에 담자(공지사항 조회할 때 오류날 부분)
			request.setAttribute("list", list);
			//4
			dispatch(request,response,"mvclist.jsp");
		
		}else if(command.equals("insertform")) {
			
			
			//4
			response.sendRedirect("mvcinsert.jsp");
			
		}else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCDto dto = new MVCDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			
			if(res >0) {			
				jsResponse(response,"글 작성성공","mvc.do?command=list");
			}else {
				jsResponse(response,"글 작성싪패","mvc.do?command=insertform");
			}
		}else if(command.equals("select")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto",dto);
			
			dispatch(request,response,"mvcselect.jsp");
		}else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch(request, response, "mvcupdate.jsp");
		}else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCDto dto = new MVCDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(seq);
			int res = biz.update(dto);
			
			if(res > 0) {
				jsResponse(response,"글 수정성공","mvc.do?command=list");
			}else {
				jsResponse(response,"글 수정싪패","mvc.do?command=updateform&seq="+seq);
			}
		}else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = biz.delete(seq);
			
			if(res>0) {
				jsResponse(response,"글 삭제성공","mvc.do?command=list");
			}else {
				jsResponse(response,"글 삭제싪패","mvc.do?command=select&seq="+seq);
			}
		}
		
		
		 
		 
		}catch(Exception e) {
			 request.setAttribute("error", e);
			 dispatch(request, response, "error.jsp");
	    }
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
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

