package com.tangYong.TFPointCardGame;

public class LinkList {
    private Node head;//头节点
    private int size;//链表大小

    public LinkList(){
        head = new Node();
        size = 0;
    }

    //插入
    public void insert(int index, Object x) throws Exception {
        if(index < 0 || index > size){
            throw new Exception("第" + index + "个节点不存在。");
        }

        Node newNode = new Node(x);
        Node temp = head;//获取头节点
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    //删除
    public Object remove(Object data) throws Exception{
        Node temp = head;
        Node newTemp;

        while( temp.next != null){
            newTemp = temp;
            temp = temp.next;

            if(temp.data.equals(data)){
                newTemp.next = newTemp.next.next;
                break;
            }
        }

        size--;
        return temp.data;
    }

    //查找
    public Object get(int index) throws Exception{
        if(index < 0 || index >= size){
            throw new Exception("第" + index + "个元素不存在");
        }

        Node temp = head;//获取头节点
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
            i++;
            System.out.print(temp.next.data + ", " + i + ",  ");
            temp = temp.next;
        }
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
