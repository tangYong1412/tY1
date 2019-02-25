package com.tangYong.trafficConsultation;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DG dg = new DG();
        //创建城市交通图
        dg.createDG();
        testBestChoice(dg);

        //测试删除城市
        Scanner input = new Scanner(System.in);
        System.out.println("请输入删除城市名称：");
        dg.deleteCity( input.nextLine() );

        //测试添加城市及添加通往该城市的火车
        System.out.println("请输入添加城市名称：");
        dg.addCity(input.next());
        System.out.println("请输入添加火车信息（出发城市、票价、时间、下一个城市名称）：");
        String beginCity = input.next();
        double price = input.nextDouble();
        int time = input.nextInt();
        int end = dg.locateCity(input.next());
        dg.addTrain( beginCity, new Train( price, time, end, null));

        testBestChoice(dg);
    }

    //测试最优决策
    public static void testBestChoice(DG dg){
        Scanner input = new Scanner(System.in);
        System.out.println("请输入出发站：");
        String begin = input.nextLine();
        System.out.println("请输入终点站：");
        String end = input.nextLine();
        System.out.println("请输入最优选择，1、时间最短 2、价格最低：");
        int choice = input.nextInt();
        if( choice == 1 ){
            System.out.println( dg.suggest(dg, begin, end, false) );
        }else{
            System.out.println( dg.suggest(dg, begin, end, true) );
        }
    }
}
