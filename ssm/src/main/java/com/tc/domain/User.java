package com.tc.domain;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String rights;
    private int passNum;
    private String studentId;
    private String username;
    private String password;
    private String icon;
    private String major;
    private String grade;
    private String QQ;
    private String email;
    private boolean sex;
    private String discription;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +

                ", icon='" + icon + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", QQ='" + QQ + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", discription='" + discription + '\'' +
                '}' +
                "id=" + id +
                ", rights='" + rights + '\'' +
                ", passNum=" + passNum +
                ", studentId='" + studentId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
