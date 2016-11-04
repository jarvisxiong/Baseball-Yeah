package com.rofour.baseball.dao.manager.bean;

import java.io.Serializable;


/**
 * Created by Administrator on 2016-08-09.
 */
public class GoodsTypeBean implements Serializable {

    /**
     * 物品类型编号
     */
    private Long goodsTypeId;
    /**
     * 物品类型名称
     */
    private String name;
    /**
     * 更新人
     */
    private Long updateUser;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 物品类型描述
     */
    private String description;

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
