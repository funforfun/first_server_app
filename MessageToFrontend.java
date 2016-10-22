package com.company;

abstract class MessageToFrontend extends Message {

    MessageToFrontend(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent abonent) {
        if (abonent instanceof Frontend) {
            exec((Frontend) abonent);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + abonent.getClass());
        }
    }

    abstract void exec(Frontend frontend);
}
