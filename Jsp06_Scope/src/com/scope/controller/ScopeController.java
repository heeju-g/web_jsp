package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ScopeController() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doPost(request,response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			//콘솔에 null이 뜬 건 다른 request객체이기 때문에(값이 담긴 index의 request가 아니라)
			String requestScope = (String)request.getAttribute("requestScope");
			HttpSession session = request.getSession();
			String sessionScope = (String)session.getAttribute("sessionScope");
			ServletContext application = getServletContext();
			String applicationScope = (String)application.getAttribute("applicationScope");
			
			System.out.println("request: " +requestScope);
			System.out.println("session: " +sessionScope);
			System.out.println("application: " +applicationScope);
			
			//PrintWriter out = response.getWriter();
			
			String responseText = "<h1>RESULT</h1>"
								+ " <table border=1>"	
								+ "  <tr> "
								+ "		<th>request</th> "
								+ "		<td>"+requestScope+"</td>"
								+ "	 </tr> "
								+ "  <tr> "
								+ "		<th>session</th> "
								+ " 	<td>"+sessionScope+"</td>"
								+ "  </tr> "
								+ "  <tr>"
								+ " 	<th>application</th> "
								+ " 	<td>"+applicationScope+"</td> "
								+ "  </tr>"
								+ " </table>";
			//out.print(responseText);
			//get post방식은 그냥 보내는 것이기 때문에 getParameter 로 값을 받은 것
			String requestVal = request.getParameter("requestVal");
			System.out.println("requestVal :" + requestVal);
			
			request.setAttribute("requestScope", "request forward value");
		
			//위에서 set 해놓고 포워드했기 때문에 index의 request객체가 그대로 유지되면서 null값이 아니라 제대로 나오는 것
			RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
			dispatch.forward(request,response);
			
	}

}
