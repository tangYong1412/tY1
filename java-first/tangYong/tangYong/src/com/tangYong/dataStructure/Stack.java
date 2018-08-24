package com.tangYong.dataStructure;

import java.util.ArrayList;

public class Stack <E>{
    private ArrayList<E> data=new ArrayList<E>();//储存数据
    int maxSize;//最大储存额
    int number;//元素个数

    public Stack(int maxSize){
        this.maxSize=maxSize;
        number=0;
    }//创建一个栈

    //得到栈的深度
    public int depth(){
        return maxSize;
    }

    //得到栈中元素的个数
    public int getNumberOfData(){
        return number;
    }

    //判断栈是否已满
    public boolean isFull(){
        return number==maxSize;
    }

    //加入数据
    public void push(E addData){
        if(isFull()){
            System.out.println("栈已满");
        }
        else{
            data.add(addData);
            number++;
            System.out.println("加入数据成功");
        }
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return number==0;
    }

    //删除数据
    public void pop(){
        if(isEmpty()){
            System.out.println("栈已空");
        }
        else{
            data.remove(number-1);
            --number;
        }
    }

    public static  void main(String[] args){
        Stack stackL=new Stack(5);//创建一个栈

        //初始化数据
        for(int i=0;i<stackL.depth()-1;i++)
            stackL.push(i);
        System.out.println("设置深度为"+stackL.getNumberOfData()+"的栈, 并初始化四个数据");

        //增加一个数据
        stackL.push(5);
        System.out.println("增加一个数据，数据个数为"+stackL.getNumberOfData());

        //删除二个数据
        stackL.pop();
        stackL.pop();
        System.out.println("删除二个数据，数据个数为"+stackL.getNumberOfData());
    }
}
