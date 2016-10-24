/**
 * @Title: ResourceServiceImpl.java
 * @Package com.zhiduan.axp.bll.resourcescenter
 * @Project: axp-bll
 * @Description: (用一句话描述该文件做什么)
 * @author WJ
 * @date 2016年3月28日 下午7:16:17
 * @version V1.0
 * ──────────────────────────────────
 * Copyright (c) 2016, 指端科技.
 */

package com.zhiduan.axp.service.resource.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhiduan.axp.common.AttachConstant;
import com.zhiduan.axp.common.Constant;
import com.zhiduan.axp.common.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.aliyun.oss.OSSClient;
import com.zhiduan.axp.common.ConstantFunction;
import com.zhiduan.axp.controller.model.ResultInfo;
import com.zhiduan.axp.controller.model.resource.CredentialInfo;
import com.zhiduan.axp.controller.model.resource.CredentialURLInfo;
import com.zhiduan.axp.controller.model.resource.ServerInfo;
import com.zhiduan.axp.controller.model.resource.SignInfo;
import com.zhiduan.axp.dao.resource.bean.AttachmentBean;
import com.zhiduan.axp.dao.resource.bean.ServerBean;
import com.zhiduan.axp.dao.resource.mapper.ResourceMapper;
import com.zhiduan.axp.dao.user.mapper.UserMapper;
import com.zhiduan.axp.exception.BusinessException;
import com.zhiduan.axp.service.resource.ResourceService;

/**
 * @author WJ
 * @ClassName: ResourceServiceImpl
 * @Description:
 * @date 2016年3月28日 下午7:16:17
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param attachmentTypeId 附件类型id
     * @return ServerInfo    服务器信息
     * @Description: 获取服务器信息
     * @author WJ
     * @date 2016年3月29日 下午1:02:31
     */
    @Override
    public ServerInfo getServerInfo(String attachmentTypeId) {
        //暂时只有一个服务器,如果以后有多个,需要根据服务器id来查询
        ServerBean bean = resourceMapper.getServer(ConstantFunction.getOssServerPic());
        ServerInfo info = null;
        if (bean != null) {//节省下远程调用
            String bucketName = resourceMapper.getBucketName(attachmentTypeId);
            if (!StringUtils.isBlank(bucketName)) {
                info = new ServerInfo(bean.getServerId(), bean.getEndpoint(), bean.getAccessKeyId(), bean.getAccessKeySecret(), bucketName);
            }
        }
        return info;
    }

    /**
     * @param bean
     * @Method: saveFileInfo
     * @Description: 保存附件信息
     * @date 2016年3月31日 上午10:41:51
     **/
    @Override
    public void saveFileInfo(AttachmentBean bean) {
        resourceMapper.insertAttachment(bean);
    }

    /**
     * @param bean 附件信息
     * @return void    返回类型
     * @Method: updateFileInfo
     * @Description: 更新附件信息表
     * @author WJ
     * @date 2016年3月31日 下午3:04:12
     **/
    @Override
    public void updateFileInfo(AttachmentBean bean) {
        resourceMapper.updateAttachment(bean);

    }

    /**
     * @param fileUrl
     * @return boolean    返回类型
     * @Description: 是否是重复上传的, 重复上传说明记录已经存在
     * @author WJ
     * @date 2016年3月31日 下午3:04:38
     **/
    @Override
    public boolean ifExist(String fileUrl, Long userId) {
        Map<String, String> map = new HashMap<>();
        map.put("fileUrl", fileUrl);
        map.put("userId", userId.toString());
        int count = resourceMapper.ifExist(map);
        return count != 0;
    }

    /**
     * @param sourceId
     * @param serverId
     * @param typeId
     * @param fileKey
     * @param fileName
     * @param length
     * @param userId
     * @param username
     * @Description: 插入附件信息表
     */
    private void insertFileInfo(String sourceId, String serverId, String typeId, String fileKey, String fileName,
                                Long length, Long userId, String username) {
        String suffix = "";// 默认没有后缀名时
        if (fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        AttachmentBean bean = new AttachmentBean(sourceId, serverId, typeId, fileKey, fileName, suffix, length, userId,
                username, new Date());
        // 如果重复插入,那么要覆盖之前的
        if (ifExist(bean.getFileUrl(), userId)) {
            updateFileInfo(bean);
        } else {
            saveFileInfo(bean);
        }
    }

    /**
     * @param serverInfo
     * @param fileKey
     * @param toUpload
     * @Description: oss上传
     */
    private void ossUpload(ServerInfo serverInfo, String fileKey, InputStream toUpload) {
        OSSClient client = null;
        try {
            client = new OSSClient(serverInfo.getEndpoint(), serverInfo.getAccessKeyId(),
                    serverInfo.getAccessKeySecret());
            client.putObject(serverInfo.getBucketName(), fileKey, toUpload);
        } catch (Exception e) {
            throw e;
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    @Override
    public ResultInfo uploadSign(SignInfo signInfo) {
        if (StringUtils.isBlank(signInfo.getFile()) || !validSignInfo(signInfo)) {
            throw new BusinessException("111");
        }
        //获取服务器信息
        ServerInfo serverInfo = getServerInfo(signInfo.getAttachmentTypeId());
        if (serverInfo != null) {
            //base64解码
            ByteArrayInputStream in = getDecodeStream(signInfo.getFile());
            //oss上传
            ossUpload(serverInfo, signInfo.getFileKey(), in);
            //插入附件信息
            insertFileInfo(signInfo.getSourceId(), serverInfo.getAttachmentServerId(), signInfo.getAttachmentTypeId(),
                    signInfo.getFileKey(), signInfo.getFileName(), (long) in.available(), signInfo.getUserId(),
                    signInfo.getUserName());
        } else {
            throw new BusinessException("07001");
        }
        return new ResultInfo(0, "", "上传成功");
    }

    /**
     * @param credentialInfo
     * @return
     * @throws Exception
     * @Description: 上传用户证件信息
     */
    public ResultInfo uploadCredential(CredentialInfo credentialInfo) throws Exception {
        vlidCredentialInfo(credentialInfo);
        //检查审核状态 ,0:未上传,1:审核中,2为审核通过,3审核失败
        Integer status = userMapper.queryVerifyStatus(credentialInfo.getUserId());
        if (status != null && status == 2) {
            throw new BusinessException("02023");
        }
        String sourceId;
        if (credentialInfo.getSourceId().equals("1")) {
            sourceId = ConstantFunction.getSourceAndroid();
            // 安卓需要转码（临时）
            credentialInfo.setFile(java.net.URLDecoder.decode(credentialInfo.getFile(), "utf-8"));
        } else {
            sourceId = ConstantFunction.getSourceIos();
        }
        String attachmentTypeId = ConstantFunction.getAtmTypeUserauth();
        ServerInfo serverInfo = getServerInfo(attachmentTypeId);
        if (serverInfo != null) {
            //base64解码
            ByteArrayInputStream in = getDecodeStream(credentialInfo.getFile());
            int size = in.available();
            //生成文件名
            StringBuilder fileName = new StringBuilder().append(ConstantFunction.getExp()).append("/").append(credentialInfo.getUserId()).append("/");
            if (credentialInfo.getType().equals("1")) {
                fileName.append(ConstantFunction.getCredentialTypeIdHold());
            } else if (credentialInfo.getType().equals("2")) {
                fileName.append(ConstantFunction.getCredentialTypeId());
            } else {
                fileName.append(ConstantFunction.getCredentialTypeOther());
            }
            String originName = credentialInfo.getFileName();
            if (originName.contains(".") && originName.lastIndexOf(".") != originName.length() - 1) {
                fileName.append(".").append(originName.substring(originName.lastIndexOf(".") + 1));
            } else {
                throw new BusinessException("07004");
            }
            String fileKey = fileName.toString();
            //oss上传
            ossUpload(serverInfo, fileKey, in);
            //插入附件信息
            insertFileInfo(sourceId, serverInfo.getAttachmentServerId(), attachmentTypeId,
                    fileKey, credentialInfo.getFileName(), (long) size, credentialInfo.getUserId(),
                    credentialInfo.getUserName());
        } else {
            throw new BusinessException("07001");
        }
        return new ResultInfo(0, "", "上传成功");
    }

    /**
     * @param userId
     * @return 操作结果
     * @Description: 获取已上传的用户证件的url
     */
    public ResultInfo getCredentialUrls(Long userId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bizId", userId);
            map.put("attachType", AttachConstant.TYPE_AUTH_EXP);

            String url = Constant.axpurl.get("resource_getOriginAttachList_serv");
            // 定义反序列化 数据格式
            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
            };
            ResultInfo credentialUrls = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
            return new ResultInfo(0, "", "操作成功", credentialUrls.getData());
        } catch (Exception e) {
            return new ResultInfo(-1, "", "");
        }
    }

    /**
     * @param userId
     * @param creType identityCard studentCard
     * @return
     */
    public ResultInfo getCredentialUrls(Long userId, String creType) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId.toString());
        map.put("typeId", ConstantFunction.getAtmTypeUserauth());
        List<String> fileKeys = resourceMapper.getCredentialFileKey(map);
        if (fileKeys == null || fileKeys.isEmpty()) {
            return new ResultInfo(-1, "07005", "");
        }
        OSSClient client = null;
        try {
            ServerInfo serverInfo = getServerInfo(ConstantFunction.getAtmTypeUserauth());
            client = new OSSClient(serverInfo.getEndpoint(), serverInfo.getAccessKeyId(),
                    serverInfo.getAccessKeySecret());
            List<CredentialURLInfo> urls = new ArrayList<>();
            for (String fileKey : fileKeys) {
                //过期时间5分钟
                URL url = client.generatePresignedUrl(serverInfo.getBucketName(), fileKey, new Date(System.currentTimeMillis() + 60 * 1000 * 5));
                int type = 0;

//				if (fileKey.contains(creType)){不要这个业务条件了
                //货源
                if (fileKey.contains("packet")) {
                    if (fileKey.contains(ConstantFunction.getCredentialTypeIdHold())) {
                        type = 1;
                    } else if (fileKey.contains(ConstantFunction.getCredentialTypeId())) {
                        type = 2;
                    }
                }
//                else {
//                    //理论上不可能,除非是手动干预了数据库的数据
//                    throw new BusinessException("07005");
//                }
//				}

                urls.add(new CredentialURLInfo(type, url.toString()));
            }
            return new ResultInfo(0, "", "操作成功", urls);
        } catch (Exception e) {
            throw e;
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    /**
     * @param credentialInfo
     * @Description: 校验方法
     */
    private void vlidCredentialInfo(CredentialInfo credentialInfo) {
        if (StringUtils.isBlank(credentialInfo.getType()) || StringUtils.isBlank(credentialInfo.getSourceId()) ||
                StringUtils.isBlank(credentialInfo.getFileName()) || StringUtils.isBlank(credentialInfo.getFile())) {
            throw new BusinessException("111");
        } else if (!credentialInfo.getType().matches("^(1|2|3){1}$")) {
            throw new BusinessException("07003");
        } else if (!credentialInfo.getSourceId().matches("^(1|2){1}$")) {
            throw new BusinessException("07002");
        }
    }

    /**
     * @param file
     * @return
     * @Description: 解码base64后的字节流
     */
    private ByteArrayInputStream getDecodeStream(String file) {
        byte[] fileBytes = Base64Utils.decodeFromString(file);
        ByteArrayInputStream in = new ByteArrayInputStream(fileBytes);
        return in;
    }

    /**
     * @param signInfo
     * @return boolean    返回类型
     * @Method: valid
     * @Description: 校验signinfo参数
     * @author WJ
     * @date 2016年4月4日 上午11:57:14
     **/

    private boolean validSignInfo(SignInfo signInfo) {
        if (StringUtils.isBlank(signInfo.getAttachmentTypeId()) || StringUtils.isBlank(signInfo.getFileKey())
                || StringUtils.isBlank(signInfo.getFileName()) || StringUtils.isBlank(signInfo.getSourceId())
                || StringUtils.isBlank(signInfo.getUserName()) || signInfo.getUserId() == null) {
            return false;
        }
        return true;
    }


}
