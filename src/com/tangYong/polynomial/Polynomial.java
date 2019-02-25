package com.tangYong.polynomial;

public class Polynomial {
    //头节点
    private PolynomialNode head;

    //单项式数量
    private int number;

    public Polynomial(){
        head = new PolynomialNode();
        number = 0;
    }

    //添加节点（尾插法）
    public boolean insert(String operator, double coefficient, double index){
        int thisOperator;
        if(operator.equals("+")){
            thisOperator = 0;
        }else if(operator.equals("-")){
            thisOperator = 1;
        }else{
            System.out.println("输入运算符不合法。");
            return false;
        }

        //创建新节点
        PolynomialNode newNode = new PolynomialNode(thisOperator, coefficient, index);

        //获取头节点
        PolynomialNode temp = head;
        //插入位置之前节点
        PolynomialNode tempBefore;
        //添加
        while(temp.getNext() != null){
            //保存之前节点
            tempBefore = temp;
            //获取下一个节点
            temp = temp.getNext();
            //如果当前节点指数小于插入指数
            if(temp.getIndex() < index){
                newNode.setNext(temp);
                tempBefore.setNext(newNode);
                number++;
                return true;
            }
        }

        temp.setNext(newNode);
        number++;
        return true;
    }

    //更新输入下标前一个
    public boolean set(int operator, int updateIndex, double coefficient, double index){
        if(updateIndex < 0 || updateIndex >= number){
            System.out.println("输入位置不合法。");
            return false;
        }


        //获取头节点
        PolynomialNode temp = head;
        //找到更新节点
        for(int i = 0; i < updateIndex; i++){
            temp = temp.getNext();
        }

        //更新系数和指数
        temp.setOperator(operator);
        temp.setCoefficient(coefficient);
        temp.setIndex(index);
        return true;
    }

    //删除节点
    public boolean delete(int index){
        if(index < 0 || index >= number){
            System.out.println("输入位置不合法。");
            return false;
        }

        //获取头节点
        PolynomialNode temp = head;
        //找到要删除节点的前一个节点
        for(int i = 0; i < index - 1; i++){
            temp = temp.getNext();
        }

        //删除
        temp.setNext(temp.getNext().getNext());
        number--;
        return true;
    }

    //相加
    public Polynomial add(Polynomial polynomial){
        //结果多项式
        Polynomial result = copyData();

        //多项式相加
        //获取头节点
        PolynomialNode resultTemp = result.getHead();
        PolynomialNode resultTempBefore;
        PolynomialNode newNode;
        PolynomialNode temp = polynomial.getHead();
        while(temp.getNext() != null){
            temp = temp.getNext();

            while(resultTemp.getNext() != null){
                resultTempBefore = resultTemp;
                resultTemp = resultTemp.getNext();

                //相同指数的项式相加
                if(resultTemp.getIndex() == temp.getIndex()){
                    //判断运算符号
                    if(resultTemp.getOperator() == temp.getOperator()){//符号相同，系数直接相加
                        resultTemp.setCoefficient(resultTemp.getCoefficient() + temp.getCoefficient());
                        //下一个相加节点和当前相加节点同次
                        resultTemp = resultTempBefore;
                        break;
                    }else{
                        //如果符号相反，系数相同,删除该节点
                        if(resultTemp.getCoefficient() == temp.getCoefficient()){
                            resultTempBefore.setNext(resultTemp.getNext());
                            result.setNumber(result.getNumber() - 1);
                            resultTemp = resultTempBefore;
                            break;
                        }

                        if(resultTemp.getCoefficient() > temp.getCoefficient()){
                            resultTemp.setCoefficient(resultTemp.getCoefficient() - temp.getCoefficient());
                            //下一个相加节点和当前相加节点同次
                            resultTemp = resultTempBefore;
                            break;
                        }

                        if(resultTemp.getCoefficient() < temp.getCoefficient()){
                            resultTemp.setOperator(temp.getOperator());
                            resultTemp.setCoefficient(temp.getCoefficient() - resultTemp.getCoefficient());
                            //下一个相加节点和当前相加节点同次
                            resultTemp = resultTempBefore;
                            break;
                        }
                    }
                }

                //小于插入
                if(resultTemp.getIndex() < temp.getIndex()){
                    newNode = new PolynomialNode(temp.getOperator(), temp.getCoefficient(), temp.getIndex());

                    newNode.setNext(resultTemp);
                    resultTempBefore.setNext(newNode);
                    result.setNumber(result.getNumber() + 1);
                    //下一个相加节点和当前相加节点同次
                    resultTemp = resultTempBefore;
                    break;
                }
            }
        }

        //相加的最后一个项比被加最后一项小
        resultTemp = result.getHead();
        while(resultTemp.getNext() != null){
            resultTemp = resultTemp.getNext();
        }
        if(temp.getIndex() < resultTemp.getIndex()){
            resultTemp.setNext(temp);
        }

        return result;
    }

    //相减
    public Polynomial sub(Polynomial polynomial){
        PolynomialNode temp = polynomial.getHead();
        while(temp.getNext() != null){
            temp = temp.getNext();
            if(temp.getOperator() == 0){
                temp.setOperator(1);
            }else{
                temp.setOperator(0);
            }
        }

        return add(polynomial);
    }

    //计算x处值
    public double calculate(int value){
        double result = 0;

        //获取头节点
        PolynomialNode temp = head;
        while(temp.getNext() != null){
            temp = temp.getNext();

            if(temp.getOperator() == 0){
                result += temp.getCoefficient()*(Math.pow(value, temp.getIndex()));
            }else{
                result -= temp.getCoefficient()*(Math.pow(value, temp.getIndex()));
            }
        }

        return result;
    }

    //求导
    public Polynomial derivation(){
        //获取头节点
        PolynomialNode temp = head;

        Polynomial result = copyData();

        PolynomialNode tempBefore;
        temp = result.getHead();
        while(temp.getNext() != null){
            tempBefore = temp;
            temp = temp.getNext();

            //如果为常数
            if(temp.getIndex() == 0){
                tempBefore.setNext(temp.getNext());
                result.setNumber(result.getNumber() - 1);
                temp = tempBefore;
            }else{
                temp.setCoefficient(temp.getIndex()*temp.getCoefficient());
                temp.setIndex(temp.getIndex()-1);
            }
        }

        return result;
    }

    //复制数据
    public Polynomial copyData(){
        //获取头节点
        PolynomialNode temp = head;

        Polynomial result = new Polynomial();
        String operator;
        while(temp.getNext() != null){
            temp = temp.getNext();
            if(temp.getOperator() == 0){
                operator = "+";
            }else{
                operator = "-";
            }
            result.insert(operator, temp.getCoefficient(), temp.getIndex());
        }

        return result;
    }

    //打印到控制台（按指数降序）
    public void display(){
        //获取头节点
        PolynomialNode temp = head;
        System.out.print(number+"   ");
        while(temp.getNext() != null){
            temp = temp.getNext();
            if(temp.getOperator() == 0)
                System.out.print("+");
            else
                System.out.print("-");
            System.out.print(temp.getCoefficient() + "——" + temp.getIndex() + "  ");
        }
    }

    public PolynomialNode getHead() {
        return head;
    }

    public void setHead(PolynomialNode head) {
        this.head = head;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
