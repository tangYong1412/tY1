package com.tangYong.trafficConsultation;

/**
 * 火车类
 */
public class Train {
    //列车票价
    public double price;
    //路途时间,分钟为单位
    public int time;
    //下一个城市
    public int cityNum;
    //下一条弧
    public Train train;

    public Train(double price, int time, int cityNum, Train train){
        this.price = price;
        this.time = time;
        this.cityNum = cityNum;
        this.train = train;
    }
}
