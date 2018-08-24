package com.tangYong.Udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Udp_One_To_One_Client {
    public static  void main(String[] args){
        //创建发送方的套字节，ip默认本地，端口随机
        try {
            DatagramSocket client = new DatagramSocket();
            while(true) {
                byte[] b = new byte[1024];
                Scanner input = new Scanner(System.in);
                String strSend = input.nextLine();
                b = strSend.getBytes();

                //创建发送数据报文
                DatagramPacket sendPacket = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 10011);
                //发送信息
                client.send(sendPacket);

                if(strSend.equals("exit")){
                    break;
                }

                b = new byte[1024];

                //创建接收数据报文
                DatagramPacket receivePacket = new DatagramPacket(b, b.length);
                //接收信息
                client.receive(receivePacket);
                //转换为字符串
                String strReceive = new String(b);
                System.out.println("服务端：" + strReceive);
            }
            //关闭
            client.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
