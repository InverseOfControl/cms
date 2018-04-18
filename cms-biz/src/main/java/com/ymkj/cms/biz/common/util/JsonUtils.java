package com.ymkj.cms.biz.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsonUtils {
	/**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private JsonUtils() {
    }

    public static String encode(Object obj) {
        try {
        
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        } catch (JsonMappingException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        } catch (IOException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        }
        return null;
    }
    
    
    public static String encode2DayFormat(Object obj) {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	objectMapper.setDateFormat(sdf);
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        } catch (JsonMappingException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        } catch (IOException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        }
        return null;
    }


    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("decode(String, Class<T>)", e);
        } catch (JsonMappingException e) {
            logger.error("decode(String, Class<T>)", e);
        } catch (IOException e) {
            logger.error("decode(String, Class<T>)", e);
        }
        return null;
    }
    
    
    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode2DayFormat(String json, Class<T> valueType) {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	objectMapper.setDateFormat(sdf);
            return objectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("decode(String, Class<T>)", e);
        } catch (JsonMappingException e) {
            logger.error("decode(String, Class<T>)", e);
        } catch (IOException e) {
            logger.error("decode(String, Class<T>)", e);
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param jsonTypeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error("decode(String, JsonTypeReference<T>)", e);
        } catch (JsonMappingException e) {
            logger.error("decode(String, JsonTypeReference<T>)", e);
        } catch (IOException e) {
            logger.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }
	
	/*public static void main(String[] args) {
		APPCardLoanInfoEntity appCard =new APPCardLoanInfoEntity();
		//appCard.setAppNo(appNo);
		String testStr="\"tmAppCarInfo\": {\"carLoan\": null,"+
		        "\"checkId\": 8201851,"+
		        "\"monthPaymentAmt\": 0,"+
		        "\"carTypr\": \"\","+
		        "\"operationStatus\": \"\","+
		        "\"carLoanTerm\": 0,"+
		        "\"carSeat\": \"\","+
		        "\"org\": \"000000000000\","+
		        "\"updateDate\": \"2015-07-31 11:49:22\","+
		        "\"localPlate\": null,"+
		        "\"appNo\": \"20150731100000005401\","+
		        "\"createUser\": \"00212305\","+
		        "\"transferDate\": \"\","+
		        "\"nakedCarPrice\": 0,"+
		        "\"carBuyDate\": \"\","+
		        "\"createDate\": \"2015-07-31 10:45:53\","+
		        "\"carBuyPrice\": 0,"+
		        "\"jpaVersion\": 2,"+
		        "\"updateUser\": \"admin\"}";	
		
		ObjectMapper mapper = new ObjectMapper();
		//解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
		//解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		HashMap jsonMap =null;
		try {
			 jsonMap = mapper.readValue(testStr,HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set set=	jsonMap.keySet();
		for (Object object : set) {
			System.err.println(object.toString());
			
		}
	}*/
	

}

