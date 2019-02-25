package com.tangYong.trafficConsultation;

import java.util.Scanner;

/**
 *有向图，构成城市交通
 */
public class DG {
    //城市数量、列车数量
    private int cityNum, trainNum;

    //城市数组
    private City[] cities;

    //列车数组
    private Train[][] trains;

    public DG(){
        this(0, 0);
    }

    public DG(int cityNum, int trainNum){
        this.cityNum = cityNum;
        this.trainNum = trainNum;
    }

    //创建有向图
    public void createDG(){
//        Scanner input = new Scanner(System.in);
//        System.out.println("请输入城市数量：");
//        cityNum = input.nextInt();
//        System.out.println("请输入列车数量：");
//        trainNum = input.nextInt();
        cityNum = 11;
        trainNum = 32;

        //初始化城市数组
        cities = new City[cityNum];
        //初始化列车数组
        trains = new Train[cityNum][cityNum];

        //请输入城市名称
//        System.out.println("请依次输入各城市的名称（如重庆）：");
//        for(int i = 0; i < cityNum; i++){
//            cities[i] = new City(input.next(), null );
//        }

        //测试数据
        cities[0] = new City("西宁", null);
        cities[1] = new City("兰州", null);
        cities[2] = new City("贵阳", null);
        cities[3] = new City("重庆", null);
        cities[4] = new City("广州", null);
        cities[5] = new City("株洲", null);
        cities[6] = new City("郑州", null);
        cities[7] = new City("南昌", null);
        cities[8] = new City("北京", null);
        cities[9] = new City("天津", null);
        cities[10] = new City("上海", null);

        //输入各城市之间的直接路径
//        String str;
//        String begin = "";
//        String end = "";
//        int currentCity;
//        int nextCity;
//        double price = 0;
//        int time;
//        int number = 0;
//        int index = 0;
//
//        System.out.println("请输入各城市之间的直接路径、火车的票价以及乘坐时间（分钟为单位）（如:重庆 成都 90 90）：");
//        for( int i = 0; i < trainNum; i++){
//            str = input.nextLine();
//            for( int j = 0; j < str.length(); j++){
//                if( str.charAt(j) == ' '){
//                    if( number == 0 ){
//                        begin = str.substring( index, j );
//                    }else if( number == 1 ){
//                        end = str.substring( index, j );
//                    }else {
//                        price = Double.valueOf( str.substring( index, j ) );
//                    }
//
//                    index = j + 1;
//                    number++;
//                }
//            }
//            //时间
//            time = Integer.valueOf( str.substring( index,  str.length() ) );
//            index = number = 0;
//
//            //出发城市
//            currentCity = locateCity( begin );
//            //终点城市
//            nextCity = locateCity( end );
//            //添加新的列车
//            addTrain( currentCity, nextCity, price, time );
//            trains[currentCity][nextCity] = new Train( price ,time, nextCity, null);
//        }

        //测试数据
        addTrain( 0, 1, 10, 10 );
        trains[0][1] = new Train( 10, 10, 1, null );
        addTrain( 1, 0, 10, 10 );
        trains[1][0] = new Train( 10, 10, 0, null );


        addTrain( 1, 8, 20, 20 );
        trains[1][8] = new Train( 20, 20, 8, null );
        addTrain( 8, 1, 20, 20 );
        trains[8][1] = new Train( 20, 20, 1, null );


        addTrain( 2, 8, 30, 30 );
        trains[2][8] = new Train( 30, 30, 8, null );
        addTrain( 8, 2, 30, 30 );
        trains[8][2] = new Train( 30, 30, 2, null );


        addTrain( 3, 8, 40, 40 );
        trains[3][8] = new Train( 40, 40, 8, null );
        addTrain( 8, 3, 40, 40 );
        trains[8][3] = new Train( 40, 40, 3, null );


        addTrain( 6, 8, 50, 50 );
        trains[6][8] = new Train( 50, 50, 8, null );
        addTrain( 8, 6, 50, 50 );
        trains[8][6] = new Train( 50, 50, 6, null );


        addTrain( 8, 9, 60, 60 );
        trains[8][9] = new Train( 60, 60, 9, null );
        addTrain( 9, 8, 60, 60 );
        trains[9][8] = new Train( 60, 60, 8, null );


        addTrain( 9, 10, 70, 70 );
        trains[9][10] = new Train( 70, 70, 10, null );
        addTrain( 10, 9, 70, 70 );
        trains[10][9] = new Train( 70, 70, 9, null );


        addTrain( 7, 10, 80, 80 );
        trains[7][10] = new Train( 80, 80, 10, null );
        addTrain( 10, 7, 80, 80 );
        trains[10][7] = new Train( 80, 80, 7, null );


        addTrain( 6, 7, 90, 90 );
        trains[6][7] = new Train( 90, 90, 7, null );
        addTrain( 7, 6, 90, 90 );
        trains[7][6] = new Train( 90, 90, 6, null );


        addTrain( 5, 7, 100, 100 );
        trains[5][7] = new Train( 100, 100, 7, null );
        addTrain( 7, 5, 100, 100 );
        trains[7][5] = new Train( 100, 100, 5, null );


        addTrain( 5, 6, 110, 110 );
        trains[5][6] = new Train( 110, 110, 6, null );
        addTrain( 6, 5, 110, 110 );
        trains[6][5] = new Train( 110, 110, 5, null );


        addTrain( 2, 5, 120, 120 );
        trains[2][5] = new Train( 120, 120, 5, null );
        addTrain( 5, 2, 120, 120 );
        trains[5][2] = new Train( 120, 120, 2, null );


        addTrain( 2, 4, 130, 130 );
        trains[2][4] = new Train( 130, 130, 4, null );
        addTrain( 4, 2, 130, 130 );
        trains[4][2] = new Train( 130, 130, 2, null );


        addTrain( 1, 2, 140, 140 );
        trains[1][2] = new Train( 140, 140, 2, null );
        addTrain( 2, 1, 140, 140 );
        trains[2][1] = new Train( 140, 140, 1, null );


        addTrain( 2, 3, 150, 150 );
        trains[2][3] = new Train( 150, 150, 3, null );
        addTrain( 3, 2, 150, 150 );
        trains[3][2] = new Train( 150, 150, 2, null );


        addTrain( 3, 4, 160, 160 );
        trains[3][4] = new Train( 160, 160, 4, null );
        addTrain( 4, 3, 160, 160 );
        trains[4][3] = new Train( 160, 160, 3, null );
    }

    //添加弧，头插法，将弧节点插入对应边表
    private void addTrain(int currentCity, int nextCity, double price, int time){
        //新的列车,票价、时间、到达城市、下一条弧
        Train train = new Train(price, time, nextCity, cities[currentCity].firstTrain);

        //头插法
        cities[currentCity].firstTrain = train;
    }

    //根据顶点的值，返回其在数组中的位置，如果不存在返回-1
    public int locateCity(String cityName){
        for( int i = 0; i < cityNum; i++){
            if( cities[i].cityName.equals( cityName ) ){
                return i;
            }
        }

        return -1;
    }

    //最少时间、或省钱
    public String suggest(DG dg, String begin, String end, boolean choice){
        //获取出发城市下标
        int beginI = locateCity( begin );
        int endI = locateCity( end );

        //如果存在这两个城市
        if( beginI != -1 && endI != -1 ){
            ShortestPath_FLOYD shortestPath_floyd = new ShortestPath_FLOYD();
            shortestPath_floyd.FLOYD( dg, choice );
            //获取最短路径
            int[][] P = shortestPath_floyd.getP();
            String result = "";
            int k = 0;
            for(int  v = 0; v < P.length; v++){
                for(int w = 0; w < P[v].length; w++){
                   //循环到对应城市
                    if( v == beginI && w == endI ){
                        //获取前驱表最近一个前驱
                        k = P[v][w];
                        result = cities[v].cityName;
                        while( k != w){
                            result = result.concat( "->" + cities[k].cityName );
                            k = P[k][w];
                        }
                        result = result.concat( "->" + cities[w].cityName );
                   }

                }
            }

            return result;
        }else{
            return null;
        }
    }

    //复制信息
    public Object[] arrayCopy(Object[] src, int srcPos, Object[] dest, int destPos, int length){
        for(int i = 0 ; i < length ; i++ ){
            dest[destPos++] = src[srcPos++];
        }

        return dest;
    }

    //添加城市
    public void addCity(String cityName){
        City[] cities = new City[++cityNum];

        //复制信息
        cities = (City[])arrayCopy(this.cities, 0, cities, 0, cityNum -1);

        cities[cityNum - 1] = new City(cityName, null);
        this.cities = cities;
    }


    //删除城市
    public City deleteCity(String cityName){
        int index = locateCity(cityName);

        //如果城市存在
        if( index != -1 ){
            City city;
            City[] cities = new City[--cityNum];

            if( index == 0 ){//删除开始位置
                cities = (City[]) arrayCopy(this.cities, 1, cities, 0, cityNum);
                city = this.cities[0];
            }else if( index == cityNum - 1 ){//删除最后一位置
                cities = (City[]) arrayCopy(this.cities, 0, cities, 0, cityNum);
                city = this.cities[cityNum];
            }else{
                cities = (City[]) arrayCopy(this.cities, 0, cities, 0, index);
                cities = (City[]) arrayCopy(this.cities, index + 1, cities, index, cityNum - index);
                city = this.cities[index];
            }
            deleteTrainEnd(cityName, index);
            this.cities = cities;
            return city;
        }

        return null;
    }

    //删除以该城市为终点的火车
    public void deleteTrainEnd(String cityName, int index){
        for( int i = 0; i < this.cities.length; i++ ){
            //如果不是被删除的城市
            if( i != index){
                Train train = this.cities[i].firstTrain;

                //如果第一个火车的终点是被删除的城市
                if( train != null && train.cityNum == index ){
                    this.cities[i].firstTrain = train.train;
                    //删除火车
                    deleteTrain(this.cities[i].cityName, cityName);
                }else{//第一个火车的终点站不是被删除的城市
                    Train tempTrain = null;
                    while( train != null ){
                        //如果这个火车的终点是被删除的城市
                        if( train.cityNum == index ){
                            tempTrain.train = train.train;
                            //删除火车
                            deleteTrain(this.cities[i].cityName, cityName);
                            break;
                        }

                        tempTrain = train;
                        train = train.train;
                    }
                }
            }
        }
    }

    //添加火车，不支持在原来已有基础上添加
    public void addTrain(String beginCity, Train train){
        int begin = locateCity(beginCity);

        //如果出发和终点城市存在
        if( begin != -1 && ( train.cityNum >= 0 && train.cityNum < cityNum ) && train != null){
            //头插法添加弧
            train.train = cities[begin].firstTrain;
            cities[begin].firstTrain = train;

            //火车数组中添加
            int end = train.cityNum;
            //复制邻接矩阵
            Train[][] trains = new Train[cityNum][cityNum];
            for( int i = 0; i < cityNum - 1; i++ ){
                for( int j =0; j < cityNum - 1; j++){
                    trains[i][j] = this.trains[i][j];
                }
            }

            trains[begin][end] = train;

            this.trains = trains;
        }
    }

    //删除火车
    public Train deleteTrain(String beginCity, String endCity){
        int begin = locateCity(beginCity);
        int end = locateCity(endCity);

        //如果城市存在
        if( begin != -1 && end != -1 ){
            for(int i = 0; i < this.trains.length; i++){
                for( int j = 0; j < this.trains[i].length; j++ ){
                    //删除城市之间的火车
                    if( i == begin && j == end ){
                        Train train;
                        Train[] trains = new Train[this.trains[i].length - 1];

                        if( j == 0 ){//删除开始位置
                            trains = (Train[]) arrayCopy(this.trains[i], 1, trains, 0, this.trains[i].length - 1);
                            train = this.trains[i][0];
                        }else if( j == this.trains[i].length - 1 ){//删除最后一位置
                            trains = (Train[]) arrayCopy(this.trains[i], 0, trains, 0, this.trains[i].length - 1);
                            train = this.trains[i][this.trains[i].length - 1];
                        }else{
                            trains = (Train[]) arrayCopy(this.trains[i], 0, trains, 0, j);
                            trains = (Train[]) arrayCopy(this.trains[i], j + 1, trains, j, this.trains[i].length - 1 - j);
                            train = this.trains[i][j];
                        }

                        this.trains[i] = trains;
                        return train;
                    }
                }
            }
        }

        return null;
    }

    //查询火车信息
    public Train checkTrain( String beginCity, String endCity ){
        int begin = locateCity(beginCity);
        int end = locateCity(endCity);

        if( begin != -1 && end != -1 ){
            return trains[begin][end];
        }

        return null;
    }

    //修改火车信息，只能修改价格和时间
    public void updateTrain( double price, int time, String beginCity, String endCity ){
        int begin = locateCity(beginCity);
        int end = locateCity(endCity);

        //如果城市存在
        if( begin != -1 && end != -1 ){
            trains[begin][end].price = price;
            trains[begin][end].time = time;
        }
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public int getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public Train[][] getTrains() {
        return trains;
    }

    public void setTrains(Train[][] trains) {
        this.trains = trains;
    }
}
