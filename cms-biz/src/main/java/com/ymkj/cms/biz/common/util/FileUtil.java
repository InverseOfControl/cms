package com.ymkj.cms.biz.common.util;

import java.io.File;

public class FileUtil {

	/**
	 * 删除文件或者目录
	 * @param file
	 */
	public static void deleteFile(File file){
		if (file.exists()) {
			if (file.isFile()) {//判断是否是文件
				file.delete();
			}else if (file.isDirectory()) {//判断是否是目录
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
		
	}
}
