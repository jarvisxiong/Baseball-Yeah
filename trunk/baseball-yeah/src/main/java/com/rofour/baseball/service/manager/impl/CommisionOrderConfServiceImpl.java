/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.service.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.CommonConsistEnum;
import com.rofour.baseball.dao.manager.bean.CommisionOrderConfBean;
import com.rofour.baseball.dao.manager.mapper.CommisionOrderConfMapper;
import com.rofour.baseball.dao.user.bean.UserStoreExpBean;
import com.rofour.baseball.dao.user.mapper.UserStoreExpMapper;
import com.rofour.baseball.service.manager.CommisionOrderConfService;

import net.sf.json.JSONArray;

/**
 * @ClassName: CommisionOrderConfServiceImpl
 * @Description: 抽成规则维护服务类
 * @author Administrator
 * @date 2016年10月26日 下午5:32:12 
 */
@Service("commisionOrderConfService")
public class CommisionOrderConfServiceImpl implements CommisionOrderConfService {

	@Autowired
	@Qualifier("commisionOrderConfMapper")
	CommisionOrderConfMapper commisionOrderConfMapper;
	
    @Autowired
    @Qualifier("userStoreExpMapper")
    private UserStoreExpMapper userStoreExpMapper;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

	
    /**
     * 增加一条配置
     * 
     */
	@Override
	public int insert(CommisionOrderConfBean bean) {
		//创建时间是当前时间
        bean.setCreateDate(new Date());
        //默认状态是禁用
        bean.setState(0);
        bean.setCostMode(0);
        //元转换成分
	    try {
		   bean.setCostValue(new Float(bean.getCostValueYuan()*100).longValue());
	    } catch (Exception e) {
	    	logger.error(e.getMessage(), e);
	    	bean.setCostValue(0L);
	    }
	    //商户的话加入store_id
	    if(StringUtils.equals(bean.getRoleType(), CommonConsistEnum.ROLE_STORE.getCode())){
	    	//查询出对应的store_id 
		    UserStoreExpBean userStoreExp = userStoreExpMapper.selectByPrimaryKey(bean.getUserId());
		    if(null!=userStoreExp){
		    	bean.setStoreId(userStoreExp.getStoreId());
		    }
	    }
	    //coo,导入费的话加入store_id
	    if((StringUtils.equals(bean.getRoleType(), CommonConsistEnum.ROLE_COO.getCode()) 
				&& StringUtils.equals(bean.getCostType(), CommonConsistEnum.STATE_SERVICE_COST.getCode()))){
	    	if(StringUtils.isNotEmpty(String.valueOf(bean.getSupervisorId()))){
	    		//查询出对应的store_id 
			    UserStoreExpBean userStoreExp = userStoreExpMapper.selectByPrimaryKey(bean.getSupervisorId());
			    if(null!=userStoreExp){
			    	bean.setStoreId(userStoreExp.getStoreId());
			    }
	    	}
	    }
		return commisionOrderConfMapper.insert(bean);
	}

	@Override
	public int updateCostValue(CommisionOrderConfBean bean) {
        //元转换成分
	    try {
		    bean.setCostValue(new Float(bean.getCostValueYuan()*100).longValue());
	    } catch (Exception e) {
	    	logger.error(e.getMessage(), e);
		    bean.setCostValue(0L);
	    }
		return commisionOrderConfMapper.updateCostValueByPrimaryKey(bean);
	}
	

	@Override
	public int del(Long id) {
		return commisionOrderConfMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 按条件查询配置
	 */
	@Override
	public List<CommisionOrderConfBean> getCommisionOrderConfList(CommisionOrderConfBean bean) {
		return commisionOrderConfMapper.getCommisionOrderConfList(bean);
	}

	/**
	 * 查询符合条件的总条数
	 */
	@Override
	public int selectAllCount(CommisionOrderConfBean bean) {
		return commisionOrderConfMapper.selectAllCount(bean);
	}

    /**
     * 增加的时候判断，是否已经存在相同的记录
     * 一下情况视为重复：CEO服务费，COO服务费，根据：角色，用户ID，订单类型，费用类型判断
	 * COO导入费的话：角色，用户ID，订单类型，费用类型，商户手机号
	 * 商户服务费的话：角色，用户ID，学校ID，订单类型，费用类型
     * */
	@Override
	public int isExistSameRecord(CommisionOrderConfBean bean) {
		CommisionOrderConfBean copyOne = new CommisionOrderConfBean();
		BeanUtils.copyProperties(bean, copyOne);
		int result = 0;
		//不是商户不考虑学校
		if(!StringUtils.equals(bean.getRoleType(), CommonConsistEnum.ROLE_STORE.getCode()) ){
			copyOne.setCollegeId(null);
		}
		//不是COO和导入费不考虑被导入的商户
		if(!StringUtils.equals(bean.getRoleType(), CommonConsistEnum.ROLE_COO.getCode()) 
				|| !StringUtils.equals(bean.getCostType(), CommonConsistEnum.STATE_SERVICE_COST.getCode())){
			copyOne.setSupervisorId(null);
		}
		result = commisionOrderConfMapper.isExtNormalCostType(copyOne);
		return result;
	}

	/**
	 * 根据手机号带出姓名和学校信息
	 */
	@Override
	public CommisionOrderConfBean getNormalUserInfoByPhone(String phone,String roleType) {
		CommisionOrderConfBean bean = new CommisionOrderConfBean();
		bean.setPhone(phone);
		if(StringUtils.equals(roleType, CommonConsistEnum.ROLE_COO.getCode())){
			bean.setRoleType("2");
		}else{
			bean.setRoleType("3");
		}
		return commisionOrderConfMapper.getUserInfoByPhone(bean);
	}
	
	/**
	 * 根据手机号带出商户姓名和学校信息
	 * 
	 */
	@Override
	public CommisionOrderConfBean getStoreUserInfoByPhone(String phone) {
		return commisionOrderConfMapper.getStoreUserInfoByPhone(phone);
	}
	
	/**
	 * 根据coo_id查询出关联的商户
	 */
	@Override
	public List<CommisionOrderConfBean> getSupervisorListByCooId(Long userId) {
		return commisionOrderConfMapper.getSupervisorListByCooId(userId);
	}

	/**
	 * 禁用一批配置
	 */
	@Override
	public int disable(CommisionOrderConfBean bean) {
		JSONArray ids = JSONArray.fromObject(bean.getIds());
		Object[] _ids = ids.toArray();
		return commisionOrderConfMapper.disable(_ids);
	}

	/**
	 * 启用一批配置
	 */
	@Override
	public int enable(CommisionOrderConfBean bean) {
		JSONArray ids = JSONArray.fromObject(bean.getIds());
		Object[] _ids = ids.toArray();
		return commisionOrderConfMapper.enable(_ids);
	}
	
	
}
