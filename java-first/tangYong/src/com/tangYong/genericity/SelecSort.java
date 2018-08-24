package com.tangYong.genericity;

public class SelecSort {
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
        selectionSort(list);
    }

    public static <E extends Comparable<E>>  void selectionSort(E[] e){
        //元素最小的数组的下标
        int minIndex=0;

        //储存转换中间值
        E temp;

        //对list从小到大选择排序
        for(int i=0;i<e.length;i++){
            minIndex=i;
            for(int j=i+1;j<e.length;j++){
                if(e[minIndex].compareTo(e[j])>0){
                    temp=e[minIndex];
                    e[minIndex]=e[j];
                    e[j]=temp;
                }
            }
        }

        System.out.println("排序之后");
        for(int i=0;i<10;i++){
            System.out.print(e[i]+" ");
        }
    }
}
