package com.company;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();
        (new Thread(frontend)).start();

        Server server = new Server(8080);
        server.setHandler(frontend);
        server.start();
        server.join();
    }
}