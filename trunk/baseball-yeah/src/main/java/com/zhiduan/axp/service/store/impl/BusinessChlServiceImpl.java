package com.zhiduan.axp.service.store.impl;

import com.zhiduan.axp.common.StringUtils;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.store.BusinessChannelInfo;
import com.zhiduan.axp.dao.store.mapper.BusinessChannelMapper;
import com.zhiduan.axp.service.store.BusinessChlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: BusinessChlServiceImpl
 * @Description: 商户渠道服务实现
 * @author ZhangLei
 * @date 2016年8月31日 下午5:15:20 
 */
@Service("businessChlService")
public class BusinessChlServiceImpl implements BusinessChlService {

	private static final Logger LOG = LoggerFactory.getLogger(BusinessChlServiceImpl.class);


	@Resource(name="businessChannelMapper")
	private BusinessChannelMapper businessChannelMapper;

    /**
     * @Description: 查询所有非系统商户渠道
     * @param businessChannelInfo
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#selectAllBusinessChl(com.zhiduan.axp.controller.model.store.BusinessChannelInfo) 
     */
    @Override
	public List<BusinessChannelInfo> selectAllBusinessChl(BusinessChannelInfo businessChannelInfo) {
        return businessChannelMapper.selectAllBusinessChl(businessChannelInfo);
	}

	/**
	 * @Description: 查询所有非系统商户渠道总数
	 * @param businessChannelInfo
	 * @return 
	 * @see com.zhiduan.axp.service.store.BusinessChlService#selectAllBusinessChlTotal(com.zhiduan.axp.controller.model.store.BusinessChannelInfo) 
	 */
	@Override
	public Integer selectAllBusinessChlTotal(BusinessChannelInfo businessChannelInfo) {
        return businessChannelMapper.selectAllBusinessChlTotal(businessChannelInfo);
    }

    /**
     * @Description: 逻辑删除商户渠道
     * @param channelId
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#delBussinessChl(java.lang.Long) 
     */
    @Override
    public ResultInfo delBussinessChl(Long channelId) {
        if (businessChannelMapper.delBussinessChl(channelId)>0){
            return new ResultInfo(0,"","删除商户渠道成功");
        }else {
        return  new ResultInfo(-1,"","删除商户渠道失败");
        }
    }

    /**
     * @Description: 禁用商户渠道
     * @param channelId
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#cancleBussinessChl(java.lang.Long) 
     */
    @Override
    public ResultInfo cancleBussinessChl(Long channelId) {
        if (businessChannelMapper.cancleBussinessChl(channelId)>0){
            return new ResultInfo(0,"","禁用商户渠道成功");
        }else {
            return  new ResultInfo(-1,"","禁用商户渠道失败");
        }
    }

    /**
     * @Description: 启用商户渠道
     * @param channelId
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#enabledBussinessChl(java.lang.Long) 
     */
    @Override
    public ResultInfo enabledBussinessChl(Long channelId) {
        if (businessChannelMapper.enabledBussinessChl(channelId)>0){
            return new ResultInfo(0,"","启用商户渠道成功");
        }else {
            return  new ResultInfo(-1,"","启用商户渠道失败");
        }
    }

    /**
     * @Description: 增加商户渠道
     * @param businessChannelInfo
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#addBussinessChl(com.zhiduan.axp.controller.model.store.BusinessChannelInfo) 
     */
    @Override
    public ResultInfo addBussinessChl(BusinessChannelInfo businessChannelInfo) {

        if (StringUtils.isEmpty(businessChannelInfo.getChannelName())){
          return  new ResultInfo(-1,"","渠道名称不能为空");
        }else if (StringUtils.isEmpty(businessChannelInfo.getChannelCode())){
            return  new ResultInfo(-1,"","渠道编码不能为空");
        }else if (ifExistChannelCode(businessChannelInfo.getChannelCode()).getSuccess()!=0){
            return  new ResultInfo(-1,"","渠道编码已经存在");
        }else if (StringUtils.isEmpty(businessChannelInfo.getContacts())){
            return  new ResultInfo(-1,"","联系人不能为空");
        }else if (StringUtils.isEmpty(businessChannelInfo.getConnectPhone())){
            return  new ResultInfo(-1,"","联系手机号不能为空");
        }else if (StringUtils.isEmpty(businessChannelInfo.getSpreadUrl())){
            return  new ResultInfo(-1,"","推广URL不能为空");
        }else if (StringUtils.isEmpty(businessChannelInfo.getTargetUrl())){
            return  new ResultInfo(-1,"","目标URL不能为空");
        }
        businessChannelInfo.setState((byte) 0);
        businessChannelInfo.setIsDeleted((byte) 0);
        businessChannelInfo.setCreateDate(new Date());

        if (businessChannelMapper.insertSelective(businessChannelInfo)>0){
            return new ResultInfo(0,"","增加商户渠道成功");
        }else {
            return  new ResultInfo(-1,"","增加商户渠道失败");
        }
    }

    /**
     * @Description: 校验商户渠道是否存在
     * @param channelCode
     * @return 
     * @see com.zhiduan.axp.service.store.BusinessChlService#ifExistChannelCode(java.lang.String) 
     */
    @Override
    public ResultInfo ifExistChannelCode(String channelCode) {
        if (businessChannelMapper.ifExistChannelCode(channelCode)==0){
            return new ResultInfo(0,"","商户渠道编码可以使用");
        }else {
            return  new ResultInfo(-1,"","商户渠道编码已存在");
        }
    }
}
