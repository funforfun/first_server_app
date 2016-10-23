package com.company;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {

        MessageSystem messageSystem = new MessageSystem();

        FrontendImpl frontend = new FrontendImpl(messageSystem);
        AccountServiceImpl accountService = new AccountServiceImpl(messageSystem);

        (new Thread(frontend)).start();
        (new Thread(accountService)).start();

        Server server = new Server(8080);
        server.setHandler(frontend);
        server.start();
        server.join();

    }
}