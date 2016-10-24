/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.RolePermissionInfo;
import com.zhiduan.axp.dao.manager.bean.RolePermissionBean;
import com.zhiduan.axp.dao.manager.mapper.RolePermissionMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.RolePermissionService;

/**
* @ClassName: RolePermissionServiceImpl
* @Description: 角色权限管理实现业务层
* @author ZhangLei
* @date 2016年5月5日 下午6:46:47 
*
*/
    
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	@Qualifier("rolePermissionMapper")
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * @Description: 添加角色权限业务
	 * @param tb
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#addRolePermission(com.zhiduan.axp.idl.managecenter.service.entity.RolePermissionInfo)
	 */
	public int addRolePermission(RolePermissionInfo tb) {
		if (tb.getRoleId() == null || tb.getMenuId() == null) {
			throw new BusinessException("111");
		}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("roleId", tb.getRoleId());
		map.put("menuId", tb.getMenuId());
		if (isRolePermissionExists(map)) {
			throw new BusinessException("01011");
		}
		RolePermissionBean record = new RolePermissionBean();
		BeanUtils.copyProperties(tb, record);
		int result = rolePermissionMapper.insert(record);
		return result;
	}

	/**
	 * @Description: 删除角色权限业务
	 * @param rolePermissionId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#deleteRolePermission(java.lang.Long)
	 */
	public int deleteRolePermission(Long rolePermissionId) {
		int result = rolePermissionMapper.deleteByPrimaryKey(rolePermissionId);
		if (result == 0) {
			throw new BusinessException("01010");
		}
		return result;
	}

	/**
	 * @Description: 修改角色权限业务
	 * @param tb
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#updateRolePermission(com.zhiduan.axp.idl.managecenter.service.entity.RolePermissionInfo)
	 */
	public int updateRolePermission(RolePermissionInfo tb) {
		if (tb.getRolePermissionId() == null 
				|| tb.getRoleId() == null
				|| tb.getMenuId() == null) {
			throw new BusinessException("111");
		}
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("roleId", tb.getRoleId());
		map.put("menuId", tb.getMenuId());
		map.put("rolePermissionId", tb.getRolePermissionId());
		if (isRolePermissionExists(map)) {
			throw new BusinessException("01011");
		}
		RolePermissionBean record = new RolePermissionBean();
		BeanUtils.copyProperties(tb, record);
		int result = rolePermissionMapper.updateByPrimaryKey(record);
		if(result == 0){
			throw new BusinessException("01010");
		}
		return result;
	}

	/**
	 * @Description: 查看角色权限业务
	 * @param rolePermissionId
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#selectRolePermission(java.lang.Long)
	 */
	public RolePermissionInfo selectRolePermission(Long rolePermissionId) {
		RolePermissionBean record = rolePermissionMapper.selectByPrimaryKey(rolePermissionId);
		if(record == null){
			throw new BusinessException("01010");
		}
		RolePermissionInfo tb = new RolePermissionInfo();
		BeanUtils.copyProperties(record, tb);
		return tb;
	}

	/**
	 * @Description: 根据角色ID查询所有的角色权限
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#selectAll()
	 */
	public List<RolePermissionBean> selectAll() {
		List<RolePermissionBean> dataList = rolePermissionMapper.selectAll();
		if(dataList == null || dataList.isEmpty()){
			throw new BusinessException("01010");
		}
		return dataList;
	}
	
	@Override
	public Integer getTotal() {
		Integer i=rolePermissionMapper.getTotal();
		return i;
	}
	/**
	 * @Description: 校验角色权限是否存在
	 * @param map
	 * @return
	 * @see com.zhiduan.axp.idl.managecenter.service.RolePermissionService#isRolePermissionExists(java.util.Map)
	 */
	public boolean isRolePermissionExists(Map<String, Long> map) {
		int count = rolePermissionMapper.isRolePermissionExists(map);
		return count > 0;
	}

	
	@Override
	public List<RolePermissionBean> selectRolePermissionByUserName(String userName) {
		List<RolePermissionBean> permissions = rolePermissionMapper.selectAllPermissionByUserName(userName);
		if(permissions == null){
			throw new BusinessException("01010");
		}
		return permissions;
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResultInfo batchAdd(List<RolePermissionInfo> list) {
		if(list == null || list.isEmpty()){
			throw new BusinessException("111");
		}
		for (RolePermissionInfo permission : list) {
			if(permission.getRoleId() == null || permission.getMenuId() == null){
				throw new BusinessException("111");
			}
		}
		//去重
		Set<RolePermissionInfo> set = new HashSet<>(list);
		list.clear();
		list.addAll(set);
		
		List<RolePermissionBean> dataList = new ArrayList<RolePermissionBean>();
		rolePermissionMapper.deleteByRoleId(list.get(0).getRoleId());
		for (RolePermissionInfo permissionInfo : list) {
			RolePermissionBean record = new RolePermissionBean();
			BeanUtils.copyProperties(permissionInfo, record);
			dataList.add(record);
		}
		int num = rolePermissionMapper.batchInsert(dataList);
		if (num == 0) {
			throw new BusinessException("01012");
		}
		return new ResultInfo(0, "", "批量更新角色权限信息成功");
	}
}
