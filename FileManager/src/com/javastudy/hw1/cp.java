package com.javastudy.hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class cp implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		File from,to;
		if(args[1].contains(":")){
			from=new File(args[1]);
		}else{
			from=new File(currDir.toString(),args[1]);		
		}

		if(args[2].contains(":")){
			to=new File(args[2]);
		}else{
			to=new File(currDir.toString(),args[2]);					
		}

		if (from.isDirectory()) {
			try {
				copydir(from, to);
			} catch (IOException e) {
				System.out.println(e);
			}

		}else{
			try {
				copy(from, to);
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	private static void copydir(File from,File to) throws IOException {
		File[] file=from.listFiles();
		for(File f:file){
			if(f.isDirectory()){
				File newto=new File(to, f.getName());
				copydir(f, newto);
			}else{
				if(!to.exists())
					to.mkdirs();
				File newto=new File(to, f.getName());
				copy(f, newto);
			}
		}
	}

	private static void copy(File from,File to) throws IOException {
		FileInputStream fi=new FileInputStream(from);
		FileOutputStream fout=new FileOutputStream(to);
		byte b[]=new byte[1024*1024*1];
		int len=fi.read(b);
		while(len!=-1){
			fout.write(b, 0, len);
			len=fi.read(b);
		}		
		fi.close();
		fout.close();
	}
}
