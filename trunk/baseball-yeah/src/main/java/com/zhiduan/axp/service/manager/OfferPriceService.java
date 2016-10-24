/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.OfferPriceInfo;
import com.zhiduan.axp.dao.manager.bean.OfferAreaBean;

import java.util.List;



public interface OfferPriceService {

    List<OfferPriceInfo> getAll(OfferPriceInfo offerPriceInfo);
    List<OfferAreaBean> selectAll();
    Integer getAllTotal(OfferPriceInfo offerPriceInfo);

    void delete(Long offerAreaId);

    ResultInfo<Object> add(OfferPriceInfo offerPriceInfo);

    void update(OfferPriceInfo offerPriceInfo);
}