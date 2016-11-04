package com.rofour.baseball.controller.model.message;

import com.rofour.baseball.controller.model.BasePage;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wny on 2016-06-16.
 */
public class MessageSearchInfo extends BasePage implements Serializable {

    /**
     * 查询关键字:手机号等
     */
    private String keyWord;

    /**
     * 发送人ID
     */
    private Long sendUserID;

    /**
     * 发送人商户
     */
    private Long sendStoreID;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息状态 0 发送中  1已发送  2成功 -1失败
     */
    private Byte status;

    /**
     * 开始提交时间
     */
    private String startSubmitTime;

    /**
     * 结束提交时间
     */
       private String endSubmitTime;
    /**
     * 汇总方式（all:总统计;day:按日;month:按月)
     */
    private String sumMethod;

    /**
     * 发送人手机号
     */
    private  String sendPhone;

    /**
     * 业务id
     */
    private  Long bizId;
    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }


    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public String getSumMethod() {
        return sumMethod;
    }

    public void setSumMethod(String sumMethod) {
        this.sumMethod = sumMethod;
    }
    public Long getSendStoreID() {
        return sendStoreID;
    }

    public void setSendStoreID(Long sendStoreID) {
        this.sendStoreID = sendStoreID;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * @return startSubmitTime
     */

    public String getStartSubmitTime() {
        return startSubmitTime;
    }

    /**
     * @param startSubmitTime the startSubmitTime to set
     */

    public void setStartSubmitTime(String startSubmitTime) {
        this.startSubmitTime = startSubmitTime;
    }

    /**
     * @return endSubmitTime
     */

    public String getEndSubmitTime() {
        return endSubmitTime;
    }

    /**
     * @param endSubmitTime the endSubmitTime to set
     */

    public void setEndSubmitTime(String endSubmitTime) {
        this.endSubmitTime = endSubmitTime;
    }

    /**
     * @return sendUserID
     */

    public Long getSendUserID() {
        return sendUserID;
    }

    /**
     * @param sendUserID the 发送人ID to set
     */

    public void setSendUserID(Long sendUserID) {
        this.sendUserID = sendUserID;
    }

    /**
     * @return type
     */

    public String getType() {
        return type;
    }

    /**
     * @param type the  消息类型 to set
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return status 0 发送中  1已发送  2成功 -1失败
     */

    public Byte getStatus() {
        return status;
    }

    /**
     * @param status the 消息状态 to set 0 发送中  1已发送  2成功 -1失败
     */

    public void setStatus(Byte status) {
        this.status = status;
    }

    public MessageSearchInfo() {
    }

    public MessageSearchInfo(String keyWord, Long sendUserID, Long sendStoreID, String type, Byte status, String startSubmitTime, String endSubmitTime, String sumMethod, String sendPhone) {
        this.keyWord = keyWord;
        this.sendUserID = sendUserID;
        this.sendStoreID = sendStoreID;
        this.type = type;
        this.status = status;
        this.startSubmitTime = startSubmitTime;
        this.endSubmitTime = endSubmitTime;
        this.sumMethod = sumMethod;
        this.sendPhone = sendPhone;
    }
}
