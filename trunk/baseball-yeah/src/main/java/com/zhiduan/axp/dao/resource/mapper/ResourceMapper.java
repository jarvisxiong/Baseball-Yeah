/**  
* @Title: ResourceMapper.java
* @Package com.zhiduan.axp.idl.resourcecenter.dao
* @Project: axp-idl
* @Description: 资源中心接口
* @author WJ
* @date 2016年3月29日 上午10:47:56 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/


    
package com.zhiduan.axp.dao.resource.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.dao.resource.bean.AttachmentBean;
import com.zhiduan.axp.dao.resource.bean.ServerBean;

/**
* @ClassName: ResourceMapper
* @Description: 资源中心接口
* @author WJ
* @date 2016年3月29日 上午10:47:56 
*
*/
@Named("resourceMapper")
public interface ResourceMapper {
	/**
	 * 是否启用 1
	 */
	public static short RESOURCE_SERVER_ENABLED = 1;
	/**
	 * 不启用 0
	 */
	public static short RESOURCE_SERVER_DISABLED = 0;
	/**
	 * 
	 * @Method: getServer
	 * @Description: 根据 bucketName 获取已启用的server信息,有可能有多个
	 * @param  id 附件服务器id
	 * @return ServerBean    返回类型
	 * @author WJ
	 * @date 2016年3月29日 上午11:52:17 
	 *
	 */
	public ServerBean getServer(String id);
	/**
	 * @Method: insertAttachment
	 * @Description: 插入附件信息
	 * @param  bean  附件实体类
	 * @return void    返回类型
	 * @author WJ
	 * @date 2016年3月31日 下午1:04:30 
	 **/
	    
	public void insertAttachment(AttachmentBean bean);
	/**
	 * @Method: updateAttachment
	 * @Description:  更新附件信息
	 * @param  bean
	 * @return void    返回类型
	 * @author WJ
	 * @date 2016年3月31日 下午3:13:24 
	 **/
	    
	public int updateAttachment(AttachmentBean bean);
	/**
	 * @Method: selectAttachment
	 * @Description: 是否已经存在fileUrl的记录
	 * @param  map
	 * @return void    返回类型
	 * @author WJ
	 * @date 2016年3月31日 下午3:14:03 
	 **/
	    
	public int ifExist(Map<String,String> map);
	/**
	 * 
	 * @Method: getBucketName
	 * @Description: 查找bucketName
	 * @param  id 附件类型id
	 * @return String    返回类型
	 * @author WJ
	 * @date 2016年4月4日 上午11:25:56 
	 *
	 */
	public String getBucketName(String id);
	/**
	 * 
	 * @Description:根据用户id和附件类型id查找file_url
	 * @param map
	 * @return url 集合
	 */
	public List<String> getCredentialFileKey(Map<String, String> map);
}
