/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.Ztree;
import com.rofour.baseball.controller.model.manager.MenuInfo;
import com.rofour.baseball.dao.manager.bean.MenuBean;

/**
 * @ClassName: TbMenuService
 * @Description: 管理中心--菜单服务接口
 * @author xl
 * @date 2016年3月26日 下午1:47:51
 *
 */

public interface MenuService {

	/**
	 * @Description: 按菜单ID删除菜单
	 * @param menuId
	 * @return
	 */


	int deleteByPrimaryKey(Long menuId,HttpServletRequest request);

	/**
	 * @Description:新增菜单
	 * @param record
	 * @return
	 */

	ResultInfo insertSelective(MenuInfo record,HttpServletRequest request);

	/**
	 * @Description: 按主键ID查询菜单
	 * @param menuId
	 * @return
	 */

	MenuInfo selectByPrimaryKey(Long menuId);

	/**
	 * @Description: 更新菜单信息
	 * @param record
	 * @return
	 */

	ResultInfo updateByPrimaryKeySelective(MenuInfo record,HttpServletRequest request);

	/**
	 * @Description: 获取所有菜单
	 * @return
	 */

	List<MenuBean> selectAll(MenuBean bean);


	List<MenuBean> selectAllForRole();
	/**
	 * 
	 * @Description: 查询总数
	 * @param bean
	 * @return
	 */
	Integer getTotal(MenuBean bean);

	/**
	 * @Method: getZtree
	 * @Description: 获取菜单树
	 * @param @param bean
	 * @param @return    参数
	 * @return List<Ztree>    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月11日 下午8:23:28 
	 **/
	    
	List<Ztree> getZtree(MenuBean bean);

	/**
	 * @Method: getUserMenu
	 * @Description: 获取角色的菜单
	 * @param @param roleId
	 * @param @return    参数
	 * @return List<MenuInfo>    返回类型
	 * @throws
	 * @author ZhangLei
	 * @date 2016年5月11日 下午9:08:44 
	 **/
	    
	List<MenuInfo> getRoleMenu(Long roleId);
	/**
	 * 
	 * @Description:查找全部非叶子 菜单
	 * @return
	 */
	List<MenuInfo> selectAllParent();

	/**
	 * 获取二级菜单
	 * @param userName
	 * @param menuId
	 * @return
	 * @author wuzhiquan
	 */
    List<MenuBean> getChlidrenMenu(String userName, String menuId);
}