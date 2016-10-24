package com.zhiduan.axp.service.store;

import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.store.BusinessChannelInfo;

import java.util.List;

/**
 * @ClassName: BusinessChlService
 * @Description: 商户渠道服务接口
 * @author ZhangLei
 * @date 2016年8月31日 下午5:16:10 
 */
public interface BusinessChlService {

    /**
     * @Description: 查询所有商户渠道
     * @param businessChannelInfo
     * @return 
     */
        
    List<BusinessChannelInfo> selectAllBusinessChl(BusinessChannelInfo businessChannelInfo);

    /**
     * @Description: 查询所有商户渠道总数
     * @param businessChannelInfo
     * @return 
     */
        
    Integer selectAllBusinessChlTotal(BusinessChannelInfo businessChannelInfo);

    /**
     * @Description: 逻辑删除商户渠道
     * @param channelId
     * @return 
     */
        
    ResultInfo delBussinessChl(Long channelId);

    /**
     * @Description: 禁用商户渠道
     * @param channelId
     * @return 
     */
        
    ResultInfo cancleBussinessChl(Long channelId);

    /**
     * @Description: 启用商户渠道
     * @param channelId
     * @return 
     */
        
    ResultInfo enabledBussinessChl(Long channelId);

    /**
     * @Description: 增加商户渠道
     * @param businessChannelInfo
     * @return 
     */
        
    ResultInfo addBussinessChl(BusinessChannelInfo businessChannelInfo);

    /**
     * @Description: 验证商户渠道是否存在
     * @param channelCode
     * @return 
     */
        
    ResultInfo ifExistChannelCode(String channelCode);
}
