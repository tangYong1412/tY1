package com.tangYong.Tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Tcp_One_To_One_Server{
    public static void main(String[] args){
        try {
            //创建一个服务器套接字，指明监听的端口
            ServerSocket server = new ServerSocket(10000);
            //接受创建建立
            Socket socket = server.accept();

            //接收和发送
            InputStream reader = socket.getInputStream();
            OutputStream writer = socket.getOutputStream();

            while(true) {
                //按1024个字节读
                byte[] b = new byte[1024];
                //将流中的信息放入字节
                reader.read(b);
                //将字节数组转换为字符串
                String strRead = new String(b);
                //输出到控制台
                System.out.println("客户端说：" + strRead);

                b = new byte[1024];
                //创建input
                Scanner input = new Scanner(System.in);
                //录入发送信息，并转换为字节
                String strWriter = input.nextLine();
                b = strWriter.getBytes();

                if(strWriter.equals("exit")){
                    break;
                }

                //将发送信息写入流中
                writer.write(b);

                writer.flush();

            }
            writer.close();
            reader.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
