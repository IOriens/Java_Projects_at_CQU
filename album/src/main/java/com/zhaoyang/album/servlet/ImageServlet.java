package com.zhaoyang.album.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.zhaoyang.album.dao.dao;
import com.zhaoyang.album.domain.UserImage;

@WebServlet("/ImageServlet")
@MultipartConfig(location = "E:/webalbum", 
				maxFileSize = 8388608, fileSizeThreshold = 819200)
public class ImageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	private static final String imageDir = "E:/webalbum";

	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		String op = request.getParameter("op");
		try {
			switch (op) {
			case "deleteImage":
				DeleteImage(request,response);
				break;
			case "listImage":
				ListImage(request,response);
				break;
			case "image":
				ShowImage(request,response);
				break;
			case "editImage":
				updateImage(request,response);
				break;
			case "addImage":
				AddImage(request,response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void AddImage(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
			UserImage userImage = new UserImage();
			String imageName = request.getParameter("imageName");
			userImage.setImageName(imageName);
			String albumID = request.getParameter("albumId");			
			userImage.setAlbumId(Integer.parseInt(albumID));
			String imageDes = request.getParameter("imageDes");
			userImage.setImageDes(imageDes);;
			String fileName = albumID.toString() + System.nanoTime();
			userImage.setImage (fileName);	
			
			File dir=new File("E:/webalbum");
			if(!dir.exists()){
				dir.mkdirs();
			}
			try {
				dao.addImage(userImage);
				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
					if (part.getName().equals("imageFile")) {
						part.write(fileName);
						break;
					}
				}			
				response.sendRedirect("list.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void updateImage(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {		
		String imageID = request.getParameter("imageId");
		int imgID=Integer.parseInt(imageID);				
		String imageName = request.getParameter("imageName");
		String imageDes = request.getParameter("imageDes");
		try {
			dao.updateImage(imgID,imageName,imageDes);
			response.sendRedirect("list.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void DeleteImage(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String imageID = request.getParameter("imageID");
		UserImage userImage;
		try {
			userImage = dao.getImage(imageID);
			String image = userImage.getImage();
			File file = new File(imageDir, image);
			if(file.exists()){
				if (file.delete()){
					dao.decreseNumber(userImage.getAlbumId());
					dao.deleteImage(imageID);
				}
			}						
			response.sendRedirect("list.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ListImage(HttpServletRequest request,HttpServletResponse response) 
				throws Exception {
		String albumID = request.getParameter("albumID");
		int albumI =   Integer.parseInt(albumID);
		List<UserImage> userImages;
		try {
			userImages = dao.getAllImage(albumI);
			if(userImages.size()>0){
				request.setAttribute("userImages", userImages);			
				RequestDispatcher rd = request.getRequestDispatcher("viewImage.jsp");
				rd.forward(request, response);
			}else{
				request.setAttribute("albumID", albumI);
				RequestDispatcher rd = request.getRequestDispatcher("addImage.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ShowImage(HttpServletRequest request,HttpServletResponse response) 
			throws Exception {
		String imageId = request.getParameter("imageId");
		UserImage userImage;
		try {			
			userImage = dao.getImage(imageId);
			String image = userImage.getImage();
			File file = new File(imageDir,  image);
			InputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void copy(InputStream in, OutputStream out) throws IOException {
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = in.read(bytes)) > 0)
			out.write(bytes, 0, len);
		out.close();
		in.close();
	}

}

	


	

