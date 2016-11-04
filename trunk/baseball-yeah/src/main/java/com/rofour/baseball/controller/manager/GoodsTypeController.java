package com.rofour.baseball.controller.manager;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.GoodsTypeInfo;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.GoodsTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.awt.SystemColor.info;


/**
 * Created by Administrator on 2016-08-09.
 * 物品类型控制器
 */
@Controller
@RequestMapping("/manage/goodstype")
public class GoodsTypeController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource(name="goodsTypeService")
    private GoodsTypeService goodsTypeService;

    /**
     * 查询物品类型
     * @param request
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public List<GoodsTypeInfo> query(HttpServletRequest request)
    {
        try {
            return goodsTypeService.getByCondition();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * insert
     * @Method: insert
     * @Description: 新增物品类型
     * @param @param request
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年7月20日 上午10:47:14
     *
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResultInfo<Object> insert(HttpServletRequest request)
    {
        try {
            String data=request.getParameter("data");
            GoodsTypeInfo info= JsonUtils.readValue(data, GoodsTypeInfo.class);
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            if (sessionUser != null) {
                info.setUpdateUser(Long.valueOf(sessionUser.getUserManagerId().toString()));
            }
            return goodsTypeService.addGoodsType(info);
        } catch (Exception e) {
            return processException(e,logger);
        }
    }

    /**
     *
     * @Method:update
     * @Description: 修改物品类型
     * @param @param request
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年7月20日 上午10:47:14
     *
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo<Object> update(HttpServletRequest request)
    {
        try {
            String data=request.getParameter("data");
            GoodsTypeInfo info=JsonUtils.readValue(data, GoodsTypeInfo.class);
            UserManagerLoginBean sessionUser = (UserManagerLoginBean) request.getSession().getAttribute("user");
            if (sessionUser != null) {
                info.setUpdateUser(Long.valueOf(sessionUser.getUserManagerId().toString()));
            }
            return goodsTypeService.updateGoodsType(info);
        } catch (Exception e) {
            return processException(e,logger);
        }
    }
    /**
     *
     * @Method: delete
     * @Description: 删除物品类型
     * @param @param request
     * @param @return    参数
     * @return ResultInfo<String>    返回类型
     * @throws
     * @author heyi
     * @date 2016年7月20日 上午10:51:20
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
        public ResultInfo<Object> delete(HttpServletRequest request)
    {
        try {
            String data=request.getParameter("goodsTypeId");
            return goodsTypeService.delGoodsType(Long.valueOf(data));

        } catch (Exception e) {
            return processException(e,logger);
        }
    }
}
