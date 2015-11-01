package com.javastudy.hw1;
//import com.javastudy.hw1.manager;

public class ManagerFactory {
	public Manager getManager(String type){
		if("mkdir".equals(type)){
			return new mkdir();
		}else if("ls".equals(type)){			
			return new ls();
		}else if("cd".equals(type)){
			return new cd();
		}
		
		else if("cat".equals(type)){
			return new cat();
		}
		
		else if("cp".equals(type)){
			return new cp();
		}
		
		else if("decoding".equals(type)){
			return new decoding();
		}
		
		else if("encoding".equals(type)){
			return new encoding();
		}
		else if("pwd".equals(type)){
			return new pwd();
		}
		
		else if("newzip".equals(type)){
			return new newzip();
		}
		
		else if("newunzip".equals(type)){
			return new newunzip();
		}
		else if("rm".equals(type)){
			return new rm();
		}
		else{
			return new Error();
		}
	}
}
