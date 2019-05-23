package com.sojson.common.model;

/**
 * @author allen
 */
public class Teacher {
    private String tId;
    private String tName;
    private String tWechatId;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettWechatId() {
        return tWechatId;
    }

    public void settWechatId(String tWechatId) {
        this.tWechatId = tWechatId;
    }
}
