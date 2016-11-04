package com.rofour.baseball.service.waybill.impl;

import com.rofour.baseball.controller.model.waybill.ScanInfo;
import com.rofour.baseball.dao.waybill.bean.ScanBean;
import com.rofour.baseball.dao.waybill.mapper.WaybillScanMapper;
import com.rofour.baseball.service.waybill.WaybillScanService;
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
