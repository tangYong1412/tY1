package com.tangYong.teachingPreparation;

public class ArcNode {
    //该弧所指向顶点位置
    private int adjVex;
    //指向下一条弧度
    private ArcNode nextArc;

    public ArcNode(){
        this(-1);
    }

    public ArcNode(int adjVex){
        this(adjVex, null);
    }

    public ArcNode(int adjVex, ArcNode nextArc){
        this.adjVex = adjVex;
        this.nextArc = nextArc;
    }

    public int getAdjVex() {
        return adjVex;
    }

    public void setAdjVex(int adjVex) {
        this.adjVex = adjVex;
    }

    public ArcNode getNextArc() {
        return nextArc;
    }

    public void setNextArc(ArcNode nextArc) {
        this.nextArc = nextArc;
    }

}
