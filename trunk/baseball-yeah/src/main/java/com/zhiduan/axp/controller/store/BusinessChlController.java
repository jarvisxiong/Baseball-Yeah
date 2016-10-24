package com.zhiduan.axp.controller.store;

import com.zhiduan.axp.common.JsonUtils;
import com.zhiduan.axp.common.RandomHelper;
import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.store.BusinessChannelInfo;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.store.BusinessChlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: BusinessChlController
 * @Description: 商户渠道控制层
 * @author ZhangLei
 * @date 2016年8月31日 下午5:12:35 
 */
@Controller
@RequestMapping(value = "/business/chl")
public class BusinessChlController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BusinessChlController.class);

    @Resource(name="businessChlService")
    private BusinessChlService businessChlService;

    /**
     * @Description: 查询所有非系统商户渠道
     * @param request
     * @param response
     * @param businessChannelInfo 
     */
        
    @RequestMapping(value = "/queryAll")
    @ResponseBody
    public void selectAllStoreChannel(HttpServletRequest request, HttpServletResponse response, BusinessChannelInfo businessChannelInfo) {
        DataGrid<BusinessChannelInfo> grid=new DataGrid<BusinessChannelInfo>();
        try {
            if (StringUtils.isEmpty(businessChannelInfo.getSort())) {
                businessChannelInfo.setSort("createDate");
            }
            LOG.error(JsonUtils.translateToJson(businessChannelInfo));
            List<BusinessChannelInfo> list = businessChlService.selectAllBusinessChl(businessChannelInfo);
            grid.setRows(list);
            grid.setTotal(businessChlService.selectAllBusinessChlTotal(businessChannelInfo));
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        writeJson(grid, response);
    }

    /**
     * @Description: 增加商户渠道
     * @param businessChannelInfo
     * @param request
     * @return 
     */
        
    @RequestMapping(value = "/addBussinessChl",method=RequestMethod.POST)
    @ResponseBody
    public ResultInfo<BusinessChannelInfo> addBussinessChl(BusinessChannelInfo businessChannelInfo, HttpServletRequest request) {
        try {
            UserManagerLoginBean userInfo= (UserManagerLoginBean) request.getSession().getAttribute("user");
            businessChannelInfo.setCreateUser(userInfo.getUserManagerId());
            return businessChlService.addBussinessChl(businessChannelInfo);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return new ResultInfo<BusinessChannelInfo>(-1,"1060","增加商户渠道出错");
        }
    }

    /**
     * @Description: 逻辑删除商户渠道
     * @param channelId
     * @param request
     * @return 
     */
        
    @RequestMapping(value = "/delBussinessChl",method=RequestMethod.POST)
    @ResponseBody
    public ResultInfo<BusinessChannelInfo> delBussinessChl(Long channelId, HttpServletRequest request) {
        try {
            return businessChlService.delBussinessChl(channelId);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return new ResultInfo<BusinessChannelInfo>(-1,"1060","删除商户渠道出错");
        }
    }

    /**
     * @Description: 禁用商户渠道
     * @param channelId
     * @param request
     * @return 
     */
        
    @RequestMapping(value = "/cancleBussinessChl",method=RequestMethod.POST)
    @ResponseBody
    public ResultInfo<BusinessChannelInfo> cancleBussinessChl(Long channelId, HttpServletRequest request) {
        try {
            return businessChlService.cancleBussinessChl(channelId);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return new ResultInfo<BusinessChannelInfo>(-1,"1060","禁用商户渠道出错");
        }
    }

    /**
     * @Description: 启用商户渠道
     * @param channelId
     * @param request
     * @return 
     */
        
    @RequestMapping(value = "/enabledBussinessChl",method=RequestMethod.POST)
    @ResponseBody
    public ResultInfo<BusinessChannelInfo> enabledBussinessChl(Long channelId, HttpServletRequest request) {
        try {
            return businessChlService.enabledBussinessChl(channelId);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
            return new ResultInfo<BusinessChannelInfo>(-1,"1060","启用商户渠道出错");
        }
    }

    /**
     * @Description: 验证商户渠道是否存在
     * @param channelCode
     * @param response 
     */
        
    @RequestMapping(value = "/ifExistChannelCode")
    @ResponseBody
    public void ifExistChannelCode(String   channelCode, HttpServletResponse response) {
        ResultInfo resultInfo=null;
        try {
            resultInfo=businessChlService.ifExistChannelCode(channelCode);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        writeJson(resultInfo, response);
    }

    /**
     * @Description: 获取6位商户渠道编码
     * @param response 
     */
        
    @RequestMapping(value = "/getRandomChlCode")
    @ResponseBody
    public void getRandomChlCode(HttpServletResponse response) {
        String channelCodeStr=null;
        try {
            channelCodeStr= RandomHelper.generateString(6);
            while (businessChlService.ifExistChannelCode(channelCodeStr).getSuccess()!=0){
                channelCodeStr=RandomHelper.generateString(6);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        writeJson(channelCodeStr, response);
    }
}
