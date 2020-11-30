package com.github.wetest.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author JZWen
 * @date 2020/11/29
 */
public class TimeClient {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("client test");
            System.out.println("------------------------");
            String resp = in.readLine();
            System.out.println("resp = " + resp);

        } catch (Exception e) {

        } finally {
            out.close();
            in.close();
            socket.close();

        }
    }


}
