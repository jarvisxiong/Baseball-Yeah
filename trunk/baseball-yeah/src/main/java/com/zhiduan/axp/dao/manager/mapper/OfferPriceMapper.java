/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.zhiduan.axp.dao.manager.mapper;

import com.zhiduan.axp.controller.model.manager.OfferPriceInfo;
import com.zhiduan.axp.dao.manager.bean.AreaBean;
import com.zhiduan.axp.dao.manager.bean.OfferAreaBean;

import javax.inject.Named;
import java.util.List;


@Named("offerPriceMapper")
public interface OfferPriceMapper {


    List<OfferPriceInfo> getAll(OfferPriceInfo offerPriceInfo);
    List<OfferAreaBean> selectAll();
    Integer getAllTotal(OfferPriceInfo offerPriceInfo);

    Integer isExistOfferArea(String offerAreaName);

    void deleteOfferArea(Long offerAreaId);

    void addOfferArea(OfferPriceInfo offerPriceInfo);

    void updateOfferArea(OfferPriceInfo offerPriceInfo);

    void deleteOfferProvince(Long offerAreaId);

    void addOfferProvince(List<OfferPriceInfo> list);

}