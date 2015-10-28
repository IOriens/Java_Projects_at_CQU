package com.javastudy.hw1;

import java.io.File;

//import com.javastudy.hw1.manager;

public class ls implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		File f=new File(currDir.toString());
		File[] list=f.listFiles();	
		Boolean flag1=true;
		Boolean flag2=true;
		
		//当前目录没有文件时提示没有文件
		if(list.length==0){
			System.out.println("No file in this directory!");
		}
		
		//输出所有文件夹名
		for(File li:list){
			if(flag1){
				System.out.println("---DIR---");
				flag1=false;
			}
			
			if(li.isDirectory())
				System.out.println("DIR:"+li.getName());
		}
		
		//输出所有文件名
		for(File li:list){
			if(flag2){
				System.out.println("---FILE---");
				flag2=false;
			}
			if(!li.isDirectory())				
				System.out.println(li.getName());			
		}	
	}	
}
