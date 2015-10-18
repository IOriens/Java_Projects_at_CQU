package com.javastudy.hw1;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class cat implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		if (args[1].contains(":\\") || args[1].charAt(0) == '/') {
			FileReader in = null;
			BufferedReader read = null;
			String s = null;			
			//BufferedWriter writer = null;

			try {
				in = new FileReader(args[1]);
				read=new BufferedReader(in);
				while ((s = read.readLine()) != null) {
		                System.out.println(s);
				}
			} catch (IOException ex) {
				System.out.println("找不到指定文件！！");
			} finally {
				try {
					//writer.close();
					read.close();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}

		} else {
			StringBuffer tempdir=new StringBuffer(currDir);
//			System.out.println(tempdir.toString());
			tempdir.append("\\" + args[1]);
			
			FileReader in = null;
			BufferedReader read = null;
			String s = null;
			//BufferedWriter writer = null;

			try {
				in = new FileReader(tempdir.toString());				
				read=new BufferedReader(in);
				while ((s = read.readLine()) != null) {
		                System.out.println(s);
				}
			} catch (IOException ex) {
				System.out.println("找不到指定文件！！");
			} finally {
				try {
					//writer.close();
					read.close();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

}
