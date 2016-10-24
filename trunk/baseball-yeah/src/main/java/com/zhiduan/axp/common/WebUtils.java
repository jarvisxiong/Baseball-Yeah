package com.zhiduan.axp.common;

import com.zhiduan.axp.controller.model.Permission;
import com.zhiduan.axp.dao.manager.bean.UserManagerLogBean;
import com.zhiduan.axp.dao.user.bean.UserManagerLoginBean;
import com.zhiduan.axp.service.manager.UserManagerLog;
import com.zhiduan.axp.service.user.impl.UserManagerServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WebUtils {

    /**
     * 操作类型枚举 持续添加
     */
//    public enum OperationType {
//        ADD, DELETE, EDIT, QUERY, PRINT, AUDIT, INITPWD
//    }

    @Autowired
    @Qualifier("userManagerLog")
    UserManagerLog userManagerLog;

    private static final Logger logger = LoggerFactory.getLogger(UserManagerServiceImp.class);

    public String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String strRemark = "";
    private String strNewValue = "";
    private String strOldValue = "";

    /**
     * 添加系统日志
     *
     * @param request
     * @param remark        操作备注
     * @param menuId        菜单ID
     * @param operationType 操作类型枚举
     */
    public void userLog(HttpServletRequest request, String remark, long menuId, String newValue, String oldValue, String operationType) {
        UserManagerLogBean model = new UserManagerLogBean();
        UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
        model.setRemark(remark);
        model.setNewValue(newValue);
        model.setOldValue(oldValue);
        model.setMenuId(menuId);
        model.setOperationTime(new Date());
        model.setOperationType(operationType);
        model.setUserManagerId(userManagerLoginBean.getUserManagerId());
        model.setUserName(userManagerLoginBean.getUserName());
        model.setUserIp(getRemoteAddress(request));
        userManagerLog.insert(model);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(String.format("日期 ：%s,操作人：%s,操作：%s%s%s", format.format(new Date()), userManagerLoginBean.getUserManagerId(), remark,strNewValue,strOldValue ));
    }

    /**
     * 数据新增系统日志
     *
     * @param request
     * @param menuId  菜单ID
     * @param model   新增的对象
     */
    public void userAddLog(HttpServletRequest request, long menuId, Object model) {
        try {
            strRemark = "新增";
            strNewValue = JsonUtils.translateToJson(model);
        } catch (IOException e) {
            e.printStackTrace();
        }

        userLog(request, strRemark, menuId, strNewValue, "", Permission.ADD.name());
    }

    /**
     * 数据编辑日志
     *
     * @param request
     * @param menuId      菜单ID
     * @param editModel   旧数据对象
     * @param updateModel 更新的新对象
     */
    public void userEditLog(HttpServletRequest request, long menuId, Object editModel, Object updateModel) {
        try {
            strRemark = "编辑";
            strOldValue = JsonUtils.translateToJson(editModel);
            strNewValue = JsonUtils.translateToJson(updateModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userLog(request, strRemark, menuId, strNewValue, strOldValue, Permission.EDIT.name());
    }

    /**
     * 数据删除日志
     *
     * @param request
     * @param menuId      菜单ID
     * @param deleteModel 删除的数据对象
     */
    public void userDeleteLog(HttpServletRequest request, long menuId, Object deleteModel) {
        try {
            strRemark = "删除";
            strOldValue = JsonUtils.translateToJson(deleteModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userLog(request, strRemark, menuId, "", strOldValue, Permission.DELETE.name());
    }

}
