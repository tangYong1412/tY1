package com.tangYong.addressBook;

public class Node {
    public Object data;//数据
    public Node next;//下一个节点

    public Node(){
        this(null);
    }

    public Node(Object data){
        this.data = data;
        next = null;
    }
}
