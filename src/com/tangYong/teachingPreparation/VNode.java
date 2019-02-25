package com.tangYong.teachingPreparation;

public class VNode {
    //课程编号
    private String courseNo;
    //课程学分
    private double credit;
    //第一跳依附的边
    private ArcNode firstArc;

    public VNode(){
        this(null);
    }

    public VNode(String courseNo){
        this(courseNo, 0);
    }

    public VNode(String courseNo, double credit){
        this(courseNo, credit, null);
    }

    public VNode(String courseNo, double credit,  ArcNode firstArc){
        this.courseNo = courseNo;
        this.credit = credit;
        this.firstArc = firstArc;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public ArcNode getFirstArc() {
        return firstArc;
    }

    public void setFirstArc(ArcNode firstArc) {
        this.firstArc = firstArc;
    }

}
