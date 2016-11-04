package com.rofour.baseball.service.store;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.store.AxpStoreInfo;
import com.rofour.baseball.controller.model.store.CustomAxpInfo;
import com.rofour.baseball.controller.model.store.CustomStoreInfo;
import com.rofour.baseball.controller.model.store.ExpStoreInfo;
import com.rofour.baseball.controller.model.store.SearchStoreInfo;
import com.rofour.baseball.controller.model.store.SimpleStoreInfo;
import com.rofour.baseball.controller.model.store.StoreInfo;

/**
 * @author WJ
 * @ClassName: StoreService
 * @Description: 商户服务接口
 * @date 2016年3月25日 下午12:54:52
 */

public interface StoreService {
    /**
     * 根据学校id查询商户编码,再返回store_id和store_code
     *
     * @param id
     * @return
     */
    List<SimpleStoreInfo> queryByCollegeId(Long id);

    /**
     * 根据商户id查询站点的所有信息
     *
     * @param id
     * @return
     */
    CustomStoreInfo querySiteById(Long id);

    /**
     * 增加商户
     *
     * @param store
     */
    long add(StoreInfo store);

    /**
     * 根据id删除,逻辑删除,实际表中只是更新是否删除字段
     *
     * @param id
     */
    int removeById(Long id);

    /**
     * 更新操作
     *
     * @param store
     */
    void update(StoreInfo store);

    /**
     * 按手机号码查询商户信息
     *
     * @param phone
     * @return
     * @author xl
     */
    SearchStoreInfo selectStoreByPhone(String phone);

    /**
     * @param @return 参数
     * @return List<AxpStoreInfo>    返回类型
     * @throws
     * @Method: selectAxpStoreInfo
     * @Description: 查询爱学派所有门店信息
     * @author heyi
     * @date 2016年4月7日 下午9:34:31
     */
    List<AxpStoreInfo> selectAxpStoreInfo();

    /**
     * @param @return 参数
     * @return List<ExpStoreInfo>    返回类型
     * @throws
     * @Method: selectExpStoreInfo
     * @Description: 查询所有快递站点信息
     * @author heyi
     * @date 2016年4月8日 下午3:50:59
     */
    List<ExpStoreInfo> selectExpStoreInfo();

    /**
     * @param @param  map
     * @param @return 参数
     * @return List<ExpStoreInfo>    返回类型
     * @throws
     * @Method: selectExpStoreInfo
     * @Description: 查询所有快递站点信息(带参数)
     * @author heyi
     * @date 2016年7月13日 下午2:06:54
     */
    List<ExpStoreInfo> selectExpStoreInfo(Map<String, Object> map);

    /**
     * @param @param  axpInfo
     * @param @return
     * @return ResultInfo    返回类型
     * @throws
     * @Method: addAxpInfo
     * @Description: 增加门店信息
     * @author WJ
     * @date 2016年4月11日 下午1:25:35
     **/
    long addAxpInfo(CustomAxpInfo axpInfo);

    /**
     * 根据商户ID逻辑删除商户信息。
     *
     * @param storeId 商户ID
     */
    void delete(long storeId);

    /**
     * @param axpInfo
     * @return ResultInfo    返回类型
     * @Method: updateAxpInfo
     * @Description: 更新门店信息
     * @author WJ
     * @date 2016年4月11日 下午5:10:47
     **/
    void updateAxpInfo(CustomAxpInfo axpInfo);

    /**
     * @param @param  storeInfo
     * @param @return 参数
     * @return ResultInfo    返回类型
     * @throws
     * @Method: addExpInfo
     * @Description:增加快递站点信息
     * @author heyi
     * @date 2016年4月18日 上午10:31:22
     */
    ResultInfo addExpInfo(ExpStoreInfo storeInfo, HttpServletRequest reaquest);

    /**
     * @param @param  storeInfo
     * @param @return 参数
     * @return ResultInfo    返回类型
     * @throws
     * @Method: updateExpInfo
     * @Description: 更新快递站点信息
     * @author heyi
     * @date 2016年4月18日 上午10:31:37
     */
    ResultInfo updateExpInfo(ExpStoreInfo storeInfo, HttpServletRequest request);

    /**
     * 根据手机号查询商户和快递信息
     *
     * @param phone
     * @return
     * @throws Exception
     */
    SearchStoreInfo selectStoreExpressByPhone(String phone) throws Exception;

    List<AxpStoreInfo> selectCollegesByStoreId(Long storeId) throws Exception;

    int removeByIds(List<Integer> ids, HttpServletRequest request);

    /**
     * 是否存在coo
     * @param ids
     * @return
     */
    int existCoo(List<Integer> ids);

    /**
     *查询站点下员工数量
     * @param ids
     * @return
     */
    int getStoreUser(List<Integer> ids);


    /**
     * @param @param  ids
     * @param @param  request
     * @param @return 参数
     * @return int    返回类型
     * @throws
     * @Method: batchCheckPacketMode
     * @Description:批量更新开启众包模式
     * @author heyi
     * @date 2016年6月3日 下午2:48:57
     */
    int batchCheckPacketMode(Map<String, Object> map, HttpServletRequest request);
}
