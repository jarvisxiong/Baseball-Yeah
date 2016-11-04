/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.controller.manager;


import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.SimpleZtree;
import com.rofour.baseball.controller.model.manager.AreaInfo;
import com.rofour.baseball.controller.model.manager.OfferPriceInfo;
import com.rofour.baseball.controller.model.manager.UserManagerInfo;
import com.rofour.baseball.controller.model.store.ExpStoreInfo;
import com.rofour.baseball.dao.manager.bean.OfferAreaBean;
import com.rofour.baseball.dao.manager.bean.SearchUserManagerBean;
import com.rofour.baseball.service.manager.Area;
import com.rofour.baseball.service.manager.OfferPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Offer price controller.
 *
 * @author will
 * @Description
 * @date 2016 -08-04 15:58:00
 */
@Controller
@RequestMapping("/manage/offerPrice")
public class OfferPriceController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The Offer price service.
     */
    @Resource(name = "offerPriceService")
    OfferPriceService offerPriceService;

    /**
     * Search all.
     *
     * @param response       the response
     * @param offerPriceInfo the offer price info
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public void searchAll(HttpServletResponse response, OfferPriceInfo offerPriceInfo) {
        List<OfferPriceInfo> list = null;
        DataGrid<OfferPriceInfo> grid = new DataGrid<OfferPriceInfo>();
        if (StringUtils.isEmpty(offerPriceInfo.getSort())) {
            offerPriceInfo.setSort("offerAreaId");
        }
        try {
            list = offerPriceService.getAll(offerPriceInfo);
            grid.setRows(list);
            grid.setTotal(offerPriceService.getAllTotal(offerPriceInfo));
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        writeJson(grid, response);
    }

    /**
     * Select exp store info.
     *
     * @param request  the request
     * @param response the response
     */
    @ResponseBody
    @RequestMapping(value = "/allforsel", method = RequestMethod.POST)
    public void selectExpStoreInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<OfferAreaBean> infoList = offerPriceService.selectAll();
            List<SelectModel> sellist = new ArrayList<>();
            for (OfferAreaBean offerPriceInfo : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(offerPriceInfo.getOfferAreaId().toString());
                selectModel.setText(offerPriceInfo.getOfferAreaName());
                sellist.add(selectModel);
            }
            writeJson(sellist, response);
        } catch (Exception e) {
            logger.error("获取信息异常", e);
        }
    }

    /**
     * Delete result info.
     *
     * @param response       the response
     * @param offerPriceInfo the offer price info
     * @return the result info
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delete(HttpServletResponse response, OfferPriceInfo offerPriceInfo) {
        try {
            offerPriceService.delete(offerPriceInfo.getOfferAreaId());
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return new ResultInfo(0, "", "");
    }

    /**
     * Update result info.
     *
     * @param response       the response
     * @param offerPriceInfo the offer price info
     * @return the result info
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo update(HttpServletResponse response, OfferPriceInfo offerPriceInfo) {
        try {
            return offerPriceService.update(offerPriceInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
            return new ResultInfo(-1, "", "添加失败");
        }
//        return new ResultInfo(0, "", "");
    }

    /**
     * Add result info.
     *
     * @param response       the response
     * @param offerPriceInfo the offer price info
     * @return the result info
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo add(HttpServletResponse response, OfferPriceInfo offerPriceInfo) {
        try {
            return offerPriceService.add(offerPriceInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
            return new ResultInfo(-1, "", "添加失败");
        }
    }

}
