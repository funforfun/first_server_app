package com.company;

import org.eclipse.jetty.server.Server;

/**
 * TODO:
 * 1: разобрать с местами в коде, где непонятно
 * 2: привести код в порядок
 * 3: решить новую задачу
 */
public class JettyServer
{
    public static void main(String[] args) throws Exception
    {
        simpleServer();
    }

    private static void simpleServer() throws Exception
    {
        Frontend frontend = new Frontend();
        (new Thread(frontend)).start();

        Server server = new Server(8080);
        server.setHandler(frontend);
        server.start();
        server.join();
    }
}