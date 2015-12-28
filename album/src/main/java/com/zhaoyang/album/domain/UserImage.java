package com.zhaoyang.album.domain;

public class UserImage {
	
	public int imageID;
	   
//	public int uID;

	public String imageName;
  
	public int albumID;
  
	public String imageDes;
	
	public String image;
  

	public int getImageId() {
		return imageID;
	}

	public void setImageId(int imageID) {
		this.imageID = imageID;
	}

	
//	public int getuID() {
//		return uID;
//	}
//
//	public void setuID(int uID) {
//		this.uID = uID;
//	}

	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	public String getImageDes() {
			return imageDes;
	}

	public void setImageDes(String imageDes) {
			this.imageDes = imageDes;
	}
		
	public int getAlbumId() {
				return albumID;
	}

	public void setAlbumId(int albumID) {
				this.albumID = albumID;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
