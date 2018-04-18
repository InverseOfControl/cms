package com.ymkj.cms.web.boss.controller.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymkj.base.core.web.controller.BaseController;
import com.ymkj.cms.biz.api.vo.request.master.ReqRedisManagerVO;
import com.ymkj.cms.biz.api.vo.response.master.ResRedisManagerVO;
import com.ymkj.cms.web.boss.common.RedisUtil;
import com.ymkj.cms.web.boss.common.ResponsePage;

@Controller
@RequestMapping("redisManager")
public class RedisManagerController extends BaseController {

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
    private RedisTemplate<Serializable, Object> redisTemplate; 

	@RequestMapping("view")
	public String view() {
		return "master/redisManager/redisManagerList";
	}
	/**
	 * 查看缓存数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "listPage")
	@ResponseBody
	public ResponsePage<ResRedisManagerVO> listPage(ReqRedisManagerVO reqRedisManagerVO) {
		String name = reqRedisManagerVO.getName();
		if(name == null){
			name = "";
		}
		Set<Serializable> keys = redisUtil.getKeys(name);
		List<ResRedisManagerVO> rows = new ArrayList<ResRedisManagerVO>();
		for (Serializable key : keys) {
			ResRedisManagerVO vo = new ResRedisManagerVO();
			vo.setKey(key.toString());
			vo.setName(key.toString());
			rows.add(vo);
		}
		ResponsePage<ResRedisManagerVO> pageList = new ResponsePage<ResRedisManagerVO>();
		pageList.setRows(rows);
		pageList.setTotal(rows.size());
		
		return pageList;
	}
	/**
	 * redis数据移除
	 * 
	 * @return
	 */
	@RequestMapping(value = "remove")
	@ResponseBody
	public Map<String, Object> remove(ReqRedisManagerVO reqRedisManagerVO) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		
		String[] keyList = reqRedisManagerVO.getKeys().split(",");
		for (String key : keyList) {
			redisUtil.remove(key);
		}
		hashMap.put("isSuccess", true);
		return hashMap;
	}

}
