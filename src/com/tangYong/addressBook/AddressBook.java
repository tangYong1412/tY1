package com.tangYong.addressBook;


public class AddressBook {
    //联系人链表数组
    private LinkList[] contacts;

    public AddressBook(){
        //26个字母对应一个链表
        contacts = new LinkList[26];

        //初始化
        for(int i = 0; i < contacts.length; i++){
            contacts[i] = new LinkList();
        }
    }

    //添加联系人
    public void addContact(int index, Contact contact){
        if( index < 0 || index >= 26){
            System.out.println("第" + index + "个字母不存在。");
            return;
        }

        try {
            //末尾位置添加
            contacts[index].insert(contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除
    public Object deleteContact(int firstIndex, int secIndex){
        if ( firstIndex < 0 || firstIndex >= 26) {
            System.out.println("第" + firstIndex + "个字母不存在。");
            return null;
        }

        return contacts[firstIndex].remove(secIndex);
    }

    //修改
    public Object update(int firstIndex, int secIndex, Contact contact){
        if ( firstIndex < 0 || firstIndex >= 26) {
            System.out.println("第" + firstIndex + "个字母不存在。");
            return null;
        }

        return contacts[firstIndex].update(secIndex, contact);
    }

    public LinkList[] getContacts() {
        return contacts;
    }

    public void setContacts(LinkList[] contacts) {
        this.contacts = contacts;
    }
}
