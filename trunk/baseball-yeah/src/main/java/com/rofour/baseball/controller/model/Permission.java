package com.rofour.baseball.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

public enum Permission {
    ADD(1L, "增加", "ADD"), DELETE(2L, "删除", "DELETE"), EDIT(3L, "编辑", "EDIT"), QUERY(4L, "查询", "QUERY"), PRINT(5L, "打印",
            "PRINT"), AUDIT(7L, "审核", "AUDIT"), INITPWD(8L, "初始化密码", "INITPWD"), SEALED(9L, "账号封存", "SEALED"), ACTIVATION(10L, "账号激活", "ACTIVATION"), PACKETPATTEN(11L, "众包模式 ", "PACKETPATTEN"), ACCOUNTFREZE(12L, "账号冻结", "ACCOUNTFREZE"), ARRIVALACCOUNT(13L, "到账", "ARRIVALACCOUNT"),
    ENABLE(14L, "启用", "ENABLE"), FROZEN(15L, "冻结", "FROZEN"),GENERATE(16L, "生成代金券", "GENERATE");

    Long id;

    String name;

    String dbName;

    private Permission() {
    }

    private Permission(Long id, String name, String dbName) {
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

    public static String getPermissionName(String index) {
        for (Permission p : Permission.values()) {
            if (p.getDbName().equals(index)) {
                return p.getName();
            }
        }
        return null;
    }

    public static Long getPermissionId(String index) {
        for (Permission p : Permission.values()) {
            if (p.getDbName().equals(index)) {
                return p.getId();
            }
        }
        return null;
    }

    public static String getDbNameById(Long index) {
        for (Permission p : Permission.values()) {
            if (p.getId().equals(index)) {
                return p.getDbName();
            }
        }
        return null;
    }

    public static List<String> getAllDbName() {
        List<String> perDbNames = new ArrayList<String>();
        for (Permission p : Permission.values()) {
            perDbNames.add(p.getDbName());
        }
        return perDbNames;
    }
}
