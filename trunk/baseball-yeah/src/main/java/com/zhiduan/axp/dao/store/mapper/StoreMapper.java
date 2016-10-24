package com.zhiduan.axp.dao.store.mapper;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.zhiduan.axp.controller.model.store.CustomStoreInfo;
import com.zhiduan.axp.dao.bean.SmsAccountBean;
import com.zhiduan.axp.dao.store.bean.AxpBean;
import com.zhiduan.axp.dao.store.bean.AxpDetailBean;
import com.zhiduan.axp.dao.store.bean.AxpStoreBean;
import com.zhiduan.axp.dao.store.bean.ExpStoreBean;
import com.zhiduan.axp.dao.store.bean.SearchStoreBean;
import com.zhiduan.axp.dao.store.bean.SiteAxpBean;
import com.zhiduan.axp.dao.store.bean.StoreBean;
import com.zhiduan.axp.dao.store.bean.StoreExpBean;
import com.zhiduan.axp.dao.store.bean.StoreSchoolBean;
import com.zhiduan.axp.dao.store.bean.StoreSiteBean;

/**
 * @author WJ
 * @ClassName: StoreMapper
 * @Description: 商户中心数据操作接口
 * @date 2016年3月25日 下午12:55:41
 */
@Named("storeMapper")
public interface StoreMapper {
    /**
     * 表中状态,0:存在
     */
    public static int STOREMAPPER_DELETE_STATUS_EXIST = 0;
    /**
     * 表中状态,1:已删除
     */
    public static int STOREMAPPER_DELETE_STATUS_DELETED = 1;
    /**
     * 1:启用,默认
     */
    public static int STOREMAPPER_STATUS_ENABLED = 1;
    /**
     * 0:禁用
     */
    public static int STOREMAPPER_STATUS_DISABLED = 0;
    /**
     * 快递站点 默认
     */
    public static int STOREMAPPER_TYPE_EXP_SITE = 1;
    /**
     * 爱学派门店
     */
    public static int STOREMAPPER_TYPE_AXP = 2;
    /**
     * 默认的快递公司
     */
    public static int STOREMAPPER_DEFAULT_EXP = 1;
    /**
     * 非默认的快递公司
     */
    public static int STOREMAPPER_NON_DEFAULT_EXP = 0;

    /**
     * 增加商户并返回商户主键。
     *
     * @param bean 商户信息
     * @return 主键
     */
    long insertStore(StoreBean bean);

    /**
     * 更新操作
     *
     * @param store
     */
    int updateStore(StoreBean bean);

    /**
     * 根据商户id删除商户_快递公司对应记录
     *
     * @param id
     */
    int deleteStoreExp(long id);

    /**
     * 增加商户_快递公司对应记录
     *
     * @param bean
     */
    void insertStoreExp(StoreExpBean bean);

    /**
     * 根据商户id删除商户_学校对应记录
     *
     * @param id
     */
    int deleteStoreSchool(long id);

    /**
     * 增加商户_快递站点对应记录
     *
     * @param bean
     */
    long insertStoreSite(StoreSiteBean bean);

    /**
     * 修改商户_快递站点对应记录
     *
     * @param bean
     */
    int updateStoreSite(StoreSiteBean bean);

    /**
     * 根据store_id删除快递站点_axp门店对应记录
     *
     * @param id
     */
    int deleteSiteAxpByStoreId(long id);

    /**
     * 根据axp_id删除快递站点_axp门店对应记录
     *
     * @param id
     */
    int deleteSiteAxpByAxpId(long id);

    /**
     * 增加快递站点_axp门店对应记录
     *
     * @param bean
     */
    long insertSiteAxp(SiteAxpBean bean);

    /**
     * 根据学校编号查找
     *
     * @param id
     */
    List<StoreBean> selectStoreByCollegeId(long id);

    /**
     * 根据商户id删除非默认的快递公司
     *
     * @param id
     */
    int deleteNoDefault(long id);

    /**
     * 根据商户id删除所有快递公司
     *
     * @param id
     */
    int deleteRelativeExp(long id);

    /**
     * 返回已存在的storeCode和storeName条数,理论上只有一个
     */
    int ifStoreExist(Map<String, String> map);

    /**
     * 返回已存在的phone条数,理论上只有一个
     */
    int ifPhoneExist(Map<String, String> map);

    /**
     * @return Long    返回类型
     * @Method: getSeq
     * @Description: 获得序列下一个值
     * @author WJ
     * @date 2016年3月28日 下午6:29:43
     */
    Long getSeq();

    /**
     * @param expressId
     * @return int    返回类型
     * @Method: ifExpressExist
     * @Description: 返回已存在的快递条数, 理论上等于expressId.size()
     * @author WJ
     * @date 2016年4月2日 上午10:06:15
     */
    int ifExpressExist(List<Long> expressId);

    /**
     * @param axpId
     * @return int    返回类型
     * @Method: ifAxpExist
     * @Description: 返回已存在的门店条数, 理论上等于axpId.size()
     * @author WJ
     * @date 2016年4月2日 上午10:10:35
     */
    int ifAxpExist(List<Long> axpId);

    /**
     * @param id
     * @return int    返回类型
     * @Method: LogicalDeleteStore
     * @Description: 逻辑删除商户
     * @author WJ
     * @date 2016年4月3日 下午1:58:36
     */
    int logicalDeleteStore(long id);

    /**
     * 按手机号码查询商户信息
     *
     * @param phone
     * @return
     * @author xl
     */
    SearchStoreBean selectStoreByPhone(String phone);

    /**
     * @param @return 参数
     * @return List<AxpStoreBean>    返回类型
     * @throws
     * @Method: selectAxpStoreInfo
     * @Description: 查询所有爱学派门店信息
     * @author heyi
     * @date 2016年4月7日 下午9:33:50
     */
    List<AxpStoreBean> selectAxpStoreInfo();

    /**
     * @param @return 参数
     * @return List<ExpStoreBean>    返回类型
     * @throws
     * @Method: selectExpStoreInfo
     * @Description: 查询快递站点所有信息
     * @author heyi
     * @date 2016年4月8日 下午3:43:14
     */
    List<ExpStoreBean> selectExpStoreInfo();
    /**
     * 
     * @Method: selectExpStoreInfoByParames
     * @Description: 带参数查询快递站点信息
     * @param @param map
     * @param @return    参数
     * @return List<ExpStoreBean>    返回类型
     * @throws
     * @author heyi
     * @date 2016年7月13日 下午2:05:27 
     *
     */
    List<ExpStoreBean> selectExpStoreInfoByParames(Map<String,Object> map);
    /**
     * @param expList
     * @Method: insertSiteAxpList
     * @Description: 批量插入tb_store_exp_axp_rel 信息
     * @author WJ
     * @date 2016年4月11日 上午9:43:37
     **/

    void insertSiteAxpList(List<SiteAxpBean> expList);

    /**
     * @param colList
     * @Method: insertStoreSchoolList
     * @Description: 批量插入门店-学校对应信息  tb_store_college_rel
     * @author WJ
     * @date 2016年4月11日 上午10:59:20
     **/

    void insertStoreSchoolList(List<StoreSchoolBean> colList);

    /**
     * @param ecList
     * @Method: insertEcList
     * @Description: 批量插入门店和快递公司对应信息 tb_store_ec_rel
     * @author WJ
     * @date 2016年4月11日 上午11:29:23
     **/

    void insertEcList(List<StoreExpBean> ecList);

    /**
     * @param @param axpDetailBean
     * @return void    返回类型
     * @throws
     * @Method: insertAxpDetail
     * @Description: 插入门店具体信息, 暂时有负责人, 号码, 营业执照
     * @author WJ
     * @date 2016年4月11日 下午1:22:58
     **/

    void insertAxpDetail(AxpDetailBean axpDetailBean);

    /**
     * @param storeId
     * @return int    返回类型
     * @Method: ifStoreExist
     * @Description: 根据id判断是否存在商户信息, 不包括逻辑删除的
     * @author WJ
     * @date 2016年4月11日 下午2:30:41
     */
    int ifStoreIdExist(List<Long> storeId);

    /**
     * @param colList
     * @return int    返回类型
     * @Method: ifCollegeExist
     * @Description: 判断学校信息是否存在
     * @author WJ
     * @date 2016年4月11日 下午2:36:57
     **/

    int ifCollegeExist(List<Long> colList);

    /**
     * @param axpId
     * @return void    返回类型
     * @Method: deleteAxpDetail
     * @Description: 删除tb_store_axp 门店具体信息
     * @author WJ
     * @date 2016年4月11日 下午4:07:41
     **/

    void deleteAxpDetail(long axpId);

    /**
     * @param axpDetailBean
     * @Method: updateAxpDetail
     * @Description: 更新axp门店具体信息
     * @author WJ
     * @date 2016年4月11日 下午5:39:21
     **/

    void updateAxpDetail(AxpDetailBean axpDetailBean);

    /**
     * @param storeId
     * @return CustomStoreInfo    返回类型
     * @Method: querySiteById
     * @Description: 根据商户id, 查询站点的所有信息
     * @author WJ
     * @date 2016年4月12日 下午4:10:46
     */
    CustomStoreInfo querySiteById(long storeId);

    /**
     * @param @param  map
     * @param @return 参数
     * @return int    返回类型
     * @throws
     * @Method: isExpExsits
     * @Description: 判断门店名称或编号是否重复
     * @author heyi
     * @date 2016年4月16日 下午5:03:18
     */
    int isAxpExsits(Map<String, Object> map);

	List<AxpBean> selectCollegesByStoreId(Long storeId);

    SearchStoreBean selectStoreExpressByPhone(String phone);
    
    /**
     * 
     * @Method: logicalDeleteStoreByIds
     * @Description: 批量删除快递站点
     * @param @param ids
     * @param @return    参数
     * @return int    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月3日 下午2:47:21 
     *
     */
    int logicalDeleteStoreByIds(List<Integer> ids);
    /**
     * 
     * @Method: batchCheckPacketMode
     * @Description: 批量更改开启众包模式
     * @param @param ids
     * @param @return    参数
     * @return int    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月3日 下午2:46:52 
     *
     */
    int batchCheckPacketMode(Map<String,Object> map);
    /**
     * 增加短信账户
     */
    void addSmsAccount(SmsAccountBean bean);
     /**
     * 
     * @Method: selectStoreNotDeleted
     * @Description: 根据商户编号查询商户
     * @param @param storeId
     * @param @return    参数
     * @return List<StoreBean>    返回类型
     * @throws
     * @author heyi
     * @date 2016年6月2日 下午2:05:14 
     *
     */
     List<StoreBean>  selectStoreNotDeleted(Long storeId);
     
     /**
      * 
      * @Method: selectStoreSite
      * @Description: 根据快递站点编号查询快递站点
      * @param @param store_exp_id
      * @param @return    参数
      * @return List<StoreSiteBean>    返回类型
      * @throws
      * @author heyi
      * @date 2016年6月2日 下午2:13:28 
      *
      */
     List<StoreSiteBean> selectStoreSite(Long store_exp_id);
     /**
      * 
      * @Method: selectEcListByStoreId
      * @Description: 根据商户Id查询相关联的快递公司
      * @param @param storeId
      * @param @return    参数
      * @return List<StoreExpBean>    返回类型
      * @throws
      * @author heyi
      * @date 2016年6月2日 下午2:27:12 
      *
      */
     List<StoreExpBean>  selectEcListByStoreId(Long storeId);
     /**
      * 
      * @Method: selectColListByStoreId
      * @Description: 根据商户ID查询关联的学校
      * @param @param storeId
      * @param @return    参数
      * @return List<StoreSchoolBean>    返回类型
      * @throws
      * @author heyi
      * @date 2016年6月2日 下午2:44:51 
      *
      */
     List<StoreSchoolBean>  selectColListByStoreId(Long storeId);
}
