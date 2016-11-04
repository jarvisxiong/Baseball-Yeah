package com.rofour.baseball.dao.manager.mapper;


import com.rofour.baseball.dao.manager.bean.PickupAddressBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PickupAddressMapper
 * @Description: 代取件取件地址表数据库操作接口
 * @author: xulang
 * @Date: 2016-08-09 13:43
 */
@Named("pickupAddressMapper")
public interface PickupAddressMapper {
    /**
     * 根据主键删除
     *
     * @param list
     * @return
     */
    int deleteByPrimaryKey(List<Long> list);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(PickupAddressBean record);

    /**
     * 动态新增
     *
     * @param record
     * @return
     */
    int insertSelective(PickupAddressBean record);

    /**
     * 按主键查询
     *
     * @param PickupAddressId
     * @return
     */
    PickupAddressBean selectByPrimaryKey(Long PickupAddressId);

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    List<PickupAddressBean> selectList(Map<String, Object> map);

    /**
     * 查询列表总数
     * @return
     */
    int selectListCount(Map<String,Object> map);

    /**
     * 动态更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PickupAddressBean record);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(PickupAddressBean record);
}