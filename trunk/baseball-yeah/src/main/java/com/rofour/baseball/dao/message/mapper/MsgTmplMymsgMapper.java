package com.rofour.baseball.dao.message.mapper;

import com.rofour.baseball.dao.message.bean.MsgTmplMymsgBean;

import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named("msgTmplMymsgMapper")
public interface MsgTmplMymsgMapper {

    int insertSelective(MsgTmplMymsgBean record);

    MsgTmplMymsgBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(MsgTmplMymsgBean record);

    /**
     * 按条件查询
     *
     * @param map
     * @return
     */
    List<MsgTmplMymsgBean> selectList(Map<String, Object> map);

    /**
     * 按条件查询列表条数
     *
     * @param msgType
     * @return
     */
    int selectAllCount(Integer msgType);
}