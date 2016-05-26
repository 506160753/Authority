package com.authority.mapper;

import java.util.Map;
import com.authority.entity.DdFormMap;
import com.authority.mapper.base.BaseMapper;

public interface DdMapper extends BaseMapper{
	/**
	 * 判断dmlx,dm 是否唯一
	 * @param map
	 * @return
	 */
	public DdFormMap isExist(Map<String, Object> map);
	
}
