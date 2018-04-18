package com.ymkj.cms.biz.service.sign.bohai.two;

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
public class BoHaiTwoUtils {

	private final static Logger logger = LoggerFactory.getLogger(BoHaiTwoUtils.class);

	/**
	 * @Description:过滤掉不需要推送的附件</p>
	 * @date 2017年7月10日 下午5:27:09
	 * @param listMap 所有文件夹类型
	 */
	public static JSONArray boHaiTwoPicUpload(JSONArray listMap){
		JSONArray jsonArray = new JSONArray();
		//身份证正面、身份证反面、贷款合同、委托扣款授权书_证大、申请表
		for (int i = 0; i < listMap.length(); i++) {
			JSONObject ob = listMap.getJSONObject(i);
			String code =null;
			if (ob == null || ob.isNull("code")){
				code = "";
			} else {
				code = ob.getString("code");
			}
			if("S41".equals(code) 
					|| "S42".equals(code) 
					|| "S1".equals(code) 
					|| "S3".equals(code) 
					|| "A".equals(code)){
				jsonArray.put(ob);
			}
		}
		return jsonArray;
	}

	/**
	 * @Description:文件code转换成中文</p>
	 * @date 2017年7月10日 下午7:18:14
	 * @param code 文件夹编码
	 */
	public static String picCodeFormat(String code){
		String codeCN = "";
		if(code.equals("S41")) codeCN = "身份证正面";
		if(code.equals("S42")) codeCN = "身份证反面";
		if(code.equals("S1")) codeCN = "贷款合同";
		if(code.equals("S3")) codeCN = "委托扣款授权书_证大";
		if(code.equals("A")) codeCN = "申请表";
		return codeCN;
	}

	/**
	 * @Description:通过url获取文件流并转成byte</p>
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
	 * @Description:获取文件上传模拟数据</p>
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
