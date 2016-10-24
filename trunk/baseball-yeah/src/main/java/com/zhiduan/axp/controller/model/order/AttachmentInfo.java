package com.zhiduan.axp.controller.model.order;


import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: AttachmentInfo
 * @Description:
 * @author ZXY
 * @date 2016/8/24 15:08
 */
public class AttachmentInfo implements Serializable {

    private static final long serialVersionUID = -17396366729383343L;
    /** 主键 **/
    private Long attachmentId;
    /** 业务id **/
    private Long bizId;
    /** 来源 **/
    private String source;
    /** 原始文件名 **/
    private String originFileName;
    /** 地址 **/
    private String fileUrl;
    /** 缩略图地址 **/
    private String nailFileUrl;
    /** 文件格式 **/
    private String extension;
    /** 文件大小 **/
    private Long size;
    /** 上传人id **/
    private Long uploadUserId;
    /** 上传人名称 **/
    private String uploadUserName;
    /** 上传时间 **/
    private Date uploadTime;

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getNailFileUrl() {
        return nailFileUrl;
    }

    public void setNailFileUrl(String nailFileUrl) {
        this.nailFileUrl = nailFileUrl;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
