package com.zhiduan.axp.dao.manager.mapper;

import com.zhiduan.axp.dao.manager.bean.OfferBean;
import com.zhiduan.axp.dao.manager.bean.OfferExpressCollageBran;
import com.zhiduan.axp.dao.manager.bean.OfferExpressCompanyBran;

import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016-07-06.
 */
@Named("companyOfferPriceMapper")
public interface CompanyOfferPriceMapper {
    /**
     * @param bean
     * @return int
     * @Description: 新增
     */
    int insert(OfferBean bean);

    int insertExpressCompany(OfferExpressCompanyBran bean);
    int insertExpressCollage(OfferExpressCollageBran bean);
    int deleteExpressCompany(Long areaId);
    int deleteExpressCollage(Long areaId);
    int update(OfferBean bean);

    /**
     * @param areaId
     * @return int 删除数量
     * @Description: 按主键ID删除菜单
     **/
    int deleteByPrimaryKey(Long areaId);


    /**
     * @return List<FocusPicBean>
     * @Description: 查询所有
     **/
    List<OfferBean> selectAll(OfferBean bean);

    int selectAllCount(OfferBean bean);

    int selectIsExtNameCount(OfferBean bean);

}
