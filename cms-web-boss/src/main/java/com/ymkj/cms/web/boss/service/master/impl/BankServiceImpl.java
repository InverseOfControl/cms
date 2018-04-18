package com.ymkj.cms.web.boss.service.master.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.biz.api.message.Response;
import com.ymkj.base.core.web.result.PageResult;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSBankVO;
import com.ymkj.cms.biz.api.vo.response.master.ResBMSCheckIsExitsVO;
import com.ymkj.cms.web.boss.facade.apply.BankFacade;
import com.ymkj.cms.web.boss.service.master.IBankService;

@Service
public class BankServiceImpl implements IBankService {
	@Autowired
	private BankFacade bankFacade;

	@Override
	public ResBMSBankVO findById(Long id) {
		return bankFacade.findById(id);
	}

	@Override
	public PageResult<ResBMSBankVO> listPage(ReqBMSBankVO reqDemoVO) {
		PageResult<ResBMSBankVO> pageResult = bankFacade.listPage(reqDemoVO);
		return pageResult;
	}

	@Override
	public boolean addBank(ReqBMSBankVO reqDemoVO) {
		int resMsg = bankFacade.addBank(reqDemoVO);
		return resMsg == 1 ? true : false;
	}

	@Override
	public boolean deleteBank(String id) {
		return bankFacade.deleteBank(id);
	}

	@Override
	public boolean updateBank(ReqBMSBankVO reqDemoVO) {
		return bankFacade.updateBank(reqDemoVO);
	}

	@Override
	public List<ResBMSBankVO> getAllBank(ReqBMSBankVO reqDemoVO) {
		return bankFacade.getAllBank(reqDemoVO);
	}

	@Override
	public ResBMSBankVO findOne(ReqBMSBankVO reqDemoVO) {
		return bankFacade.findOne(reqDemoVO);
	}
	
	@Override
	public Map<String, Object> Analytical(String fileName, MultipartFile Mfile) {
		InputStream is = null;
		boolean isExcel2003 = true;
		if (isExcel2007(fileName)) {
			isExcel2003 = false;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			is = (Mfile.getInputStream());
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			List<ReqBMSBankVO> productList = getBankList(wb, Mfile);
			map.put("bankList", productList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	public List<ReqBMSBankVO> getBankList(Workbook wb, MultipartFile Mfile) {
		List<ReqBMSBankVO> bankList = new ArrayList<ReqBMSBankVO>();
		// 第一个sheet 获取机构信息的sheet
		Sheet sheet = null;// 定义工作表
		int ss=0;
		// 获取工作表的个数,并循环
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			if (wb.getSheetAt(i).getSheetName().equals("银行列表模板")) {
				sheet = wb.getSheetAt(i);
			} else{
				ss++;
			}
		}
		if(sheet==null&&ss!=0){
			sheet=wb.getSheetAt(0);
		}

		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			int sNum = 0;
			String failureCause = "";
			if (i != 0) {
				if (null != row && row.getFirstCellNum() > -1) {
					int rowNum = row.getLastCellNum();
					ReqBMSBankVO bankVo = new ReqBMSBankVO();
					for (int c = 0; c < rowNum; c++) {
						String count = null;
						Cell cell = row.getCell(c);
						if (null != cell) {
							if (c == 0) {// 银行代码
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									bankVo.setCode(cell.getStringCellValue());
									bankVo.setDerivedResult("成功");

								} else {
									bankVo.setCode("");
									bankVo.setDerivedResult("失败");
									failureCause += "银行代码为空;";
									sNum++;
								}
							} else if (c == 1) {// 银行名称
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									bankVo.setName(cell.getStringCellValue());
									bankVo.setDerivedResult("成功");
								} else {
									bankVo.setName("");
									bankVo.setDerivedResult("失败");
									failureCause += "银行名称为空;";
									sNum++;
								}
							} else if (c == 2) {// 备注
								cell.setCellType(Cell.CELL_TYPE_STRING);
								if (!cell.getStringCellValue().isEmpty()) {
									
									bankVo.setRemarkExcel(cell.getStringCellValue().toString());
								
									bankVo.setDerivedResult("成功");
								} else {
									bankVo.setRemarkExcel("");
								}
							}
							bankVo.setFailureCause(failureCause);
						}
					}
					if (sNum > 0) {
						bankVo.setDerivedResult("失败");
					} else {
						bankVo.setDerivedResult("成功");
					}
					bankList.add(bankVo);
				}
			}
		}

		return bankList;

	}

	// 判断传进来的文件类型
	public boolean isExcel2007(String fileName) {
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			/* System.out.println("read2003Excel(file)"); */
			return false;// read2003Excel(file);//
		} else if ("xlsx".equals(extension)) {
			/* System.out.println("read2007Excel(file)"); */
			return true;// read2007Excel(file);//
		}
		return false;

	}

	@Override
	public Response<ResBMSCheckIsExitsVO> checkBankIsExits(ReqBMSBankVO reqBankVO) {
		return bankFacade.checkBankIsExits(reqBankVO);
	}

	@Override
	public Boolean findChannelBankById(ReqBMSBankVO reqBankVO) {
		return bankFacade.findChannelBankById(reqBankVO);
	}


}
