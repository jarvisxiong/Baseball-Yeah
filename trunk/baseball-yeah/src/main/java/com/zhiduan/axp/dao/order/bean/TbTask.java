package com.zhiduan.axp.dao.order.bean;

import com.zhiduan.axp.controller.model.BasePage;
import com.zhiduan.axp.controller.model.order.AttachmentInfo;
import com.zhiduan.axp.interceptor.ShiroRealm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Tb task.
 *
 * @author will
 * @Description
 * @date 2016 -08-23 15:06:41
 */
public class TbTask extends BasePage implements Serializable {

    private static final long serialVersionUID = -725949111222335299L;

    /**
     *
     */
    private Long taskId;


    /**
     *任务单号
     */
    private Long taskNo;

    /**
     *任务主题
     */
    private String theme;

    private String fullName;



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *任务状态
     */
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     *进行中
     */
    private  String carry;
    /**
     *取消
     */
    private  String cancel;

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }
    /**
     *完成
     */
    private  String done;

    /**
     *描述
     */
    private String description;
    /**
     *任务规则
     */
    private String rule;
    /**
     *要求
     */
    private String claim;
    /**
     *封面图片
     */
    private String taskCover;
    /**
     *是否需要图片
     */
    private Byte isImg;
    /**
     *任务每单赏金
     */
    private Integer taskUnitPrice;
    /**
     *任务单子数量
     */
    private Integer unitTotal;
    /**
     *每人接单上限
     */
    private Integer upperLimit;
    /**
     *每单多少件
     */
    private Integer taskUnitNum;
    /**
     *任务等级 1：绿 2：蓝 3：紫 4：金
     */
    private Byte taskLevel;
    /**
     *学校编号
     */
    private Long collegeId;
    /**
     *开始有效日期
     */
    private Date effectiveStartDate;
    /**
     *结束有效日期
     */
    private Date effectiveEndDate;
    /**
     *任务截止日期
     */
    private Date deadline;
    /**
     *创建人
     */
    private Long createUserId;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *是否已发布
     */
    private Byte isPublished;
    /**
     *发布人
     */
    private Long publishUserId;
    /**
     *发布时间
     */
    private Date publishTime;
    /**
     *是否已处理
     */
    private Byte isProcessed;

    /**
     *创建人
     */
    private String userName;
    /**
     *部门
     */
    private String dutyName;
    /**
     *校区
     */
    private String simpleName;

    /**
     *附件
     */
    private  List<String> files;

    /**
     *是否需要任务
     */
    private Byte isRemark;

    public Byte getIsRemark() {
        return isRemark;
    }

    public void setIsRemark(Byte isRemark) {
        this.isRemark = isRemark;
    }

    public List<String> getRemoveFiles() {
        return removeFiles;
    }

    public void setRemoveFiles(List<String> removeFiles) {
        this.removeFiles = removeFiles;
    }

    private  List<String> removeFiles;

    public List<AttachmentInfo> getAttachmentInfos() {
        return attachmentInfos;
    }

    public void setAttachmentInfos(List<AttachmentInfo> attachmentInfos) {
        this.attachmentInfos = attachmentInfos;
    }

    /**
     *附件
     */
    List<AttachmentInfo>  attachmentInfos;

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    private  String  startDate;

    private  String pubstartDate;

    private  String pubendDate;

    public String getPubstartDate() {
        return pubstartDate;
    }

    public void setPubstartDate(String pubstartDate) {
        this.pubstartDate = pubstartDate;
    }

    public String getPubendDate() {
        return pubendDate;
    }

    public void setPubendDate(String pubendDate) {
        this.pubendDate = pubendDate;
    }

    private  String  dutyId;

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    private  String endDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    private List<TbTaskSub> tbTaskSubs;

    public List<TbTaskSub> getTbTaskSubs() {
        return tbTaskSubs;
    }

    public void setTbTaskSubs(List<TbTaskSub> tbTaskSubs) {
        this.tbTaskSubs = tbTaskSubs;
    }

    /**
     * Instantiates a new Tb task.
     *
     * @param taskId             the task id
     * @param taskNo             the task no
     * @param theme              the theme
     * @param description        the description
     * @param rule               the rule
     * @param claim              the claim
     * @param taskCover          the task cover
     * @param isImg              the is img
     * @param taskUnitPrice      the task unit price
     * @param unitTotal          the unit total
     * @param upperLimit         the upper limit
     * @param taskUnitNum        the task unit num
     * @param taskLevel          the task level
     * @param collegeId          the college id
     * @param effectiveStartDate the effective start date
     * @param effectiveEndDate   the effective end date
     * @param deadline           the deadline
     * @param createUserId       the create user id
     * @param createTime         the create time
     * @param isPublished        the is published
     * @param publishUserId      the publish user id
     * @param publishTime        the publish time
     * @param isProcessed        the is processed
     */
    public TbTask(Long taskId, Long taskNo, String theme, String description, String rule, String claim, String taskCover, Byte isImg,
                  Integer taskUnitPrice, Integer unitTotal, Integer upperLimit, Integer taskUnitNum, Byte taskLevel, Long collegeId,
                  Date effectiveStartDate, Date effectiveEndDate, Date deadline, Long createUserId, Date createTime, Byte isPublished,
                  Long publishUserId, Date publishTime, Byte isProcessed,String userName,String dutyName,String simpleName) {
        this.taskId = taskId;
        this.taskNo = taskNo;
        this.theme = theme;
        this.description = description;
        this.rule = rule;
        this.claim = claim;
        this.taskCover = taskCover;
        this.isImg = isImg;
        this.taskUnitPrice = taskUnitPrice;
        this.unitTotal = unitTotal;
        this.upperLimit = upperLimit;
        this.taskUnitNum = taskUnitNum;
        this.taskLevel = taskLevel;
        this.collegeId = collegeId;
        this.effectiveStartDate = effectiveStartDate;
        this.effectiveEndDate = effectiveEndDate;
        this.deadline = deadline;
        this.createUserId = createUserId;
        this.createTime = createTime;
        this.isPublished = isPublished;
        this.publishUserId = publishUserId;
        this.publishTime = publishTime;
        this.isProcessed = isProcessed;


        this.userName=userName;
        this.dutyName=dutyName;
        this.simpleName=simpleName;
    }

    /**
     * Instantiates a new Tb task.
     */
    public TbTask() {
        super();
    }

    /**
     * Gets task id.
     *
     * @return the task id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * Sets task id.
     *
     * @param taskId the task id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * Gets task no.
     *
     * @return the task no
     */
    public Long getTaskNo() {
        return taskNo;
    }

    /**
     * Sets task no.
     *
     * @param taskNo the task no
     */
    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Gets theme.
     *
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Sets theme.
     *
     * @param theme the theme
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * Gets rule.
     *
     * @return the rule
     */
    public String getRule() {
        return rule;
    }

    /**
     * Sets rule.
     *
     * @param rule the rule
     */
    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    /**
     * Gets claim.
     *
     * @return the claim
     */
    public String getClaim() {
        return claim;
    }

    /**
     * Sets claim.
     *
     * @param claim the claim
     */
    public void setClaim(String claim) {
        this.claim = claim == null ? null : claim.trim();
    }

    /**
     * Gets task cover.
     *
     * @return the task cover
     */
    public String getTaskCover() {
        return taskCover;
    }

    /**
     * Sets task cover.
     *
     * @param taskCover the task cover
     */
    public void setTaskCover(String taskCover) {
        this.taskCover = taskCover == null ? null : taskCover.trim();
    }

    /**
     * Gets is img.
     *
     * @return the is img
     */
    public Byte getIsImg() {
        return isImg;
    }

    /**
     * Sets is img.
     *
     * @param isImg the is img
     */
    public void setIsImg(Byte isImg) {
        this.isImg = isImg;
    }

    /**
     * Gets task unit price.
     *
     * @return the task unit price
     */
    public Integer getTaskUnitPrice() {
        return taskUnitPrice;
    }

    /**
     * Sets task unit price.
     *
     * @param taskUnitPrice the task unit price
     */
    public void setTaskUnitPrice(Integer taskUnitPrice) {
        this.taskUnitPrice = taskUnitPrice;
    }

    /**
     * Gets unit total.
     *
     * @return the unit total
     */
    public Integer getUnitTotal() {
        return unitTotal;
    }

    /**
     * Sets unit total.
     *
     * @param unitTotal the unit total
     */
    public void setUnitTotal(Integer unitTotal) {
        this.unitTotal = unitTotal;
    }

    /**
     * Gets upper limit.
     *
     * @return the upper limit
     */
    public Integer getUpperLimit() {
        return upperLimit;
    }

    /**
     * Sets upper limit.
     *
     * @param upperLimit the upper limit
     */
    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * Gets task unit num.
     *
     * @return the task unit num
     */
    public Integer getTaskUnitNum() {
        return taskUnitNum;
    }

    /**
     * Sets task unit num.
     *
     * @param taskUnitNum the task unit num
     */
    public void setTaskUnitNum(Integer taskUnitNum) {
        this.taskUnitNum = taskUnitNum;
    }

    /**
     * Gets task level.
     *
     * @return the task level
     */
    public Byte getTaskLevel() {
        return taskLevel;
    }

    /**
     * Sets task level.
     *
     * @param taskLevel the task level
     */
    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    /**
     * Gets college id.
     *
     * @return the college id
     */
    public Long getCollegeId() {
        return collegeId;
    }

    /**
     * Sets college id.
     *
     * @param collegeId the college id
     */
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * Gets effective start date.
     *
     * @return the effective start date
     */
    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    /**
     * Sets effective start date.
     *
     * @param effectiveStartDate the effective start date
     */
    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    /**
     * Gets effective end date.
     *
     * @return the effective end date
     */
    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    /**
     * Sets effective end date.
     *
     * @param effectiveEndDate the effective end date
     */
    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    /**
     * Gets deadline.
     *
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets deadline.
     *
     * @param deadline the deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets create user id.
     *
     * @return the create user id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * Sets create user id.
     *
     * @param createUserId the create user id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets is published.
     *
     * @return the is published
     */
    public Byte getIsPublished() {
        return isPublished;
    }

    /**
     * Sets is published.
     *
     * @param isPublished the is published
     */
    public void setIsPublished(Byte isPublished) {
        this.isPublished = isPublished;
    }

    /**
     * Gets publish user id.
     *
     * @return the publish user id
     */
    public Long getPublishUserId() {
        return publishUserId;
    }

    /**
     * Sets publish user id.
     *
     * @param publishUserId the publish user id
     */
    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    /**
     * Gets publish time.
     *
     * @return the publish time
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * Sets publish time.
     *
     * @param publishTime the publish time
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * Gets is processed.
     *
     * @return the is processed
     */
    public Byte getIsProcessed() {
        return isProcessed;
    }

    /**
     * Sets is processed.
     *
     * @param isProcessed the is processed
     */
    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
    }
}