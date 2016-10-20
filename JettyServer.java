package com.company;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO:
 * 1: разобрать с местами в коде, где непонятно
 * 2: привести код в порядок
 * 3: решить новую задачу
 */
public class JettyServer extends AbstractHandler
{


    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {

//        log.info("New message!");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println(Frontend.getPage());

//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        baseRequest.setHandled(true);
//        PageGenerator pg = new PageGenerator();
//      String session_id = request.getParameter("session_id");
//
//      int id;
//
//      if(session_id != null){
//    	  id = Integer.parseInt(request.getParameter("session_id"));
//      }
//      else{
//          id = at.getAndIncrement();
//      }
//
//        response.getWriter().println(pg.getWebPage(id));
//        System.out.println("**********");
//    	System.out.println(request.getParameter("session_id"));
//    	System.out.println(id);
//
//    	// request.getParameter(), getParameterValues() or getParameterMap()
//
//    	System.out.println("**********");
////        response.getWriter().println("<h1>Your sessionid: " + ai.get() +"</h1>");

    }

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