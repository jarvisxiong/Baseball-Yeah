package com.rofour.baseball.service.waybill;

import com.rofour.baseball.controller.model.waybill.ScanInfo;

/**
 * Created by wny on 2016-06-30.
 */
public interface WaybillScanService {

    ScanInfo getScanInfoById(Long scanId);
}
