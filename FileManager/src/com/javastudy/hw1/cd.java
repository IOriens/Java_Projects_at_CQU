package com.javastudy.hw1;

import java.io.File;

public class cd implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {

		if("..".equals(args[1])){
			
			/*输入cd ..进入上层目录*/						
			String[] tmp=currDir.toString().split("\\\\");//使用正则表达式来分割地址
			StringBuffer tmp2=new StringBuffer(tmp[0]);			
			for(int i=1;i<tmp.length-1;i++){					
				tmp2.append("\\");
				tmp2.append(tmp[i]);				
			}//剔除最后一层目录
			
			//重点：清除传入的StringBuffer的内容，并修改为替代内容
			currDir.replace(0, currDir.length(), tmp2.toString());
			
		}else if(args[1].contains(":\\") || args[1].charAt(0) == '/') {
			
			/*包含绝对路径的情况*/
			File tempFileDir=new File(args[1]);
			//重点：需要判断路径是否存在
			if(tempFileDir.isDirectory())
				currDir=new StringBuffer(args[1]);
			else
				System.out.println("该目录不存在");
			
		}else{
			
			/*使用相对路径的情况*/
			StringBuffer tempForCheck=new StringBuffer(currDir);
			tempForCheck.append("\\"+args[1]);
			File tempFileDir=new File(tempForCheck.toString());
			if(tempFileDir.isDirectory()){
				currDir.append("\\"+args[1]);
			}else{
				System.out.println("该目录不存在");
			}
						
		}
	}

}
