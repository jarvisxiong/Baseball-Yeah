package com.zhiduan.axp.dao.message.mapper;

import com.zhiduan.axp.dao.message.bean.MsgTmplWxBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsgTmplWxMapper
 * @Description: 微信模板表数据库操作接口
 * @author: xulang
 * @Date: 2016-09-13 13:55
 */
@Named("msgTmplWxMapper")
public interface MsgTmplWxMapper {
    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insertSelective(MsgTmplWxBean record);

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    MsgTmplWxBean selectByPrimaryKey(Integer id);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MsgTmplWxBean record);

    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    List<MsgTmplWxBean> selectList(Map<String, Object> map);

    /**
     * 按条件查询列表条数
     *
     * @param msgType
     * @return
     */
    int selectAllCount(Integer msgType);
}