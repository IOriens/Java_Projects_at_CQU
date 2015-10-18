package com.javastudy.hw1;

import java.io.*;
//import java.util.Scanner;

public class encoding implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		FileInputStream f = null;
		if (args[1].contains(":\\")) {
			try {
				f = new FileInputStream(args[1]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StringBuffer tempdir = new StringBuffer(currDir);
			tempdir.append("\\" + args[1]);
			try {
				f = new FileInputStream(tempdir.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int q = 0;
		int i = 0;
		String encmsg = "";
		String msg = "";
		do {
			try {
				i = f.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i != -1) {
				msg = msg + (char) i;
				System.out.print((char) i);
			}
		} while (i != -1);
		System.out.println("");
		System.out.println("plese input  key");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputStream k = null;
		if (args[2].contains(":\\")) {
			try {
				k = new FileOutputStream(args[2]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StringBuffer tempdir2 = new StringBuffer(currDir);
			tempdir2.append("\\" + args[2]);
			try {
				k = new FileOutputStream(tempdir2.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int p = 0; p < key.length(); p++)
			try {
				k.write(key.charAt(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			k.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int t = 0; t < msg.length(); t++) {
			encmsg = encmsg + (char) (msg.charAt(t) ^ key.charAt(q));
			q++;
			if (q == key.length())
				q = 0;
		}
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileOutputStream fout = null;
		if (args[3].contains(":\\")) {
			try {
				fout = new FileOutputStream(args[3]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StringBuffer tempdir = new StringBuffer(currDir);
			tempdir.append("\\" + args[3]);
			try {
				fout = new FileOutputStream(tempdir.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int p = 0; p < encmsg.length(); p++)
			try {
				fout.write(encmsg.charAt(p));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
