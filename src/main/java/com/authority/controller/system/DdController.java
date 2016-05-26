package com.authority.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.authority.annotation.SystemLog;
import com.authority.controller.index.BaseController;
import com.authority.entity.DdFormMap;
import com.authority.exception.SystemException;
import com.authority.mapper.DdMapper;
import com.authority.plugin.PageView;
import com.authority.util.Common;
/**
 * 数据字典控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dd/")
public class DdController extends BaseController{
	
	@Inject
	private DdMapper ddMapper;
	
	/**
	 * 分页返回页面
	 * 获取权限buttom
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String listUI(Model model) throws Exception{
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dd/list";
	}
	
	/**
	 * 获取分页数据
	 * @param pageNow
	 * @param pageSize
	 * @param column
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
			String pageSize,String column,String sort) throws Exception {
		DdFormMap ddFormMap  = getFormMap(DdFormMap.class);
		ddFormMap = toFormMap(ddFormMap, pageNow, pageSize, ddFormMap.getStr("orderby"));
		pageView.setRecords(ddMapper.findByPage(ddFormMap));
		return pageView;
	}
	
	/**
	 * 获取添加页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) throws Exception{
		return Common.BACKGROUND_PATH + "/system/dd/add";
	}
	
	/**
	 * 添加数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="字典管理",methods="字典管理-新增字典")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception{
		try {
			DdFormMap ddFormMap = getFormMap(DdFormMap.class);
			ddMapper.addEntity(ddFormMap);
		} catch (Exception e) {
			throw new SystemException("新增数据字典失败");
		}
		return "success";
	}
	
	/**
	 * 删除数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="字典管理",methods="字典管理-删除数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity() throws Exception{
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			ddMapper.deleteByAttribute("id", id, DdFormMap.class);
		}
		return "success";
	}
	
	/**
	 * 获取修改数据并返回页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editUI")
	public String editEntity(Model model) throws Exception{
		String id = getPara("id");
		if(Common.isNotEmpty(id))
			model.addAttribute("dd", ddMapper.findbyFrist("id", id, DdFormMap.class));
		return Common.BACKGROUND_PATH + "/system/dd/edit";
	}
	
	/**
	 * 修改数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="字典管理",methods="字典管理-修改数据")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity() throws Exception{
		try {
			DdFormMap ddFormMap = getFormMap(DdFormMap.class);
			ddMapper.editEntity(ddFormMap);
		} catch (Exception e) {
			throw new SystemException("修改数据字典异常");
		}
		return "success";
	}

	/**
	 * 验证字典是否重复
	 * 
	 * @param name
	 * @return
	 */
	@ResponseBody
	@RequestMapping("isExist")
	public boolean isExist(String dmlx,String dm) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dmlx", dmlx);
		map.put("dm", dm);
		DdFormMap ddFormMap = ddMapper.isExist(map);
		if (ddFormMap == null) {
			return true;
		} else {
			return false;
		}
	}
}
