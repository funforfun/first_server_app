package com.company;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.session.SessionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

class Frontend extends AbstractHandler implements Abonent, Runnable {

    private static AtomicInteger handleCount = new AtomicInteger(0);
    private static AtomicInteger lastUserId = new AtomicInteger(1);
    private static Logger log = Logger.getLogger("TestLogName");
    private final MessageSystem messageSystem;
    private SessionHandler sessionHandler;

    private Address address;
    private Map<String, Integer> nameToId = new HashMap<String, Integer>();
    private Map<Integer, UserSession> sessionIdToSession = new HashMap<Integer, UserSession>();


    Frontend(MessageSystem messageSystem, SessionHandler sessionHandler) {
        this.messageSystem = messageSystem;
        this.sessionHandler = sessionHandler;
        this.address = new Address();
        messageSystem.addService(this);
    }


    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
//            ThreadSleepHelper.sleep(10);
            ThreadSleepHelper.sleep(7000);
            log.info("count requests: " + handleCount);
        }
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        request.setSessionManager(sessionHandler.getSessionManager());
        log.info("Session id: " + httpServletRequest.getSession(true).getId());
        log.info("Requested Session id: " + httpServletRequest.getRequestedSessionId());

//        request.setSessionManager(sessionHandler.getSessionManager());
//        log.info("Session id: " + request.getSession().getId());
//        log.info("Session id: " + userSession.getId());
        handleCount.incrementAndGet();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        if (!request.getMethod().equals("POST")) {
            httpServletResponse.getWriter().println(AuthenticationPageGenerator.getPageWithSessionId(lastUserId.getAndIncrement()));
            log.info(request.getMethod() + " " + handleCount);
            return;
        }

        log.info("POST! " + handleCount);
        String name = request.getParameter("name");
//            String session_id = request.getParameter('session_id');
        log.info("Name from form: " + name);


        Integer id = nameToId.get(name);

        if (id != null) {
            httpServletResponse.getWriter().println("<h1>User name: " + name + " Id:" + id + "</h1>");
        } else {
            httpServletResponse.getWriter().println("<h1>Wait for authorization, " + name + "</h1>");
            Address addressAccountService = messageSystem.getAddressService().getAddress(AccountService.class);
            messageSystem.sendMessage(new MessageGetUserId(getAddress(), addressAccountService, name));
        }


//        // TODO: через куки!
//        Cookie[] cookies = httpServletRequest.getCookies();
//
//        if (cookies.length == 0) {
//            Cookie oreo = new Cookie("session_id", "" + lastUserId.getAndIncrement());
//            httpServletResponse.addCookie(oreo);
//            log.info("НОВЫЙ КУК:" + oreo.getValue());
//        } else {
//            log.info("cookies.length:" + cookies.length);
//            for (Cookie el : cookies) {
//                if (el.getName().equals("session_id")) {
//                    log.info("el.getValue():" + el.getValue());
//                }
//            }
//        }
    }

    void setId(String name, Integer id) {
        nameToId.put(name, id);
    }

    public Address getAddress() {
        return address;
    }
}