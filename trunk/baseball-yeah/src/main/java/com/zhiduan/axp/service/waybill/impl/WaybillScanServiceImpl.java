package com.zhiduan.axp.service.waybill.impl;

import com.zhiduan.axp.controller.model.waybill.ScanInfo;
import com.zhiduan.axp.dao.waybill.bean.ScanBean;
import com.zhiduan.axp.dao.waybill.mapper.WaybillScanMapper;
import com.zhiduan.axp.service.waybill.WaybillScanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by wny on 2016-06-30.
 */
@Service("waybillScanService")
public class WaybillScanServiceImpl implements WaybillScanService {


    @Autowired
    @Qualifier("waybillScanMapper")
    private WaybillScanMapper scanMapper;

    @Override
    public ScanInfo getScanInfoById(Long scanId) {
        ScanBean info = scanMapper.getScanInfoById(scanId);
        ScanInfo temp = new ScanInfo();
        BeanUtils.copyProperties(info, temp);
        return temp;
    }
}
