package com.zhiduan.axp.dao.waybill.mapper;

import com.zhiduan.axp.dao.waybill.bean.ScanBean;
import org.apache.ibatis.annotations.Param;

import javax.inject.Named;
import java.util.List;

/**
 * Created by wny on 2016-06-30.
 */
@Named("waybillScanMapper")
public interface WaybillScanMapper {

 ScanBean getScanInfoById(@Param("scanId") Long scanId);
}
