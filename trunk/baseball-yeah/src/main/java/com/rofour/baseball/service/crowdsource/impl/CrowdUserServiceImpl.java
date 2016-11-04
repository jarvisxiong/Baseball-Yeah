package com.rofour.baseball.service.crowdsource.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.*;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.Permission;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.resource.CredentialURLInfo;
import com.rofour.baseball.dao.crowdsource.bean.CollegePieBean;
import com.rofour.baseball.dao.crowdsource.bean.CrowdsourceBean;
import com.rofour.baseball.dao.crowdsource.mapper.CrowdUserMapper;
import com.rofour.baseball.dao.order.bean.PacketOrderBean;
import com.rofour.baseball.service.crowdsource.CrowdUserService;
import com.rofour.baseball.service.resource.ResourceService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */

@Service("crowdUserService")
public class CrowdUserServiceImpl implements CrowdUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    @Qualifier("crowdUserMapper")
    private CrowdUserMapper crowdUserMapper;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    // 删除用户缓存中的数据
    private void deleteUserRedis(long userManagerId) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        Map<String, String> ma = hashOperations.entries("axp:user:login" + userManagerId);
        if (ma != null) {
            for (Map.Entry<String, String> entry : ma.entrySet()) {
                redisTemplate.delete(entry.getValue());
            }
        }
    }

    @Override
    public DataGrid<CrowdsourceBean> getCrowdsourceList(CrowdsourceBean crowdsourceBean) throws Exception {
        List<CrowdsourceBean> list = null;
        int total = 0;
        if (!StringUtils.isEmpty(crowdsourceBean.getSearchType())) {
            list = crowdUserMapper.getListForCoOrCollege(crowdsourceBean);
            total = crowdUserMapper.getListForCoOrCollegeCount(crowdsourceBean);
        } else {
            list = crowdUserMapper.getCrowdsourceList(crowdsourceBean);
            total = crowdUserMapper.getCrowdsourceListCount(crowdsourceBean);
        }
        DataGrid<CrowdsourceBean> dataList = new DataGrid<CrowdsourceBean>();
        dataList.setRows(list);
        dataList.setTotal(total);
        return dataList;
    }

    @Override
    public int getCrowdsourceListCount(CrowdsourceBean crowdsourceBean) throws Exception {
        return crowdUserMapper.getCrowdsourceListCount(crowdsourceBean);
    }

    @Override
    public CrowdsourceBean getCrowdUserDetail(CrowdsourceBean crowdsourceBean) throws Exception {
        CrowdsourceBean bean = null;
        try {
            bean = crowdUserMapper.getCrowdUserDetail(crowdsourceBean);
            if (bean != null) {
                List<CredentialURLInfo> iconList = setUserImgUrlInfo(bean.getUserId(), AttachConstant.TYPE_AUTH_PACKET);
                if (bean.getAccountImg() != null) {
                    List<CredentialURLInfo> cardList = setUserImgUrlInfo(bean.getUserId(), AttachConstant.TYPE_USER);
                    if (CollectionUtils.isNotEmpty(iconList) && CollectionUtils.isNotEmpty(cardList)) {
                        iconList.addAll(cardList);
                    }
                }
                if (CollectionUtils.isNotEmpty(iconList)) {
                    bean.setPhotoList(iconList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public CollegePieBean getCollegeDetail(CollegePieBean collegePieBean) throws Exception {
        return crowdUserMapper.getCollegeDetail(collegePieBean);
    }

    @Override
    public int updateState(CrowdsourceBean crowdsourceBean, HttpServletRequest request) throws Exception {

        int retuni = crowdUserMapper.updateState(crowdsourceBean);
        for (String userId : crowdsourceBean.getUserIdArr()) {
            if (crowdsourceBean.getUserState().equals("0")) {
                webUtils.userLog(request, String.format("用户ID：%s 封存", userId), 143, "", "", Permission.SEALED.name());
                deleteUserRedis(Long.parseLong(userId));
            } else {
                webUtils.userLog(request, String.format("用户ID：%s 激活", userId), 143, "", "", Permission.ACTIVATION.name());
            }
        }


        return retuni;
    }

    @Override
    public int UpdateUser(CrowdsourceBean crowdsourceBean) throws Exception {
        Integer i = crowdUserMapper.updateUser(crowdsourceBean);
        deleteUserRedis(crowdsourceBean.getUserId());
        return i;
    }

    @Override
    public PacketOrderBean getOrderDetail(PacketOrderBean bean) throws Exception {
        return crowdUserMapper.getOrderDetail(bean);
    }


    /**
     * 获取用户照片
     *
     * @param bizId
     * @param
     */
    private List<CredentialURLInfo> setUserImgUrlInfo(long bizId, String creType) {
        List<CredentialURLInfo> credentialURLInfo = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", bizId);
            map.put("attachType", creType);

            String url = Constant.axpurl.get("resource_getOriginAttachList_serv");

            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo credentialUrls = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            if (null != credentialUrls && credentialUrls.getSuccess() == 1) {
                credentialURLInfo = (List<CredentialURLInfo>) credentialUrls.getData();
//                if (CollectionUtils.isNotEmpty(credentialURLInfo)) {
//                    crowdsourceBean.setPhotoList(credentialURLInfo);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentialURLInfo;
    }
}
