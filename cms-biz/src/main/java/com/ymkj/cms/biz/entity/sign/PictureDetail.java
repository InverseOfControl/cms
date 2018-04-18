package com.ymkj.cms.biz.entity.sign;

/**
 * 封装图片查询接口返回参数
 * 
 * @author CHENTW
 *
 */
public class PictureDetail {
  private String id; // 标识
  private String imgname; // 图片名称
  private String savename; // 保存名称
  private String subclassSort; // 具体类型如：身份证件、工资证明、房产证明
  private String uptime; // 上传日期
  private String appNo; // 申请件编号
  private String sysName; // 系统名称
  private String url; // 文件访问地址
  private String ifWaste; // 作废（Y|是 N|否）
  private String ifPatchBolt; // 补件（Y|是 N|否）
  private String creator; // 创建人
  private String createJobnum; // 创建人工号
  private String createTime; // 创建时间
  private String modifier; // 修改人
  private String modifiedJobnum; // 修改人工号
  private String modifierTime; // 修改人时间
  private String sortsid; // 当前排序号
  private String MaxSortsid; // 最大排序号

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImgname() {
    return imgname;
  }

  public void setImgname(String imgname) {
    this.imgname = imgname;
  }

  public String getSavename() {
    return savename;
  }

  public void setSavename(String savename) {
    this.savename = savename;
  }

  public String getSubclassSort() {
    return subclassSort;
  }

  public void setSubclassSort(String subclassSort) {
    this.subclassSort = subclassSort;
  }

  public String getUptime() {
    return uptime;
  }

  public void setUptime(String uptime) {
    this.uptime = uptime;
  }

  public String getAppNo() {
    return appNo;
  }

  public void setAppNo(String appNo) {
    this.appNo = appNo;
  }

  public String getSysName() {
    return sysName;
  }

  public void setSysName(String sysName) {
    this.sysName = sysName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getIfWaste() {
    return ifWaste;
  }

  public void setIfWaste(String ifWaste) {
    this.ifWaste = ifWaste;
  }

  public String getIfPatchBolt() {
    return ifPatchBolt;
  }

  public void setIfPatchBolt(String ifPatchBolt) {
    this.ifPatchBolt = ifPatchBolt;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getCreateJobnum() {
    return createJobnum;
  }

  public void setCreateJobnum(String createJobnum) {
    this.createJobnum = createJobnum;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getModifier() {
    return modifier;
  }

  public void setModifier(String modifier) {
    this.modifier = modifier;
  }

  public String getModifiedJobnum() {
    return modifiedJobnum;
  }

  public void setModifiedJobnum(String modifiedJobnum) {
    this.modifiedJobnum = modifiedJobnum;
  }

  public String getModifierTime() {
    return modifierTime;
  }

  public void setModifierTime(String modifierTime) {
    this.modifierTime = modifierTime;
  }

  public String getSortsid() {
    return sortsid;
  }

  public void setSortsid(String sortsid) {
    this.sortsid = sortsid;
  }

  public String getMaxSortsid() {
    return MaxSortsid;
  }

  public void setMaxSortsid(String maxSortsid) {
    MaxSortsid = maxSortsid;
  }

}
