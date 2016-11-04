/**
 * Copyright (c) 2016, 指端科技.
 */
package com.rofour.baseball.controller.message;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.message.MsgTypeParasInfo;
import com.rofour.baseball.service.message.MsgTypeParasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MsgTypeParasController
 * @Description: 消息类型属性控制器
 * @author: xulang
 * @date: 2016年09月02日 11:19
 */
@Controller
@RequestMapping("/message/typeparas")
public class MsgTypeParasController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("msgTypeParasService")
    private MsgTypeParasService msgTypeParasService;


    /**
     * 查询列表
     * @param request
     * @param response
     * @param msgType
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public DataGrid<MsgTypeParasInfo> queryMsgTypeGrid(HttpServletRequest request, HttpServletResponse response, Integer msgType, Integer limit, Integer offset) {
        List<MsgTypeParasInfo> list = msgTypeParasService.getAll(msgType, limit, offset);
        DataGrid<MsgTypeParasInfo> dataList = new DataGrid<>();
        dataList.setRows(list);
        dataList.setTotal(msgTypeParasService.getAllCount(msgType));
        return dataList;
    }

    /**
     * 查询列表--下拉框数据源
     *
     * @param request
     * @param response
     * @param msgType
     */
    @RequestMapping(value = "/sellist", method = RequestMethod.GET)
    public void getMsgTypeSellList(HttpServletRequest request, HttpServletResponse response, Integer msgType) {
        List<SelectModel> sellist = new ArrayList<>();
        List<MsgTypeParasInfo> list = msgTypeParasService.getAll(msgType, null, null);
        for (MsgTypeParasInfo item : list) {
            SelectModel temp = new SelectModel();
            temp.setText(item.getParasName());
            temp.setId(item.getParasId().toString());
            sellist.add(temp);
        }
        writeJson(sellist, response);
    }

    /**
     * 新增
     *
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addMsgType(HttpServletRequest request, HttpServletResponse response, MsgTypeParasInfo info) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (info == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数不能为空!");
            return result;
        }
        try {
            int id = msgTypeParasService.insert(info);
            if (id <= 0) {
                result.setSuccess(-1);
                result.setMessage("操作失败，添加数据失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(-1);
            result.setMessage("操作失败!");
            return result;
        }
        return result;
    }

    /**
     * 更新
     *
     * @param request
     * @param response
     * @param info
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateMsgType(HttpServletRequest request, HttpServletResponse response, MsgTypeParasInfo info) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (info == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数不能为空!");
            return result;
        }
        try {
            msgTypeParasService.updateById(info);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(-1);
            result.setMessage("操作失败!");
            return result;
        }
        return result;
    }

    /**
     * 删除
     *
     * @param request
     * @param response
     * @param parasId
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delMsgTypeParas(HttpServletRequest request, HttpServletResponse response, Long parasId) {
        ResultInfo result = new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if (parasId == null) {
            result.setSuccess(-1);
            result.setMessage("操作失败，参数id不能为空!");
        }
        try {
            msgTypeParasService.deleteById(parasId);
        } catch (Exception e) {
            result.setSuccess(-1);
            result.setMessage("操作失败，删除数据失败!");
            logger.error(e.getMessage(), logger);
        }
        return result;
    }
}
