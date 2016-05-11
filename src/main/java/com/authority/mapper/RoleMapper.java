package com.authority.mapper;

import java.util.List;

import com.authority.entity.RoleFormMap;
import com.authority.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
