package com.javastudy.hw1;

public class cd implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		if (args[1].contains(":\\") || args[1].charAt(0) == '/') {
			currDir=new StringBuffer(args[1]);
		}else{
			currDir.append("\\"+args[1]);
		}
	}

}
