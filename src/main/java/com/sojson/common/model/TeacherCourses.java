package com.sojson.common.model;

public class TeacherCourses {
    private String cSession;
    private String courseName;
    private String classRoomName;
    private String className;

    @Override
    public String toString() {
        return "TeacherCourses{" +
                "cSession='" + cSession + '\'' +
                ", courseName='" + courseName + '\'' +
                ", classRoomName='" + classRoomName + '\'' +
                ", className='" + className + '\'' +
                '}';
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
