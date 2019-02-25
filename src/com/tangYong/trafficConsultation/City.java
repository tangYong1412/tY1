package com.tangYong.trafficConsultation;

/**
 *城市类
 */

public class City {
    //城市名称
    public String cityName;
    //第一条依附弧
    public Train firstTrain;

    public City(String cityName, Train firstTrain){
        this.cityName =cityName;
        this.firstTrain = firstTrain;
    }
}
