package main.java.web.model;

import main.java.web.Flection.Column;
import main.java.web.model.base.Entity;

public class Adminer extends Entity {
    @Column(value = "admiNo")
    String adminNo;
    @Column(value = "passWord")
    String passWord;

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTableName() {
        return "admin";
    }
}
