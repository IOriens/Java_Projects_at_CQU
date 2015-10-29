package com.javastudy.hw1;

import java.io.*;


public class decoding implements Manager {

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		FileInputStream g = null;
		FileInputStream k1 = null;
		String msg1 = "";
		String decmsg = "";
		String key = "";
		int i = 0;
		if (args[1].contains(":\\")) {
			try {
				g = new FileInputStream(args[1]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StringBuffer tempdir = new StringBuffer(currDir);
			tempdir.append("\\" + args[1]);
			try {
				g = new FileInputStream(tempdir.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		do {
			try {
				i = g.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i != -1) {
				msg1 = msg1 + (char) i;
				System.out.print((char) i);
			}

		} while (i != -1);
		try {
			g.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("");
		System.out.println("plese input  key");
		String key1 = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			key1 = br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (args[2].contains(":\\")) {
			try {
				k1 = new FileInputStream(args[2]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StringBuffer tempdir = new StringBuffer(currDir);
			tempdir.append("\\" + args[2]);
			try {
				k1 = new FileInputStream(tempdir.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		do {
			try {
				i = k1.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i != -1)
				key = key + (char) i;
		} while (i != -1);
		if (key.length() == key1.length()) {
			int l = 0;
			for (int n = 0; n < key.length(); n++) {
				if (key.charAt(n) == key1.charAt(n)) {
					if (n == key.length() - 1)
						l = 1;
					continue;
				} else
					System.out.println("error key");
			}
			if (l == 1) {
				int q = 0;
				for (int z = 0; z < msg1.length(); z++) {
					decmsg = decmsg + (char) (msg1.charAt(z) ^ key1.charAt(q));
					q++;
					if (q == key.length())
						q = 0;
				}
				System.out.println(decmsg);
			}
		} else
			System.out.println("error key");
	}

}