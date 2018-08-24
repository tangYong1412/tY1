package com.tangYong.Ioc;

public class Animal {
    double weight;
    String type;

    public Animal(){
        weight=100;
        type="tiger";
    }

    public Animal(double weight, String type){
        this.weight=weight;
        this.type=type;
    }

    public void define(String str){
        System.out.println(str);
    }
}
