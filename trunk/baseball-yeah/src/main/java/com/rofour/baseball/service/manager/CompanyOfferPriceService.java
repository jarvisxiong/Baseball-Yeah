package com.rofour.baseball.service.manager;

import com.rofour.baseball.dao.manager.bean.OfferBean;

import java.util.List;

public interface CompanyOfferPriceService {

    int insert(OfferBean logBean);
    int update(OfferBean logBean);
    int del(OfferBean logBean);

    List<OfferBean> getAll(OfferBean logBean);

    int selectAllCount(OfferBean logBean);
    int selectIsExtNameCount(OfferBean logBean);

}
