package com.rofour.baseball.service.message;

import com.rofour.baseball.controller.model.message.MsgTmplWxInfo;

import java.util.List;

/**
 * @ClassName: MsgTmplWxService
 * @Description: 微信模板操作服务
 * @author: xulang
 * @date: 2016年09月06日 13:21
 */

public interface MsgTmplWxService {
    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(MsgTmplWxInfo record) throws Exception;

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    MsgTmplWxInfo selectByPrimaryKey(Integer id);

    /**
     * 按条件查询
     *
     * @param msgType
     * @param limit
     * @param offset
     * @return
     */
    List<MsgTmplWxInfo> selectAll(Integer msgType, Integer limit, Integer offset);

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
    int update(MsgTmplWxInfo record) throws Exception;
}
