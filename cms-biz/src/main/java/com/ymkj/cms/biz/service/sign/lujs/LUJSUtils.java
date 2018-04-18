package com.ymkj.cms.biz.service.sign.lujs;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LUJSUtils {

	private final static Logger logger = LoggerFactory.getLogger(LUJSUtils.class);
	public static final String BDD = "00014"; //保单贷
	public static final String WGDRD = "00015"; //网购达人贷
	public static final String KYD = "00018"; //卡友贷

	/**
	 * @Description:过滤掉不需要推送给陆金所的附件</p>
	 * @uthor YM10159
	 * @date 2017年7月10日 下午5:27:09
	 * @param listMap 所有文件夹类型
	 */
	public static JSONArray lujsPicUpload(JSONArray listMap, boolean reportFlag, String productCode){
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < listMap.length(); i++) {
			JSONObject ob = listMap.getJSONObject(i);
			String code = ob.getString("code");
			//公共附件
			if("S4".equals(code) || "S12".equals(code) || "S10".equals(code) || "S8".equals(code) || "S11".equals(code)){
				 jsonArray.put(ob);
			}
			//征信报告
			if(!reportFlag && "L".equals(code)) jsonArray.put(ob);
			//网购达人贷A
			if(productCode.equals(LUJSUtils.WGDRD) && "N".equals(code)) jsonArray.put(ob);
			//卡友贷
			if(productCode.equals(LUJSUtils.KYD) && "M".equals(code)) jsonArray.put(ob);
			//保单贷
			if(productCode.equals(LUJSUtils.BDD) && "H".equals(code)) jsonArray.put(ob);
		}
		return jsonArray;
	}
	
	/**
	 * @Description:保单贷、卡友贷新、网购达人贷新增附件上传</p>
	 * @uthor YM10159
	 * @date 2017年10月31日 下午4:07:27
	 */
	public static JSONArray lujsOtherPicUpload(JSONArray listMap,JSONArray uploadListMap,String productCode,String reportId){
		for (int i = 0; i < listMap.length(); i++) {
			JSONObject ob = listMap.getJSONObject(i);
			String code = ob.getString("code");
			//身份证明、征信报告、个人流水、其它（保单贷和网购达人贷）
			if("B".equals(code) || ("L".equals(code) && StringUtils.isNotBlank(reportId)) || "C".equals(code)){
				uploadListMap.put(ob);
			}
			if("M".equals(code) && !productCode.equals(LUJSUtils.KYD)){
				uploadListMap.put(ob);
			}
		}
		return uploadListMap;
	}

	/**
	 * @Description:文件code转换成中文</p>
	 * @uthor YM10159
	 * @date 2017年7月10日 下午7:18:14
	 * @param code 文件夹编码
	 */
	public static String picCodeFormat(String code){
		String codeCN = "";
		if(code.equals("S4")) codeCN = "身份证";
		if(code.equals("S12")) codeCN = "身份验证结果";
		if(code.equals("S11")) codeCN = "风险金服务协议";
		if(code.equals("S10")) codeCN = "面签照片";
		if(code.equals("S8")) codeCN = "咨询服务协议";
		if(code.equals("L")) codeCN = "征信报告";
		if(code.equals("N")) codeCN = "网购资料"; //针对网购达人贷A
		if(code.equals("M")) codeCN = "其他"; //针对卡友贷
		if(code.equals("H")) codeCN = "保单信息"; //针对保单贷
		return codeCN;
	}

	/**
	 * @Description:通过url获取文件流并转成byte</p>
	 * @uthor YM10159
	 * @date 2017年7月11日 下午2:01:12
	 * @param strUrl
	 */
	public static byte[] getByteFromUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btCreditzx = readInputStream(inStream);// 得到图片的二进制数据
			return btCreditzx;
		} catch (Exception e) {
			logger.debug("转换地址为Byte[]失败。",e);
		}
		return null;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		try{
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			return outStream.toByteArray();
		}catch(Exception e){
			logger.debug("转换地址为Byte[]失败。",e);
		}finally{
			inStream.close();
		}
		return null;
	}
	
	/**
	 * @Description:通过身份证号获取年龄</p>
	 * @uthor YM10159
	 * @date 2017年7月11日 下午6:07:08
	 */
	public static String getAgeByIdNo(String IdNO){  
		 String dates=IdNO.substring(6, 10);
	     SimpleDateFormat df = new SimpleDateFormat("yyyy");
	     String year=df.format(new Date());
	     int u=Integer.parseInt(year)-Integer.parseInt(dates);
	     return String.valueOf(u);
    } 
	
	/**
	 * @Description:获取陆金所文件上传模拟数据</p>
	 * @uthor YM10159
	 * @date 2017年7月13日 下午4:47:16
	 */
	public static JSONArray getModelData(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		for (int i = 0; i < 6; i++) {
			jsonObject = new JSONObject();
			if(i == 0){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145519333630594.jpg");
				jsonObject.put("subclassSort", "L");
			}
			if(i == 1){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145522683734212.jpg");
				jsonObject.put("subclassSort", "N");
			}
			if(i == 2){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145526268002975.jpg");
				jsonObject.put("subclassSort", "S1");
			}
			if(i == 3){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145530250094741.jpg");
				jsonObject.put("subclassSort", "S4");
			}
			if(i == 4){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145554151052199.jpg");
				jsonObject.put("subclassSort", "S10");
			}
			if(i == 5){
				jsonObject.put("resKey", "ZDJR_TO_LUFAX20170713145557768247495.jpg");
				jsonObject.put("subclassSort", "S12");
			}
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}
}
