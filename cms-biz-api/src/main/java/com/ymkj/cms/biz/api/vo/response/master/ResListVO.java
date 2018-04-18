package com.ymkj.cms.biz.api.vo.response.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ymkj.base.core.biz.api.message.Response;

public class ResListVO<T extends Serializable> extends Response<T> implements Serializable{
	
	private static final long serialVersionUID = 8004383310265243848L;
	
	private List<T> paramList; 
	
	/**
	 * sizeCount用来标示内部数组究竟有没有实际的内容，如果=0，则标示没有，所以使用时先判断 getSize() > 0 再取对象
	 */
	private int sizeCount;
	/**
	 * 必须提供一个没有参数的构造函数，因为如果补提供，在调用方调用Executer接口时，会报一个param为NullPointerException的异常
	 * 大胆猜测是 这里通过dubbo返回的ResListVO是通过JVM序列化重新构建的一个实体类，不是Executer接口的实现类返回的实体类，所以会报这个异常
	 */
	public ResListVO(){}
	
	public ResListVO(List<T> param) {
		if(param != null && param.size() > 0)  {
			this.paramList = new ArrayList<T>(param);
			this.sizeCount = param.size();
		}
		else {
			this.paramList = new ArrayList<T>();
			this.sizeCount = 0;
		}
	}
	
	public void setParamList(List<T> param) {
		if(param != null && param.size() > 0) {
			if(sizeCount > 0) {
				this.paramList.addAll(param);
				this.sizeCount += param.size();
			}
			else {
				this.paramList = new ArrayList<T>(param);
				this.sizeCount = param.size();
			}
		}
	}
	
	public List<T> getCollections() {
		if(paramList == null) return null;
		return this.paramList;
	}
	
	public int getSize() {
		return this.sizeCount;
	}

}
