package com.tangYong.teachingPreparation;

//链栈
public class LinkStack {
    public class Node{
        private Object data;
        private Node next;

        public Node(){
            this(null);
        }

        public Node(Object data){
            this.data = data;
            next = null;
        }
    }

    private Node top;

    public LinkStack(){
    }

    //置空
    public void clear(){
        top = null;
    }

    //判空
    public boolean isEmpty(){
        return top == null;
    }

    //取栈顶元素
    public Object peek(){
        if(!isEmpty()){
            return top.data;
        }else{
            return null;
        }
    }

    //入栈，由于是链表，所以没有最大限制
    public void push(Object data){
        Node newNode = new Node(data);//新节点
        newNode.next = top;
        top = newNode;
    }

    //出栈
    public Object pop(){
        if(isEmpty()){
            return null;
        }else{
            Node temp = top;
            top = top.next;
            return temp.data;
        }
    }

    //输出所有元素
    public void display(){
        Node temp = top;//获取头节点
        while(temp.next != null){
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
    }
}
