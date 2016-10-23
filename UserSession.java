package com.company;

import java.util.concurrent.atomic.AtomicInteger;

class UserSession {
    private static AtomicInteger currentSessionId = new AtomicInteger(1);
    private int sessionId;
    private String name;
    private int userId;

    UserSession() {
        sessionId = currentSessionId.getAndIncrement();
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
