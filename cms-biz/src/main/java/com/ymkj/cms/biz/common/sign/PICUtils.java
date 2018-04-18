package com.ymkj.cms.biz.common.sign;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.ymkj.cms.biz.common.util.BmsLogger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 包商银行工具类
 * Created by YM10140 on 2017/6/22.
 */
public class PICUtils {

    private static final Log log = LogFactory.getLog(PICUtils.class);
    public static void main(String[] args) throws Exception {
//        String content = "//pic.ezendai.com:8081/pic-app/file/cfs/201708088EA36E/B/fdf0d69e83b44f5d8b693b6984bf84d2.jpg";
//        String pwd = "7543210987654321";
//        String ss = encryptAES(content, pwd);
//        System.out.println(ss);
//        String sss = java.net.URLEncoder.encode(ss, "utf-8");
//        System.out.println();
//        System.out.println(sss);
//        imgToPdf("http://172.16.230.50:8080/pic-app/file/aps/20170628191539964581/R/87a95f20ccec4835a1585ca38596e36c.jpg", "D://contractHome//哈哈.pdf");
//        System.out.println(ConfigTools.encrypt("bms,123"));
//        String[] files = { "D://contractHome//phb.pdf", "D://contractHome//phb2.pdf"};
//        String savepath = "D://contractHome//finalTest.pdf";
//        morePdfTopdf(files, savepath);
    }

    /**
     * 图片类型
     *
     * @param code
     * @return
     */
    public static String getImageType(String code,int flag,String patchType) {
        switch (code) {
            case "S1"://贷款合同 个人消费贷款合同扫描版原件（PDF格式）
                return "04";
            case "S10":
                //03 手持身份证照片
                //05 借款人手持个人消费贷款合同正面照片
                if(patchType!=null){
                    if(patchType.contains("03") && patchType.contains("05")){
                        if (flag==0) {
                            return "03";
                        } else {
                            return "05";
                        }
                    }else if(patchType.contains("03") && !patchType.contains("05")){
                        return "03";
                    }else if(patchType.contains("05") && !patchType.contains("03")){
                        return "05";
                    }
                }
                //非补件
                if (flag==0) {
                    return "03";
                } else {
                    return "05";
                }
            case "S41": //身份证正面照片
                return "01";
            case "S42"://身份证反面照片
                return "02";
            case "S9"://还款代扣授权书扫描版原件照片
                return "07";
            case "S13"://机构申请表PIC4 S13
                return "15";
            case "Q"://机构央行征信查询授权书
                return "06";
            case "R"://机构第三方数据查询授权书
                return "08";
            case "B"://身份证明
                return "14";
            default:
                return "00";
        }
    }

    /**
     * 获取需要补件的文件夹
     * @param patchType
     * @return
     */
    public static JSONArray getPatchFolder(String patchType){
        JSONArray patchList=new JSONArray();
        Set<String> resultSet=new HashSet<>();
        if(patchType!=null) {
            String[] split = patchType.split("\\|");
            for (String str : split) {
                if ("01".equals(str)) {
                    resultSet.add("S41");
                } else if ("02".equals(str)) {
                    resultSet.add("S42");
                } else if ("03".equals(str) || "05".equals(str)) {
                    resultSet.add("S10");
                } else if ("04".equals(str)) {
                    resultSet.add("S1");
                } else if ("07".equals(str)) {
                    resultSet.add("S9");
                } else if ("15".equals(str)) {
                    resultSet.add("S13");
                }else if("06".equals(str)){
                    resultSet.add("Q");
                }else if("14".equals(str)){
                    resultSet.add("B");
                }else if("08".equals(str)){
                    resultSet.add("R");

                }
            }
        }
        for (String s : resultSet) {
            patchList.put(s);
        }
        return patchList;
    }

    /**
     * 获取需要推送给包银的文件夹
     * @return
     */
    public static JSONArray getPushFolder(){
        JSONArray patchList=new JSONArray();
        patchList.put("S1");
        patchList.put("S9");
        patchList.put("S10");
        patchList.put("S41");
        patchList.put("S13");
        patchList.put("S42");
        return patchList;
    }

    /**
     * 获取文件夹对应的中文名字
     * @param code
     * @return
     */
    public static String getFoldName(String code){
        if("S41".equals(code)){
            return "身份证正面";
        }else if("S42".equals(code)){
            return "身份证反面";
        }else if("S10".equals(code)){
            return "面签照片";
        }else if("S1".equals(code)){
            return "贷款合同";
        }else if("S9".equals(code)){
            return "还款代扣授权书_机构";
        }else if("S13".equals(code)){
            return "机构申请表";
        }else if("Q".equals(code)){
            return "机构央行征信查询授权书";
        }else if("B".equals(code)){
            return "身份证明";
        }else if("R".equals(code)){
            return "机构第三方数据查询授权书";
        }else {
            return "所传code对应文件夹不存在！";
        }
    }
    //01:身份证正面照片，02身份证反面照片，03手持身份证照片04个人消费贷款合同扫描版原件（PDF格式），
    //05借款人手持个人消费贷款合同正面照片。06个人征信查询授权协议扫描版原件照片,07还款代扣授权书扫描版原件照片,
    //08个人信息使用和第三方机构数据查询授权书扫描版原件照片 14身份证复印件 15 申请表

    /**
     * 获取缺失的图片信息
     * @param array
     * @return
     */
    public static String getMissImageType(JSONArray array) {
        List<String> list = new ArrayList<>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("07");
        list.add("15");
        List<String> resultList=new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String imageType = jsonObject.getString("imageType");
            resultList.add(imageType);
        }
        list.removeAll(resultList);
        StringBuffer sb = new StringBuffer();
        if(list.size()>0){
            for (String img : list) {
                switch (img) {
                    case "04":
                        sb.append("个人消费贷款合同扫描版原件，");
                        break;
                    case "03":
                        sb.append("手持身份证照片，");
                        break;
                    case "05":
                        sb.append("借款人手持个人消费贷款合同正面照片，");
                        break;
                    case "01":
                        sb.append("身份证正面照片，");
                        break;
                    case "02":
                        sb.append("身份证反面照片，");
                        break;
                    case "07":
                        sb.append("还款代扣授权书扫描版原件照片，");
                        break;
                    default:
                        sb.append("机构申请表，");
                        break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 过滤掉不需要推送给包银的附件
     *
     * @param listMap
     */
    public static JSONArray baoYinPicUpload(JSONArray listMap) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < listMap.length(); i++) {
            JSONObject ob = listMap.getJSONObject(i);
            //贷款合同先生成pdf到应用服务器，然后上传到pic系统，返回地址传给包银
            String code = ob.getString("code");
            if ( "S1".equals(code) || "S10".equals(code) || "S41".equals(code) || "S42".equals(code) || "S9".equals(code) || "S13".equals(code)) {
                jsonArray.put(ob);
            }
        }
        return jsonArray;
    }

    /**
     * 包银渠道电子签章校验
     *
     * @param listMap
     */
    public static JSONArray baoYinElectronicSignature(JSONArray listMap) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < listMap.length(); i++) {
            JSONObject ob = listMap.getJSONObject(i);
            //贷款合同先生成pdf到应用服务器，然后上传到pic系统，返回地址传给包银
            String code = ob.getString("code");
            if ( "S11".equals(code) || "S8".equals(code) || "S2".equals(code)) {
                jsonArray.put(ob);
            }
        }
        return jsonArray;
    }
    /**
     * @Description:文件code转换成中文</p>
     * @uthor YM10159
     * @date 2017年7月10日 下午7:18:14
     * @param code 文件夹编码
     */
    public static String baoYinPicCodeFormat(String code){
        String codeCN = "";
        if(code.equals("S11")) codeCN = "个人借款咨询服务风险基金协议";
        if(code.equals("S8")) codeCN = "个人借款咨询服务协议";
        if(code.equals("S2")) codeCN = "温馨还款提示函";
        return codeCN;
    }

    /**
     * ("数据加密 plainTextData要加密的字符串")
     *
     * @param plainTextData
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptAES(String plainTextData, String key) throws Exception {
        // 加密数据
        Cipher cipher = Cipher.getInstance("AES");
        log.info("加密字符串："+plainTextData);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        byte[] encryptedData = cipher.doFinal(plainTextData.getBytes("UTF-8"));
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * ("数据解密 encryptedData要解密的字符串")
     *
     * @param encryptedData
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptAES(String encryptedData, String key) throws Exception {

        // 解密数据
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        byte[] decryptedData = cipher.doFinal(Base64.decodeBase64(encryptedData));
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 将jsonObject数据转化为map形式
     *
     * @param json
     * @param result
     */
    public static void decodeJSONObject(JSONObject json, Map<String, String> result) {
        try {
            Iterator<String> keys = json.keys();
            JSONObject jo = null;
            JSONArray ja = null;
            Object o;
            String key;
            while (keys.hasNext()) {
                key = keys.next();
                o = json.get(key);
                if (o instanceof JSONObject) {
                    jo = (JSONObject) o;
                    if (jo.keys().hasNext()) {
                        decodeJSONObject(jo, result);
                    } else {
                        System.out.println(key);
                    }
                } else if (o instanceof JSONArray) {
                    ja = (JSONArray) o;
                    if (ja != null && ja.length() > 0) {
                        Object p = ja.get(0);
                        if (p instanceof JSONObject) {
                            decodeJSONObject((JSONObject) p, result);
                        }
                    } else {
                        System.out.println(key);
                    }
                } else {
                    result.put(key, o.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将图片转换成pdf文件
     * imgFilePath 需要被转换的img所存放的位置。 例如imgFilePath="D:\\projectPath\\55555.jpg";
     * pdfFilePath 转换后的pdf所存放的位置 例如pdfFilePath="D:\\projectPath\\test.pdf";
     *
     * @param imageUrl
     * @param pdfFilePath
     * @return
     * @throws IOException
     */
    public static boolean imgToPdf(String imageUrl, String pdfFilePath) {
        log.info("*******************************开始转PDF..."+imageUrl);
        Document document = new Document();
        FileOutputStream fos = null;
        try {
            //防止生成的文件名乱码
            String fileName = new String(pdfFilePath.getBytes(), "UTF-8");
            fos = new FileOutputStream(fileName);
            PdfWriter.getInstance(document, fos);
            // 设置文档的大小
            document.setPageSize(PageSize.A4);
            // 打开文档
            document.open();
            // 读取一个图片
            Image image = Image.getInstance(imageUrl);
            float imageHeight = image.getScaledHeight();
            float imageWidth = image.getScaledWidth();
            int i = 0;
            while (imageHeight > 500 || imageWidth > 500) {
                image.scalePercent(100 - i);
                i++;
                imageHeight = image.getScaledHeight();
                imageWidth = image.getScaledWidth();
            }
            image.setAlignment(Image.ALIGN_CENTER);
            //设置图片的绝对位置
            // image.setAbsolutePosition(0, 0);
            // image.scaleAbsolute(500, 400);
            // 插入一个图片
            document.add(image);
            log.info("*******************************转PDF完成..."+pdfFilePath);
        } catch (DocumentException de) {
            BmsLogger.info(de.getMessage());
        } catch (IOException ioe) {
            BmsLogger.info(ioe.getMessage());
        }
        document.close();
        try {
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * IText实现多个pdf转成一个pdf
     *
     * @param files
     * @param savePath
     */
    public static boolean morePdfTopdf(String[] files, String savePath) {
        log.info("*********5张贷款合同pdf合并为一个pdf文件开始*********："+files.length+"，保存路径："+savePath);
        boolean flag=true;
        String[] sortFiles = new String[5];//排过序的文件目录
        for (int i = 0; i < files.length; i++) {
            if (files[i].endsWith("1.pdf")) {
                sortFiles[0] = files[i];
            } else if (files[i].endsWith("2.pdf")) {
                sortFiles[1] = files[i];
            } else if (files[i].endsWith("3.pdf")) {
                sortFiles[2] = files[i];
            } else if (files[i].endsWith("4.pdf")) {
                sortFiles[3] = files[i];
            } else if (files[i].endsWith("5.pdf")) {
                sortFiles[4] = files[i];
            }
        }
        Document document = null;
        try {
            document = new Document(new PdfReader(sortFiles[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savePath));
            document.open();
            for (int i = 0; i < sortFiles.length; i++) {
                PdfReader reader = new PdfReader(sortFiles[i]);
                int n = reader.getNumberOfPages();//获得总页码
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);//从当前Pdf,获取第j页
                    copy.addPage(page);
                }
            }
            flag=true;
        } catch (Exception e) {
            log.info("贷款合同合并异常：",e);
            e.printStackTrace();
            flag=false;
        }finally {
            if (document != null) {
                document.close();
            }
            return flag;
        }
    }

    /**
     * 获取文件夹的所有文件目录地址
     *
     * @param folder
     * @return
     * @throws IOException
     */
    public static String[] getFiles(String folder) throws IOException {
        log.info("获取指定文件路径下的所有文件：" + folder);
        File _folder = new File(folder);
        String[] filesInFolder;
        if (_folder.isDirectory()) {
            filesInFolder = _folder.list();
            if (filesInFolder.length > 0) {
                for (int i = 0; i < filesInFolder.length; i++) {
                    filesInFolder[i] = folder + "/" + filesInFolder[i];
                }
            }
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path     FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
     */
    public static boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

}
