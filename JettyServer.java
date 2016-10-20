package com.company;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

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
//        withStaticServer();
    }

    private static void simpleServer() throws Exception, InterruptedException
    {
        Frontend frontend = new Frontend();
        Thread thread = new Thread(frontend);
        thread.start();

        Server server = new Server(8080);
//        server.setHandler(new JettyServer());
//        server.start();
//        server.join();
        server.setHandler(frontend);
        server.start();
        server.join();
    }

    private static void withStaticServer() throws Exception, InterruptedException {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });

        resource_handler.setResourceBase(".");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, (Handler) new JettyServer() });
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}