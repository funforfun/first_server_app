package com.company;

class AccountService implements Runnable {
    private final MessageSystem messageSystem;

    AccountService(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }

    @Override
    public void run() {

    }
}
