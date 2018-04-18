package com.ymkj.cms.web.boss.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {

	// 根据文件路径下载
	public static boolean downLoadFile(String filePath, String uploadPath, HttpServletResponse response)
			throws Exception {
		// 文件路径不存在就创建
		File uploadPathFile = new File(uploadPath);
		if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
			uploadPathFile.mkdir();
		}
		if (filePath == null || filePath == "") {
			return false;
		}
		// 文件不存在
		filePath = encodeStr(filePath);
		File file = new File(filePath); // 根据文件路径获得File文件
		if (!file.exists()) {
			return false;
		}
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		byte[] buffer = new byte[4096];// 缓冲区
		response.setContentType("application/msexcel;charset=GBK");
		String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8").replace("+", "%20");
		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName));
		response.setContentLength((int) file.length());
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); // 不可少
			response.flushBuffer();// 不可少
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return false;
	}

	// 转格式
	private static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * <pre>
	 * 根据原始文件名获取其扩展名
	 * </pre>
	 *
	 * @param originalFilename
	 * @return
	 */
	public static String getSuffixName(String originalFilename) {
		String suffix = null;
		if (0 <= originalFilename.lastIndexOf('.')
				&& originalFilename.lastIndexOf('.') < originalFilename.length() - 1) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
		}
		return suffix;
	}

	public static boolean downLoadExcelFile(String filePath, String fileName, HttpServletResponse response)
			throws Exception {
		File file = new File(filePath); // 根据文件路径获得File文件
		// 设置下载的文件类型
		response.setContentType("application/msexcel;charset=GBK");
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName));
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); // 不可少
			response.flushBuffer();// 不可少
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return true;
	}
}
