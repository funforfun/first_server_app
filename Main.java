package com.company;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;

public class Main {

    public static void main(String[] args) throws Exception {

        MessageSystem messageSystem = new MessageSystem();

        SessionHandler sessionHandler = new SessionHandler();

        Frontend frontend = new Frontend(messageSystem, sessionHandler);
        AccountService accountService = new AccountService(messageSystem);

        (new Thread(frontend)).start();
        (new Thread(accountService)).start();


        Server server = new Server(8080);
        sessionHandler.setServer(server);
        sessionHandler.start();
        server.setSessionIdManager(sessionHandler.getSessionManager().getSessionIdManager());
        System.out.println("getSessionIdManager: " + sessionHandler.getSessionManager().getSessionIdManager());
        server.setHandler(frontend);
        server.start();
        server.join();

    }
}