package com.zhiduan.axp.service.waybill;

import com.zhiduan.axp.controller.model.waybill.ScanInfo;

/**
 * Created by wny on 2016-06-30.
 */
public interface WaybillScanService {

    ScanInfo getScanInfoById(Long scanId);
}
