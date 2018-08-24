package com.tangYong.Tcp;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Tcp_One_To_One_Client {
    public static void main(String[] args){
        //创建一个服务器套接字，指定监听端口和ip
        try {
            //创建一个流套接字指定ip和监听端口
            Socket client = new Socket(InetAddress.getLocalHost(),10000);
            //发送和接收
            OutputStream writer = client.getOutputStream();
            InputStream reader = client.getInputStream();

            while(true){
                byte[] b = new byte[1024];
                //创建一个input
                Scanner input = new Scanner(System.in);
                String strWriter = input.nextLine();
                //转换为字节
                b = strWriter.getBytes();
                //将信息输入流中
                writer.write(b);

                if(strWriter.equals("exit")){
                    break;
                }

                b = new byte[1024];

                //将信息读入b中
                reader.read(b);
                //将字节转换为字符串
                String strReader = new String(b);
                System.out.println("服务端：" + strReader);

                writer.flush();

            }
            writer.close();
            reader.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
