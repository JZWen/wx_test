package com.github.wetest.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author JZWen
 * @date 2020/11/29
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("receive message : " + body);
                out.println(new Date().getTime() + "   test");
            }
        } catch (Exception e) {

        } finally {
            try {
                in.close();
                out.close();
                this.socket.close();
                System.out.println("IO socket close");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
