package com.rofour.baseball.dao.manager.mapper;

import com.rofour.baseball.dao.manager.bean.PickupAddressCollegeBean;

import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: PickupAddressCollegeMapper
 * @Description: 取件地址学校关联表数据库操作接口
 * @author: xulang
 * @Date: 2016-08-10 15:24
 */
@Named("pickupAddressCollegeMapper")
public interface PickupAddressCollegeMapper {
    /**
     * 按主键删除
     *
     * @param pacId
     * @return
     */
    int deleteByPrimaryKey(Long pacId);

    /**
     * 按主键批量删除
     *
     * @param pickupAddressId
     * @return
     */
    int deleteByPickupAddressId(Long pickupAddressId);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(PickupAddressCollegeBean record);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(List<PickupAddressCollegeBean> list);

    /**
     * 按主键查询
     *
     * @param pacId
     * @return
     */
    PickupAddressCollegeBean selectByPrimaryKey(Long pacId);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(PickupAddressCollegeBean record);
}