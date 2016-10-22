package com.company;

import java.util.HashMap;
import java.util.Map;

class AccountService implements Abonent, Runnable {
    private Address address;
    private final MessageSystem messageSystem;
    private Map<String, Integer> fakeAccounter = new HashMap<String, Integer>();

    AccountService(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
        this.fakeAccounter.put("Tully", 1);
        this.fakeAccounter.put("Sully", 2);
    }

    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            ThreadSleepHelper.sleep(10);
        }

    }

    Integer getUserId(String name) {
        ThreadSleepHelper.sleep(500);
        return fakeAccounter.get(name);
    }

    MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getAddress() {
        return address;
    }
}
