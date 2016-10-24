/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.controller.manager;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.PickupAddressInfo;
import com.zhiduan.axp.service.manager.PickupAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: PickupAddressController
 * @Description: 代取件取件地址维护控制器
 * @author: xulang
 * @date: 2016年08月09日 14:41
 */
@Controller
@RequestMapping("/manage/pickupaddress")
public class PickupAddressController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("pickupAddressService")
    private PickupAddressService pickupAddressService;

    /**
     * 新增
     *
     * @param request
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultInfo<Object> add(HttpServletRequest request, PickupAddressInfo info) {
        try {
            pickupAddressService.insert(info);
            return new ResultInfo<Object>(0, "", "新增成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * 编辑
     *
     * @param request
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultInfo<Object> edit(HttpServletRequest request, PickupAddressInfo info) {
        try {
            pickupAddressService.updateByPrimaryKey(info);
            return new ResultInfo<Object>(0, "", "编辑成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * 删除
     *
     * @param request
     * @param pickupAddressIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResultInfo<Object> delete(@RequestParam("pickupAddressIds") List<Long> pickupAddressIds, HttpServletRequest request) {
        try {
            pickupAddressService.deleteByPrimaryKey(pickupAddressIds);
            return new ResultInfo<Object>(0, "", "删除成功");
        } catch (Exception e) {
            return processException(e, logger);
        }
    }

    /**
     * 查询
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(HttpServletRequest request, HttpServletResponse response) {
        List<PickupAddressInfo> pickupAddressInfos = null;
        DataGrid<PickupAddressInfo> grid = new DataGrid<PickupAddressInfo>();
        try {
            String storeName = request.getParameter("storeName");
            String storePhone = request.getParameter("storePhone");
            Integer limit = Integer.valueOf(request.getParameter("limit"));
            Integer offset = Integer.valueOf(request.getParameter("offset"));
            pickupAddressInfos = pickupAddressService.selectList(storeName.trim(), storePhone.trim(), limit, offset);
            grid.setRows(pickupAddressInfos);
            grid.setTotal(pickupAddressService.selectListCount(storeName.trim(), storePhone.trim()));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }
}
