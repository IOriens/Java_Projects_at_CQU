package com.javastudy.hw1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class newzip implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		System.out.println("文件压缩");
		ZipOutputStream out = null;
		//FileInputStream in = null;
		String blank = "";
		String path = "";
		try{
			if (args[2].contains(":\\")) {

				// Create the ZIP file
				out = new ZipOutputStream(new FileOutputStream(args[2]));

			}
			else {
				StringBuffer tempdir = new StringBuffer(currDir);
				tempdir.append("\\" + args[2]);
				try {
					out = new ZipOutputStream(new FileOutputStream(tempdir.toString()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			if (args[1].contains(":\\")) {
				path = args[1];
			}
			else {
				StringBuffer tempdir = new StringBuffer(currDir);
				tempdir.append("\\" + args[1]);
				path = tempdir.toString();
				
			}

			File file = new File(path);
			if (file .exists()) {
				if (file.isFile()) {
					compressFile(file, out,blank);
				} else {
					compressFolder(file, out,blank);
				}
			} else {
				System.out.println("要压缩的文件不存在！");
			}
			// Complete the ZIP file
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void compressFolder(File file, ZipOutputStream out,String blank) {   
		File[] files = file.listFiles();  
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				compressFile(files[i], out,blank+ file.getName() + File.separator);
			} else{
				compressFolder(files[i], out,blank + file.getName() + File.separator);
			}
		}  
	}  
	void compressFile(File file, ZipOutputStream out,String blank) { 
		try {  
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry=new ZipEntry(blank+file.getName());
			out.putNextEntry(entry);

			int count;  
			byte data[] = new byte[1024];  
			while ((count = bis.read(data, 0, 1024)) != -1) {  
				out.write(data, 0, count); 
			}  
			bis.close();  
		} catch (Exception e) {  
			throw new RuntimeException(e);  
		}  
	}  

}


