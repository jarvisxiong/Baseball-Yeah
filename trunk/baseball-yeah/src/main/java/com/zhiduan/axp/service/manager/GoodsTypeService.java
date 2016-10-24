package com.zhiduan.axp.service.manager;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.GoodsTypeInfo;

import java.util.List;

import static java.awt.SystemColor.info;

/**
 * Created by Administrator on 2016-08-09.
 */

public interface GoodsTypeService {

    /**
     * 根据条件查询物品类型信息
     * @return
     */
    List<GoodsTypeInfo> getByCondition();

    /**
     * 添加物品类型
     * @param info
     * @return
     */
    ResultInfo<Object> addGoodsType(GoodsTypeInfo info);

    /**
     * 更新物品类型
     * @param info
     * @return
     */
    ResultInfo<Object> updateGoodsType(GoodsTypeInfo info);

    /**
     * 删除物品类型
     * @param goodsTypeId
     * @return
     */
    ResultInfo<Object> delGoodsType(Long goodsTypeId);


}
