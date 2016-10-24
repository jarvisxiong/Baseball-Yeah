package com.zhiduan.axp.service.manager.impl;

import com.zhiduan.axp.controller.model.manager.OfferPriceInfo;
import com.zhiduan.axp.dao.manager.bean.FocusPicBean;
import com.zhiduan.axp.dao.manager.bean.OfferBean;
import com.zhiduan.axp.dao.manager.bean.OfferExpressCollageBran;
import com.zhiduan.axp.dao.manager.bean.OfferExpressCompanyBran;
import com.zhiduan.axp.dao.manager.mapper.CompanyOfferPriceMapper;
import com.zhiduan.axp.dao.manager.mapper.FocusPicMapper;
import com.zhiduan.axp.service.manager.CompanyOfferPriceService;
import com.zhiduan.axp.service.manager.FocusPicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-07-06.
 */
@Service("companyOfferPriceService")
public class CompanyOfferPriceImpl implements CompanyOfferPriceService {

    @Autowired
    @Qualifier("companyOfferPriceMapper")
    private CompanyOfferPriceMapper companyOfferPriceMapper;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(OfferBean bean) {
        int i = 0;
        bean.setInitialWeight(bean.getInitialWeight() * 100);
        bean.setAdditionalWeight(bean.getAdditionalWeight() * 100);
        i = companyOfferPriceMapper.insert(bean);
        for (long comid : bean.getEcList()) {
            OfferExpressCompanyBran offerCompany = new OfferExpressCompanyBran();
            offerCompany.setOfferId(bean.getOfferId());
            offerCompany.setExpressCompanyId(comid);
            companyOfferPriceMapper.insertExpressCompany(offerCompany);
        }
        for (long collageid : bean.getCollageList()) {
            OfferExpressCollageBran expressCollageBran = new OfferExpressCollageBran();
            expressCollageBran.setOfferId(bean.getOfferId());
            expressCollageBran.setCollegeId(collageid);
            companyOfferPriceMapper.insertExpressCollage(expressCollageBran);
        }
        return i;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int update(OfferBean bean) {

        bean.setInitialWeight(bean.getInitialWeight() * 100);
        bean.setAdditionalWeight(bean.getAdditionalWeight() * 100);
        companyOfferPriceMapper.deleteExpressCompany(bean.getOfferId());
        companyOfferPriceMapper.deleteExpressCollage(bean.getOfferId());
        for (long comid : bean.getEcList()) {
            OfferExpressCompanyBran offerCompany = new OfferExpressCompanyBran();
            offerCompany.setOfferId(bean.getOfferId());
            offerCompany.setExpressCompanyId(comid);
            companyOfferPriceMapper.insertExpressCompany(offerCompany);
        }

        for (long collageid : bean.getCollageList()) {
            OfferExpressCollageBran expressCollageBran = new OfferExpressCollageBran();
            expressCollageBran.setOfferId(bean.getOfferId());
            expressCollageBran.setCollegeId(collageid);
            companyOfferPriceMapper.insertExpressCollage(expressCollageBran);
        }
        return companyOfferPriceMapper.update(bean);
    }

    public List<OfferBean> getAll(OfferBean picBean) {
        List<OfferBean> offerPriceInfos = companyOfferPriceMapper.selectAll(picBean);
        if (offerPriceInfos != null && offerPriceInfos.size() > 0) {
            for (OfferBean o : offerPriceInfos) {
                o.setAdditionalWeight(o.getAdditionalWeight() / 100);
                o.setInitialWeight(o.getInitialWeight() / 100);
                if (!StringUtils.isBlank(o.getCompanyIds())) {
                    String[] provinceIds = o.getCompanyIds().split(",");
                    if (provinceIds != null && provinceIds.length > 0) {
                        List<Long> sss = new ArrayList<Long>();
                        for (String s : provinceIds) {
                            o.setEcList(sss);
                            o.getEcList().add(Long.parseLong(s));
                        }
                    }
                }
                if (!StringUtils.isBlank(o.getCollageIds())) {
                    String[] collageIds = o.getCollageIds().split(",");
                    if (collageIds != null && collageIds.length > 0) {
                        List<Long> sss = new ArrayList<Long>();
                        for (String s : collageIds) {
                            o.setCollageList(sss);
                            o.getCollageList().add(Long.parseLong(s));
                        }
                    }
                }
            }
        }
        return offerPriceInfos;
    }

    public int del(OfferBean logBean) {
        return companyOfferPriceMapper.deleteByPrimaryKey(logBean.getOfferId());
    }

    public int selectAllCount(OfferBean picBean) {
        return companyOfferPriceMapper.selectAllCount(picBean);
    }

    public int selectIsExtNameCount(OfferBean offerBean) {
        return companyOfferPriceMapper.selectIsExtNameCount(offerBean);
    }
}
