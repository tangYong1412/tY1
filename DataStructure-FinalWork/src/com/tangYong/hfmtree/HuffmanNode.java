package com.tangYong.hfmtree;

public class HuffmanNode {
    private int weight;
    private int flag;
    private char letter;
    private HuffmanNode parent, lChild, rChild;
    private double[] center;//中心坐标

    public HuffmanNode(){
        this(0);
    }

    public HuffmanNode(int weight){
        this.weight = weight;
        flag = 0;
        letter = '*';
        parent = lChild = rChild = null;
        center = new double[2];

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public HuffmanNode getParent() {
        return parent;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public HuffmanNode getlChild() {
        return lChild;
    }

    public void setlChild(HuffmanNode lChild) {
        this.lChild = lChild;
    }

    public HuffmanNode getrChild() {
        return rChild;
    }

    public void setrChild(HuffmanNode rChild) {
        this.rChild = rChild;
    }

    public double[] getCenter() {
        return center;
    }

    public void setCenter(double[] center) {
        this.center = center;
    }
}
