package com.javastudy.hw1;

import java.io.File;

public class mkdir implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		//此处使用windows系统
		if(args[1].contains(":\\")||args[1].charAt(0)=='/'){
			File file =new File(args[1]);
			
			if(!file.exists()&&!file.isDirectory()){
				System.out.println("//不存在");
				
				boolean status=file.mkdirs();
				if(status){
					System.out.println("//目录成功创建");
				}else{
					System.out.println("//目录创建失败");
				}
			}else{
				System.out.println("//目录存在");
			}
			
		}else{
			currDir.append("\\"+args[1]);
			File file =new File(currDir.toString());
			
			if(!file.exists()&&!file.isDirectory()){
				System.out.println("//不存在");
				boolean status=file.mkdirs();
				if(status){
					System.out.println("//目录成功创建");
				}else{
					System.out.println("//目录创建失败");
				}
			}else{
				System.out.println("//目录存在");
			}
			
		}
	}	
}
