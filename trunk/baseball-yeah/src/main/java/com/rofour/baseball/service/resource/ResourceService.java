/**  
* @Title: ResourceService.java
* @package com.rofour.baseball.idl.resourcescenter
* @Project: axp-idl
* @Description: 资源管理接口
* @author WJ
* @date 2016年3月28日 下午7:18:26 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.rofour.baseball.service.resource;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.resource.CredentialInfo;
import com.rofour.baseball.controller.model.resource.ServerInfo;
import com.rofour.baseball.controller.model.resource.SignInfo;
import com.rofour.baseball.dao.resource.bean.AttachmentBean;

/**
* @ClassName: ResourceService
* @Description: 资源管理接口
* @author WJ
* @date 2016年3月28日 下午7:18:26 
*
*/

public interface ResourceService {
	/**
	 * @Method: getServerInfo
	 * @Description: 获取服务器信息
	 * @param  attachmentTypeId    附件类型id
	 * @return ServerInfo    服务器信息
	 * @author WJ
	 * @date 2016年3月29日 下午1:02:31 
	 */
	public ServerInfo getServerInfo(String attachmentTypeId);
	/**
	 * 
	 * @Method: uploadSign
	 * @Description: 上传签收信息
	 * @param  signInfo
	 * @return ResultInfo    返回类型
	 * @author WJ
	 * @date 2016年4月4日 上午11:50:47 
	 *
	 */
	public ResultInfo uploadSign(SignInfo signInfo);
	/**
	 * @Method: saveFileInfo
	 * @Description: 保存附件信息
	 * @param  bean
	 * @date 2016年3月31日 上午10:41:51 
	 **/
	    
	public void saveFileInfo(AttachmentBean bean);

	/**
	 * @Method: updateFileInfo
	 * @Description: 更新附件信息表
	 * @param  bean   附件信息
	 * @return void    返回类型
	 * @author WJ
	 * @date 2016年3月31日 下午3:04:12 
	 **/
	    
	public void updateFileInfo(AttachmentBean bean);

	/**
	 * @Method: ifExist
	 * @Description: 是否是重复上传的,重复上传说明记录已经存在
	 * @param  fileUrl
	 * @param  userId
	 * @return boolean    返回类型
	 * @author WJ
	 * @date 2016年3月31日 下午3:04:38 
	 **/
	    
	public boolean ifExist(String fileUrl,Long userId);
	/**
	 * 
	 * @Description:上传用户证件信息
	 * @param credentialInfo
	 * @return
	 * @throws Exception
	 */
	public ResultInfo uploadCredential(CredentialInfo credentialInfo) throws Exception;
	/**
	 * 
	 * @Description: 获取已上传的用户证件的url
	 * @param userId
	 * @return 操作结果
	 */
	public ResultInfo getCredentialUrls(Long userId);

	ResultInfo getCredentialUrls(Long userId,String creType);
}
