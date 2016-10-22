package com.company;

class MessageToAccountService extends Message{

    MessageToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    void exec(Abonent abonent) {

    }
}
