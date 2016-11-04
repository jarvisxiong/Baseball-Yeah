package com.rofour.baseball.common;

import java.util.ArrayList;
import java.util.List;

public enum Quota {
    START_TIME(1L, "开始时间", "START_TIME"), END_TIME(2L, "结束时间", "END_TIME"), COLLEGE_ID(3L, "学校编号", "COLLEGE_ID"), CITY_ID(4L, "城市编号", "CITY_ID"),
    PROVINCE_ID(5L, "省份编号","PROVINCE_ID"),IS_NEW(6L, "新用户", "IS_NEW"),ORDER_COUNT(7L,"订单量","ORDER_COUNT"),CAN_USE(8L,"是否可用","CAN_USE");

    Long id;

    String name;

    String dbName;

    private Quota() {
    }

    private Quota(Long id, String name, String dbName) {
        this.id = id;
        this.name = name;
        this.dbName = dbName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public static String getQuotaName(String index) {
        for (Quota p : Quota.values()) {
            if (p.getDbName().equals(index)) {
                return p.getName();
            }
        }
        return null;
    }

    public static Long getQuotaId(String index) {
        for (Quota p : Quota.values()) {
            if (p.getDbName().equals(index)) {
                return p.getId();
            }
        }
        return null;
    }

    public static String getDbNameById(Long index) {
        for (Quota p : Quota.values()) {
            if (p.getId().equals(index)) {
                return p.getDbName();
            }
        }
        return null;
    }

    public static List<String> getAllDbName() {
        List<String> perDbNames = new ArrayList<String>();
        for (Quota p : Quota.values()) {
            perDbNames.add(p.getDbName());
        }
        return perDbNames;
    }
}
