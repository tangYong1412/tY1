package main.java.web.model;

import main.java.web.Flection.Column;
import main.java.web.model.base.Entity;

public class Student extends Entity {
    @Column(value = "userNo")
    String userNo;
    @Column(value = "userNa")
    String userNa;
    @Column(value = "userAge")
    String userAge;
    @Column(value = "userSex")
    String userSex;
    @Column(value = "userPs")
    String userPs;
    @Column(value = "Cname")
    String cname;
    @Column(value = "Grade")
    String grade;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserNa() {
        return userNa;
    }

    public void setUserNa(String userNa) {
        this.userNa = userNa;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPs() {
        return userPs;
    }

    public void setUserPs(String userPs) {
        this.userPs = userPs;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTableName(){
        return "student";
    }
}
