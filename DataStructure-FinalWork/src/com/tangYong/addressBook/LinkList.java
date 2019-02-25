package com.tangYong.addressBook;

import com.tangYong.addressBook.Node;

public class LinkList {
    private Node head;//头节点
    private int size;//链表大小

    public LinkList(){
        head = new Node();
        size = 0;
    }

    //尾插法
    public void insert(Object data){
        Node newNode = new Node(data);

        //获取头节点
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = newNode;
        size++;
    }

    //删除
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("第"+ index + "节点不存在。");
            return null;
        }

        Node temp = head;

        for(int i = 0; i < size; i++){
            //下标匹配
            if(i == index){
                break;
            }

            temp = temp.next;
        }

        Node returnData = temp.next;
        temp.next = temp.next.next;
        size--;
        return returnData;
    }

    //更新数据
    public Object update(int index, Contact contact){
        if(index < 0 || index >= size){
            System.out.println("第"+ index + "节点不存在。");
            return null;
        }

        Node temp = head;

        for(int i = 0; i < size; i++){
            temp = temp.next;
            if(i == index){
                break;
            }
        }

        temp.data = contact;

        return temp.data;
    }

    //查找
    public Object get(int index) throws Exception{
        if(index < 0 || index >= size){
            throw new Exception("第" + index + "个元素不存在");
        }

        //获取第一个节点
        Node temp = head.next;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }

        return temp.data;
    }

    //展示链表元素
    public void display(){
        Node temp = head;//获取头节点
        int i = 0;
        while(temp.next != null){
            System.out.print(((Contact)temp.next.data).name + ", " + ( i++ ) + ",  ");
            temp = temp.next;
        }

        System.out.println();
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }
}
