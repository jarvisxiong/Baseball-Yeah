package com.rofour.baseball.dao.manager.mapper;

import com.rofour.baseball.dao.manager.bean.GoodsTypeBean;

import javax.inject.Named;
import java.util.List;


/**
 * Created by Administrator on 2016-08-09.
 * 物品类型接口映射
 */
@Named("goodsTypeMapper")
public interface GoodsTypeMapper {

    List<GoodsTypeBean> selectByPrimaryKey(GoodsTypeBean bean);

    int deleteByPrimaryKey(Long goodsTypeId);

    int insert(GoodsTypeBean bean);

    int updateByPrimaryKeySelective(GoodsTypeBean bean);

    List<GoodsTypeBean> selectAll();

    int isNameExist(GoodsTypeBean bean);
}
