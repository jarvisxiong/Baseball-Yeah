/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.dao.manager.bean.CityOrderConfBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.CityOrderConfService;

/**
 * @ClassName: CityOrderConfController
 * @Description: 城市最小赏金配置的增删改查接口
 * @author zdh
 * @date 2016年10月14日 上午11:00:00
 *
 */
@Controller
@RequestMapping(value = "/manage/cityOrderConf")
public class CityOrderConfController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("cityOrderConfService")
    private CityOrderConfService cityOrderConfService;
    
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
    public ResultInfo<Object> enableOrDisable(HttpServletRequest req, HttpServletResponse response, CityOrderConfBean bean) {
        int i = 0;
        if(bean.getState()==1){
        	i = cityOrderConfService.enable(bean);
        }else{
        	i = cityOrderConfService.disable(bean);
        }
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "执行成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "执行失败");
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
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, CityOrderConfBean bean) {
        int i = 0;
        i = cityOrderConfService.del(bean);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败");
        }
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
    public ResultInfo<Object> update(HttpServletRequest request, HttpServletResponse response, CityOrderConfBean bean){
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
        //将元转换为分
        bean.setCostValue(bean.getCostValue()*100);
        i = cityOrderConfService.updateCostValue(bean);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
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
    public ResultInfo<Object> add(HttpServletRequest request, HttpServletResponse response, CityOrderConfBean bean){
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
    	//创建时间是当前时间
        bean.setCreateDate(new Date());
        //默认状态是禁用
        bean.setState(0);
        //将元转换为分
        bean.setCostValue(bean.getCostValue()*100);
        if (cityOrderConfService.selectIsExtNameCount(bean) > 0) {
            return new ResultInfo<Object>(-1, "0", "同一个城市，订单类型，服务类型的记录已经存在");
        }
        i = cityOrderConfService.insert(bean);
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }
    
    /*
     * 根据条件查询出配置信息
     */
    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public void getCityOrderConfList(HttpServletRequest request, HttpServletResponse response, @RequestBody CityOrderConfBean bean) {
        if (StringUtils.isBlank(bean.getSort())) {
            bean.setSort("create_date");//默认以id排序
            bean.setOrder("desc");
        }
        if(StringUtils.equals("costValue", bean.getSort())){
        	bean.setSort("cost_value");//默认以id排序
        }
        DataGrid<CityOrderConfBean> grid = new DataGrid<CityOrderConfBean>();
        try {
            List<CityOrderConfBean> offList = cityOrderConfService.getAll(bean);
            grid.setRows(offList);
            grid.setTotal(cityOrderConfService.selectAllCount(bean));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }


}
