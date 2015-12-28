package com.zhaoyang.album.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhaoyang.album.dao.dao;
import com.zhaoyang.album.domain.UserAlbum;

@WebServlet("/AlbumServlet")
@MultipartConfig(location = "E:/webalbum", 
maxFileSize = 8388608, fileSizeThreshold = 819200)
public class AlbumServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public AlbumServlet() {
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

			case "deleteAlbum":
				DeleteAlbum(request,response);
				break;

			case "list":
				ListAblum(request,response);
				break;

			case "creatAlbum":
				CreateAlbum(request,response);
				break;

			case "editAlbum":
				updateAlbum(request,response);
				break;

			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}



	private void ListAblum(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String uID = request.getParameter("uid");		
		int uId =  Integer.parseInt(uID);
		List<UserAlbum> albums;
		try {
			albums = dao.getAllAlbum(uId);
			if(albums.size()>0){
				request.setAttribute("albums22", albums);					
				RequestDispatcher rd = request.getRequestDispatcher("viewAlbum.jsp");
				rd.forward(request, response);
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("addAlbum.jsp?uid="+uId);
				rd.forward(request, response);
			}
		
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void CreateAlbum(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		UserAlbum album = new UserAlbum();
		String albumName=request.getParameter("albumName");
		String uID=request.getParameter("uid");
		album.setAlbumName(albumName);
		album.setuID(Integer.parseInt(uID));
		dao pd=new dao();
		try {
			if(albumName.length()>0)
				pd.addAlbum(album);
			response.sendRedirect("list.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void DeleteAlbum(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String albumID = request.getParameter("albumID");
		int albumId = Integer.parseInt(albumID);
		
		try {
			System.out.println("=================albumservelet========================albumid:"+albumId);
			dao.deleteAlbum(albumId);
			response.sendRedirect("list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateAlbum(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {				
		String albumID = request.getParameter("albumID");
		String newAlbumName = request.getParameter("newAlbumName");	
		
		try {
			dao.updateAlbum(Integer.parseInt(albumID),newAlbumName);
			RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






