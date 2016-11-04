/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.rofour.baseball.common.CommonConsistEnum;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.dao.manager.bean.CommisionOrderConfBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.CommisionOrderConfService;

/**
 * @ClassName: CommisionOrderConfController
 * @Description: 提成规则维护控制层
 * @author zdh
 * @date 2016年10月26日 下午5:29:29
 */
@Controller
@RequestMapping(value = "/manage/commisionOrderConf")
public class CommisionOrderConfController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("commisionOrderConfService")
	CommisionOrderConfService commisionOrderConfService;

	/*
	 * 根据条件查询出配置信息
	 */
	@RequestMapping(value = "getCommisionOrderConfList", method = RequestMethod.POST)
	public void getCommisionOrderConfList(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CommisionOrderConfBean bean) {
		if (StringUtils.isBlank(bean.getSort())) {
			bean.setSort("create_date");// 默认以id排序
			bean.setOrder("desc");
		}
		if (StringUtils.equals("costValue", bean.getSort())) {
			bean.setSort("cost_value");// 默认以id排序
		}
		DataGrid<CommisionOrderConfBean> grid = new DataGrid<CommisionOrderConfBean>();
		try {
			List<CommisionOrderConfBean> offList = commisionOrderConfService.getCommisionOrderConfList(bean);
			grid.setRows(offList);
			grid.setTotal(commisionOrderConfService.selectAllCount(bean));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		writeJson(grid, response);
	}
	
	/**
	 * 
	 * @Description: 选择好了角色类型，电话号码，带出姓名，学校
	 * @param roleType 角色类型
	 * @param phone 电话号码
	 * @return
	 */
	@RequestMapping(value = "/getUserInfoByPhone", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getUserInfoByPhone(@RequestParam String roleType,@RequestParam String phone) {
		Map<String, String> map = new HashMap<String, String>();
		CommisionOrderConfBean bean = null;
		if(StringUtils.equals(roleType, CommonConsistEnum.ROLE_STORE.getCode())){
			//商户的话，校验商户tb_user中是否存在，且在商户责任人表中也要存在
			bean = commisionOrderConfService.getStoreUserInfoByPhone(phone);
			if(null != bean){
				if(bean.getState() == 2){
					map.put("userId", String.valueOf(bean.getUserId()));
					map.put("userName", bean.getUserName());
				}else{
					map.put("userId", "");
					map.put("message", "商户状态不是审核通过的状态");
				}
			}else{
				map.put("userId", "");
				map.put("message", "");
			}
		}else{
			//CEO,COO的话校验tb_user中是否存在
			bean = commisionOrderConfService.getNormalUserInfoByPhone(phone,roleType);
			if(null != bean){
				map.put("userId", String.valueOf(bean.getUserId()));
				map.put("userName", bean.getUserName());
				map.put("collegeId", String.valueOf(bean.getCollegeId()));
				map.put("collegeName", bean.getCollegeName());
			}else{
				map.put("userId", "");
				map.put("message", "");
			}
		}
		
		return JSONUtils.toJSONString(map);
	}
	
	/**
	 * 
	 * @Description: 增加一条配置
	 * @param request
	 * @param response
	 * @param bean
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultInfo<Object> add(HttpServletRequest request, HttpServletResponse response, CommisionOrderConfBean bean){
    	int i = 0;
    	//设置创建人
    	try {
    		UserManagerLoginBean user = (UserManagerLoginBean)request.getSession().getAttribute("user");
    		if(null != user){
    			bean.setCreateUser(user.getUserManagerId());
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	bean.setModifyFlag(false);
        if (commisionOrderConfService.isExistSameRecord(bean) > 0) {
            return new ResultInfo<Object>(-1, "0", "同一个用户，角色，订单类型，费用类型的记录已经存在");
        }
        i = commisionOrderConfService.insert(bean);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }
	

	/**
	*
	* @Description: 删选出学校附近的商户
	* @return List<SelectModel>
	*/
	@ResponseBody
	@RequestMapping(value = "/getSupervisorList", method = RequestMethod.GET)
	public List<SelectModel> getSupervisorList(@RequestParam String userId) {
		List<CommisionOrderConfBean> list = commisionOrderConfService.getSupervisorListByCooId(Long.valueOf(userId));
		List<SelectModel> sellist = new ArrayList<>();
		SelectModel selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.COMMON_EMPTY.getCode());
		selectModel.setText(CommonConsistEnum.COMMON_EMPTY.getDesc());
		for(CommisionOrderConfBean current : list){
			selectModel = new SelectModel();
			selectModel.setId(String.valueOf(current.getUserId()));
			selectModel.setText(current.getPhone() + "   " + current.getUserName());
			sellist.add(selectModel);
		}
		return sellist;
	}
	
	
    /**
     * 
     * @Description: 修改一条配置
     * @param request
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultInfo<Object> update(HttpServletRequest request, HttpServletResponse response, CommisionOrderConfBean bean){
    	int i = 0;
    	//设置更新人为创建人
    	try {
    		UserManagerLoginBean user = (UserManagerLoginBean)request.getSession().getAttribute("user");
    		if(null != user){
    			bean.setUpdateUser(user.getUserManagerId());
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
    	//更新时间是当前时间
        bean.setUpdateDate(new Date());
        bean.setModifyFlag(true);
        if (commisionOrderConfService.isExistSameRecord(bean) > 0) {
            return new ResultInfo<Object>(-1, "0", "同一个用户，角色，订单类型，费用类型的记录已经存在");
        }
        i = commisionOrderConfService.updateCostValue(bean);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "修改成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "修改失败");
        }
    }
    
    /**
     * 
     * @Description: 删除一条配置
     * @param req
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, CommisionOrderConfBean bean) {
        int i = 0;
        i = commisionOrderConfService.del(bean.getId());
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败");
        }
    }
    
    
    /**
     * 
     * @Description: 启用或者停用一条配置
     * @param req
     * @param response
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "enableOrDisable", method = RequestMethod.POST)
    public ResultInfo<Object> enableOrDisable(HttpServletRequest req, HttpServletResponse response, CommisionOrderConfBean bean) {
        int i = 0;
        if(bean.getState()==1){
        	i = commisionOrderConfService.enable(bean);
        }else{
        	i = commisionOrderConfService.disable(bean);
        }
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "执行成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "执行失败");
        }
    }
}
