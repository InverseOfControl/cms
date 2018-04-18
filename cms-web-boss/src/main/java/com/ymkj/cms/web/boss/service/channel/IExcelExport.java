package com.ymkj.cms.web.boss.service.channel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ymkj.cms.biz.api.vo.request.channel.ExcelObjVo;
import com.ymkj.cms.biz.api.vo.request.channel.ReqImportExcelVO;

/**
 * @author YM10189
 * @date 2017年5月9日
 * @Description:excel(xls) interface
 */
public interface IExcelExport {

	/**
	 * 债权审核导出
	 * 
	 * @param response
	 * @param datas
	 * @param fieldMap
	 * @param objClass
	 * @param excelVo
	 * @throws Exception
	 */
	void createLoanCheckExcel(HttpServletResponse response, List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass, ExcelObjVo excelVo)
			throws Exception;

	/**
	 * 债权导出
	 * 
	 * @param response
	 * @param datas
	 * @param fieldMap
	 * @param objClass
	 * @param excelVo
	 * @throws Exception
	 */
	void createLoanExcel(HttpServletResponse response, List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass, ExcelObjVo excelVo)
			throws Exception;

	/**
	 * 划拨审请书导出
	 * 
	 * @param response
	 * @param tmpFilePath
	 * @param map
	 * @param excelVo
	 * @throws Exception
	 */
	void createtransferAppExcel(HttpServletResponse response, String tmpFilePath, Map<String, Object> map, ExcelObjVo excelVo) throws Exception;
	
	
	/**
	 * 解析报盘数
	 * @param outStream
	 * @return
	 */
	List<Map<String, String>>importOfferExcel(InputStream inputStream)throws Exception;

	/**
	 * 构建work
	 * 
	 * @param datas
	 * @param fieldMap
	 * @param objClass
	 * @param excelVo
	 * @return
	 * @throws Exception
	 */
	public Workbook buildWork(List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass, ExcelObjVo excelVo) throws Exception;

	public Workbook buildWork(Workbook workBook, List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass, ExcelObjVo excelVo)
			throws Exception;

	public Workbook buildWorkFormat(List<?> datas, Map<String, String[]> fieldMap, Class<?> objClass, ExcelObjVo excelVo) throws Exception;
	
	public  void wirteExcel(Workbook work, HttpServletResponse response, String fileName) throws IOException;
	
	/**
	 * 解析龙信小贷放款文件
	 * @param outStream
	 * @return
	 */
	List<ReqImportExcelVO> importExcel(Workbook workBook) throws Exception;
	
	/**
	 * 把龙信小贷的反馈数据追加到excel中
	 * @param work
	 * @param lxxdDatas
	 * @throws Exception
	 */
	void addLxxdDataToExcel(Workbook work,List<ReqImportExcelVO> lxxdDatas) throws Exception;
	


}
