package com.rofour.baseball.controller.model.report;

import java.io.Serializable;

/**
 * @author ZXY
 * @ClassName: ReportThirdPartyInfo
 * @Description: 第三方报表
 * @date 2016-04-16 09:13:24
 */

public class ReportThirdPartyPhoneInfo implements Serializable {

    private static final long serialVersionUID = -3943800111324072878L;
    /**
     * 学校名称
     **/
    private String collegeName;
    /**
     * 所属区域名称
     **/
    private String phone;

    public ReportThirdPartyPhoneInfo() {
        super();
    }

    public ReportThirdPartyPhoneInfo(String collegeName, String phone) {
        this.collegeName = collegeName;
        this.phone = phone;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}