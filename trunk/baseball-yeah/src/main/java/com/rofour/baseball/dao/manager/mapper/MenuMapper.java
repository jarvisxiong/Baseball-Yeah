/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.rofour.baseball.controller.model.Ztree;
import com.rofour.baseball.controller.model.manager.MenuInfo;
import com.rofour.baseball.dao.manager.bean.MenuBean;

/**
 * @ClassName: TbMenuMapper
 * @Description: 菜单数据库操作接口
 * @author xl
 * @date 2016年3月26日 下午1:34:48
 *
 */

@Named("tbMenuMapper")
public interface MenuMapper {

	/**
	 * 
	 * @Description: 按主键ID删除菜单
	 * @param menuId
	 * @return 删除的数量
	 */
	int deleteByPrimaryKey(Long menuId);

	/**
	 * 
	 * @Description: 新增菜单
	 * @param record
	 * @return int
	 */
	int insertSelective(MenuBean record);

	/**
	 * 
	 * @Description: 按主键查询菜单
	 * @param menuId
	 * @return MenuBean
	 */
	MenuBean selectByPrimaryKey(Long menuId);

	/**
	 * 
	 * @Description:  更新菜单
	 * @param record
	 * @return 更新的数量
	 */
	int updateByPrimaryKeySelective(MenuBean record);

	/**
	 * 
	 * @Description:  查询所有菜单列表
	 * @return List<MenuBean>
	 */
	List<MenuBean> selectAll(MenuBean bean);

	/**
	 *
	 * @Description:  查询所有菜单列表包含角色
	 * @return List<MenuBean>
	 */
	List<MenuBean> selectAllForRole();


	/**
	 * 
	 * @Description: 同一父菜单下菜单标题是否已存在
	 * @param map
	 * @return 重复的数量
	 */
	int isMenuExistUnderSameParent(Map<String, Object> map);

	/**
	 * 
	 * @Description:  验证菜单是否存在
	 * @param menuId
	 * @return 重复的数量
	 */
	int isMenuExist(Long menuId);
	/**
	 * 
	 * @Description:根据条件查询总数
	 * @param bean
	 * @return
	 */
	Integer getTotal(MenuBean bean);
	
	/**
	 * 
	 * @Description:  查询所有菜单列表
	 * @return List<MenuBean>
	 */
	List<Ztree> getZtree(MenuBean bean);
	
	/**
	 * @Method: getUserMenu
	 * @Description: 获取角色已有的menu
	 * @param @param bean
	 * @param @return    参数
	 * @return List<MenuInfo>    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月11日 下午9:05:33 
	 **/
	    
	List<MenuInfo> getRoleMenu(Long roleId);
	
	/**
	 * @Method: selectAllMenuId
	 * @Description: 获取所有menuId
	 * @param @return    参数
	 * @return List<Long>    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月18日 下午9:00:43 
	 **/
	    
	List<Long> selectAllMenuId();
	/**
	 * 
	 * @Description:查找全部非叶子 菜单
	 * @return
	 */
	List<MenuInfo> selectAllParent();
}