package com.oriens.servlet3_1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="exampleServlet", urlPatterns="/test1")  
public class ExampleServlet extends HttpServlet {  
   
   private static final long serialVersionUID = 1L;  
   
   @Override  
   protected void doGet(HttpServletRequest request,  
         HttpServletResponse response) throws ServletException, IOException {  
      this.doPost(request, response);  
   }  
   
   @Override  
   protected void doPost(HttpServletRequest request,  
         HttpServletResponse response) throws ServletException, IOException {
	  String name=new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
	  int num=0;
	  num++;
	  String url="/outCome.jsp";
      request.setAttribute("username", name);
      request.setAttribute("usernum", num);      
      response.setCharacterEncoding("utf8");
      RequestDispatcher rd=getServletContext().getRequestDispatcher(url);
      rd.forward(request, response);
   }  
   
} 