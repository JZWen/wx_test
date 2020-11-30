package com.github.wetest.netty.nio;

import org.apache.catalina.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @author JZWen
 * @date 2020/11/30
 */
public class MultServerHandler implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private volatile boolean stop;

    public MultServerHandler(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("socket port ： " + port);
        } catch (Exception e) {
            System.out.println("socket error ");
        }
    }

    @Override
    public void run() {

        while (!stop) {

            try {
                selector.select(1000);

                //拿到所有的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
