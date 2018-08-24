package com.tangYong.Ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Ioc {

    public Ioc(){

    }

    public void getIoc(Class t){
        //获取里面t这个描述对象中的全部属性
        Field[] fields=t.getDeclaredFields();

        //遍历这些属性，查找到修改目标
        for(Field field: fields){
            //得到属性的注解的描述对象
            Annotation annotation=field.getAnnotation(Cell.class);
            //如果该属性没有注解，进行下一次循环
            if(annotation==null)
                continue;
            //如果有注解，判断该属性是不是要修改的目标
            if(field.getName().equals("animal")){
                //打印注解的value值
                System.out.println(field.getName()+"的注解值是："+((Cell) annotation).value());

                try {
                    //通过注解value值，获得Animal的权限定名称，然后获取Animal类的描述对象
                    Class animal=Class.forName(((Cell) annotation).value());
                    //改变私有属性为true>>可修改私有属性
                    field.setAccessible(true);
                    //开始注入Animal类的对象
                    field.set(t.newInstance(), (Animal)animal.newInstance());
                    //开始验证是否注入成功：通过getAnimal方法得到注入的animal，修改animal中的define方法验证
                    String methodName="get"+field.getName().toUpperCase().charAt(0)+field.getName().toLowerCase().substring(1);
                    //得到getAnimal方法
                    Method method=t.getMethod(methodName);
                    //通过invoke方法得到t中的animal
                    Animal animal2=(Animal)method.invoke(t.newInstance());
                    animal2.define("我注入成功了");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        //创建Ioc的实例
        Ioc ioc=new Ioc();
        //获得Birder的描述对象
        Class birder=Birder.class;

        //将描述对象传入
        ioc.getIoc(birder);


    }
}
