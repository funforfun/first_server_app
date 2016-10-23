package com.company;

import java.util.concurrent.atomic.AtomicInteger;

class UserSession {
    private static AtomicInteger currentSessionId = new AtomicInteger(1);
    private Integer sessionId;
    private String name;

    UserSession() {
        sessionId = currentSessionId.getAndIncrement();
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
