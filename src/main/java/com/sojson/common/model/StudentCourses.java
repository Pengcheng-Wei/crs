package com.sojson.common.model;

public class StudentCourses {
    private String cSession;
    private String courseName;
    private String classRoomName;
    private String tName;
    private String tId;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getcSession() {
        return cSession;
    }

    public void setcSession(String cSession) {
        this.cSession = cSession;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "cSession='" + cSession + '\'' +
                ", courseName='" + courseName + '\'' +
                ", classRoomName='" + classRoomName + '\'' +
                ", tName='" + tName + '\'' +
                '}';
    }
}
