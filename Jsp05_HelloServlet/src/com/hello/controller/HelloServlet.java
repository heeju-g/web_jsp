package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이 매핑과 xml파일의 서블릿매핑이 같으면 충돌나기 때매 안되고, 경로 앞에 / 꼭 붙이기
@WebServlet("/controller.do")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String initParam;
	
    public HelloServlet() {
    	System.out.println("HelloServlet생성");
       
      
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("HelloServlet init");
    	
    	initParam = config.getInitParameter("actor");
    	String contextParam = config.getServletContext().getInitParameter("singer");
    	
    	System.out.println("initParam :" + initParam);
    	System.out.println("contextParam :" + contextParam);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("get 방식으로 들어옴");
		
		//클라이언트가 서버에게.request객체
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		//서버가 클라이언트에게.response객체
		//서버가 response객체에게 html형태의 문자열을 write(서버 기준으로 나가니까 out)
		PrintWriter out = response.getWriter();
		out.print("<h1 style='color:red;'>Hello Servlet</h1>");
		out.print("<h2> 계층구조/LifeCycle/url-mapping</h2>");
		out.print("<a href='home.html'>home...</a>");
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("post방식으로 들어옴");
		
		String command = request.getParameter("command");
		System.out.println("{"+command+"}");
		
		String result = "<h1 style='color:blue;'>Hello Servlet</h1>"
						+ " <h2>계층구조/LifeCycle/url=mapping</h2>"
						+ " <a href='home.html'>home,,,</a>";
		
		response.getWriter().append(result);
						
		
	
	}
	@Override
	public void destroy() {
		System.out.println("HelloServlet destroy");
	}
	
	
	
	
	
	
	

}
