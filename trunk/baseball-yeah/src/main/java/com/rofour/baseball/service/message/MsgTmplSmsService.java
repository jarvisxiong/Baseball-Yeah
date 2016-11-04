package com.rofour.baseball.service.message;

import com.rofour.baseball.controller.model.message.MsgTmplSmsInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: MsgTmplSmsService
 * @Description: 短信模板操作服务
 * @author: xulang
 * @date: 2016年09月06日 13:21
 */

public interface MsgTmplSmsService {
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
    int insert(MsgTmplSmsInfo record) throws Exception;

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    MsgTmplSmsInfo selectByPrimaryKey(Integer id);

    /**
     * 按条件查询
     *
     * @param msgType
     * @param limit
     * @param offset
     * @return
     */
    List<MsgTmplSmsInfo> selectAll(Integer msgType, Integer limit, Integer offset);

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
    int updateByPrimaryKey(MsgTmplSmsInfo record) throws Exception;
}
