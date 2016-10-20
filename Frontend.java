package com.company;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

class Frontend extends AbstractHandler implements Runnable {

    private static AtomicInteger handleCount = new AtomicInteger(0);
    private static final int refreshTime = 1000;
    private static Logger log = Logger.getLogger("TestLogName");

    private static String pagePart0 = "<html>" +
            "<head>" +
            "<script type='text/JavaScript'>" +
            "function setClientTime(){" +
            "currentTime = new Date();" +
            "hours = currentTime.getHours();" +
            "minutes = currentTime.getMinutes();" +
            "seconds = currentTime.getSeconds();" +
            "if (minutes < 10)" +
            "minutes = '0' + minutes;" +
            "if (seconds < 10)" +
            "seconds = '0' + seconds;" +
            "timeString = hours + ':' + minutes + ':' + seconds;" +
            "document.getElementById('ClientTime').innerHTML = timeString;" +
            "}" +
            "function refresh(){" +
            "location.reload(true);" +
            "}" +
            "</script>" +
            "</head>" +
            "<body onload='setInterval(function(){refresh()}," + refreshTime + "); setClientTime();'>" +
            "<p>Client time: <span id='ClientTime'></span></p>" +
            "<p>Server time: ";

    private static String pagePart1 = "</p>" +
            "</body>" +
            "</html>";


    private static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    static String getPage() {
        return pagePart0 + getTime() + pagePart1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            log.info("" + handleCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        handleCount.incrementAndGet();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        httpServletResponse.getWriter().println(Frontend.getPage());
    }
}