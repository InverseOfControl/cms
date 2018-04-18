package com.ymkj.cms.biz.common.util;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.ymkj.cms.biz.exception.BizErrorCode;
import com.ymkj.cms.biz.exception.BizException;

/**
 * HTML生成PDF工具类
 * 
 * @author user
 *
 */
public class Html2PDFUtil {

	private final static Logger logger = LoggerFactory
			.getLogger(Html2PDFUtil.class);

	/**
	 * 替换字符串中占位符，占位符结构为${}
	 * 
	 * @param content
	 *            含有占位符字符串
	 * @param values
	 * @return
	 */
	public static String replaceText(String content, Map<String, Object> values) {
		// 填充值为空时，直接原样返回。
		if (values == null || values.size() == 0) {
			return content;
		}
		// 根据正则进行匹配
		Pattern pattern = Pattern.compile("\\$\\{(\\w*)\\}");
		Matcher matcher = pattern.matcher(content);
		String value = null;
		Object temp = null;
		while (matcher.find()) {
			temp = values.get(matcher.group(1));
			value = temp == null ? "" : temp.toString();
			content = content.replace(matcher.group(), value);
		}
		return content;
	}
	/** 
	 * html 必须是格式良好的 
	 * @param str 
	 * @return 
	 * @throws Exception 
	 */  
	public static String formatHtml(String str) throws Exception {  
	    Document document = null;  
	    document = (Document) DocumentHelper.parseText(str);  
	  
	    OutputFormat format = OutputFormat.createPrettyPrint();  
	    format.setEncoding("utf-8");  
	    StringWriter writer = new StringWriter();  
	  
	    HTMLWriter htmlWriter = new HTMLWriter(writer, format);  
	  
	    htmlWriter.write(document);  
	    htmlWriter.close();  
	    return writer.toString();  
	} 
	/**
	 * 根据模板生成PDF,传入的HTML模板需要包含HTML,HEADE,BODY完整标签，以免生成PDF样式出现问题
	 * 
	 * @param content
	 *            含有占位符的模板
	 * @param values
	 *            模板填充值
	 * @param fileName
	 *            输出文件路名
	 * @throws ProcessException
	 */
	public static void generatePdf(String content, Map<String, Object> values,
			String fileName)  {
		Document document = new Document(PageSize.A4);
		FileOutputStream fos;
		try {
//			BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
//	        Font font = new Font(bf, 12, Font.NORMAL);  
			fos = new FileOutputStream(fileName);
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			writer.setViewerPreferences(PdfWriter.HideToolbar);
			document.open();
//			System.out.println(content);
			content = replaceText(content, values);
			// HTML解析生成PDF
//			XMLWorkerHelper.getInstance().parseXHtml(writer, document, IOUtils.toInputStream(content),Charset.forName("UTF-8"));
			XMLWorkerHelper.getInstance().parseXHtml(writer, document,
					new ByteArrayInputStream(content.getBytes("utf-8")),Charset.forName("UTF-8"));
			document.close();
			writer.close();
			IOUtils.closeQuietly(fos);
			logger.debug("生成\"{}\"成功", fileName);
		} catch (FileNotFoundException e) {
			logger.error("输出文件路径不存在", e);
			throw new BizException(BizErrorCode.FILEPATH_NOT_FOUND,"输出文件路径不存在");
		} catch (Exception e) {
			logger.error("生成文件报错", e);
			throw new BizException(BizErrorCode.CREATEFILE_EOERR,"生成文件报错");
		}

	}

/*	public static void main(String[] args) throws Exception {
		
	System.out.println(System.getProperty("user.dir"));	
	
	String content = ""
			+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> "
			+ "<body>"
			+ "本协议由以下叁方于 ${year}       年    ${month}   月   ${day}    日在   ${city}     市    ${area}      区签署并履行："
			+"</body>";
		//String content = FileUtils.readFileToString(new File("D:\\test1.txt"),Charset.forName("utf-8"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractNum", "201554545522");
		map.put("month", "3");
		map.put("mail", "21");
		map.put("city", "上海");
		map.put("area", "浦东新区");
		Html2PDFUtil.generatePdf(content, map,
				System.getProperty("user.dir")+"/"+"src/main/resources/contracthome/"+"1.pdf");

	}*/

}