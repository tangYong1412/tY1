package com.tangYong.hfmtree;

public class LinkQueue {
    public class Node {
        Object element;// 存放值
        Node next;

        public Node(Object element) {
            this.element = element;
        }

        public Node() {
        }
    }

    private Node front; // 队首指针
    private Node rear; // 队尾指针

    // 构造函数
    public LinkQueue(){
        front = rear = null;
    }

    //置空
    public void clear(){
        front = rear = null;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return front == null;
    }

    //获取队列长度
    public int getLenth(){
        Node p = front;
        int length = 0;
        while(p != null){
            p = p.next;
            ++length;
        }

        return length;
    }

    //获取队首元素
    public Object peek(){
        if(!isEmpty()){
            return front.element;
        }else{
            return null;
        }
    }

    // 入队操作
    public void offer(Object x){
        Node p = new Node(x); //新结点
        if(front != null){
            rear.next = p;
            rear = p;
        }else{
            front = rear = p;
        }
    }

    //出队操作
    public Object poll(){
        if(front != null){
            Node p = front; // 获取头节点
            front = front.next;
            if(p == rear){
                rear = null;
            }
            return p.element;
        }else{
            return null;
        }
    }
}