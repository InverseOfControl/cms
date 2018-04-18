package com.ymkj.cms.web.boss.controller.master;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.biz.api.vo.request.master.ReqBMSReasonVO;
import com.ymkj.cms.web.boss.common.DateUtil;
import com.ymkj.cms.web.boss.service.master.IReasonService;

@Controller
@RequestMapping("reason")
public class ReasonController extends BaseController {

	@Autowired
	private IReasonService reasonService;

	private String ss = null;

	/**
	 * 返回取消原因视图
	 * 
	 * @return
	 */
	@RequestMapping("cancelResonImportView")
	public String cancelResonDataImport() {
		ss = "取消";
		return "master/reason/cancelReasonDataImport";
	}

	/**
	 * 返回退回原因视图
	 * 
	 * @return
	 */
	@RequestMapping("returnResonImportView")
	public String returnResonDataImport() {
		ss = "退回";
		return "master/reason/returnResonDataImport";
	}

	/**
	 * 返回拒绝原因视图
	 * 
	 * @return
	 */
	@RequestMapping("rejectResonImportView")
	public String productDataImport() {
		ss = "拒绝";
		return "master/reason/rejectReasonDataImport";
	}

	/**
	 * 导入excel,下载sql文件
	 * 
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/resonDataImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@RequestParam("reasonUploadfile") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean result = false;
		// 判断文件是否为空
		if (multipartFile == null)
			return null;
		// 获取文件名
		String name = multipartFile.getOriginalFilename();
		System.out.println("name:" + name);
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = multipartFile.getSize();
		if (name == null || ("").equals(name) && size == 0)
			System.out.println("传入的文件为空文件!");// return null;
		/* System.out.println("操作类型:" + ss); */
		Map<String, Object> map = reasonService.Analytical(name, multipartFile, ss);
		// 取消原因list集合
		List<ReqBMSReasonVO> cancelReasonList = (List<ReqBMSReasonVO>) map.get("cancelReasonList");
		// 退回原因list集合
		List<ReqBMSReasonVO> returnReasonList = (List<ReqBMSReasonVO>) map.get("returnReasonList");
		// 拒绝原因list集合
		List<ReqBMSReasonVO> rejectReasonList = (List<ReqBMSReasonVO>) map.get("rejectReasonList");
		String fileName = "";
		Date nowDate = new Date();
		if (cancelReasonList != null) {
			fileName = "取消原因数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		} else if (returnReasonList != null) {
			fileName = "退回原因数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		} else if (rejectReasonList != null) {
			fileName = "拒绝原因数据导入" + DateUtil.defaultFormatDay(nowDate) + ".sql";
		}

		response.reset();
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "iso8859-1"));// 设定输出文件头
		response.setContentType("text/x-plain");

		try {
			ServletOutputStream out = response.getOutputStream();
			String path = System.getProperty("java.io.tmpdir") + "\\poem.txt";
			File files = new File(path);
			FileOutputStream fos = new FileOutputStream(files);
			Writer writer = new OutputStreamWriter(fos, "GBK");
			String text = null;
			/* UserSession user= ApplicationContext.getUser(); */
			// 生成插入原因表sql
			writer.write("/*delete bms_reason sql*/ \n");
			writer.write("/*truncate table bms_reason;*/ \n");
			writer.write("/*insert bms_reason sql*/ \n");

			
			if (cancelReasonList != null) {// 取消原因
				
				for (ReqBMSReasonVO cancelReasonVo : cancelReasonList) {
					String reasonText="";
					if (cancelReasonVo.getTheFirstCellReason() != null) {
					  if (cancelReasonVo.getShenQingLuRu() != null) {
						  if(cancelReasonVo.getShenQingLuRu().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'SQLR','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getLuRuFuHe() != null) {
						 if(cancelReasonVo.getLuRuFuHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'LRFH','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getXinShenChuShen() != null) {
						 if(cancelReasonVo.getXinShenChuShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							} 
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'XSCS','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getXinShenZhongShen() != null) {
						if(cancelReasonVo.getXinShenZhongShen().equals("√")){
							reasonText="1";
						}else{
							reasonText="2";
						} 
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'XSZS','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getHeTonQianYue() != null) {
						if(cancelReasonVo.getHeTonQianYue().equals("√")){
							reasonText="1";
						}else{
							reasonText="2";
						}  
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'HTQY','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getHeTonQueRen() != null) {
						if(cancelReasonVo.getHeTonQueRen().equals("√")){
							reasonText="1";
						}else{
							reasonText="2";
						} 
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'HTQR','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getFangKuanShenHe() != null) {
						if(cancelReasonVo.getFangKuanShenHe().equals("√")){
							reasonText="1";
						}else{
							reasonText="2";
						} 
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'FKSH','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					if (cancelReasonVo.getFangKuanQueRen() != null) {
						if(cancelReasonVo.getFangKuanQueRen().equals("√")){
							reasonText="1";
						}else{
							reasonText="2";
						} 
						text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
								+ "values('" + cancelReasonVo.getTheFirstCellReason() + "','" + cancelReasonVo.getRemark()
								+ "','admin',sysdate(),1,1,0,'FKQR','cancel','1',0,'"
								+ cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"')";

						writer.write(text + ";\n");
					}
					}
					if (cancelReasonVo.getTheUpperLevelReason()!=null)  {
						if (cancelReasonVo.getShenQingLuRu() != null) {
							if(cancelReasonVo.getShenQingLuRu().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}

							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
									+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'SQLR','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ cancelReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='SQLR' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						}
						if (cancelReasonVo.getLuRuFuHe() != null) {
							 if(cancelReasonVo.getLuRuFuHe().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
							 text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
										+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'LRFH','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ cancelReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='LRFH' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
								writer.write(text + ";\n");
						}
						if (cancelReasonVo.getXinShenChuShen() != null) {
							 if(cancelReasonVo.getXinShenChuShen().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								} 
							 text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
										+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'XSCS','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ cancelReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='XSCS' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
								writer.write(text + ";\n");

							writer.write(text + ";\n");
						}
						if (cancelReasonVo.getXinShenZhongShen() != null) {
							if(cancelReasonVo.getXinShenZhongShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							} 
							 text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
										+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'XSZS','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ cancelReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='XSZS' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
								writer.write(text + ";\n");
						}
						if (cancelReasonVo.getHeTonQianYue() != null) {
							if(cancelReasonVo.getHeTonQianYue().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}  
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
									+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQY','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ cancelReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='HTQY' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						if (cancelReasonVo.getHeTonQueRen() != null) {
							if(cancelReasonVo.getHeTonQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							} 
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
									+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQR','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ cancelReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='HTQR' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						}
						if (cancelReasonVo.getFangKuanShenHe() != null) {
							if(cancelReasonVo.getFangKuanShenHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							} 
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
									+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKSH','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ cancelReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='FKSH' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						}
						if (cancelReasonVo.getFangKuanQueRen() != null) {
							if(cancelReasonVo.getFangKuanQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							} 
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CODE,REASON_TEXPLAIN)"
									+ "values('" + cancelReasonVo.getReason() + "','" + cancelReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKQR','cancel','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ cancelReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='FKQR' AND OPERATION_TYPE='cancel' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+cancelReasonVo.getCanRequestDays()+"','"+cancelReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						}

						}
					}
				}

			} else if (returnReasonList != null) {// 退回原因

				for (ReqBMSReasonVO returnReasonVo : returnReasonList) {
					String reasonText="";
					if (returnReasonVo.getTheFirstCellReason() != null) {
						if (returnReasonVo.getShenQingLuRu() != null) {
							if(returnReasonVo.getShenQingLuRu().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'SQLR','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getLuRuFuHe() != null) {
							if(returnReasonVo.getLuRuFuHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'LRFH','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getXinShenChuShen() != null) {
							if(returnReasonVo.getXinShenChuShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'XSCS','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenReturnMD()!=null){
							if(returnReasonVo.getZhongShenReturnMD().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'ZSRTNLR','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenReturnCS()!=null){
							if(returnReasonVo.getZhongShenReturnCS().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'ZSRTNCS','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";
							writer.write(text + ";\n");
						}

						if (returnReasonVo.getHeTonQianYue() != null) {
							if(returnReasonVo.getHeTonQianYue().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'HTQY','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getHeTonQueRen() != null) {
							if(returnReasonVo.getHeTonQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'HTQR','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getFangKuanShenHe() != null) {
							if(returnReasonVo.getFangKuanShenHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'FKSH','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getFangKuanQueRen() != null) {
							if(returnReasonVo.getFangKuanQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'FKQR','return','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getChuShenGuaQi()!=null){
							if(returnReasonVo.getChuShenGuaQi().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'XSCS','hang','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenGuaQi()!=null){
							if(returnReasonVo.getZhongShenGuaQi().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getTheFirstCellReason() + "','"
									+ returnReasonVo.getRemark() + "','admin',sysdate(),1,1,0,'XSZS','hang','1',0,'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}

					}

					if (returnReasonVo.getTheUpperLevelReason()!=null)  {
						if (returnReasonVo.getShenQingLuRu() != null) {
							if(returnReasonVo.getShenQingLuRu().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}

							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'SQLR','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='SQLR' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"' )";
							writer.write(text + ";\n");
						}
						if (returnReasonVo.getLuRuFuHe() != null) {
							if(returnReasonVo.getLuRuFuHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'LRFH','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='LRFH' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"' )";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getXinShenChuShen() != null) {
							if(returnReasonVo.getXinShenChuShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'XSCS','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='XSCS' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenReturnMD()!=null){
							if(returnReasonVo.getZhongShenReturnMD().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'ZSRTNLR','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='ZSRTNLR' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenReturnCS()!=null){
							if(returnReasonVo.getZhongShenReturnCS().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'ZSRTNCS','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='ZSRTNCS' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"' )";

							writer.write(text + ";\n");
						}

						if (returnReasonVo.getHeTonQianYue() != null) {
							if(returnReasonVo.getHeTonQianYue().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQY','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='HTQY' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"' )";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getHeTonQueRen() != null) {
							if(returnReasonVo.getHeTonQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQR','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='HTQR' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getFangKuanShenHe() != null) {
							if(returnReasonVo.getFangKuanShenHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKSH','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='FKSH' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"' )";

							writer.write(text + ";\n");
						}
						if (returnReasonVo.getFangKuanQueRen() != null) {
							if(returnReasonVo.getFangKuanQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKQR','return','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='FKQR' AND OPERATION_TYPE='return' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getChuShenGuaQi()!=null){
							if(returnReasonVo.getChuShenGuaQi().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'XSCS','hang','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='XSCS' AND OPERATION_TYPE='hang' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(returnReasonVo.getZhongShenGuaQi()!=null){
							if(returnReasonVo.getZhongShenGuaQi().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CODE,REASON_TEXPLAIN)"
									+ "values('" + returnReasonVo.getReason() + "','" + returnReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'XSZS','hang','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
									+ returnReasonVo.getTheUpperLevelReason()
									+ "' AND OPERATION_MODULE='XSZS' AND OPERATION_TYPE='hang' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"+returnReasonVo.getCode()+"','"+reasonText+"')";

							writer.write(text + ";\n");
						}
					}
				}

			} else if (rejectReasonList != null) {// 拒绝原因
				for (ReqBMSReasonVO rejectReasonVo : rejectReasonList) {
					String isBlackList=rejectReasonVo.getRemark()==""?"":"isBlackList_Y";
					String reasonText="";
					if (rejectReasonVo.getTheFirstCellReason() != null) {
						if (rejectReasonVo.getShenQingLuRu() != null) {
							if(rejectReasonVo.getShenQingLuRu().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'SQLR','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";


							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getLuRuFuHe() != null) {
							if(rejectReasonVo.getLuRuFuHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'LRFH','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getXinShenChuShen() != null) {
							if(rejectReasonVo.getXinShenChuShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'XSCS','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getXinShenZhongShen() != null) {
							if(rejectReasonVo.getXinShenZhongShen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'XSZS','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if(rejectReasonVo.getSqjxiwh()!=null){
							if(rejectReasonVo.getSqjxiwh().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'SQJXXWH','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getHeTonQianYue() != null) {
							if(rejectReasonVo.getHeTonQianYue().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQY','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getHeTonQueRen() != null) {
							if(rejectReasonVo.getHeTonQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'HTQR','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getFangKuanShenHe() != null) {
							if(rejectReasonVo.getFangKuanShenHe().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKSH','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
						if (rejectReasonVo.getFangKuanQueRen() != null) {
							if(rejectReasonVo.getFangKuanQueRen().equals("√")){
								reasonText="1";
							}else{
								reasonText="2";
							}
							text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
									+ "values('" + rejectReasonVo.getTheFirstCellReason() + "','" + rejectReasonVo.getRemark()
									+ "','admin',sysdate(),1,1,0,'FKQR','reject','1',0,'"
									+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+ "','"+reasonText+"')";

							writer.write(text + ";\n");
						}
				}
					if (rejectReasonVo.getTheUpperLevelReason()!=null) {
						if (rejectReasonVo.getReason() != null) {
							if (rejectReasonVo.getShenQingLuRu() != null) {
								if(rejectReasonVo.getShenQingLuRu().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'SQLR','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='SQLR' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getLuRuFuHe() != null) {
								if(rejectReasonVo.getLuRuFuHe().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'LRFH','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='LRFH' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getXinShenChuShen() != null) {
								if(rejectReasonVo.getXinShenChuShen().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'XSCS','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='XSCS' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getXinShenZhongShen() != null) {
								if(rejectReasonVo.getXinShenZhongShen().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'XSZS','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='XSZS' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+ " ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if(rejectReasonVo.getSqjxiwh()!=null){
								if(rejectReasonVo.getSqjxiwh().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}	
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'SQJXXWH','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='SQJXXWH' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+ " ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");	
							}
							if (rejectReasonVo.getHeTonQianYue() != null) {
								if(rejectReasonVo.getHeTonQianYue().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'HTQY','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='HTQY' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getHeTonQueRen() != null) {
								if(rejectReasonVo.getHeTonQueRen().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'HTQR','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='HTQR' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getFangKuanShenHe() != null) {
								if(rejectReasonVo.getFangKuanShenHe().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'FKSH','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='FKSH' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays() +" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";

								writer.write(text + ";\n");
							}
							if (rejectReasonVo.getFangKuanQueRen() != null) {
								if(rejectReasonVo.getFangKuanQueRen().equals("√")){
									reasonText="1";
								}else{
									reasonText="2";
								}
								text = "insert into bms_reason(REASON,REMARK,CREATOR,CREATED_TIME,CREATOR_ID,VERSION,IS_DELETE,OPERATION_MODULE,OPERATION_TYPE,TYPE,PARENT_ID,CAN_REQUEST_DAYS,CONDITION_TYPE,CODE,REASON_TEXPLAIN)"
										+ "values('" + rejectReasonVo.getReason() + "','" + rejectReasonVo.getRemark()
										+ "','admin',sysdate(),1,1,0,'FKQR','reject','2',(SELECT U.RESON_ID FROM (SELECT ID RESON_ID FROM BMS_REASON WHERE REASON ='"
										+ rejectReasonVo.getTheUpperLevelReason()
										+ "' AND OPERATION_MODULE='FKQR' AND OPERATION_TYPE='reject' AND TYPE=1 AND REASON_TEXPLAIN !='3') U ),'"
										+ rejectReasonVo.getCanRequestDays()+" ','"+isBlackList+"','"+rejectReasonVo.getCode()+"','"+reasonText+"')";
								writer.write(text + ";\n");
							}
						}

					}

				}

			}
			// 判断sql语句是否有生成
			if (text != null) {
				result = true;
			}
			writer.close();
			fos.close();
			FileInputStream fis = new java.io.FileInputStream(files);
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
			byte[] cache = new byte[4096];
			for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
				byteOutputStream.write(cache, 0, offset);
			}
			byte[] bt = null;
			bt = byteOutputStream.toByteArray();
			out.write(bt);
			out.flush();
			out.close();
			fis.close();
			if (files.exists()) {
				files.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		hashMap.put("isSuccess", result);

		return hashMap;
	}

}
