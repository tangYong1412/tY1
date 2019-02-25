package com.tangYong.polynomial;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Polynomial polynomial = new Polynomial();

        Scanner input = new Scanner(System.in);
        System.out.println("请输入项数：");
        int number = input.nextInt();
        for(int i = 0; i < number; i++){
            System.out.println("请依次输入操作符、系数和指数。");
            while(!polynomial.insert(input.next(), input.nextInt(), input.nextInt())){
                System.out.println("输入格式错误，请重新输入：");
            }
        }

        Polynomial polynomial1 = new Polynomial();
        System.out.println("请输入项数：");
        number = input.nextInt();
        for(int i = 0; i < number; i++){
            System.out.println("请依次输入操作符、系数和指数。");
            while(!polynomial1.insert(input.next(), input.nextInt(), input.nextInt())){
                System.out.println("输入格式错误，请重新输入：");
            }
        }

        polynomial1.display();
        System.out.println();

        System.out.println("相加结果：");
        polynomial.add(polynomial1).display();
        System.out.println();

        System.out.println("相减结果：");
        polynomial.sub(polynomial1).display();
        System.out.println();

        System.out.println("请输入x的取值：");
        int value = input.nextInt();
        System.out.println("x取值" + value + "时，多项式值为" + polynomial.calculate(value));

        System.out.println("求导结果：");
        polynomial.derivation().display();
    }
}
