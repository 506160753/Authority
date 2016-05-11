package com.authority.mapper;

import java.util.List;

import com.authority.entity.UserFormMap;
import com.authority.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
