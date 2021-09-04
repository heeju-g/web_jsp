package com.answer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;


@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		
		AnswerBiz biz = new AnswerBizImpl();
		
		if(command.equals("list")) {
			List<AnswerDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch(request,response,"boardlist.jsp");
			
		}else if(command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request, response, "boardselect.jsp");
			
		}else if(command.equals("insert")) {
			response.sendRedirect("insert.jsp");
		}else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			AnswerDto dto = new AnswerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.boardInsert(dto);
			if(res>0) {
				jsResponse(response, "글 작성 성공", "answer.do?command=list");
			}else {
				jsResponse(response, "글 작성 실패", "answer.do?command=insert");
			}
		}else if(command.equals("update")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request,response,"boardupdate.jsp");
		}else if(command.equals("updateres")){
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			
			AnswerDto dto = new AnswerDto();
			dto.setBoardno(boardno);
			dto.setTitle(title);
			dto.setContent(content);
			
			
			int res = biz.boardUpdate(dto);
			if(res > 0) {
				jsResponse(response, "글 수정 성공", "answer.do?command=list");
			}else {
				jsResponse(response, "글 수정 실패", "answer.do?command=update&boardno="+boardno);
			}
		}else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			int res = biz.boardDelete(boardno);
			
			if( res> 0) {
				jsResponse(response, "글 삭제 성공", "answer.do?command=list");
			}else {
				jsResponse(response, "글 삭제 실패", "answer.do?command=detail&boardno="+boardno);
			}
		}else if(command.equals("answerform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto dto = biz.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch(request,response,"boardanswer.jsp");
		}else if(command.equals("answerproc")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			AnswerDto dto= new AnswerDto(parentboardno,0,0,0,title,content,writer,null);
			
			int res= biz.answerProc(dto);
			if(res >0) {
				jsResponse(response, "답변성공", "answer.do?command=list");
			}else {
				jsResponse(response, "답변실패", "answer.do?command=answerform&boardno="+parentboardno);
			}
			
		}
		
		
		
		response.getWriter().append("<h1 style='color: red;'>잘못왔다</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
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
