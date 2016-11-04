package com.rofour.baseball.service.manager;

import java.util.List;

import com.rofour.baseball.controller.model.manager.KeywordInfo;
import com.rofour.baseball.dao.manager.bean.KeywordBean;

public interface KeywordService {
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
    public boolean validateKeyword(KeywordBean bean);
}
