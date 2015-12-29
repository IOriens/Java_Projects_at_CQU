package com.zhaoyang.album.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zhaoyang.album.domain.UserAlbum;
import com.zhaoyang.album.domain.UserImage;
import com.zhaoyang.album.domain.UserInfo;

public class dao {
	//添加
	public static Boolean addImage(final UserImage userImage) throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn
						.prepareStatement("insert into image values(?,?,?,?,?)");
				ps.setInt(1, userImage.imageID);
				ps.setString(2, userImage.imageName);
				ps.setString(3, userImage.imageDes);
				ps.setInt(4, userImage.albumID);
				ps.setString(5, userImage.image);
				dao.addNumber(userImage.albumID);
				return ps.execute();


			}
		};
		return t.execute();
	}

	public static Boolean addNumber(final int albumID) throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override 
			protected Boolean doTransaction(Connection conn) throws Exception {
				UserAlbum userAlbum = dao.getAlbum(albumID);
				PreparedStatement ps = conn
						.prepareStatement("update album set number=? where albumID=?");
				ps.setInt(1, (userAlbum.number+1));
				ps.setInt(2, (userAlbum.albumID));				
				return ps.execute();

			}
		};
		return t.execute();
	}
	
	public static Boolean decreseNumber(final int albumID) throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override 
			protected Boolean doTransaction(Connection conn) throws Exception {
				UserAlbum userAlbum = dao.getAlbum(albumID);
				PreparedStatement ps = conn
						.prepareStatement("update album set number=? where albumID=?");
				ps.setInt(1, (userAlbum.number-1));
				ps.setInt(2, (userAlbum.albumID));				
				return ps.execute();

			}
		};
		return t.execute();
	}
	//获取
	public static UserImage getImage(final String imageID) throws Exception {
		JDBCTemplate<UserImage> q = new Query<UserImage>() {
			@Override
			protected UserImage doQuery(Connection conn) throws Exception {
				PreparedStatement ps = conn
						.prepareStatement("select * from image where imageID=?");
				ps.setString(1, imageID);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				UserImage userImage = null;
				if (rs.next()) {
					userImage = new UserImage();
					userImage.imageID = rs.getInt("imageID");
					userImage.imageName = rs.getString("imagename");
					userImage.imageDes = rs.getString("imageDes");
					userImage.albumID = rs.getInt("albumID");
					userImage.image = rs.getString("image");
				}
				return userImage;
			}
		};
		return q.execute();
	}

	// 获取全部
	public static List<UserImage> getAllImage(int albumID) throws Exception {
		JDBCTemplate<List<UserImage>> q = new Query<List<UserImage>>() {

			@Override
			protected List<UserImage> doQuery(Connection conn) throws Exception {
				List<UserImage> userImages = new ArrayList<UserImage>();
				PreparedStatement ps = conn
						.prepareStatement("select * from image where albumID=?");
				ps.setInt(1, albumID);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					UserImage userImage = new UserImage();
					userImage.imageID = rs.getInt("imageID");
					userImage.imageName = rs.getString("imagename");
					userImage.imageDes = rs.getString("imageDes");
					userImage.albumID = rs.getInt("albumID");
					userImage.image = rs.getString("image");
					userImages.add(userImage);
				}
				return userImages;
			}
		};
		return q.execute();
	}

	// 修改
	public static Boolean updateImage(final int imageID,final String imageName,final String imageDes)
			throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception { 
				PreparedStatement ps = conn
						.prepareStatement("update image set imageName=?,imageDes=? where imageID=?");
				ps.setString(1, imageName);
				ps.setString(2, imageDes);
				//ps.setString(3, userImage.imageDes);
				ps.setInt(3, imageID);
				//ps.setInt(5, userImage.uID);
				return ps.execute();
			}
		};
		return t.execute();
	}

	// 删除
	public static Boolean deleteImage(final String imageID) throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn
						.prepareStatement("delete from image where imageID=?");
				ps.setString(1, imageID);
				return ps.execute();
			}
		};
		return t.execute();
	}

	//添加相册
	public  Boolean addAlbum(final UserAlbum album) throws Exception {
		final int userID = album.getuID();
		final String albumName = album.getAlbumName();
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("insert into album(uID,albumName,number) values (?,?,?);");
				ps.setInt(1, userID);
				ps.setString(2, albumName);
				ps.setInt(3, 0);
				return ps.execute();
			}
		};
		return t.execute();
	}

	public static List<UserAlbum> getAllAlbum(final int uID) throws Exception {
		JDBCTemplate<List<UserAlbum>> t = new Query<List<UserAlbum>>() {
			@Override
			protected List<UserAlbum> doQuery(Connection conn) throws Exception {
				List<UserAlbum> albums = new ArrayList<UserAlbum>();
				PreparedStatement ps = conn.prepareStatement("select * from album where uID=? ");
				ps.setInt(1, uID);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					UserAlbum album = new UserAlbum();
					album.setAlbumIDId(rs.getInt("albumID"));
					album.setuID (rs.getInt("uID"));
					album.setAlbumName(rs.getString("albumName"));				
					album.setNumber(rs.getInt("number"));					
					albums.add(album);//重点理解
				}
				return albums;
			}
		};
		return t.execute();
	}

	public static UserAlbum getAlbum(final int albumID) throws Exception {
		JDBCTemplate<UserAlbum> q = new Query<UserAlbum>() {
			@Override
			protected UserAlbum doQuery(Connection conn) throws Exception {
				PreparedStatement ps = conn
						.prepareStatement("select * from album where albumID=?");
				ps.setInt(1, albumID);				
				ps.execute();
				ResultSet rs = ps.getResultSet();
				UserAlbum userAlbum = null;
				if (rs.next()) {
					userAlbum = new UserAlbum();
					userAlbum.setAlbumIDId(rs.getInt("albumID"));
					userAlbum.setAlbumName(rs.getString("albumName"));
					userAlbum.setNumber(rs.getInt("number"));
					userAlbum.setuID(rs.getInt("uID")); 
				}
				return userAlbum;
			}
		};
		return q.execute();
	}

	public static Boolean updateAlbum(final int albumID,final String newAlbumName)
			throws Exception {		
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("update album set albumName=? where albumID=?");
				ps.setString(1, newAlbumName);				
				ps.setInt(2,albumID);
				return ps.execute();
			}
		};
		return t.execute();
	}

	public static Boolean deleteAlbum(final int albumID) throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("delete from album where AlbumID=?");
				ps.setInt(1, albumID);
				return ps.execute();
			}
		};
		return t.execute();
	}

	public  Boolean registerUser(UserInfo user) throws Exception {
		//		final String userName = user.getUserName();
		//		final String Userpswd = user.getUserPswd();
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("insert into user (userName,userPswd,sex,hobby) values (?,?,?,?);");
				ps.setString(1, user.getUserName());
				ps.setString(2, user.getUserPswd());
				ps.setString(3, user.getSex());
				ps.setString(4, user.getHobby());																			

				//这里可以优化------------------------------------------
				return ps.execute();
			}
		};
		return t.execute();
	}



	public  boolean login(final String userName, final String userPswd)
			throws Exception {
		JDBCTemplate<Boolean> t = new Transaction<Boolean>() {
			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("select * from user where userName=? and userPswd=? and sex=? and hobby=?");
				ps.setString(1, userName);
				ps.setString(2, userPswd); 
				//这里可以优化------------------------------------------
				return ps.execute();
			}
		};
		return t.execute();
	}

	public static UserInfo getuserInfo(final String userName, final String userPswd) throws Exception {
		JDBCTemplate<UserInfo>  t= new Query<UserInfo>() {
			@Override
			protected UserInfo doQuery(Connection conn) throws Exception {
				PreparedStatement ps = conn.prepareStatement("select * from user where userName=? and userPswd=?");
				ps.setString(1, userName);
				ps.setString(2, userPswd);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				UserInfo info = null;			
				if (rs.next()){
					info = new UserInfo();
					info.setuID(rs.getInt("uID"));		
					info.setSex(rs.getString("sex"));
					info.setHobby(rs.getString("hobby"));
					info.setUserName(rs.getString("userName"));
				}
				return info;
			}
		};
		return t.execute();
	}
}
