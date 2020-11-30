package com.github.wetest.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author JZWen
 * @date 2020/11/29
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            int port = 8080;
            System.out.println();
            Socket socket = null;
            server = new ServerSocket(port);
            while (true) {
                socket = server.accept();
                System.out.println("1111111");
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {

        } finally {
            if (server != null) {
                System.out.println("server close");
                server.close();
                server = null;
            }
        }

    }

}
