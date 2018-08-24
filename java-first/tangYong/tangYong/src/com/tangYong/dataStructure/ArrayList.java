package com.tangYong.dataStructure;

public class ArrayList<E> {
    private Object[] data;
    private int size;

    //默认创建一个长度为10的object数组
    public ArrayList() {
        data = new Object[10];
        size = 0;
    }

    //创建一个指定大小的Object数组
    public ArrayList(int length) {
        data = new Object[length];
        size = 0;
    }

    //返回数组中元素的个数
    public int size() {
        return size;
    }

    //返回数组的长度
    public int getNumberOfMember() {
        return size;
    }

    //判断数组是否已满，已满增加一倍长度
    public void addLength() {
        if (size == data.length) {
            Object[] newData = new Object[(data.length) * 2];//新创建一个长度为两倍的数组
            //将数据复制进入新数组
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            //将新建数组传给原数组
            data = newData;
        }
    }

    //尾部增加
    public void add(E addData) {
        addLength();//判断数组是否已满
        data[size] = addData;//尾部添加
        size++;//元素个数增加
        System.out.println("增加成功");
    }

    //特定位置增加
    public void add(int index, E addData) {
        addLength();//判断数组是否已满

        if (index >= 0 && index < size) {
            //数组元素后移
            for (int i = size - 1; i > index; i--)
                data[i + 1] = data[i];

            data[index + 1] = addData;//空位添加新数据
            size++;//元素个数增加
            System.out.println("增加成功");
        } else {
            System.out.println("位置不存在");
        }
    }

    //删除特定内容的数据
    public void removeData(E removeData) {
        for (int i = 0; i < size; i++) {
            //判断是否有该数据，有就删除
            if (data[i] == removeData) {
                for (int j = i + 1; j < size; j++)
                    data[j - 1] = data[j];

                data[size] = null;
                size--;//元素个数减少
                System.out.println("删除成功");
                break;
            }
        }
    }

    //删除指定下标的数据
    public void remove(int index) {
        if (index >= 0 && index < size) {
            for (int j = index + 1; j < size; j++)
                data[j - 1] = data[j];

            data[size] = null;
            size--;//元素个数减少
            System.out.println("删除成功");
        } else {
            System.out.println("位置不存在");
        }
    }

    //查看数据
    public Object get(int index) {
        if (index >= 0 && index < size)
            return data[index];
        else
            return "位置不存在";
    }

    public void change(int index, E changeData) {
        if (index >= 0 && index < size) {
            data[index] = changeData;
            System.out.println("修改成功");
        } else
            System.out.println("位置不存在");
    }

    public static void main(String[] args) {
        //创建一个默认长度为10的arryList
        ArrayList<Integer> myArryList = new ArrayList<Integer>();

        //加入11个元素
        for (int i = 0; i < 11; i++)
            myArryList.add(i);
        //打印
        for (int i = 0; i < 11; i++)
            System.out.print(myArryList.get(i) + " ");
        System.out.println();

        //在第一个元素后添加一个元素
        myArryList.add(0, 11);
        for (int i = 0; i < myArryList.size(); i++)
            System.out.print(myArryList.get(i) + " ");
        System.out.println();

        //改变第二个元素
        myArryList.change(2, 12);
        for (int i = 0; i < myArryList.size(); i++)
            System.out.print(myArryList.get(i) + " ");
        System.out.println();

        //删除第二个元素
        myArryList.remove(2);
        for (int i = 0; i < myArryList.size(); i++)
            System.out.print(myArryList.get(i) + " ");
        System.out.println();

        //删除数据8
        myArryList.removeData(8);
        for (int i = 0; i < myArryList.size(); i++)
            System.out.print(myArryList.get(i) + " ");
        System.out.println();
    }
}
