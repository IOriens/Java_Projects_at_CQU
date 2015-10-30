package com.javastudy.hw1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class newzip implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		System.out.println("文件压缩");

		byte[] buf = new byte[1024];
		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(args[2]));
			// Compress the files
			FileInputStream in = new FileInputStream(args[1]);
			// Add ZIP entry to output stream.
			out.putNextEntry(new ZipEntry(args[1]));
			// Transfer bytes from the file to the ZIP file
			int len;
			while ( (len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			// Complete the entry
			out.closeEntry();
			in.close();

			// Complete the ZIP file
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}


