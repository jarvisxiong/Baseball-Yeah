/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.zhiduan.axp.controller.model.manager.OfferPriceInfo;
import com.zhiduan.axp.dao.manager.bean.OfferAreaBean;
import com.zhiduan.axp.dao.manager.mapper.*;
import com.zhiduan.axp.service.manager.OfferPriceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.UserInfo;
import com.zhiduan.axp.controller.model.manager.AreaInfo;
import com.zhiduan.axp.dao.manager.bean.AreaBean;
import com.zhiduan.axp.dao.manager.bean.ProvinceBean;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.Area;

import javax.annotation.Resource;

/**
 * @ClassName: AreaImpl
 * @Description: 区域服务实现
 * @author cy
 * @date 2016年4月19日 下午4:54:39
 */
@Service("offerPriceService")
public class OfferPriceServiceImpl implements OfferPriceService {

    @Resource(name = "offerPriceMapper")
    OfferPriceMapper offerPriceMapper;

    @Override
    public List<OfferPriceInfo> getAll(OfferPriceInfo offerPriceInfo) {
        List<OfferPriceInfo> offerPriceInfos=offerPriceMapper.getAll(offerPriceInfo);
        if (offerPriceInfos!=null && offerPriceInfos.size()>0){
            for (OfferPriceInfo o: offerPriceInfos) {
                if (!StringUtils.isBlank(o.getProvinceIdsStr())){
                    String[] provinceIds=o.getProvinceIdsStr().split(",");
                    if (provinceIds!=null && provinceIds.length>0){
                        for (String s:provinceIds) {
                            o.getProvinceIds().add(Long.valueOf(s));
                        }
                    }
                }
            }
        }
        return offerPriceInfos;
    }

    @Override
    public Integer getAllTotal(OfferPriceInfo offerPriceInfo) {
        return offerPriceMapper.getAllTotal(offerPriceInfo);
    }
    @Override
    public List<OfferAreaBean> selectAll(){
        return offerPriceMapper.selectAll();
    }

    @Override
    public void delete(Long offerAreaId) {
        offerPriceMapper.deleteOfferArea(offerAreaId);
        offerPriceMapper.deleteOfferProvince(offerAreaId);
    }

    @Override
    public ResultInfo<Object> add(OfferPriceInfo offerPriceInfo) {
        if (offerPriceMapper.isExistOfferArea(offerPriceInfo.getOfferAreaName())>0){
            return new ResultInfo<Object>(-1,"","区域名称已经存在");
        }else {
            offerPriceMapper.addOfferArea(offerPriceInfo);
            if (offerPriceInfo.getProvinceIds()!=null && offerPriceInfo.getProvinceIds().size()>0){
                List<OfferPriceInfo> list=new ArrayList<OfferPriceInfo>();
                for (Long provinceId: offerPriceInfo.getProvinceIds()) {
                    list.add(new OfferPriceInfo(offerPriceInfo.getOfferAreaId(),provinceId));
                }
                offerPriceMapper.addOfferProvince(list);
            }
            return new ResultInfo<Object>(0,"","");
        }
    }

    @Override
    public void update(OfferPriceInfo offerPriceInfo) {
        offerPriceMapper.updateOfferArea(offerPriceInfo);
        offerPriceMapper.deleteOfferProvince(offerPriceInfo.getOfferAreaId());
        if (offerPriceInfo.getProvinceIds()!=null && offerPriceInfo.getProvinceIds().size()>0){
            List<OfferPriceInfo> list=new ArrayList<OfferPriceInfo>();
            for (Long provinceId: offerPriceInfo.getProvinceIds()) {
                list.add(new OfferPriceInfo(offerPriceInfo.getOfferAreaId(),provinceId));
            }
            offerPriceMapper.addOfferProvince(list);
        }
    }
}