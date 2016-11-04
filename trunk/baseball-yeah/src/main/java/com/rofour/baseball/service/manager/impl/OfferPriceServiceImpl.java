/**  
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.rofour.baseball.controller.model.manager.OfferPriceInfo;
import com.rofour.baseball.dao.manager.bean.OfferAreaBean;
import com.rofour.baseball.dao.manager.mapper.*;
import com.rofour.baseball.service.manager.OfferPriceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.UserInfo;
import com.rofour.baseball.controller.model.manager.AreaInfo;
import com.rofour.baseball.dao.manager.bean.AreaBean;
import com.rofour.baseball.dao.manager.bean.ProvinceBean;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.Area;

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
    public ResultInfo<Object> update(OfferPriceInfo offerPriceInfo) {
        if (offerPriceMapper.isExistOfferArea(offerPriceInfo.getOfferAreaName())>0){
            return new ResultInfo<Object>(-1,"","区域名称已经存在");
        }
        offerPriceMapper.updateOfferArea(offerPriceInfo);
        offerPriceMapper.deleteOfferProvince(offerPriceInfo.getOfferAreaId());
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