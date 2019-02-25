package com.tangYong.trafficConsultation;

/**
 * 弗洛伊德算法
 * 代码简化，核心思想还是戴克斯特拉算法、
 * 求两点之间最短，然后叠加组成最短路径
 */

public class ShortestPath_FLOYD {
    //前驱表，存储P[m][n]的前驱顶点，及n
    private int[][] P;
    //邻接矩阵
    private double[][] D;

    private final static double INFINITY = Double.MAX_VALUE;

    public void FLOYD(DG dg, boolean choice){
        int cityNum = dg.getCityNum();
        //前驱表
        P = new int[cityNum][cityNum];
        //邻接矩阵
        D = new double[cityNum][cityNum];

        //构建邻接矩阵，初始化前驱表
        for( int v = 0; v < cityNum; v++ ){
            for( int w = 0; w < cityNum; w++ ){
                //如果出发点和重点相同
                if( v == w ){
                    D[v][w] = 0;
                }else{
                    D[v][w] = getWeight( dg.getTrains()[v][w], choice);
                }

                P[v][w] = w;
            }
        }

        //中间经过顶点
        for( int u = 0; u < cityNum; u++){
            //循环所有路径
            for( int v = 0; v < cityNum; v++){

                for( int w = 0; w < cityNum; w++ ){

                    //如果进过路径小于原路径
                    if( D[v][u] < INFINITY && D[u][w] < INFINITY &&
                            D[v][u] + D[u][w] < D[v][w] ){
                        D[v][w] =  D[v][u] + D[u][w];

                        //前驱设置为经过下标为u的顶点
                        P[v][w] = P[v][u];
                    }
                }
            }
        }
    }

    //获取权值，票价、时间
    private double getWeight(Train train, boolean choice){
        //如果没有直接路径
        if( train == null ){
            return INFINITY;
        }

        //票价最低
        if( choice ){
            return train.price;
        }else{
            return train.time;
        }
    }

    public int[][] getP() {
        return P;
    }

    public void setP(int[][] p) {
        P = p;
    }

    public double[][] getD() {
        return D;
    }

    public void setD(double[][] d) {
        D = d;
    }
}
