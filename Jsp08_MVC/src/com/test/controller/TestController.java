package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.biz.TestBiz;
import com.test.biz.TestBizImpl;
import com.test.dto.TestDto;

@WebServlet("/TestController")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public TestController() {
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		TestBiz biz = new TestBizImpl();
		
		if(command.equals("list")) {
			
			
			List<TestDto> list = biz.selectList();
			
			request.setAttribute("list", list);
			dispatch(request,response,"testlist.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
