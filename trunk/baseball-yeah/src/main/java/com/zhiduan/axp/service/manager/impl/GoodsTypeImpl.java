package com.zhiduan.axp.service.manager.impl;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.manager.GoodsTypeInfo;
import com.zhiduan.axp.dao.manager.bean.GoodsTypeBean;
import com.zhiduan.axp.dao.manager.mapper.GoodsTypeMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.manager.GoodsTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016-08-09.
 */
@Service("goodsTypeService")
public class GoodsTypeImpl implements GoodsTypeService {

    @Resource(name="goodsTypeMapper")
    private GoodsTypeMapper goodsTypeMapper;
    /**
     * 根据条件获取物品信息
     * @param
     * @return
     */
    @Override
    public List<GoodsTypeInfo> getByCondition() {
        List<GoodsTypeBean> beanList=goodsTypeMapper.selectAll();
        List<GoodsTypeInfo> infoList=new ArrayList<>();
        for (GoodsTypeBean goodsTypeBean : beanList) {
            GoodsTypeInfo newInfo=new GoodsTypeInfo();
            BeanUtils.copyProperties( goodsTypeBean,newInfo);
            infoList.add(newInfo);
        }
        return infoList;
    }

    /**
     * 添加物品类型
     * @param info
     * @return
     */
    @Override
    public ResultInfo<Object> addGoodsType(GoodsTypeInfo info) {
        try {
            GoodsTypeBean bean=new GoodsTypeBean();
            BeanUtils.copyProperties(info,bean);
            if(goodsTypeMapper.isNameExist(bean)>0)
            {
                return new ResultInfo<>(-1,"","已存在重复的名称",null);
            }
            int result=goodsTypeMapper.insert(bean);
            if(result>0)
            {
                return new ResultInfo<>(0,"","添加成功",null);
            }
            return new ResultInfo<>(-1,"","添加失败",null);
        }
        catch (Exception e)
        {
           throw new BusinessException("102");
        }
    }

    /**
     * 更新物品类型
     * @param info
     * @return
     */
    @Override
    public ResultInfo<Object> updateGoodsType(GoodsTypeInfo info) {
        try {
            GoodsTypeBean bean = new GoodsTypeBean();
            BeanUtils.copyProperties(info, bean);
            if(goodsTypeMapper.isNameExist(bean)>0)
            {
                return new ResultInfo<>(-1,"","已存在重复的名称",null);
            }
            int result = goodsTypeMapper.updateByPrimaryKeySelective(bean);
            if (result > 0) {
                return new ResultInfo<>(0, "", "更新成功", null);
            }
            return new ResultInfo<>(-1, "", "更新失败", null);
        }
        catch (Exception e)
        {
            throw  new BusinessException("102");
        }
    }

    /**
     * 删除物品类型
     * @param goodsTypeId
     * @return
     */
    @Override
    public ResultInfo<Object> delGoodsType(Long goodsTypeId) {
        try {
            int result=goodsTypeMapper.deleteByPrimaryKey(goodsTypeId);
            if(result>0)
            {
                return new ResultInfo<>(0,"","删除成功",null);
            }
            return new ResultInfo<>(-1,"","删除失败",null);
        }
        catch (Exception e)
        {
            throw new BusinessException("102");
        }
    }
}
