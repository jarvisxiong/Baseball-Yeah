package com.zhiduan.axp.controller.message;

import com.zhiduan.axp.controller.base.BaseController;
import com.zhiduan.axp.controller.model.DataGrid;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.SelectModel;
import com.zhiduan.axp.controller.model.message.MsgTypeInfo;
import com.zhiduan.axp.service.message.MsgTypeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息类型控制器
 * Created by wny on 2016-09-01.
 */
@Controller
@RequestMapping(value = "/message/msgType")
public class MsgTypeController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("msgTypeService")
    private MsgTypeService msgTypeService;

    @RequestMapping(value = "/gotoMsgType")
    public ModelAndView msgTypeIndex(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return new ModelAndView("message/msgType/msgTypeManager");
        } else {
            return new ModelAndView("/");
        }
    }

    /**
     * 查询消息类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryMsgTypeGrid", method = RequestMethod.GET)
    public
    @ResponseBody
    List<MsgTypeInfo> queryMsgTypeGrid(
            HttpServletRequest request, HttpServletResponse response) {
        List<MsgTypeInfo> list = msgTypeService.getAllData();
//        DataGrid<MsgTypeInfo> dataList = new DataGrid<>();
//        dataList.setRows(list);
//        dataList.setTotal(list.size());
        return list;
    }

    /***
     * 获取可用消息类型selectModel格式的json字符串
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMsgTypeSellList", method = RequestMethod.GET)
    @ResponseBody
    public void getMsgTypeSellList(HttpServletRequest request, HttpServletResponse response) {
        List<SelectModel> sellist = new ArrayList<>();
        List<MsgTypeInfo> list = msgTypeService.getAllData();
        for (MsgTypeInfo item :list
                ) {
            SelectModel temp = new SelectModel();
            temp.setText(item.getMsgTypeName());
            temp.setId(item.getMsgType().toString());
            sellist.add(temp);
        }

        writeJson(sellist, response);
    }
    /***
     * 获取可用的一级消息类型selectModel格式的json字符串
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getFirstMsgTypeSellList", method = RequestMethod.POST)
    @ResponseBody
    public void getFirstMsgTypeSellList(HttpServletRequest request, HttpServletResponse response) {
        List<SelectModel> sellist = new ArrayList<>();
        List<MsgTypeInfo> list = msgTypeService.getAllData();
        for (MsgTypeInfo item :list
                ) {
            if(item.getTypeLevel()==null||!item.getTypeLevel().equals((byte)1))
            {
                continue;
            }
            SelectModel sel = new SelectModel();
            sel.setId(" ");
            sel.setText("请选择");
            sellist.add(sel);
            SelectModel temp = new SelectModel();
            temp.setText(item.getMsgTypeName());
            temp.setId(item.getMsgType().toString());
            sellist.add(temp);
        }

        writeJson(sellist, response);
    }
    @RequestMapping(value = "/addMsgType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addMsgType(HttpServletRequest request, HttpServletResponse response,MsgTypeInfo info)
    {
        ResultInfo result=new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if(info==null||info.getMsgType()==null||info.getMsgTypeName()==null)
        {
            result.setSuccess(-1);
            result.setMessage("操作失败，类型名称或类型编码不能为空!");
        }
        else
        {
         int id=    msgTypeService.addInfo(info);
            if(id<=0)
            {
                result.setSuccess(-1);
                result.setMessage("操作失败，添加数据失败!");
            }

        }
        return  result;
    }

    @RequestMapping(value = "/updateMsgType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateMsgType(HttpServletRequest request, HttpServletResponse response,MsgTypeInfo info)
    {
        ResultInfo result=new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if(info==null||info.getMsgType()==null||info.getMsgTypeName()==null||info.getId()==null)
        {
            result.setSuccess(-1);
            result.setMessage("操作失败，类型id、类型名称或类型编码不能为空!");
        }
        else
        {
            boolean isSucess=    msgTypeService.updateInfo(info);
            if(!isSucess)
            {
                result.setSuccess(-1);
                result.setMessage("操作失败，更新数据失败!");
            }

        }
        return  result;
    }

    @RequestMapping(value = "/delMsgType", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo delMsgType(HttpServletRequest request, HttpServletResponse response,@Param("info") Integer info)
    {
        ResultInfo result=new ResultInfo();
        result.setSuccess(0);
        result.setMessage("操作成功!");
        if(info==null)
        {
            result.setSuccess(-1);
            result.setMessage("操作失败，类型id不能为空!");
        }
        else
        {
            boolean isSucess=msgTypeService.delInfo(info);
            if(!isSucess)
            {
                result.setSuccess(-1);
                result.setMessage("操作失败，删除数据失败!");
            }

        }
        return  result;
    }
}
