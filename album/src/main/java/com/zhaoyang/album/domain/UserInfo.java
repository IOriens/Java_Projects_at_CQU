package com.zhaoyang.album.domain;

public class UserInfo {
    	//uid
	    private int uID;
	    //username
		private String userName;
		//passwprd
		private String userPswd;
		//sex
		private String sex;
		//hobby
		private String hobby;
		
		public int getuID() {
			return uID;
		}

		public void setuID(int uID) {
			this.uID = uID;
		}
		
		public void setUserName(String userName){
			this.userName = userName;
		}
		
		public String getUserName(){
			return userName;
		}
		
		public void setUserPswd(String userPswd){
			this.userPswd = userPswd;
		}
		
		public String getUserPswd(){
			return userPswd;
		}
		
		public String getSex(){
			return sex;
		}
		
		public void setSex(String sex){
			this.sex = sex;
		}
		
		public String getHobby(){
			return hobby;
		}
		
		public void setHobby(String hobby){
			this.hobby = hobby;
		}
		
//		public String getHeadURL(){
//			return headURL;
//		}
//		
//		public void setHeadURL(String headURL){
//			this.headURL = headURL;
//		}
   
}
