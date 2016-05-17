package com.authority.mapper;

import java.util.List;

import com.authority.entity.ResFormMap;
import com.authority.mapper.base.BaseMapper;

public interface ResourcesMapper extends BaseMapper {
	/**
	 * 根据节点关系获取
	 * @param map
	 * @return
	 */
	public List<ResFormMap> findChildlists(ResFormMap map);

	/**
	 * 获取该用户的权限
	 * @param map
	 * @return
	 */
	public List<ResFormMap> findRes(ResFormMap map);

	/**
	 * 修改资源顺序
	 * @param map
	 */
	public void updateSortOrder(List<ResFormMap> map);
	
	/**
	 * 根据账号Id获取该用户的权限 
	 * @param userId
	 * @return
	 */
	public List<ResFormMap> findUserResourcess(String userId);
	
	/**
	 * 只对角色关关联资源进行操作
	 * 根据角色id获取资源
	 * @param roleId
	 * @return
	 */
	public List<ResFormMap> findRoleResorucess(Integer roleId);
	
}
