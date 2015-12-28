package com.zhaoyang.album.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zhaoyang.album.dao.dao;
import com.zhaoyang.album.domain.UserInfo;

@WebServlet("/UserServlet")
@MultipartConfig(location = "E:/webalbum", 
maxFileSize = 8388608, fileSizeThreshold = 819200)
public class UserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		String op = request.getParameter("op");
		try {
			switch (op) {
			case "login":
				Login(request,response);
				break;
			case "register":
				register(request,response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void register(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String userName=request.getParameter("userName");
		String userPswd=request.getParameter("userPswd");
		String sex=request.getParameter("sex");
		String hobby=request.getParameter("hobby");
		UserInfo user=new UserInfo();
		user.setUserName(userName);
		user.setUserPswd(userPswd);
		user.setSex(sex);
		user.setHobby(hobby);	
		dao pd=new dao();
		try {
			pd.registerUser(user);		
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Login(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String userName=request.getParameter("userName");
		String userPswd = request.getParameter("userPswd");
		try {	
			UserInfo info = dao.getuserInfo(userName, userPswd);		
			HttpSession session = request.getSession(true);
			session.setAttribute("uID", info.getuID());			
			session.setAttribute("sex", info.getSex());
			session.setAttribute("hobby",info.getHobby());	
			session.setAttribute("userName", info.getUserName());				
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}






