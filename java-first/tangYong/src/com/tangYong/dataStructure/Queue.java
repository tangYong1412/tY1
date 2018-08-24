package com.tangYong.dataStructure;

import java.util.ArrayList;

public class Queue <E>{
    private ArrayList<E> data=new ArrayList<E>();//储存数据
    int maxSize;//最大储存额
    int number;//元素个数

    public Queue(int maxSize){
        this.maxSize=maxSize;
        number=0;

        for(int i=0;i<maxSize;i++)
            data.add(null);
    }//创建一个队列

    //得到队列的长度
    public int length(){
        return maxSize;
    }

    //得到队列中元素的个数
    public int getNumberOfData(){
        return number;
    }

    //判断队列是否已满
    public boolean isFull(){
        return number==maxSize;
    }

    //加入数据
    public void addQ(E addData){
        if(isFull()){
            System.out.println("队列已满");
        }
        else{
            number++;
            //判断有多少空位，向前补齐
            data.set(maxSize-number, addData);

            System.out.println("加入数据成功");
        }
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return number==0;
    }

    //删除数据
    public void remaoveQ(){
        if(isEmpty()){
            System.out.println("队列已空");
        }
        else{
            --number;
            for(int i=1;i<=number;i++)
                data.set(maxSize-i, data.get(maxSize-i-1));
        }
    }

    public static void main(String[] args){
        Queue queueQ=new Queue(5);//创建一个栈

        //初始化数据
        for(int i=0;i<queueQ.length()-1;i++)
            queueQ.addQ(i);
        System.out.println("设置深度为"+queueQ.getNumberOfData()+"的队列, 并初始化四个数据");

        //增加一个数据
        queueQ.addQ(5);
        System.out.println("增加一个数据，数据个数为"+queueQ.getNumberOfData());

        //删除二个数据
        queueQ.remaoveQ();
        queueQ.remaoveQ();
        System.out.println("删除二个数据，数据个数为"+queueQ.getNumberOfData());
    }
}
