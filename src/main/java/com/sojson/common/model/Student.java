package com.sojson.common.model;

public class Student {
    private String sId;
    private String sName;
    private String className;
    private String sWechatId;
    private int partiCnt;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getsWechatId() {
        return sWechatId;
    }

    public void setsWechatId(String sWechatId) {
        this.sWechatId = sWechatId;
    }

    public int getPartiCnt() {
        return partiCnt;
    }

    public void setPartiCnt(int partiCnt) {
        this.partiCnt = partiCnt;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", className='" + className + '\'' +
                ", sWechatId='" + sWechatId + '\'' +
                ", partiCnt=" + partiCnt +
                '}';
    }
}
