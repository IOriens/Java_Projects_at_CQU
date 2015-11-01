package com.javastudy.hw1;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class newunzip implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		// TODO Auto-generated method stub
		System.out.println("文件解压缩");
		String targetPath = currDir.toString();
		StringBuffer zipFilePath = new StringBuffer();
		try {
			// Open the ZIP file
			
			if (args[1].contains(":\\")) {
				// Compress the files
				zipFilePath.append(args[1]);
			}
			
			else
			{
				zipFilePath.append(currDir+"\\" + args[1]);
			}
			
			if(args.length == 3)
			{
				targetPath = args[2];

			}

			 BufferedOutputStream os = null;
			 InputStream is = null;
			 ZipFile zipFile = null;
			         try {
			             zipFile = new ZipFile(zipFilePath.toString());
			             String directoryPath = "";
			             if (null == targetPath || "".equals(targetPath)) {
			                 directoryPath = zipFilePath.substring(0, zipFilePath
			                         .lastIndexOf("."));
			             } else {
			                 directoryPath = targetPath;
			             }
			             Enumeration<? extends ZipEntry> entryEnum = zipFile.entries();
			             if (null != entryEnum) {
			                 ZipEntry zipEntry = null;
			                 while (entryEnum.hasMoreElements()) {
			                     zipEntry = (ZipEntry) entryEnum.nextElement();
			                     if (zipEntry.isDirectory()) {
			                         directoryPath = directoryPath + File.separator
			                                 + zipEntry.getName();
			                         System.out.println(directoryPath);
			                         continue;
			                     }
			                     if (zipEntry.getSize() > 0) {
			                         // 文件
			                         File targetFile = newunzip.buildFile(directoryPath
			                                 + File.separator + zipEntry.getName(), false);
			                         os = new BufferedOutputStream(new FileOutputStream(
			                                 targetFile));
			                         is = zipFile.getInputStream(zipEntry);
			                         byte[] buffer = new byte[4096];
			                         int readLen = 0;
			                         while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
			                           os.write(buffer, 0, readLen);
			                         }
			                         os.flush();
			                         os.close();
			                     } else {
			                         // 空目录
			                    	 newunzip.buildFile(directoryPath + File.separator
			                                 + zipEntry.getName(), true);
			                     }
			                 }
			             }
			         } catch (IOException ex) {
			             throw ex;
			         } finally {
			             if(null != zipFile){
			                 zipFile.close();;
			             }
			             if (null != is) {
			                 is.close();
			             }
			             if (null != os) {
			                 os.close();
			             }
			         }
			    
						

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public static File buildFile(String fileName, boolean isDirectory) {
    File target = new File(fileName);
    if (isDirectory) {
        target.mkdirs();
    } else {
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
            target = new File(target.getAbsolutePath());
        }
    }
    return target;
} 
}