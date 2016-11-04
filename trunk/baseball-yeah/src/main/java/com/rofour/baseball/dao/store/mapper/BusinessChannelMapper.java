package com.rofour.baseball.dao.store.mapper;

import com.rofour.baseball.controller.model.store.BusinessChannelInfo;
import com.rofour.baseball.dao.store.bean.BusinessChannelBean;

import javax.inject.Named;
import java.util.List;

/**
 * @ClassName: BusinessChannelMapper
 * @Description: 商户渠道数据层
 * @author ZhangLei
 * @date 2016年8月31日 下午5:27:58 
 */
@Named("businessChannelMapper")
public interface BusinessChannelMapper {
    int deleteByPrimaryKey(Integer channelId);

    int insert(BusinessChannelBean record);

    int insertSelective(BusinessChannelInfo record);

    BusinessChannelBean selectByPrimaryKey(Integer channelId);

    int updateByPrimaryKeySelective(BusinessChannelBean record);

    int updateByPrimaryKey(BusinessChannelBean record);

    /**
     * @Description: 查询所有商户渠道
     * @param businessChannelInfo
     * @return 
     */
        
    List<BusinessChannelInfo> selectAllBusinessChl(BusinessChannelInfo businessChannelInfo);

    /**
     * @Description: 查询所有商户渠道数量
     * @param businessChannelInfo
     * @return 
     */
        
    Integer selectAllBusinessChlTotal(BusinessChannelInfo businessChannelInfo);

    /**
     * @Description: 逻辑删除商户渠道
     * @param channelId
     * @return 
     */
        
    int delBussinessChl(Long channelId);

    /**
     * @Description: 禁用商户渠道
     * @param channelId
     * @return 
     */
        
    int cancleBussinessChl(Long channelId);

    /**
     * @Description: 启用商户渠道
     * @param channelId
     * @return 
     */
        
    int enabledBussinessChl(Long channelId);

    /**
     * @Description: 验证渠道编码是否存在
     * @param channelCode
     * @return 
     */
        
    Integer ifExistChannelCode(String channelCode);
}