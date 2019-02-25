package com.tangYong.teachingPreparation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        DAG dag = new DAG();

        dag.createDAG();
        VNode[] courses = dag.getCourses();
        ArcNode arcNode;

        for( int i = 0; i < dag.getCourseNum(); i++){
            arcNode = courses[i].getFirstArc();
            while( arcNode != null ){
                System.out.println(courses[i].getCourseNo() + " " + courses[arcNode.getAdjVex()].getCourseNo());

                arcNode = arcNode.getNextArc();
            }

            System.out.println("---------------------------------------------------");
        }

        String[] str = dag.topologicalSort();
        if( str!= null){
            for(String string : str){
                System.out.print(string + " ");
            }
            System.out.println();

            Scanner input = new Scanner(System.in);
            System.out.println("请输入选择安排：1.学习负担尽量均匀； 2.课程尽可能地集中在前几个学期中");
            int choice = input.nextInt();

            String[] arrange = dag.arrangeCourse(str, choice);

            File file = new File("src/com/tangYong/teachingPreparation/text/teachingPrepare");
            try {
                if(!file.exists()){
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file, true);
                byte[] bytes = new byte[1024];

                for(int i = 0; i < arrange.length; i++){
                    bytes = ("第" + (i + 1) + "学期：" + arrange[i] + "\r\n").getBytes() ;
                    fos.write(bytes);

                    System.out.println("第" + (i + 1) + "学期：" + arrange[i]);
                }

                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("您输入的给定条件不能制定出教学计划。");
        }

    }
}
