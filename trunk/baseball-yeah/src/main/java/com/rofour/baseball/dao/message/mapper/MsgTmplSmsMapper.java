package com.rofour.baseball.dao.message.mapper;

import com.rofour.baseball.dao.message.bean.MsgTmplSmsBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsgTmplSmsMapper
 * @Description: 短信模板表数据库操作接口
 * @author: xulang
 * @Date: 2016-09-06 13:03
 */
@Named("msgTmplSmsMapper")
public interface MsgTmplSmsMapper {
    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(MsgTmplSmsBean record);

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    MsgTmplSmsBean selectByPrimaryKey(Integer id);

    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    List<MsgTmplSmsBean> selectAll(Map<String, Object> map);

    /**
     * 按条件查询列表条数
     *
     * @param msgType
     * @return
     */
    int selectAllCount(Integer msgType);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(MsgTmplSmsBean record);
}