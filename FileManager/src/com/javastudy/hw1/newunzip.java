package com.javastudy.hw1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class newunzip implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		System.out.println("文件解压缩");
		try {
			// Open the ZIP file
			@SuppressWarnings("resource")
			ZipFile zf = new ZipFile(args[1]);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
				// Get the entry name
				ZipEntry entry = ( (ZipEntry) entries.nextElement());
				String zipEntryName = entry.getName();
				java.io.InputStream in = zf.getInputStream(entry);
				// System.out.println(zipEntryName);
				FileOutputStream out = new FileOutputStream(args[1] + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ( (len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				// Close the file and stream
				in.close();
				out.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
