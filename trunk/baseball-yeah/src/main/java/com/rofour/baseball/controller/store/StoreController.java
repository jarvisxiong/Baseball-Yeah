package com.rofour.baseball.controller.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.JsonUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.SelectModel;
import com.rofour.baseball.controller.model.store.AxpStoreInfo;
import com.rofour.baseball.controller.model.store.CustomAxpInfo;
import com.rofour.baseball.controller.model.store.CustomStoreInfo;
import com.rofour.baseball.controller.model.store.DelInfo;
import com.rofour.baseball.controller.model.store.ExpStoreInfo;
import com.rofour.baseball.controller.model.store.SearchStoreInfo;
import com.rofour.baseball.controller.model.store.SimpleStoreInfo;
import com.rofour.baseball.controller.model.store.StoreInfo;
import com.rofour.baseball.service.store.StoreService;

/**
 * @author WJ
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
@Controller
@RequestMapping(value = "/store/exp", method = RequestMethod.POST)
public class StoreController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private StoreService storeService;

    @ResponseBody
    @RequestMapping("/add")
    public ResultInfo add(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始添加商户信息[data:" + data + "]");
        StoreInfo store;
        try {
            store = JsonUtils.readValue(data, StoreInfo.class);
        } catch (Exception e) {
            LOG.error("解析商户信息失败");
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }

        ResultInfo result;
        try {
            storeService.add(store);
            result = new ResultInfo();
        } catch (Exception e) {
            LOG.error("添加商户信息失败", e);
            result = new ResultInfo(-1, "1030010002", "操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delbatch")
    public ResultInfo delbatch(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始批量删除商户信息[data:" + data + "]");
        List<Integer> ids = new ArrayList<Integer>();
        try {
            DelInfo delInfo = JsonUtils.readValue(data, DelInfo.class);
            ids = delInfo.getIds();
        } catch (Exception e) {
            LOG.error("解析商户信息失败");
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }
        int num = 0;
        try {
            if (storeService.existCoo(ids) > 0) {
                return new ResultInfo(-1, "", "请解除与COO的挂靠关系后再尝试删除");
            }
            if (storeService.getStoreUser(ids) > ids.size()) {
                return new ResultInfo(-1, "", "站点下不止一个用户不能删除");
            }
            num = storeService.removeByIds(ids, req);
        } catch (Exception e) {
            LOG.error("删除商户信息失败", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
        if (num == 0) {
            LOG.info("没有此条商户信息");
            return new ResultInfo(0, "1030010004", "没有此条记录");
        }
        LOG.info("删除商户信息成功");
        return new ResultInfo(0, "", "OK");
    }

    @ResponseBody
    @RequestMapping("/del")
    public ResultInfo del(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始删除商户信息[data:" + data + "]");
        String id;
        try {
            id = JsonUtils.readValueByName(data, "id");
        } catch (Exception e) {
            LOG.error("解析商户信息失败");
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }
        if (id == null || !id.matches("^\\d+$")) {
            LOG.error("传入参数错误");
            return new ResultInfo(-1, "1030010003", "传入参数错误");
        }
        int num = 0;
        try {
            num = storeService.removeById(Long.valueOf(id));
        } catch (Exception e) {
            LOG.error("删除商户信息失败", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
        if (num == 0) {
            LOG.info("没有此条商户信息");
            return new ResultInfo(0, "1030010004", "没有此条记录");
        }
        LOG.info("删除商户信息成功");
        return new ResultInfo(0, "", "OK");
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultInfo update(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始更新商户信息[data:" + data + "]");
        StoreInfo store;
        try {
            store = JsonUtils.readValue(data, StoreInfo.class);
        } catch (Exception e) {
            LOG.error("解析商户信息失败");
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }

        ResultInfo result;
        try {
            storeService.update(store);
            result = new ResultInfo();
        } catch (Exception e) {
            LOG.error("更新商户信息失败", e);
            result = new ResultInfo(-1, "1030010002", "操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getbycollegeid")
    public ResultInfo queryByCollegeId(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始查询商户信息[data:" + data + "]");
        String collegeId;
        try {
            collegeId = JsonUtils.readValueByName(data, "collegeId");
        } catch (Exception e) {
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }
        if (collegeId == null || !collegeId.matches("^\\d+$")) {
            LOG.error("传入参数错误");
            return new ResultInfo(-1, "1030010003", "传入参数错误");
        }
        List<SimpleStoreInfo> list = null;
        try {
            list = storeService.queryByCollegeId(Long.valueOf(collegeId));
        } catch (Exception e) {
            LOG.error("查询商户信息失败", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
        LOG.info("查询商户成功");
        if (list != null && list.isEmpty()) {
            return new ResultInfo(0, "1030010004", "没有此条记录", list);
        } else {
            return new ResultInfo(0, "", "查询成功", list);
        }
    }

    @ResponseBody
    @RequestMapping("/querysite")
    public ResultInfo querySite(HttpServletRequest req) {
        String data = req.getParameter("data");
        LOG.info("开始查询商户信息[data:" + data + "]");
        String id;
        try {
            id = JsonUtils.readValueByName(data, "id");
        } catch (Exception e) {
            LOG.error("解析参数失败");
            return new ResultInfo(-1, "1030010001", "读取json参数失败");
        }
        if (id == null || !id.matches("^\\d+$")) {
            LOG.error("传入参数错误");
            return new ResultInfo(-1, "1030010003", "传入参数错误");
        }
        CustomStoreInfo result = null;
        try {
            result = storeService.querySiteById(Long.valueOf(id));
            if (result == null) {
                return new ResultInfo(-1, "1030010004", "没有此条记录");
            }
        } catch (Exception e) {
            LOG.error("查询商户信息失败", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
        LOG.info("查询商户信息成功");
        return new ResultInfo(0, "", "查询成功", result);
    }

    /**
     * 按手机号码查询商户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/querybyphone")
    public ResultInfo selectStoreInfoByPhone(HttpServletRequest request) {
        try {
            String data = request.getParameter("data");
            if (StringUtils.isBlank(data)) {
                return new ResultInfo(-1, "1030010003", "参数错误");
            }
            String phone = JsonUtils.readValueByName(data, "phone");
            if (StringUtils.isBlank(phone)) {
                return new ResultInfo(-1, "1030010003", "参数错误");
            }
            SearchStoreInfo result = storeService.selectStoreByPhone(phone);
            if (result == null) {
                return new ResultInfo(-1, "1030010004", "没有此条记录");
            }
            return new ResultInfo(0, "", "", result);
        } catch (JsonParseException e) {
            LOG.error("解析参数异常：", e);
            return new ResultInfo(-1, "1030010001", "解析json参数失败");
        } catch (JsonMappingException e) {
            LOG.error("解析参数异常：", e);
            return new ResultInfo(-1, "1030010001", "解析json参数失败");
        } catch (Exception e) {
            LOG.error("运单查询异常：", e);
            return new ResultInfo(-1, "1030010002", "查询失败");
        }
    }

    @ResponseBody
    @RequestMapping("/allstoreinfo")
    public ResultInfo selectAxpStoreInfo(HttpServletRequest request) {
        try {

            List<AxpStoreInfo> infoList = storeService.selectAxpStoreInfo();
            if (infoList == null || infoList.isEmpty()) {
                return new ResultInfo(-1, "1030010004", "没有此条记录");
            }
            return new ResultInfo(0, "", "", infoList);
        } catch (Exception e) {
            LOG.error("获取信息异常", e);
            return new ResultInfo(-1, "1030010002", "查询失败");
        }
    }

    @ResponseBody
    @RequestMapping("/expstoreinfo")
    public void selectExpStoreInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<ExpStoreInfo> infoList = storeService.selectExpStoreInfo();
            List<SelectModel> sellist = new ArrayList<>();
            SelectModel sel = new SelectModel();
            sel.setId(" ");
            sel.setText("请选择");
            sellist.add(sel);
            for (ExpStoreInfo expStoreInfo : infoList) {

                SelectModel selectModel = new SelectModel();
                selectModel.setId(expStoreInfo.getStoreId().toString());
                selectModel.setText(expStoreInfo.getStoreName());
                sellist.add(selectModel);
            }

            writeJson(sellist, response);

        } catch (Exception e) {
            LOG.error("获取信息异常", e);
        }
    }

    @ResponseBody
    @RequestMapping("/storecodeinfo")
    public void selectStoreCodeInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<ExpStoreInfo> infoList = storeService.selectExpStoreInfo();
            List<SelectModel> sellist = new ArrayList<>();
            SelectModel sel = new SelectModel();
            sel.setId(" ");
            sel.setText("请选择");
            sellist.add(sel);
            for (ExpStoreInfo expStoreInfo : infoList) {
                SelectModel selectModel = new SelectModel();
                selectModel.setId(expStoreInfo.getStoreId().toString());
                selectModel.setText(expStoreInfo.getStoreCode());
                sellist.add(selectModel);
            }

            writeJson(sellist, response);

        } catch (Exception e) {
            LOG.error("获取信息异常", e);
        }
    }

    @RequestMapping(value = "/postExpInfoList")
    @ResponseBody
    public List<ExpStoreInfo> postExpInfoList(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            String storeName = request.getParameter("storeName") == null ? "" : request.getParameter("storeName");
            String expressCompanyId = request.getParameter("expressCompanyId") == null ? ""
                    : request.getParameter("expressCompanyId");
            String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");

            HashMap<String, Object> map = new HashMap<>();
            map.put("storeName", storeName);
            map.put("expressCompanyId", expressCompanyId);
            map.put("phone", phone);
            List<ExpStoreInfo> infoList = storeService.selectExpStoreInfo(map);
            return infoList;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/addaxpinfo")
    public ResultInfo addAxpInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        LOG.info("开始添加门店信息[data:" + data + "]");
        CustomAxpInfo axpInfo;
        try {
            axpInfo = JsonUtils.readValue(data, CustomAxpInfo.class);
            long id = storeService.addAxpInfo(axpInfo);
            return new ResultInfo();
        } catch (Exception e) {
            LOG.error("增加门店异常", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
    }

    @ResponseBody
    @RequestMapping("/delaxpinfo")
    public ResultInfo delAxpInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        LOG.info("开始删除门店信息[data:" + data + "]");
        try {
            String id = JsonUtils.readValueByName(data, "id");
            storeService.delete(Long.parseLong(id));
            return new ResultInfo();
        } catch (Exception e) {
            LOG.error("删除门店异常", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
    }

    @ResponseBody
    @RequestMapping("/updateaxpinfo")
    public ResultInfo updateAxpInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        LOG.info("开始更新门店信息[data:" + data + "]");
        CustomAxpInfo axpInfo;
        try {
            axpInfo = JsonUtils.readValue(data, CustomAxpInfo.class);
            storeService.updateAxpInfo(axpInfo);
            return new ResultInfo();
        } catch (Exception e) {
            LOG.error("更新门店异常", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
    }

    @ResponseBody
    @RequestMapping("/addexpinfo")
    public ResultInfo addExpInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        LOG.info("开始添加快递站点信息[data:" + data + "]");
        ExpStoreInfo store;
        try {
            store = JsonUtils.readValue(data, ExpStoreInfo.class);
            return storeService.addExpInfo(store, request);
        } catch (Exception e) {
            LOG.error("增加快递站点异常", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
    }

    @ResponseBody
    @RequestMapping("/updateexpinfo")
    public ResultInfo updateExpInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        LOG.info("开始更新快递站点信息[data:" + data + "]");
        ExpStoreInfo store;
        try {
            store = JsonUtils.readValue(data, ExpStoreInfo.class);
            return storeService.updateExpInfo(store, request);

        } catch (Exception e) {
            LOG.error("更新快递站点信息异常", e);
            return new ResultInfo(-1, "1030010002", "操作失败");
        }
    }

    /**
     * @param @param  request
     * @param @return 参数
     * @return ResultInfo 返回类型
     * @throws @author heyi
     * @Method: batchCheckPacketMode
     * @Description: 开关众包模式
     * @date 2016年6月3日 下午3:08:18
     */
    @ResponseBody
    @RequestMapping("/checkpacketmode")
    public ResultInfo batchCheckPacketMode(HttpServletRequest request) {
        try {
            String ids = request.getParameter("ids");
            String mgrMode = request.getParameter("mgrMode");
            String sendMode = request.getParameter("sendMode");
            LOG.info("开始更改众包模式[data:{" + ids + "," + mgrMode + "," + sendMode + "]");
            HashMap<String, Object> map = new HashMap<String, Object>();
            String[] array = ids.split("\\,");
            map.put("ids", array);
            map.put("mgrMode", mgrMode);
            map.put("sendMode", sendMode);
            int result = storeService.batchCheckPacketMode(map, request);
            if (result > 0) {
                return new ResultInfo(0, "", "操作成功");
            } else {
                return new ResultInfo(-1, "", "操作失败");
            }
        } catch (Exception e) {
            LOG.error("更新众包模式发生异常", e);
            return new ResultInfo(-1, "", "操作失败");
        }

    }
}
