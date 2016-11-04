/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.service.manager.impl;

import com.rofour.baseball.common.AxpUtils;
import com.rofour.baseball.common.StringUtils;
import com.rofour.baseball.controller.model.manager.PickupAddressInfo;
import com.rofour.baseball.dao.manager.bean.PickupAddressBean;
import com.rofour.baseball.dao.manager.bean.PickupAddressCollegeBean;
import com.rofour.baseball.dao.manager.mapper.PickupAddressCollegeMapper;
import com.rofour.baseball.dao.manager.mapper.PickupAddressMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.PickupAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PickupAddressImpl
 * @Description: 代取件取件地址表服务实现类
 * @author: xulang
 * @date: 2016年08月09日 14:08
 */
@Service("pickupAddressService")
public class PickupAddressImpl implements PickupAddressService {

    Logger logger = LoggerFactory.getLogger(PickupAddressImpl.class);

    @Autowired
    @Qualifier("pickupAddressMapper")
    private PickupAddressMapper pickupAddressMapper;

    @Autowired
    @Qualifier("pickupAddressCollegeMapper")
    private PickupAddressCollegeMapper pickupAddressCollegeMapper;

    /**
     * 按主键删除
     *
     * @param pickupAddressIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(List<Long> pickupAddressIds) {
        return pickupAddressMapper.deleteByPrimaryKey(pickupAddressIds);
    }

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int insert(PickupAddressInfo record) {
        PickupAddressBean bean = new PickupAddressBean();
        AxpUtils.copyProperties(record, bean);
        int count = pickupAddressMapper.insert(bean);
        //先删除，在新增
        pickupAddressCollegeMapper.deleteByPickupAddressId(bean.getPickupAddressId());
        record.setPickupAddressId(bean.getPickupAddressId());
        List<PickupAddressCollegeBean> pickupAddressCollegeBeenList = buildPickupCollegeBeans(record);
        pickupAddressCollegeMapper.batchInsert(pickupAddressCollegeBeenList);
        return count;
    }

    /**
     * 按主键查询
     *
     * @param pickupAddressId
     * @return
     */
    @Override
    public PickupAddressInfo selectByPrimaryKey(Long pickupAddressId) {
        PickupAddressBean pickupAddressBean = pickupAddressMapper.selectByPrimaryKey(pickupAddressId);
        if (pickupAddressBean == null) {
            throw new BusinessException("01024");
        }
        PickupAddressInfo pickupAddressInfo = new PickupAddressInfo();
        AxpUtils.copyProperties(pickupAddressBean, pickupAddressInfo);
        return pickupAddressInfo;
    }

    /**
     * 查询列表
     *
     * @param storeName
     * @param storePhone
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public List<PickupAddressInfo> selectList(String storeName, String storePhone, Integer limit, Integer offset,String sort,String order) {
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(storeName)) {
            map.put("storeName", MessageFormat.format("%{0}%", storeName));
        }
        if (!StringUtils.isEmpty(storePhone)) {
            map.put("storePhone", MessageFormat.format("%{0}%", storePhone));
        }
        map.put("limit", limit);
        map.put("offset", offset);

        map.put("sort", sort);map.put("order", order);
        List<PickupAddressBean> pickupAddressBeanList = pickupAddressMapper.selectList(map);
        if (pickupAddressBeanList.size() < 1) {
            throw new BusinessException("01024");
        }
        List<PickupAddressInfo> pickupAddressInfoList = new ArrayList<PickupAddressInfo>();
        for (PickupAddressBean bean : pickupAddressBeanList) {
            PickupAddressInfo pickupAddressInfo = new PickupAddressInfo();
            AxpUtils.copyProperties(bean, pickupAddressInfo);
            pickupAddressInfoList.add(pickupAddressInfo);
        }
        return pickupAddressInfoList;
    }

    /**
     * 查询列表总数
     *
     * @param storeName
     * @param storePhone
     * @return
     */
    @Override
    public int selectListCount(String storeName, String storePhone) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(storeName)) {
            map.put("storeName", MessageFormat.format("%{0}%", storeName));
        }
        if (!StringUtils.isEmpty(storePhone)) {
            map.put("storePhone", MessageFormat.format("%{0}%", storePhone));
        }
        return pickupAddressMapper.selectListCount(map);
    }


    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKey(PickupAddressInfo record) {
        PickupAddressBean bean = new PickupAddressBean();
        AxpUtils.copyProperties(record, bean);
        //先删除，在新增
        pickupAddressCollegeMapper.deleteByPickupAddressId(record.getPickupAddressId());
        List<PickupAddressCollegeBean> pickupAddressCollegeBeenList = buildPickupCollegeBeans(record);
        pickupAddressCollegeMapper.batchInsert(pickupAddressCollegeBeenList);
        int count = pickupAddressMapper.updateByPrimaryKeySelective(bean);
        return count;
    }

    /**
     * 组装取件地址学校关联实体
     *
     * @param record
     * @return
     */
    private List<PickupAddressCollegeBean> buildPickupCollegeBeans(PickupAddressInfo record) {
        List<PickupAddressCollegeBean> pickupAddressCollegeBeenList = new ArrayList<PickupAddressCollegeBean>();
        for (Long item : record.getColleges()) {
            PickupAddressCollegeBean pickupAddressCollegeBean = new PickupAddressCollegeBean();
            pickupAddressCollegeBean.setCollegeId(item);
            pickupAddressCollegeBean.setPickupAddressId(record.getPickupAddressId());
            pickupAddressCollegeBeenList.add(pickupAddressCollegeBean);
        }
        return pickupAddressCollegeBeenList;
    }
}
