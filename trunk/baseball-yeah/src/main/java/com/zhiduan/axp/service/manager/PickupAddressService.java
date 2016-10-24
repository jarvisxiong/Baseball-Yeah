package com.zhiduan.axp.service.manager;


import com.zhiduan.axp.controller.model.manager.PickupAddressInfo;

import java.util.List;

/**
 * @ClassName: PickupAddressService
 * @Description: 代取件取件地址表服务接口
 * @author: xulang
 * @Date: 2016-08-09 13:43
 */
public interface PickupAddressService {
    /**
     * 根据主键删除
     *
     * @param pickupAddressIds
     * @return
     */
    int deleteByPrimaryKey(List<Long> pickupAddressIds);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(PickupAddressInfo record);

    /**
     * 按主键查询
     *
     * @param pickupAddressId
     * @return
     */
    PickupAddressInfo selectByPrimaryKey(Long pickupAddressId);

    /**
     * 查询列表
     * @param storeName
     * @param storePhone
     * @param limit
     * @param offset
     * @return
     */
    List<PickupAddressInfo> selectList(String storeName,String storePhone,Integer limit,Integer offset);

    /**
     * 查询列表总数
     * @param storeName
     * @param storePhone
     * @return
     */
    int selectListCount(String storeName,String storePhone);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(PickupAddressInfo record);
}