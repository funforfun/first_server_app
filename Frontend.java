package com.company;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

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
    private static AtomicInteger lastUserId = new AtomicInteger(0);
    private static Logger log = Logger.getLogger("TestLogName");
    private final MessageSystem messageSystem;

    private static String GAME_NAME = "/test/";
    private Address address;
    private Map<String, Integer> nameToId = new HashMap<String, Integer>();


    Frontend(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
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

        handleCount.incrementAndGet();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        httpServletResponse.getWriter().println(PageGenerator.getPage());

        if (true) {
            return;
        }
        /*
         * Вариант через форму:
         * if(request.getMethod().equals("POST")){
         *  request.getParameter('session_id');
         * }
         */

        if (!s.equals(GAME_NAME)) {
            return;
        }

        String name = "Tully";
        Integer id = nameToId.get(name);

        if (id != null) {
            httpServletResponse.getWriter().println("<h1>User name: " + name + " Id:" + id + "</h1>");
        } else {
            httpServletResponse.getWriter().println("<h1>Wait for authorization</h1>");
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