/**  
 * Copyright (c) 2016, 指端科技.
 */



package com.rofour.baseball.dao.manager.mapper;

import java.util.List;

import javax.inject.Named;

import com.rofour.baseball.controller.model.manager.KeywordInfo;
import com.rofour.baseball.dao.manager.bean.KeywordBean;

/**
* @ClassName: AreaMapper
* @Description: 区域数据库操作接口
* @author cy
* @date 2016-04-18 10:24:31
*
*/
@Named("keywordMapper")
public interface KeywordMapper {

	/**
	 * 
	 * @Description: 新增
	 * @param bean
	 * @return int
	 */
    public int insertKeyword(KeywordBean bean);

    /**
    * @Description: 按主键ID删除菜单
    * @param  bean
    * @return int 删除数量
    **/
    public int deleteByPrimaryKey(KeywordBean bean);

    /**
     * 取关键字列表
     * @param bean
     * @return
     */
    public List<KeywordInfo> getList(KeywordInfo info);
    
    /**
     * 关键字统计
     * @param bean
     * @return
     */
    public int getListCount(KeywordInfo info);
    
    /**
     * 验证关键字
     * @param bean
     * @return
     */
    public List<KeywordBean> validateKeyword(KeywordBean bean);
    /**
     * 查询全部
     * @param bean
     * @return
     */
    public List<String> selectAll();
}