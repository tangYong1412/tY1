package com.tangYong.dataStructure;

public class LinkList<E>{
    //链表
    public static class Node<E>{
        Node<E> nextAddress=null;//储存下一个node的位置
        E data;//储存当前node的数据

        public Node(E data){
            this.data=data;
        }
    }

    //链表的大小
    static int size=0;
    Node<E> head;//链表头部

    public LinkList(){
        head=null;
    }

    public static void main(String[] args){
        //写入一组数据，存入链表，并打印出来
        Integer[] integers={1, 2 , 3, 4, 5, 6};
        LinkList<Integer> list=new LinkList<Integer>();

        for(int i=0;i<integers.length;i++){
            list.addFootNode(integers[i]);
        }

        for(int i=1;i<=size;i++){
            System.out.print(list.checkNode(i).data+" ");
        }
        System.out.println();

        //头部增加
        list.addHeadNode(7);
        for(int i=1;i<=size;i++){
            System.out.print(list.checkNode(i).data+" ");
        }
        System.out.println();

        //中间添加并打印
        list.addBodyNode(8, 2);//在第二个后面添加
        for(int i=1;i<=size;i++){
            System.out.print(list.checkNode(i).data+" ");
        }
        System.out.println();

        //删除并打印
        list.deleteNode(2);
        for(int i=1;i<=size;i++){
            System.out.print(list.checkNode(i).data+" ");
        }
        System.out.println();

        //更改
        list.changeNode(2, 10);
        for(int i=1;i<=size;i++){
            System.out.print(list.checkNode(i).data+" ");
        }
        System.out.println();
    }

    //查看数据
    public  Node checkNode(int checkIndex){
        Node<E> temp=head;//取得第一个node

        //取得指定的node
        for(int i=0;i<checkIndex-1;i++)
            temp=temp.nextAddress;

        return temp;
    }

    //头部增加
    public  void addHeadNode(E data){
        int addIndex=1;
        addBodyNode(data, addIndex);

        E temp=head.data;
        head.data=data;
        head.nextAddress.data=temp;
    }

    //中间增加
    public  void addBodyNode(E data, int addIndex){
        //将要增加的node
        Node<E> node=new Node<E>(data);

        Node<E> temp=head;//取得第一个node
        //取得指定的node
        for(int i=0;i<addIndex-1;i++)
            temp=temp.nextAddress;

        Node<E> temp1=temp.nextAddress;//

        temp.nextAddress=node;
        node.nextAddress=temp1;

        size++;
    }

    //脚部增加
    public  void addFootNode(E data){
        //将要增加的node
        Node<E> node=new Node(data);

        if(size==0){//链表中无node
            head=node;
        }
        else{
            Node<E> temp=head;//取得第一个node
            while(temp.nextAddress!=null)
                temp=temp.nextAddress;

            temp.nextAddress=node;
        }

        size++;
    }

    //删除
    public  void deleteNode(int deleteIndex){
        Node<E> temp=head;//取得第一个node

        //取得指定的node
        for(int i=0;i<deleteIndex-2;i++)
            temp=temp.nextAddress;

        //删除指定node
        temp.nextAddress=temp.nextAddress.nextAddress;
        size--;
    }

    //改变node2中的值
    public  void changeNode(int changeIndex, E changeData){
        Node<E> temp=head;//取得第一个node

        //取得指定的node
        for(int i=0;i<changeIndex-1;i++)
            temp=temp.nextAddress;

        temp.data=changeData;
    }
}
