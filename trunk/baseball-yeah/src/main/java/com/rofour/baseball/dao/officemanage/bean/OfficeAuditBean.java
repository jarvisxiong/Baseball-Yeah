package com.rofour.baseball.dao.officemanage.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: OfficeAuditBean
 * @Description:
 * @author ZXY
 * @date 2016/10/17 18:55
 */
public class OfficeAuditBean implements Serializable {

    private static final long serialVersionUID = 676859167884185634L;

    private Long auditId;

    private Integer optType;

    private Long packetUserId;

    private Long applyUserId;

    private Integer auditState;

    private Date applyTime;

    private Long auditUserId;

    private Date auditTime;

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Long getPacketUserId() {
        return packetUserId;
    }

    public void setPacketUserId(Long packetUserId) {
        this.packetUserId = packetUserId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
