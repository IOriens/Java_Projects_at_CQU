package com.javastudy.hw1;

import java.io.File;

public class rm implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		if(args[1].contains(":")){
			File f=new File(args[1]);
			if(f.exists()){
				f.delete();
				System.out.println("File deletion finished");
			}else{
				System.out.println("File deletion finished");
			}
		}else {
			File f=new File(currDir.toString(),args[1]);
			System.out.println(f.toString()+"=====");
			if(f.exists()){
				f.delete();
				System.out.println("The file has been deleted");
			}else{
				System.out.println("File or FileDir doesn't exist!");
			}
		}
	}
}
