

    
package com.rofour.baseball.controller.model.waybill;

import java.io.Serializable;

/**
* @ClassName: WayBillProblemTypeInfo
* @Description: 运单问题类型实体
* @author heyi
* @date 2016年7月20日 上午9:10:56 
*
*/

public class WayBillProblemTypeInfo implements Serializable {

	
	private static final long serialVersionUID = 5439257910861064175L;
    
	/**
	 * 问题类型编码
	 */
	private String problemTypeCode;
	/**
	 * 问题类型名称
	 */
	private String problemTypeName;
	/**
	 * 问题所属(1：货源 2：门店 3：众包 4：收件人)
	 */
	private Integer problemTypeGroup;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 是否启用
	 */
	private Boolean isEnabled;
	/**
	 * 是否必须做退件
	 */
	private Boolean doReturn;
	/**
	 * 是否需要上传附件
	 */
	private Boolean doUplaodfile;
	/**
	 * 登记时间
	 */
	private String updateTime;
	/**
	 * 登记人
	 */
	private Integer updateUserId;
	/**
	 * 登记人昵称
	 */
	private String nickName;

	/**
	 * 所属组
	 */
	private String groupName;
	private String updateStartTime;
	private String updateEndTime;
	
	/**
	 * @return groupName
	 */
	
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return problemTypeCode
	 */
	
	public String getProblemTypeCode() {
		return problemTypeCode;
	}
	/**
	 * @param problemTypeCode the problemTypeCode to set
	 */
	
	public void setProblemTypeCode(String problemTypeCode) {
		this.problemTypeCode = problemTypeCode;
	}
	/**
	 * @return problemTypeName
	 */
	
	public String getProblemTypeName() {
		return problemTypeName;
	}
	/**
	 * @param problemTypeName the problemTypeName to set
	 */
	
	public void setProblemTypeName(String problemTypeName) {
		this.problemTypeName = problemTypeName;
	}
	/**
	 * @return problemTypeGroup
	 */
	
	public Integer getProblemTypeGroup() {
		return problemTypeGroup;
	}
	/**
	 * @param problemTypeGroup the problemTypeGroup to set
	 */
	
	public void setProblemTypeGroup(Integer problemTypeGroup) {
		this.problemTypeGroup = problemTypeGroup;
	}
	/**
	 * @return sortNo
	 */
	
	public Integer getSortNo() {
		return sortNo;
	}
	/**
	 * @param sortNo the sortNo to set
	 */
	
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * @return isEnabled
	 */
	
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	/**
	 * @param isEnabled the isEnabled to set
	 */
	
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * @return doReturn
	 */
	
	public Boolean getDoReturn() {
		return doReturn;
	}
	/**
	 * @param doReturn the doReturn to set
	 */
	
	public void setDoReturn(Boolean doReturn) {
		this.doReturn = doReturn;
	}
	/**
	 * @return doUplaodfile
	 */
	
	public Boolean getDoUplaodfile() {
		return doUplaodfile;
	}
	/**
	 * @param doUplaodfile the doUplaodfile to set
	 */
	
	public void setDoUplaodfile(Boolean doUplaodfile) {
		this.doUplaodfile = doUplaodfile;
	}
	/**
	 * @return updateTime
	 */
	
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return updateUserId
	 */
	
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	/**
	 * @param updateUserId the updateUserId to set
	 */
	
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUpdateStartTime() {
		return updateStartTime;
	}
	public void setUpdateStartTime(String updateStartTime) {
		this.updateStartTime = updateStartTime;
	}
	public String getUpdateEndTime() {
		return updateEndTime;
	}
	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}
	
}
