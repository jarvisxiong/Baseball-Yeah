package com.rofour.baseball.dao.manager.mapper;

import com.rofour.baseball.dao.manager.bean.AreaBean;
import com.rofour.baseball.dao.manager.bean.FocusPicBean;
import com.rofour.baseball.service.manager.FocusPicService;

import javax.inject.Named;
import java.util.List;

@Named("focusPicMapper")
public interface FocusPicMapper {

    /**
     *
     * @Description: 新增
     * @param bean
     * @return int
     */
    int insert(FocusPicBean bean);

    /**
     * @Description: 按主键ID删除菜单
     * @param  areaId
     * @return int 删除数量
     **/
    int deleteByPrimaryKey(Long areaId);



    /**
     * @Description: 查询所有
     * @return List<FocusPicBean>
     **/
    List<FocusPicBean> selectAll(FocusPicBean bean);

    int selectAllCount(FocusPicBean bean);
}
