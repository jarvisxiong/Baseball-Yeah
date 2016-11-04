/**
 * Copyright (c) 2016, 指端科技.
 */

package com.rofour.baseball.service.manager.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.RequestWorkSigninInfo;
import com.rofour.baseball.dao.manager.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.common.RedisCommons;
import com.rofour.baseball.common.RedisKeyConstants;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.Permission;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.manager.CityCollegeInfo;
import com.rofour.baseball.controller.model.manager.CollegeInfo;
import com.rofour.baseball.controller.model.manager.CollegeManageInfo;
import com.rofour.baseball.dao.manager.mapper.CityMapper;
import com.rofour.baseball.dao.manager.mapper.CollegeMapper;
import com.rofour.baseball.dao.manager.mapper.CountyMapper;
import com.rofour.baseball.dao.manager.mapper.PropertyDictMapper;
import com.rofour.baseball.dao.manager.mapper.ProvinceMapper;
import com.rofour.baseball.exception.BusinessException;
import com.rofour.baseball.service.manager.CollegeService;
import com.rofour.baseball.service.wallet.impl.WalletDrawServiceImpl;
/**
 * @author xl
 * @ClassName: TbCollegeImpl
 * @Description: 管理中心--学校操作实现类
 * @date 2016年3月26日 下午1:08:01
 */

@Service("collegeService")
public class CollegeImpl implements CollegeService {

    @Autowired
    @Qualifier("collegeMapper")
    private CollegeMapper collegeMapper;

    @Autowired
    @Qualifier("cityMapper")
    private CityMapper citymapper;

    @Autowired
    @Qualifier("countyMapper")
    private CountyMapper countyMapper;

    @Autowired
    @Qualifier("provinceMapper")
    private ProvinceMapper provincemapper;

    @Autowired
    @Qualifier("propertyDictMapper")
    private PropertyDictMapper propertyDictMapper;

    @Autowired
    private WebUtils webUtils;
    
    @Resource(name = "redisCommons")
    private RedisCommons redisCommons;
    Logger logger = LoggerFactory.getLogger(WalletDrawServiceImpl.class);

    /**
     * @return
     * @Description: 查询学校信息列表
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#getCollegeList()
     */
    @Override
    public List<CollegeInfo> getCollegeList() {
        List<CollegeBean> list = collegeMapper.selectAll();
        List<CollegeInfo> datalist = new ArrayList<CollegeInfo>();
        for (CollegeBean item : list) {
            CollegeInfo college = new CollegeInfo();
            BeanUtils.copyProperties(item, college);
            datalist.add(college);
        }

        return datalist;
    }

    
    /**
     * @return
     * @Description: 查询学校信息列表
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#getCollegeList()
     */
    @Override
    public List<CollegeBean> getCollegeBeanList() {
        List<CollegeBean> list = collegeMapper.selectAll();
        return list;
    }
    /**
     * @return
     * @Description: 查询学校下拉列表
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#getCollegeList()
     */
    @Override
    public List<CollegeInfo> getSelectCollegeList() {
        List<CollegeSelectBean> list = collegeMapper.querySelect();
        List<CollegeInfo> datalist = new ArrayList<CollegeInfo>();
        for (CollegeSelectBean item : list) {
            CollegeInfo college = new CollegeInfo();
            BeanUtils.copyProperties(item, college);
            datalist.add(college);
        }

        return datalist;
    }

    /**
     * @param college
     * @param request
     * @return
     * @Description: 保存学校信息
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#saveCollege(com.rofour.baseball.idl.managecenter.service.entity.CollegeInfo)
     */
    @Override
    public ResultInfo saveCollege(CollegeInfo college, HttpServletRequest request) {
        validateInfo(college);
        CollegeBean collegeBean = new CollegeBean();
        BeanUtils.copyProperties(college, collegeBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("collegeCode", collegeBean.getCollegeCode());
        map.put("fullName", collegeBean.getFullName());
        if (collegeMapper.isCollegeExist(map) > 0) {
            throw new BusinessException("01026");
        }
        int status = collegeMapper.insert(collegeBean);
        webUtils.userAddLog(request, 15, collegeBean);
        if (status != 0) {
            //删除学校的缓存
            delCache();
            removeCollegeCache();
            return new ResultInfo(0, "", "保存成功", "");
        } else {
            throw new BusinessException("01027");
        }

    }

    /**
     * @param collegeId
     * @param request
     * @return
     * @Description: 删除学校信息
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#deleteCollege(java.lang.Long)
     */
    @Override
    public int deleteCollege(Long collegeId, HttpServletRequest request) {
        if (collegeId == null)
            throw new BusinessException("111");
        int status = collegeMapper.delete(collegeId);
        //删除学校的缓存
        removeCollegeCache();
        webUtils.userDeleteLog(request, 15, collegeId);
        if (status == 0) {
            throw new BusinessException("01027");
        }
        delCache();
        return status;
    }

    /**
     * @param college
     * @param request
     * @return
     * @Description: 更新学校信息
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#updateCollege(com.rofour.baseball.idl.managecenter.service.entity.CollegeInfo)
     */
    @Override
    public ResultInfo updateCollege(CollegeInfo college, HttpServletRequest request) {
        if (college.getCollegeId() == null) {
            throw new BusinessException("111");
        }
        validateInfo(college);
        CollegeBean collegeBean = new CollegeBean();
        BeanUtils.copyProperties(college, collegeBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("collegeId", collegeBean.getCollegeId());
        map.put("collegeCode", collegeBean.getCollegeCode());
        map.put("fullName", collegeBean.getFullName());
        if (collegeMapper.isCollegeExist(map) > 0) {
            throw new BusinessException("01026");
        }
        CollegeBean collegeOldBean = collegeMapper.selectById(collegeBean.getCollegeId());
        int updateCount = collegeMapper.update(collegeBean);
        //删除学校的缓存
        removeCollegeCache();
        webUtils.userEditLog(request, 15, collegeOldBean, collegeBean);
        if (updateCount == 0) {
            throw new BusinessException("01027");
        }
        delCache();
        return new ResultInfo(0, "0", "学校更新成功", "");
    }

    /**
     * @param cityId
     * @return
     * @Description: 根据城市ID查询学校信息
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#selectByCityId(java.lang.Long)
     */
    @Override
    public List<CityCollegeInfo> selectByCityId(Long cityId) {
        List<CityCollegeBean> collegeBeans = collegeMapper.selectByCityId(cityId);
        List<CityCollegeInfo> collegeInfos = new ArrayList<CityCollegeInfo>();

        for (CityCollegeBean item : collegeBeans) {
            CityCollegeInfo cityCollegeInfo = new CityCollegeInfo();
            BeanUtils.copyProperties(item, cityCollegeInfo);
            collegeInfos.add(cityCollegeInfo);
        }

        return collegeInfos;
    }

    /**
     * @param cityId
     * @return
     * @Description: 根据城市ID查询学校信息
     * @see com.rofour.baseball.idl.managecenter.service.CollegeService#selectByCityId(java.lang.Long)
     */
    @Override
    public List<CityCollegeBean> selectFromCityId(Long cityId) {
        List<CityCollegeBean> collegeBeans=new ArrayList<CityCollegeBean>();
        collegeBeans = collegeMapper.selectByCityId(cityId);
        return collegeBeans;
    }

    /**
     * @param college
     * @return
     * @Description: 字段校验
     */

    private void validateInfo(CollegeInfo college) {
        if (StringUtils.isBlank(college.getCollegeCode()) || StringUtils.isBlank(college.getFullName())
                || StringUtils.isBlank(college.getSimpleName()) || StringUtils.isBlank(college.getNature())
                || StringUtils.isBlank(college.getCollegeType()) || college.getStatus() == null) {
            throw new BusinessException("111");
        }
        if (college.getCollegeCode().length() > 20 || college.getFullName().length() > 200
                || college.getSimpleName().length() > 100 || college.getRemark().length() > 100
                || college.getStatus().toString().length() > 1 || college.getNature().length() > 20
                || college.getCollegeType().length() > 20) {
            throw new BusinessException("112");
        }

    }


    @Override
    public List<CollegeManageInfo> selectManageCollegeInfo() {
        List<CollegeManageBean> list = collegeMapper.selectManageCollegeInfo();
        List<CollegeManageInfo> datalist = new ArrayList<CollegeManageInfo>();

        for (CollegeManageBean item : list) {
            CollegeManageInfo college = new CollegeManageInfo();
            BeanUtils.copyProperties(item, college);
            datalist.add(college);
        }

        return datalist;
    }

    @Override
    public List<CollegeManageBean> selectCollegeAJAX(CollegeBean collegeBean) {
        List<CollegeManageBean> list = collegeMapper.selectCollegeAJAX(collegeBean);
        return list;
    }

    public List<CollegeManageBean> selectCollegeForEdit(CollegeBean collegeBean) {
        
        List<CollegeManageBean> list = collegeMapper.selectCollegeForEdit(collegeBean);
        return list;
    }
    /**
     * @return
     * @Description: 学校新增修改控件数据源
     * @see com.rofour.baseball.service.manager.CollegeService#getPropertyInfos()
     */
    @Override
    public ResultInfo<Object> getPropertyInfos() {
        ResultInfo<Object> resultInfo = new ResultInfo<Object>();
        SelectModel sel = new SelectModel();
        // sel.setId("");
        // sel.setText("请选择");
        // 省
        List<SelectModel> provinceInfos = new ArrayList<SelectModel>();
        // provinceInfos.add(sel);
        // 市
        List<SelectModel> cityInfos = new ArrayList<SelectModel>();
        // cityInfos.add(sel);
        // 区
        List<SelectModel> countyInfos = new ArrayList<SelectModel>();
        // countyInfos.add(sel);
        // 学校类型
        List<SelectModel> collegeTypes = new ArrayList<SelectModel>();
        // collegeTypes.add(sel);
        // 学校性质
        List<SelectModel> natures = new ArrayList<SelectModel>();
        // natures.add(sel);

        List<ProvinceBean> provinceBeans = provincemapper.selectAllProvince();
        for (ProvinceBean item : provinceBeans) {
            sel = new SelectModel();
            sel.setId(item.getProvinceId().toString());
            sel.setText(item.getProvinceName());
            provinceInfos.add(sel);
        }
        /*String provicneRedisKey = RedisKeyConstants.PROVINCE_ALL_MAP;
        List<ProvinceInfo> provinces = redisCommons.getList(provicneRedisKey, ProvinceInfo.class);
        if(CollectionUtils.isEmpty(provinces)){
        	provinces = new ArrayList<ProvinceInfo>();
        	List<ProvinceBean> provinceBeans = provincemapper.selectAllProvince();
        	if(CollectionUtils.isNotEmpty(provinceBeans)){
        		for (ProvinceBean item : provinceBeans) {
					ProvinceInfo province = new ProvinceInfo();
					BeanUtils.copyProperties(item, province);
					provinces.add(province);
				}
				redisCommons.set(provicneRedisKey, provinces);
        	}
        }
        for (ProvinceInfo item : provinces) {
            sel = new SelectModel();
            sel.setId(item.getProvinceId().toString());
            sel.setText(item.getProvinceName());
            provinceInfos.add(sel);
        }*/
        List<CityBean> cityBeans = citymapper.selectAll();
        for (CityBean item : cityBeans) {
            sel = new SelectModel();
            sel.setId(item.getCityId().toString());
            sel.setText(item.getCityName());
            cityInfos.add(sel);
        }
        /*String cityRedisKey = RedisKeyConstants.CITY_ALL_MAP;
        List<CityInfo> citys = redisCommons.getList(cityRedisKey, CityInfo.class);
        if(CollectionUtils.isEmpty(citys)){
        	citys = new ArrayList<CityInfo>();
        	List<CityBean> cityBeans = citymapper.selectAll();
        	if(CollectionUtils.isNotEmpty(cityBeans)){
        		for (CityBean item : cityBeans) {
					CityInfo city = new CityInfo();
					BeanUtils.copyProperties(item, city);
					citys.add(city);
				}
				redisCommons.set(cityRedisKey, citys);
        	}
        }
        for (CityInfo item : citys) {
            sel = new SelectModel();
            sel.setId(item.getCityId().toString());
            sel.setText(item.getCityName());
            cityInfos.add(sel);
        }*/
        List<CountyBean> countyBeans = countyMapper.selectAll();
          for (CountyBean item : countyBeans) {
            sel = new SelectModel();
            sel.setId(item.getCountyId().toString());
            sel.setText(item.getCountyName());
            countyInfos.add(sel);
        }
        /*String countyRedisKey = RedisKeyConstants.COUNTY_ALL_MAP;
        List<CountyInfo> countys = redisCommons.getList(countyRedisKey, CountyInfo.class);
        if(CollectionUtils.isEmpty(countys)){
        	countys = new ArrayList<CountyInfo>();
        	List<CountyBean> countyBeans = countyMapper.selectAll();
        	if(CollectionUtils.isNotEmpty(countyBeans)){
        		for (CountyBean item : countyBeans) {
					CountyInfo county = new CountyInfo();
					BeanUtils.copyProperties(item, county);
					countys.add(county);
				}
				redisCommons.set(countyRedisKey, countys);
        	}
        }
        for (CountyInfo item : countys) {
            sel = new SelectModel();
            sel.setId(item.getCountyId().toString());
            sel.setText(item.getCountyName());
            countyInfos.add(sel);
        }*/
        List<PropertyDictBean> propertyDictBeans = propertyDictMapper.getPropertyDictList();
        for (PropertyDictBean item : propertyDictBeans) {
            sel = new SelectModel();
            sel.setId(item.getPropertyId().toString());
            sel.setText(item.getPropertyValue());
            if ("COLLEGE_TYPE".equals(item.getCallAlias())) {
                collegeTypes.add(sel);
            } else if ("NATURE".equals(item.getCallAlias())) {
                natures.add(sel);
            }
        }
        Map<String, List<?>> map = new HashMap<String, List<?>>();
        map.put("province", provinceInfos);
        map.put("city", cityInfos);
        map.put("county", countyInfos);
        map.put("collegeType", collegeTypes);
        map.put("nature", natures);
        resultInfo.setSuccess(0);
        resultInfo.setData(map);
        return resultInfo;
    }

    /**
     * @param isOk
     * @param collegeIds
     * @return
     * @throws IOException
     * @Description: 修改学校众包模式
     * @see com.rofour.baseball.service.manager.CollegeService#changePacketModel(java.lang.String,
     * java.lang.String[])
     */
    @Override
    public int changePacketModel(HttpServletRequest request, String isOk, String... collegeIds) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isOk", isOk);
        map.put("collegeIds", collegeIds);
        int rows = collegeMapper.changePacketModel(map);
        removeCollegeCache();
        delCache();
        String oldPacketModels = collegeMapper.getPacketMode(map);
        webUtils.userLog(request,
                String.format("学校ID：%s 众包模式状态由{%s} 改为：%s", JsonUtils.translateToJson(collegeIds), oldPacketModels, isOk),
                15, "", "", Permission.AUDIT.name());
        return rows;
    }

    /**
     * 获取明天的日期
     * @return
     */
    private Date getTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Override
    public List<CollegeBean> selectCollegeAndWorkSignin(Long cityId){
        HashMap<String, String> map = new HashMap<>();
        map.put("cityId",cityId+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("date",sdf.format(this.getTomorrow()));
        List<CollegeBean> collegeBeanList=new ArrayList<CollegeBean>();
        collegeBeanList = collegeMapper.selectCollegeAndWorkSignin(map);
        return collegeBeanList;
    }

    @Override
    public List<CollegeBean> selectCollegeAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo){
        List<CollegeBean> collegeBeanList=new ArrayList<CollegeBean>();
        collegeBeanList = collegeMapper.selectCollegeAndWorkSigninPage(requestWorkSigninInfo);
        return collegeBeanList;
    }

    @Override
    public List<PuserBean> selectPuserAndWorkSigninPage(RequestWorkSigninInfo requestWorkSigninInfo){
        List<PuserBean> puserBeanList=new ArrayList<PuserBean>();
        puserBeanList = collegeMapper.selectPuserAndWorkSigninPage(requestWorkSigninInfo);
        return puserBeanList;
    }

    @Override
    public int getCollegeTotal(Long cityId){
        return collegeMapper.getCollegeTotal(cityId);
    }

    @Override
    public int getPuserTotal(Long collegeId){
        return collegeMapper.getPuserTotal(collegeId);
    }


    @Override
    public List<PuserBean> selectPuserAndWorkSignin(Long collegeId){
        HashMap<String, String> map = new HashMap<>();
        map.put("collegeId",collegeId+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("date",sdf.format(this.getTomorrow()));
        List<PuserBean> puserBeanList=new ArrayList<PuserBean>();
        puserBeanList = collegeMapper.selectPuserAndWorkSignin(map);
        return puserBeanList;
    }

    /**
     * 删除学校的缓存
     */
    private void removeCollegeCache() {
        final TypeReference<ResultInfo<?>> colleageTypeRef = new TypeReference<ResultInfo<?>>() {
        };
        String url = Constant.axpurl.get("college_remove_serv");
        ResultInfo<?> data = null;
        try {
            data = (ResultInfo<?>) HttpClientUtils.post(url, null, colleageTypeRef);
            if (data.getSuccess() != 0) {
                logger.error("调用AXP接口失败:" + data.getCode() + "," + data.getMessage());
                throw new BusinessException("104");
            }
        } catch (IOException e) {
            throw new BusinessException("104");
        }
    }
    
    public void delCache()
    {
        String redisKey = RedisKeyConstants.COLLEAGE_MAP;
        String key=RedisKeyConstants.COLLEAGE_LIST;
        redisCommons.delete(redisKey);
        redisCommons.delete(key);
    }

    /**
     * 重载方法
     * @param collegeId
     * @return
     */
    @Override
    public CollegeBean selectByCollegeId(Long collegeId)
    {
        return collegeMapper.selectById(collegeId);
    }
}
