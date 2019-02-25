package com.tangYong.addressBook;

public class Contact {
    //姓名
    public String name;
    //电话
    public String phoneNumber;
    //电子邮件
    public String eMail;

    public Contact(){
        this(null, null, null);
    }

    public Contact(String name, String phoneNumber, String  eMail){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }
}
