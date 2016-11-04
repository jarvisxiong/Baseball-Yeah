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
import com.rofour.baseball.dao.manager.bean.OfferBean;
import com.rofour.baseball.service.manager.CompanyOfferPriceService;

/**
 * Created by Administrator on 2016-07-06.
 */

@Controller
@RequestMapping("/manage/companyofferprice")
public class CompanyOfferPriceController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("companyOfferPriceService")
    private CompanyOfferPriceService companyOfferPriceService;


    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response, @RequestBody OfferBean bean) {
        if (StringUtils.isBlank(bean.getSort())) {
            bean.setSort("create_time");//默认以id排序
            bean.setOrder("desc");
        }
        DataGrid<OfferBean> grid = new DataGrid<OfferBean>();
        try {
            List<OfferBean> offList = companyOfferPriceService.getAll(bean);
            grid.setRows(offList);
            grid.setTotal(companyOfferPriceService.selectAllCount(bean));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        writeJson(grid, response);
    }

    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public ResultInfo<Object> del(HttpServletRequest req, HttpServletResponse response, OfferBean bean) {
        int i = 0;
        i = companyOfferPriceService.del(bean);
//        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "删除成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResultInfo<Object> add(HttpServletRequest req, HttpServletResponse response, OfferBean bean) {
        int i = 0;
        bean.setCreateTime(new Date());
        bean.setIsDeleted(Byte.parseByte("0"));
        if (companyOfferPriceService.selectIsExtNameCount(bean) > 0) {
            return new ResultInfo<Object>(-1, "0", "名称不能重复");
        }
        i = companyOfferPriceService.insert(bean);
//        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "添加成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultInfo<Object> update(HttpServletRequest req, HttpServletResponse response, OfferBean bean) {
        int i = 0;
        i = companyOfferPriceService.update(bean);
//        ResultInfo<Object> result = new ResultInfo<Object>();
        if (i > 0) {
            return new ResultInfo<Object>(0, "0", "更新成功");
        } else {
            return new ResultInfo<Object>(-1, "0", "更新失败");
        }
    }
}
