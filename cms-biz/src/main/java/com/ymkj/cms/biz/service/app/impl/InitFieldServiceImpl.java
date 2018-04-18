package com.ymkj.cms.biz.service.app.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ymkj.base.core.biz.dao.BaseDao;
import com.ymkj.base.core.biz.service.BaseServiceImpl;
import com.ymkj.cms.biz.dao.app.IInitFieldDao;
import com.ymkj.cms.biz.entity.app.BMSExtendsionFieldEntity;
import com.ymkj.cms.biz.service.app.IInitFieldService;

@Service
public class InitFieldServiceImpl extends BaseServiceImpl<BMSExtendsionFieldEntity> implements IInitFieldService{

	private static Log log = LogFactory.getLog(InitFieldServiceImpl.class);

	@Autowired
	private IInitFieldDao initDataDao;

	@Override
	protected BaseDao<BMSExtendsionFieldEntity> getDao() {
		return initDataDao;
	}

	@Override
	public Map<String,Object> initField(String appCurrentTime) {
		long startTime = System.currentTimeMillis();
		
		//返回集合
		Map<String,Object> returnMap = new LinkedHashMap<>();
		//下拉框列表
		List<String> selectList = new ArrayList<String>();

		//app时间与数据库时间对比
		List<Map<String,Object>> UpdateFieldsList = initDataDao.getUpdateFieldsTime();
		String lastUpdateDateStr = ObjectUtils.toString(UpdateFieldsList.get(0).get("update_time"));

		returnMap = compareAppTime(appCurrentTime,lastUpdateDateStr);
		if(!returnMap.isEmpty()){
			return returnMap;
		}

		//字段所属组列表信息
		List<Map<String,Object>> fieldGroupList = initDataDao.getFieldGroup();
		
		for (Map<String, Object> fieldGroup : fieldGroupList) {
			
			//封装fieldList节点数据
			Map<String,Object> groupMap = new HashMap<>();

			String groupCode = ObjectUtils.toString(fieldGroup.get("GROUP_CODE"));
			String groupName = ObjectUtils.toString(fieldGroup.get("GROUP_NAME"));

			List<BMSExtendsionFieldEntity> fieldList = initDataDao.getFieldByGroupCode(groupCode);

			if(CollectionUtils.isNotEmpty(fieldList)){
				List<Map<String,Object>> tempFieldList = null;
				groupMap.put("typeName", groupName);
				
				tempFieldList = (List<Map<String, Object>>) this.packageNodeList(fieldList, selectList).get("fieldList");
				selectList = (List<String>) this.packageNodeList(fieldList, selectList).get("selectList");
				
				groupMap.put("fieldList", tempFieldList);
			}

			//获取隐藏组列表信息
			List<Map<String,Object>> hideLabelList = initDataDao.getHideLabelList(groupCode);
			if(CollectionUtils.isNotEmpty(hideLabelList)){
				List<Map<String,Object>> expandList = new ArrayList<>();

				groupMap.put("sectionKey", groupCode);
				groupMap.put("sectionLabel",groupName);

				for (Map<String, Object> hideLabelMap : hideLabelList) {
					Map<String,Object> expandMap = new HashMap<>();
					List<BMSExtendsionFieldEntity> hideLabelDetailList = new ArrayList<>();

					String hideLableCode = ObjectUtils.toString(hideLabelMap.get("hide_label_code"));
					String hideLableName = ObjectUtils.toString(hideLabelMap.get("hide_label_name"));

					expandMap.put("sectionKey",hideLableCode);
					expandMap.put("sectionLabel",hideLableName);

					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("groupCode", groupCode);
					paramMap.put("hideLabelCode", hideLableCode);
					hideLabelDetailList = initDataDao.getHideLabelDetailList(paramMap);

					if(CollectionUtils.isNotEmpty(hideLabelDetailList)){
						List<Map<String,Object>> tempFieldList = null;
						
						tempFieldList = (List<Map<String, Object>>) this.packageNodeList(hideLabelDetailList, selectList).get("fieldList");
						selectList = (List<String>) this.packageNodeList(hideLabelDetailList, selectList).get("selectList");
						
						expandMap.put("fieldList", tempFieldList);
						expandList.add(expandMap);
					}
				}
				
				groupMap.put("expandList", expandList);
			}else{
				groupMap.put("sectionKey", groupCode);
				groupMap.put("sectionLabel", "");
			}
			returnMap.put(groupCode, groupMap);
		}
		
		//封装listData节点数据
		if(CollectionUtils.isNotEmpty(selectList)){
			Map<String, Object> listDataMap = new HashMap<String, Object>();
			
			for (String selectStr : selectList) {
				if(selectStr.equals("relationship")){
					List<Map<String,Object>> relationshipList = getListData(selectStr);
					listDataMap.put("relationship", getListData(selectStr));
					listDataMap.put("relationship1", relationshipList.subList(0, relationshipList.size()-1));
				}else{
					listDataMap.put(selectStr, getListData(selectStr));
				}
			}
			listDataMap.put("areaList", getProvicesList());
		    listDataMap.put("workTypeRelaList", packageWorkTypeRelation("app_jobType_rel"));
		    listDataMap.put("noGovInstitution", packagePostRelation("app_corpStructure_rel"));
			returnMap.put("listData", listDataMap);
		}
		long endTime = System.currentTimeMillis();
		log.info("app初始化接口耗用时间："+(endTime-startTime));
		return returnMap;
	}
	
	/**
	 * @Description:获取公司性质对应的下拉框数据</p>
	 * @uthor YM10159
	 * @date 2017年7月27日 上午11:57:01
	 */
	public List<Map<String,Object>> getListData(String selectStr){
		List<Map<String,Object>> adapaterList = initDataDao.getAdapaterList(selectStr);
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		
		for (Map<String, Object> map : adapaterList) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("name", map.get("code"));
			tempMap.put("title", map.get("nameCn"));
			tempList.add(tempMap);
		}
		return tempList;
	} 
	
	/**
	 * <p>Description:app初始化控件时间对比</p>
	 * @uthor YM10159
	 * @date 2017年4月16日 上午10:32:56
	 * @param @param appCurrentTime app当前时间
	 * @param @param lastUpdateDateStr 最新修改时间
	 */
	public Map<String,Object> compareAppTime(String appCurrentTime,String lastUpdateDateStr){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
		
		if(StringUtils.isNotBlank(appCurrentTime) && StringUtils.isNotBlank(lastUpdateDateStr)){
			Date appCurrDate = null;
			Date lastUpdateDate = null;
			try {
				appCurrDate = df.parse(appCurrentTime);
				lastUpdateDate = df.parse(lastUpdateDateStr);
			} catch (ParseException e) {}

			if (!lastUpdateDate.after(appCurrDate)){
				returnMap.put("flag", true);
				returnMap.put("lastUpdateTime", lastUpdateDateStr);
				returnMap.put("message", "查询成功");
				returnMap.put("currentTime", df.format(new Date()));
			}
		}
		return returnMap;
	}

	/**
	 * <p>Description:封装json节点数据</p>
	 * @uthor YM10159
	 * @date 2017年4月16日 上午10:10:00
	 * @param @param fieldList 包括： fieldList节点和expandList节点
	 * @param @param selectList 下拉框列表
	 */
	public Map<String,Object> packageNodeList(List<BMSExtendsionFieldEntity> fieldList,List<String> selectList){
		Map<String,Object> returnMap = new HashMap<>();
		List<Map<String,Object>> tempFieldList = new ArrayList<>(); 
		String splitFlag = "@split@";

		if(CollectionUtils.isNotEmpty(fieldList)){

			for (BMSExtendsionFieldEntity extendsionFieldEntity : fieldList) {
				Map<String,Object> tempMap = new LinkedHashMap<>();
				
				String fieldType = ObjectUtils.toString(extendsionFieldEntity.getFieldType());
				String relationCodeType = ObjectUtils.toString(extendsionFieldEntity.getRelationCodeType());

				//下拉框数据
				if(fieldType.equals("3") && !selectList.contains(relationCodeType)){
					selectList.add(relationCodeType);
				}

				tempMap.put("label",ObjectUtils.toString(extendsionFieldEntity.getFieldName()));
				tempMap.put("fieldKey", ObjectUtils.toString(extendsionFieldEntity.getFieldCode()));
				tempMap.put("inputType", fieldType);
				tempMap.put("isRequested", ObjectUtils.toString(extendsionFieldEntity.getIsRequested()));
				tempMap.put("isEdited", ObjectUtils.toString(extendsionFieldEntity.getIsEdited()));
				tempMap.put("inputData", relationCodeType);
				tempMap.put("defaultValue", ObjectUtils.toString(extendsionFieldEntity.getDefaultValue()));
				
				String fieldRegex = ObjectUtils.toString(extendsionFieldEntity.getFieldRegex());
				String fieldTip = ObjectUtils.toString(extendsionFieldEntity.getFieldTip());
				String fieldName = ObjectUtils.toString(extendsionFieldEntity.getFieldName());
				List<Object> fieldLegalList = new ArrayList<Object>();
				if (StringUtils.isNotBlank(fieldRegex)) {
					String[] fieldRegexArr = fieldRegex.split(splitFlag);
					String[] fieldTipArr = fieldTip.split(splitFlag);
					for (int i = 0; i < fieldRegexArr.length; i++) {
						Map<String,Object> tempMap1 = new HashMap<>();
						tempMap1.put("regex", fieldRegexArr[i]);
						tempMap1.put("tip", "["+fieldName+"]"+fieldTipArr[i]);
						fieldLegalList.add(tempMap1);
					}
				}
				tempMap.put("fieldRegex", fieldLegalList);
				tempMap.put("isRequestedAssets", ObjectUtils.toString(extendsionFieldEntity.getIsRequestedAssets()));
				tempFieldList.add(tempMap);
			}
		}
		
		returnMap.put("fieldList", tempFieldList);
		returnMap.put("selectList", selectList);
		
		return returnMap;
	}

	/**
	 * 读取数据库封装省市区数据
	 * 效率底下,如果省市区数据修改，建议调用此方法生成到文件中
	 * @author YM10159
	 */
	public Map<String, Object> packageProvincesList() {
		Map<String,Object> returnMap = new HashMap<>();
		Map<String,Object> area0Map = new HashMap<>();
		Map<String,Object> area1Map = new HashMap<>();
		Map<String,Object> area2Map = new HashMap<>();

		List<Map<String,Object>> provinceList = this.initDataDao.getProvincesList("0");
		for (Map<String,Object> provinceMap : provinceList) {
			String code = ObjectUtils.toString(provinceMap.get("code"));
			String name = ObjectUtils.toString(provinceMap.get("name"));
			String areaId = ObjectUtils.toString(provinceMap.get("areaId"));

			area0Map.put(code, name);

			List<Map<String,Object>> cityList = this.initDataDao.getProvincesList(areaId);
			List<Object> returnCityList = new ArrayList<>();
			for (Map<String,Object> cityMap : cityList) {
				List<String> singleCityList = new ArrayList<>();
				singleCityList.add(ObjectUtils.toString(cityMap.get("name")));
				singleCityList.add(ObjectUtils.toString(cityMap.get("code")));
				returnCityList.add(singleCityList);

				List<Map<String,Object>> areaList = this.initDataDao.getProvincesList(ObjectUtils.toString(cityMap.get("areaId")));
				List<Object> returnAreaList = new ArrayList<>();
				for (Map<String,Object> areaMap : areaList) {
					List<String> singleAreaList = new ArrayList<>();
					singleAreaList.add(ObjectUtils.toString(areaMap.get("name")));
					singleAreaList.add(ObjectUtils.toString(areaMap.get("code")));
					returnAreaList.add(singleAreaList);
				}
				area2Map.put(ObjectUtils.toString(cityMap.get("code")), returnAreaList);
			}
			area1Map.put(ObjectUtils.toString(provinceMap.get("code")), returnCityList);
		}

		returnMap.put("area0", area0Map);
		returnMap.put("area1", area1Map);
		returnMap.put("area2", area2Map);

		return returnMap;
	}

	/**
	 * 读取数据库封装工作类型、单位性质、职业关联信息
	 * @author YM10159
	 */
	public Map<String, Object> packageWorkTypeRelation(String enumBelongFlag) {
		Map<String,Object> returnMap = new HashMap<>();
		Map<String,Object> firstReturnMap = new HashMap<>();
		Map<String,Object> secondReturnMap = new HashMap<>();
		Map<String,Object> thirdReturnMap = new HashMap<>();

		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("enumBelongFlag", enumBelongFlag);
		paramsMap.put("level", "1");
		paramsMap.put("parentFirstId", "");
		paramsMap.put("parentSecondId", "");
		List<Map<String,Object>> firstList = this.initDataDao.getWorkTypeRelation(paramsMap);

		for (Map<String,Object> firstMap : firstList) {
			String code = ObjectUtils.toString(firstMap.get("code"));
			String name = ObjectUtils.toString(firstMap.get("name"));
			String id = ObjectUtils.toString(firstMap.get("id"));
			//封装一级节点
			firstReturnMap.put(code, name);

			paramsMap.put("level", "2");
			paramsMap.put("parentFirstId", id);
			paramsMap.put("parentSecondId", "");
			List<Map<String,Object>> secondList = this.initDataDao.getWorkTypeRelation(paramsMap);
			if(CollectionUtils.isNotEmpty(secondList)){
				List<Object> tempSecondList = new ArrayList<>();
				for (Map<String,Object> secondMap : secondList) {
					List<String> singleCorpStructureList = new ArrayList<>();
					singleCorpStructureList.add(secondMap.get("name").toString());
					singleCorpStructureList.add(secondMap.get("code").toString());
					tempSecondList.add(singleCorpStructureList);

					paramsMap.put("level", "3");
					paramsMap.put("parentFirstId", id);
					paramsMap.put("parentSecondId", secondMap.get("id"));
					List<Map<String,Object>> thirdList = this.initDataDao.getWorkTypeRelation(paramsMap);
					if(CollectionUtils.isNotEmpty(thirdList)){
						List<Object> tempThirdList = new ArrayList<>();
						for (Map<String,Object> thirdMap : thirdList) {
							List<String> singleProfessionTypeList = new ArrayList<>();
							singleProfessionTypeList.add(ObjectUtils.toString(thirdMap.get("name")));
							singleProfessionTypeList.add(ObjectUtils.toString(thirdMap.get("code")));
							tempThirdList.add(singleProfessionTypeList);
						}
						thirdReturnMap.put(code + "_" + secondMap.get("code").toString(), tempThirdList);
					}
				}
				secondReturnMap.put(code, tempSecondList);
			}
		}

		returnMap.put("firstList", firstReturnMap);
		returnMap.put("secondList", secondReturnMap);
		if(!thirdReturnMap.isEmpty()){
			returnMap.put("thirdList", thirdReturnMap);
		}

		return returnMap;
	}
	
	/**
	 * 封装职务下拉框数据
	 * @return 返回职务列表
	 */
	public Map<String, Object> packagePostRelation(String enumBelongFlag) {
		Map<String,Object> secondReturnMap = new HashMap<>();

		Map<String,Object> paramsMap = new HashMap<>();
		paramsMap.put("enumBelongFlag", enumBelongFlag);
		paramsMap.put("level", "1");
		paramsMap.put("parentFirstId", "");
		paramsMap.put("parentSecondId", "");
		List<Map<String,Object>> firstList = this.initDataDao.getWorkTypeRelation(paramsMap);

		for (Map<String,Object> firstMap : firstList) {
			String code = ObjectUtils.toString(firstMap.get("code"));
			String id = ObjectUtils.toString(firstMap.get("id"));

			paramsMap.put("level", "2");
			paramsMap.put("parentFirstId", id);
			paramsMap.put("parentSecondId", "");
			List<Map<String,Object>> secondList = this.initDataDao.getWorkTypeRelation(paramsMap);
			if(CollectionUtils.isNotEmpty(secondList)){
				List<Object> tempSecondList = new ArrayList<>();
				for (Map<String,Object> secondMap : secondList) {
					List<String> singleCorpStructureList = new ArrayList<>();
					singleCorpStructureList.add(secondMap.get("name").toString());
					singleCorpStructureList.add(secondMap.get("code").toString());
					tempSecondList.add(singleCorpStructureList);
				}
				secondReturnMap.put(code, tempSecondList);
			}
		}

		return secondReturnMap;
	}
	
	/**
	 * 从文件中读取生成好的省市区数据
	 * @author YM10159
	 */
	public Map<String,Object> getProvicesList() {
		InputStream is = getClass().getResourceAsStream("/mybatis/mapper/app/area.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String lineStr = "";
		try {
			while ((lineStr = br.readLine()) != null)
				sb.append(lineStr.replaceAll("\\s", ""));
		} catch (Exception e) {
			log.error("APP读取文件省市区数据异常：", e);
		}finally {
			try {
				is.close();
				br.close();
			} catch (IOException e) {}
		}
		JSONObject json = new JSONObject(sb.toString());
		return json.toMap();
	}
	
	public static void main(String args[]){
		String regex = "^((?!1{11}).)*$";
		String str = "11111111112";
		System.out.println(str.matches(regex));
	}
}



