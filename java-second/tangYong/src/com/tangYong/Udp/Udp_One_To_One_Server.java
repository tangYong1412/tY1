package com.tangYong.Udp;


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Udp_One_To_One_Server {
    public static void main(String[] args){
        try {

            //创建套接字，标明监听端口和ip地址
            DatagramSocket sever = new DatagramSocket(10011, InetAddress.getLocalHost());

            while(true) {
                byte[] b = new byte[1024];
                //创建接受数据报文
                DatagramPacket receivePackege = new DatagramPacket(b, b.length);
                //通过数据套接收信息
                sever.receive(receivePackege);
                //转换为字符串
                String strReceive = new String(b);
                System.out.println("客户端：" + strReceive);

                b = new byte[1024];
                //创建发送数据报文,通过接收的数据报文得到发送ip
                Scanner input = new Scanner(System.in);
                String strSend = input.nextLine();
                //转换为字节
                b = strSend.getBytes();

                if(strSend.equals("exit")){
                    break;
                }
                //数据放入数据报文
                DatagramPacket sendPackege = new DatagramPacket(b, b.length, receivePackege.getSocketAddress());

                //发送
                sever.send(sendPackege);
            }
            //关闭
            sever.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
