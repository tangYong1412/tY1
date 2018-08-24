package com.tangYong.genericity;

public class BubbleSort {
    public static  void main(String[] args) {
        //定义一个arryList
        Integer[] list=new Integer[10];

        //循环赋值.由大到小
        for(int i=10;i>0;i--){
            list[10-i]=i;
        }

        System.out.println("排序之前");
        //排序之前
        for(int i=0;i<10;i++){
            System.out.print(list[i]+" ");
        }
        System.out.println();

        //调用选择排序
        bubblationSort(list);
    }

    public static <E extends Comparable<E>>  void bubblationSort(E[] e){
        //储存转换中间值
        E temp;

        //对list从小到大选择排序
        for(int i=0;i<e.length;i++){
            for(int j=0;j<e.length-i-1;j++){
                if(e[j].compareTo(e[j+1])>0){
                    temp=e[j];
                    e[j]=e[j+1];
                    e[j+1]=temp;
                }
            }
        }

        System.out.println("排序之后");
        for(int i=0;i<10;i++){
            System.out.print(e[i]+" ");
        }
    }
}
