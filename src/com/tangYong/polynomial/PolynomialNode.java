package com.tangYong.polynomial;

public class PolynomialNode {

    //运算符
    private int operator;

    //系数
    private double coefficient;

    //指数
    private double index;

    //指针
    private PolynomialNode next;

    public PolynomialNode(){
        this(0, 0, 0);
    }

    public PolynomialNode(int operator, double coefficient, double index){
        this(operator,coefficient, index, null);
    }

    public PolynomialNode(int operator, double coefficient, double index, PolynomialNode next){
        this.operator = operator;
        this.coefficient = coefficient;
        this.index = index;
        this.next = next;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    public PolynomialNode getNext() {
        return next;
    }

    public void setNext(PolynomialNode next) {
        this.next = next;
    }

}
