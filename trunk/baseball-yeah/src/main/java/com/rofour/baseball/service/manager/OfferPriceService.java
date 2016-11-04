/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.service.manager;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.OfferPriceInfo;
import com.rofour.baseball.dao.manager.bean.OfferAreaBean;

import java.util.List;



public interface OfferPriceService {

    List<OfferPriceInfo> getAll(OfferPriceInfo offerPriceInfo);
    List<OfferAreaBean> selectAll();
    Integer getAllTotal(OfferPriceInfo offerPriceInfo);

    void delete(Long offerAreaId);

    ResultInfo<Object> add(OfferPriceInfo offerPriceInfo);

    ResultInfo<Object> update(OfferPriceInfo offerPriceInfo);
}